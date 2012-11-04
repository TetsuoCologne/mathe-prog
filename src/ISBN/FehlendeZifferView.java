package ISBN;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FehlendeZifferView extends JPanel implements ActionListener,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Model model;
	private JLabel heading;
	private JLabel isbnLabel;
	private JFormattedTextField isbnField;
	private JButton berechnen;
	private JButton clear;
	private JLabel ergebnisLabel;
	private JFormattedTextField ergebnisField;
	
	public FehlendeZifferView(Model model){
		this.model = model;
		
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box middleBox = Box.createHorizontalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box leftMiddleBox = Box.createVerticalBox();
		leftMiddleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box rightMiddleBox = Box.createVerticalBox();
		rightMiddleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Box lowerBox = Box.createVerticalBox();
		lowerBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		heading = new JLabel("Ergänzen der fehlenden Ziffer:");
		heading.setAlignmentX(CENTER_ALIGNMENT);
		mainBox.add(heading);
		
		
		isbnLabel = new JLabel("Für die fehlende Ziffer bitte ein 'X' eingeben:");
		isbnField = new JFormattedTextField();
		leftMiddleBox.add(isbnLabel);
		leftMiddleBox.add(Box.createVerticalStrut(20));
		leftMiddleBox.add(isbnField);
		
		berechnen = new JButton("Berechnen");
		berechnen.addActionListener(this);
		clear = new JButton("clear");
		clear.addActionListener(this);
		
		rightMiddleBox.add(berechnen);
		rightMiddleBox.add(Box.createVerticalStrut(10));
		rightMiddleBox.add(clear);
		
		ergebnisLabel = new JLabel("Ergebnis:");
		ergebnisLabel.setAlignmentX(CENTER_ALIGNMENT);
		ergebnisField = new JFormattedTextField();
		ergebnisField.setAlignmentX(CENTER_ALIGNMENT);
		
		lowerBox.add(ergebnisLabel);
		lowerBox.add(Box.createVerticalStrut(10));
		lowerBox.add(ergebnisField);
		ergebnisField.setMaximumSize(new Dimension(100, 20));
		
		
		middleBox.add(leftMiddleBox);
		middleBox.add(rightMiddleBox);
	
		mainBox.add(middleBox);
		mainBox.add(lowerBox);
		
		this.add(mainBox);
		
	
	
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}
