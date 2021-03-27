import {StyleSheet, View} from 'react-native';
import React, {FC} from 'react';
import {Text} from "native-base";

const PreviousGamesScreen: FC = () => {
    return (
        <View style={styles.container}>
            <Text>Maps</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});

export default PreviousGamesScreen;
