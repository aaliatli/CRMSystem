package com.example.WebProject2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.WebProject2.Controller.LoginService;
import com.example.WebProject2.entity.Login;
import com.example.WebProject2.repository.LoginRepository;

@SpringBootTest
public class LoginServiceEntegrationTest {
    
    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginRepository loginRepo;

    @Test
    public void testLogin(){
        loginRepo.deleteAll(); 

        Login user = new Login();
        user.setUserMail("cs@cs");
        user.setUserPassword("cs");
        loginRepo.save(user);

        Login result = loginService.getByCredentials("cs@cs", "cs");

        assertNotNull(result);
        assertEquals("cs@cs", result.getUserMail());
    }

}
