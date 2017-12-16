package xiaoyin.tj212.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xiaoyin.tj212.cn.entity.MyAuthority;
import xiaoyin.tj212.cn.entity.User;
import xiaoyin.tj212.cn.service.AuthorityService;
import xiaoyin.tj212.cn.service.UserService;
import xiaoyin.tj212.cn.utils.ConstraintViolationExceptionHandler;
import xiaoyin.tj212.cn.vo.ResponseObject;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 查询所用用户
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value = "async",required = false)boolean async,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "0")int pageIndex,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "0")int pageSize,
                             @RequestParam(value = "name",required = false,defaultValue = "")String name,
                             Model model) {
        Pageable pageable=new PageRequest(pageIndex,pageSize);
        Page<User> userPage=userService.listUsersByNameLike(name,pageable);
        List<User> users=userPage.getContent();

        model.addAttribute("page",userPage);
        model.addAttribute("userList",users);
        return new ModelAndView("users/list","userModel",model);
    }

    @GetMapping("/add")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new User(null,null,null,null));
        return new ModelAndView("users/add","userModel",model);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(User user,Long authorityId){
        List<MyAuthority> myAuthorities=new ArrayList<>();
        myAuthorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(myAuthorities);

        //判断密码是否做了变更
        User originalUser=userService.getUserById(user.getId());
        String rawPassword=originalUser.getPassword();
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPwd=encoder.encode(user.getPassword());
        boolean isMatched=encoder.matches(rawPassword,encodedPwd);
        if (!isMatched){
            user.setEncodePassword(user.getPassword());
        }else {
            user.setPassword(user.getPassword());
        }

        try {
            userService.saveUser(user);
        }catch (ConstraintViolationException e){
            return ResponseEntity.ok().body(new ResponseObject(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new ResponseObject(true,"处理成功",user));
    }

    @DeleteMapping(value = "edit/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") Long id,Model model){
        try{
            userService.removeUser(id);
        }catch (ConstraintViolationException e){
            return ResponseEntity.ok().body(new ResponseObject(false,e.getMessage()));
        }
        return ResponseEntity.ok().body(new ResponseObject(true,"删除成功"));
    }

    @GetMapping(value = "edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id")Long id,Model model){
        User user=userService.getUserById(id);
        model.addAttribute("user",user);
        return new ModelAndView("users/edit","userModel",model);
    }
}