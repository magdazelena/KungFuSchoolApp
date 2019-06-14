package kungfu.Classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
/**
 * Equipment decorative class
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
	 * @param integer
	 * @param String
	 * @param Master
	 */
	public EquipmentDecorative(Integer serial, String name, Master owner) {
		super(serial, name);
		setOwner(owner);
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
	 * @param long	 */
	private void setId(long id) {
        this.id = id;
    }
/**
 * Gets owner
 * @return Master
 */
	@ManyToOne
	public Master getOwner() {
		return this.owner;
	}
	/**
	 * Sets owner
	 * @param Master
	 */
	public void setOwner(Master master) {
		this.owner = master;
		master.addDecor(this);
		
	}
}
