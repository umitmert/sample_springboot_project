package com.ibss.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibss.domain.Permission;
import com.ibss.repository.PermissionRepository;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    
    @Resource
    private PermissionRepository permissionDAO;

    @Override
    public void addPermission(Permission permission) {
        permissionDAO.save(permission);
    }

    @Override
    public Permission getPermission(int id){
        return permissionDAO.getOne(id);
    }

    @Override
    public Permission getPermission(String permissionname) {
        return permissionDAO.findByName(permissionname);
    }

    @Override
    public void updatePermission(Permission permission){
        permissionDAO.save(permission);
    }

    @Override
    public void deletePermission(int id) {
        permissionDAO.delete(id);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionDAO.findAll();
    }
}
