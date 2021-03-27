import Colors from '../constants/Colors';
import {HeaderButtons, Item} from 'react-navigation-header-buttons';
import CustomHeaderButton from '../components/CustomHeaderButton';
import {createStackNavigator} from '@react-navigation/stack';
import React, {FC} from 'react';
import PreviousGamesScreen from '../screens/PreviousGamesScreen';
import MapsScreen from '../screens/MapsScreen';

const defaultHeaderOptions = ({navigation}: { navigation: any }) => {
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
        <Stack.Navigator screenOptions={({navigation}) => (defaultHeaderOptions({navigation}))}>
            <Stack.Screen name="Previous games" component={PreviousGamesScreen}/>
        </Stack.Navigator>
    );
}

export const MapsStack: FC = () => {
    return (
        <Stack.Navigator screenOptions={({navigation}) => (defaultHeaderOptions({navigation}))}>
            <Stack.Screen name="Maps" component={MapsScreen}/>
        </Stack.Navigator>
    );
}
