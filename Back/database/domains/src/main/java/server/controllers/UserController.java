package server.database.domains.src.main.java.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.database.domains.src.main.java.server.database.domains.User;
import server.database.domains.src.main.java.server.database.repositories.UserRepository;
import server.database.domains.src.main.java.server.dto.UserDTO;
import server.database.domains.src.main.java.server.validators.Hasher;

@RestController
@CrossOrigin
@PropertySource("classpath:server/controllers/application.properties")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @CrossOrigin
    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){
        User user = userRepository.getUserByEmail(userDTO.getEmail());
        if (user == null){return false;}
        String hash = Hasher.getHash(userDTO.getPassword());
        return user.getPassword().equals(hash);
    }


    @CrossOrigin
    @PostMapping("/register")
    public boolean register(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(Hasher.getHash(userDTO.getPassword()));
        User dbUser = userRepository.getUserByEmail(userDTO.getEmail());
        if(dbUser != null){
            return false;
        }
        userRepository.save(user);
        return true;
    }
}
