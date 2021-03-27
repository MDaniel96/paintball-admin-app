import {Game} from '../model/Game';
import Config from '../constants/Config';

export class GameService {

    static async getGames(): Promise<Game[]> {
        try {
            let response = await fetch(`${Config.base_url}/api/game`);
            return await response.json();
        } catch (error) {
            console.error(error);
            return [];
        }
    }
}
