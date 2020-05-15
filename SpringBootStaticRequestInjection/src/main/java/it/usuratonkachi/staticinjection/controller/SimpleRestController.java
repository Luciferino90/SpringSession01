package it.usuratonkachi.staticinjection.controller;


import it.usuratonkachi.staticinjection.utils.StaticUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class SimpleRestController {

    private ConcurrentHashMap<String, AtomicInteger> loggedRequestValues = new ConcurrentHashMap<>();

    @GetMapping("/")
    public String getValue(){
        String keyRequestScope = StaticUtils.requestValue();
        if (loggedRequestValues.containsKey(keyRequestScope))
            loggedRequestValues.computeIfPresent(keyRequestScope, (key, value) -> new AtomicInteger(value.incrementAndGet()));
        else
            loggedRequestValues.putIfAbsent(keyRequestScope, new AtomicInteger(1));
        return keyRequestScope;
    }

    @GetMapping("/getLogRequest")
    public Map<String, AtomicInteger> getLogRequest() {
        return loggedRequestValues;
    }

}
