package ISBN;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/**
 * 	Aufgabe 4: ISBN-Prüfverfahren
 *	@author Marc Fielder (20101508); Franziska Krebs(20101552)
 *	Diese Klasse erstellt das Fenster und zeigt es an.
 */
public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame(){
		super("ISBN");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		PruefzifferModel pruefZifferModel = new PruefzifferModel();
		KorrekteISBNModel korrektModel = new KorrekteISBNModel();
		FehlendeZifferModel fehlendeZifferModel = new FehlendeZifferModel();

		PrüfzifferView pruefzifferKorrektheit = new PrüfzifferView(pruefZifferModel);
		KorrektView korrektView = new KorrektView(korrektModel);
		FehlendeZifferView fehlendeZiffer = new FehlendeZifferView(fehlendeZifferModel);


		JTabbedPane pane = new JTabbedPane();
		pane.add("Prüfzifferberechnung",pruefzifferKorrektheit);
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
