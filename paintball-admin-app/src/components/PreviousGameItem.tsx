import React, {FC} from 'react';
import {Button, StyleSheet, TouchableOpacity, View} from 'react-native';
import {List, Text} from 'react-native-paper';
import Colors from '../constants/Colors';
import {Game} from '../model/Game';
import Swipeable from 'react-native-gesture-handler/Swipeable';

interface Props {
    game: Game;
    onDelete: (id: number) => void
    onSelect: (id: number) => void
}

const PreviousGameItem: FC<Props> = (props) => {

    const deleteButton = () => {
        return (
            <Button
                title='Delete'
                color={Colors.red}
                onPress={() => {
                    props.onDelete(props.game.id)
                }}/>
        );
    };

    return (
        <TouchableOpacity
            onPress={() => props.onSelect(props.game.id)}
            style={styles.touchableItem}>
            <Swipeable renderRightActions={deleteButton}>
                <List.Item
                    style={styles.listItem}
                    title={props.game.name}
                    titleStyle={{paddingBottom: 4}}
                    description={props.game.type}
                    left={() => <List.Icon icon="calendar"/>}
                    right={() =>
                        <View style={{alignSelf: 'center'}}>
                            <Text>{props.game.redPlayers.length + props.game.bluePlayers.length + ' players'}</Text>
                            <Text>{props.game.date}</Text>
                        </View>
                    }/>
            </Swipeable>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    touchableItem: {
        marginTop: 15,
        width: '90%'
    },
    listItem: {
        backgroundColor: Colors.lightGrey,
        borderRadius: 10
    }
});

export default PreviousGameItem;
