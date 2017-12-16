package xiaoyin.tj212.cn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoyin.tj212.cn.entity.MyAuthority;

public interface AuthorityRepository extends JpaRepository<MyAuthority,Long> {

}
