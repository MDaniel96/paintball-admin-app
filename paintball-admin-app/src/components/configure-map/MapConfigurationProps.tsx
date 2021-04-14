import {Map} from '../../model/Map';

export interface MapConfigurationProps {
    map: Map;
    onDataSaved: () => void;
}
