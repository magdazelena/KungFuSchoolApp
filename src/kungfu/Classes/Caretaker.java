package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Caretaker")
public class Caretaker {
	public enum Type {Mother, Father, Other};
	private long id;
	private Type type;
	private Person person = null;
	private List<Student> caredFor = new ArrayList<>();
	public Caretaker() {}
	public Caretaker(Person p, Type type) throws Exception {
		if(p == null) throw new Exception("Person must exist to become caretaker");
		if(this.person != null) throw new Exception("Caretaker is a person");
		setPerson(p);
		this.type = type;
		p.setCaretaker(this);
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
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	  //asocjacja
	   @OneToOne
	   @JoinColumn(name = "fk_person")
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) throws Exception {
		if(person == null) throw new Exception("Can't nullify person");
		if(this.person == null)
		this.person = person;
	}
	
	@OneToMany(mappedBy = "caretaker", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<Student> getCaredFor(){
		   return this.caredFor;
	   }
	   public void addCaredFor(Student s) {
		   getCaredFor().add(s);
		   s.setCaretaker(this);
	   }
	   public void removeMemberTeam(Student s) {
		   getCaredFor().remove(s);
		   s.setCaretaker(null);
	   }
	   public void setCaredFor(List<Student> pg) {
		   this.caredFor = pg;
	   } 
	@Override 
	public String toString() {
		return person.getFullName()+" type: "+getType();
	}
}
