package xiaoyin.tj212.cn.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class MyAuthority implements GrantedAuthority{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
