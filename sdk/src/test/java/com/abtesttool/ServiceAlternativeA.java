package com.abtesttool;

import com.abtesttool.annotations.AlternativeClass;
import org.springframework.stereotype.Service;

@Service(value = "alternativeA")
@AlternativeClass(experimentId = "expOne", alternativeId = "A")
public class ServiceAlternativeA implements ServiceAlternative {

    public String doSomething(int value) {
        return "Value:["+value+"]";
    }
}
