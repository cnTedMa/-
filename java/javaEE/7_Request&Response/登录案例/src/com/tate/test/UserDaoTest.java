package com.tate.test;

import com.tate.dao.UserDao;
import com.tate.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testLogin() {
        User loginUser = new User();
        loginUser.setUsername("tate");
        loginUser.setPassword("123456");

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);
    }


}
