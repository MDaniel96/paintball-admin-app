import React, {FC, useEffect, useState} from 'react';
import {ScrollView, StyleSheet, Text, View} from 'react-native';
import {Button, Card, List, TextInput} from 'react-native-paper';
import Colors from '../constants/Colors';
import {Game} from '../model/Game';
import {GameService} from '../service/GameService';
import PlayerListItem from '../components/PlayerListItem';
import {User} from '../model/User';
import {Audio} from 'expo-av';
import * as FileSystem from 'expo-file-system';
import AudioRecordPanel from '../components/AudioRecordPanel';

const RunningGamesEditScreen: FC = (props: any) => {

    const [game, setGame] = useState<Game>(props.route.params.game);

    const [nameInput, setNameInput] = useState<string>('');
    const [typeInput, setTypeInput] = useState<string>('');

    const [isAudioRecordShown, setIsAudioRecordShown] = useState<boolean>(false);

    const [recording, setRecording] = useState<any>();
    const [base64, setBase64] = useState<string>('');

    useEffect(() => {
        GameService.getGame(game.id).then((data) => {
            setFields(data);
        });
    }, []);

    const setFields = (gameData: Game) => {
        setGame(gameData);
        setNameInput(gameData.name);
        setTypeInput(gameData.type);
    };

    const saveGame = () => {
        let newGame = new Game();
        newGame.name = nameInput;
        newGame.type = typeInput;
        newGame.state = game.state;
        newGame.date = game.date;
        GameService.editGame(game.id, newGame).then(data => {
            setFields(data);
        });
    }

    const changeState = () => {
        const nextState = game.state === 'STARTED' ? 'FINISHED' : 'STARTED';
        GameService.changeGameState(game.id, nextState).then(data => {
            setFields(data);
        });
    };

    const getNextState = (): string => {
        if (game.state === 'STARTED') {
            return 'End';
        } else if (game.state === 'CREATED') {
            return 'Start';
        }
        return '';
    };

    const getStateColor = (): string => {
        if (game.state === 'STARTED') {
            return 'green'
        } else if (game.state === 'CREATED') {
            return 'orange'
        }
        return 'red';
    };

    const kickPlayer = (player: User) => {
        const color = game.redPlayers.find(p => p === player) ? 'red' : 'blue';
        GameService.kickPlayerFromGame(game.id, player.id, color).then(data => {
            setGame(data);
        });
    };

    const isPlayerDead = (player: User): boolean => {
        return !!game.deadPlayers.find(p => p.id === player.id);
    };

    const openVoiceMessagePanel = () => {
        setIsAudioRecordShown(true);
    }

    const cancelRecording = async () => {
        setIsAudioRecordShown(false);
        if (recording) {
            await stopRecording();
        }
        setRecording(undefined);
        setBase64('');
    }

    const startRecording = async () => {
        try {
            await Audio.requestPermissionsAsync();
            await Audio.setAudioModeAsync({
                allowsRecordingIOS: true,
                playsInSilentModeIOS: true,
            });
            const audioRecording = new Audio.Recording();
            await audioRecording.prepareToRecordAsync(Audio.RECORDING_OPTIONS_PRESET_HIGH_QUALITY);
            await audioRecording.startAsync();
            setRecording(audioRecording);
        } catch (err) {
            console.error('Failed to start recording', err);
        }
    }

    const stopRecording = async () => {
        setRecording(undefined);
        await recording.stopAndUnloadAsync();
        const uri = recording.getURI();
        let fileBase64 = await FileSystem.readAsStringAsync(uri, {encoding: 'base64'});
        setBase64(fileBase64);
    };

    const sendRecording = (target: string) => {
        setIsAudioRecordShown(false);
        GameService.sendVoiceMessage(game.id, target, base64)
            .then(result => {
                setBase64('');
                console.log(result)
            })
            .catch(err => console.log(err));
    }

    return (
        <ScrollView>
            <View style={styles.container}>
                <Card>
                    <Card.Title
                        title={`Edit ${game.name}`}/>
                    <View style={styles.subtitle}>
                        <Text>{game.state}</Text>
                        <List.Icon icon="circle" color={getStateColor()}/>
                    </View>
                    <Card.Content>
                        <TextInput
                            style={styles.textInput}
                            label="Game name"
                            onChangeText={(text) => setNameInput(text)}
                            value={nameInput}/>
                        <TextInput
                            style={styles.textInput}
                            label="Game type"
                            onChangeText={(text) => setTypeInput(text)}
                            value={typeInput}/>
                        <TextInput
                            style={styles.textInput}
                            label="Localization mode"
                            onChangeText={(text) => setTypeInput(text)}
                            value={game.localizationMode}
                            disabled/>
                        <Button style={styles.button} mode="outlined"
                                onPress={saveGame}>
                            Save
                        </Button>
                        {
                            game.state !== 'FINISHED' &&
                            <View>
                                <Button style={styles.button} mode="contained"
                                        onPress={changeState}>
                                    {`${getNextState()} game`}
                                </Button>
                                <Button style={styles.button} mode="outlined"
                                        onPress={openVoiceMessagePanel}>
                                    Send voice message
                                </Button>
                            </View>
                        }
                    </Card.Content>
                </Card>
                <Card style={{marginTop: 30}}>
                    <Card.Content>
                        <List.Accordion
                            title="Map"
                            left={() => <List.Icon {...props} color={Colors.black} icon="map"/>}>
                            <List.Item title={game.map?.name}/>
                        </List.Accordion>
                        <List.Accordion
                            title="Red team players"
                            left={() => <List.Icon {...props} color={Colors.red} icon="circle"/>}>
                            {game.redPlayers.map(player => (
                                <PlayerListItem key={player.id} player={player} dead={isPlayerDead(player)}
                                                kickPlayer={kickPlayer}/>
                            ))}
                        </List.Accordion>
                        <List.Accordion
                            title="Blue team players"
                            left={() => <List.Icon {...props} color={Colors.blue} icon="circle"/>}>
                            {game.bluePlayers.map(player => (
                                <PlayerListItem key={player.id} player={player} dead={isPlayerDead(player)}
                                                kickPlayer={kickPlayer}/>
                            ))}
                        </List.Accordion>
                    </Card.Content>
                </Card>
            </View>
            <AudioRecordPanel
                show={isAudioRecordShown}
                onCancel={cancelRecording}
                onSend={sendRecording}
                startRecording={startRecording}
                stopRecording={stopRecording}
                hasRecording={base64 !== ''}
            />
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: Colors.white,
        padding: 15
    },
    textInput: {
        marginBottom: 10
    },
    subtitle: {
        flexDirection: "row",
        alignItems: "center",
        marginBottom: 30,
        paddingLeft: 20
    },
    button: {
        marginBottom: 10
    }
});

export default RunningGamesEditScreen;
