package kungfu.Classes;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Equipment")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="equipment") 
public abstract class Equipment {
	private long id;
	private Integer serialNumber;
	private String name;
	private School school;
	public Equipment() {}

	public Equipment(int serial,String name) {
		setName(name);
		setSerialNumber(serial);
	}
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	private void setId(long id) {
        this.id = id;
    }
	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	@JoinColumn(name="fk_school")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	@Basic
	public Integer getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		if(this.serialNumber == null)
			this.serialNumber = serialNumber;
	}
}
