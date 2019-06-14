package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Accountant class
 * @see java.lang.Object
 */
@Entity(name="Accountant")
@DiscriminatorValue("accountant")  
public class Accountant extends Employee{
	private long id;
	private Integer certificateNumber;
	private List<School> schools = new ArrayList<>();
	public Accountant() {}
	/**
	 * Accountant constructor
	 * @param person (super)
	 * @param salary (super)
	 * @param cert
	 * @throws Exception of superclass
	 */
	public Accountant(Person person, Double salary, Integer cert) throws Exception {
		super(person, salary);
		this.certificateNumber = cert;
	}
	//connections 
	/**
	 * Gets list of schools 
	 * @return school list
	 */
	@OneToMany(mappedBy = "accountant", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<School> getSchools(){
		   return this.schools;
	   }
	/**
	 * Add school to list
	 * @param school
	 */
	   public void addSchool(School school) {
		   getSchools().add(school);
		   school.setAccountant(this);
	   }
	   /**
	    * Remove school from list
	    * @param school
	    */
	   public void removeSchool(School school) {
		   getSchools().remove(school);
		   school.setAccountant(null);
	   }
	   /**
	    * Sets school list
	    * @param school list
	    */
	   public void setSchools(List<School> school) {
		   this.schools = school;
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
	 * @param long
	 */
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets certificate number
	 * @return integer
	 */
	@Basic
	public Integer getCertificateNumber() {
		return this.certificateNumber;
	}
	/**
	 * Sets certificate person
	 * @param integer
	 */
	public void setCertificateNumber(Integer nr) {
		this.certificateNumber = nr;
	}
	/**
	 * Do finance report for the school
	 * @param school
	 * @return string (confirm)
	 */
	public String doFinanceReport(School school) {
		long money = school.getStudentCount() * Member.yearFee;
		return "Przychód z opłat rocznych: " + money;
	}
	/**
	 * Raise salary of the employee by given percent
	 * @param double
	 */
	public void raiseSalary(Double percent) {
		super.setSalary(super.getSalary()+super.getSalary()*percent);
	}
	/**
	 * to string method
	 */
	@Override
	public String toString() {
		return "Certifikat: "+getCertificateNumber();
	}
}
