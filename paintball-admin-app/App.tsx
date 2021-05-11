import React, {useEffect, useState} from 'react';
import {Provider as PaperProvider} from 'react-native-paper';
import LoginScreen from './src/screens/LoginScreen';
import {UserService} from './src/service/UserService';
import {User} from './src/model/User';
import DrawerNavigator from './src/navigation/DrawerNavigator';

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
        <PaperProvider>
            {loggedInUser.id === 0 ?
                <LoginScreen onLogin={getLoggedInUser}/> :
                <DrawerNavigator loggedInUser={loggedInUser} onLogout={getLoggedInUser}/>
            }
        </PaperProvider>
    );
};

export default App;
