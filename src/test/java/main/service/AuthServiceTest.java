package main.service;

import com.example.application.Application;
import com.example.application.domain.Role;
import com.example.application.repos.UserRepo;
import com.example.application.service.AuthService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class AuthServiceTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepo userRepo;
    @Test
    public void getRoutesForRoleTest(){
        List<AuthService.AuthorisedRoute> routes =  authService.getRoutesForRole(Role.ADMIN);
        Assert.assertEquals(22, routes.size());
    }
    @Test
    public void authenticate_WrongUsername_AccessDenied(){
        boolean authenticated = authService.authenticate("admin", "123ergretg");
        org.junit.Assert.assertEquals(false, authenticated);
    }
    @Test
    public void authenticate_UserDoNotExist_AccessDenied(){
        boolean authenticated = authService.authenticate("admin228", "123");
        org.junit.Assert.assertEquals(false, authenticated);
    }
}