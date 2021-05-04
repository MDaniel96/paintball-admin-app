import {Anchor} from './Anchor';
import {Obstacle} from './Obstacle';
import {Location} from './Location';

export class Map {
    id: number = 0;
    name: string = '';
    orientation: number = -1;
    width: number = -1;
    height: number = -1;
    borderX: number = -1;
    borderY: number = -1;
    borderWidth: number = -1;
    borderHeight: number = -1;
    anchorRadiusInMm: number = -1;
    anchorRadiusInPixels: number = -1;
    location: Location = new Location();
    obstacles: Obstacle[] = [];
    anchors: Anchor[] = [];
}
