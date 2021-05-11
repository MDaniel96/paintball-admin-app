import React, {FC, useState} from 'react';
import {Button, StyleSheet} from 'react-native';
import {Modal, Portal, TextInput, Title} from 'react-native-paper';
import DropDown from 'react-native-paper-dropdown';

interface Props {
    show: boolean;
    hasRecording: boolean;
    onCancel: () => void;
    startRecording: () => void;
    stopRecording: () => void;
    onSend: (target: string) => void;
}

const AudioRecordPanel: FC<Props> = (props) => {

    const targetList = [
        {label: 'Both teams', value: 'both'},
        {label: 'Red team', value: 'red'},
        {label: 'Blue team', value: 'blue'}
    ];
    const [target, setTarget] = useState<string>(targetList[0].value);
    const [showDropdownTarget, setShowDropDownTarget] = useState<boolean>(false);

    const [isRecording, setIsRecording] = useState<boolean>(false);

    const sendRecording = async () => {
        await props.onSend(target);
    };

    const startRecording = async () => {
        setIsRecording(true);
        await props.startRecording();
    }

    const stopRecording = async () => {
        setIsRecording(false);
        await props.stopRecording()
    }

    const onCancel = async () => {
        setIsRecording(false);
        await props.onCancel()
    }

    return (
        <Portal>
            <Modal visible={props.show} onDismiss={props.onCancel} contentContainerStyle={styles.modal}>
                <Title style={styles.textInput}>
                    Send audio message to {target} team{target === 'both' ? 's' : ''}
                </Title>
                <DropDown
                    label={'Target'}
                    mode={'outlined'}
                    value={target}
                    setValue={setTarget}
                    list={targetList}
                    visible={showDropdownTarget}
                    showDropDown={() => setShowDropDownTarget(true)}
                    onDismiss={() => setShowDropDownTarget(false)}
                    inputProps={{
                        right: <TextInput.Icon name={'menu-down'}/>
                    }}
                />
                <Button
                    title={isRecording ? 'Stop Recording' : 'Start Recording'}
                    onPress={isRecording ? stopRecording : startRecording}
                />
                {
                    props.hasRecording &&
                    <Button
                        title={'Send Recording'}
                        onPress={sendRecording}
                    />
                }
                <Button
                    title={'Cancel'}
                    onPress={onCancel}
                />
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

export default AudioRecordPanel;
