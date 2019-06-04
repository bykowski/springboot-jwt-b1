package pl.bykowski.springbootjwtb1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Api {

    @GetMapping("/api/hello")
    public String get() {
        return "hello!";
    }

    @PostMapping("logIn")
    public String login(@RequestBody User user) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC512(user.getPassword());
            token = JWT.create()
                    .withSubject(user.getLogin())
                    .withClaim("roles", "user")
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
        }
        return token;
    }
}
