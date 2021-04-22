import {Map} from '../model/Map';
import Config from '../constants/Config';
import {CreateMapRequest} from '../model/util/CreateMapRequest';
import {EditBordersRequest} from '../model/util/EditBordersRequest';
import {EditObstaclesRequest} from '../model/util/EditObstaclesRequest';

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

    static async editBorders(id: number, editBordersRequest: EditBordersRequest): Promise<Map> {
        try {
            let response = await fetch(`${MAP_API}/${id}`, {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(editBordersRequest)
            });
            return await response.json();
        } catch (error) {
            console.error('Editing map borders', error);
            return new Map();
        }
    }

    static async editObstacles(id: number, editObstaclesRequest: EditObstaclesRequest): Promise<Map> {
        try {
            let response = await fetch(`${MAP_API}/${id}`, {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(editObstaclesRequest)
            });
            return await response.json();
        } catch (error) {
            console.error('Editing map obstacles', error);
            return new Map();
        }
    }
}
