package org.sid.services;


import java.util.List;

import org.sid.entities.Role;
import org.sid.entities.Users;
import org.sid.services.common.IOperations;

public interface UserService extends IOperations<Users>{

	public Users saveUser(String username,String password,String confirmedPassword);
	public Role save (Role role);
	public Users loadUserByUsername(String usename);
	public void addRoleToUser(String username,String roleName);
	
	public List<Users> findAlls();
}
