package Zahlendarstellung;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * 	@author Marc Fiedler, Franziska Krebs
 * 
 * 	Diese Klasse erstellt die View für den ersten Tab.
 */

public class ZahlenPanel extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	// Anzeigeobjekte
	private ZahlenModel model;
	private JLabel heading;
	private JButton berechnen;
	private JLabel eingabeLab;
	private JTextField eingabeField;
	private JLabel ausgabeLab;
	private JTextField ausgabeField;
	private JButton clear;
	private JLabel baseEingabeLab;
	private JComboBox baseEingabe;
	private JLabel baseAusgabeLab;
	private JComboBox baseAusgabe;
	private String [] exampleBases = {"0","1","2","3","4","5","6","7"};



	//Konstruktor
	public ZahlenPanel(ZahlenModel model){
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

		//mittlere Box (besteht aus 3 einzelnen Boxen)
		Box middleBox = Box.createHorizontalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		//3 Boxen, die in die mittlere gesetzt werden
		Box box1 = Box.createVerticalBox();
		box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		eingabeLab = new JLabel("Zahl eingeben:");
		eingabeLab.setAlignmentX(CENTER_ALIGNMENT);
		eingabeField = new JTextField();
		baseEingabeLab = new JLabel("Basis wählen:");
		baseEingabeLab.setAlignmentX(CENTER_ALIGNMENT);
		baseEingabe = new JComboBox(exampleBases);
		baseEingabe.setEditable(true);
		baseEingabe.addActionListener(this);


		box1.add(eingabeLab);
		box1.add(Box.createVerticalStrut(10));
		box1.add(eingabeField);
		box1.add(Box.createVerticalStrut(10));
		box1.add(baseEingabeLab);
		box1.add(Box.createVerticalStrut(10));
		box1.add(baseEingabe);


		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		berechnen = new JButton("Umrechnen");
		berechnen.addActionListener(this);
		box2.add(berechnen);

		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		ausgabeLab = new JLabel("Ergebnis der Umrechung");
		ausgabeLab.setAlignmentX(CENTER_ALIGNMENT);
		ausgabeField = new JTextField();
		baseAusgabe = new JComboBox(exampleBases);
		baseAusgabe.setEditable(true);
		baseAusgabe.addActionListener(this);
		baseAusgabeLab = new JLabel("Basis wählen:");
		baseAusgabeLab.setAlignmentX(CENTER_ALIGNMENT);

		box3.add(ausgabeLab);
		box3.add(Box.createVerticalStrut(10));
		ausgabeField.setEditable(false);
		box3.add(ausgabeField);
		box3.add(Box.createVerticalStrut(10));
		box3.add(baseAusgabeLab);
		box3.add(Box.createVerticalStrut(10));
		box3.add(baseAusgabe);

		//mittlere Box zusammenpacken und der Hauotbox hinzufügen
		middleBox.add(box1);
		middleBox.add(Box.createHorizontalGlue());
		middleBox.add(box2);
		middleBox.add(Box.createHorizontalGlue());
		middleBox.add(box3);

		mainBox.add(middleBox);

		//untere Box erstellen
		Box bottomBox = Box.createHorizontalBox();
		bottomBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 50));
		clear = new JButton("Löschen");
		clear.addActionListener(this);
		bottomBox.add(clear);


		//untere Box der Hauptbox hinzufügen
		mainBox.add(bottomBox);



		this.add(mainBox);

	}

	private void readInput(){
		try {

			model.setBaseEingabe(Integer.valueOf((String) (baseEingabe.getSelectedItem())));
			model.setBaseAusgabe(Integer.valueOf((String)(baseAusgabe.getSelectedItem())));
			model.setEingabe(String.valueOf(eingabeField.getText()));
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		} 		
		catch(FalscheEingabeException fe){
			JOptionPane.showMessageDialog(this, "Der eingegebene String passt nicht zur Basis");
		}


	}

	private void clear(){

		ausgabeField.setText("");
		eingabeField.setText("");
		baseAusgabe.setSelectedIndex(0);
		baseEingabe.setSelectedIndex(0);

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
