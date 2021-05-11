import React, {FC} from 'react';
import {createDrawerNavigator} from '@react-navigation/drawer';
import {NavigationContainer} from '@react-navigation/native';
import {ConfigureMapStack, MapsStack, PreviousGamesStack} from './StackNavigator';
import {User} from '../model/User';

const Drawer = createDrawerNavigator();

interface Props {
    loggedInUser: User;
}

const DrawerNavigator: FC<Props> = (props) => {

    const isAdmin = (): boolean => {
        return props.loggedInUser?.roles?.map(role => role.name)?.includes('ADMIN');
    }

    return (
        <NavigationContainer>
            <Drawer.Navigator initialRouteName="Previous games">
                <Drawer.Screen name="Previous games" component={PreviousGamesStack}/>
                {isAdmin() && <Drawer.Screen name="Maps" component={MapsStack}/>}
                {isAdmin() && <Drawer.Screen name="Configure new map" component={ConfigureMapStack}/>}
            </Drawer.Navigator>
        </NavigationContainer>
    );
}

export default DrawerNavigator;
