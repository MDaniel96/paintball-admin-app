import React, {FC, useEffect, useState} from 'react';
import {RefreshControl, ScrollView, StyleSheet} from 'react-native';
import {Game} from '../model/Game';
import {GameFilter} from '../model/util/GameFilter';
import {GameService} from '../service/GameService';
import {List} from 'react-native-paper';
import Colors from '../constants/Colors';
import RunningGameItem from '../components/RunningGameItem';
import {HeaderButtons, Item} from 'react-navigation-header-buttons';
import CustomHeaderButton from '../components/CustomHeaderButton';
import NewGamePanel from '../components/NewGamePanel';

const RunningGamesScreen: FC = (props: any) => {

    const STARTED_STATE: string = 'STARTED';
    const CREATED_STATE: string = 'CREATED';

    const [games, setGames] = useState<Game[]>([]);
    const [isCreateGameShown, setCreateGameShown] = useState<boolean>(false);

    const [isRefreshing, setRefreshing] = useState<boolean>(false);

    useEffect(() => {
        getGames();
    }, []);

    useEffect(() => {
        props.navigation.setOptions(headerOptions);
    }, []);

    const getGames = () => {
        setRefreshing(true);
        GameService.getGames(new GameFilter()).then(data => {
            const runningGames = data
                .filter(game => game.state === STARTED_STATE || game.state === CREATED_STATE)
            setGames(runningGames);
            setRefreshing(false);
        })
    }

    const selectGame = (id: number) => {
        props.navigation.navigate({
            name: 'Game settings',
            params: {
                game: games.find(g => g.id === id)
            }
        });
    }

    const deleteGame = (id: number) => {
        setGames(
            games.filter(game => game.id !== id)
        )
        GameService.deleteGame(id).then(() => {
            getGames();
        });
    }

    const cancelCreateGame = () => {
        setCreateGameShown(false);
    };

    const createGame = (name: string, type: string, mapId: number, connectionMode: string) => {
        setCreateGameShown(false);
        GameService.createGame(name, type, mapId, connectionMode).then(() => {
            getGames();
        });
    };

    const headerOptions = {
        headerRight: () => (
            <HeaderButtons HeaderButtonComponent={CustomHeaderButton}>
                <Item
                    title="Add"
                    iconName="add-circle-outline"
                    onPress={() => {
                        setCreateGameShown(true);
                    }}
                />
            </HeaderButtons>
        )
    }

    return (
        <ScrollView
            style={styles.container}
            refreshControl={
                <RefreshControl
                    refreshing={isRefreshing}
                    onRefresh={getGames}
                />
            }>
            <List.Section style={styles.listContainer}>
                {games.map(game => (
                    <RunningGameItem
                        key={game.id}
                        game={game}
                        onDelete={deleteGame}
                        onSelect={selectGame}/>
                ))}
            </List.Section>
            <NewGamePanel show={isCreateGameShown} onCancel={cancelCreateGame} onCreate={createGame} />
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    container: {
        backgroundColor: Colors.white,
    },
    listContainer: {
        alignItems: 'center'
    }
});

export default RunningGamesScreen;
