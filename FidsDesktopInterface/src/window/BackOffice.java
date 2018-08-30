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
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import model.Company;
import model.Utilisateur;

public class BackOffice extends JPanel {
	private JList list;
	private  Map<String, ImageIcon> imageMap;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3;
	private DefaultListModel<Utilisateur> listModel;
	private DefaultListModel<Utilisateur> listModelSave;
	private DefaultListModel<model.Company> companies;
	private int heightImage = 45;
	private int widthImage = 45;
	private JTextField search;
	private double SumPlus = 0;
	private double sumMinus = 0;
	private  DecimalFormat df2 = new DecimalFormat(".##");
	/**
	 * Create the panel.
	 */
	public BackOffice() {
		setLayout(null);
		setBackground(Color.decode("#EEEEEE"));
	
	        
	        listModel = new DefaultListModel();
	        companies = new DefaultListModel();
	        listModelSave = new DefaultListModel();
	        list = new JList(listModel);
		    list.setBounds(33, 95, 160, 156);
		    scrollPane = new JScrollPane(list);
			scrollPane.setBounds(33, 95, 160, 156);
			add(scrollPane);
			search = new JTextField();
			search.setBounds(33, 74, 160, 19);
			search.setText("Search");
			search.setForeground(Color.GRAY);
			add(search);
			search.setColumns(10);
			
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

	}
	private void populateUser() {
		System.out.println("STEP1");
		MainFram.firebaseFunction.getUsersRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				System.out.println("STEP2");
				for(DataSnapshot dataSnapshot : arg0.getChildren()) {
					listModel.addElement(new Utilisateur(dataSnapshot));					
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
						companies.get(i).initiatNumberUser();
						if(companies.get(i).getDif() > 0)
							SumPlus += companies.get(i).getDif();
						else sumMinus += companies.get(i).getDif(); 
					}
					
					
					for(int i = 0; i < companies.size(); i++) {
					if(companies.get(i).getDif() > 0)
						companies.get(i).setCompensation("À verser : " + (df2.format(	(companies.get(i).getDif()*(-sumMinus))/SumPlus)));
					else companies.get(i).setCompensation("À recevoir : " + -companies.get(i).getDif() );}
					  String[] nameList = new String[companies.getSize()];
					  for(int i = 0; i < companies.size(); i++)
						  nameList[i] = companies.getElementAt(i).toString();
				        imageMap = createImageMap(companies);
				        JList list2 = new JList(nameList);
				        list2.setCellRenderer(new MarioListRenderer());
				        JScrollPane scrollPane_1 = new JScrollPane(list2);
						scrollPane_1.setBounds(43, 265, 500, 170);
						add(scrollPane_1);
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
}
