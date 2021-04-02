import React from 'react';
import DrawerNavigator from './src/navigation/DrawerNavigator';
import {Provider as PaperProvider} from 'react-native-paper';

const App: React.FC = () => {
    return (
        <PaperProvider>
            <DrawerNavigator/>
        </PaperProvider>
    );
};

export default App;
