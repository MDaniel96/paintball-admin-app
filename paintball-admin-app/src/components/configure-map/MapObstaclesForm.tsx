import React, {FC, useEffect, useState} from 'react';
import {ScrollView, StyleSheet, View} from 'react-native';
import SaveButton from './SaveButton';
import {MapConfigurationProps} from './MapConfigurationProps';
import Colors from '../../constants/Colors';
import {Rect} from 'react-native-svg';
import {MapService} from '../../service/MapService';
import {ActivityIndicator, Button, Divider} from 'react-native-paper';
import {Obstacle} from '../../model/Obstacle';
import ObstacleEdit from './ObstacleEdit';
// @ts-ignore
import cloneDeep from 'lodash/cloneDeep';
import MapPanel from './MapPanel';
import {ObstacleDetector} from '../../util/ObstacleDetector';
import {ObstacleConverter} from '../../util/ObstacleConverter';

const MapObstaclesForm: FC<MapConfigurationProps> = (props) => {

    const [isSaved, setSaved] = useState<boolean>(false);
    const [isDetecting, setIsDetecting] = useState<boolean>(false);

    const [editWidth, setEditWidth] = useState<number>(-1);
    const [editHeight, setEditHeight] = useState<number>(-1);

    const [obstacles, setObstacles] = useState<Obstacle[]>([]);
    const [selectedObstacle, setSelectedObstacle] = useState<Obstacle>(new Obstacle());

    const isEditable = () => props.map.borderX !== -1;

    useEffect(() => {
        setObstacles([]);
        initValues();
    }, [editHeight, editWidth]);

    const initValues = () => {
        if (props.map.obstacles.length !== 0) {
            const obstacleConverter = new ObstacleConverter(props.map.width, props.map.height, editWidth, editHeight);
            setObstacles(obstacleConverter.fromImageToScreen(props.map.obstacles));
            setSaved(true);
        }
    }

    const selectObstacle = (obstacle: Obstacle) => {
        setSelectedObstacle(obstacle);
        const obstaclesCopy = [...obstacles];
        obstaclesCopy.forEach(o => o.selected = (o.id === obstacle.id));
        setObstacles([...obstaclesCopy]);
    }

    const detectObstacles = async () => {
        setIsDetecting(true);
        const detectedObstacles: Obstacle[] = await ObstacleDetector.detect(props.map.id);
        console.log('Detected obstacles', detectedObstacles);
        const obstacleConverter = new ObstacleConverter(props.map.width, props.map.height, editWidth, editHeight);
        const screenObstacles = obstacleConverter.fromDetectionToScreen(detectedObstacles)
        setObstacles(screenObstacles);
        setIsDetecting(false);
    }

    const addObstacle = () => {
        const newObstacle = {
            id: Math.floor(Math.random() * 1000), selected: true,
            x: 150, y: 150, width: 20, height: 20
        };
        setSelectedObstacle(newObstacle);
        const obstaclesCopy = [...obstacles];
        obstaclesCopy.forEach(o => o.selected = false);
        setObstacles([...obstaclesCopy, newObstacle]);
    }

    const deleteSelectedObstacle = () => {
        const obstaclesCopy = [...obstacles]
            .filter(o => o.id !== selectedObstacle.id);
        setObstacles([...obstaclesCopy]);
        setSelectedObstacle(new Obstacle());
    }

    useEffect(() => {
        if (selectedObstacle.id !== -1) {
            const obstaclesCopy = [...obstacles]
                .filter(o => o.id !== selectedObstacle.id);
            setObstacles([...obstaclesCopy, selectedObstacle]);
        }
    }, [selectedObstacle]);

    const save = () => {
        if (!isSaved && obstacles.length !== 0) {
            const obstacleConverter = new ObstacleConverter(props.map.width, props.map.height, editWidth, editHeight);
            const imageObstacles = obstacleConverter.fromScreenToImage(obstacles);
            MapService.editObstacles(props.map.id, {
                obstacles: imageObstacles
            }).then(() => {
                setSaved(true);
                props.onDataSaved();
            });
        } else {
            alert('Please add some obstacles');
        }
    }

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <View style={styles.imageContainer} onLayout={(event) => {
                    setEditWidth(event.nativeEvent.layout.width);
                    setEditHeight(event.nativeEvent.layout.height);
                }}>
                    {
                        props.map.borderX !== -1 &&
                        <MapPanel
                            editHeight={editHeight}
                            editWidth={editWidth}
                            mapId={props.map.id}>
                            {obstacles.map(obstacle => (
                                <Rect key={obstacle.id}
                                      x={obstacle.x} y={obstacle.y}
                                      width={obstacle.width} height={obstacle.height}
                                      stroke={obstacle.selected ? 'yellow' : 'red'} strokeWidth="2"
                                      onPress={() => selectObstacle(obstacle)}/>
                            ))}
                        </MapPanel>
                    }
                </View>
                <View style={styles.detailsContainer}>
                    <Button mode="contained" onPress={detectObstacles} style={styles.button} disabled={isSaved}>
                        Detect obstacles</Button>
                    <Button mode="outlined" onPress={addObstacle} style={styles.button} disabled={isSaved}>
                        Add obstacle</Button>
                    <Divider/>
                    {selectedObstacle.id !== -1 && <ScrollView>
                        <ObstacleEdit
                            obstacle={selectedObstacle}
                            setObstacle={setSelectedObstacle}
                            isSaved={isSaved}/>
                        <Button mode="outlined" color="red" onPress={deleteSelectedObstacle} style={styles.button}
                                disabled={isSaved}>
                            Delete obstacle</Button>
                    </ScrollView>}
                </View>
            </View>
            <SaveButton
                isSaved={isSaved}
                save={save}
                disabled={!isEditable()}/>
            {isDetecting && <ActivityIndicator size="large" color={Colors.lightGrey} style={styles.spinner}/>}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '100%'
    },
    editContainer: {
        flex: .83
    },
    imageContainer: {
        flex: .5,
        backgroundColor: Colors.lightGrey,
        padding: 10,
        alignItems: 'center',
        justifyContent: 'center'
    },
    detailsContainer: {
        flex: .5,
        padding: 10,
        backgroundColor: Colors.white,
        zIndex: 100
    },
    button: {
        marginBottom: 8
    },
    spinner: {
        position: 'absolute',
        top: 170,
        left: 170
    }
});

export default MapObstaclesForm;
