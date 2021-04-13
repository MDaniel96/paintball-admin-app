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

const ConfigureMapScreen: FC = () => {
    LogBox.ignoreLogs(['Animated.event now requires']);

    const isFocused = useIsFocused();

    const [isConfigStarted, setConfigStarted] = useState<boolean>(false);

    const [map, setMap] = useState<Map>(new Map());

    useEffect(() => {
        getMapUnderCreation();
    }, [isFocused]);

    const getMapUnderCreation = () => {
        UserService.getMapUnderCreation(1).then(data => {
            if (data) {
                setMap(data);
            }
        });
    }

    const segments = [
        {
            title: 'Details',
            view: () => <MapDetailsForm/>
        },
        {
            title: 'Edges',
            view: () => <MapEdgesForm/>
        },
        {
            title: 'Obstacles',
            view: () => <MapObstaclesForm/>
        },
        {
            title: 'Anchors',
            view: () => <MapAnchorsForm/>
        },
        {
            title: 'Finish',
            view: () => <Text>finish editing</Text>
        }
    ];

    const noMapView = (
        <View style={styles.noMapContainer}>
            <Text>You have no map under creation</Text>
            <Button mode="contained" onPress={() => setConfigStarted(true)}>Start map config</Button>
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
    }
});

export default ConfigureMapScreen;
