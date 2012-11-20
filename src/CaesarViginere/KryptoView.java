package CaesarViginere;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private JScrollPane scrollpane;

	
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
		plainText = new JTextArea();
		plainText.setPreferredSize(new Dimension(200, 200));
		plainText.setLineWrap(true);
		
		Box midMiddleBox = Box.createVerticalBox();
		
//		ImageIcon icon = new ImageIcon("PfeilRechts.jpg");
//		icon.setImage(icon.getImage().getScaledInstance(100	, 100, Image.SCALE_DEFAULT));
		chiffrieren = new JButton("Chiffrieren");
		
		dechiffrieren = new JButton("Dechiffrieren");
		
		
		midMiddleBox.add(chiffrieren);
		midMiddleBox.add(Box.createVerticalStrut(10));
		midMiddleBox.add(dechiffrieren);
		
		chiffreText = new JTextArea();
		chiffreText.setPreferredSize(new Dimension(200, 200));
		chiffreText.setLineWrap(true);
		
		middleBox.add(plainText);
		middleBox.add(Box.createHorizontalStrut(20));
		middleBox.add(midMiddleBox);
		middleBox.add(Box.createHorizontalStrut(20));
		middleBox.add(chiffreText);
		
		Box lowerBox = Box.createHorizontalBox();
		clearPlain = new JButton("clear");
		clearChiffre = new JButton("clear");
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

	public void loadData(){
		File currentDir = null;
		chooser = new JFileChooser(currentDir);
		chooser.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
		chooser.showOpenDialog(null);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadKlartext){
			loadData();
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
