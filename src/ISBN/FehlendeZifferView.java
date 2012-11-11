package ISBN;

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
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.MaskFormatter;

public class FehlendeZifferView extends JPanel implements ActionListener,Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FehlendeZifferModel model;
	private JLabel heading;
	private JLabel isbnLabel;
	private JFormattedTextField isbnField;
	private JButton compute;
	private JButton clear;
	private JLabel digitLabel;
	private JFormattedTextField digitField;


	
	public FehlendeZifferView(FehlendeZifferModel model){
		this.model = model;
		model.addObserver(this);
		
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
		
		isbnLabel = new JLabel("Für die fehlende Ziffer bitte ein '*' eingeben:");
		
		try {
			MaskFormatter mask = new MaskFormatter("*-*****-***-*");
			mask.setValidCharacters("0123456789xX*");
			mask.setPlaceholderCharacter('0');
			isbnField = new JFormattedTextField(mask);
			isbnField.setText("*123456789X");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		leftMiddleBox.add(isbnLabel);
		leftMiddleBox.add(Box.createVerticalStrut(20));
		leftMiddleBox.add(isbnField);
		isbnField.setMaximumSize(new Dimension(300, 20));
	
		compute = new JButton("Berechnen");
		compute.addActionListener(this);
		clear = new JButton("clear");
		clear.addActionListener(this);
		
		rightMiddleBox.add(compute);
		rightMiddleBox.add(Box.createVerticalStrut(10));
		rightMiddleBox.add(clear);
		
		digitLabel = new JLabel("Ergebnis:");
		digitLabel.setAlignmentX(CENTER_ALIGNMENT);
		digitField = new JFormattedTextField();
		digitField.setAlignmentX(CENTER_ALIGNMENT);
		digitField.setEditable(false);

	
		lowerBox.add(digitLabel);
		lowerBox.add(Box.createVerticalStrut(10));
		lowerBox.add(digitField);
		digitField.setMaximumSize(new Dimension(100, 20));
		
		
		middleBox.add(leftMiddleBox);
		middleBox.add(rightMiddleBox);
	
		mainBox.add(middleBox);
		mainBox.add(lowerBox);
		
		this.add(mainBox);
	
		
	}
	
	public void readInput(){
		
		isbnField.setText(isbnField.getText().toUpperCase());
		model.setInput(isbnField.getText());
		model.computeMissingDigit();
	
		try {
			model.checkEingabe();
		} catch (FalscheEingabeException fee) {
			JOptionPane.showMessageDialog(this, fee.getMessage());
		}
		
	}
	
	public void clear(){
		isbnField.setText("*123456789X");
		digitField.setText("");
	}
	@Override
	public void update(Observable o, Object arg) {
		digitField.setText(model.getResult());
		
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



}
