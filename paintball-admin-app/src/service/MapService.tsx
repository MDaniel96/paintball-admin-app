import {Map} from '../model/Map';
import Config from '../constants/Config';
import {CreateMapRequest} from '../model/util/CreateMapRequest';
import {EditBordersRequest} from '../model/util/EditBordersRequest';
import {EditObstaclesRequest} from '../model/util/EditObstaclesRequest';
import {Anchor} from '../model/Anchor';
import {EditAnchorsRequest} from '../model/util/EditAnchorsRequest';

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

    static async editAnchors(id: number, editAnchorsRequest: EditAnchorsRequest): Promise<Map> {
        try {
            let response = await fetch(`${MAP_API}/${id}`, {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(editAnchorsRequest)
            });
            return await response.json();
        } catch (error) {
            console.error('Editing map anchors', error);
            return new Map();
        }
    }

    static async finishEdit(id: number): Promise<Map> {
        try {
            let response = await fetch(`${MAP_API}/${id}/finish-edit`, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'}
            });
            return await response.json();
        } catch (error) {
            console.error('Finish editing map', error);
            return new Map();
        }
    }

    static async calculateAnchors(id: number, anchorRadius: number): Promise<Anchor[]> {
        try {
            let url = `${MAP_API}/${id}/calculate-anchors/${anchorRadius}`;
            let response = await fetch(url);
            return await response.json();
        } catch (error) {
            console.error('Calculating anchors', error);
            return [];
        }
    }
}
