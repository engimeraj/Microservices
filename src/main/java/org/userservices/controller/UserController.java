package org.userservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.userservices.payload.PostResponse;
import org.userservices.payload.UserDto;
import org.userservices.service.UserService;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    // http://localhost:8080/api/getUser/{id}
    @PostMapping("/saveUser")
    public ResponseEntity<?> createUser(@RequestBody UserDto dto){
        userService.saveUserDetail(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // http://localhost:8080/api/getUser/{id}
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable String id){
        UserDto user = userService.getById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    //http://localhost:8080/api/getAllUser
    @GetMapping("/getAllUser")
    public ResponseEntity<PostResponse> getAll(
            @RequestParam(value = "pageNumber" ,defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "userId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
       PostResponse postResponse = userService.getAll(pageNumber, pageSize, sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id , @RequestBody UserDto userDto){
        userService.updateById(id,userDto);
        return new ResponseEntity<>("User is updated",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
   public ResponseEntity<UserDto> deleteUser(@PathVariable String id){
       UserDto userDto =userService.deleteUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
   }
}
