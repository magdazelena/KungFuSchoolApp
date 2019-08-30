package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
/**
 * Equipment sportive class
 * @see java.lang.Object
 */
@Entity(name="EquipmentSportive")
@DiscriminatorValue("sportive")  
public class EquipmentSportive extends Equipment{
	private long id;
	private Integer minGrade;
	private List<Rental> rented = new ArrayList<>();
	public EquipmentSportive() {}
/**
 * Constructor
 * @param serial
 * @param name
 * @param mingrade
 */
	public EquipmentSportive(Integer serial, String name, Integer mingrade) {
		super(serial, name);
		setMinGrade(mingrade);
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
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets min grade
	 * @return minGrade
	 */
	@Basic
	public Integer getMinGrade() {
		return minGrade;
	}
	/**
	 * Sets min grade
	 * @param minGrade
	 */
	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}
	/**
	 * Association
	 * Gets Rental list
	 * @return rented
	 */
	@OneToMany(mappedBy = "eqSportive", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Rental> getRented() {
		return rented;
	}
/**
 * Sets Rented
 * @param rented
 */
	public void setRented(List<Rental> rented) {
		this.rented = rented;
	}
	/**
	 * Add Rented
	 * @param rent
	 */
	public void addRented(Rental rent) {
		if(!this.rented.contains(rent)) {
			this.rented.add(rent);
			rent.setEqSportive(this);
		}
	}
	/**
	 * Remove rented
	 * @param rent
	 */
	public void removeRented(Rental rent) {
		if(this.rented.contains(rent)) {
			this.rented.remove(rent);
			rent.setEqSportive(null);
		}
			
	}
}
