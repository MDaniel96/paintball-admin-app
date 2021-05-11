import React, {FC} from 'react';
import {Image} from 'react-native';
import Colors from '../../constants/Colors';
import {ForeignObject} from 'react-native-svg';
import {MAP_API} from '../../service/MapService';
import SvgPanZoom from 'react-native-svg-pan-zoom';

interface Props {
    editHeight: number;
    editWidth: number;
    mapId: number;
}

const MapPanel: FC<Props> = (props) => {

    return (
        <SvgPanZoom
            canvasHeight={props.editHeight}
            canvasWidth={props.editWidth}
            minScale={1.1}
            maxScale={3.0}
            initialZoom={1.5}
            onZoom={() => {
            }}
            canvasStyle={{backgroundColor: Colors.black}}
            viewStyle={{backgroundColor: Colors.lightGrey}}>
            <ForeignObject>
                <Image
                    source={{uri: `${MAP_API}/image/${props.mapId}`}}
                    style={{width: '100%', height: '100%'}}/>
            </ForeignObject>
            {props.children}
        </SvgPanZoom>
    );
}

export default MapPanel;

