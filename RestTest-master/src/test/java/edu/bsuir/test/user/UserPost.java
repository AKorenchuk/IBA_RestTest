package edu.bsuir.test.user;

import edu.bsuir.pojo.User;
import edu.bsuir.test.BasicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPost extends BasicTest {

    private User postUser;
    public String location="";

    @Before
    public void setUp() {
        postUser = createDummyUser();
    }

    @Test
    public void shouldCreateUser() {
        location = createResource("user/", postUser);
        User getUser = getResource(location, User.class);
        assertThat(getUser).isEqualToIgnoringGivenFields(postUser, "id");
    }

    @Test( expected = AssertionError.class)
    public void shouldCreateIdenticalUser() {
        location = createResource("user/", postUser);
        String secondUserLocation = createResource("user/", postUser);
    }

    @Test
    public void shouldCreateEmptyUser() {
        User user = new User();
        location = createResource("user/", user);
        assertThat(getResource(location,User.class)).isEqualToIgnoringGivenFields(user, "id");
    }

    @Test (expected = AssertionError.class)
    public void shouldCreateUserOnlyWithName(){
        User user = new User().setName("Anna");
        location = createResource("user/", user);
        assertThat(getResource(location,User.class)).isEqualToIgnoringGivenFields(user, "id");

    }

    @After
    public void shutDown() {
        delete(location);
    }

    private User createDummyUser() {
        return new User().setName("John")
                .setAge(10)
                .setSalary(3000);
    }

}
