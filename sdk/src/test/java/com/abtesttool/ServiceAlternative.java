package com.abtesttool;

import com.abtesttool.annotations.AlternativeMethod;

/**
 * Created by lino on 4/8/17.
 */
public interface ServiceAlternative {

    @AlternativeMethod
    String doSomething(int value);
}
