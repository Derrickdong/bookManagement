package web;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import model.User;


public class AuthInceptor extends HandlerInterceptorAdapter{

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String path = "";
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RequestMapping annotation = method.getAnnotation(RequestMapping.class);
			path = annotation.value()[0];
		}else {
			throw new RuntimeException("输入的url地址不存在！");
		}
		
		List<String> allPermissionPathes = (List<String>) request.getServletContext().getAttribute("userPermissionSet");
		
		List<String> loginUserAllPath = (List<String>) session.getAttribute("loginUserAllPath");
		
		User loginUser = (User) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/login.html");
		}else {
			boolean isAdmin = (boolean) session.getAttribute("isAdmin");
			if(!isAdmin  &&  allPermissionPathes.contains(path)) {
				if(!loginUserAllPath.contains(path)) {
					throw new RuntimeException("你没有访问该页面的权限！");
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
