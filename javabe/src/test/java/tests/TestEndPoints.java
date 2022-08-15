package tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parent.repo.UserRepo;

@RestController
@RequestMapping("/baseurl/")
public class TestEndPoints {
    static {
        System.setProperty("conf.file", "src/test/resources/application.properties");
    }

    @Autowired
    private UserRepo repository;

    @GetMapping("/get-test")
    public ResponseEntity<String> onGet() {
        System.out.println("Anshul" + repository);
        return ResponseEntity.ok("Hi");
    }
}
