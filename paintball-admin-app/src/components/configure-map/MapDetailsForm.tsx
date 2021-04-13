import React, {FC, useEffect, useState} from 'react';
import {Picker, ScrollView, StyleSheet, View} from 'react-native';
import SaveButton from './SaveButton';
import Colors from '../../constants/Colors';
import {TextInput} from 'react-native-paper';
import {Location} from '../../model/Location';
import {LocationService} from '../../service/LocationService';

const MapDetailsForm: FC = () => {

    const [isSaved, setSaved] = useState<boolean>(false);

    const [name, setName] = useState<string>('');
    const [orientation, setOrientation] = useState<string>('');
    const [location, setLocation] = useState<Location>(new Location());

    const [locations, setLocations] = useState<Location[]>([]);

    useEffect(() => {
        getLocations();
    }, []);

    const getLocations = () => {
        LocationService.getLocations().then(data => {
            setLocations(data);
        });
    }

    const save = () => {
        if (name != '' && orientation != '' && location.name != '') {
            setSaved(true);
            console.log('selected location', location);
        } else {
            alert('Please fill out all details');
        }
    }

    return (
        <View style={styles.container}>
            <View style={styles.editContainer}>
                <View style={styles.imageContainer}>
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
            <SaveButton isSaved={isSaved} save={save}/>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '100%'
    },
    editContainer: {
        flex: .83
    },
    imageContainer: {
        flex: .5,
        backgroundColor: Colors.lightGrey,
        padding: 15
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
