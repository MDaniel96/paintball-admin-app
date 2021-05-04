import {Anchor} from '../Anchor';

export class EditAnchorsRequest {
    anchors: Anchor[] = [];
    anchorRadiusInMm: number = -1;
    anchorRadiusInPixels: number = -1;
}
