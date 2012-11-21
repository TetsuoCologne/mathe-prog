package CaesarViginere;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class KryptoView extends JPanel implements Observer, ActionListener{


	private JCheckBox caesar;
	private JCheckBox viginere;
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
	private CaesarModel caesarModel;
	private ViginereModel vigModel;
	private JScrollPane scrollpanePlain;
	private JScrollPane scrollpaneChiffre;



	public KryptoView(CaesarModel caModel, ViginereModel vigModel){
		this.caesarModel = caModel;
		this.vigModel = vigModel;



		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(10));

		Box heading = Box.createHorizontalBox();
		auswahl = new JLabel("Wählen Sie bitte eine Codierung:");
		heading.add(auswahl);

		Box firstBox = Box.createHorizontalBox();
		caesar = new JCheckBox("Caesar-Code", false);
		viginere = new JCheckBox("Viginere-Code", false);


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
		dechiffrieren = new JButton("Dechiffrieren");


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


	/**
	 * 
	 */
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

		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			String a="";

			while(a!=null){

				System.out.println(a);
				sb.append(a);
				a = br.readLine();

			}		
			br.close();


		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Die Datei existiert nicht!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Input Fehler");
		}

		return sb.toString().toLowerCase();
	}

	public String normalizeInput(String input){
		char [] zeichen = input.toCharArray();
		StringBuilder output = new StringBuilder();

		for(int i = 0;i<zeichen.length;i++){
			if(Character.isLetter(zeichen[i])){

				switch(zeichen[i]){
				case 'ß':output.append("ss");
				break;
				case 'ä': 	output.append("ae");
				break;
				case 'ö': 	output.append("oe");
				break;
				case 'ü':	output.append("ue");
				break;
				default:output.append(zeichen[i]);
				}
			}
		}
		return output.toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadKlartext){
			plainText.setText(normalizeInput(loadData()));
		}else if(e.getSource() == loadChiffretext){
			chiffreText.setText(normalizeInput(loadData()).toUpperCase());
		}
		else if(e.getSource() == clearPlain){
			plainText.setText("");
		}
		else if(e.getSource() == chiffreText){
			chiffreText.setText("");
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
