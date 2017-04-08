package com.abtesttool;

import com.abtesttool.annotations.AlternativeClass;
import com.abtesttool.annotations.AlternativeInfo;
import com.abtesttool.annotations.AlternativeMethod;
import org.springframework.stereotype.Service;

@Service(value = "alternativeA")
@AlternativeClass(experimentId = "expOne", alternativeId = "A")
public class ServiceAlternativeA implements ServiceAlternative {

    @AlternativeMethod(sendOutput = true)
    public String doSomething(@AlternativeInfo(send = true) int value) {
        return "Value:["+value+"]";
    }
}
