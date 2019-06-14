package kungfu.Classes;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * Person class
 * @see java.lang.Object
 */
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
    /**
     * Constructor
     * @param String name
     * @param String lastName
     * @param String phone
     */
    public Person(String name, String lastName, String phone){
        this.lastName = lastName;
        this.name = name;
        this.phone = phone;   
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
    /**Sets id
     * @param long
     */
    private void setId(long id) {
        this.id = id;
    }
  //asocjacja
    /**
     * Association
     * Gets Member
     * @return Member
     */
   @OneToOne(mappedBy = "person", orphanRemoval = true)
   public Member getMember() {
	   return this.member;
   }
   /**
    * Sets Member
    * @param Member
    */
   public void setMember(Member p) {
	   if(this.member == null) {
		   this.member = p;
	   }
		   
   }
   //asocjacja
   /**
    * Association
    * Gets Employee
    * @return Employee
    */
   @OneToOne(mappedBy = "person", orphanRemoval = true)
   public Employee getEmployee() {
	   return this.employee;
   }
   /**
    * Sets employee
    * @param Employee
    */
   public void setEmployee(Employee p) {
	   if(this.employee == null)
		   this.employee = p;
   }
   //asocjacja
   /**
    * Gets Caretaker
    * @return Caretaker
    */
   @OneToOne(mappedBy = "person", orphanRemoval = true)
   public Caretaker getCaretaker() {
	   return this.caretaker;
   }
   /**
    * Sets Caretaker
    * @param Caretaker
    */
   public void setCaretaker(Caretaker p) {
	   if(this.caretaker == null)
		   this.caretaker = p;
   }
   /**
    * Gets Name
    * @return String
    */
    @Basic
    public String getName() {
    	return this.name;
    }
    /**
     * Sets name
     * @param String
     */
    public void setName(String name) {
    	this.name = name;
    }
    /**
     * Gets last name
     * @return String
     */
    @Basic
    public String getLastName() {
    	return this.lastName;
    }
    /**
     * Sets last name
     * @param String
     */
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
/**
 * Get full concat name
 * @return String
 */
    @Transient
    public String getFullName() {
    	return getName()+" "+getLastName();
    }
    /**
     * Gets phone
     * @return String
     */
    @Basic
    public String getPhone() {
    	return this.phone;
    }
    /**
     * Sets phone
     * @param String
     */
    public void setPhone(String number) {
    	this.phone = number;
    }
/**
 * Overrides to String
 */
    @Override
    public String toString() {
        return String.format("Osoba: %s , telefon: %s,  id: %s (@%s)", getFullName(),  getPhone(),  getId(), super.hashCode());
    }
}
