import {Game} from '../model/Game';
import Config from '../constants/Config';
import {GameFilter} from '../model/util/GameFilter';
import {Platform} from 'react-native';

export class GameService {

    static async getGames(gameFilter: GameFilter): Promise<Game[]> {
        try {
            let url = `${Config.base_url}/api/game${this.getQueryParams(gameFilter)}`;
            let response = await fetch(url);
            return await response.json();
        } catch (error) {
            console.error('Getting games', error);
            return [];
        }
    }

    static async getGame(id: number): Promise<Game> {
        try {
            let url = `${Config.base_url}/api/game/${id}`;
            let response = await fetch(url);
            return await response.json();
        } catch (error) {
            console.error('Getting game', error);
            return new Game();
        }
    }

    static async deleteGame(id: number): Promise<any> {
        try {
            let url = `${Config.base_url}/api/game/${id}`;
            return await fetch(url, {method: 'DELETE'});
        } catch (error) {
            console.error('Deleting game', error);
            return [];
        }
    }

    static async editGame(id: number, game: Game): Promise<Game> {
        try {
            let url = `${Config.base_url}/api/game/${id}`;
            let response = await fetch(url, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(game)
            });
            return await response.json();
        } catch (error) {
            console.error('Editing game', error);
            return new Game();
        }
    }

    private static getQueryParams(gameFilter: GameFilter): string {
        let query = gameFilter.state ? '?&state=' + gameFilter.state : '';
        query += gameFilter.name ? '&name=' + gameFilter.name : '';
        query += gameFilter.type ? `&type=${gameFilter.type}` : '';
        if (Platform.OS !== 'ios') {
            query += gameFilter.date ? `&date=${gameFilter.date.toISOString().split('T')[0]}` : '';
        }
        return query;
    }
}
