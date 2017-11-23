package com.product.rest.services;

import com.product.rest.pojos.User;
import com.product.rest.repos.UserMongoRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserMongoRepository userMongoRepository;

    /**
     * Create users
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        User user1 = new User("Alice", 23);
        User user2 = new User("Bob", 38);

        Assert.assertNull(user1.getId());
        Assert.assertNull(user2.getId());

        this.userMongoRepository.save(user1);
        this.userMongoRepository.save(user2);

        Assert.assertNotNull(user1.getId());
        Assert.assertNotNull(user2.getId());
    }

    /**
     * Test fetch data
     */
    @Test
    public void testFetchData(){
        User userA = userMongoRepository.findByName("Bob");
        Assert.assertNotNull(userA);
        Assert.assertEquals(38, userA.getAge());

        Iterator<User> users = userMongoRepository.findAll().iterator();
        int count = 0;
        while(users.hasNext()){
            users.next();
            count++;
        }

        Assert.assertEquals(count, 2);
    }

    /**
     * Test data update
     */
    @Test
    public void testDataUpdate(){
        User userB = userMongoRepository.findByName("Bob");
        userB.setAge(40);
        userMongoRepository.save(userB);
        User userC = userMongoRepository.findByName("Bob");
        Assert.assertNotNull(userC);
        Assert.assertEquals(40, userC.getAge());
    }

    @After
    public void tearDown() throws Exception {
        this.userMongoRepository.deleteAll();
    }
}
