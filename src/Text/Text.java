package Text;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * 
 * 
 * @author franzi
 *
 *	Testen des Box Layouts
 */

public class Text extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Text(){
	Box mainBox = Box.createHorizontalBox();
	mainBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	JLabel a = new JLabel("  Mäischen liebt Bärchen");
	mainBox.add(a);
	this.add(mainBox); //Hinzufügen zum ContentPane
	
	Box box2 = Box.createVerticalBox();
	// box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	JLabel b = new JLabel("  b");
	box2.add(b);
	
	mainBox.add(box2);
	
	}
}
