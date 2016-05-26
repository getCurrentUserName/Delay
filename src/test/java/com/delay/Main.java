package com.delay;

import com.delay.config.DataConfig;
import com.delay.domain.entities.user.User;
import com.delay.services.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.logging.Logger;

/**
 * Created by LucidMinds on 25.05.16.
 * package com.delay;
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class Main {

    private Logger logger = Logger.getLogger(Main.class.getName());
    @Autowired
    UserService userService;

    @Test
    public void findByUsername() {
        User user = userService.findByUsername("Ogoyukin");
        logger.info(user.toString());
    }
}
