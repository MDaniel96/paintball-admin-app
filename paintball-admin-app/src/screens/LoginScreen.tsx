import React, {FC, useState} from 'react';
import {StyleSheet, Text, View} from 'react-native';
import {Button, TextInput} from 'react-native-paper';
import {AuthService} from '../service/AuthService';
import {UserService} from '../service/UserService';

interface Props {
    onLogin: () => void;
}

const LoginScreen: FC<Props> = (props) => {

    const [username, setUsername] = useState<string>('admin');
    const [password, setPassword] = useState<string>('admin');

    const isFilled = (): boolean => {
        return username.length !== 0 && password.length !== 0;
    }

    const login = () => {
        AuthService.login(username, password).then(() => {
            props.onLogin();
        });
    }

    const register = () => {
        UserService.registerUser({username: username, password: password}).then((data: any) => {
            if (data.httpStatus === 'BAD_REQUEST') {
                alert(data.message);
            } else {
                alert('Registration successful');
            }
        });
    }

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Paintball Admin App</Text>
            <TextInput
                style={styles.input}
                label="Username"
                mode="outlined"
                onChangeText={(text) => setUsername(text)}
                value={username}/>
            <TextInput
                style={styles.input}
                label="Password"
                secureTextEntry={true}
                mode="outlined"
                onChangeText={(text) => setPassword(text)}
                value={password}/>
            <View style={styles.buttonContainer}>
                <Button
                    style={styles.button} disabled={!isFilled()}
                    mode="outlined" onPress={login}>Login
                </Button>
                <Button
                    style={styles.button} disabled={!isFilled()}
                    mode="contained" onPress={register}>Register
                </Button>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        width: '100%',
        flexDirection: 'column',
        padding: 20
    },
    title: {
        marginTop: 70,
        fontSize: 30,
        fontWeight: 'bold',
        alignSelf: 'flex-start',
        marginBottom: 70
    },
    input: {
        marginBottom: 15
    },
    buttonContainer: {
        marginTop: 20,
        flexDirection: 'row',
        justifyContent: 'space-around'
    },
    button: {
        width: '48%'
    }
});

export default LoginScreen;
