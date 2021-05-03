import React, {FC, useState} from 'react';
import {ScrollView, StyleSheet, View} from 'react-native';
import SaveButton from './SaveButton';
import {MapConfigurationProps} from './MapConfigurationProps';
import MapPanel from './MapPanel';
import {Circle} from 'react-native-svg';
import {Button, TextInput} from 'react-native-paper';
import Colors from '../../constants/Colors';
import {Anchor} from '../../model/Anchor';

const MapAnchorsForm: FC<MapConfigurationProps> = (props) => {

    const [isSaved, setSaved] = useState<boolean>(false);

    const [radiusPx, setRadiusPx] = useState<string>('');
    const [radiusMeter, setRadiusMeter] = useState<string>('');

    const [editWidth, setEditWidth] = useState<number>(-1);
    const [editHeight, setEditHeight] = useState<number>(-1);

    const [anchors, setAnchors] = useState<Anchor[]>([]);

    const isEditable = () => props.map.obstacles.length !== 0;

    const calculateAnchors = () => {
        setAnchors(
            [
                {id: Math.floor(Math.random() * 1000), x: 100, y: 100, radius: 20},
                {id: Math.floor(Math.random() * 1000), x: 150, y: 150, radius: 40},
                {id: Math.floor(Math.random() * 1000), x: 250, y: 250, radius: 50}
            ]
        );
    }

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <View style={styles.imageContainer} onLayout={(event) => {
                    setEditWidth(event.nativeEvent.layout.width);
                    setEditHeight(event.nativeEvent.layout.height);
                }}>
                    <MapPanel
                        editHeight={editHeight}
                        editWidth={editWidth}
                        mapId={props.map.id}>
                        {anchors.map(anchor => (
                            <Circle key={anchor.id} cx={anchor.x} cy={anchor.y} r={anchor.radius}
                                    stroke="yellow" strokeWidth="2"/>
                        ))}
                    </MapPanel>
                </View>
                <View style={styles.detailsContainer}>
                    <ScrollView>
                        <TextInput
                            style={styles.input}
                            label="Anchors' radius in meters"
                            keyboardType="numeric"
                            mode="outlined"
                            onChangeText={(text) => setRadiusMeter(text)}
                            value={radiusMeter}/>
                        <TextInput
                            style={styles.input}
                            label="Anchors' radius in pixel"
                            keyboardType="numeric"
                            mode="outlined"
                            onChangeText={(text) => setRadiusPx(text)}
                            value={radiusPx}/>
                        <Button mode="contained" disabled={!radiusMeter || !radiusPx}
                                onPress={calculateAnchors}>Calculate anchor positions</Button>
                    </ScrollView>
                </View>
            </View>
            <SaveButton
                isSaved={isSaved}
                save={() => setSaved(true)}
                disabled={!isEditable()}/>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        width: '100%',
        height: '100%'
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
        backgroundColor: Colors.white,
        padding: 20,
        zIndex: 100
    },
    input: {
        marginBottom: 15
    }
});

export default MapAnchorsForm;
