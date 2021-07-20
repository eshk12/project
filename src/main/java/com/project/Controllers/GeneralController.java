package com.project.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;



@CrossOrigin
@RestController
public class GeneralController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralController.class);



    @PostConstruct
    public void init () {
    }

    @RequestMapping("/test")
    public String test () {
        return "success";
    }




}


