package com.generation.SpringSecurityJWT.controller;
import com.generation.SpringSecurityJWT.model.User;
import com.generation.SpringSecurityJWT.repository.UserRepository;
import com.generation.SpringSecurityJWT.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Test
    @DisplayName("Pruebas UserController save")
    void saveTest(){
        //Verificar que el user service sea llamado el método save
        /*Agregar dependencias*/
        BCryptPasswordEncoder bCryptPasswordEncoder=mock(BCryptPasswordEncoder.class);
        UserService userService=mock(UserService.class);
        UserController userController=new UserController(userService,bCryptPasswordEncoder);
        //Objeto para instanciar
        User user=new User();

        /*Crear un usuario*/
        user.setName("El Serch");
        user.setSurname("Borjon");
        user.setUsername("elserch@proton.com");
        user.setAddress("México");
        user.setPassword("Kh3te1mporta");

        /*Intervenir la prueba*/
        when(userService.save(any(User.class))).thenReturn(user);
        User result=userService.save(user);
        verify(userService, times(1)).save(any(User.class));

        assertEquals(user.getName(),result.getName());
        assertEquals(user.getSurname(),result.getSurname());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(),result.getPassword());
    }


    @Test@DisplayName("Prueba para el metodo getUser")
    void getUserTest(){
        /*Verificar que se ha llamado el servicio
           Verificar que el servicio devuelve un objeto de tipo User*/
        BCryptPasswordEncoder bCryptPasswordEncoder=mock(BCryptPasswordEncoder.class);
        UserService userService=mock(UserService.class);
        UserController userController=new UserController(userService,bCryptPasswordEncoder);
        User user2=new User();

        user2.setId(2L);
        user2.setName("El Serch2");
        user2.setSurname("Borjon2");
        user2.setUsername("elserch2@proton.com");
        user2.setAddress("México");
        user2.setPassword("Kh3te1mportax2");

        /*es necesario guardar al usuario*/
        when(userService.getUser(2L)).thenReturn(user2);
        User result2=userService.getUser(2L);
        verify(userService, times(1)).save(any(User.class));

        //assertEquals(user2.getId(), result2.getId());
        assertEquals(user2.getName(),result2.getName());
        assertEquals(user2.getSurname(),result2.getSurname());
        assertEquals(user2.getUsername(), result2.getUsername());
        assertEquals(user2.getPassword(),result2.getPassword());
    }

}