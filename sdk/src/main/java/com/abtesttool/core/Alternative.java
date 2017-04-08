package com.abtesttool.core;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by lino on 4/8/17.
 */
public class Alternative {

    private String alternativeId;
    private float probability;
    private Object implementation;

    private float minValue;
    private float maxValue;

    public Alternative(String alternativeId, float probability, Object implementation) {
        this.alternativeId = alternativeId;
        this.probability = probability;
        this.implementation = implementation;
    }

    public String getAlternativeId() {
        return alternativeId;
    }

    public float getProbability() {
        return probability;
    }

    public Object getImplementation() {
        return implementation;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return "Alternative{" +
                "alternativeId='" + alternativeId + '\'' +
                ", probability=" + probability +
                ", implementation=" + implementation +
                '}';
    }


}
