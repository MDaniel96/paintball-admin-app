import React, {Dispatch, FC, useEffect} from 'react';
import {StyleSheet, View} from 'react-native';
import {Card, Paragraph, Title} from 'react-native-paper';
import {User} from '../model/User';
import {useDispatch, useSelector} from 'react-redux';
import {UserState} from '../store/reducers/UserReducer';
import {getLoggedInUserAction} from '../store/actions/UserActions';

const ProfileScreen: FC = () => {
    const dispatch: Dispatch<any> = useDispatch();

    const loggedInUser: User = useSelector((state: { users: UserState }) => state.users.loggedInUser);

    useEffect(() => {
        dispatch(getLoggedInUserAction());
    }, []);

    return (
        <View style={styles.container}>
            <Card style={styles.profileCard}>
                <Card.Title title={loggedInUser.username} subtitle="Username"/>
                <Card.Content>
                    <Title>{loggedInUser.roles.map(role => role.name).join()}</Title>
                    <Paragraph>Roles</Paragraph>
                </Card.Content>
            </Card>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        padding: 30
    },
    profileCard: {
        width: '100%'
    }
});

export default ProfileScreen;
