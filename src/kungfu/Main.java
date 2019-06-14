package kungfu;

import javax.swing.JFrame;

import kungfu.Views.GeneralView;

public class Main {

	public static void main(String[] args) {
		new Controller();
		try {
			Controller.generateData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controller.printExampleData();
		
		
		GeneralView frame = new GeneralView();
		frame.setTitle("Szkoła kung fu - widok administracyjny");
		frame.setName("Szkoła kung fu - widok administracyjny");
		frame.setVisible(true);
	}
}