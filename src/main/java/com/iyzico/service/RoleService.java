package com.iyzico.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.iyzico.domain.Role;


public interface RoleService {

    public void addRole(Role role) ;

    public Role getRole(Integer id) ;
    
    public Role getRole(String rolename);

    public void updateRole(Role role);

    public void deleteRole(Integer id);

    public List<Role> getRoles();
    
	public Page<Role> search(PageRequest request, Specification<Role> search);

	public List<Role> search(Specification<Role> search);

	public Page<Role> search(PageRequest request);
}
