import React, {FC} from 'react';
import {StyleSheet, Text, View} from 'react-native';

const MapsScreen: FC = () => {
    return (
        <View style={styles.container}>
            <Text>Maps</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});

export default MapsScreen;
