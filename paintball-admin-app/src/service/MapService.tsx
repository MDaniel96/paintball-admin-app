import {Map} from '../model/Map';
import Config from '../constants/Config';
import {CreateMapRequest} from '../model/util/CreateMapRequest';

export const MAP_API = `${Config.base_url}/api/map`;

export class MapService {

    static async createMap(createMapRequest: CreateMapRequest): Promise<Map> {
        try {
            let response = await fetch(MAP_API, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(createMapRequest)
            });
            return await response.json();
        } catch (error) {
            console.error('Creating map', error);
            return new Map();
        }
    }
}
