package com.tw;

import org.springframework.boot.SpringApplication;

/**
 * Created by twer on 7/15/14.
 */
public class Application {

    public static void main(String[] args)
    {
//        ApplicationContext appContext =
//                new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//
//        UserBo userBo = (UserBo)appContext.getBean("userBo");
//
//        User user = new User();
//        user.setUserName("LiHao");
//        userBo.save(user);

        SpringApplication.run(Application.class, args);
    }
}
