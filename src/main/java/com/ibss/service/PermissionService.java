package com.ibss.service;

import java.util.List;

import com.ibss.domain.Permission;


public interface PermissionService {

    public void addPermission(Permission permission);

    public Permission getPermission(int id);
    
    public Permission getPermission(String permissionname);

    public void updatePermission(Permission permission);

    public void deletePermission(int id);

    public List<Permission> getPermissions();

}
