vanhackaton-abtestframework
===================

This project was created fo Vanhackathon 2017. It is a framework for A/B Tests

----------

Usage
-------------

You have a interface that could have more than one implementation. Each Implementation is an alternative.

    public interface ServiceAlternative {
        String doSomething(int value);
    }

Two alternative implementations each one with 50% of probability to be choose:
Alternative A:

    @Service(value = "alternativeA")
    @AlternativeClass(experimentId = "expOne", alternativeId = "A", probability = 0.5f)
    public class ServiceAlternativeA implements ServiceAlternative {
    	@AlternativeMethod(sendOutput = true)
        public String doSomething(@AlternativeInfo(send=true) int value) {
            return "Value:["+value+"]";
        }
    }

Alternative B: 

    @Service(value = "alternativeB")
    @AlternativeClass(experimentId = "expOne", alternativeId = "B", probability = 0.5f)
    public class ServiceAlternativeB implements ServiceAlternative {
    	@AlternativeMethod(sendOutput = true)
        public String doSomething(@AlternativeInfo(send=true) int value) {
            return "Value:["+value+"]";
        }
    }


The service who should use one of the alternatives:

    import com.abtesttool.core.AlternativeFactory;
    ...
    @Service
    public class ServiceBusiness {   	
        @Autowired
       	private AlternativeFactory alternativeFactory ;
        
        public void doTooMuchThings(  ) {
        
       		ServiceAlternative serviceAlternative = (ServiceAlternative) alternativeFactory.getAlternativeImplementation("expOne");
        	String resp = alternative.doSomething(7);
       		...
       	}
    }

----------

Behavior
========
The class **com.abtesttool.core.AlternativeFactory ** has the logic about choose the correct implementation for the test scenario.

The annotation **@AlternativeMethod** is used by **ExperimentInterceptor** who will intercept the call and log all input and output data if the annotations with the flags allow it.

The current implementation only use logback as default implementation for SL4J to log information that can be used later.

In the future optionally you will can send all info to an API.


Pre-requisites
=======
 - Java JDK 8
 - Spring Context
 - Logback


----------


Todo
====

We define an API to receive info about the A/B Test and you can configure the probabilities for each alternative of an Experiment by API. His definition could be seen at [A/B Test API Definition](api/spec/api-spec.md) .