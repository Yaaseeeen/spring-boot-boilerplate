package com.example.springboot.controller;


import com.example.springboot.model.dto.UserDto;
import com.example.springboot.security.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    //    private final UserService12 userService;
    private final UserService userService;

//    @PostMapping()
//    @ApiOperation(value = "Метод для добавления пользователя")
//    public ResponseEntity<UserDto> add(@Valid @RequestBody UserDto userDto) {
//        return ResponseEntity.ok().body(userService.create(userDto));
//    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Метод для получения пользователя")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @GetMapping()
//    todo
    @ApiOperation(value = "Метод для получения пользователя")
    public ResponseEntity<List<UserDto>> getALLByPage() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Метод для изменения пользователя")
    public ResponseEntity<UserDto> edit(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Метод для удаления пользователя")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {

        userService.delete(id);
        return ResponseEntity.ok().body("Deleted!");
    }
}
