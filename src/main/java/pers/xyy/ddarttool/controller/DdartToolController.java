package pers.xyy.ddarttool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyy.ddarttool.service.DaartToolService;

@RestController
@RequestMapping("/daart")
public class DdartToolController {

    @Autowired
    private DaartToolService service;

    @GetMapping(value = "examples")
    @CrossOrigin
    public String examples() {
        return service.examples();
    }

    @GetMapping(value = "analyze")
    @CrossOrigin
    public String analyze() {
        return service.analyze();
    }

    @GetMapping(value = "replace/{index}")
    @CrossOrigin
    public String replace(@PathVariable("index") int index) {
        return service.replace(index);
    }

    @GetMapping(value = "apply/{index}")
    @CrossOrigin
    public String apply(@PathVariable("index") int index) {
        return service.apply(index);
    }

    @GetMapping(value = "replaceall")
    @CrossOrigin
    public String replaceAll() {
        return service.replaceAll();
    }

}
