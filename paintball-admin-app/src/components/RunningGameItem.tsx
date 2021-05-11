import React, {FC} from 'react';
import {Game} from '../model/Game';
import {Button, StyleSheet, TouchableOpacity, View} from 'react-native';
import Colors from '../constants/Colors';
import Swipeable from "react-native-gesture-handler/Swipeable";
import {List, Text} from 'react-native-paper';

interface Props {
    game: Game;
    onDelete: (id: number) => void
    onSelect: (id: number) => void
}

const RunningGameItem: FC<Props> = (props) => {

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
                    left={() => <List.Icon icon="circle" color={props.game.state === 'STARTED' ? 'green' : 'orange'}/>}
                    right={() =>
                        <View style={{alignSelf: 'center'}}>
                            <Text>{props.game.redPlayers.length + props.game.bluePlayers.length + ' players'}</Text>
                        </View>
                    }/>
            </Swipeable>
        </TouchableOpacity>
    );
};

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

export default RunningGameItem;
