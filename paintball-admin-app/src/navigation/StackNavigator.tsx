import Colors from '../constants/Colors';
import {HeaderButtons, Item} from 'react-navigation-header-buttons';
import CustomHeaderButton from '../components/CustomHeaderButton';
import {createStackNavigator} from '@react-navigation/stack';
import React, {FC} from 'react';
import PreviousGamesScreen from '../screens/PreviousGamesScreen';
import MapsScreen from '../screens/MapsScreen';
import PreviousGameEditScreen from '../screens/PreviousGameEditScreen';

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

export const PreviousGamesStack: FC = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Previous games" component={PreviousGamesScreen}
                          options={({navigation}) => (toggleHeaderOptions({navigation}))}/>
            <Stack.Screen name="Game details" component={PreviousGameEditScreen}/>
        </Stack.Navigator>
    );
}

export const MapsStack: FC = () => {
    return (
        <Stack.Navigator>
            <Stack.Screen name="Maps" component={MapsScreen}
                          options={({navigation}) => (toggleHeaderOptions({navigation}))}/>
        </Stack.Navigator>
    );
}
