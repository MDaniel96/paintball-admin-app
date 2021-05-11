import Colors from '../constants/Colors';
import {HeaderButtons, Item} from 'react-navigation-header-buttons';
import CustomHeaderButton from '../components/CustomHeaderButton';
import {createStackNavigator} from '@react-navigation/stack';
import React, {FC} from 'react';
import PreviousGamesScreen from '../screens/PreviousGamesScreen';
import PreviousGameEditScreen from '../screens/PreviousGameEditScreen';
import ConfigureMapScreen from '../screens/ConfigureMapScreen';
import ProfileScreen from '../screens/ProfileScreen';
import RunningGamesScreen from '../screens/RunningGamesScreen';
import RunningGamesEditScreen from '../screens/RunningGamesEditScreen';

const toggleHeaderOptions = ({navigation}: { navigation: any }) => {
    return {
        headerStyle: {
            backgroundColor: Colors.white
        },
        headerLeft: () => (
            <HeaderButtons HeaderButtonComponent={CustomHeaderButton}>
                <Item
                    title="Menu"
                    iconName="ios-menu"
                    onPress={() => {
                        navigation.toggleDrawer();
                    }}
                />
            </HeaderButtons>
        )
    };
};

const Stack = createStackNavigator();

export const ProfileStack: FC = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Profile" component={ProfileScreen}
                          options={({navigation}) => (toggleHeaderOptions({navigation}))}/>
        </Stack.Navigator>
    );
}

export const PreviousGamesStack: FC = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Previous games" component={PreviousGamesScreen}
                          options={({navigation}) => (toggleHeaderOptions({navigation}))}/>
            <Stack.Screen name="Game details" component={PreviousGameEditScreen}/>
        </Stack.Navigator>
    );
}

export const ConfigureMapStack: FC = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Configure map" component={ConfigureMapScreen}
                          options={({navigation}) => (toggleHeaderOptions({navigation}))}/>
        </Stack.Navigator>
    );
}

export const RunningGamesStack: FC = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Running games" component={RunningGamesScreen}
                          options={({navigation}) => (toggleHeaderOptions({navigation}))}/>
            <Stack.Screen name="Game settings" component={RunningGamesEditScreen}/>
        </Stack.Navigator>
    );
}
