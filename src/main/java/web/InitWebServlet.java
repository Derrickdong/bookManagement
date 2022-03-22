package web;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.ResourceService;


public class InitWebServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	//初始化spring的ioc容器的引用，初始化权限
	@Override
	public void init() throws ServletException {
		ServletContext context =  getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		
		try {
			String packageName = "cn.interview.management.controller";
			String packageNamePath = packageName.replace(".", "/"); 
			String packageNameRealPath = this.getClass().getClassLoader().getResource(packageNamePath).getPath();
			File file = new File(packageNameRealPath);
			String[] classFileNames  = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith(".class")) {
						return true;
					}
					return false;
				}
			});
			
			List<String> pathes = new ArrayList<>();
			
			for(String classFileName:classFileNames) {
				classFileName = classFileName.substring(0,classFileName.indexOf(".class"));
				String classAllpackageName = packageName + "." + classFileName;
				Class clazz = Class.forName(classAllpackageName);
				if(!clazz.isAnnotationPresent(AuthClass.class)) continue;
				Method[] methods = clazz.getDeclaredMethods();
				for(Method method : methods) {
					if(!method.isAnnotationPresent(AuthMethod.class)) continue;
					RequestMapping reqMapping = method.getAnnotation(RequestMapping.class);
					pathes.add(reqMapping.value()[0]);
				}
			}
			
			ResourceService resourceService = (ResourceService) applicationContext.getBean("resourceService");
			resourceService.initPathes(pathes);
			context.setAttribute("allPermissionPathes", pathes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
