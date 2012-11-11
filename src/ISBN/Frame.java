package ISBN;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame(){
		super("ISBN");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		PrüfzifferModel prüfZifferModel = new PrüfzifferModel();
		KorrekteISBNModel korrektModel = new KorrekteISBNModel();
		FehlendeZifferModel fehlendeZifferModel = new FehlendeZifferModel();

		PrüfzifferView prüfzifferKorrektheit = new PrüfzifferView(prüfZifferModel);
		KorrektView korrektView = new KorrektView(korrektModel);
		FehlendeZifferView fehlendeZiffer = new FehlendeZifferView(fehlendeZifferModel);


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
