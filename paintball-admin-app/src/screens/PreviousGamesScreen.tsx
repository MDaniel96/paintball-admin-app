import {StyleSheet, View} from 'react-native';
import React, {FC, useEffect, useState} from 'react';
import {Text} from "native-base";
import {Game} from '../model/Game';
import {GameService} from '../service/GameService';

const PreviousGamesScreen: FC = () => {

    const [games, setGames] = useState<Game[]>([]);

    useEffect(() => {
        GameService.getGames().then(data => {
            setGames(data);
        })
    }, []);

    return (
        <View style={styles.container}>
            <Text style={{fontWeight: 'bold'}}>Previous games</Text>
            {games.map(game => (
                <Text key={game.id}>{game.name}</Text>
            ))}
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
