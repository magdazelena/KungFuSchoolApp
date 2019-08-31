package kungfu.Views;
import java.util.*;

import kungfu.Controller;
import kungfu.Classes.*;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Session;

public class RentalMemberTable extends AbstractTableModel{

	private List<Rental> memRentals = new ArrayList<>();
	public RentalMemberTable(Member m) {
		this.memRentals =m.getMemberRentals();
	}
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return memRentals.size();
	}
	@Override 
	public String getColumnName(int colNr) {
		if(colNr == 0) return "Nazwa sprzętu";
		if(colNr ==1) return "Nr seryjny";
		if(colNr ==2 ) return "Min. stopień wyp.";
		if(colNr ==3 ) return "Właściciel";
		if(colNr ==4) return "Data wypożyczenia";
		if(colNr==5) return "Data zwrotu";
		return "";
	}

	@Override
	public Object getValueAt(int rowNr, int colNr) {
		Rental rental = memRentals.get(rowNr);
		if(colNr == 0) return rental.getEqSportive().getName();
		if(colNr == 1) return rental.getEqSportive().getSerialNumber().toString();
		if(colNr == 2) return rental.getEqSportive().getMinGrade().toString();
		if(colNr == 3) return rental.getEqSportive().getSchool().toString();
		if(colNr == 4) return rental.getRentDay().toString();
		if(colNr == 5) {
			if(rental.getReturnDay() != null) return rental.getReturnDay().toString();
			else return "Wciąż w wypożyczeniu";
		}
		return null;
	}
	public List<Rental> getRentals(){
		return this.memRentals;
	}
}
