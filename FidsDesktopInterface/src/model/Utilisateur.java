package model;

import java.util.Random;

import com.fasterxml.jackson.core.sym.Name;
import com.google.firebase.database.DataSnapshot;

public class Utilisateur {
	private String id;
    private String wallet ;
    private String email ; 
    private String nom ;
    private String username;
    private String imageUrl;
    private String prenom ;
    private String telephone ;
    private String siret ; 
    private double balance ;
    
	public Utilisateur(String id, String wallet, String email, String nom, String username,
			String imageUrl, String prenom, String telephone) {
		super();
		this.id = id;
		this.wallet = wallet;
		this.email = email;
		this.nom = nom;
		this.username = username;
		this.imageUrl = imageUrl;
		this.prenom = prenom;
		this.telephone = telephone;
	}
	
	public Utilisateur() {
		
	}
	public Utilisateur(DataSnapshot dataSnapshot) {
		id = dataSnapshot.getKey().toString();
		wallet = dataSnapshot.child("wallet").getValue().toString();
		imageUrl = dataSnapshot.child("imageUrl").getValue().toString();
		email = dataSnapshot.child("email").getValue().toString();
		nom = dataSnapshot.child("name").getValue().toString();
		prenom = dataSnapshot.child("first_name").getValue().toString();
		username = dataSnapshot.child("username").getValue().toString();
		telephone = dataSnapshot.child("number").getValue().toString();
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  nom + " " + prenom ;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the wallet
	 */
	public String getWallet() {
		return wallet;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
 
	/**
	 * @return the siret
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	
    public String[] getRow() {
    	return new String[] { "",nom, prenom, username, email, telephone, MultichaineFunction.getBalanceByWallet(wallet) +"", (new Random().nextInt((1500 - 0) + 1) + 0) + "",
    			(new Random().nextInt((1500 - 0) + 1) + 0) + "", "" };
    }
    
 
}
