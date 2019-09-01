package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Accountant class
 * extends Employee
 * @version 1.0
 * @author magda
 * @see Employee
 */
@Entity(name="Accountant")
@DiscriminatorValue("accountant")  
public class Accountant extends Employee{
	private long id;
	private Integer certificateNumber;
	private List<School> schools = new ArrayList<>();
	private Accountant() {}
	/**
	 * Accountant constructor (private)
	 * @param person Person to become Accountant
	 * @param salary Salary expressed as Double
	 * @param cert Ceritficate id number as Integer
	 * @throws Exception of superclass
	 */
	private Accountant(Person person, Double salary, Integer cert) throws Exception {
		super(person, salary);
		this.certificateNumber = cert;
	}
	/**
	 * Static method to create Accountant object based on Person
	 * @see Person 
	 * @param person Person to become Accountant
	 * @param salary Salary expressed as Double
	 * @param cert Certificate id number as Integer
	 * @return Accountant
	 * @throws Exception if Person is null
	 */
	public static Accountant createAccountant(Person person, Double salary, Integer cert) throws Exception {
		if(person == null) throw new Exception("Osoba nie istnieje");
		Accountant acc = new Accountant(person, salary, cert);
		person.setEmployee(acc);
		return acc;
	}
	//connections 
	/**
	 * Gets list of schools Accountant handles
	 * @return schools
	 */
	@OneToMany(mappedBy = "accountant", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<School> getSchools(){
		   return this.schools;
	   }
	/**
	 * Add school to list Accountant handles
	 * @param school School to be added
	 */
	   public void addSchool(School school) {
		   getSchools().add(school);
		   school.setAccountant(this);
	   }
	   /**
	    * Remove school from list Accountant handles
	    * @param school School to be removed
	    */
	   public void removeSchool(School school) {
		   getSchools().remove(school);
		   school.setAccountant(null);
	   }
	   /**
	    * Sets school list Accountant handles
	    * @param school School to be set
	    */
	   public void setSchools(List<School> school) {
		   this.schools = school;
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
	 * @param id Id to be set
	 */
	@SuppressWarnings("unused")
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets certificate number
	 * @return certificateNumber
	 */
	@Basic
	public Integer getCertificateNumber() {
		return this.certificateNumber;
	}
	/**
	 * Sets certificate number
	 * @param nr Ceritficate number as Integer
	 */
	public void setCertificateNumber(Integer nr) {
		this.certificateNumber = nr;
	}
	/**
	 * Does finance report for the school
	 * @param school School to be reported
	 * @return String
	 */
	public String doFinanceReport(School school) {
		long money = school.getStudentCount() * Member.yearFee;
		return "Przychód z opłat rocznych: " + money;
	}
	/**
	 * Raises salary of the employee by given percent
	 * @param percent Percent of salary raise
	 */
	public void raiseSalary(Double percent) {
		super.setSalary(super.getSalary()+super.getSalary()*percent);
	}
	/**
	 * to string method returns ceritificate credentials
	 */
	@Override
	public String toString() {
		return "Certifikat: "+getCertificateNumber();
	}
}
