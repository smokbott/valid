package co.com.valid.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Person Entity class
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */

@Entity
@Table(name = "person", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "lastname" }) })
@ApiModel("Model person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "the person's id", required = true)
	private long id;

	@NotNull
	@Column(name = "name")
	@ApiModelProperty(value = "the person's name", required = true)
	private String name;

	@NotNull
	@Column(name = "lastname")
	@ApiModelProperty(value = "the person's lastname", required = true)
	private String lastname;

	@Column(name = "processed")
	@ApiModelProperty(value = "the person's processed")
	private boolean processed;

	@Column(name = "created")
	@ApiModelProperty(value = "the person's create Date", required = false)
	private Date createDate;

	@Column(name = "edited")
	@ApiModelProperty(value = "the person's edited Date", required = false)
	private Date updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
