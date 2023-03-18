package com.gcs.domain.coordinate;

import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import lombok.Setter;

@Setter
public class Locate implements Locateable{
    private LlhLocatable llh;
    private NedLocatable ned;

    @Override
    public float getLat() {
        return llh.getLat();
    }

    @Override
    public float getLng() {
        return llh.getLng();
    }

    @Override
    public float getAlt() {
        return llh.getAlt();
    }

    @Override
    public float getX() {
        return ned.getX();
    }

    @Override
    public float getY() {
        return ned.getY();
    }

    @Override
    public float getZ() {
        return ned.getZ();
    }

    @Override
    public LlhLocatable getLlh() {
        return llh;
    }

    @Override
    public NedLocatable getNed() {
        return null;
    }
}
