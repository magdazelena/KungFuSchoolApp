package kungfu.Classes;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "person")
public class Person {
    
	private long id;
	
    private String name;
    private String lastName;
    private String phone = null;
    /*mapping inheritance: */
    private Member member = null;
    private Employee employee = null;
    private Caretaker caretaker = null;
    
  
    /*req*/
    public Person() {}
    public Person(String name, String lastName, String phone){
        this.lastName = lastName;
        this.name = name;
        this.phone = phone;   
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
  //asocjacja
   @OneToOne(mappedBy = "person", orphanRemoval = true)
   public Member getMember() {
	   return this.member;
   }
   public void setMember(Member p) {
	   if(this.member == null) {
		   this.member = p;
	   }
		   
   }
   //asocjacja
   @OneToOne(mappedBy = "person", orphanRemoval = true)
   public Employee getEmployee() {
	   return this.employee;
   }
   public void setEmployee(Employee p) {
	   if(this.employee == null)
		   this.employee = p;
   }
   //asocjacja
   @OneToOne(mappedBy = "person", orphanRemoval = true)
   public Caretaker getCaretaker() {
	   return this.caretaker;
   }
   public void setCaretaker(Caretaker p) {
	   if(this.caretaker == null)
		   this.caretaker = p;
   }
   
    @Basic
    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    @Basic
    public String getLastName() {
    	return this.lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
    	return getName()+" "+getLastName();
    }
    @Basic
    public String getPhone() {
    	return this.phone;
    }
    public void setPhone(String number) {
    	this.phone = number;
    }

    @Override
    public String toString() {
        return String.format("Osoba: %s , telefon: %s,  id: %s (@%s)", getFullName(),  getPhone(),  getId(), super.hashCode());
    }
}
