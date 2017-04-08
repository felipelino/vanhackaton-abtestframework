package com.abtesttool.core;

/**
 * Created by lino on 4/8/17.
 */
public interface AlternativeFactory {
    Object getAlternativeImplementation(String experimentId);

    Alternative getAlternativeByExperiment(String experimentId);
}
