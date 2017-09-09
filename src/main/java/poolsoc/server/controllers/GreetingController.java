package poolsoc.server.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poolsoc.server.viewmodels.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public GreetingVM greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new GreetingVM(counter.incrementAndGet(),
                            String.format(template, name));
    }
}