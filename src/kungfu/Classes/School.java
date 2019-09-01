package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * School class
 * @version 1.0
 * @author magda
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
	 * @param leader Master leading the school
	 * @param accountant Accountant making the reports
	 */
	public School(Master leader, Accountant accountant) {
		setLeader(leader);
		setAccountant(accountant);
	}
	/**
	 * Gets id
	 * @return id
	 */
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
	/**
	 * Sets id
	 * @param id
	 */
	@SuppressWarnings("unused")
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets leader
	 * @return leader
	 */
	@OneToOne
	@JoinColumn(name = "fk_leader")
	public Master getLeader() {
		return leader;
	}
	/**
	 * Sets leader
	 * @param leader Master leading the school
	 */
	public void setLeader(Master leader) {
		this.leader = leader;
		leader.setLedSchool(this);
	}
	/**
	 * Gets studentCount
	 * @return studentCount
	 */
	@Basic
	public Integer getStudentCount() {
		return studentCount;
	}
	/**
	 * Set studentCount
	 * @param studentCount Integer of total students
	 */
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	/**
	 * Increment count
	 * @throws Exception if student number exceeds 100
	 */
	public void incStudentCount() throws Exception {
		if(getStudentCount()<100)
			setStudentCount(getStudentCount()+1);
		else throw new Exception("Student limit exceeded");
	}
	/**
	 * Decrement student count
	 */
	public void decStudentCount() {
		if(getStudentCount() >0)
			setStudentCount(getStudentCount()-1);
	}
	/**
	 * Calculates student count for school
	 */
	public void calculateStudentCount() {
		this.studentCount = Member.getStudentListSize();
	}
	/**
	 * Get head quarters
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
	 * Sets Location of school head quarters
	 * @param hQ Location of school HQ
	 */
	public void sethQ(Location hQ) {
		if(this.hQ != null) {
			this.hQ.setIsHQ(false);
			this.hQ = hQ;
		}
		hQ.setIsHQ(true);
	}
	/**
	 * Get locations
	 * @return List
	 */
	@OneToMany(mappedBy="school")
	public List<Location> getLocations() {
		return locations;
	}
	/**
	 * Set locations
	 * @param locations List of school locations
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	/**
	 * Adds location to list
	 * @param location Location to be added
	 */
	public void addLocation(Location location) {
		if(!this.getLocations().contains(location))
			this.locations.add(location);
		if(location.getIsHQ())
			this.sethQ(location);
	}
	/**
	 * Removes location from list
	 * @param location Location to be removed
	 */
	public void removeLocation(Location location) {
		if(this.getLocations().contains(location))
			this.locations.remove(location);
		if(location.getIsHQ())
			this.sethQ(null);
	}
	/**
	 * Gets Accountant
	 * @return Accountant
	 */
	@ManyToOne
	public Accountant getAccountant() {
		return accountant;
	}
	/**
	 * Sets Accountant
	 * @param accountant Accountant to be set
	 */
	public void setAccountant(Accountant accountant) {
		if(this.accountant == null) {
			this.accountant = accountant;
			accountant.addSchool(this);
		}
		
	}
	/**
	 * Gets Equipment
	 * @return List
	 */
	@OneToMany(mappedBy="school")
	public List<Equipment> getEquipment() {
		return equipment;
	}
	/**
	 * Sets equipment
	 * @param equipment Equipment List
	 */
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	/**
	 * Adds equipment to list
	 * @param eq Equipment to be added
	 */
	public void addEquipment(Equipment eq) {
		if(!this.equipment.contains(eq)) {
			this.equipment.add(eq);
			eq.setSchool(this);
		}
	}
	/**
	 * Removes equipment from list 
	 * @param eq Equipment to be removed
	 */
	public void removeEquipment(Equipment eq) {
		if(this.equipment.contains(eq)) {
			this.equipment.remove(eq);
			eq.setSchool(null);
		}
	}
	/**
	 * Overrides toString method
	 */
	public String toString() {
		return "Szkoła na "+this.gethQ().getStreet()+", "+this.gethQ().getCity();
	}
}
