package com.navercorp.pinpoint.bootstrap.instrument;

/**
 * @author emeroad
 */
public interface Scope  {
    int push();

    int depth();

    int pop() ;

    String getName();

}
