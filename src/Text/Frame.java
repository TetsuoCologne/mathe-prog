package Text;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame(){
		super("TestFrame");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			
		});
		Text text = new Text();
		
		this.add(text);
		this.setPreferredSize(new Dimension(300, 100));
		this.pack();
		this.setVisible(true);
		
	}
	
	public static void main(String args[]){
		Frame frame = new Frame();
	
		
		
	}
	
}
