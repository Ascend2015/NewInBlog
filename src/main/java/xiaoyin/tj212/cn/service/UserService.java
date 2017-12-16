package xiaoyin.tj212.cn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import xiaoyin.tj212.cn.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    void removeUserInBatch(List<User> users);

    User updateUser(User user);

    User registerUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> listUsers();

    /**
     * 根据姓名进行分页模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);



}
