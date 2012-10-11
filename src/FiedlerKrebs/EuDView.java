package FiedlerKrebs;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
/**
 * 	Diese Klasse stellt die View-Komponente des MVC- Prinzips dar. Es legt die Einstellungen für das Fenster fest, liest die Daten ein und 
 * 	implementiert das Observer-Pattern. 
 * 	@author Marc Fielder, Franziska Krebs
 *	
 */
public class EuDView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	private EuDModel model;
	
	//Buttons und Felder zur Ein- und Ausgabe
	private JButton compute = new JButton("Berechnen");
	private JTextField a = new JTextField("",10);
	private JTextField b = new JTextField("",10);
	private JTextField g = new JTextField("",6);
	private JTextField ba = new JTextField("",6); 
	private JTextField bb = new JTextField("",6);

	//Konstruktor
	public EuDView(EuDModel model)  {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		a.setAlignmentX(LEFT_ALIGNMENT);
		box.add(a);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		b.setAlignmentX(LEFT_ALIGNMENT);
		box.add(b);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(compute);
		add(box);		

		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  ggT"));		
		box2.add(Box.createVerticalStrut(5));
		g.setAlignmentX(LEFT_ALIGNMENT);
		g.setEditable(false);
		box2.add(g);
		add(box2);		
		
		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(15,15,15,50));
		box3.add(new JLabel("Bezout-Koeffizient von a "));
		box3.add(Box.createVerticalStrut(5));
		ba.setAlignmentX(LEFT_ALIGNMENT);
		ba.setEditable(false);
		box3.add(ba);
		box3.add(Box.createVerticalStrut(5));
		box3.add(new JLabel("Bezout-Koeffizient von b"));
		box3.add(Box.createVerticalStrut(5));
		bb.setAlignmentX(LEFT_ALIGNMENT);
		bb.setEditable(false);
		box3.add(bb);
		add(box3);
	
	}

	//Einlesen der Werte
	private void readInput(){
		try {
			model.setA(Integer.valueOf(a.getText()));
			model.setB(Integer.valueOf(b.getText()));
			model.berechneGgT();

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		} 		
		catch(ZeroException ze){
			JOptionPane.showMessageDialog(this, "ggT(0,0) ist nicht definiert!");
		}
		catch (NegativeEingabeException ne){
			JOptionPane.showMessageDialog(this, "Bitte keine negativen Eingaben!");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute) readInput();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		g.setText(model.getGgt()+"");
		ba.setText(model.getBezoutA()+"");
		bb.setText(model.getBezoutB()+"");
	}

}



