import React, {FC} from 'react';
import {Button, TextInput} from 'react-native-paper';
import {StyleSheet, View} from 'react-native';

interface Props {
    label: string;
    value: number;
    setValue: (value: number) => void;
    disabled: boolean;
}

const RectEdit: FC<Props> = (props) => {

    return (
        <View style={styles.editArea}>
            <TextInput
                style={{flex: .7}}
                label={props.label}
                keyboardType="numeric"
                mode="outlined"
                disabled={props.disabled}
                onChangeText={(text) => props.setValue(text ? parseInt(text) : 0)}
                value={props.value.toString()}/>
            <Button
                style={styles.editButton}
                mode="outlined"
                disabled={props.disabled}
                onPress={() => props.setValue(props.value - 10)}>-</Button>
            <Button
                style={styles.editButton}
                mode="outlined"
                disabled={props.disabled}
                onPress={() => props.setValue(props.value + 10)}>+</Button>
        </View>
    );
}

const styles = StyleSheet.create({
    editArea: {
        flexDirection: 'row',
        marginBottom: 15,
        justifyContent: 'space-around'
    },
    editButton: {
        justifyContent: 'center'
    }
});

export default RectEdit;

