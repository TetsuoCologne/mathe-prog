package Zahlendarstellung;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/**
 *	@author Marc Fielder (), Franziska Krebs (20101552)
 */

public class Frame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	
	public Frame(){
		super("Zahlendarstellung");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			
		});
		ZahlenModel zaModel = new ZahlenModel();
		PotenzModel poModel = new PotenzModel();
		ZahlenPanel zahlenView = new ZahlenPanel(zaModel);
		PotenzPanel potenzView = new PotenzPanel(poModel);
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab("Zahlensysteme", zahlenView);
		tabPane.addTab("Potenzen", potenzView);
		
		getContentPane().add(tabPane);
				
		setPreferredSize(new Dimension(800, 400));
		pack();
		
	}
	
	public static void main(String args[]){
		Frame frame = new Frame();
		frame.setVisible(true);
		
		
	}
}
