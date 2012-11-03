package Wochentagsberechnung;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 *
 *	@author Marc Fielder (20101508); Franziska Krebs(20101552)
 *	Diese Klasse erstellt das Fenster und zeigt es an.
 *
 */
public class WochenFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	public WochenFrame(){
		super("Wochentagsberechnung");
		addWindowFocusListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		WochenModel model = new WochenModel();
		WochenView view = new WochenView(model);
		getContentPane().add(view);
		
		
		setPreferredSize(new Dimension(500, 250));
		pack();
		
	}
	public static void main(String[] args) {
		WochenFrame frame = new WochenFrame();
		frame.setVisible(true);

	}

}
