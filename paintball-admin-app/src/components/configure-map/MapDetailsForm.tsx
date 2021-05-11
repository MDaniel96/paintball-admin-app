import React, {FC, useEffect, useState} from 'react';
import {Image, Picker, Platform, ScrollView, StyleSheet, View} from 'react-native';
import SaveButton from './SaveButton';
import Colors from '../../constants/Colors';
import {Button, TextInput} from 'react-native-paper';
import {Location} from '../../model/Location';
import {LocationService} from '../../service/LocationService';
import * as ImagePicker from 'expo-image-picker';
import {ImageInfo} from 'expo-image-picker/build/ImagePicker.types';
import {MAP_API, MapService} from '../../service/MapService';
import {MapConfigurationProps} from './MapConfigurationProps';

const MapDetailsForm: FC<MapConfigurationProps> = (props) => {

    const [isSaved, setSaved] = useState<boolean>(false);

    const [image, setImage] = useState<ImageInfo>();
    const [name, setName] = useState<string>('');
    const [orientation, setOrientation] = useState<string>('');
    const [location, setLocation] = useState<Location>(new Location());

    const [locations, setLocations] = useState<Location[]>([]);

    useEffect(() => {
        getLocations();
        initValues();
        askGalleryPermission();
    }, []);

    const getLocations = () => {
        LocationService.getLocations().then(data => {
            setLocations(data);
        });
    }

    const initValues = () => {
        if (props.map.name) {
            setName(props.map.name);
            setOrientation(props.map.orientation.toString());
            setLocation(props.map.location);
            setSaved(true);
        }
    }

    const selectImage = async () => {
        let result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.All,
            base64: true,
            quality: 1,
        });
        if (!result.cancelled) {
            setImage(result);
        }
    };

    const askGalleryPermission = async () => {
        if (Platform.OS !== 'web') {
            const {status} = await ImagePicker.requestMediaLibraryPermissionsAsync();
            if (status !== 'granted') {
                alert('Sorry, we need camera roll permissions to make this work!');
            }
        }
    }

    const save = () => {
        if (!isSaved && name != '' && orientation != '' && location.name != '' && image && image.base64) {
            MapService.createMap({
                locationId: location.id,
                name: name,
                imageBase64: image.base64,
                orientation: parseInt(orientation),
                width: image.width,
                height: image.height
            }).then(() => {
                setSaved(true);
                setImage(undefined);
                props.onDataSaved();
            });
        } else {
            alert('Please fill out all details');
        }
    }

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <View style={styles.imageContainer}>
                    <Button style={styles.pickButton} mode="contained" onPress={selectImage}>Select map image</Button>
                    {image && <Image source={{uri: image.uri}} style={{width: '100%', height: '100%'}}/>}
                    {props.map.name !== '' &&
                    <Image source={{uri: `${MAP_API}/image/${props.map.id}`}} style={{width: '100%', height: '100%'}}/>}
                </View>
                <View style={styles.detailsContainer}>
                    <ScrollView>
                        <TextInput
                            style={styles.input}
                            label="Map name"
                            mode="outlined"
                            onChangeText={(text) => setName(text)}
                            value={name}/>
                        <TextInput
                            style={styles.input}
                            label="Map orientation"
                            keyboardType="numeric"
                            mode="outlined"
                            onChangeText={(text) => setOrientation(text)}
                            value={orientation}/>
                        <View style={{borderWidth: 1, borderRadius: 4, backgroundColor: Colors.lightGrey}}>
                            <Picker
                                selectedValue={location}
                                style={{height: 60, width: '100%', borderStyle: 'solid'}}
                                onValueChange={(itemValue, _) => setLocation(itemValue)}>
                                <Picker.Item value={new Location()} label='Map location'/>
                                {locations.map(loc => (
                                    <Picker.Item key={loc.id} label={loc.name} value={loc}/>
                                ))}
                            </Picker>
                        </View>
                    </ScrollView>
                </View>
            </View>
            <SaveButton
                isSaved={isSaved}
                save={save}
                disabled={false}/>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '100%',
        zIndex: 2000,
        backgroundColor: Colors.white
    },
    editContainer: {
        flex: .83
    },
    imageContainer: {
        flex: .5,
        backgroundColor: Colors.lightGrey,
        padding: 10,
        alignItems: 'center',
        justifyContent: 'center'
    },
    pickButton: {
        position: 'absolute',
        zIndex: 100
    },
    detailsContainer: {
        flex: .5,
        borderRadius: 10,
        padding: 20
    },
    input: {
        marginBottom: 15
    }
});

export default MapDetailsForm;
