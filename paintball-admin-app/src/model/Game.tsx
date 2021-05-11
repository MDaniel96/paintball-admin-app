import {User} from './User';
import {Map} from './Map';

export class Game {
    id: number = 0;
    name: string = '';
    type: string = '';
    state: string = '';
    localizationMode: string = '';
    date: Date = new Date();
    redPlayers: User[] = [];
    bluePlayers: User[] = [];
    deadPlayers: User[] = [];
    map: Map = new Map();
}
