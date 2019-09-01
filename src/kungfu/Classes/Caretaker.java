package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Caretaker of a minor
 * @version 1.0
 * @author magda
 *
 */
@Entity(name="Caretaker")
public class Caretaker {
	public enum Type {Mother, Father, Other};
	private long id;
	private Type type;
	private Person person = null;
	private List<Student> caredFor = new ArrayList<>();
	private Caretaker() {}
	/**
	 * Caretaker constructor
	 * @param p Person to be Caretaker
	 * @param type Enum of type of Caretaker (Mother, Father, Other)
	 * @throws Exception of null Person
	 */
	private Caretaker(Person p, Type type) throws Exception {
		if(p == null) throw new Exception("Person must exist to become caretaker");
		if(this.person != null) throw new Exception("Caretaker is a person");
		setPerson(p);
		this.type = type;
		p.setCaretaker(this);
	}
	/**
	 * Static method to create Caretaker object
	 * @param p Person to become Caretaker 
	 * @param type Enum of type of Caretaker (Mother, Father, Other)
	 * @return Caretaker
	 * @throws Exception if Person doesn't exist
	 */
	public static Caretaker createCaretaker(Person p, Type type) throws Exception {
		if(p==null)throw new Exception("Osoba nie istnieje");
		Caretaker c = new Caretaker(p, type);
		p.setCaretaker(c);
		return c;
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
	 * Gets type of Caretaker
	 * @return Type
	 */
	@Basic
	public Type getType() {
		return type;
	}
	/**
	 * Sets Type of Caretaker
	 * @param type Enum of Mother, Father or Other
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * Gets Person
	 * Assocation 
	 * @return Person
	 */
	  //asocjacja
	   @OneToOne
	   @JoinColumn(name = "fk_person")
	public Person getPerson() {
		return person;
	}
	   /**
	    * Sets person
	    * @param person to be associated
	    * @throws Exception is person is set to null
	    */
	public void setPerson(Person person) throws Exception {
		if(person == null) throw new Exception("Can't nullify person");
		if(this.person == null)
		this.person = person;
	}
	/**
	 * Association
	 * Gets List of protegees
	 * @return List<Student> list
	 */
	@OneToMany(mappedBy = "caretaker", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<Student> getCaredFor(){
		   return this.caredFor;
	   }
	/**
	 * Add student to be cared for
	 * @param student to be added
	 */
	   public void addCaredFor(Student student) {
		   getCaredFor().add(student);
		   student.setCaretaker(this);
	   }
	   /**
	    * Remove student being cared for
	    * @param s Student to be removed 
	    */
	   public void removeCaredFor(Student s) {
		   getCaredFor().remove(s);
		   s.setCaretaker(null);
	   }
	   /**
	    * Sets cared for students
	    * @param list list of Student objects
	    */
	   public void setCaredFor(List<Student> list) {
		   this.caredFor = list;
	   } 
	   /**
	    * Pay fee for minor cared for
	    * @param s Student to be payed for
	    */
	   public void payForCaredOne(Student s) {
		   if(caredFor.contains(s))
			   s.payFee();
	   }
	   /**
	    * Overrides to string
	    */
	@Override 
	public String toString() {
		return person.getFullName()+" type: "+getType();
	}
}
