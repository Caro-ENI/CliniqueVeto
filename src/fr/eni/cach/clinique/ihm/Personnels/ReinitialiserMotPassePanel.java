package fr.eni.cach.clinique.ihm.Personnels;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.cach.clinique.ihm.UtilsIHM;

public class ReinitialiserMotPassePanel extends JPanel {

	private static final long serialVersionUID = 8882775963959575605L;
	
	private JPanel panelBoutons;
	private JPanel panelInfos;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttValider;
	private JButton bttAnnuler;
	
	private JLabel lblCodePers;
	private JLabel lblNom;
	private JLabel lblMotPasse;
	private JLabel lblRole;
	
	private JTextField tfCodePers;
	private JTextField tfNom;
	private JTextField tfMotPasse;
	private JTextField tfRole;
	
	
	public ReinitialiserMotPassePanel () {
		
		// Initialisation des components
		
			this.createLblCodePers ();
			this.createLblNom ();
			this.createLblMotPasse ();
			this.createLblRole();
			
			this.createTfCodePers ();
			this.createTfNom();
			this.createTfMotPasse();
			this.createTfRole();
			
			this.createBttValider();
			this.createBttAnnuler ();
		
		// Initialisation des panels
			
			this.createPanelBoutons ();
			this.createPanelInfos ();
			
		// Panel global	
			
			
			BorderLayout layoutGlobal = new BorderLayout();
			this.setLayout(layoutGlobal);
			
			this.add(getPanelBoutons(), BorderLayout.NORTH);
			this.add(getPanelInfos(), BorderLayout.CENTER);	
		
		
	}
	
	
	//--------------------------------------CREATION DES PANELS-------------------------------------------
	
	
	private void createPanelInfos() {
		
		panelInfos = new JPanel (new GridBagLayout());
		
		// L'utilisateur n'a pas besoin de voir afficher  son code salarié.
		
		//utilsIHM.addComponentTo(getLblCodePers(), panelInfos, 0, 0, 1, 1, 0.3, true);
		//utilsIHM.addComponentTo(getTfCodePers(), panelInfos, 1, 0, 1, 1, 0.7, true);
		
		utilsIHM.addComponentTo(getLblNom(), panelInfos, 0, 0, 1, 1, 0.3, true);
		utilsIHM.addComponentTo(getTfNom(), panelInfos, 1, 0, 1, 1, 0.7, true);
		
		utilsIHM.addComponentTo(getLblMotPasse(), panelInfos, 0, 1, 1, 1, 0.3, true);
		utilsIHM.addComponentTo(getTfMotPasse(), panelInfos, 1, 1, 1, 1, 0.7, true);
		
		utilsIHM.addComponentTo(getLblRole(), panelInfos, 0, 2, 1, 1, 0.3, true);
		utilsIHM.addComponentTo(getTfRole(), panelInfos, 1, 2, 1, 1, 0.7, true);
		
	}


	private void createPanelBoutons() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelBoutons = new JPanel (new FlowLayout(FlowLayout.RIGHT));
		
		// pour mettre une bordure sur le panel boutons : faire appel à la BorderFactory
		panelBoutons.setBorder(BorderFactory.createEtchedBorder());
		
		panelBoutons.add(getBttValider());
		panelBoutons.add(getBttAnnuler());
		
		
	}


	//------------------------------------------CREAION DES COMPONENTS-------------------------------------------------


	private void createBttAnnuler() {
		bttAnnuler = new JButton ("Annuler");
		
	}


	private void createBttValider() {
		bttValider = new JButton ("Valider");
		
	}


	private void createTfRole() {
		tfRole = new JTextField("Rôle pré rempli");
		
	}


	private void createTfMotPasse() {
		tfMotPasse = new JTextField("");
		
	}


	private void createTfNom() {
		tfNom = new JTextField ("Nom pré rempli");
		
	}


	private void createTfCodePers() {
		tfCodePers = new JTextField("Code pré rempli");
		
	}


	private void createLblRole() {
		lblRole = new JLabel ("Rôle");
		
	}


	private void createLblMotPasse() {
		lblMotPasse = new JLabel ("Mot de passe");
		
	}


	private void createLblNom() {
		lblNom = new JLabel ("Nom");
		
	}


	private void createLblCodePers() {
		lblCodePers = new JLabel ("Code Personnel");
		
		
	}
	
	//-------------------------------------------------GETTERS---------------------------------------------------------------


	public JPanel getPanelBoutons() {
		return panelBoutons;
	}


	public JPanel getPanelInfos() {
		return panelInfos;
	}


	public JButton getBttValider() {
		return bttValider;
	}


	public JButton getBttAnnuler() {
		return bttAnnuler;
	}


	public JLabel getLblCodePers() {
		return lblCodePers;
	}


	public JLabel getLblNom() {
		return lblNom;
	}


	public JLabel getLblMotPasse() {
		return lblMotPasse;
	}


	public JLabel getLblRole() {
		return lblRole;
	}


	public JTextField getTfCodePers() {
		return tfCodePers;
	}


	public JTextField getTfNom() {
		return tfNom;
	}


	public JTextField getTfMotPasse() {
		return tfMotPasse;
	}


	public JTextField getTfRole() {
		return tfRole;
	}
		
	
	
	
	
	
	
	

}
