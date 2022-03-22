package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.RoleDao;
import model.Permission;
import model.Role;
import model.User;
import service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession httpSession) {
		User loginUser = userService.login(username, password);
		httpSession.setAttribute("loginUser", loginUser);
		boolean isAdmin = false;
		Set<String> userPermissionSet = new HashSet<String>();
		Set<Permission> permissions;
		Set<Role> roles = loginUser.getRoles();
		for(Role role:roles ) {
			if (role.getRoleName().equals("admin")) {
				isAdmin = true;
				break;
			}
			permissions = role.getPermissions();
			for(Permission permission:permissions) {
				userPermissionSet.add(permission.getResource());
			}
		}
		httpSession.setAttribute("isAdmin", isAdmin);
		if (!isAdmin) {
			httpSession.setAttribute("userPermissionSet", userPermissionSet);
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/main")
	public String main() {
		return "main";
	}
}
