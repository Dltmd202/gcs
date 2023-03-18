import React, {useEffect} from "react";
import {path} from "../../atoms/Icon/DroneMarkerIcon";

interface DroneMarkerOptions extends google.maps.MarkerOptions {
    color: string,
    complementaryColor: string,
    focused: boolean
}

const Drone: React.FC<DroneMarkerOptions> = ({
                                                 color,
                                                 position,
                                                 complementaryColor,
                                                 focused,
                                                 ...options}) => {
    const [marker, setMarker] = React.useState<google.maps.Marker>();


    useEffect(() => {
        if (!marker) {
            setMarker(new google.maps.Marker());
        }


        return () => {
            if (marker) {
                marker.setMap(null);
            }
        };
    }, [marker]);

    useEffect(() => {
        if (marker) {
            marker.setOptions(options);
            marker.setPosition(position);
            marker.setIcon({
                path: path,
                fillColor: color,
                fillOpacity: 0.9,
                scale: 0.07
            });
        }
    }, [marker, options, position]);

    return null;
};

export default Drone