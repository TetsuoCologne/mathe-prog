package ISBN;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */

	public Frame(){
		super("ISBN");
		addWindowListener(new WindowAdapter() {
			public void windowclosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		Model model = new Model();
		PrüfzifferView prüfzifferKorrektheit = new PrüfzifferView(model);
		KorrektView korrektView = new KorrektView(model);
		FehlendeZifferView fehlendeZiffer = new FehlendeZifferView(model);
		
	
		JTabbedPane pane = new JTabbedPane();
		pane.add("Prüfzifferberechnung",prüfzifferKorrektheit);
		pane.add("Korrektheit überprüfen",korrektView);
		pane.add("Fehlende Ziffer ergänzen",fehlendeZiffer);
		
		getContentPane().add(pane);
		setPreferredSize(new Dimension(700, 300));
		pack();
		
	}
	
	public static void main(String args[]){
		Frame frame = new Frame();
		frame.setVisible(true);
	}

}
