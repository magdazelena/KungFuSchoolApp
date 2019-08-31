package kungfu;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import kungfu.Classes.Accountant;
import kungfu.Classes.Caretaker;
import kungfu.Classes.EquipmentDecorative;
import kungfu.Classes.EquipmentSportive;
import kungfu.Classes.Location;
import kungfu.Classes.Master;
import kungfu.Classes.Member;
import kungfu.Classes.Member.Status;
import kungfu.Classes.MemberTeam;
import kungfu.Classes.Person;
import kungfu.Classes.Rental;
import kungfu.Classes.School;
import kungfu.Classes.Student;
import kungfu.Classes.Team;
import kungfu.Classes.Caretaker.Type;

public class Controller {
	
	
	
		private static StandardServiceRegistry registry = null;
		private static SessionFactory sessionFactory = null;
		
		public Controller() {
			try {
				registry = new StandardServiceRegistryBuilder()
						.configure() 
						.build();
				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			}catch(Exception e) {
				e.printStackTrace();
				StandardServiceRegistryBuilder.destroy( registry );
			}
		}
		
		public static Session getSession() {
			return sessionFactory.openSession();
		}
		public static void generateData() throws Exception {
			//test data
			String phones = "78787979";
			//people:
			Person person1 = new Person("Ala", "Makota", phones);
			Person person2 = new Person("Zosia", "Nowak", phones);
			Person person3 = new Person("Janusz", "Jot", phones);
			Person person4 = new Person("Stefan", "Siermiężny", phones);
			//memebers:
			Member m1 = new Member(person1, LocalDate.of(2000, 12, 1), 200);
			Member m2 = new Member(person2, LocalDate.of(1980, 05,11), 300);
			Member m3 = new Member(person3, LocalDate.of(2010,6,6), 150);
			Member m4 = new Member(person4, LocalDate.of(1985, 9, 21), 200);
			//Member test = new Member(person1, LocalDate.of(1999, 12,01), 100);
			m2.setFormerClub("EWTO Copenhagen");
			m1.setJoinDate(LocalDate.of(2015, 12, 01));
			
			//students:
			Student student1 = new Student(m1);
			//Student student3test = new Student(m3);
			Caretaker mum = new Caretaker(person2, Type.Mother);
			Student student2 = new Student(m3, mum);
			student1.setGrade(9);
			//test:
			//student2.setGrade(13);
			//student1.setGrade(11);
			student2.setGrade(12);
			//student2.upGrade();
			
			//master:
			Master master = new Master(m2);
			master.setGrade(15);
			Master master2 = new Master(m4, master);
			
			//test:
			//Student s = new Student(m4);
			
			//Team:
			Team team1 = new Team(1, master);
			Team team2 = new Team(2, master2);
			Team team3 = new Team(3, master);
			//Team test = new Team(1, master);
			
			List<String> days = new ArrayList<>();
			days.add("Środa");
			days.add("Czwartek");
			
			team1.setDays(days);
			
			
			//Assign students to team
			MemberTeam mt = new MemberTeam(m1, team1);
			MemberTeam mt1 = new MemberTeam(m2, team1);
			MemberTeam mt2 = new MemberTeam(m3, team2);
			MemberTeam mt3 = new MemberTeam(m4, team3);
			mt3.setLeaveDate(Date.valueOf(LocalDate.of(2019, 7, 1)));
			//Employee - Accountant
			Accountant acc = new Accountant(person4, 4000.00, 12345);
			
			//School
			School school = new School(master, acc);
			Location l1 = Location.createLocation(school,"Walecznych", 12, 00123,"Warszawa", true);
			Location l2 = Location.createLocation(school,"Marynarska", 1223, 1190, "Warszawa", false);
			team1.setLocation(l1);
			team2.setLocation(l2);
			team3.setLocation(l1);
			
			//Equipment decor
			EquipmentDecorative decor = new EquipmentDecorative(123, "Portret Yip Mana", master);
			EquipmentDecorative decor2 = new EquipmentDecorative(124, "Portret Leung Tin", master);
			school.addEquipment(decor);
			school.addEquipment(decor2);
			decor2.setOwner(master2);
			
			//Equipment sport
			EquipmentSportive makiwara = new EquipmentSportive(122, "Makiwara", 3);
			EquipmentSportive manekin = new EquipmentSportive(120, "Manekin drewniany", 12);
			school.addEquipment(makiwara);
			school.addEquipment(manekin);
			
			//Rental 
			//Rental rentManekin = new Rental(m1, manekin);
			Rental rentManekin = new Rental(m2, manekin);
			Rental rentMakiwara = new Rental(m1, makiwara);
			rentMakiwara.setReturnDay(Date.valueOf(LocalDate.of(2020, 1, 1)));
			m1.setStatus(Status.Suspended);
			//test data end
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(person1);
			session.saveOrUpdate(person3);
			session.saveOrUpdate(person2);
			session.saveOrUpdate(m1);
			session.saveOrUpdate(m2);
			session.saveOrUpdate(m3);
			session.saveOrUpdate(student1);
			session.saveOrUpdate(student2);
			session.saveOrUpdate(mum);
			session.saveOrUpdate(master);
			session.saveOrUpdate(person4);
			session.save(m4);
			session.save(master2);
			session.save(team1);
			session.save(team2);
			session.save(team3);
			session.save(mt); session.save(mt1); session.save(mt2); session.save(mt3);
			session.save(acc);
			session.save(school);
			session.save(l1); session.save(l2);
			session.save(decor); session.save(decor2);
			session.save(manekin); session.save(makiwara);
			session.save(rentMakiwara); session.save(rentManekin);
			//test data
			session.getTransaction().commit();
			session.flush();
			session.close();
			session.getSessionFactory().close();
		}
		public static void printExampleData() {
			System.out.println("\nZ bazy danych");
			Session session = sessionFactory.openSession();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Person> peopleFromDb = session.createQuery( "from person" ).list();
			for ( Person p : peopleFromDb) {
				System.out.println(p+"\n");
			}
			List<Member> membersFromDb = session.createQuery( "from Member" ).list();
			for ( Member m : membersFromDb) {
				System.out.println(m+"\n");
			}
			List<Team> teamsFromDb = session.createQuery( "from Team" ).list();
			
			String[][] groupTable = new String[teamsFromDb.size()][3];
			int i =0;
			for( Team t : teamsFromDb) {
				groupTable[i][0] = t.getTeamNr().toString();
				groupTable[i][1] = t.getDays().toString();
				groupTable[i][2] = t.getHours().toString();
				i++;
			}
			
			
			session.getTransaction().commit();
			session.close();
		}
		
}
