package xiaoyin.tj212.cn.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2,max = 20)
    @Column(nullable = false,length = 20)//映射为字段，值不能为空
    private String name;

    @NotEmpty(message = "邮箱不能为空")
    @Size(max = 50)
    @Email(message = "邮箱格式不正确")
    @Column(nullable = false,length = 50,unique = true)//映射为字段，值不能为空
    private String email;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 3,max = 30)
    @Column(nullable = false,length = 30,unique = true)//映射为字段，值不能为空
    private String username;//用户登录时所用的用户名

    @NotEmpty(message = "密码不能为空")
    @Size(min = 3,max = 100,message = "密码长度为3-100")
    @Column(nullable = false,length = 100)//映射为字段，值不能为空
    private String password;

    @Column(length = 300)
    private String avatar;//头像图片的地址

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "my_authority_id",referencedColumnName = "id"))
    private List<MyAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        //需要将List<Authority>转化为List<SimpleGrantedAuthority>，否则前端拿不到列表名称
        List<SimpleGrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (GrantedAuthority authority:authorities
             ) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return grantedAuthorities;
    }

    public void setAuthorities(List<MyAuthority> authorities) {
        this.authorities = authorities;
    }

    public User( String name, String email, String username,String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password=password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEncodePassword(String password){
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodePassword=encoder.encode(password);
        this.password=encodePassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    protected User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
