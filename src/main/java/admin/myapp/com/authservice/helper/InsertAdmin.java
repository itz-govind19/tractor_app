//package admin.myapp.com.authservice.helper;
//
//import admin.myapp.com.authservice.controller.authController.AuthController;
//import admin.myapp.com.authservice.dto.authDTOS.RegisterRequest;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashSet;
//
//@Configuration
//public class InsertAdmin {
//
//    @Autowired
//    private AuthController controller;
//
//    @PostConstruct
//    private void insertUser(){
//        RegisterRequest req = new RegisterRequest();
//        req.setUsername("admin");
//        req.setPassword("admin1234");
//        req.setEmail("admin@admin.com");
//        req.setName("admin");
//        req.setMobile("9865998240");
//        HashSet<String> objects = new HashSet<>();
//        objects.add("ADMIN");
//        objects.add("USER");
//        req.setRoles(new HashSet<>());
//        controller.register(req);
//    }
//
//}
