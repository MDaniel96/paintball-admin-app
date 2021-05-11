import React, {FC, useEffect, useState} from 'react';
import {StyleSheet, View} from 'react-native';
import {Card, Paragraph, Title} from 'react-native-paper';
import {User} from '../model/User';
import {UserService} from '../service/UserService';

const ProfileScreen: FC = () => {

    const [loggedInUser, setLoggedInUser] = useState<User>(new User());

    useEffect(() => {
        getLoggedInUser();
    }, []);

    const getLoggedInUser = () => {
        UserService.getLoggedInUser().then(data => {
            setLoggedInUser(data);
        });
    }

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
