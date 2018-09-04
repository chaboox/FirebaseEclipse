package window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import model.Article;
import model.FirebaseFunction;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CaisseEnregistreuse extends JPanel {
	private JTextField textField;
	private JTextField textField_1, textField_2;
	private JList list;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3, textPane4;
	private DefaultListModel<Article> listModel;
	private JButton ajouter;
	private DefaultTableModel tableModel,tableModelT;
	private Double totalFidz = 0.0;
	private JTable table, tableT;
	private  DecimalFormat df2 = new DecimalFormat(".##");
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public CaisseEnregistreuse() throws IOException {
		setLayout(null);
		 listModel = new DefaultListModel();
		 Font f = new Font(Font.SANS_SERIF, 0, 25);
		 Font f2 = new Font(Font.SANS_SERIF, 0, 21);
		list = new JList(listModel);
	    list.setBounds(33, 75, 160, 156);
		textPane = new JTextPane();
		
		textPane.setBounds(33, 243, 160, 19);
		textPane.setText(" À payer : " + sumPrice(listModel) + " €");
	 	//add(textPane);
	 	
	 	textPane2 = new JTextPane();
	 	textPane2.setEditable(false);
		textPane2.setBounds(33, 568, 500, 29);
		textPane2.setText("Payé en Fidz : 0.0 ");
		textPane2.setBackground(Color.decode("#EEEEEE"));
		textPane2.setFont(f2);
	 	add(textPane2);
	 	
		textPane3 = new JTextPane();
		textPane3.setEditable(false);
		textPane3.setBounds(33, 603, 500, 29);
		textPane3.setFont(f2);
		textPane3.setBackground(Color.decode("#EEEEEE"));
		textPane3.setText("Reste à payer : 0.0 €");
	 	add(textPane3);
	 	
	 	textPane4 = new JTextPane();
		textPane4.setEditable(false);
		textPane4.setBounds(33, 638, 500, 29);
		textPane4.setFont(f2);
		textPane4.setBackground(Color.decode("#EEEEEE"));
		textPane4.setText("Fidz gagnés : 0.0 ");
	 	add(textPane4);
		
		textField = new JTextField();
		textField.setFont(f2);
		textField.setBounds(33, 37, 243, 29);
		add(textField);
		textField.setColumns(10);
			
		textField_1 = new JTextField();
		textField_1.setFont(f2);
		textField_1.setBounds(280, 37, 214, 29);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		textField_2 = new JTextField();
		textField_2.setFont(f2);
		textField_2.setBounds(497, 37, 214, 29);
		add(textField_2);
		textField_2.setColumns(10);
		
		JTextPane textFieldP = new JTextPane();
		textFieldP.setEditable(false);
		textFieldP.setBounds(33, 7, 143, 29);
		textFieldP.setBackground(Color.decode("#EEEEEE"));
		textFieldP.setFont(f);
		textFieldP.setText("Article");
		add(textFieldP);
		
			
		JTextPane textField_1P = new JTextPane();
		textField_1P.setEditable(false);
		textField_1P.setBounds(280, 7, 114, 29);
		textField_1P.setFont(f);
		textField_1P.setText("Prix");
		textField_1P.setBackground(Color.decode("#EEEEEE"));
		add(textField_1P);
		
		JTextPane textField_2P = new JTextPane();
		textField_2P.setEditable(false);
		textField_2P.setBounds(497, 7, 114, 29);
		textField_2P.setFont(f);
		textField_2P.setText("Nombre");
		textField_2P.setBackground(Color.decode("#EEEEEE"));
		add(textField_2P);
		
		ajouter = new JButton("Ajouter");
		ajouter.setBounds(717, 37, 217, 29);
		ajouter.setFont(f2);
		add(ajouter);
	
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(33, 75, 160, 156);
		//add(scrollPane);
		String title [] =  {"Produit",
                "Nombre",
                "H.T",
                "T.T.C"
                };
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Produit",
	                "Nombre",
	                "H.T",
	                "T.T.C"
			}
		));
		  tableModel = (DefaultTableModel) table.getModel();
		  table.setRowHeight(30);
		  table.setFont(f2);
		  table.getTableHeader().setFont(f);
		    //tableModel.addRow(title);
		table.setBounds(205, 75, 221, 156);
		 JScrollPane sp = new JScrollPane(table);
		 sp.setBounds(33, 75, 900, 456);
	        add(sp);
	        
	//add(table);
		
	        
	        
	        tableT = new JTable();
	        
			tableT.setModel(new DefaultTableModel(
				new Object[][] {
					{"Total", null, "0.0", "0.0"},
				},
				new String[] {
					"New column", "New column", "New column", "New column"
				}
			));
			  tableModelT = (DefaultTableModel) tableT.getModel();

			    //tableModel.addRow(title);
			tableT.setBounds(205, 375, 221, 30);
			tableT.setTableHeader(null);
			tableT.setFont(f2);
			// tableT.setRowHeight(0, 29);
			 tableT.setRowHeight(27);
			 JScrollPane spT = new JScrollPane(tableT);
			 spT.setBounds(33, 531, 900, 30);
		        add(spT);
	        
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 1) {
		        		System.out.println("C2");
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            listModel.remove(index);
		            textPane.setText(" À payer : " + sumPrice(listModel) + " €");
					textPane3.setText("Reste à payer : " + df2.format(sumPrice(listModel)- totalFidz*0.5) + " €");
					textPane4.setText("Fidz gagnés : " +( df2.format((sumPrice(listModel)- totalFidz*0.5)/10)));
		            
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
				System.out.println("SCAN");
				for(DataSnapshot child: arg0.getChildren()) {
					if(arg0.getKey().toString().equals("-1")) {
					totalFidz += Double.parseDouble(child.getValue().toString());
					textPane2.setText("Payé en Fidz : " + totalFidz);		
					textPane3.setText("Reste à payer : " + df2.format(sumPrice(tableModel, 3 ) - totalFidz*0.5) + " €");
					textPane4.setText("Fidz gagnés : " + (df2.format((sumPrice(tableModel, 3 ) - totalFidz*0.5)/10)));
					arg0.getRef().removeValueAsync();}
					else {System.out.println(child.getValue().toString());
						MainFram.firebaseFunction.getProduitRef().child(child.getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
						
						@Override
						public void onDataChange(DataSnapshot arg1) {
							arg0.getRef().removeValueAsync();
							// TODO Auto-generated method stub
							int f = -1;
							for(int i = 0 ; i < tableModel.getRowCount(); i++)
							if(arg1.child("nom").getValue().toString().equals(tableModel.getValueAt(i, 0)))
								f = i;
							if(f == -1)
							tableModel.addRow(new String[] {arg1.child("nom").getValue().toString(),"1",arg1.child("prix").getValue().toString() + "",df2.format(Double.parseDouble(arg1.child("prix").getValue().toString())*1.2) + ""});
							
							else {
								tableModel.insertRow(f, new String[] {(String) tableModel.getValueAt(f, 0),""+(Integer.parseInt((String)tableModel.getValueAt(f, 1))+1), ""+
										df2.format((Double.parseDouble((String)tableModel.getValueAt(f, 2)))*(Integer.parseInt((String)tableModel.getValueAt(f, 1))+1)/(Integer.parseInt((String)tableModel.getValueAt(f, 1)))), ""+
									df2.format((Double.parseDouble((String)tableModel.getValueAt(f, 3)))*(Integer.parseInt((String)tableModel.getValueAt(f, 1))+1)/(Integer.parseInt((String)tableModel.getValueAt(f, 1))))});
								tableModel.removeRow(f+1);
								//tableModel.setValueAt(Integer.parseInt((String)tableModel.getValueAt(f, 1))+1, f, 1);
							//tableModel.setValueAt((Double.parseDouble((String)tableModel.getValueAt(f, 1))+1)* (Double.parseDouble((String)tableModel.getValueAt(f, 2))), f, 2);
							//tableModel.setValueAt(0, f, 2);
							//tableModel.setValueAt(2* (Double.parseDouble((String)tableModel.getValueAt(f, 2))), f, 2);
							//tableModel.setValueAt(2* (Double.parseDouble((String)tableModel.getValueAt(f, 3))), f, 3);
							
							System.out.println("2  =" + (Double.parseDouble((String)tableModel.getValueAt(f, 1))+1));
							}
							double sumPay = sumPrice(tableModel, 3 );
							textPane3.setText("Reste à payer : " + df2.format(sumPay- totalFidz*0.5) + " €");
							textPane4.setText("Fidz gagnés : " +( df2.format((sumPay- totalFidz*0.5)/10)));
							tableModelT.setValueAt(sumPrice(tableModel, 2 ), 0, 2);
							//tableModelT.setValueAt(", 0, 2);
							tableModelT.setValueAt(sumPay, 0, 3);
							//table.setModel(tableModel);
							System.out.println("cpt + "+ Integer.parseInt((String)tableModel.getValueAt(f, 1)));
							System.out.println("COUNT " +tableModel.getRowCount());
							textPane.setText(" À payer : " + sumPrice(listModel) + " €");
							
							//arg0.getRef().removeValueAsync();
						}
						
						@Override
						public void onCancelled(DatabaseError arg0) {
							// TODO Auto-generated method stub
							
						}
					});
					}	
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
				if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("")) {
					// add data
					//listModel.addElement(textField.getText() + " " + textField_1.getText() + " €");
					listModel.addElement(new Article(textField.getText(),Double.parseDouble(textField_1.getText())));
					tableModel.addRow(new String[] {textField.getText(),textField_2.getText(),Double.parseDouble(textField_1.getText()) + "",df2.format(Double.parseDouble(textField_1.getText())*1.2) + ""});
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textPane.setText(" À payer : " + sumPrice(listModel) + " €");
					Double sumPay = sumPrice(tableModel, 3 );
					textPane3.setText("Reste à payer : " +df2.format (sumPay- totalFidz*0.5) + " €");
					textPane4.setText("Fidz gagnés : " +( df2.format((sumPay- totalFidz*0.5)/10)));
					
					tableModelT.setValueAt(sumPrice(tableModel, 2 ), 0, 2);
					//tableModelT.setValueAt(", 0, 2);
					tableModelT.setValueAt(sumPay, 0, 3);
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
	public double sumPrice(DefaultTableModel model, int column) {
		double sum = 0 ; 
		for(int i = 0 ; i < model.getRowCount() ; i++) {
			sum +=Double.parseDouble( (String) model.getValueAt(i, column));
		}
		return sum;
	}
	public JButton getAjoutButton() {
		return ajouter;
	}
	
}
