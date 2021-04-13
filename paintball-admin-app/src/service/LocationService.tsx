import Config from '../constants/Config';
import {Location} from '../model/Location';

const LOCATION_API = `${Config.base_url}/api/location`;

export class LocationService {

    static async getLocations(): Promise<Location[]> {
        try {
            let response = await fetch(LOCATION_API);
            return await response.json();
        } catch (error) {
            console.error('Getting locations', error);
            return [];
        }
    }
}
