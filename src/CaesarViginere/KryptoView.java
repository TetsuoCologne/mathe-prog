package CaesarViginere;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private CaesarModel caesarModel;
	private ViginereModel vigModel;
	private JScrollPane scrollpanePlain;
	private JScrollPane scrollpaneChiffre;



	public KryptoView(CaesarModel caModel, ViginereModel vigModel){
		this.caesarModel = caModel;
		this.vigModel = vigModel;
		caModel.addObserver(this);
		vigModel.addObserver(this);


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
		chiffreText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!chiffreText.getText().equals("")){
					plainText.setEditable(false);
					plainText.setBackground(Color.lightGray);
				}
				else{
					plainText.setEditable(true);
					plainText.setBackground(Color.white);
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		chiffreText.setLineWrap(true);
		scrollpaneChiffre = new JScrollPane(chiffreText);
		scrollpaneChiffre.setPreferredSize(new Dimension(200, 200));

		plainText = new JTextArea();
		plainText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(!plainText.getText().equals("")){
					chiffreText.setEditable(false);
					chiffreText.setBackground(Color.lightGray);
				}
				else{
					chiffreText.setEditable(true);
					chiffreText.setBackground(Color.white);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
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

	public void readInputDechiffrierenViginere() throws EingabeException{
		vigModel.setKey(keyField.getText());
		vigModel.setChiffrat(chiffreText.getText());
	}

	public void readInputChiffrierenViginere() throws EingabeException{
		vigModel.setKey(keyField.getText());
		vigModel.setPlain(plainText.getText());

	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loadKlartext){
			if(!chiffreText.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Es kann nur eine Eingabe erfolgen!");
			else plainText.setText(loadData());
		}else if(e.getSource() == loadChiffretext){
			if(!plainText.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Es kann nur eine Eingabe erfolgen!");
			else chiffreText.setText(loadData().toUpperCase());
		}
		else if(e.getSource() == clearPlain){
			plainText.setText("");
			plainText.setEditable(true);
			plainText.setBackground(Color.white);
		}
		else if(e.getSource() == clearChiffre){
			chiffreText.setText("");
			chiffreText.setEditable(true);
			chiffreText.setBackground(Color.white);
		}
		else if(e.getSource() == chiffrieren){
			if(viginere.isSelected()){
				try {
					readInputChiffrierenViginere();
					plainText.setText(vigModel.getPlain());
					keyField.setText(vigModel.getKey());
					vigModel.chiffrieren();

				} catch (EingabeException ee) {
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}

			}
			else if(caesar.isSelected()){

			}
			else JOptionPane.showMessageDialog(this, "Bitte wählen Sie einen Algorithmus aus!");

		}
		else if (e.getSource() == dechiffrieren){
			if(viginere.isSelected()){
				try {
					readInputDechiffrierenViginere();
					chiffreText.setText(vigModel.getChiffrat());
					keyField.setText(vigModel.getKey());
					vigModel.dechiffrieren();

				} catch (EingabeException ee) {
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}

			}
			else if(caesar.isSelected()){

			}	
			else JOptionPane.showMessageDialog(this, "Bitte wählen Sie einen Algorithmus aus!");

		}	


	}

	@Override
	public void update(Observable arg0, Object arg1) {
		chiffreText.setText(vigModel.getChiffrat());
		plainText.setText(vigModel.getPlain());


	}

}
