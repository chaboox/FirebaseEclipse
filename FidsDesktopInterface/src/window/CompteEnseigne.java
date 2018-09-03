package window;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import model.Article;
import model.Utilisateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;

public class CompteEnseigne extends JPanel {
	private JList list;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3;
	private DefaultListModel<Utilisateur> listModel;
	private DefaultListModel<Utilisateur> listModelSave;
	private JTextField search;
	private JTextPane dis, disM, colM, total, totalv, user;
	private JTextPane col;
	private JTextPane solde;
	private double SumPlus = 0;
	private int heightImage = 65;
	private int widthImage = 65;
	private double sumMinus = 0;
	private double mySolde;
	private DefaultTableModel tableModel,tableModelT;
	private Double totalFids = 0.0;
	private JTable table, tableT;
	private  DecimalFormat df2 = new DecimalFormat(".##");
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	/**
	 * Create the panel.
	 */
	public CompteEnseigne() {
		setLayout(null);
		setBackground(Color.decode("#EEEEEE"));
		 Font f = new Font(Font.SANS_SERIF, 0, 25);
		 Font f2 = new Font(Font.SANS_SERIF, 0, 21);
		 Font f3 = new Font(Font.SANS_SERIF, 0, 12);
		 Font ft = new Font(Font.SANS_SERIF,1, 17);
		 Font ftitle = new Font(Font.SANS_SERIF, 1, 32);
		listModel = new DefaultListModel();
		listModelSave = new DefaultListModel();
		list = new JList(listModel);
		    list.setBounds(33, 95, 160, 156);
		    scrollPane = new JScrollPane(list);
			scrollPane.setBounds(33, 95, 160, 156);
			//add(scrollPane);
			
		JButton btnNewButton = new JButton("Créer une annonce");
		btnNewButton.setBounds(400, 700, 200, 25);
		add(btnNewButton);
		
	/*	 JXDatePicker picker = new JXDatePicker();
		 picker.setBounds(0, 0, 100, 100  );
	        picker.setDate(Calendar.getInstance().getTime());
	        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
	        add(picker);*/
	        
		search = new JTextField();
		search.setBounds(33, 74, 160, 19);
		search.setText("Search");
		search.setForeground(Color.GRAY);
		//add(search);
		search.setColumns(10);
		
		total = new JTextPane();
		total.setEditable(false);
		total.setBounds(175, 130, 130, 19);
		total.setFont(ft);
		total.setText("Total : ");
		total.setBackground(Color.decode("#EEEEEE"));
		add(total);
		
		totalv = new JTextPane();
		totalv.setEditable(false);
		totalv.setBounds(153, 230, 130, 19);
		totalv.setFont(ft);
		totalv.setText("");
		totalv.setBackground(Color.decode("#EEEEEE"));
		add(totalv);
		
		user = new JTextPane();
		user.setEditable(false);
		user.setBounds(400, 307, 160, 19);
		user.setFont(ft);
		user.setText("Utilisateurs");
		user.setBackground(Color.decode("#EEEEEE"));
		add(user);
		
		dis = new JTextPane();
		dis.setEditable(false);
		dis.setBounds(307, 130, 160, 19);
		dis.setFont(f3);
		dis.setBackground(Color.decode("#EEEEEE"));
		add(dis);
		
		
		col = new JTextPane();
		col.setFont(f3);
		col.setEditable(false);
		col.setBounds(507, 130, 160, 19);
		col.setBackground(Color.decode("#EEEEEE"));
		add(col);
		
		
		disM = new JTextPane();
		disM.setEditable(false);
		disM.setBounds(307, 180, 160, 19);
		disM.setFont(f3);
		disM.setBackground(Color.decode("#EEEEEE"));
		add(disM);
		
		
		colM = new JTextPane();
		colM.setFont(f3);
		colM.setEditable(false);
		colM.setBounds(507, 180, 160, 19);
		colM.setBackground(Color.decode("#EEEEEE"));
		add(colM);
		
		
		solde = new JTextPane();
		solde.setEditable(false);
		solde.setBounds(307, 230, 500, 19);
		solde.setFont(f3);
		solde.setBackground(Color.decode("#EEEEEE"));
		add(solde);
		 String[] columnNames = {
				 "image",
				 "Nom",
	                "Prenom",
	                "Identifiant",
	                "Email",
	                "Téléphone",
	                "Balance",
	                "Fids collectés",
	                "Fids distribués",
	                "Autre enseignes",
	                };
	        Object[][] data =
	        {
	        };

		 DefaultTableModel model = new DefaultTableModel(data, columnNames)
	        {
	            //  Returning the Class of each column will allow different
	            //  renderers to be used based on Class
	            public Class getColumnClass(int column)
	            {
	                return getValueAt(0, column).getClass();
	            }
	        };
		
		
table = new JTable(model);
	/*	
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Nom",
	                "Prenom",
	                "Identifiant",
	                "Email",
	                "Téléphone",
	                "Balance",
	                "Fids collectés",
	                "Fids distribués",
	                "Autre enseignes"
	                	
			}
		));*/
		  tableModel = (DefaultTableModel) table.getModel();
		  table.setRowHeight(65);
		  table.setFont(f3);
		  table.getTableHeader().setFont(f3);
		    //tableModel.addRow(title);
		 
		  TableColumnModel tcm = table.getColumnModel();
		  //table.getColumn(0).setPreferredWidth(70);
		    //tableModel.addRow(title);
		  tcm.getColumn(0).setPreferredWidth(45);     //Name
		  tcm.getColumn(1).setPreferredWidth(30);    //prenom
		  tcm.getColumn(2).setPreferredWidth(40);    //username
		  tcm.getColumn(3).setPreferredWidth(45);//email
		  tcm.getColumn(4).setPreferredWidth(130);//phone
		  tcm.getColumn(5).setPreferredWidth(60);//balance
		  tcm.getColumn(6).setPreferredWidth(25);
		  tcm.getColumn(7).setPreferredWidth(60);
		  tcm.getColumn(8).setPreferredWidth(60);
		  tcm.getColumn(9).setPreferredWidth(150);
		
		
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(43, 350, 900, 268);
		add(scrollPane_1);
		
		JTextPane namecompany = new JTextPane();
		namecompany.setText(MainFram.firebaseFunction.nameCompany);
		namecompany.setFont(ftitle);
		
		namecompany.setBackground(Color.decode("#EEEEEE"));
		namecompany.setBounds(400, 12, 487, 33);
		
		add(namecompany);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"}));
		comboBox.setSelectedIndex(7);
		comboBox.setBounds(43, 180, 100, 25);
		add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("item" + e.getItem());
				if(e.getItem().equals("Août")) {
					disM.setText("Fids distribués : 360" );
					colM.setText("Fids collectés : 120" );
				}
				else if(e.getItem().equals("Juillet")) {
					disM.setText("Fids distribués : 123" );
					colM.setText("Fids collectés : 145" );
				}
				else {
					disM.setText("Fids distribués : 0" );
					colM.setText("Fids collectés : 0" );
				}
			}
		});
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2018"}));
		comboBox_1.setBounds(150, 180, 90, 25);
		add(comboBox_1);
		
		
		search.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("REM");
				
				listModel.removeAllElements();
				for(int i = 0 ; i < listModelSave.getSize() ; i++)
					if(listModelSave.getElementAt(i).toString().toLowerCase().contains(search.getText().toLowerCase()))
						listModel.addElement(listModelSave.getElementAt(i));
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("UPD");
				if(!search.getText().equals("Search")) {
				listModel.removeAllElements();
				for(int i = 0 ; i < listModelSave.getSize() ; i++)
					if(listModelSave.getElementAt(i).toString().toLowerCase().contains(search.getText().toLowerCase()))
						listModel.addElement(listModelSave.getElementAt(i));
			}}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("CHA");
			}
		});
		search.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(search.getText().equals("")) {
				search.setText("Search");
				search.setForeground(Color.GRAY);}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(search.getText().equals("Search")) {
				search.setText("");
				search.setForeground(Color.BLACK);}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					JFrame addAnnonce = new AddAnnonce();
					addAnnonce.setVisible(true);
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        		System.out.println("C2");
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            JFrame detailUser = new DetailUser(listModel.elementAt(index));
		            detailUser.setVisible(true);
		        } else if (evt.getClickCount() == 3) {

	        		System.out.println("C3");
		            // Triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
		populateUser();
		populateFids();

	}
	private void populateUser() {
		System.out.println("STEP1");
		MainFram.firebaseFunction.getUsersRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				System.out.println("STEP2");
				for(DataSnapshot dataSnapshot : arg0.getChildren()) {
					System.out.println("PATH " + dataSnapshot.getKey().toString() + " " );
					MainFram.firebaseFunction.getCompanyUserRef().child(dataSnapshot.getKey().toString()).
					child(MainFram.firebaseFunction.getIdCompany()).addChildEventListener(new ChildEventListener() {
						
						@Override
						public void onChildRemoved(DataSnapshot arg0) {
							// TODO Auto-generated method stub
							System.out.println(dataSnapshot.getKey().toString());
							for(int i = 0 ; i < listModel.getSize() ; i++)
								if(listModel.getElementAt(i).getId().equals(dataSnapshot.getKey().toString())) {
									listModel.removeElementAt(i);
									i--;}
							for(int i = 0 ; i < listModelSave.getSize() ; i++)
								if(listModelSave.getElementAt(i).getId().equals(dataSnapshot.getKey().toString())) {
									listModelSave.removeElementAt(i);
									i--;}
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
							System.out.println("STEP3");
							Utilisateur u = new Utilisateur(dataSnapshot);
							listModel.addElement(u);
							tableModel.addRow(u.getRow());
							populateAutreEns(tableModel,tableModel.getRowCount()-1  , 9, u.getId());
							populateImageUsers(tableModel,tableModel.getRowCount()-1, 0, u.getImageUrl());
							listModelSave.addElement(u);
							//
						}
						
						@Override
						public void onCancelled(DatabaseError arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				//	listModel.addElement(new Utilisateur(dataSnapshot));					
					}
				//for(int i = 0 ; i < listModel.getSize() ; i++)
					//listModelSave.addElement(listModel.getElementAt(i));
			
			}
		
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void populateFids() {
		MainFram.firebaseFunction.getAllcompanyRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				for(DataSnapshot dataSnapshot: arg0.getChildren()) {
					if(MainFram.firebaseFunction.getIdCompany().equals(dataSnapshot.getKey().toString())) {
						mySolde = Double.parseDouble(dataSnapshot.child("dis").getValue().toString()) 
								- Double.parseDouble(dataSnapshot.child("col").getValue().toString());
				dis.setText("Fids distribués : " +dataSnapshot.child("dis").getValue().toString() );
				col.setText("Fids collectés : " + dataSnapshot.child("col").getValue().toString() );
				disM.setText("Fids distribués : 360" );
				colM.setText("Fids collectés : 120" );
					}
					if((Double.parseDouble(dataSnapshot.child("dis").getValue().toString()) 
							- Double.parseDouble(dataSnapshot.child("col").getValue().toString()) )> 0)
						SumPlus += Double.parseDouble(dataSnapshot.child("dis").getValue().toString()) 
								- Double.parseDouble(dataSnapshot.child("col").getValue().toString());
					else 
						sumMinus += Double.parseDouble(dataSnapshot.child("dis").getValue().toString()) 
						- Double.parseDouble(dataSnapshot.child("col").getValue().toString());
				}
			if(mySolde > 0) {
				totalv.setText("À verser : ");
				solde.setText( (df2.format((mySolde*(-sumMinus))/SumPlus)) + " Fidz le 01/10/2018");}
			else { solde.setText( -mySolde + " Fidz le 01/10/2018" );
			totalv.setText("À recevoir : ");}
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void populateAutreEns(DefaultTableModel listModel, int row, int column, String idUser) {
		MainFram.firebaseFunction.getCompanyUserRef().child(idUser).addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
					String autre = "";
					for(DataSnapshot child2 : arg0.getChildren()) {
						String idC =child2.getKey().toString();
						if(! child2.getKey().toString().equals(MainFram.firebaseFunction.getIdCompany())) {
							if(idC.equals("1"))
							autre = autre + "Kiloutou - ";
							else if (idC.equals("2")) {
								autre = autre + "Leroy Merlin -";
							}
							else if (idC.equals("3")) {
								autre = autre + "Manpower -";
							}
							else if (idC.equals("4")) {
								autre = autre + "Point.P -";
							}
						
						
					}
					
							
				}	if(autre.length() > 2) autre = autre.substring(0, autre.length()-2);
					listModel.setValueAt(autre, row, column);;
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	 public void populateImageUsers(DefaultTableModel listModel, int row, int column, String imageUrl) {
		 Image image = null;
	        try {
	            URL url = new URL(imageUrl);
	            image = ImageIO.read(url);
	           // listModel.setValueAt(new ImageIcon(image), row, column);;
	            Icon icon = ResizeImage(new ImageIcon(image));
	            listModel.setValueAt(icon, row, column);;
	           
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	 }
	 public ImageIcon ResizeImage(ImageIcon MyImage)
	    {
	       // ImageIcon MyImage = new ImageIcon(ImagePath);
	        Image img = MyImage.getImage();
	        double w = img.getWidth(new ImageObserver() {
				
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					// TODO Auto-generated method stub
					return false;
				}
			});
	        double h = img.getHeight(new ImageObserver() {
				
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					// TODO Auto-generated method stub
					return false;
				}
			});
	        Image newImg;
	        if(w > h ) 
	        newImg = img.getScaledInstance(widthImage,(int)((double)heightImage * (h/w)) , Image.SCALE_SMOOTH);
	        else 
	        	newImg = img.getScaledInstance((int)((double)widthImage * (w/h)) ,heightImage , Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        return image;
	    }
}
