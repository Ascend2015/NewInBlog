package xiaoyin.tj212.cn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xiaoyin.tj212.cn.entity.User;

/**
 * JpaRepository 继承了PagingAndSortingRepository，拥有分页和排序的接口
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据用户姓名查询分页用户列表
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);
}
