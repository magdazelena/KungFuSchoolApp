package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Employee class (abstract)
 * @see java.lang.Object
 */
@Entity(name="Employee")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="employee")  
public abstract class Employee {
	private long id;
	private LocalDate employmentDate;
	private LocalDate releaseDate = null;
	private Double salary;
	private Long TaxIdNumber = null;
	private Person person;
	
	public Employee() {}
/**
 * Constructor Employee
 * @param person
 * @param salary
 * @throws Exception
 */
	public Employee(Person person,Double salary) throws Exception {
		if(person == null) throw new Exception("Person must exist to become Employee");
		setPerson(person);
		this.salary = salary;
		this.employmentDate = LocalDate.now();
	}
	
	//connections:
	/**
	 * Association
	 * Gets Person
	 * @return person
	 */
	 @OneToOne
	   @JoinColumn(name = "fk_person")
	   public Person getPerson() {
		   return this.person;
	   }
	 /**
	  * Sets person
	  * @param p
	  * @throws Exception
	  */
	   public void setPerson(Person p) throws Exception {
		   if(p == null) throw new Exception("Can't set person to null");
		   if(getPerson() == null)
			this.person = p;
	   }
	
	//methods
	   /**
	    * Release employee
	    *
	    */
	public void releaseEmployee() {
		setReleaseDate(LocalDate.now());
	}
	/**
	 * Raise salary
	 * @param percent
	 */
	public void raiseSalary(Double percent) {
		this.salary = this.salary*percent + this.salary;
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
	private void setId(long id) {
        this.id = id;
    }
	/**
	 * Gets employment date
	 * @return employmentDate
	 */
	@Basic
	public LocalDate getEmploymentDate() {
		return employmentDate;
	}
/**
 * Sets employment date
 * 
 * @param employmentDate
 */
	public void setEmploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}
	/**
	 * Gets release date 
	 * @return releaseDate
	 */
	@Basic
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	/**
	 * Sets release date
	 * @param releaseDate
	 */
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * Gets salary
	 * @return double
	 */
	@Basic
	public Double getSalary() {
		return salary;
	}
	/**
	 * Sets salary
	 * @param salary
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	/**
	 * Gets Tax id number
	 * @return taxIdNumber
	 */
	@Basic
	public Long getTaxIdNumber() {
		return TaxIdNumber;
	}
/**
 * Sets tax id number
 * @param taxIdNumber
 */
	public void setTaxIdNumber(Long taxIdNumber) {
		TaxIdNumber = taxIdNumber;
	}
	
}
