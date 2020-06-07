package it.usuratonkachi.trial.solutions.statemachine.medium.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfigurer extends EnumStateMachineConfigurerAdapter<BookStates, BookEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<BookStates, BookEvents> states) throws Exception {
        states
                .withStates()
                .initial(BookStates.AVAILABLE)
                .state(BookStates.AVAILABLE, entryAction(), exitAction())
                .state(BookStates.BORROWED, entryAction(), exitAction())
                .state(BookStates.IN_REPAIR, entryAction(), exitAction());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BookStates, BookEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(BookStates.AVAILABLE)
                .target(BookStates.BORROWED)
                .event(BookEvents.BORROW)
                .and()
                .withExternal()
                .source(BookStates.BORROWED)
                .target(BookStates.AVAILABLE)
                .event(BookEvents.RETURN)
                .and()
                .withExternal()
                .source(BookStates.AVAILABLE)
                .target(BookStates.IN_REPAIR)
                .event(BookEvents.START_REPAIR)
                .and()
                .withExternal()
                .source(BookStates.IN_REPAIR)
                .target(BookStates.AVAILABLE)
                .event(BookEvents.END_REPAIR);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<BookStates, BookEvents> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true);
    }

    @Bean
    public Action<BookStates, BookEvents> entryAction(){
        return ctx -> log.info("Entry action {} to get from {} to {}",
                ctx.getEvent(),
                ctx.getSource(),
                ctx.getTarget()
        );
    }

    @Bean
    public Action<BookStates, BookEvents> exitAction(){
        return ctx -> log.info("Exit action {} to get from {} to {}",
                ctx.getEvent(),
                ctx.getSource(),
                ctx.getTarget()
        );
    }

}
