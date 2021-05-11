import {Button, ScrollView, StyleSheet, View} from 'react-native';
import React, {FC, useEffect, useState} from 'react';
import Colors from '../constants/Colors';
import {Game} from '../model/Game';
import {GameService} from '../service/GameService';
import {Card, List, TextInput} from 'react-native-paper';
import DateTimePickerModal from 'react-native-modal-datetime-picker';

const PreviousGameEditScreen: FC = (props: any) => {

    const gameId = props.route.params.gameId;

    const [game, setGame] = useState<Game>(new Game());

    const [nameInput, setNameInput] = useState<string>('');
    const [typeInput, setTypeInput] = useState<string>('');
    const [dateInput, setDateInput] = useState<Date>(new Date());

    const [isDateEditable, setDateEditable] = useState<boolean>(true);
    const [isDatePickerVisible, setDatePickerVisibility] = useState<boolean>(false);

    useEffect(() => {
        GameService.getGame(gameId).then((data) => {
            setGame(data);
            setNameInput(data.name);
            setTypeInput(data.type);
            setDateInput(new Date(data.date));
        });
    }, [gameId]);

    const saveGame = () => {
        let newGame = new Game();
        newGame.name = nameInput;
        newGame.type = typeInput;
        newGame.state = game.state;
        newGame.date = dateInput;
        GameService.editGame(game.id, newGame).then((data) => {
            setGame(data)
        });
    }

    return (
        <View style={styles.container}>
            <Card>
                <Card.Title title={`Edit ${game.name}`} subtitle="Finished" style={{marginBottom: 30}}/>
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
                        label="Game date"
                        editable={isDateEditable}
                        onTouchStart={() => {
                            setDatePickerVisibility(true)
                            setDateEditable(false);
                        }}
                        value={dateInput.toLocaleDateString()}/>
                    <TextInput
                        style={styles.textInput}
                        label="Connection mode"
                        onChangeText={(text) => setTypeInput(text)}
                        value={game.connectionMode}
                        disabled/>
                    <Button
                        onPress={saveGame}
                        title={'Save'}/>
                    <DateTimePickerModal
                        isVisible={isDatePickerVisible}
                        mode="date"
                        onConfirm={(newDate: Date) => {
                            setDatePickerVisibility(false);
                            setDateEditable(true);
                            setDateInput(newDate);
                        }}
                        onCancel={() => {
                            setDatePickerVisibility(false);
                            setDateEditable(true);
                        }}
                    />
                </Card.Content>
            </Card>

            <ScrollView>
                <Card style={{marginTop: 30}}>
                    <Card.Content>
                        <List.Accordion
                            title="Map"
                            left={() => <List.Icon {...props} color={Colors.black} icon="map"/>}>
                            <List.Item title="Map TODO"/>
                        </List.Accordion>
                        <List.Accordion
                            title="Red team players"
                            left={() => <List.Icon {...props} color={Colors.red} icon="circle"/>}>
                            {game.redPlayers.map(player => (
                                <List.Item key={player.id} title={player.username}/>
                            ))}
                        </List.Accordion>
                        <List.Accordion
                            title="Blue team players"
                            left={() => <List.Icon {...props} color={Colors.blue} icon="circle"/>}>
                            {game.redPlayers.map(player => (
                                <List.Item key={player.id} title={player.username}/>
                            ))}
                        </List.Accordion>
                    </Card.Content>
                </Card>
            </ScrollView>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: Colors.white,
        padding: 15
    },
    textInput: {
        marginBottom: 10
    }
});

export default PreviousGameEditScreen;
