package it.usuratonkachi.staticinjection.controller;


import it.usuratonkachi.staticinjection.utils.StaticUtils;
import it.usuratonkachi.staticinjection.wrapper.Wrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class SimpleRestController {

    private ConcurrentHashMap<String, Map<String, AtomicInteger>> loggedRequestValues = new ConcurrentHashMap<>();

    @GetMapping("/")
    public synchronized Wrapper getValue(){
        Wrapper wrapper = StaticUtils.requestValue();

        Optional.ofNullable(loggedRequestValues.putIfAbsent(wrapper.getSessionId(), new ConcurrentHashMap<>()))
                .map(map -> map.putIfAbsent(wrapper.getRequestId(), new AtomicInteger(0)))
                .ifPresent(AtomicInteger::incrementAndGet);
        return wrapper;
    }

    @GetMapping("/getLogRequest")
    public ConcurrentHashMap<String, Map<String, AtomicInteger>> getLogRequest() {
        return loggedRequestValues;
    }

}
