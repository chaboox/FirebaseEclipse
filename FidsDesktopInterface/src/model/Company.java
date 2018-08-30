package model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import window.MainFram;

public class Company {
	private String name;
	private String dis;
	private String col;
	private String imageUrl;
	private long numberUser =0;
	private String id;
	private double dif;
	private String compensation;
	
	public Company(String name, String dis, String col, String imageUrl) {
		this.name = name;
		this.dis = dis;
		this.col = col;
		this.imageUrl = imageUrl;
	}
	
	public Company(DataSnapshot dataSnapshot) {
		name = dataSnapshot.child("name").getValue().toString();
		imageUrl = dataSnapshot.child("imageUrl").getValue().toString();
		dis = dataSnapshot.child("dis").getValue().toString();
		col = dataSnapshot.child("col").getValue().toString();
		id = dataSnapshot.getKey().toString();
		dif = Double.parseDouble(dis) - Double.parseDouble(col);
	}
	
	public void initiatNumberUser() {
		MainFram.firebaseFunction.getCompanyUserRef().addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				for(DataSnapshot child: arg0.getChildren()) {
					child.child(id).getRef().addListenerForSingleValueEvent(new ValueEventListener() {
						
						@Override
						public void onDataChange(DataSnapshot arg1) {
							// TODO Auto-generated method stub
							System.out.print("Key " + arg1 + " VALUE " + arg1.getValue().toString());
							numberUser++;
							System.out.println("NUM " + name + "    " + numberUser );
						}
						
						@Override
						public void onCancelled(DatabaseError arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			//	numberUser = arg0.getChildrenCount();
				
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * @param compensation the compensation to set
	 */
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dis
	 */
	public String getDis() {
		return dis;
	}

	/**
	 * @param dis the dis to set
	 */
	public void setDis(String dis) {
		this.dis = dis;
	}

	/**
	 * @return the col
	 */
	public String getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(String col) {
		this.col = col;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the numberUser
	 */
	public long getNumberUser() {
		return numberUser;
	}

	/**
	 * @param numberUser the numberUser to set
	 */
	public void setNumberUser(long numberUser) {
		this.numberUser = numberUser;
	}
	
	

	/**
	 * @return the dif
	 */
	public double getDif() {
		return dif;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  name + "  distribué : " + dis + ", utilisé : " + col + ", " + compensation;
	}
	
	
	
	 
}
