export class CreateMapRequest {
    locationId: number = -1;
    name: string = '';
    imageBase64: string = '';
    orientation: number = -1;
    topLeftLatitude: number = -1;
    topLeftLongitude: number = -1;
    topRightLongitude: number = -1;
    width: number = -1;
    height: number = -1;
}
