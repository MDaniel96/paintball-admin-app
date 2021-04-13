import {Anchor} from './Anchor';
import {Obstacle} from './Obstacle';
import {Location} from './Location';

export class Map {
    id: number = 0;
    name: string = '';
    orientation: number = -1;
    borderX: number = -1;
    borderY: number = -1;
    borderWidth: number = -1;
    borderHeight: number = -1;
    location: Location = new Location();
    obstacles: Obstacle[] = [];
    anchors: Anchor[] = [];
}
