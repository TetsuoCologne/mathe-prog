package FiedlerKrebsCaesarViginere;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * 	Diese Klasse stellt die View dar und baut das Fenster auf.
 * @author Marc Fielder, Franziska Krebs
 *
 */
public class KryptoView extends JPanel implements Observer, ActionListener{


	private ButtonGroup bgroup;
	private JRadioButton caesar;
	private JRadioButton viginere;
	private JLabel auswahl;
	private JButton loadKlartext;
	private JButton loadChiffretext;
	private JLabel plainTextLabel;
	private JLabel chiffreTextLabel;
	private JTextArea plainText;
	private JTextArea chiffreText;
	private JButton chiffrieren;
	private JButton dechiffrieren;
	private JButton clearPlain;
	private JButton clearChiffre;
	private JLabel keyLabel;
	private JTextField keyField;
	private JFileChooser chooser;
	private FileNameExtensionFilter filter;
	private Verschluesselung model;
	private JScrollPane scrollpanePlain;
	private JScrollPane scrollpaneChiffre;



	public KryptoView(Verschluesselung model){

		this.model = model;
		model.addObserver(this);

		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(10));

		Box heading = Box.createHorizontalBox();
		auswahl = new JLabel("Wählen Sie bitte eine Codierung:");
		heading.add(auswahl);

		Box firstBox = Box.createHorizontalBox();
		caesar = new JRadioButton("Caesar-Code", false);
		viginere = new JRadioButton("Viginere-Code", false);
		bgroup = new ButtonGroup();
		bgroup.add(caesar);
		bgroup.add(viginere);

		firstBox.add(caesar);
		firstBox.add(Box.createHorizontalStrut(100));
		firstBox.add(viginere);

		Box upperBox = Box.createHorizontalBox();
		loadKlartext = new JButton("Klartext laden");
		loadKlartext.addActionListener(this);
		loadChiffretext = new JButton("Chiffretext laden");
		loadChiffretext.addActionListener(this);
		upperBox.add(loadKlartext);
		upperBox.add(Box.createHorizontalStrut(90));
		upperBox.add(loadChiffretext);

		Box secondBox = Box.createHorizontalBox();
		keyLabel = new JLabel("Schlüssel eingeben:");
		keyField = new JTextField();
		keyField.setToolTipText("Hier sind nur Buchstaben erlaubt");
		secondBox.add(keyLabel);
		secondBox.add(Box.createHorizontalStrut(20));
		secondBox.add(keyField);

		Box thirdBox = Box.createHorizontalBox();
		plainTextLabel = new JLabel("Klartext");
		chiffreTextLabel = new JLabel("Chiffretext");


		thirdBox.add(plainTextLabel);
		thirdBox.add(Box.createHorizontalStrut(200));
		thirdBox.add(chiffreTextLabel);


		Box middleBox = Box.createHorizontalBox();
		Box midMiddleBox = Box.createVerticalBox();

		chiffrieren = new JButton("Chiffrieren");
		chiffrieren.addActionListener(this);
		dechiffrieren = new JButton("Dechiffrieren");
		dechiffrieren.addActionListener(this);

		midMiddleBox.add(chiffrieren);
		midMiddleBox.add(Box.createVerticalStrut(10));
		midMiddleBox.add(dechiffrieren);

		chiffreText = new JTextArea();
		chiffreText.setLineWrap(true);
		scrollpaneChiffre = new JScrollPane(chiffreText);
		scrollpaneChiffre.setPreferredSize(new Dimension(200, 200));

		plainText = new JTextArea();
		plainText.setLineWrap(true);
		scrollpanePlain = new JScrollPane(plainText);
		scrollpanePlain.setPreferredSize(new Dimension(200, 200));

		middleBox.add(scrollpanePlain);
		middleBox.add(Box.createHorizontalStrut(20));
		middleBox.add(midMiddleBox);
		middleBox.add(Box.createHorizontalStrut(20));
		middleBox.add(scrollpaneChiffre);

		Box lowerBox = Box.createHorizontalBox();
		clearPlain = new JButton("clear");
		clearPlain.addActionListener(this);
		clearChiffre = new JButton("clear");
		clearChiffre.addActionListener(this);
		lowerBox.add(clearPlain);
		lowerBox.add(Box.createHorizontalStrut(100));
		lowerBox.add(clearChiffre);


		mainBox.add(heading);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(firstBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(upperBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(secondBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(thirdBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(middleBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(lowerBox);
		this.add(mainBox);	


	}

	private static final long serialVersionUID = 1L;

	public String loadData(){
		File currentDir = null;
		filter = new FileNameExtensionFilter("Text", "txt");
		chooser = new JFileChooser(currentDir);
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.showOpenDialog(null);
		StringBuilder sb = new StringBuilder();

		File input = chooser.getSelectedFile();
		if(input!=null){
			try {
				BufferedReader br = new BufferedReader(new FileReader(input));
				String a="";

				while(a!=null){

					sb.append(a);
					a = br.readLine();

				}		
				br.close();

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(this, "Die Datei existiert nicht!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Input Fehler");
			}
		}

		return sb.toString().toLowerCase();

	}

	/**
	 * 	Setzt die Felder für die Dechiffrierung von Viginere.
	 * 	Die normalisierten Texte werden wieder in die Eingabefelder geschrieben.
	 * 	@throws EingabeException Wird hochpropagiert von den Settern, wenn eine Eingabe leer oder ungültig
	 * 	sein sollte.
	 */
	public void readInputDechiffrierenViginere() throws EingabeException{
		model.setKeyViginere(keyField.getText());
		model.setChiffrat(chiffreText.getText());
	}

	/**
	 * 	Setzt die Felder für die Chiffrierung von Viginere.
	 * 	Die normalisierten Texte werden wieder in die Eingabefelder geschrieben.
	 * 	@throws EingabeException Wird hochpropagiert von den Settern, wenn eine Eingabe leer oder ungültig
	 * 	sein sollte.
	 */
	public void readInputChiffrierenViginere() throws EingabeException{
		model.setKeyViginere(keyField.getText());
		model.setPlain(plainText.getText());

	}

	/**
	 * 	Setzt die Felder für die Chiffrierung von Caesar.
	 * 	Die normalisierten Texte werden wieder in die Eingabefelder geschrieben.
	 * 	@throws EingabeException Wird hochpropagiert von den Settern, wenn eine Eingabe leer oder ungültig
	 * 	sein sollte.
	 */
	public void readInputChiffrierenCaesar() throws EingabeException{
		model.setKeyCaesar(keyField.getText());
		model.setPlain(plainText.getText());

	}

	/**
	 * 	Setzt die Felder für die Dechiffrierung von Caesar.
	 * 	Die normalisierten Texte werden wieder in die Eingabefelder geschrieben.
	 * 	@throws EingabeException Wird hochpropagiert von den Settern, wenn eine Eingabe leer oder ungültig
	 * 	sein sollte.
	 * 	
	 */
	public void readInputDechiffrierenCaesar() throws EingabeException{

		model.setKeyCaesar(keyField.getText());
		model.setChiffrat(chiffreText.getText());

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loadKlartext){
			plainText.setText(loadData());
		}
		if(e.getSource() == loadChiffretext){
			chiffreText.setText(loadData().toUpperCase());
		}
		if(e.getSource() == clearPlain){
			plainText.setText("");
		}

		if(e.getSource() == clearChiffre){
			chiffreText.setText("");
		}
		if(e.getSource() == chiffrieren){
			if(viginere.isSelected()){
				try {
					readInputChiffrierenViginere();
					plainText.setText(model.getPlain());
					keyField.setText(model.getKeyViginere());
					model.chiffrierenViginere();

				} catch (EingabeException ee) {
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}

			}
			else if(caesar.isSelected()){
				try {
					readInputChiffrierenCaesar();
					plainText.setText(model.getPlain());
					keyField.setText(String.valueOf(model.getKeyCaesar()));
					model.chiffrierenCaesar();

				} catch (EingabeException ee) {
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}

			}
			else JOptionPane.showMessageDialog(this, "Bitte wählen Sie einen Algorithmus aus!");

		}
		else if (e.getSource() == dechiffrieren){
			if(viginere.isSelected()){
				try {
					readInputDechiffrierenViginere();
					chiffreText.setText(model.getChiffrat());
					keyField.setText(model.getKeyViginere());
					model.dechiffrierenViginere();

				} catch (EingabeException ee) {
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}

			}
			else if(caesar.isSelected()){
				try {
					readInputDechiffrierenCaesar();
					chiffreText.setText(model.getChiffrat());
					keyField.setText(String.valueOf(model.getKeyCaesar()));
					model.dechiffrierenCaesar();
				} catch (EingabeException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}

			}	
			else JOptionPane.showMessageDialog(this, "Bitte wählen Sie einen Algorithmus aus!");

		}	


	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(caesar.isSelected()){
			chiffreText.setText(model.getChiffrat());
			plainText.setText(model.getPlain());

		}if(viginere.isSelected()){
			chiffreText.setText(model.getChiffrat());
			plainText.setText(model.getPlain());
		}


	}

}
