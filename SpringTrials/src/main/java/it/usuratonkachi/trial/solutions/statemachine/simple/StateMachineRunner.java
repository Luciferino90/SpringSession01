package it.usuratonkachi.trial.solutions.statemachine.simple;

import it.usuratonkachi.trial.solutions.GenericRunner;
import it.usuratonkachi.trial.solutions.statemachine.simple.config.Event;
import it.usuratonkachi.trial.solutions.statemachine.simple.config.State;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class StateMachineRunner implements GenericRunner {

    private final StateMachine<State, Event> machine;

    @SneakyThrows
    @Override
    public void run(String... args) {
        machine.start();
        machine.sendEvent(Event.EVENT1);
        machine.sendEvent(Event.EVENT2);
    }

}
