import React, {FC, useState} from 'react';
import {StyleSheet, Text, View} from 'react-native';
import SaveButton from './SaveButton';

const MapObstaclesForm: FC = () => {

    const [isSaved, setSaved] = useState<boolean>(false);

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <Text>Map obstacles form</Text>
            </View>
            <SaveButton isSaved={isSaved} save={() => setSaved(true)}/>
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

export default MapObstaclesForm;
