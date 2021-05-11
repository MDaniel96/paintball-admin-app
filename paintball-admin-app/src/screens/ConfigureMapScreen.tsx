import React, {FC, useEffect, useState} from 'react';
import {LogBox, StyleSheet, Text, View} from 'react-native';
// @ts-ignore
import SegmentControl from 'react-native-segment-control';
import MapDetailsForm from '../components/configure-map/MapDetailsForm';
import MapObstaclesForm from '../components/configure-map/MapObstaclesForm';
import MapEdgesForm from '../components/configure-map/MapEdgesForm';
import MapAnchorsForm from '../components/configure-map/MapAnchorsForm';
import {UserService} from '../service/UserService';
import {useIsFocused} from "@react-navigation/native";
import {Map} from '../model/Map';
import Colors from '../constants/Colors';
import {Button} from 'react-native-paper';
import {MapService} from '../service/MapService';
import {User} from '../model/User';

const ConfigureMapScreen: FC = () => {
    LogBox.ignoreLogs(['Animated.event now requires']);

    const isFocused = useIsFocused();

    const [isConfigStarted, setConfigStarted] = useState<boolean>(false);

    const [map, setMap] = useState<Map>(new Map());

    useEffect(() => {
        getMapUnderCreation();
    }, [isFocused]);

    const onDataChanged = () => getMapUnderCreation();

    const getMapUnderCreation = () => {
        UserService.getLoggedInUser().then((data: User) => {
            const mapUnderCreation = data.mapsUnderCreation[0];
            if (mapUnderCreation) {
                setMap(mapUnderCreation);
            } else {
                setMap(new Map());
            }
        });
    }

    const finishMap = () => {
        MapService.finishEdit(map.id).then(() => {
            getMapUnderCreation();
            setConfigStarted(false);
            alert('Succesfully configured this map, now you can add it to new games');
        });
    }

    const segments = [
        {
            title: 'Details',
            view: () => <MapDetailsForm map={map} onDataSaved={onDataChanged}/>
        },
        {
            title: 'Edges',
            view: () => <MapEdgesForm map={map} onDataSaved={onDataChanged}/>
        },
        {
            title: 'Obstacles',
            view: () => <MapObstaclesForm map={map} onDataSaved={onDataChanged}/>
        },
        {
            title: 'Anchors',
            view: () => <MapAnchorsForm map={map} onDataSaved={onDataChanged}/>
        },
        {
            title: 'Finish',
            view: () => finishMapView
        }
    ];

    const noMapView = (
        <View style={styles.noMapContainer}>
            <Text>You have no map under configuration</Text>
            <Button mode="contained" onPress={() => setConfigStarted(true)}>Start map config</Button>
        </View>
    );

    const finishMapView = (
        <View style={styles.finishMapContainer}>
            <Text>Finish configuring this map by pressing the button below</Text>
            <Button mode="contained" onPress={finishMap}>Finish map</Button>
        </View>
    );

    return (
        <View style={styles.container}>
            {
                isConfigStarted || map.name ?
                    <SegmentControl segments={segments}/> :
                    noMapView
            }
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'flex-start'
    },
    noMapContainer: {
        flex: 1,
        marginHorizontal: 50,
        marginVertical: 200,
        backgroundColor: Colors.lightGrey,
        borderRadius: 10,
        justifyContent: 'space-around',
        alignItems: 'center'
    },
    finishMapContainer: {
        flex: 1,
        width: '100%',
        height: '100%',
        backgroundColor: Colors.white,
        justifyContent: 'space-evenly',
        alignItems: 'center'
    }
});

export default ConfigureMapScreen;
