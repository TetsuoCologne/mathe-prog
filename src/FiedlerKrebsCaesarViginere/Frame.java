package FiedlerKrebsCaesarViginere;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 *	@author Marc Fielder (20101508), Franziska Krebs (20101552)
 *	Erzeugt ein Fenster für die Ein- und Ausgabe.
 */

public class Frame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	
	public Frame(){
		super("Kryptographie: Caesar - und Viginere - Code");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			
		});
		Verschluesselung model = new Verschluesselung();
		KryptoView view = new KryptoView(model);
		this.getContentPane().add(view);
		setPreferredSize(new Dimension(800, 500));
		pack();
		
	}
	
	public static void main(String args[]){
		Frame frame = new Frame();
		frame.setVisible(true);
		
	}
}



