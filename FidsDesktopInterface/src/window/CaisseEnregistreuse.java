package window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;


import model.Article;
import model.FirebaseFunction;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CaisseEnregistreuse extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JList list;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3;
	private DefaultListModel<Article> listModel;
	private JButton ajouter;
	
	private Double totalFids = 0.0;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public CaisseEnregistreuse() throws IOException {
		setLayout(null);
		 listModel = new DefaultListModel();
		  
		list = new JList(listModel);
	    list.setBounds(33, 75, 160, 156);
		textPane = new JTextPane();
		textPane.setBounds(33, 243, 160, 19);
		textPane.setText(" À payer : " + sumPrice(listModel) + " €");
	 	add(textPane);
	 	
	 	textPane2 = new JTextPane();
		textPane2.setBounds(33, 268, 160, 19);
		textPane2.setText("Payé en Fids : 0.0 ");
	 	add(textPane2);
	 	
		textPane3 = new JTextPane();
		textPane3.setBounds(33, 293, 160, 19);
		textPane3.setText("Reste à payer : 0.0 €");
	 	add(textPane3);
		
		textField = new JTextField();
		textField.setBounds(33, 27, 143, 19);
		add(textField);
		textField.setColumns(10);
			
		textField_1 = new JTextField();
		textField_1.setBounds(180, 27, 114, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane textFieldP = new JTextPane();
		textFieldP.setEditable(false);
		textFieldP.setBounds(33, 7, 143, 19);
		textFieldP.setBackground(Color.decode("#EEEEEE"));
		textFieldP.setText("Article");
		add(textFieldP);
		
			
		JTextPane textField_1P = new JTextPane();
		textField_1P.setEditable(false);
		textField_1P.setBounds(180, 7, 114, 19);
		textField_1P.setText("Prix");
		textField_1P.setBackground(Color.decode("#EEEEEE"));
		add(textField_1P);
		
		
		ajouter = new JButton("Ajouter");
		ajouter.setBounds(303, 27, 117, 19);
		add(ajouter);
	
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(33, 75, 160, 156);
		add(scrollPane);
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 1) {
		        		System.out.println("C2");
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            listModel.remove(index);
		            textPane.setText(" À payer : " + sumPrice(listModel) + " €");
					textPane3.setText("Reste à payer : " + (sumPrice(listModel)- totalFids*2) + " €");
		            
		        } 
		    }
		});
		
		MainFram.firebaseFunction.getfactureRef().addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				for(DataSnapshot child: arg0.getChildren()) {
					totalFids += Double.parseDouble(child.getValue().toString());
					textPane2.setText("Payé en Fids : " + totalFids);		
					textPane3.setText("Reste à payer : " + (sumPrice(listModel) - totalFids*2) + " €");
					arg0.getRef().removeValueAsync();
					
				}
				
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
					// add data
					//listModel.addElement(textField.getText() + " " + textField_1.getText() + " €");
					listModel.addElement(new Article(textField.getText(),Double.parseDouble(textField_1.getText())));
					textField.setText("");
					textField_1.setText("");
					textPane.setText(" À payer : " + sumPrice(listModel) + " €");
					textPane3.setText("Reste à payer : " + (sumPrice(listModel)- totalFids*2) + " €");
					textField.requestFocus();
					
					
				}
			}
		});

	}
	public double sumPrice(DefaultListModel<Article> list) {
		double sum = 0 ; 
		for(int i = 0 ; i < list.size() ; i++) {
			sum += list.get(i).getPrice();
		}
		return sum;
	}
	public JButton getAjoutButton() {
		return ajouter;
	}
}
