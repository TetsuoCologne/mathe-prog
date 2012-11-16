package ISBN;


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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
/**
 * 	Diese Klasse erstellt die View für die Prüfziffer - Berechnung.
 */
public class PrüfzifferView extends JPanel implements ActionListener,Observer{


	private static final long serialVersionUID = 1L;

	private PruefzifferModel model;
	private JLabel headline;
	private JLabel isbnLabel;
	private JFormattedTextField isbnField;
	private JLabel pruefLabel;
	private JTextField pruefField;
	private JButton compute;
	private JButton clear;

	
	
	public PrüfzifferView(PruefzifferModel model){
		
		this.model = model;
		model.addObserver(this);
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		Box middleBox = Box.createHorizontalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		Box leftMiddleBox = Box.createVerticalBox();
		leftMiddleBox.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
		
		Box rightMiddleBox = Box.createVerticalBox();
		rightMiddleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 30));
		
		Box headerBox = Box.createHorizontalBox();
		headerBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		Box korrektBox = Box.createVerticalBox();
		korrektBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		headline = new JLabel("Prüfziffer einer ISBN-10 berechnen");
		headline.setAlignmentX(CENTER_ALIGNMENT);
		headerBox.add(headline);
		
		isbnLabel = new JLabel("ISBN eingeben:");
		try {
			MaskFormatter mask = new MaskFormatter("#-#####-###-");
			mask.setPlaceholderCharacter('0');
			isbnField = new JFormattedTextField(mask);
			isbnField.setText("123456789");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		leftMiddleBox.add(isbnLabel);
		leftMiddleBox.add(Box.createVerticalStrut(10));
		leftMiddleBox.add(isbnField);

		
		pruefLabel =new JLabel("Prüfziffer:");
		pruefField = new JTextField();
		pruefField.setEditable(false);
		rightMiddleBox.add(pruefLabel);
		rightMiddleBox.add(Box.createVerticalStrut(10));
		rightMiddleBox.add(pruefField);
		
		middleBox.add(leftMiddleBox);
		middleBox.add(rightMiddleBox);
		
		compute = new JButton("Berechnen");
		compute.addActionListener(this);
		clear = new JButton("clear");
		clear.addActionListener(this);
		buttonBox.add(compute);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(clear);

		
		mainBox.add(headerBox);
		mainBox.add(middleBox);
		mainBox.add(buttonBox);

		
		
		this.add(mainBox);
	}
	
	public void readInput(){
		model.setInput(isbnField.getText());
		model.berechnePruefziffer();
	}
	
	public void clear(){
		isbnField.setText("123456789");
		pruefField.setText("");
	}
	@Override
	public void update(Observable o, Object arg) {
		pruefField.setText(String.valueOf(model.getErgebnisZiffer()));
		
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
