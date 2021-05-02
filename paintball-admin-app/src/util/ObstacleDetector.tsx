import {fetch} from '@tensorflow/tfjs-react-native';
import * as tf from '@tensorflow/tfjs';
import {LogBox} from 'react-native';
import * as jpeg from 'jpeg-js'
import * as cocossd from "@tensorflow-models/coco-ssd";
import {Obstacle} from '../model/Obstacle';
import {MAP_API} from '../service/MapService';

export class ObstacleDetector {

    static async detect(mapId: number): Promise<Obstacle[]> {
        LogBox.ignoreLogs(['tf.nonMaxSuppression()']);

        await tf.ready();
        console.log('Detection started');
        const model = await cocossd.load();

        const response = await fetch(`${MAP_API}/${mapId}/jpeg`, {}, {isBinary: true});
        const imageData = await response.arrayBuffer();

        const imageTensor = this.imageToTensor(imageData);
        const predictions = await model.detect(imageTensor);

        return predictions.map(prediction => {
            return {
                x: prediction.bbox[0], y: prediction.bbox[1],
                width: prediction.bbox[2], height: prediction.bbox[3],
                id: Math.floor(Math.random() * 1000), selected: false
            }
        });
    }

    private static imageToTensor(rawImageData: ArrayBuffer) {
        const {width, height, data} = jpeg.decode(rawImageData, {useTArray: true});

        // Drop the alpha channel info for mobilenet
        const buffer = new Uint8Array(width * height * 3);

        let offset = 0; // offset into original data
        for (let i = 0; i < buffer.length; i += 3) {
            buffer[i] = data[offset];
            buffer[i + 1] = data[offset + 1];
            buffer[i + 2] = data[offset + 2];
            offset += 4;
        }

        return tf.tensor3d(buffer, [height, width, 3]);
    }
}
