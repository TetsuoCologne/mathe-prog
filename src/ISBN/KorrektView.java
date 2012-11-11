package ISBN;

import java.awt.Color;
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
import javax.swing.text.MaskFormatter;

import Wochentagsberechnung.FalscherMonatException;
import Wochentagsberechnung.FalschesJahrException;
import Wochentagsberechnung.TagNichtImMonatException;

public class KorrektView extends JPanel implements ActionListener, Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private KorrekteISBNModel model;
	private JLabel headline;
	private JLabel isbnLabel;
	private JFormattedTextField isbnField;
	private JButton pruefen;
	private JButton clear;
	private JTextField ergebnis;



	public KorrektView(KorrekteISBNModel model){
		this.model = model;
		model.addObserver(this);

		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Box middleBox = Box.createHorizontalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		Box leftBox = Box.createVerticalBox();
		leftBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Box rightBox = Box.createVerticalBox();
		rightBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Box lowerBox = Box.createVerticalBox();
		lowerBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		headline = new JLabel("Korrektheitsprüfung einer ISBN-10");
		headline.setAlignmentX(CENTER_ALIGNMENT);

		mainBox.add(headline);

		isbnLabel = new JLabel("ISBN eingeben:");
		try {
			MaskFormatter mask = new MaskFormatter("#-#####-###-A");
			mask.setValidCharacters("0123456789Xx");
			mask.setPlaceholderCharacter('0');
			isbnField = new JFormattedTextField(mask);
			isbnField.setText("1234567890");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		leftBox.add(isbnLabel);
		leftBox.add(Box.createVerticalStrut(20));
		leftBox.add(isbnField);

		pruefen = new JButton("Überprüfen");
		pruefen.setAlignmentX(CENTER_ALIGNMENT);
		pruefen.addActionListener(this);
		clear = new JButton("clear");
		clear.addActionListener(this);
		clear.setAlignmentX(CENTER_ALIGNMENT);

		rightBox.add(pruefen);
		rightBox.add(Box.createVerticalStrut(10));
		rightBox.add(clear);

		ergebnis = new JTextField("");
		ergebnis.setBackground(Color.cyan);
		ergebnis.addActionListener(this);
		ergebnis.setEditable(false);


		middleBox.add(leftBox);
		middleBox.add(rightBox);

		mainBox.add(middleBox);
		lowerBox.add(ergebnis);
		mainBox.add(lowerBox);

		this.add(mainBox);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(model.getCheck()){
			ergebnis.setBackground(Color.green);
			ergebnis.setText("Korrekte ISBN!");
		}
		else {
			ergebnis.setBackground(Color.red);
			ergebnis.setText("Nicht korrekte ISBN!");
		}	

	}

	public void readInput(){
		isbnField.setText(isbnField.getText().toUpperCase());
		model.setInput(isbnField.getText());
		model.checkISBN();
		

	}

	public void clear(){
		ergebnis.setBackground(Color.cyan);
		isbnField.setText("1234567890");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pruefen){
			readInput();
			
			
		}
		else clear();


	}

}
