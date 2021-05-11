import {User} from './User';

export class Game {
    id: number = 0;
    name: string = '';
    type: string = '';
    state: string = '';
    connectionMode: string = '';
    date: Date = new Date();
    redPlayers: User[] = [];
    bluePlayers: User[] = [];
    deadPlayers: User[] = [];
}
