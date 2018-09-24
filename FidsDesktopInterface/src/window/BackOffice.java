package window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import model.Company;
import model.Transaction;
import model.Utilisateur;

public class BackOffice extends JPanel {
	private JList list;
	private  Map<String, ImageIcon> imageMap;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3, user, transa, ens;
	private DefaultListModel<Utilisateur> listModel;
	private DefaultListModel<Utilisateur> listModelSave;
	private DefaultListModel<model.Company> companies;
	private int heightImage = 65;
	private int widthImage = 65;
	private JTextField search;
	private double SumPlus = 0;
	private DefaultTableModel tableModel,tableModelC, tableModelT;
	private Double totalFids = 0.0;
	private JTable table, tableC, tableT;
	private double sumMinus = 0;
	private  DecimalFormat df2 = new DecimalFormat(".##");
	/**
	 * Create the panel.
	 */
	public BackOffice() {
		setLayout(null);
		setBackground(Color.decode("#EEEEEE"));
			
			Font f = new Font(Font.SANS_SERIF, 0, 25);
			Font f2 = new Font(Font.SANS_SERIF, 0, 21);
			Font f3 = new Font(Font.SANS_SERIF, 0, 15);
			Font f3s = new Font(Font.SANS_SERIF, 0, 12);
			Font ft = new Font(Font.SANS_SERIF,1, 17);
			Font ftitle = new Font(Font.SANS_SERIF, 1, 32);
	        listModel = new DefaultListModel();
	        companies = new DefaultListModel();
	        listModelSave = new DefaultListModel();
	        list = new JList(listModel);
		    list.setBounds(33, 95, 160, 156);
		    scrollPane = new JScrollPane(list);
			scrollPane.setBounds(33, 95, 160, 156);
		//	add(scrollPane);
			search = new JTextField();
			search.setBounds(33, 74, 160, 19);
			search.setText("Search");
			search.setForeground(Color.GRAY);
			//add(search);
			search.setColumns(10);
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(250, 12, 90, 90);
			add(lblNewLabel);
			ImageIcon iconLogo = new ImageIcon("/home/mcq-1/eclipse-workspace/FidsDesktopInterface/logokiloutou.jpg");
			// In init() method write this code
			lblNewLabel.setIcon(ResizeImageLogo(iconLogo));
			
			JLabel lblNewLabel2 = new JLabel("");
			lblNewLabel2.setBounds(370, 12, 90, 90);
			add(lblNewLabel2);
			ImageIcon iconLogo2 = new ImageIcon("/home/mcq-1/eclipse-workspace/FidsDesktopInterface/lo.png");
			// In init() method write this code
			lblNewLabel2.setIcon(ResizeImageLogo(iconLogo2));
			
			
			JLabel lblNewLabel3 = new JLabel("");
			lblNewLabel3.setBounds(490, 12, 90, 90);
			add(lblNewLabel3);
			ImageIcon iconLogo3 = new ImageIcon("/home/mcq-1/eclipse-workspace/FidsDesktopInterface/man.png");
			// In init() method write this code
			lblNewLabel3.setIcon(ResizeImageLogo(iconLogo3));
			
			JLabel lblNewLabel4 = new JLabel("");
			lblNewLabel4.setBounds(610, 12, 90, 90);
			add(lblNewLabel4);
			ImageIcon iconLogo4 = new ImageIcon("/home/mcq-1/eclipse-workspace/FidsDesktopInterface/p.jpeg");
			// In init() method write this code
			lblNewLabel4.setIcon(ResizeImageLogo(iconLogo4));
			
			
			user = new JTextPane();
			user.setEditable(false);
			user.setBounds(400, 660, 160, 19);
			user.setFont(ft);
			user.setText("Utilisateurs");
			user.setBackground(Color.decode("#EEEEEE"));
			add(user);
			
			transa = new JTextPane();
			transa.setEditable(false);
			transa.setBounds(430, 371, 160, 19);
			transa.setFont(ft);
			transa.setText("Transactions");
			transa.setBackground(Color.decode("#EEEEEE"));
			add(transa);
			ens = new JTextPane();
			ens.setEditable(false);
			ens.setBounds(420, 110, 160, 39);
			ens.setFont(ft);
			ens.setText("Enseignes");
			ens.setBackground(Color.decode("#EEEEEE"));
			add(ens);
			
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
			
			
			  tableModel = (DefaultTableModel) table.getModel();
			  table.setRowHeight(65);
			  table.setFont(f3s);
			  table.getTableHeader().setFont(f3s);
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
			scrollPane_1.setBounds(43, 700, 900, 268);
			add(scrollPane_1);
			
			
			tableC = new JTable();
			
			tableC.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nom",
		                "Nombre utilisateurs",
		                "Distribué",
		                "Collecté",
		                "Différence" 	
				}
			));
			  tableModelC = (DefaultTableModel) tableC.getModel();
			  tableC.setRowHeight(30);
			  tableC.setFont(f3);
			  tableC.getTableHeader().setFont(f3);
			    //tableModel.addRow(title);
			  JScrollPane scrollPane_c = new JScrollPane(tableC);
			  scrollPane_c.setBounds(43, 150, 900, 145);
			  add(scrollPane_c);
			
			  
			  
			  //Table transaction
			  
			  String[] columnNamesT = {"Enseigne",
						 "Opération",
						 "Montant",
			                "Utilisateur",
			                "Date",
			                };
			        Object[][] dataT =
			        {
			        };

				 DefaultTableModel modelT = new DefaultTableModel(dataT, columnNamesT);
			        
				
				
		tableT = new JTable(modelT);
			
				  tableModelT = (DefaultTableModel) tableT.getModel();
				  tableT.setRowHeight(30);
				  tableT.setFont(f3);
				  tableT.getTableHeader().setFont(f3);
			  
			  
			  
			  
			
			
			
			JScrollPane scrollPane_2 = new JScrollPane(tableT);
			scrollPane_2.setBounds(43, 400, 900, 200);
			add(scrollPane_2);
			
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
			populateCompany();
			populateTransaction();

	}
	private void populateUser() {
		System.out.println("STEP11");
		MainFram.firebaseFunction.getUsersRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				System.out.println("STEP22");
				for(DataSnapshot dataSnapshot : arg0.getChildren()) {
					if(!dataSnapshot.getKey().toString().equals("1")) {
					Utilisateur u = new Utilisateur(dataSnapshot);
					listModel.addElement(u);
					tableModel.addRow(u.getRow());
					populateAutreEns(tableModel, tableModel.getRowCount()-1 ,9,u.getId());
					populateImageUsers(tableModel,tableModel.getRowCount()-1, 0, u.getImageUrl());
					
					
					}
					}
				for(int i = 0 ; i < listModel.getSize() ; i++)
					listModelSave.addElement(listModel.getElementAt(i));
			
			}
		
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void populateCompany()
	{
		
		MainFram.firebaseFunction.getAllcompanyRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
					for(DataSnapshot dataSnapshot : arg0.getChildren()) {
						companies.addElement(new Company(dataSnapshot));
					}
					for(int i = 0; i < companies.size(); i++) {
					//	companies.get(i).initiatNumberUser();
						if(companies.get(i).getDif() > 0)
							SumPlus += companies.get(i).getDif();
						else sumMinus += companies.get(i).getDif(); 
					}
					
					
					for(int i = 0; i < companies.size(); i++) {
					if(companies.get(i).getDif() > 0)
						//companies.get(i).setCompensation("À verser : " + (df2.format(	(companies.get(i).getDif()*(-sumMinus))/SumPlus)));
						companies.get(i).setCompensation("" + (df2.format(	(companies.get(i).getDif()*(-sumMinus))/SumPlus)));
					else //companies.get(i).setCompensation("À recevoir : " + -companies.get(i).getDif() );
						companies.get(i).setCompensation("" + companies.get(i).getDif() );
						}
					  String[] nameList = new String[companies.getSize()];
					  for(int i = 0; i < companies.size(); i++) {
						  nameList[i] = companies.getElementAt(i).toString();
						  tableModelC.addRow(companies.get(i).getRow());
						  companies.get(i).initiatNumberUser(tableModelC, i, 1);}
				        imageMap = createImageMap(companies);
				        JList list2 = new JList(nameList);
				        MarioListRenderer marioListRenderer = new MarioListRenderer();
				       // marioListRenderer.getComponent(0).setBackground(Color.BLACK);;
				       list2.setCellRenderer(marioListRenderer);
				        JScrollPane scrollPane_1 = new JScrollPane(list2);
						scrollPane_1.setBounds(43, 265, 500, 170);
						//add(scrollPane_1);
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private Map<String, ImageIcon> createImageMap(DefaultListModel<Company> comp) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
        	for(int i = 0 ; i < comp.getSize(); i++)
            map.put(comp.getElementAt(i).toString(), ResizeImage(new ImageIcon(new URL(comp.getElementAt(i).getImageUrl()))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("STEP4");
        return map;
    }
	
	 public class MarioListRenderer extends DefaultListCellRenderer {

	        Font font = new Font("helvitica", Font.BOLD, 24);

	        @Override
	        public Component getListCellRendererComponent(
	                JList list, Object value, int index,
	                boolean isSelected, boolean cellHasFocus) {

	            JLabel label = (JLabel) super.getListCellRendererComponent(
	                    list, value, index, isSelected, cellHasFocus);
	            label.setIcon(imageMap.get((String) value));
	            label.setHorizontalTextPosition(JLabel.RIGHT);
	           // label.setFont(font);
	            return label;
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
	 public void populateAutreEns(DefaultTableModel listModel, int row, int column, String idUser) {
			MainFram.firebaseFunction.getCompanyUserRef().child(idUser).addListenerForSingleValueEvent(new ValueEventListener() {
				
				@Override
				public void onDataChange(DataSnapshot arg0) {
					// TODO Auto-generated method stub
					
						String autre = "";
						for(DataSnapshot child2 : arg0.getChildren()) {
							String idC =child2.getKey().toString();
						
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
	 public void populateTransaction() {
			MainFram.firebaseFunction.getAllTransactionRef().addListenerForSingleValueEvent(new ValueEventListener() {
				
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					// TODO Auto-generated method stub
					for(DataSnapshot arg2 : dataSnapshot.getChildren()) {
						arg2.getRef().addChildEventListener(new ChildEventListener() {
							
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
								Transaction transaction = new Transaction(arg0, arg2.getKey().toString());
								System.out.println("HNA 3");
								tableModelT.addRow(transaction.getRowPlus());
							 
							}
							
							@Override
							public void onChildAdded(DataSnapshot arg0, String arg1) {
								// TODO Auto-generated method stub
								System.out.println("HNA 4" +arg0.getValue().toString() );
								
								Transaction transaction = new Transaction(arg0, arg2.getKey().toString());
								tableModelT.addRow(transaction.getRowPlus());
							
							}
							
							@Override
							public void onCancelled(DatabaseError arg0) {
								// TODO Auto-generated method stub
								System.out.println("HNA 5");
							}
						});
					}
				}
				
				@Override
				public void onCancelled(DatabaseError arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			
			
			
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
