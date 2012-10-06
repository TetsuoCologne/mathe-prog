package Euklid;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Exceptions.ZeroException;

public class EuDView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	EuDModel model;
	
	JButton compute = new JButton("Berechnen");

	JTextField a = new JTextField("",10);
	JTextField b = new JTextField("",10);
	JTextField g = new JTextField("",6);
	JTextField ba = new JTextField("", 5);
	JTextField bb = new JTextField("", 5);

	public EuDView(EuDModel model)  {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 50));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		a.setAlignmentX(LEFT_ALIGNMENT);
		box.add(a);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		b.setAlignmentX(LEFT_ALIGNMENT);
		box.add(b);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(compute);
		add(box);		
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  ggt"));		
		box2.add(Box.createVerticalStrut(5));
		g.setAlignmentX(LEFT_ALIGNMENT);
		g.setEditable(false);
		box2.add(g);
		add(box2);		
		
		Box box3 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		box3.add(new JLabel("  Bezout-Koeffizient von a "));
		box3.add(Box.createVerticalStrut(10));
		ba.setAlignmentX(LEFT_ALIGNMENT);
		bb.setEditable(false);
		box3.add(ba);
		add(box3);
		
		

	}

	private void readInput(){
		try {
			model.setA(Integer.valueOf(a.getText()));
			model.setB(Integer.valueOf(b.getText()));
			model.ggt();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		} 		
		catch (ZeroException ze){
			JOptionPane.showMessageDialog(this, "ggt(0,0) ist nichts definiert");
	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute) readInput();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		g.setText(model.getGgt()+"");
	}
}



