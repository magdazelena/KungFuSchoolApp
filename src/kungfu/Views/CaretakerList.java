package kungfu.Views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import org.hibernate.Session;

import kungfu.Controller;
import kungfu.Classes.Caretaker;

@SuppressWarnings({ "serial", "rawtypes" })
public class CaretakerList extends AbstractListModel{

	private List<Caretaker> caretakers = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public CaretakerList() {
		Session s = Controller.getSession();
		this.setCaretakers(s.createQuery( "from Caretaker" ).list());
		s.close();
	}
	@Override
	public Caretaker getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return caretakers.get(arg0);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return caretakers.size();
	}
	public List<Caretaker> getCaretakers() {
		return caretakers;
	}
	public void setCaretakers(List<Caretaker> caretakers) {
		this.caretakers = caretakers;
	}

}
