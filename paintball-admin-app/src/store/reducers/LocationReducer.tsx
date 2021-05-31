import {Location} from '../../model/Location';
import {LocationAction, SET_LOCATIONS} from '../actions/LocationActions';

export type LocationState = {
    locations: Location[];
}

const locationReducer = (state: LocationState = {locations: []}, action: LocationAction): LocationState => {
    switch (action.type) {
        case SET_LOCATIONS:
            return {
                locations: action.locations,
            };
    }

    return {
        locations: state.locations,
    };
}

export default locationReducer;
