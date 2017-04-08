package com.abtesttool;

import com.abtesttool.core.Alternative;
import com.abtesttool.core.AlternativeFactory;
import com.abtesttool.core.AlternativeFactoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class, loader=AnnotationConfigContextLoader.class)
public class AlternativeFactoryTest {

    @Autowired
    private AlternativeFactory alternativeFactory;

    @Test
    public void getAlternative() throws Exception {
        int countA = 0;
        int countB = 0;
        int totalRepetitions = 10000;
        for(int i = 0; i < 10000 ; i++) {
            Alternative alternative = alternativeFactory.getAlternativeByExperiment("expOne");
            if(alternative.getAlternativeId().equalsIgnoreCase("A")) {
                countA++;
            }
            else {
                countB++;
            }
        }
        float percentFault = (Math.abs(countA - countB) * 100) / totalRepetitions;
        boolean result =  (percentFault<= 5) ; //5% of tolerance for probability error
        Assert.assertTrue(  result );
    }

    @Test
    public void interceptor() {
        ServiceAlternative serviceAlternative = (ServiceAlternative) alternativeFactory.getAlternativeImplementation("expOne");
        String result = serviceAlternative.doSomething(7);
        Assert.assertNotNull(result);
    }

}
