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
/**
 * Equipment class (abstract)
 * @see java.lang.Object
 */
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
/**
 * Equipment constructor
 * @param int
 * @param String
 */
	public Equipment(int serial,String name) {
		setName(name);
		setSerialNumber(serial);
	}
	/**
	 * Gets id
	 * @return long
	 */
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	/**
	 * Sets id
	 * @param long
	 */
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets name
	 * @return String
	 */
	@Basic
	public String getName() {
		return name;
	}
/**
 * Sets name
 * @param String
 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets school
	 * @return School
	 */
	@ManyToOne
	@JoinColumn(name="fk_school")
	public School getSchool() {
		return school;
	}
/**
 * Sets school
 * @param School
 */
	public void setSchool(School school) {
		this.school = school;
	}
	/**
	 * Gets serial
	 * @return integer
	 */
	@Basic
	public Integer getSerialNumber() {
		return serialNumber;
	}
	/**
	 * Sets Serial
	 * @param integer
	 */
	
	public void setSerialNumber(int serialNumber) {
		if(this.serialNumber == null)
			this.serialNumber = serialNumber;
	}
}
