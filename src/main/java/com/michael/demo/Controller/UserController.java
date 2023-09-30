package com.michael.demo.Controller;
import com.michael.demo.dto.UserDto;
import com.michael.demo.errorhandling.UnauthorizedException;
import com.michael.demo.model.User;
import com.michael.demo.service.UserService;
import com.michael.demo.util.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return new ResponseEntity<>("user created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, HttpServletRequest request) {
        try {
            String token = jwtToken.extractTokenFromHeader(request.getHeader("Authorization"));
            return userService.getUserById(id, token);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
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
