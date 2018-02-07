package com.ibss.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "iyzico_role")
public class Role extends BaseEntity implements Serializable, GrantedAuthority  {

    private static final long serialVersionUID = 6874667425302308430L;
    static Logger logger = LoggerFactory.getLogger(Role.class);

	@NotNull(message = "{error.roles.role.null}")
    @NotEmpty(message = "{error.roles.role.empty}")
    @Size(max = 50, message = "{error.roles.role.max}")
    @Column(name = "rolename", length = 50)
    private String rolename;
 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private List<User> users = new ArrayList<User>();
	
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions",
        joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") }
    )    
    private Set<Permission> permissions;

    public Role() {
		super();
	}
    
    public Role(Integer id) {
	}
 
	public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<Permission> getPermissions() { 
        return permissions; 
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
 
    public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
    public String toString() {
        return String.format("%s(id=%d, rolename='%s')", 
                this.getClass().getSimpleName(), 
                this.getId(), this.getRolename());
    }
    
    @Override
    public String getAuthority() {
        return getRolename();
    }
}