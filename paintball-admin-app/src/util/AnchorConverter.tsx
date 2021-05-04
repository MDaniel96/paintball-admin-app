// @ts-ignore
import cloneDeep from 'lodash/cloneDeep';
import {Anchor} from '../model/Anchor';

export class AnchorConverter {

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

    fromImageToScreen(imageAnchors: Anchor[]): Anchor[] {
        const screenAnchors: Anchor[] = cloneDeep(imageAnchors);
        screenAnchors.forEach(o => {
            o.x = Math.round(o.x * this.screenWidth / this.mapWidth);
            o.y = Math.round(o.y * this.screenHeight / this.mapHeight);
            o.radius = Math.round(o.radius * this.screenWidth / this.mapWidth);
        });
        return screenAnchors;
    }
}
