package com.julianmartinez.avoris;

import com.julianmartinez.avoris.model.User;
import com.julianmartinez.avoris.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvorisApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void testUserCrud(){
		long addedTestUserId = userRepository.addUser("john doe");
		Assert.assertEquals(1, addedTestUserId);

		userRepository.addUser("jane doe");
		List<User> getTestUserList = userRepository.getUserList();
		Assert.assertEquals(2, getTestUserList.size());

		User getTestUser = userRepository.getUserById(2);
		Assert.assertEquals("jane doe", getTestUser.getName());

		userRepository.modifyUserById(2L, "foo");
		Assert.assertEquals("foo", userRepository.getUserById(2).getName());

		userRepository.removeUserById(2);
		Assert.assertNull(userRepository.getUserById(2));
	}



}
