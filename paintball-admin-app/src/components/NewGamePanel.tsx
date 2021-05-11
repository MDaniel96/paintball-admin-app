import React, {FC, useEffect, useState} from 'react';
import {Button, Modal, Portal, TextInput, Title} from 'react-native-paper';
import {StyleSheet, View} from 'react-native';
import DropDown from 'react-native-paper-dropdown';
import {MapService} from '../service/MapService';
import {Map} from '../model/Map';

interface Props {
    show: boolean;
    onCancel: () => void;
    onCreate: (name: string, type: string, mapId: number, connectionMode: string) => void;
}

const NewGamePanel: FC<Props> = (props) => {

    const [nameInput, setNameInput] = useState<string>('');
    const [typeInput, setTypeInput] = useState<string>('');
    const [mapList, setMapList] = useState<{label: string, value: number}[]>([]);
    const [mapIdInput, setMapIdInput] = useState<number>(-1);

    const connectionModeList = [
        {label: 'BLUETOOTH', value: 'BLUETOOTH'},
        {label: 'UWB', value: 'UWB'}
    ];
    const [connectionMode, setConnectionMode] = useState<string>(connectionModeList[0].label);

    const [showDropDownMap, setShowDropDownMap] = useState<boolean>(false);
    const [showDropDownConnection, setShowDropDownConnection] = useState<boolean>(false);

    useEffect(() => {
        getMaps();
    }, []);

    const getMaps = () => {
        MapService.getMaps().then(data => {
            setMapList(
                data.map(m => {
                    return {label: m.name, value: m.id};
                })
            );
        });
    };

    const validInputs = () => {
        return nameInput !== '' && typeInput !== '' && mapIdInput !== -1;
    };

    const create = () => {
        props.onCreate(nameInput, typeInput, mapIdInput, connectionMode);
        setNameInput('');
        setTypeInput('');
        setMapIdInput(-1);
        setConnectionMode(connectionModeList[0].label);
    }

    const cancel = () => {
        props.onCancel();
        setNameInput('');
        setTypeInput('');
        setMapIdInput(-1);
        setConnectionMode(connectionModeList[0].label);
    }

    return (
        <Portal>
            <Modal visible={props.show} onDismiss={props.onCancel} contentContainerStyle={styles.modal}>
                <Title style={styles.textInput}>Create game</Title>
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
                <DropDown
                    label={'Connection Mode'}
                    mode={'outlined'}
                    value={connectionMode}
                    setValue={setConnectionMode}
                    list={connectionModeList}
                    visible={showDropDownConnection}
                    showDropDown={() => setShowDropDownConnection(true)}
                    onDismiss={() => setShowDropDownConnection(false)}
                    inputProps={{
                        right:  <TextInput.Icon name={'menu-down'}  />
                    }}
                />
                <DropDown
                    label={'Map'}
                    mode={'outlined'}
                    value={mapIdInput}
                    setValue={setMapIdInput}
                    list={mapList}
                    visible={showDropDownMap}
                    showDropDown={() => setShowDropDownMap(true)}
                    onDismiss={() => setShowDropDownMap(false)}
                    inputProps={{
                        right:  <TextInput.Icon name={'menu-down'}  />
                    }}
                />
                <View style={styles.buttonContainer}>
                    <Button mode="outlined" onPress={cancel}>CANCEL</Button>
                    <Button mode="contained" disabled={!validInputs()} onPress={create}>CREATE</Button>
                </View>
            </Modal>
        </Portal>
    );
};

const styles = StyleSheet.create({
    modal: {
        backgroundColor: 'white',
        padding: 15,
        margin: 30,
        borderRadius: 10
    },
    textInput: {
        marginBottom: 10
    },
    buttonContainer: {
        marginTop: 20,
        flexDirection: 'row',
        justifyContent: 'space-around'
    }
});

export default NewGamePanel;
