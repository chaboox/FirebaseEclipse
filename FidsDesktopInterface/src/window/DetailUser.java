package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.MultichaineFunction;
import model.Utilisateur;
import javax.swing.JTextPane;

public class DetailUser extends JFrame {
	
	private JLabel lblNewLabel;
	private JPanel contentPane;
	private int heightImage = 70;
	private int widthImage = 70;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailUser frame = new DetailUser(new Utilisateur());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/*public DetailUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(142, 98, 136, 21);
		contentPane.add(textPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(174, 29, 70, 57);
		contentPane.add(lblNewLabel);
	}*/
	public DetailUser(Utilisateur utilisateur) {
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		 StyledDocument document = new DefaultStyledDocument();

		    SimpleAttributeSet attributes = new SimpleAttributeSet();
		    attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
		   // attributes.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.TRUE);

		    try {
		      document.insertString(document.getLength(), "Bold", attributes);
		    } catch (BadLocationException badLocationException) {
		      System.err.println("Bad insert");
		    }
		
		JTextPane textPane = new JTextPane(document);
		textPane.setEditable(false);
		textPane.setBounds(142, 98, 300, 19);
		contentPane.add(textPane);
		textPane.setText(utilisateur.getPrenom() + " " + utilisateur.getNom());
		
		JTextPane identifiant = new JTextPane();
		identifiant.setEditable(false);
		identifiant.setBounds(142, 118, 300, 19);
		contentPane.add(identifiant);
		identifiant.setText("Identifiant : " + utilisateur.getUsername());
		
		JTextPane telephone = new JTextPane();
		telephone.setEditable(false);
		telephone.setBounds(142, 138, 300, 19);
		contentPane.add(telephone);
		telephone.setText("Téléphone : " + utilisateur.getTelephone());
		
		JTextPane mail = new JTextPane();
		mail.setEditable(false);
		mail.setBounds(142, 158, 300, 19);
		contentPane.add(mail);
		mail.setText("Email : " + utilisateur.getEmail());
		
		JTextPane Balance = new JTextPane();
		Balance.setEditable(false);
		Balance.setBounds(142, 178, 300, 19);
		contentPane.add(Balance);
		Balance.setText("Balance : " + MultichaineFunction.getBalanceByWallet(utilisateur.getWallet()));
		
		Image image = null;
        try {
            URL url = new URL(utilisateur.getImageUrl());
            image = ImageIO.read(url);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
         lblNewLabel = new JLabel(ResizeImage(new ImageIcon(image)));
		lblNewLabel.setBounds(174, 29, widthImage,heightImage);
		contentPane.add(lblNewLabel);
       /* JLabel label = new JLabel(new ImageIcon(image));
        contentPane.add(label);*/
        
	
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
