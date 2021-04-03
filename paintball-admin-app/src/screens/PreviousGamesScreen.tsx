import {RefreshControl, ScrollView, StyleSheet} from 'react-native';
import React, {FC, useEffect, useState} from 'react';
import {Game} from '../model/Game';
import {GameService} from '../service/GameService';
import {List} from 'react-native-paper';
import Colors from '../constants/Colors';
import {GameFilter} from '../model/util/GameFilter';
import PreviousGameItem from '../components/PreviousGameItem';
import {HeaderButtons, Item} from 'react-navigation-header-buttons';
import CustomHeaderButton from '../components/CustomHeaderButton';
import PreviousGameFilterPanel from '../components/PreviousGameFilterPanel';

const PreviousGamesScreen: FC = (props: any) => {

    const FINISHED: string = 'FINISHED';

    const [games, setGames] = useState<Game[]>([]);
    const [gameFilter, setGameFilter] = useState<GameFilter>(new GameFilter({state: FINISHED}));

    const [isRefreshing, setRefreshing] = useState<boolean>(false);
    const [isFilterShown, setFilterShown] = useState<boolean>(false);

    useEffect(() => {
        getGames();
    }, [gameFilter]);

    useEffect(() => {
        props.navigation.setOptions(headerOptions);
    }, []);

    const getGames = () => {
        setRefreshing(true);
        GameService.getGames(gameFilter).then(data => {
            setGames(data);
            setRefreshing(false);
        })
    }

    const deleteGame = (id: number) => {
        setGames(
            games.filter(game => game.id !== id)
        )
        GameService.deleteGame(id).then(() => {
            getGames();
        });
    }

    const selectGame = (id: number) => {
        props.navigation.navigate({
            name: 'Game details',
            params: {
                gameId: id
            }
        });
    }

    const cancelFilter = () => {
        setFilterShown(false);
        setGameFilter(
            new GameFilter({state: FINISHED})
        )
    }

    const filterGames = (name: string, type: string, date: Date) => {
        setFilterShown(false)
        setGameFilter(
            new GameFilter({state: FINISHED, name: name, type: type, date: date})
        )
    }

    const headerOptions = {
        headerRight: () => (
            <HeaderButtons HeaderButtonComponent={CustomHeaderButton}>
                <Item
                    title="Filter"
                    iconName="filter"
                    onPress={() => {
                        setFilterShown(true);
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
                    <PreviousGameItem
                        key={game.id}
                        game={game}
                        onDelete={deleteGame}
                        onSelect={selectGame}/>
                ))}
            </List.Section>
            <PreviousGameFilterPanel show={isFilterShown} onCancel={cancelFilter} onFilter={filterGames}/>
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: Colors.white,
    },
    listContainer: {
        alignItems: 'center'
    }
});

export default PreviousGamesScreen;
