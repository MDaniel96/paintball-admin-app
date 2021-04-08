import React, {FC} from 'react';
import {StyleSheet} from 'react-native';
import {Button} from 'react-native-paper';
import Colors from '../../constants/Colors';

interface Props {
    isSaved: boolean;
    save: () => void;
}

const SaveButton: FC<Props> = (props) => {
    return (
        <Button
            style={[styles.footer, props.isSaved ? styles.footerGreen : styles.footerRed]}
            mode="contained"
            onPress={props.save}>
            {props.isSaved ? 'Saved' : 'Save'}
        </Button>
    );
};

const styles = StyleSheet.create({
    footer: {
        flex: .2
    },
    footerRed: {
        backgroundColor: Colors.red
    },
    footerGreen: {
        backgroundColor: Colors.green
    }
});

export default SaveButton;
