package plugin.prep.some.controllers;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import plugin.prep.errors.*;
import plugin.prep.some.api.*;
import plugin.prep.some.dto.*;

@RestController
public class HelloController implements HelloApi {

    @Override
    public HelloDto hello(String name) {
        var response = new HelloDto()
            .setMessage("Hello " + name);

        return response;
    }

    @Override
    public HelloDto bad(String name) {
        throw switch (name) {
            case "alice" -> new RuntimeException("Here comes Alice!!!");
            case "bob" -> new PrepException("Bob strike back!!!", METHOD_NOT_ALLOWED, "4051");
            case "charlie" -> new IllegalArgumentException("Who is Charlie???");
            default -> new IllegalStateException();
        };
    }

}
