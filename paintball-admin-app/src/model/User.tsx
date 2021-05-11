import {Map} from './Map';
import {Role} from './Role';

export class User {
    id: number = 0;
    username: string = '';
    mapsUnderCreation: Map[] = [];
    roles: Role[] = [];
}
