package com.iyzico.domain;


import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo extends BaseEntity  implements  java.io.Serializable {

    @Column(name = "description")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Enumerated(EnumType.ORDINAL)
    private TodoState state;


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TodoState getState() {
		return state;
	}

	public void setState(TodoState state) {
		this.state = state;
	}
    
    
}
