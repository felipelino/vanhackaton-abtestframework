package com.abtesttool;

import com.abtesttool.annotations.AlternativeClass;
import org.springframework.stereotype.Service;

@Service(value = "alternativeB")
@AlternativeClass(experimentId = "expOne", alternativeId = "B")
public class ServiceAlternativeB implements ServiceAlternative {

    public String doSomething(int value) {
        return "Value:["+(value+1)+"]";
    }
}
