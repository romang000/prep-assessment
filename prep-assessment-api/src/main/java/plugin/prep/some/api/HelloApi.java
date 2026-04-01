package plugin.prep.some.api;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.some.dto.*;

public interface HelloApi {

    @GetMapping("/hello")
    @Operation(summary = "Получение приветственного сообщения")
    @Parameters(value = {
        @Parameter(
            name = "name",
            description = "Имя",
            required = true,
            schema = @Schema(implementation = String.class)
        )
    })
    HelloDto hello(
        @RequestParam String name
    );

    @GetMapping("/bad")
    @Operation(summary = "Выкидывает исключение в зависимости от имени")
    @Parameters(value = {
        @Parameter(
            name = "name",
            description = "Имя",
            required = true,
            examples = {
                @ExampleObject(
                    name = "Alice throws RuntimeException",
                    value = "alice"
                ),
                @ExampleObject(
                    name = "Bob throws PrfxException",
                    value = "bob"
                ),
                @ExampleObject(
                    name = "Charlie throws IllegalArgumentException",
                    value = "charlie"
                )
            }
        )
    })
    HelloDto bad(
        @RequestParam String name
    );

}
