
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.http.client.methods.*;

import com.google.cloud.storage.Bucket;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.FirebaseFunction;
import model.Singleton_connexion;
import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;


public class Main {
	

	public static void main(String[] args) throws IOException {
		
		

		
		// TODO Auto-generated method stub
		Singleton_connexion singleton_connexion = new Singleton_connexion();
		MultiChainCommand multiChainCommand = new MultiChainCommand(
                singleton_connexion.IP_BLOCKCHAIN,
                singleton_connexion.PORT_BLOCKCHAIN,
                singleton_connexion.LOGIN_BLOCKCHAIN,
                singleton_connexion.PASSWORD_BLOCKCHAIN);
		
				try {
					System.out.println("Balance Serveur " + multiChainCommand.getAddressCommand().getAddressBalances(singleton_connexion.WALLET_SERVER).get(0).getIssueqty());
				} catch (MultichainException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FirebaseFunction firebaseFunction = new FirebaseFunction();
				

				firebaseFunction.getDatabaseReference().child("user").child("42").addListenerForSingleValueEvent(new ValueEventListener() {
					
					@Override
					public void onDataChange(DataSnapshot arg0) {
						// TODO Auto-generated method stub
						for(DataSnapshot child : arg0.getChildren())
						System.out.println("YO" + child.getValue().toString());
					}
					
					@Override
					public void onCancelled(DatabaseError arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				// ... or use the equivalent shorthand notation
				while (true) {
					//otherwise the app get closed before the end of the firebase request
					
				}

	}

}
