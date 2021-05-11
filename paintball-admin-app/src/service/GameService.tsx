import {Game} from '../model/Game';
import Config from '../constants/Config';
import {GameFilter} from '../model/util/GameFilter';
import {Platform} from 'react-native';

const GAME_API = `${Config.base_url}/api/game`;

export class GameService {

    static async getGames(gameFilter: GameFilter): Promise<Game[]> {
        try {
            let url = `${GAME_API}${this.getQueryParams(gameFilter)}`;
            let response = await fetch(url);
            return await response.json();
        } catch (error) {
            console.error('Getting games', error);
            return [];
        }
    }

    static async getGame(id: number): Promise<Game> {
        try {
            let url = `${GAME_API}/${id}`;
            let response = await fetch(url);
            return await response.json();
        } catch (error) {
            console.error('Getting game', error);
            return new Game();
        }
    }

    static async deleteGame(id: number): Promise<any> {
        try {
            let url = `${GAME_API}/${id}`;
            return await fetch(url, {method: 'DELETE'});
        } catch (error) {
            console.error('Deleting game', error);
            return [];
        }
    }

    static async editGame(id: number, game: Game): Promise<Game> {
        try {
            let url = `${GAME_API}/${id}`;
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

    static async createGame(name: string, type: string, mapId: number, localizationMode: string): Promise<Game> {
        try {
            let url = `${GAME_API}`;
            let response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    name, type, mapId, localizationMode
                })
            });
            return await response.json();
        } catch (error) {
            console.error('Creating game', error);
            return new Game();
        }
    }

    static async changeGameState(id: number, newState: string): Promise<Game> {
        try {
            let url = `${GAME_API}/${id}/changeState?newState=${newState}`;
            let response = await fetch(url, {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({})
            });
            return await response.json();
        } catch (error) {
            console.error('Creating game', error);
            return new Game();
        }
    }

    static async kickPlayerFromGame(gameId: number, playerId: number, color: string): Promise<Game> {
        try {
            let url = `${GAME_API}/${gameId}/kickPlayer?playerId=${playerId}&color=${color}`;
            let response = await fetch(url, {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({})
            });
            return await response.json();
        } catch (error) {
            console.error('Creating game', error);
            return new Game();
        }
    }

    static async sendVoiceMessage(gameId: number, targetTeam: string, message: string): Promise<void> {
        try {
            let url = `${GAME_API}/${gameId}/sendVoiceMessage?target=${targetTeam}`;
            let response = await fetch(url, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(message)
            });
            return await response.json();
        } catch (error) {
            console.error('Creating game', error);
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
