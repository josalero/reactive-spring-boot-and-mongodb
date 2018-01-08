package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.josalero.webreactive.WebReactiveSpringBootMongoDbApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=WebReactiveSpringBootMongoDbApplication.class)
@ActiveProfiles("test")
public  abstract class WebReactiveSpringBootMongoDbApplicationTests {


}

