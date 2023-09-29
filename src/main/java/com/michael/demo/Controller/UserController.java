package com.michael.demo.Controller;
import com.michael.demo.dto.UserDto;
import com.michael.demo.model.User;
import com.michael.demo.service.UserService;
import com.michael.demo.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtToken jwtToken;

    @GetMapping()
    public List<User> getAllUsers (){
        return userService.getAllUsers();
    }
    @PostMapping()
    public String createUser (@RequestBody UserDto user){
         userService.registerUser(user);
         return "user registered";
    }

//    @GetMapping("/{id}")
//    public Optional<User> getUserById(@PathVariable Long id){
//        return userService.getUserById(id);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized. Token is required");
        }

        String realToken = token.substring(7);
        String tokenCheckResult = jwtToken.validateToken(realToken);

        if (tokenCheckResult.equalsIgnoreCase("valid")) {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized due to: " + tokenCheckResult);
        }
    }


    @PatchMapping("/{userId}/address/{addressId}")
    public ResponseEntity<String> updateUserAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId) {
        userService.updateUserAddress(userId,addressId);
        return new ResponseEntity<>("address updated successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
    }

}
