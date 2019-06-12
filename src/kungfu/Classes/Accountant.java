package kungfu.Classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Accountant")
@DiscriminatorValue("accountant")  
public class Accountant extends Employee{
	private long id;
	private Integer certificateNumber;
	private List<School> schools = new ArrayList<>();
	public Accountant() {}
	
	public Accountant(Person person, Double salary, Integer cert) throws Exception {
		super(person, salary);
		this.certificateNumber = cert;
	}
	//connections 
	@OneToMany(mappedBy = "accountant", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<School> getSchools(){
		   return this.schools;
	   }
	   public void addSchool(School school) {
		   getSchools().add(school);
		   school.setAccountant(this);
	   }
	   public void removeSchool(School school) {
		   getSchools().remove(school);
		   school.setAccountant(null);
	   }
	   public void setSchools(List<School> school) {
		   this.schools = school;
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
	public Integer getCertificateNumber() {
		return this.certificateNumber;
	}
	public void setCertificateNumber(Integer nr) {
		this.certificateNumber = nr;
	}
	
	public String doFinanceReport() {
		return "Much money";
	}
	public void raiseSalary(Double percent) {
		super.setSalary(super.getSalary()+super.getSalary()*percent);
	}
	
	public String toString() {
		return "Certifikate: "+getCertificateNumber();
	}
}
