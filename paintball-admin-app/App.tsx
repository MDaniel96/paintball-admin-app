import React, {useEffect, useState} from 'react';
import {Provider as PaperProvider} from 'react-native-paper';
import LoginScreen from './src/screens/LoginScreen';
import {Provider} from 'react-redux';
import {UserService} from './src/service/UserService';
import {User} from './src/model/User';
import DrawerNavigator from './src/navigation/DrawerNavigator';
import {applyMiddleware, combineReducers, createStore} from 'redux';
import gameReducer from './src/store/reducers/GameReducer';
import thunk from 'redux-thunk';
import userReducer from './src/store/reducers/UserReducer';
import locationReducer from './src/store/reducers/LocationReducer';

const rootReducer = combineReducers({
    games: gameReducer,
    users: userReducer,
    locations: locationReducer
});

const store = createStore(rootReducer, applyMiddleware(thunk))

const App: React.FC = () => {

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
        <Provider store={store}>
            <PaperProvider>
                {loggedInUser.id === 0 ?
                    <LoginScreen onLogin={getLoggedInUser}/> :
                    <DrawerNavigator loggedInUser={loggedInUser} onLogout={getLoggedInUser}/>
                }
            </PaperProvider>
        </Provider>
    );
};

export default App;
