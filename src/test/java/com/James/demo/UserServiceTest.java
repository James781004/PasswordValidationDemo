package com.James.demo;

import com.James.demo.entity.User;
import com.James.demo.repository.UserRepository;
import com.James.demo.service.UserService;
import com.James.demo.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void testInsert() {
        // It seems that javax validations would be ignored in junit test
        // therefore, we test only the service function here
        User user = new User();
        // user.setId(1L);
        user.setName("abc123");
        user.setPassword("1313abc");

        // Mocking userService.insertByUser(id)
        when(userRepository.save(any(User.class))).thenReturn(user);

        // [Assert] comparing the result to the expecting data
        assertEquals(userService.insertByUser(user), user);
//        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindAll() {
        // [Arrange] expecting data
        List<User> expectedList = new ArrayList();
        User user = new User();
        user.setName("name123");
        user.setPassword("password123");
        User user2 = new User();
        user2.setName("name124");
        user2.setPassword("password124");
        expectedList.add(user);
        expectedList.add(user2);

        // Mocking userService.findAll(id)
        Mockito.when(userRepository.findAll()).thenReturn(expectedList);

        // [Act] userService.findAll();
        Iterable<User> actualTodoList = userService.findAll();

        // [Assert] comparing the result to the expecting data
        assertEquals(expectedList, actualTodoList);
    }

    @Test
    public void testFindById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("abc123");
        user.setPassword("1313abc");
        Optional<User> optionalEntityType = Optional.of(user);

        // Mocking userService.findById(id)
        Mockito.when(userRepository.findById(1L)).thenReturn(optionalEntityType);

        User returned = userService.findById(user.getId());

        verify(userRepository, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userRepository);

        assertEquals(user, returned);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("abc123");
        user.setPassword("abc123");

        // Mocking userService.insertByUser(User)
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // [Act] userService.insertByUser(User)
        User updateResult = userService.insertByUser(user);

        //  [Assert] comparing the result to the expecting data
        assertEquals(user, updateResult);
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("abc123");
        user.setPassword("1313abc");
        Optional<User> optionalEntityType = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(optionalEntityType);

        // when
        userService.delete(user.getId());

        // then
        Mockito.verify(userRepository, times(1)).delete(user);
    }
}
