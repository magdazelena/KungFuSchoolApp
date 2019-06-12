package kungfu.Classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="EquipmentDecorative")
@DiscriminatorValue("decorative")  
public class EquipmentDecorative extends Equipment{
	private long id;
	private Master owner;
	public EquipmentDecorative() {}
	
	public EquipmentDecorative(Integer serial, String name, Master owner) {
		super(serial, name);
		setOwner(owner);
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

	@ManyToOne
	public Master getOwner() {
		return this.owner;
	}
	public void setOwner(Master master) {
		this.owner = master;
		master.addDecor(this);
		
	}
}
