import React, {FC, useState} from 'react';
import {StyleSheet, Text, View} from 'react-native';
import SaveButton from './SaveButton';
import {MapConfigurationProps} from './MapConfigurationProps';

const MapAnchorsForm: FC<MapConfigurationProps> = (props) => {

    const [isSaved, setSaved] = useState<boolean>(false);

    const isEditable = () => props.map.obstacles.length !== 0;

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <Text>Map details form</Text>
            </View>
            <SaveButton
                isSaved={isSaved}
                save={() => setSaved(true)}
                disabled={!isEditable()}/>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        width: '100%',
        height: '100%'
    },
    editContainer: {
        flex: .8,
        padding: 15
    }
});

export default MapAnchorsForm;
