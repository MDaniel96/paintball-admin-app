import {User} from './User';

export class Game {
    id: number = 0;
    name: string = '';
    type: string = '';
    state: string = '';
    date: Date = new Date();
    redPlayers: User[] = [];
    bluePlayers: User[] = [];
}
