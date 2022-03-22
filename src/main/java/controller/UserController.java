package controller;

import java.awt.SystemColor;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Pager;
import model.SystemContext;
import model.User;
import service.UserService;
import web.AuthClass;

@AuthClass
@Controller
public class UserController {

	private UserService userService;
	
	@RequestMapping(value = {"", "/", "/index"})
	public String index() {
		System.out.print("222222222222222222222222");
		return "index";
	}
	
	@RequestMapping(value= "/getAllUsers", method=RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}
	
	@RequestMapping(value="/getAllPagerUsers", method=RequestMethod.GET)
	public String getAllPagerUsers(Model model) {
		SystemContext.setPageOffset(1);//修改分页参数
		SystemContext.setPageSize(1);
		Pager<User> pager = userService.getAllPagerUsers();
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		model.addAttribute("pager", pager);
		return "index";
	}
}
