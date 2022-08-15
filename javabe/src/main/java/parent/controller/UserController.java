package parent.controller;

import parent.datamodel.User;
import parent.exceptions.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parent.repo.UserRepo;
import parent.service.CloakConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepo.getUsers().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {

        if (user.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID");
            // Lowercase the user login before comparing with database
        } else {
            User newUser = userRepo.createUser(user);
            CloakConfig cloakConfiguration =
                    new CloakConfig(user.getuserName(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail());

//            CloakConfig cloakConfiguration =
//                    new CloakConfig("fashan","fasd","dsa","fas@fds.com");

            cloakConfiguration.getKeyCloakConfig();
            return ResponseEntity.created(new URI("/netflix/users/" + newUser.getId()))
                    .body(newUser);
        }
    }

    @DeleteMapping("/users/{name}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("name") String userName) {
        userRepo.deleteByName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}