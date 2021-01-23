package jpastudy.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void insert() {
        User user = new User();
        user.setUsername("부트");
        Long userId = userRepository.save(user);
        User findUser = userRepository.find(userId);
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
    }
}