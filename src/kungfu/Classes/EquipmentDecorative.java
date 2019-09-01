package kungfu.Classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
/**
 * Equipment decorative class
 * @version 1.0
 * @author magda
 * @see java.lang.Object
 */
@Entity(name="EquipmentDecorative")
@DiscriminatorValue("decorative")  
public class EquipmentDecorative extends Equipment{
	private long id;
	private Master owner;
	public EquipmentDecorative() {}
/**
 * Constructor
 * @param serial Serial number of equipment as integer
 * @param name String name of equipment
 * @param owner Master who protects the equipment
 */
	public EquipmentDecorative(Integer serial, String name, Master owner) {
		super(serial, name);
		setOwner(owner);
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
	 * @param id	 */
	@SuppressWarnings("unused")
	private void setId(long id) {
        this.id = id;
    }
/**
 * Gets owner
 * @return owner
 */
	@ManyToOne
	public Master getOwner() {
		return this.owner;
	}
	/**
	 * Sets owner
	 * @param master Master who protects the equipment
	 */
	public void setOwner(Master master) {
		this.owner = master;
		master.addDecor(this);
		
	}
}
