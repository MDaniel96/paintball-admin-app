import {Obstacle} from '../model/Obstacle';
// @ts-ignore
import cloneDeep from 'lodash/cloneDeep';

export class ObstacleConverter {

    mapWidth: number;
    mapHeight: number;
    screenWidth: number;
    screenHeight: number;

    constructor(mapWidth: number = -1, mapHeight: number = -1, screenWidth: number = -1, screenHeight: number = -1) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    fromImageToScreen(imageObstacles: Obstacle[]): Obstacle[] {
        const screenObstacles: Obstacle[] = cloneDeep(imageObstacles);
        screenObstacles.forEach(o => {
            o.x = Math.round(o.x * this.screenWidth / this.mapWidth);
            o.y = Math.round(o.y * this.screenHeight / this.mapHeight);
            o.width = Math.round(o.width * this.screenWidth / this.mapWidth);
            o.height = Math.round(o.height * this.screenHeight / this.mapHeight);
        });
        return screenObstacles;
    }

    fromDetectionToScreen(imageObstacles: Obstacle[]): Obstacle[] {
        const screenObstacles: Obstacle[] = cloneDeep(imageObstacles);
        screenObstacles.forEach(o => {
            const widthDiff = (this.mapWidth - this.screenWidth) / 2;
            const heightDiff = (this.mapHeight - this.screenHeight) / 2;
            o.x = Math.round(o.x - widthDiff);
            o.y = Math.round(o.y - heightDiff);
            o.width = Math.round(o.width);
            o.height = Math.round(o.height);
        });
        return screenObstacles;
    }

    fromScreenToImage(screenObstacles: Obstacle[]): Obstacle[] {
        const imageObstacles: Obstacle[] = cloneDeep(screenObstacles);
        imageObstacles.forEach(o => {
            o.x = o.x * this.mapWidth / this.screenWidth;
            o.y = o.y * this.mapHeight / this.screenHeight;
            o.width = o.width * this.mapWidth / this.screenWidth;
            o.height = o.height * this.mapHeight / this.screenHeight;
        });
        return imageObstacles;
    }
}
