package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

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

	public Employee(Person person,Double salary) throws Exception {
		if(person == null) throw new Exception("Person must exist to become Employee");
		setPerson(person);
		this.salary = salary;
		this.employmentDate = LocalDate.now();
	}
	
	//connections:
	 @OneToOne
	   @JoinColumn(name = "fk_person")
	   public Person getPerson() {
		   return this.person;
	   }
	   public void setPerson(Person p) {
			this.person = p;
	   }
	
	//methods
	public void releaseEmployee() {
		setReleaseDate(LocalDate.now());
	}
	public void raiseSalary() {}
	
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
	public LocalDate getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(LocalDate employmentDate) {
		this.employmentDate = employmentDate;
	}
	@Basic
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	@Basic
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Basic
	public Long getTaxIdNumber() {
		return TaxIdNumber;
	}

	public void setTaxIdNumber(Long taxIdNumber) {
		TaxIdNumber = taxIdNumber;
	}
	
}
