package Zahlendarstellung;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
/**
 *	@author Marc Fielder (), Franziska Krebs (20101552)
 */

public class ZahlenFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	
	public ZahlenFrame(){
		super("Zahlendarstellung");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			
		});
		ZahlenModel model = new ZahlenModel();
		ZahlenView view = new ZahlenView(model);
		getContentPane().add(view);
		setPreferredSize(new Dimension(800, 400));
		pack();
		
	}
	
	public static void main(String args[]){
		ZahlenFrame frame = new ZahlenFrame();
		frame.setVisible(true);
		
		
	}
}
