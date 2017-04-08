package com.abtesttool;

import com.abtesttool.annotations.AlternativeClass;
import com.abtesttool.annotations.AlternativeInfo;
import com.abtesttool.annotations.AlternativeMethod;
import org.springframework.stereotype.Service;

@Service(value = "alternativeB")
@AlternativeClass(experimentId = "expOne", alternativeId = "B")
public class ServiceAlternativeB implements ServiceAlternative {

    @AlternativeMethod(sendOutput = true)
    public String doSomething(@AlternativeInfo(send = true) int value) {
        return "Value:["+(value+1)+"]";
    }
}
