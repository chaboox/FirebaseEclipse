package model;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;

public class MultichaineFunction {
	//Used to get ballance of users by their wallets 
	public static double getBalanceByWallet(String Wallet) {
		Singleton_connexion singleton_connexion = new Singleton_connexion();
	MultiChainCommand multiChainCommand = new MultiChainCommand(
            Singleton_connexion.IP_BLOCKCHAIN,
            Singleton_connexion.PORT_BLOCKCHAIN,
            Singleton_connexion.LOGIN_BLOCKCHAIN,
            Singleton_connexion.PASSWORD_BLOCKCHAIN);
	
			try {
				return multiChainCommand.getAddressCommand().getAddressBalances(Wallet).get(0).getIssueqty();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return 0.0;
			}}
}
