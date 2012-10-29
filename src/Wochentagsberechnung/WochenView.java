package Wochentagsberechnung;

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
import javax.swing.border.Border;

public class WochenView extends JPanel implements ActionListener,Observer{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private WochenModel model;
	private JLabel dayLabel;
	private JTextField dayField;
	private JLabel monthLabel;
	private JTextField monthField;
	private JLabel yearLabel;
	private JTextField yearField;
	private JLabel wochentag;
	private JTextField wochentagsField;
	private JButton compute;
	private JButton clear;
	
	
	public WochenView(WochenModel model){
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		//Hauptbox
		
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
		
		//obere Box, bestehend aus 3 Einzelboxen
		Box upperBox = Box.createHorizontalBox();
		upperBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
		//linke Box
		Box leftBox = Box.createVerticalBox();
		leftBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dayLabel = new JLabel("Tag:");
		dayField = new JTextField();
		leftBox.add(dayLabel);
		leftBox.add(dayField);
		dayField.setPreferredSize(new Dimension(40, 20));
		
		//mittlere Box
		Box middleBox = Box.createVerticalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		monthLabel = new JLabel("Monat:");
		monthField = new JTextField();
		middleBox.add(monthLabel);
		middleBox.add(monthField);
		monthField.setPreferredSize(new Dimension(40, 20));
		
		//rechte Box
		Box rightBox = Box.createVerticalBox();
		rightBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		yearLabel = new JLabel("Jahr:");
		yearField = new JTextField();
		rightBox.add(yearLabel);
		rightBox.add(yearField);
		yearField.setPreferredSize(new Dimension(40,20));
		
		//ganz rechte Box mit Wochentag
		Box wochenBox = Box.createVerticalBox();
		wochenBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		wochentag = new JLabel("Wochentag:");
		wochentagsField = new JTextField();
		wochenBox.add(wochentag);
		wochenBox.add(wochentagsField);
		wochentagsField.setEditable(false);

		
		upperBox.add(leftBox);
		upperBox.add(middleBox);
		upperBox.add(rightBox);
		upperBox.add(wochenBox);
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
		compute = new JButton("Berechnen");
		compute.addActionListener(this);
		clear = new JButton("clear");
		clear.addActionListener(this);
		
		buttonBox.add(compute);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(clear);
		
		
		mainBox.add(upperBox);
		mainBox.add(buttonBox);
		this.add(mainBox);
		
	}
	public void readInput(){
		try{
			model.setTag(Integer.valueOf(dayField.getText()));
			model.setMonat(Integer.valueOf(monthField.getText()));
			model.setJahrKomplett(yearField.getText());
			model.wochenTagsBerechnung();
			
			
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(this, "Eingabefehler");
		}
	}

	public void clear(){
		dayField.setText(" ");
		monthField.setText(" ");
		yearField.setText(" ");
		wochentagsField.setText(" ");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == compute){
			readInput();
		}
		else if(e.getSource() == clear){
			clear();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		wochentagsField.setText(model.getWochentag());
		
	}

	/**
	 * @param args
	 */
	
	

}
