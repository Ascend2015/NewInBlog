package xiaoyin.tj212.cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaoyin.tj212.cn.entity.MyAuthority;
import xiaoyin.tj212.cn.repository.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public MyAuthority getAuthorityById(Long id) {
        return authorityRepository.findOne(id);
    }
}
