import React, {FC} from 'react';
import {createDrawerNavigator} from '@react-navigation/drawer';
import {NavigationContainer} from '@react-navigation/native';
import {ConfigureMapStack, MapsStack, PreviousGamesStack} from './StackNavigator';

const Drawer = createDrawerNavigator();

const DrawerNavigator: FC = () => {
    return (
        <NavigationContainer>
            <Drawer.Navigator initialRouteName="Previous games">
                <Drawer.Screen name="Previous games" component={PreviousGamesStack}/>
                <Drawer.Screen name="Maps" component={MapsStack}/>
                <Drawer.Screen name="Configure new map" component={ConfigureMapStack}/>
            </Drawer.Navigator>
        </NavigationContainer>
    );
}

export default DrawerNavigator;
