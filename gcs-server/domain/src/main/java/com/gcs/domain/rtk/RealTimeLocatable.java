package com.gcs.domain.rtk;

import com.gcs.domain.coordinate.ned.NedLocatable;

public interface RealTimeLocatable {
    default void getRtkN(){
        this.getRtkCoordinate().getY();
    }
    default void getRtkE(){
        this.getRtkCoordinate().getY();
    }
    default void getRtkD(){
        this.getRtkCoordinate().getZ();
    }

    NedLocatable getRtkCoordinate();

}
