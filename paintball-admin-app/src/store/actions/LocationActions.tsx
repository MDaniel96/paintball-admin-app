import {Location} from '../../model/Location';
import {LocationService} from '../../service/LocationService';

export type LocationAction = {
    type: string;
    locations: Location[];
}

export const SET_LOCATIONS = 'SET_LOCATIONS';

type LocationDispatch = (args: LocationAction) => LocationAction

export const getLocationsAction = () => {
    return async (dispatch: LocationDispatch) => {
        const locations = await LocationService.getLocations();
        dispatch({
            type: SET_LOCATIONS,
            locations: locations
        });
    }
}
