package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;

import com.google.firebase.database.DatabaseReference;

import model.FirebaseFunction;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class AddAnnonce extends JFrame {
	private JLabel lblNewLabel;
	private JPanel contentPane;
	private JButton btnUpload;
	private String path = "";
	private JTextField titre;
	private JTextField prix;
	private JTextField adresse;
	private JTextField description;
	private JTextField categorie;
	private JTextField duree;
	private JTextField quantity;
	private JTextField typeEmploi;
	private JRadioButton miseEnRelation, location, achatVente;
	private String name;
	private ButtonGroup group;
	private FirebaseFunction firebaseFunction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAnnonce frame = new AddAnnonce();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AddAnnonce() throws IOException {
		 firebaseFunction = new FirebaseFunction();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddImage = new JButton("Add image");
		btnAddImage.setBounds(157, 89, 117, 25);
		contentPane.add(btnAddImage);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(165, 30, 109, 84);
		contentPane.add(lblNewLabel);
		
		btnUpload = new JButton("Upload");
		btnUpload.setBounds(157, 563, 117, 25);
		contentPane.add(btnUpload);
		
		
		titre = new JTextField();
		titre.setToolTipText("");
		titre.setBounds(160, 122, 114, 19);
		contentPane.add(titre);
		titre.setColumns(10);
		
		prix = new JTextField();
		prix.setBounds(160, 142, 114, 19);
		contentPane.add(prix);
		prix.setColumns(10);
		
		adresse = new JTextField();
		adresse.setBounds(160, 164, 114, 19);
		contentPane.add(adresse);
		adresse.setColumns(10);
		
		description = new JTextField();
		description.setBounds(160, 208, 114, 82);
		contentPane.add(description);
		description.setColumns(10);
		
		 miseEnRelation = new JRadioButton("Mise en relation");
		miseEnRelation.setBounds(157, 338, 149, 23);
		contentPane.add(miseEnRelation);
		
		 achatVente = new JRadioButton("Vente");
		achatVente.setBounds(157, 367, 149, 23);
		contentPane.add(achatVente);
		
		 location = new JRadioButton("Location");
		location.setBounds(157, 308, 149, 23);
		contentPane.add(location);
		miseEnRelation.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(miseEnRelation.isSelected())
					typeEmploi.setVisible(true);
				else
					typeEmploi.setVisible(false);
			}
		});
		achatVente.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(achatVente.isSelected())
					quantity.setVisible(true);
				else quantity.setVisible(false);
			}
		});
		location.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(location.isSelected())
					duree.setVisible(true);
				else
					duree.setVisible(false);
			}
		});
		
		 group = new ButtonGroup();
	    group.add(miseEnRelation);
	    group.add(location);
	    group.add(achatVente);
	    
	    categorie = new JTextField();
	    categorie.setBounds(160, 184, 114, 19);
	    contentPane.add(categorie);
	    categorie.setColumns(10);
	    
	    duree = new JTextField();
	    duree.setColumns(10);
	    duree.setBounds(157, 427, 114, 19);
	    contentPane.add(duree);
	    
	    quantity = new JTextField();
	    quantity.setBounds(157, 427, 114, 19);
	    contentPane.add(quantity);
	    quantity.setColumns(10);
	    
	    typeEmploi = new JTextField();
	    typeEmploi.setBounds(157, 427, 114, 19);
	    contentPane.add(typeEmploi);
	    typeEmploi.setColumns(10);
	    
	    typeEmploi.setVisible(false);
	    quantity.setVisible(false);
	    duree.setVisible(false);
	    
		btnUpload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					if(allFieldValide()) {
					addToFirebase();
					firebaseFunction.uploadImage(path,name);}
					else
						System.out.println("veuillez remplir tous les champs");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					///e1.printStackTrace();
				}
			}
		});
		
		btnAddImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 JFileChooser file = new JFileChooser();
		          file.setCurrentDirectory(new File(System.getProperty("user.home")));
		          //filter the files
		          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		          file.addChoosableFileFilter(filter);
		          int result = file.showSaveDialog(null);
		           //if the user click on save in Jfilechooser
		          if(result == JFileChooser.APPROVE_OPTION){
		              File selectedFile = file.getSelectedFile();
		              path = selectedFile.getAbsolutePath();
		              name = System.currentTimeMillis() + "" ;
		              lblNewLabel.setIcon(ResizeImage(path));
		          }
		           //if the user click on save in Jfilechooser
		          else if(result == JFileChooser.CANCEL_OPTION){
		              System.out.println("No File Select");
		          }
			}
		});
	}
	public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	public Boolean allFieldValide() {
		if(!titre.getText().equals("") && !prix.getText().equals("") && !adresse.getText().equals("") && 
				!description.getText().equals("") && !categorie.getText().equals("") && !path.equals("")) {
			if(miseEnRelation.isSelected())
				return (!typeEmploi.getText().equals(""));
			if(location.isSelected())
				return !duree.getText().equals("");
			if(achatVente.isSelected())
				return !quantity.getText().equals("");
			return false;}
		else return false;
	}
	public void addToFirebase() {
		DatabaseReference AnnonceRef = firebaseFunction.getAnnonceRef().child(System.currentTimeMillis() + "");
		AnnonceRef.child("recu").setValueAsync("false");
        AnnonceRef.child("title").setValueAsync(titre.getText().toString());
        AnnonceRef.child("id_user").setValueAsync(firebaseFunction.getIdCompany());
        AnnonceRef.child("wallet").setValueAsync("1ZWiPiAHsQ4ELb3mHW7Ce4GnwxiHSy4aqnZRLc");
        AnnonceRef.child("price").setValueAsync(prix.getText().toString());
        AnnonceRef.child("description").setValueAsync(description.getText().toString());
        AnnonceRef.child("adresse").setValueAsync(adresse.getText().toString());
       
       
        AnnonceRef.child("offre_demande").setValueAsync("Offre");
        AnnonceRef.child("categories").setValueAsync(categorie.getText().toString());
        AnnonceRef.child("imageUrl").setValueAsync("https://firebasestorage.googleapis.com/v0/b/fids-f85.appspot.com/o/image%2F" +name+  "?alt=media&token=76f65534-e1bb-4944-b229-25e873663f8d");
       
        if(location.isSelected()) {
            AnnonceRef.child("time").setValueAsync(duree.getText().toString());
        AnnonceRef.child("type").setValueAsync("Location");}
        if(miseEnRelation.isSelected()) {
            AnnonceRef.child("job_type").setValueAsync("Type d'emploi : " + typeEmploi.getText().toString());
        AnnonceRef.child("type").setValueAsync("Mise en relation");}
        if(achatVente.isSelected()) {
            AnnonceRef.child("quantity").setValueAsync(quantity.getText().toString());
        AnnonceRef.child("type").setValueAsync("Achat/Vente");}
        System.out.println("done");
		
	}
}
