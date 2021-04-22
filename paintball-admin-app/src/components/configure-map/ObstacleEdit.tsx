import React, {FC, useEffect, useState} from 'react';
import {StyleSheet, View} from 'react-native';
import {Obstacle} from '../../model/Obstacle';
import RectEdit from './RectEdit';

interface Props {
    obstacle: Obstacle;
    setObstacle: (obstacle: Obstacle) => void;
    isSaved: boolean;
}

const ObstacleEdit: FC<Props> = (props) => {

    const [x, setX] = useState<number>(-1);
    const [y, setY] = useState<number>(-1);
    const [width, setWidth] = useState<number>(-1);
    const [height, setHeight] = useState<number>(-1);

    useEffect(() => {
        setX(-1);
        setY(-1);
        setWidth(-1);
        setHeight(-1);
    }, [props.obstacle]);

    useEffect(() => {
        props.setObstacle(
            {
                id: props.obstacle.id,
                x: x === -1 ? props.obstacle.x : x,
                y: y === -1 ? props.obstacle.y : y,
                width: width === -1 ? props.obstacle.width : width,
                height: height === -1 ? props.obstacle.height : height,
                selected: props.obstacle.selected
            }
        )
    }, [x, y, width, height]);

    return (
        <View style={styles.editArea}>
            <RectEdit
                label="X"
                value={props.obstacle.x}
                setValue={setX}
                disabled={props.isSaved}/>
            <RectEdit
                label="Y"
                value={props.obstacle.y}
                setValue={setY}
                disabled={props.isSaved}/>
            <RectEdit
                label="Width"
                value={props.obstacle.width}
                setValue={setWidth}
                disabled={props.isSaved}/>
            <RectEdit
                label="Height"
                value={props.obstacle.height}
                setValue={setHeight}
                disabled={props.isSaved}/>
        </View>
    );
}

const styles = StyleSheet.create({
    editArea: {
        marginTop: 10,
    }
});

export default ObstacleEdit;

