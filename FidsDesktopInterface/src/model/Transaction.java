package model;

import java.util.Calendar;
import java.util.Random;

import com.google.firebase.database.DataSnapshot;

public class Transaction {
    private String idUser, username, typeOperation, montant, date, idAgence, enseigne;

    public Transaction(String idAgence, String idUser, String username, String typeOperation, String montant) {
        this.idUser = idUser;
        this.username = username;
        this.typeOperation = typeOperation;
        this.montant = montant;
        this.idAgence = idAgence;
    }
    
    public Transaction(DataSnapshot dataSnapshot) {
    	idUser = dataSnapshot.child("idUser").getValue().toString();
    	username = dataSnapshot.child("username").getValue().toString();
    	typeOperation = dataSnapshot.child("operation").getValue().toString();
    	montant = dataSnapshot.child("somme").getValue().toString();
    	date = dataSnapshot.getKey().toString();
    }
    
    public Transaction(DataSnapshot dataSnapshot, String ens) {
    	idUser = dataSnapshot.child("idUser").getValue().toString();
    	username = dataSnapshot.child("username").getValue().toString();
    	typeOperation = dataSnapshot.child("operation").getValue().toString();
    	montant = dataSnapshot.child("somme").getValue().toString();
    	date = dataSnapshot.getKey().toString();
    
    	if(ens.equals("1")) {
    		enseigne = "Kiloutou";
    	}
    	else enseigne = "Manpower";
    }
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [idUser=" + idUser + ", username=" + username + ", typeOperation=" + typeOperation
				+ ", montant=" + montant + ", date=" + date + "]";
	}
    
	 public String[] getRow() {
	    	return new String[] { typeOperation, montant, username, dateLongToString(Long.parseLong(date))};
	    }
	 
	 public String[] getRowPlus() {
	    	return new String[] { enseigne,typeOperation, montant, username, dateLongToString(Long.parseLong(date))};
	    }
	 public String dateLongToString(long date_notification){


	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(date_notification);

	        int mYear = calendar.get(Calendar.YEAR);
	        int mMonth = calendar.get(Calendar.MONTH) + 1 ;
	        int mDay = calendar.get(Calendar.DAY_OF_MONTH)-1;

	        int mh = calendar.get(Calendar.HOUR_OF_DAY);
	        int mm = calendar.get(Calendar.MINUTE);

	        return   "" + (mDay>9 ? mDay : ("0"+mDay)) + "/" + (mMonth > 9 ? mMonth : ("0" + mMonth))  + "/" + mYear;// + "  à " + mh + "H" + mm ;
	    }

    
}
