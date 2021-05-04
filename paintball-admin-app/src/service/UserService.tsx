import {Map} from '../model/Map';
import Config from '../constants/Config';
import {User} from '../model/User';

const USER_API = `${Config.base_url}/api/user`;

export class UserService {

    static async getMapUnderCreation(userId: number): Promise<Map> {
        try {
            let url = `${USER_API}/${userId}`;
            let response = await fetch(url);
            let user: User = await response.json();
            return user.mapsUnderCreation[0];
        } catch (error) {
            console.error('Getting map under creation', error);
            return new Map();
        }
    }
}