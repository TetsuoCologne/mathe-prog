package FiedlerKrebs;

import java.awt.event.*;

import javax.swing.*;
/**
 * Diese Klasse erzeugt das Fenster zur Ein- und Ausgabe der Daten.
 * 
 * @author Marc Fielder ( ); Franziska Krebs(20101552)
 *
 */
public class EuDFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//Konstruktor
	public EuDFrame(){
		super("Euklid'scher Algorithmus");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		EuDModel model = new EuDModel();
		EuDView view = new EuDView(model);
		getContentPane().add(view);
		setSize(500,200);
		pack();
	}

	
	public static void main(String[] args)  {
		EuDFrame ef = new EuDFrame();
		ef.setVisible(true);
	}


}
