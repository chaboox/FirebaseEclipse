package model;


public class Singleton_connexion {
	public static  String IP_BLOCKCHAIN;
    public static  String PORT_BLOCKCHAIN;
    public static  String LOGIN_BLOCKCHAIN;
    public static  String PASSWORD_BLOCKCHAIN;
    public static  String NOM_BLOCKCHAIN;
    public static  double CHANGE_FIDS_CONTRE_EUROS;
    public static  String WALLET_SERVER;
    public Singleton_connexion() {
		// TODO Auto-generated constructor stub
    	IP_BLOCKCHAIN = "localhost";
    	PORT_BLOCKCHAIN = "12346";
    	LOGIN_BLOCKCHAIN = "multichainrpc";
    	PASSWORD_BLOCKCHAIN = "5NFmaaRJszLPW17v8PKTXKdf99M6fy4qEJfnDf35gfbQ";
    	NOM_BLOCKCHAIN = "Fids";
    	CHANGE_FIDS_CONTRE_EUROS = 0.5;
    	WALLET_SERVER = "1ZWiPiAHsQ4ELb3mHW7Ce4GnwxiHSy4aqnZRLc";
    }
    
    
}
