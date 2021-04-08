import React, {FC} from 'react';
import {LogBox, StyleSheet, Text, View} from 'react-native';
// @ts-ignore
import SegmentControl from 'react-native-segment-control';
import MapDetailsForm from '../components/configure-map/MapDetailsForm';
import MapObstaclesForm from '../components/configure-map/MapObstaclesForm';
import MapEdgesForm from '../components/configure-map/MapEdgesForm';
import MapAnchorsForm from '../components/configure-map/MapAnchorsForm';

const ConfigureMapScreen: FC = () => {
    LogBox.ignoreLogs(['Animated.event now requires']);

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

    return (
        <View style={styles.container}>
            <SegmentControl segments={segments}/>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'flex-start'
    }
});

export default ConfigureMapScreen;
