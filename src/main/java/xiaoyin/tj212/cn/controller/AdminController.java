package xiaoyin.tj212.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xiaoyin.tj212.cn.vo.Menu;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {


    /**
     * 获取后台管理主页面
     *
     * @return
     */
    @GetMapping
    public ModelAndView listUsers(Model model) {
        List<Menu> menuList=new ArrayList<>();
        menuList.add(new Menu("用户管理","/users"));
        menuList.add(new Menu("角色管理","/roles"));
        menuList.add(new Menu("博客管理","/blogs"));
        menuList.add(new Menu("评论管理","/comments"));
        model.addAttribute("menuList",menuList);
        return new ModelAndView("admins/index", "model", model);
    }
}