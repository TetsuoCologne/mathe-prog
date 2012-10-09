package Zahlendarstellung;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FielderKrebs.NegativeEingabeException;
import FielderKrebs.ZeroException;

/**
 * 
 */

public class ZahlenView extends JPanel implements ActionListener, Observer {
	
	private static final long serialVersionUID = 1L;
	
	private ZahlenModel model;
	
	JLabel heading;
	JButton berechnen;
	JLabel dualLab;
	JTextField dual;
	JLabel dezLab;
	JTextField dez;
	JButton clear;


	public ZahlenView(ZahlenModel model){
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);

		//Hauptbox
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));


		//obere Box
		Box topBox = Box.createHorizontalBox();
		topBox.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		heading = new JLabel("Zahlendarstellung");
		heading.setAlignmentX(TOP_ALIGNMENT);

		topBox.add(heading);
		mainBox.add(topBox);

		//mittlere Box
		Box middleBox = Box.createHorizontalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		//3 Boxen, die in die mittlere gesetzt werden
		Box box1 = Box.createVerticalBox();
		box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dualLab = new JLabel("Zahl im Dualsystem");
		dual = new JTextField();
		dual.setPreferredSize(new Dimension(50, 20));
		box1.add(dualLab);
		box1.add(Box.createVerticalStrut(30));
		box1.add(dual);

		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		berechnen = new JButton("Umrechnen");
		box2.add(berechnen);

		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dezLab = new JLabel("Zahl im Dezimalsystem");
		dez = new JTextField();
		box3.add(dezLab);
		box3.add(Box.createVerticalStrut(30));
		box3.add(dez);

		//mittlere Box zusammenpacken und der oberen Box hinzufügen
		middleBox.add(box1);
		middleBox.add(box2);
		middleBox.add(box3);
		mainBox.add(middleBox);

		//untere Box
		Box bottomBox = Box.createHorizontalBox();
		bottomBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		clear = new JButton("Löschen");
		bottomBox.add(clear);
		mainBox.add(bottomBox);

		
	
	}
	
	private void readInput(){
		try {
			if(dez.getText() == null){
			model.setDual(Integer.valueOf(dual.getText()));
			model.dez2dual();
			}else if(dual.getText() ==null){
			 model.setDez(Integer.valueOf(dez.getText()));
			 model.dual2dez();
			}
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		} 		

		
	}

	public void clear(){
		dez.setText("");
		dual.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == berechnen) readInput();
		else if (e.getSource() ==clear) clear();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}





}
