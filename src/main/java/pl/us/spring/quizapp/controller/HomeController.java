package pl.us.spring.quizapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String home(
            @RequestParam(defaultValue = "noname", required = false) String name,
            @RequestParam(defaultValue = "0", required = false) int age
    ){
        return "Hello " + name + ", your age is " + age + ", welcome to QuizApplication";
    }


    @PostMapping("/")
    public String registerUser(@RequestParam String name){

    }



}
