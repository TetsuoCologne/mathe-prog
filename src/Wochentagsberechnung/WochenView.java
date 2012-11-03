package Wochentagsberechnung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
/**
 * 
 * @author Marc Fiedler, Franziska Krebs
 * 	Diese Klasse erstellt das Fenster.
 *
 */
public class WochenView extends JPanel implements ActionListener,Observer{

	
	private static final long serialVersionUID = 1L;
	private WochenModel model;
	private JLabel wochentag;
	private JLabel date;
	private JFormattedTextField dateField;
	private JTextField wochentagsField;
	private JButton compute;
	private JButton clear;
	
	
	public WochenView(WochenModel model){
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		//Hauptbox
		
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		//obere Box, bestehend aus 3 Einzelboxen
		Box upperBox = Box.createHorizontalBox();
		upperBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
		//linke Box
		Box leftBox = Box.createVerticalBox();
		leftBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		date = new JLabel("Datum");
		try {
			MaskFormatter mask = new MaskFormatter("##.##.####");
			mask.setPlaceholderCharacter('0');
			dateField = new JFormattedTextField(mask);
			dateField.setText("01011700");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leftBox.add(date);
		leftBox.add(dateField);
		
		//rechte Box mit Wochentag
		Box rightBox = Box.createVerticalBox();
		rightBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		wochentag = new JLabel("Wochentag:");
		wochentagsField = new JTextField();
		rightBox.add(wochentag);
		rightBox.add(wochentagsField);
		wochentagsField.setMaximumSize((new Dimension(200, 20)));
		wochentagsField.setEditable(false);

		
		upperBox.add(leftBox);
		upperBox.add(rightBox);
		
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
			model.setEingabeKomplett(dateField.getText());
			model.wochenTagsBerechnung();
		
		}
		catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(this, "Eingabefehler");
			dateField.setText("01011700");
		}
		catch(TagNichtImMonatException kse){
			JOptionPane.showMessageDialog(this, kse.getMessage());
			
		} catch (FalschesJahrException fje) {
			JOptionPane.showMessageDialog(this, fje.getMessage());
			
		} catch (FalscherMonatException fme) {
			JOptionPane.showMessageDialog(this, fme.getMessage());
		}
	}

	public void clear(){
		dateField.setText("01011700");
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


	

}
