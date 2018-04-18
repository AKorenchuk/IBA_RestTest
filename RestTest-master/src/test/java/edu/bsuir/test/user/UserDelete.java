package edu.bsuir.test.user;

import edu.bsuir.pojo.User;
import edu.bsuir.test.BasicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDelete extends BasicTest {

    public List<User> users = new ArrayList<User>();

    @Before
    public void setUp() {
        users.add(new User().setId(1).setName("Sam").setAge(30).setSalary(70000));
        users.add(new User().setId(2).setName("Tom").setAge(40).setSalary(50000));
        users.add(new User().setId(3).setName("Jerome").setAge(45).setSalary(30000));
        users.add(new User().setId(4).setName("Silvia").setAge(50).setSalary(40000));
    }

    @Test
    public void deleteAllUsers() {
        assertThat(delete("user/")).isEqualTo(204);
        assertThat(getStatusCodeOfGETRequestToAllResources("user/")).isEqualTo(204);
    }

    @Test
    public void deleteByCorerctID() {
        assertThat(delete("user/3")).isEqualTo(204);
        assertThat(getResource("user/3", User.class)).withThreadDumpOnError();
    }

    @Test
    public void deleteByIncorerctID() {
        assertThat(delete("user/45")).withThreadDumpOnError();
    }

    @Test
    public void deleteByName() {
        assertThat(delete("user/Sam")).withThreadDumpOnError();
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