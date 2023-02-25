package spring.mvc.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/mapping/users")
public class MappingController {

    @RequestMapping("/hello-world")
    public String helloWorld() {
        log.info("helloWorld");
        return "Hello World!!";
    }

    @GetMapping
    public String getUsers() {
        log.info("getUsers");
        return "getUsers";
    }

    @PostMapping
    public String postUsers() {
        log.info("postUsers");
        return "postUsers";

    }

    @PostMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "USER ID = " + userId;
    }
}
