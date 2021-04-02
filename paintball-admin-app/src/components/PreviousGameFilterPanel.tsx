import React, {FC, useState} from 'react';
import {StyleSheet, View} from 'react-native';
import {Button, Modal, Portal, TextInput, Title} from 'react-native-paper';
import DateTimePickerModal from "react-native-modal-datetime-picker";

interface Props {
    show: boolean;
    onCancel: () => void;
    onFilter: (name: string, type: string, date: Date) => void;
}

const PreviousGameFilterPanel: FC<Props> = (props) => {

    const [nameInput, setNameInput] = useState<string>('');
    const [typeInput, setTypeInput] = useState<string>('');
    const [dateInput, setDateInput] = useState<Date>(new Date());

    const [isDateEditable, setDateEditable] = useState<boolean>(true);
    const [isDatePickerVisible, setDatePickerVisibility] = useState<boolean>(false);

    const filter = () => {
        props.onFilter(nameInput, typeInput, dateInput);
    }

    const cancel = () => {
        props.onCancel();
        setNameInput('');
        setTypeInput('');
    }

    return (
        <Portal>
            <Modal visible={props.show} onDismiss={props.onCancel} contentContainerStyle={styles.modal}>
                <Title style={styles.textInput}>Filter games</Title>
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
                <DateTimePickerModal
                    isVisible={isDatePickerVisible}
                    mode="date"
                    onConfirm={(newDate: Date) => {
                        setDatePickerVisibility(false);
                        setDateEditable(true);
                        setDateInput(newDate);
                    }}
                    onCancel={() => setDatePickerVisibility(false)}
                />
                <View style={styles.buttonContainer}>
                    <Button mode="outlined" onPress={cancel}>CANCEL</Button>
                    <Button mode="contained" onPress={filter}>FILTER</Button>
                </View>
            </Modal>
        </Portal>
    );
}

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

export default PreviousGameFilterPanel;
