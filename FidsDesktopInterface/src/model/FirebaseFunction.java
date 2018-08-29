package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.api.services.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseFunction {
	private String idCompany = "1";
	private DatabaseReference databaseReference;
	public FirebaseFunction() throws IOException {
		FileInputStream serviceAccount = new FileInputStream("/home/mcq-1/eclipse-workspace/FidsDesktopInterface/service-account.json");
		System.out.println("test : " + serviceAccount.toString());
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		    .setDatabaseUrl("https://fids-f85.firebaseio.com/")
		    .setStorageBucket("fids-f85.appspot.com")
		    .build();
		
		FirebaseApp.initializeApp(options);
		databaseReference = FirebaseDatabase.getInstance().getReference();
		//uploadImage("buble.png","yo");
	}
	/**
	 * @return the databaseReference
	 */
	public DatabaseReference getDatabaseReference() {
		return databaseReference;
	}
	/**
	 * @param databaseReference the databaseReference to set
	 */
	public void setDatabaseReference(DatabaseReference databaseReference) {
		this.databaseReference = databaseReference;
	}
	
	public DatabaseReference getfactureRef() {
		return databaseReference.child("facture").child(idCompany);
	}
	
	public DatabaseReference getAnnonceRef() {
		return databaseReference.child("annonce").child(idCompany);
	}
	
	public DatabaseReference getUsersRef() {
		return databaseReference.child("user");
	}
	
	public DatabaseReference getCompanyRef() {
		return databaseReference.child("company").child(idCompany);
	}
	
	public void uploadImage(String path, String name) throws IOException {
		BufferedImage bImage = ImageIO.read(new File(path));
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "png", bos );
	      byte [] data = bos.toByteArray();
	      Bucket bucket = StorageClient.getInstance().bucket();
			 bucket.create("image/"+name, data, "image/png");
			 
	}
	/**
	 * @return the idCompany
	 */
	public String getIdCompany() {
		return idCompany;
	}
	/**
	 * @param idCompany the idCompany to set
	 */
	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}
	
	
	
}
