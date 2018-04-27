package fr.eni.cach.clinique.ihm.cliniqueVeto;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ActualiteFrame extends JFrame{

	private static final long serialVersionUID = 8641329173643887722L;

	private JPanel panelActu;
	private JLabel lblActualite;
	
	public ActualiteFrame(String nom) {

		this.setSize(400,500);
		this.setTitle("Actualités");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.createPaneActu(nom);
		
		this.setContentPane(panelActu);
		
	}
	
	
	
	/**
	 * Crée un panel d'actualités 
	 * donne la date et dit bonjour à l'utilisateur courant 
	 */
	private void createPaneActu(String nom){
		panelActu = new JPanel();
		lblActualite = new JLabel("BONJOUR " + nom + " !");
		lblActualite.setHorizontalAlignment(SwingConstants.CENTER);
		panelActu.add(lblActualite);
	}
	
	
	/* *****GETTERS***** */
	
	/**
	 * Renvoie le Panel actualités 
	 * (détail dans la JavaDoc de création
	 * @return
	 */
	public JPanel getPanelActu(){
		return panelActu;
	}
	
	
	
}

