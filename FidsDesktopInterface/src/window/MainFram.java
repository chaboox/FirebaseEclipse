package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class MainFram extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFram frame = new MainFram();
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
	public MainFram() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
	
		JTabbedPane tabPane = new JTabbedPane();
		contentPane = new CaisseEnregistreuse();
		getRootPane().setDefaultButton(((CaisseEnregistreuse) contentPane).getAjoutButton());
		tabPane.addTab( "Caisse enregistreuse", contentPane);
		tabPane.addTab( "compte Enseigne", new JPanel());
		tabPane.addTab( "back office", new JPanel());
		
		mainPanel.add(tabPane);
		
		
		/*
		contentPane = new CaisseEnregistreuse();
		getRootPane().setDefaultButton(((CaisseEnregistreuse) contentPane).getAjoutButton());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);*/
		
	}

}
