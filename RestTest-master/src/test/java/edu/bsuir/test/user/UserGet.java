package edu.bsuir.test.user;

import edu.bsuir.pojo.User;
import edu.bsuir.test.BasicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class UserGet extends BasicTest {

    public List<User> users = new ArrayList<User>();

    @Before
    public void setUp() {
        users.add(new User().setId(1).setName("Sam").setAge(30).setSalary(70000));
        users.add(new User().setId(2).setName("Tom").setAge(40).setSalary(50000));
        users.add(new User().setId(3).setName("Jerome").setAge(45).setSalary(30000));
        users.add(new User().setId(4).setName("Silvia").setAge(50).setSalary(40000));
    }

    @Test
    public void getAllUsers() {
        List<User> listOfAllUsers = getListOfAllResources("user/",User.class);
        if(listOfAllUsers.size() == users.size()) {
            for (User user : listOfAllUsers) {
                User currentUserAtList = users.get((int) (user.getId()-1));
                assertThat(currentUserAtList).isEqualToIgnoringGivenFields(user);
            }
        }
        else {
            assertTrue(false);
        }
    }

    @Test
    public void getUserByCorrectID() {
        User currentUserAtList = getResource("user/4",User.class);
        assertThat(currentUserAtList).isEqualToIgnoringGivenFields(users.get(3));
    }

    @Test(expected = AssertionError.class)
    public void getUserByUnrealID() {
        User currentUserAtList = getResource("user/20",User.class);
    }

    @Test (expected = AssertionError.class)
    public void getUserByIncorrectID() {
        User currentUserAtList = getResource("user/Sam",User.class);
    }

    @After
    public void shutDown() {
       users.clear();
    }

}
