import React, {FC, useEffect, useState} from 'react';
import {ScrollView, StyleSheet, View} from 'react-native';
import SaveButton from './SaveButton';
import {MapConfigurationProps} from './MapConfigurationProps';
import MapPanel from './MapPanel';
import {Circle} from 'react-native-svg';
import {Button, TextInput} from 'react-native-paper';
import Colors from '../../constants/Colors';
import {Anchor} from '../../model/Anchor';
import {MapService} from '../../service/MapService';
import {AnchorConverter} from '../../util/AnchorConverter';

const MapAnchorsForm: FC<MapConfigurationProps> = (props) => {

    const [isSaved, setSaved] = useState<boolean>(false);

    const [radiusPx, setRadiusPx] = useState<string>('');
    const [radiusMm, setRadiusMm] = useState<string>('');

    const [editWidth, setEditWidth] = useState<number>(-1);
    const [editHeight, setEditHeight] = useState<number>(-1);

    const [anchors, setAnchors] = useState<Anchor[]>([]);
    const [imageAnchors, setImageAnchors] = useState<Anchor[]>([]);

    const isEditable = () => props.map.obstacles.length !== 0;

    useEffect(() => {
        setAnchors([]);
        initValues();
    }, [editHeight, editWidth]);

    const initValues = () => {
        if (props.map.anchors.length !== 0) {
            const anchorConverter = new AnchorConverter(props.map.width, props.map.height, editWidth, editHeight);
            setRadiusMm(props.map.anchorRadiusInMm.toString());
            setRadiusPx(props.map.anchorRadiusInPixels.toString());
            setAnchors(anchorConverter.fromImageToScreen(props.map.anchors));
            setSaved(true);
        }
    }

    const calculateAnchors = () => {
        MapService.calculateAnchors(props.map.id, parseInt(radiusPx)).then(data => {
            setImageAnchors(data);
            const anchorConverter = new AnchorConverter(props.map.width, props.map.height, editWidth, editHeight);
            setAnchors(anchorConverter.fromImageToScreen(data));
        });
    }

    const save = () => {
        if (!isSaved && anchors.length !== 0) {
            imageAnchors.forEach(anchor => anchor.id = -1);
            MapService.editAnchors(props.map.id, {
                anchors: imageAnchors,
                anchorRadiusInMm: parseInt(radiusMm),
                anchorRadiusInPixels: parseInt(radiusPx)
            }).then(() => {
                setSaved(true);
                props.onDataSaved();
            });
        } else {
            alert('Please calculate anchors');
        }
    }

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <View style={styles.imageContainer} onLayout={(event) => {
                    setEditWidth(event.nativeEvent.layout.width);
                    setEditHeight(event.nativeEvent.layout.height);
                }}>
                    {isEditable() &&
                    <MapPanel
                        editHeight={editHeight}
                        editWidth={editWidth}
                        mapId={props.map.id}>
                        {anchors.map(anchor => (
                            <View key={anchor.id}>
                                <Circle cx={anchor.x} cy={anchor.y} r={anchor.radius}
                                        stroke="yellow" strokeWidth="1.8"/>
                                <Circle cx={anchor.x} cy={anchor.y}
                                        r="5" stroke="red" fill="red" strokeWidth="1"/>
                            </View>
                        ))}
                    </MapPanel>
                    }
                </View>
                <View style={styles.detailsContainer}>
                    <ScrollView>
                        <TextInput
                            style={styles.input}
                            label="Anchors' radius in mm"
                            keyboardType="numeric"
                            mode="outlined"
                            disabled={isSaved}
                            onChangeText={(text) => setRadiusMm(text)}
                            value={radiusMm}/>
                        <TextInput
                            style={styles.input}
                            label="Anchors' radius in pixel"
                            keyboardType="numeric"
                            mode="outlined"
                            disabled={isSaved}
                            onChangeText={(text) => setRadiusPx(text)}
                            value={radiusPx}/>
                        <Button mode="contained" disabled={!radiusMm || !radiusPx || isSaved}
                                onPress={calculateAnchors}>Calculate anchor positions</Button>
                    </ScrollView>
                </View>
            </View>
            <SaveButton
                isSaved={isSaved}
                save={save}
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
