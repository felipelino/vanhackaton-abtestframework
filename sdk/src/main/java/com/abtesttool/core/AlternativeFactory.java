package com.abtesttool.core;

import com.abtesttool.annotations.AlternativeClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by lino on 4/8/17.
 */
@Service
public class AlternativeFactory implements BeanPostProcessor {

    //Read all annotations to build this map
    //And call API to build this map
    private Map<String, List<Alternative>> mapExperimentAlternatives = new HashMap<String, List<Alternative>>();

    private Random random = new Random();

    private AlternativeFactory() {
    }

    public Alternative getAlternativeByExperiment(String experimentId) {

        List<Alternative> alternatives = mapExperimentAlternatives.get(experimentId);
        Collections.sort(alternatives, new ProbabilityComparator());
        float baseValue = 0;
        //Adjust intervals of each alternative
        for(Alternative alternative : alternatives) {
            alternative.setMinValue(baseValue);
            float nextBaseValue = baseValue + alternative.getProbability();
            alternative.setMaxValue( nextBaseValue );
            baseValue = nextBaseValue;
        }

        Alternative selectedAlternative = null;
        //Random number
        float randomFloat = this.random.nextFloat();
        for(Alternative alternative : alternatives) {
            if(randomFloat <= alternative.getMaxValue()) {
                selectedAlternative = alternative;
                break;
            }
        }
        //If for some reason are bad configuration return the last alternative. With more probability
        if(selectedAlternative == null) {
            selectedAlternative = alternatives.get(alternatives.size()-1);
        }
        return selectedAlternative;
    }

    public Object getAlternativeImplementation(String experimentId) {
        Alternative alternative = getAlternativeByExperiment(experimentId);
        return alternative.getImplementation();
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class clazz = bean.getClass();
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof AlternativeClass) {
                AlternativeClass alternativeClass = (AlternativeClass) annotation;
                String alternativeId = alternativeClass.alternativeId();
                String experimentId = alternativeClass.experimentId();
                Float probability = alternativeClass.probability();

                //TODO Call API optionally

                Alternative alternative = new Alternative(alternativeId, probability, bean);
                List<Alternative> list = this.mapExperimentAlternatives.getOrDefault(experimentId, new ArrayList<Alternative>());
                list.add(alternative);
                this.mapExperimentAlternatives.put(experimentId, list);
                break;
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
