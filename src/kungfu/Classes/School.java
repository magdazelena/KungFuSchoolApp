package kungfu.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * School class
 * @see java.lang.Object
 */
@Entity(name="School")
public class School {
	
	
	private long id;
	private Master leader;
	private Integer studentCount = 0;
	private Location hQ = null;
	private List<Location> locations = new ArrayList<>();
	private Accountant accountant;
	private List<Equipment> equipment = new ArrayList<>();
	//private Map<Integer, Equipment> equipment = new HashMap<>();
	public School() {}
	/**
	 * School constructor
	 * @param Master leader
	 * @param Accountant accountant
	 */
	public School(Master leader, Accountant accountant) {
		setLeader(leader);
		setAccountant(accountant);
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
	 * 	 */
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Association
	 * Gets leader
	 * @return Master
	 */
	@OneToOne
	@JoinColumn(name = "fk_leader")
	public Master getLeader() {
		return leader;
	}
	/**
	 * Sets leader
	 * @param Master
	 */
	public void setLeader(Master leader) {
		this.leader = leader;
		leader.setLedSchool(this);
	}
	/**
	 * Gets StudentCount
	 * @return integer
	 */
	@Basic
	public Integer getStudentCount() {
		return studentCount;
	}
	/**
	 * Sets Student count
	 * @param integer
	 */
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	/**
	 * Increment student count
	 * @param integer
	 * @throws Exception
	 */
	public void incStudentCount(Integer id) throws Exception {
		if(this.id == id)
		if(getStudentCount()<100)
			setStudentCount(getStudentCount()+1);
		else throw new Exception("Student limit exceeded");
	}
	/**
	 * Decrement student count
	 * @param integer
	 */
	public void decStudentCount(Integer id) {
		if(this.id ==id)
		if(getStudentCount() >0)
			setStudentCount(getStudentCount()-1);
	}
	/**
	 * Gets headquarter
	 * @return Location
	 */
	@Transient
	public Location gethQ() {
		Location res = null;
		for(Location l :locations) {
			if(l.getIsHQ()) res = l;
		};
		return res;
	}
	/**
	 * Sets headquarters
	 * @param Location
	 */
	public void sethQ(Location hQ) {
		if(this.hQ != null) {
			this.hQ.setIsHQ(false);
			this.hQ = hQ;
		}
		hQ.setIsHQ(true);
	}
	/**
	 * Association
	 * Gets Locations
	 * @return Location list
	 */
	@OneToMany(mappedBy="school")
	public List<Location> getLocations() {
		return locations;
	}
	/**
	 * Sets locations
	 * @param Location list
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	/**
	 * Add new location
	 * @param street
	 * @param number
	 * @param zipcode
	 * @param city
	 * @param hq
	 * @throws Exception
	 */
	public void addNewLocation(String street, Integer number, Integer zipcode, String city, Boolean hq) throws Exception {
		if(hq) {
			if(this.gethQ() !=null) throw new Exception("School already has headquaters");
		} 
		Location.createLocation(this, street, number, zipcode, city, hq);
	}
	/**
	 * Adds Location
	 * @param Location
	 */
	public void addLocation(Location location) {
		if(!this.getLocations().contains(location))
			this.locations.add(location);
		if(location.getIsHQ())
			this.sethQ(location);
	}
	/**
	 * Removes location
	 * @param Location
	 */
	public void removeLocation(Location location) {
		if(this.getLocations().contains(location))
			this.locations.remove(location);
		if(location.getIsHQ())
			this.sethQ(null);
	}
	/**
	 * Association
	 * Gets Accountant
	 * @return Accountant
	 */
	@ManyToOne
	public Accountant getAccountant() {
		return accountant;
	}
/**
 * Sets Accountant
 * @param Accountant
 */
	public void setAccountant(Accountant accountant) {
		if(this.accountant == null) {
			this.accountant = accountant;
			accountant.addSchool(this);
		}
		
	}
	/**
	 * Assocation
	 * Gets equipment
	 * @return Equipment List
	 */
	@OneToMany(mappedBy="school")
	public List<Equipment> getEquipment() {
		return equipment;
	}
	/**
	 * Sets Equipment
	 * @param Equipment List
	 */
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	/**
	 * Add Equipment
	 * @param Equipment
	 */
	public void addEquipment(Equipment eq) {
		if(!this.equipment.contains(eq)) {
			this.equipment.add(eq);
			eq.setSchool(this);
		}
	}
	/**
	 * Removes Equipment
	 * @param Equipment
	 */
	public void removeEquipment(Equipment eq) {
		if(this.equipment.contains(eq)) {
			this.equipment.remove(eq);
			eq.setSchool(null);
		}
	}
}
