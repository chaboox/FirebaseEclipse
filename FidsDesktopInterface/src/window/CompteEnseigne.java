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
import model.Transaction;
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
import javax.swing.JLabel;

public class CompteEnseigne extends JPanel {
	private JList list;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3;
	private DefaultListModel<Utilisateur> listModel;
	private DefaultListModel<Utilisateur> listModelSave;
	private JTextField search;
	private JTextPane dis, disM, colM, total, totalv, user, transa;
	private JTextPane col;
	private JTextPane solde;
	private double SumPlus = 0;
	private int heightImage = 65;
	private int widthImage = 65;
	private double sumMinus = 0;
	private double mySolde;
	private DefaultTableModel tableModel,tableModelT;
	private Double totalFidz = 0.0;
	private JTable table, tableT;
	private  DecimalFormat df2 = new DecimalFormat(".##");
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private Double totalDis, totalCol;
	
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
			totalDis =0.0;
			totalCol = 0.0;
		JButton btnNewButton = new JButton("Créer une annonce");
		btnNewButton.setBounds(505, 930, 200, 25);
		add(btnNewButton);
		
		JButton promotion = new JButton("Créer une promotion");
		promotion.setBounds(295, 930, 200, 25);
		add(promotion);
		
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
		user.setBounds(430, 320, 160, 19);
		user.setFont(ft);
		user.setText("Utilisateurs");
		user.setBackground(Color.decode("#EEEEEE"));
		add(user);
		
		transa = new JTextPane();
		transa.setEditable(false);
		transa.setBounds(430, 671, 160, 19);
		transa.setFont(ft);
		transa.setText("Transactions");
		transa.setBackground(Color.decode("#EEEEEE"));
		add(transa);
		
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
				 "Photo",
				 "Nom",
	                "Prénom",
	                "Identifiant",
	                "Email",
	                "Téléphone",
	                "Balance",
	                "Fidz collectés",
	                "Fidz distribués",
	                "Autres enseignes",
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
	                "Fidz collectés",
	                "Fidz distribués",
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
		
		  
		  //Table transaction
		  
		  String[] columnNamesT = {
					 "Opération",
					 "Montant",
		                "Utilisateur",
		                "Date",
		                };
		        Object[][] dataT =
		        {
		        };

			 DefaultTableModel modelT = new DefaultTableModel(dataT, columnNamesT)
		        {
		            //  Returning the Class of each column will allow different
		            //  renderers to be used based on Class
		            public Class getColumnClass(int column)
		            {
		                return getValueAt(0, column).getClass();
		            }
		        };
			
			
	tableT = new JTable(modelT);
		/*	
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nom",
		                "Prénom",
		                "Identifiant",
		                "Email",
		                "Téléphone",
		                "Balance",
		                "Fidz collectés",
		                "Fidz distribués",
		                "Autre enseignes"
		                	
				}
			));*/
			  tableModelT = (DefaultTableModel) tableT.getModel();
			  tableT.setRowHeight(30);
			  tableT.setFont(f3);
			  tableT.getTableHeader().setFont(f3);
		  
		  
		  
		  
		
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(43, 350, 900, 280);
		add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane(tableT);
		scrollPane_2.setBounds(43, 700, 900, 200);
		add(scrollPane_2);
		
		JTextPane namecompany = new JTextPane();
		namecompany.setEditable(false);
		namecompany.setText(MainFram.firebaseFunction.nameCompany);
		namecompany.setFont(ftitle);
		
		namecompany.setBackground(Color.decode("#EEEEEE"));
		namecompany.setBounds(400, 25, 487, 33);
		
		add(namecompany);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"}));
		comboBox.setSelectedIndex(8);
		comboBox.setBounds(43, 180, 110, 25);
		add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("item" + e.getItem());
				if(e.getItem().equals("Septembre")) {
					disM.setText("Fidz distribués : " + totalDis );
					colM.setText("Fidz collectés : " + totalCol );
				}
				
				else {
					disM.setText("Fidz distribués : 0" );
					colM.setText("Fidz collectés : 0" );
				}
			}
		});
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2018"}));
		comboBox_1.setBounds(160, 180, 90, 25);
		add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(250, 12, 90, 90);
		add(lblNewLabel);
		ImageIcon iconLogo = new ImageIcon("/home/mcq-1/eclipse-workspace/FidsDesktopInterface/logokiloutou.jpg");
		// In init() method write this code
		lblNewLabel.setIcon(ResizeImageLogo(iconLogo));
		
		
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
	promotion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					JFrame addAnnonce = new AddPromotion();
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
		populateFidz();
		populateTransaction();

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
						}
						
						@Override
						public void onChildChanged(DataSnapshot arg0, String arg1) {	
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
	
	public void populateTransaction() {
		MainFram.firebaseFunction.getTransactionRef().addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				System.out.println("HNA 1");
			}
			
			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("HNA 2");
			}
			
			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Populate list
				Transaction transaction = new Transaction(arg0);
				System.out.println("HNA 3");
				tableModelT.addRow(transaction.getRow());
				if(transaction.getTypeOperation().equals("Collecté"))
					totalCol += Double.parseDouble(transaction.getMontant());
				else 
					totalDis += Double.parseDouble(transaction.getMontant());
				dis.setText("Fidz distribués : " + totalDis);
				col.setText("Fidz collectés : " + totalCol);
					if(comboBox.getSelectedIndex() == 8) {
				disM.setText("Fidz distribués : " + totalDis);
				colM.setText("Fidz collectés : " + totalCol);}
					refreshDiff();
			}
			
			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("HNA 4" +arg0.getValue().toString() );
				
				Transaction transaction = new Transaction(arg0);
				tableModelT.addRow(transaction.getRow());
				if(transaction.getTypeOperation().equals("Collecté"))
					totalCol += Double.parseDouble(transaction.getMontant());
				else 
					totalDis += Double.parseDouble(transaction.getMontant());
				dis.setText("Fidz distribués : " + totalDis);
				col.setText("Fidz collectés : " + totalCol);
				refreshDiff();
				if(comboBox.getSelectedIndex() == 8) {
					disM.setText("Fidz distribués : " + totalDis);
					colM.setText("Fidz collectés : " + totalCol);}
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				System.out.println("HNA 5");
			}
		});
	}
	
	private void populateFidz() {
		MainFram.firebaseFunction.getAllcompanyRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				for(DataSnapshot dataSnapshot: arg0.getChildren()) {
					if(MainFram.firebaseFunction.getIdCompany().equals(dataSnapshot.getKey().toString())) {
						mySolde = Double.parseDouble(dataSnapshot.child("dis").getValue().toString()) 
								- Double.parseDouble(dataSnapshot.child("col").getValue().toString());
				dis.setText("Fidz distribués : " +dataSnapshot.child("dis").getValue().toString());
				col.setText("Fidz collectés : " + dataSnapshot.child("col").getValue().toString());
				disM.setText("Fidz distribués : 360" );
				colM.setText("Fidz collectés : 120" );
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
			refreshDiff();
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void refreshDiff() {
		Double di = totalDis - totalCol;
		if(di > 0) {
			totalv.setText("À verser : ");
			solde.setText( (df2.format((di*(-sumMinus))/SumPlus)) + " Fidz le 01/10/2018");}
		else { solde.setText( -di + " Fidz le 01/10/2018" );
		totalv.setText("À recevoir : ");}
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
								autre = autre + "Leroy Merlin - ";
							}
							else if (idC.equals("3")) {
								autre = autre + "Manpower - ";
							}
							else if (idC.equals("4")) {
								autre = autre + "Point.P - ";
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
	 public ImageIcon ResizeImageLogo(ImageIcon MyImage)
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
	        newImg = img.getScaledInstance(90,(int)((double)90 * (h/w)) , Image.SCALE_SMOOTH);
	        else 
	        	newImg = img.getScaledInstance((int)((double)90 * (w/h)) ,90 , Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        return image;
	    }
}
