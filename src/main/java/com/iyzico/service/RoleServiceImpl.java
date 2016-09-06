package com.iyzico.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyzico.domain.Role;
import com.iyzico.repository.RoleRepository;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    
    @Autowired
    private RoleRepository roleDAO;

    @Override
    public void addRole(Role role)  {
        roleDAO.save(role);
    }

    @Override
    public Role getRole(Integer id){
        return roleDAO.getOne(id);
    }

    @Override
    public Role getRole(String rolename){
        return roleDAO.getRoleName(rolename);
    }

    @Override
    public void updateRole(Role role){
        roleDAO.save(role);
    }

    @Override
    public void deleteRole(Integer id){
        roleDAO.delete(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.findAll();
    }
    
	@Override
	public Page<Role> search(PageRequest request, Specification<Role> search) {
		return roleDAO.findAll(search, request);
	}


	@Override
	public Page<Role> search(PageRequest request) {
		Page<Role> list= roleDAO.findAll(request);
		return list;
	}

	@Override
	public List<Role> search(Specification<Role> search) {
		return roleDAO.findAll(search);
	}
}
