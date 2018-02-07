package com.iyzico.service;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibss.util.Util;
import com.iyzico.domain.User;
import com.iyzico.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserServiceTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void create() {
    	User userInfo = new User();
    	userInfo.setUsername("iyzico1");
    	userInfo.setPassword(Util.encryptPassword(userInfo.getUsername(), userInfo.getUsername()));
    	userInfo.setName("Umit");
    	userInfo.setSurname("Mert");
    	userInfo.setAddress("İstanbul");
    	
    	testEntityManager.persist(userInfo);

        User user = this.userRepository.findByUsername("iyzico1");
        assertThat(user.getSurname(), equalTo("Mert"));
        assertThat(user.getAddress(), equalTo("İstanbul"));
    }
}
