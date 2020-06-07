package it.usuratonkachi.trial.solutions.statemachine.medium;

import it.usuratonkachi.trial.solutions.GenericRunner;
import it.usuratonkachi.trial.solutions.statemachine.medium.config.BookEvents;
import it.usuratonkachi.trial.solutions.statemachine.medium.config.BookStates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StateMachineApplication implements GenericRunner {

    private final StateMachine<BookStates, BookEvents> stateMachine;

    @Override
    public void run(String... args) {
        /*stateMachine.start();
        stateMachine.sendEvent(BookEvents.RETURN);
        stateMachine.sendEvent(BookEvents.BORROW);
        stateMachine.stop();*/
        boolean returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
        log.info("Return accepted " + returnAccepted);
        boolean borrowAccepted = stateMachine.sendEvent(BookEvents.BORROW);
        log.info("Borrow accepted " + borrowAccepted);
        returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
        log.info("Again, return accepted " + returnAccepted);
    }

}
