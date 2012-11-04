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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class KorrektView extends JPanel implements ActionListener, Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Model model;
	private JLabel headline;
	private JLabel isbnLabel;
	private JFormattedTextField isbnField;
	private JButton prüfen;
	private JButton clear;
	private JTextField ergebnis;



	public KorrektView(Model model){
		this.model = model;

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
			MaskFormatter maskPrüf = new MaskFormatter("#-#####-###-#");
			isbnField = new JFormattedTextField(maskPrüf);
			isbnField.setText("1234567890");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		leftBox.add(isbnLabel);
		leftBox.add(Box.createVerticalStrut(20));
		leftBox.add(isbnField);

		prüfen = new JButton("Überprüfen");
		prüfen.setAlignmentX(CENTER_ALIGNMENT);
		clear = new JButton("clear");
		clear.setAlignmentX(CENTER_ALIGNMENT);
		
		rightBox.add(prüfen);
		rightBox.add(Box.createVerticalStrut(10));
		rightBox.add(clear);
		
		ergebnis = new JTextField("");
		ergebnis.setBackground(Color.cyan);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
