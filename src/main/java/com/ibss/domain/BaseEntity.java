package com.ibss.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@MappedSuperclass
public abstract class BaseEntity implements java.io.Serializable{
	
	private static final long serialVersionUID = 6477402980386950166L;
	
	@Transient
	private Integer pageNumber;
	
	@Transient
	private Integer pageCount = 5;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column(name = "id")
	private Integer id;
	
	@Column(name="entity_status")
	private Integer entityStatus = 1;
	
//	@Version
	@Column(name="entity_version")
	private Integer entityVersion = 0;

	@Column(name="date_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	
	@Column(name="modifier")
	private String modifier;
	
	@Column(name="creation_time")
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

	
	public BaseEntity() {
	}

	
	public BaseEntity(Integer id) {
		super();
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
 
	public Integer getEntityVersion() {
		return entityVersion;
	}

	public void setEntityVersion(Integer entityVersion) {
		this.entityVersion = entityVersion;
	}

	public Integer getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(Integer entityStatus) {
		this.entityStatus = entityStatus;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}

		if ( this.id == null || obj == null || !( this.getClass().equals( obj.getClass() ) ) ) {
			return false;
		}

		BaseEntity that = ( BaseEntity ) obj;

		return this.id.equals( that.getId() );
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
	
	
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.creationTime = now;
        this.dateModified = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.dateModified = new Date();
    }

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}


}