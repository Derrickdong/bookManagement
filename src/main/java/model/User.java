package model;

import java.util.HashSet;
import java.util.Set;

public class User {

	private int id;
	private String username;
	private String password;
	private Integer state;//0:denied 1:accepted
	
	private Set<Role> roles = new HashSet<>();
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", state=" + state + ", roles="
				+ roles + "]";
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
