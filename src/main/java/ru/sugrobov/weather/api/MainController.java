package ru.sugrobov.weather.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@ApiIgnore
@Controller
public class MainController {
    @RequestMapping(value = {"/"})
    public String index() {return "index.html";}
}
