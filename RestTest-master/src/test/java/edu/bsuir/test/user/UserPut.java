package edu.bsuir.test.user;

import edu.bsuir.pojo.User;
import edu.bsuir.test.BasicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPut extends BasicTest {

    public List<User> users = new ArrayList<User>();

    @Before
    public void setUp() {
        users.add(new User().setId(1).setName("Sam").setAge(30).setSalary(70000));
        users.add(new User().setId(2).setName("Tom").setAge(40).setSalary(50000));
        users.add(new User().setId(3).setName("Jerome").setAge(45).setSalary(30000));
        users.add(new User().setId(4).setName("Silvia").setAge(50).setSalary(40000));
    }

    @Test
    public void putUser() {
        User user = users.get(1).setName("Anna");
        assertThat(put("user/2",user)).isEqualTo(200);
        assertThat(getResource("user/2",User.class)).isEqualToIgnoringGivenFields(user);
    }

    @Test
    public void putByIncorerctID() {
        User user = users.get(0).setName("Anna");
        assertThat(put("user/45",user)).withThreadDumpOnError();
    }

    @Test
    public void putByName() {
        User user = users.get(1).setName("Silvia");
        assertThat(put("user/1",user)).withThreadDumpOnError();
    }

    @Test
    public void putByNameAtRest() {
        User user = users.get(1).setName("Anna");
        assertThat(put("user/Sam",user)).withThreadDumpOnError();
    }

    @After
    public void shutDown() {
        users.clear();
        delete("user/");
        createResource("user/",new User().setId(1).setName("Sam").setAge(30).setSalary(70000));
        createResource("user/",new User().setId(2).setName("Tom").setAge(40).setSalary(50000));
        createResource("user/",new User().setId(3).setName("Jerome").setAge(45).setSalary(30000));
        createResource("user/",new User().setId(4).setName("Silvia").setAge(50).setSalary(40000));
    }

}
