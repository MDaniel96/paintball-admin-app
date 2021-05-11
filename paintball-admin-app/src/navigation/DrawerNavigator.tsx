import React, {FC} from 'react';
import {User} from '../model/User';
import {Button} from 'react-native-paper';
import {StyleSheet, View} from 'react-native';
import {AuthService} from '../service/AuthService';
import {createDrawerNavigator, DrawerItemList} from '@react-navigation/drawer';
import {NavigationContainer} from '@react-navigation/native';
import {ConfigureMapStack, MapsStack, PreviousGamesStack, ProfileStack, RunningGamesStack} from './StackNavigator';

const Drawer = createDrawerNavigator();

interface Props {
    loggedInUser: User;
    onLogout: () => void;
}

const DrawerNavigator: FC<Props> = (props) => {

    const isAdmin = (): boolean => {
        return props.loggedInUser?.roles?.map(role => role.name)?.includes('ADMIN');
    }

    const logout = () => {
        AuthService.logout().then(() => {
            props.onLogout();
        });
    }

    const content = (p: any) => {
        return (
            <View style={styles.contentContainer}>
                <DrawerItemList {...p} />
                <Button mode="outlined" onPress={logout} style={styles.logoutButton}>Logout</Button>
            </View>
        )
    }

    return (
        <NavigationContainer>
            <Drawer.Navigator initialRouteName="Profile" drawerContent={p => content(p)}>
                <Drawer.Screen name="Profile" component={ProfileStack}/>
                <Drawer.Screen name="Previous games" component={PreviousGamesStack}/>
                {isAdmin() && <Drawer.Screen name="Maps" component={MapsStack}/>}
                {isAdmin() && <Drawer.Screen name="Configure new map" component={ConfigureMapStack}/>}
                <Drawer.Screen name="Running games" component={RunningGamesStack}/>
            </Drawer.Navigator>
        </NavigationContainer>
    );
}

const styles = StyleSheet.create({
    contentContainer: {
        marginTop: 50,
        height: '100%'
    },
    logoutButton: {
        position: 'absolute',
        bottom: 150,
        left: 10
    }
});


export default DrawerNavigator;
