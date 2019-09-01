package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Caretaker class
 * @see java.lang.Object
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
	 * @param p
	 * @param type
	 * @throws Exception
	 */
	private Caretaker(Person p, Type type) throws Exception {
		if(p == null) throw new Exception("Person must exist to become caretaker");
		if(this.person != null) throw new Exception("Caretaker is a person");
		setPerson(p);
		this.type = type;
		p.setCaretaker(this);
	}
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
	 * @param long
	 */
	@SuppressWarnings("unused")
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets type
	 * @return Type
	 */
	@Basic
	public Type getType() {
		return type;
	}
	/**
	 * Sets Type
	 * @param Type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * Gets Person
	 * Assocation
	 * 
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
	    * @param Person
	    * @throws Exception
	    */
	public void setPerson(Person person) throws Exception {
		if(person == null) throw new Exception("Can't nullify person");
		if(this.person == null)
		this.person = person;
	}
	/**
	 * Association
	 * Gets List of protegees
	 * @return Student list
	 */
	@OneToMany(mappedBy = "caretaker", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<Student> getCaredFor(){
		   return this.caredFor;
	   }
	/**
	 * Add student
	 * @param Student
	 */
	   public void addCaredFor(Student s) {
		   getCaredFor().add(s);
		   s.setCaretaker(this);
	   }
	   /**
	    * remove student
	    * @param Student
	    */
	   public void removeCaredFor(Student s) {
		   getCaredFor().remove(s);
		   s.setCaretaker(null);
	   }
	   /**
	    * Sets cared for
	    * @param Student list
	    */
	   public void setCaredFor(List<Student> pg) {
		   this.caredFor = pg;
	   } 
	   /**
	    * Pay fee for minor
	    * @param Student
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
