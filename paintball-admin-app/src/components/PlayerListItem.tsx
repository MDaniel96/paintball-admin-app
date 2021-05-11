import React, {FC} from 'react';
import {Button, StyleSheet, View, Text} from 'react-native';
import {Card} from 'react-native-paper';
import {User} from '../model/User';

interface Props {
    player: User,
    dead: boolean,
    kickPlayer: (player: User) => void
}

const PlayerListItem: FC<Props> = (props) => {

    const onKickPlayer = () => {
        props.kickPlayer(props.player);
    };

    return (
        <Card style={styles.card} key={props.player.id}>
            <View style={styles.inline}>
                <Text style={props.dead ? styles.red : styles.green}>{props.player.name} {props.dead ? '(dead)': ''}</Text>
                <Button title={'Kick'} onPress={onKickPlayer} />
            </View>
        </Card>
    );
};

const styles = StyleSheet.create({
    card: {
        width: '80%',
        padding: 3,
        margin: 3,
        alignSelf: 'flex-end'
    },
    inline: {
        flexDirection: 'row',
        flex: 1,
        justifyContent: 'space-between',
        alignItems: 'center'
    },
    red: {
        color: 'red'
    },
    green: {
        color: 'green'
    }
});

export default PlayerListItem;
