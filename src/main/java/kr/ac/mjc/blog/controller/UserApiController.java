package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.dto.UserRequest;
import kr.ac.mjc.blog.dto.UserResponse;
import kr.ac.mjc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/join")
    public ResponseEntity<UserResponse> join(@RequestBody UserRequest request){
        UserResponse response=userService.join(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request, HttpSession session){
        UserResponse response=userService.login(request);
        if(response.isSuccess()){
            session.setAttribute("loginUserId",response.getUser().getId());
        }
        return ResponseEntity.ok(response);
    }

}
