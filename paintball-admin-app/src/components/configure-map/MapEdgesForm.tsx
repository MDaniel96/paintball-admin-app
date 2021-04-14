import React, {FC, useState} from 'react';
import {StyleSheet, Text, View} from 'react-native';
import SaveButton from './SaveButton';
import {MapConfigurationProps} from './MapConfigurationProps';

const MapEdgesForm: FC<MapConfigurationProps> = (props) => {

    const [isSaved, setSaved] = useState<boolean>(false);

    const isEditable = () => props.map.id;

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <Text>Map edges form</Text>
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
        flex: 1,
        width: '100%'
    },
    editContainer: {
        flex: .8,
        padding: 15
    }
});

export default MapEdgesForm;
