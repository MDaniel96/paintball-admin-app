import {Map} from '../model/Map';
import Config from '../constants/Config';
import {User} from '../model/User';
import {RegisterUserRequest} from '../model/util/RegisterUserRequest';

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

    static async getLoggedInUser(): Promise<User> {
        try {
            let url = `${USER_API}/current`;
            let response = await fetch(url);
            return await response.json();
        } catch (error) {
            console.log('Getting logged in user', error);
            return new User();
        }
    }

    static async registerUser(registerUserRequest: RegisterUserRequest): Promise<User> {
        try {
            let url = `${USER_API}/register`;
            let response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(registerUserRequest)
            });
            return await response.json();
        } catch (error) {
            console.error('Registering user', error);
            return new User();
        }
    }
}
