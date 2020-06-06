package it.usuratonkachi.trial.solutions.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfigurator extends EnumStateMachineConfigurerAdapter<State, Event> {

    //@Bean
    public StateMachine<State, Event> stateMachine() throws Exception {
        StateMachineBuilder.Builder<State, Event> builder = StateMachineBuilder.builder();
        builder.configureStates()
                .withStates()
                .initial(State.STATE1)
                .states(EnumSet.allOf(State.class));
        builder.configureTransitions()
                .withExternal()
                .source(State.STATE1).target(State.STATE2)
                .event(Event.EVENT1)
                .and()
                .withExternal()
                .source(State.STATE2).target(State.STATE1)
                .event(Event.EVENT2);
        return builder.build();
    }

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states)
            throws Exception
    {
        states
                .withStates()
                .initial(State.STATE1)
                .states(EnumSet.allOf(State.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions)
            throws Exception
    {
        transitions
                .withExternal()
                .source(State.STATE1).source(State.STATE2)
                .event(Event.EVENT1)
                .and()
                .withExternal()
                .source(State.STATE2).target(State.STATE1)
                .event(Event.EVENT2);
    }

}
