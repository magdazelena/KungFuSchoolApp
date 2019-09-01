package kungfu.Classes;

import java.time.LocalDate;

import javax.persistence.*;
import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;
/**
 * MemberTeam class
 * @version 1.0
 * @author magda
 * @see java.lang.Object
 */
@Entity(name="MemberTeam")
public class MemberTeam {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private Team team;
	private Member member;
	private Date joinDate;
	private Date leaveDate = null;
	public MemberTeam() {}
	/**
	 * MemberTeam constructor
	 * @param member Member to be added to Team
	 * @param group Team to have Member added
	 */
	public MemberTeam(Member member, Team group) {
		this.member = member;
		this.team = group;
		this.joinDate = Date.valueOf(LocalDate.now());
		this.member.addMemberTeam(this);
		this.team.addMemberTeam(this);
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
	    @SuppressWarnings("unused")
		private void setId(long id) {
	        this.id = id;
	    }
	    /**
	     * Gets Team
	     * @return Team
	     */
		@ManyToOne
		public Team getTeam() {
			return this.team;
		}
		/**
		 * Sets team
		 * @param Team Team to be added
		 */
		public void setTeam(Team Team) {
			this.team = Team;
		}
		/**
		 * Gets Member
		 * @return Member
		 */
		@ManyToOne
		public Member getMember() {
			return this.member;
		}
		/**
		 * Sets member
		 * @param p Member to be added
		 */
		public void setMember(Member p) {
			this.member = p;
			
		}
		/**
		 * Gets joinDate
		 * @return joinDate
		 */
		@Basic 
		public Date getJoinDate() {
			return this.joinDate;
		}
		/**
		 * Sets joinDate
		 * @param date Join date in sql.Date format
		 */
		public void setJoinDate(Date date) {
			this.joinDate = date;
		}
		/**
		 * Overrides to String
		 */
		@Override
		public String toString() {
			return getMember().getPerson().getFullName()+" do grupy "+getTeam().getTeamNr()+" dołączyła: "+getJoinDate();
		}
		/**
		 * Gets leave date
		 * @return leaveDate
		 */
		@Basic
		public Date getLeaveDate() {
			return leaveDate;
		}
		/**
		 * Sets leave date
		 * @param leaveDate leave date in sql.Date format
		 */
		public void setLeaveDate(Date leaveDate) {
			this.leaveDate = leaveDate;
		}
	
}
