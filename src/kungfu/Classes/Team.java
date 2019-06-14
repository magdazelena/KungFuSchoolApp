package kungfu.Classes;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
/**
 * Team class
 * @see java.lang.Object
 */
@Entity(name="Team")
public class Team {

	private long id;
	private Integer TeamNr;
	private List<MemberTeam> memberTeams = new ArrayList<>();
	private Master master;
	private List<String> days = new ArrayList<>();
	private List<String> hours = new ArrayList<>();
	private Location location = null;
	public Team() {}
	/**
	    * Team constructor
	    * @param nr is unique integer
	    * @param master is registered object
	    * @throws Exception id master is set to null 
	    
	    */
	public Team(Integer nr, Master master) throws Exception {
		if(master == null) throw new Exception("Master may not be null");
		this.TeamNr = nr;
		setMaster(master);
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
     * Gets team nr
     * @return integer
     */
	   @Basic 
	   @Column(unique=true)
	   public Integer getTeamNr() {
		   return this.TeamNr;
	   }
	   /**
	    * Sets team nr 
	    * @param nr - integer
	    */
	   public void setTeamNr(Integer nr) {
		   this.TeamNr = nr;
	   }
	   /**
	    * Gets association classes connecting team and member
	    * 
	    * @return list of MemberTeam
	    */
	   @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
	   private List<MemberTeam> getMemberTeams(){
		   return this.memberTeams;
	   }
	   /**
	    * Adding single MemberTeam object to list
	    * @param MemberTeam object
	    */
	   public void addMemberTeam(MemberTeam pg) {
		   getMemberTeams().add(pg);
		   pg.setTeam(this);
	   }
	   /**
	    * Removing single MemberTeam from list
	    * @param MemberTeam object
	    */
	   public void removeMemberTeam(MemberTeam pg) {
		   getMemberTeams().remove(pg);
		   pg.setTeam(null);
	   }
	   /**
	    * Sets list of MemberTeams
	    * @param list of MemberTeam
	    */
	   public void setMemberTeams(List<MemberTeam> pg) {
		   this.memberTeams = pg;
	   }
	   /**
	    * To string method printing nr and leader of group
	    */
	   @Override 
	   public String toString() {
		   return String.format("Grupa nr: %s, prowadzący %s", getTeamNr(), getMaster());
	   }
	   /**
	    * Association of team and master
	    * @return Master
	    */
	@ManyToOne
	public Master getMaster() {
		return master;
	}
	/**
	 * Sets Master for the team
	 * @param master
	 */
	public void setMaster(Master master) {
		if(this.master == null) {
			this.master = master;
			master.addTeam(this);
		}
		
	}
	/**
	 * Gets days of team
	 * @return string list
	 */
	@ElementCollection
	public List<String> getDays() {
		return days;
	}
	/**
	 * Sets days of team
	 * @param list string 
	 */
	public void setDays(List<String> days) {
		this.days = days;
	}
	/**
	 * Gets hours of team
	 * 
	 * @return string list
	 */
	@ElementCollection
	public List<String> getHours() {
		return hours;
	}
	/**
	 * Sets hours of team
	 * @param string list
	 */
	public void setHours(List<String> hours) {
		this.hours = hours;
	}
	/**
	 * Gets Location
	 * @return Location object
	 */
	@ManyToOne
	public Location getLocation() {
		return location;
	}
	/**
	 * Sets Location
	 * @param location
	 */
	public void setLocation(Location location) {
		if(this.location != location) {
			if(this.location != null)
				this.location.removeTeam(this);
			this.location = location;
			location.addTeam(this);
		}
	}
	/**
	 * Modify month fee of all team members
	 * @param integer
	 */
	public void modifyMonthFee(Integer change) {
		for(MemberTeam mt : memberTeams) {
			mt.getMember().setMonthFee(change);
		}
	}
	   
}
