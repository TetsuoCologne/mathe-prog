package Zahlendarstellung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 	Modulare Potenz View
 * @author franzi
 *
 */
public class PotenzPanel extends JPanel implements ActionListener,Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel aLab;
	private JLabel nLab;
	private JLabel mLab;
	private JLabel ausgabeLab;
	private JTextField aField;
	private JTextField nField;
	private JTextField mField;
	private JTextField ausgabeField;
	private JButton compute;
	private PotenzModel model;
	private char c = '\u207F';
	
	public PotenzPanel(PotenzModel model){
		this.model =model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		
		//Hauptbox
		Box mainBox = Box.createHorizontalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(30,50, 30, 50));
		
		
		Box leftBox = Box.createVerticalBox();
		leftBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		aLab = new JLabel("a");
		aField = new JTextField();
		aField.setPreferredSize(new Dimension(100, 20));
		nLab = new JLabel("n");
		nField = new JTextField();
		nField.setPreferredSize(new Dimension(100, 20));
		mLab = new JLabel("m");
		mField = new JTextField();
		mField.setPreferredSize(new Dimension(100, 20));
		
		
		leftBox.add(aLab);
		leftBox.add(aField);
		leftBox.add(Box.createVerticalStrut(10));
		leftBox.add(nLab);
		leftBox.add(nField);
		leftBox.add(Box.createVerticalStrut(10));
		leftBox.add(mLab);
		leftBox.add(mField);
		leftBox.add(Box.createVerticalStrut(10));
		
		//mittlere Box 
		Box middleBox = Box.createVerticalBox();
		middleBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		compute = new JButton("Berechnen");
		compute.addActionListener(this);
		middleBox.add(compute);
		
		//rechte Box
		
		Box rightBox = Box.createVerticalBox();
		rightBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		ausgabeLab = new JLabel("a" + c +" mod m");
		ausgabeField = new JTextField();
		ausgabeField.setMaximumSize(new Dimension(200, 20));
		ausgabeField.setEditable(false);
		
		rightBox.add(ausgabeLab);
		rightBox.add(Box.createVerticalStrut(10));
		rightBox.add(ausgabeField);
		
		
		
		mainBox.add(leftBox);
		mainBox.add(middleBox);
		mainBox.add(rightBox);
		this.add(mainBox);
	}

	@Override
	public void update(Observable o, Object arg) {
		ausgabeField.setText(String.valueOf(model.getErgebnis()));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == compute){
			readInput();
		}
		
	}
	
	private  void readInput(){
		try{
			model.setFaktor(Integer.valueOf(aField.getText()));
			model.setExponent(Integer.valueOf(nField.getText()));
			model.setMod(Integer.valueOf(mField.getText()));
			model.modularesPotenzieren();
		}
		catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(this, "Keine Buchstaben als Eingabe!" + nfe.getMessage());
		}
		catch(FalscheEingabeException fee){
			JOptionPane.showMessageDialog(this, fee.getMessage());
		}
		
	}
	
	

}
