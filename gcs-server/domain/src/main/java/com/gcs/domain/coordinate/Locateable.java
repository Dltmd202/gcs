package com.gcs.domain.coordinate;

import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;

public interface Locateable extends LlhLocatable, NedLocatable {
    LlhLocatable getLlh();
    NedLocatable getNed();

    default float getLat(){
        return getLlh().getLat();
    }
    default float getLng(){
        return getLlh().getLng();
    }
    default float getAlt(){
        return getLlh().getAlt();
    }

    default float getX() {
        return getNed().getX();
    }

    default float getY(){
        return getNed().getY();
    }

    @Override
    default float getZ() {
        return getNed().getZ();
    }
}
