import React, {FC, useEffect, useState} from 'react';
import {Image, LogBox, ScrollView, StyleSheet, View} from 'react-native';
import SaveButton from './SaveButton';
import {MapConfigurationProps} from './MapConfigurationProps';
import Colors from '../../constants/Colors';
import SvgPanZoom from 'react-native-svg-pan-zoom';
import {MAP_API, MapService} from '../../service/MapService';
import {ForeignObject, Rect} from 'react-native-svg';
import RectEdit from './RectEdit';

const MapEdgesForm: FC<MapConfigurationProps> = (props) => {
    LogBox.ignoreLogs(['componentWillMount has been renamed']);
    LogBox.ignoreLogs(['componentWillReceiveProps has been renamed']);

    const [isSaved, setSaved] = useState<boolean>(false);

    const [editWidth, setEditWidth] = useState<number>(-1);
    const [editHeight, setEditHeight] = useState<number>(-1);

    const [borderX, setBorderX] = useState<number>(50);
    const [borderY, setBorderY] = useState<number>(50);
    const [borderWidth, setBorderWidth] = useState<number>(250);
    const [borderHeight, setBorderHeight] = useState<number>(150);

    const isEditable = () => props.map.id;

    useEffect(() => {
        initValues();
    }, [editHeight, editWidth]);

    const initValues = () => {
        if (props.map.borderX !== -1 && props.map.borderY !== -1) {
            setBorderX(Math.round(props.map.borderX * editWidth / props.map.width));
            setBorderY(Math.round(props.map.borderY * editHeight / props.map.height));
            setBorderWidth(Math.round(props.map.borderWidth * editWidth / props.map.width));
            setBorderHeight(Math.round(props.map.borderHeight * editHeight / props.map.height));
            setSaved(true);
        }
    }

    const save = () => {
        if (!isSaved && borderWidth !== 250 && borderHeight !== 250) {
            MapService.editBorders(props.map.id, {
                borderX: borderX * props.map.width / editWidth,
                borderY: borderY * props.map.height / editHeight,
                borderWidth: borderWidth * props.map.width / editWidth,
                borderHeight: borderHeight * props.map.height / editHeight,
            }).then(() => {
                setSaved(true);
                props.onDataSaved();
            });
        } else {
            alert('Please edit border');
        }
    }

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <View style={styles.imageContainer} onLayout={(event) => {
                    setEditWidth(event.nativeEvent.layout.width);
                    setEditHeight(event.nativeEvent.layout.height);
                }}>
                    <SvgPanZoom
                        canvasHeight={editHeight}
                        canvasWidth={editWidth}
                        minScale={1.1}
                        maxScale={3.0}
                        initialZoom={1.5}
                        onZoom={() => {
                        }}
                        canvasStyle={{backgroundColor: Colors.black}}
                        viewStyle={{backgroundColor: Colors.lightGrey}}>
                        <ForeignObject>
                            <Image
                                source={{uri: `${MAP_API}/${props.map.id}/image`}}
                                style={{width: '100%', height: '100%'}}
                            />
                        </ForeignObject>
                        <Rect x={borderX} y={borderY} width={borderWidth} height={borderHeight} stroke="red"
                              strokeWidth="2"/>
                    </SvgPanZoom>
                </View>
                <View style={styles.detailsContainer}>
                    <ScrollView>
                        <RectEdit
                            label="Border X"
                            value={borderX}
                            setValue={setBorderX}
                            disabled={isSaved}/>
                        <RectEdit
                            label="Border Y"
                            value={borderY}
                            setValue={setBorderY}
                            disabled={isSaved}/>
                        <RectEdit
                            label="Border Width"
                            value={borderWidth}
                            setValue={setBorderWidth}
                            disabled={isSaved}/>
                        <RectEdit
                            label="Border Height"
                            value={borderHeight}
                            setValue={setBorderHeight}
                            disabled={isSaved}/>
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
        padding: 20,
        backgroundColor: Colors.white,
        zIndex: 100
    }
});

export default MapEdgesForm;
