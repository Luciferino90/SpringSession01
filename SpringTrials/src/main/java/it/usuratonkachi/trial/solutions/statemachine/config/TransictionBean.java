package it.usuratonkachi.trial.solutions.statemachine.config;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class TransictionBean {

    @OnTransition(target = "STATE1")
    void toState1(){ }

    @OnTransition(target = "STATE2")
    void toState2(){ }

}
