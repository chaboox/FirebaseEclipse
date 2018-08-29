package window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import model.Article;
import model.Utilisateur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class CompteEnseigne extends JPanel {
	private JList list;
	private JScrollPane scrollPane;
	private JTextPane textPane, textPane2, textPane3;
	private DefaultListModel<Utilisateur> listModel;
	private DefaultListModel<Utilisateur> listModelSave;
	private JTextField search;
	private JTextPane dis;
	private JTextPane col;
	/**
	 * Create the panel.
	 */
	public CompteEnseigne() {
		setLayout(null);
		setBackground(Color.decode("#EEEEEE"));
		listModel = new DefaultListModel();
		listModelSave = new DefaultListModel();
		list = new JList(listModel);
		    list.setBounds(33, 75, 160, 156);
		    scrollPane = new JScrollPane(list);
			scrollPane.setBounds(33, 75, 160, 156);
			add(scrollPane);
			populateUser();
		JButton btnNewButton = new JButton("Créer une annonce");
		btnNewButton.setBounds(220, 400, 200, 25);
		add(btnNewButton);
		
		search = new JTextField();
		search.setBounds(33, 54, 160, 19);
		search.setText("Search");
		search.setForeground(Color.GRAY);
		add(search);
		search.setColumns(10);
		
		dis = new JTextPane();
		dis.setBounds(237, 54, 160, 19);
		dis.setBackground(Color.decode("#EEEEEE"));
		add(dis);
		
		
		col = new JTextPane();
		col.setBounds(237, 74, 160, 19);
		col.setBackground(Color.decode("#EEEEEE"));
		add(col);
		
		
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
					//System.out.println("STEP3" + dataSnapshot.getKey() + dataSnapshot.getValue());
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
	
	private void populateFids() {
		MainFram.firebaseFunction.getCompanyRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				// TODO Auto-generated method stub
				dis.setText("Fids distribués : " +dataSnapshot.child("dis").getValue().toString() );
				col.setText("Fids collectés : " + dataSnapshot.child("col").getValue().toString() );
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
		
}
