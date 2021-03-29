package pl.edu.wszib.ticketbus.model;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void UserCloneTest(){

        User user = new User(1, "admin", "admin", User.Role.ADMIN);

        User clone = user.clone();

        Assert.assertEquals(user.getId(), clone.getId());
        Assert.assertEquals(user.getLogin(), clone.getLogin());
        Assert.assertEquals(user.getPass(), clone.getPass());
        Assert.assertEquals(user.getRole(), clone.getRole());

        Assert.assertNotSame(user, clone);
    }
}
