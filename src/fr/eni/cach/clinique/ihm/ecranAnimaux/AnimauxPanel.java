package fr.eni.cach.clinique.ihm.ecranAnimaux;


import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import fr.eni.cach.clinique.ihm.UtilsIHM;

public class AnimauxPanel extends JPanel {
	
	
	private static final long serialVersionUID = -4242093774482057299L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttValider ;
	private JButton bttAnnuler ;
	
	private JLabel lblCodeAnimal;
	private JLabel lblNomAnimal;
	private JLabel lblCouleur;
	private JLabel lblEspece;
	private JLabel lblTatouage;
	private JLabel lblRace;
	private JLabel lblGetCodeAnimal;
	private JLabel lblNomClient;
	
	
	private JTextField tfNomAnimal;
	private JTextField tfCouleur;
	private JTextField tfTatouage;
	
	private JComboBox<String> cbSexe;
	private JComboBox<String> cbEspece;
	private JComboBox<String> cbRace;
	
	private JPanel panelBttHaut;
	private JPanel panelClient;
	private JPanel panelAnimal;
	private JPanel panelGlobal;
	

	public AnimauxPanel() {
		
		//Initialisation des champs
		
			// Les Labels
		
		this.createLblCodeAnimal();
		this.createLblNomAnimal();
		this.createLblCouleur();
		this.createLblEspece();
		this.createLblTatouage();
		this.createLblRace();
		this.createLblGetCodeAnimal();
		this.createLblNomClient();
		
			// Les TextFields
		
		this.createTfNomAnimal();
		this.createTfCouleur ();
		this.createTfTatouage ();
		
			// Les Boutons
		
		this.createBttValider ();
		this.createBttAnnuler ();
		
		
			// Les Combobox
		
		this.createCbSexe ();
		this.createCbEspece ();
		this.createCbRace ();
		
		
		// Création des panels
		
		this.createPanelBttHaut ();
		this.createPanelClient ();
		this.createPanelAnimal ();
		
		
		//Panel Global
		
		//this.createPanelGlobal ();
		
	
		GridBagLayout layoutGlobal = new GridBagLayout();
		
		this.setLayout(layoutGlobal);
		
		utilsIHM.addComponentTo(getPanelBttHaut(), this, 0, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getPanelClient(), this, 0, 1, 1, 1, 1, true);
		utilsIHM.addComponentTo(getPanelAnimal(), this, 0, 2, 1, 4, 1, true);
		
		
		
		
		
		
		
		
		
		
		
		
	
	}
	






	//--------------------------CREATION DES PANELS-----------------------------------------
	
		private void createPanelGlobal() {
		panelGlobal = new JPanel(new GridBagLayout());
		
		utilsIHM.addComponentTo(getPanelBttHaut(), panelGlobal, 0, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getPanelClient(), panelGlobal, 0, 1, 1, 1, 1, true);
		utilsIHM.addComponentTo(getPanelAnimal(), panelGlobal, 0, 2, 1, 4, 1, true);
		
		
		
		
	}



		private void createPanelAnimal() {
			panelAnimal = new JPanel(new GridBagLayout());
			
			utilsIHM.addComponentTo(getLblCodeAnimal(), panelAnimal, 0, 0, 1, 1, 0.2, true);
			utilsIHM.addComponentTo(getLblGetCodeAnimal(), panelAnimal, 1, 0, 1, 1, 0.2, true);
			
			utilsIHM.addComponentTo(getLblNomAnimal(), panelAnimal, 0, 1, 1, 1, 0.2, true);
			utilsIHM.addComponentTo(getTfNomAnimal(), panelAnimal, 1, 1, 4	, 1, 0.6, true);
			utilsIHM.addComponentTo(getCbSexe(), panelAnimal, 5, 1, 1, 1, 0.2, true);
			
			utilsIHM.addComponentTo(getLblCouleur(), panelAnimal, 0, 2, 1, 1, 0.2, true);
			utilsIHM.addComponentTo(getTfCouleur(), panelAnimal, 1, 2, 4, 1, 0.6, true);
			
			utilsIHM.addComponentTo(getLblEspece(), panelAnimal, 0, 3, 1, 1, 0.2, true); 
			utilsIHM.addComponentTo(getCbEspece(), panelAnimal, 1, 3, 1, 1, 0.2, true);
			utilsIHM.addComponentTo(getLblRace(), panelAnimal, 3, 3, 1, 1, 0.2, true);
			utilsIHM.addComponentTo(getCbRace(), panelAnimal, 4, 3, 1, 1, 0.2, true);
			
			utilsIHM.addComponentTo(getLblTatouage(), panelAnimal , 0, 4, 1, 1, 0.2, true);
			utilsIHM.addComponentTo(getTfTatouage(), panelAnimal, 1, 4, 4, 1, 0.6, true);
		}





		private void createPanelClient() {
			panelClient = new JPanel (new GridBagLayout());
			
			panelClient.setBorder(new TitledBorder("Client : "));
			
			utilsIHM.addComponentTo(getLblNomClient(), panelClient, 0, 0, 1, 1, 1, true);
			
		}





		private void createPanelBttHaut() {
			//permet de définir l'orientation de l'écriture dans le panel 
			setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			panelBttHaut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			//panelBoutons.setBackground(Color.GRAY);
			
			// pour mettre une bordure sur le panel boutons : faire appel à la BorderFactory
			panelBttHaut.setBorder(BorderFactory.createEtchedBorder());
			
			// attention à l'ordre d'ajout
					
			panelBttHaut.add(getBttValider());
			panelBttHaut.add(getBttAnnuler());
			
			
		}
	
	//-------------------CREATION DES COMPONENTS-----------------------------------
		
	private void createCbRace() {
			String races [] = {"Race 1", "Race 2", "Race 3", "Race 4"};
			cbRace = new JComboBox<>(races);
			//cbRace.setSelectedItem("..");
			
		}

	private void createCbEspece() {
		String especes [] = {"Espèce 1", "Espèce 2", "Espèce 3", "Ezspèce 4"};
		cbEspece = new JComboBox<>(especes);
		//cbRace.setSelectedItem("..");
			
		}

	private void createCbSexe() {
		String sexes [] = {"Mâle", "Femelle", "Hermaphrodite"};
		cbSexe = new JComboBox<>(sexes);
		//cbRace.setSelectedItem("..");
			
		}
		
		
	
	private void createBttAnnuler() {
		bttAnnuler = new JButton("Annuler");
		
	}


	private void createBttValider() {
		bttValider = new JButton("Valider");
		
	}


	private void createTfTatouage() {
		tfTatouage = new JTextField();
		
	}


	private void createTfCouleur() {
		tfCouleur = new JTextField();
		
	}


	private void createTfNomAnimal() {
		tfNomAnimal = new JTextField(); 
		
	}


	private void createLblNomClient() {
		// TODO : pré-remplir le label Nom Client
		lblNomClient = new JLabel ("MOREL Christophe");
		
	}


	private void createLblGetCodeAnimal() {
		// TODO : pré-remplir le label Code Animal
		lblGetCodeAnimal = new JLabel ("8888");
	}


	private void createLblRace() {
		lblRace = new JLabel ("Race");
		
	}


	private void createLblTatouage() {
		lblTatouage = new JLabel ("Tatouage");
		
	}


	private void createLblEspece() {
		lblEspece = new JLabel ("Espèce");
		
	}


	private void createLblCouleur() {
		lblCouleur = new JLabel ("Couleur");
		
	}


	private void createLblNomAnimal() {
		lblNomAnimal = new JLabel ("Nom");
		
	}


	private void createLblCodeAnimal() {
		lblCodeAnimal = new JLabel ("Code");
		
	}
	
	
	
	//----------------------------------GETTERS------------------------------------------------

	public JButton getBttValider() {
		return bttValider;
	}

	public JButton getBttAnnuler() {
		return bttAnnuler;
	}

	public JLabel getLblCodeAnimal() {
		return lblCodeAnimal;
	}

	public JLabel getLblNomAnimal() {
		return lblNomAnimal;
	}

	public JLabel getLblCouleur() {
		return lblCouleur;
	}

	public JLabel getLblEspece() {
		return lblEspece;
	}

	public JLabel getLblTatouage() {
		return lblTatouage;
	}

	public JLabel getLblRace() {
		return lblRace;
	}

	public JLabel getLblGetCodeAnimal() {
		return lblGetCodeAnimal;
	}

	public JLabel getLblNomClient() {
		return lblNomClient;
	}

	public JTextField getTfNomAnimal() {
		return tfNomAnimal;
	}

	public JTextField getTfCouleur() {
		return tfCouleur;
	}

	public JTextField getTfTatouage() {
		return tfTatouage;
	}

	public JComboBox<String> getCbSexe() {
		return cbSexe;
	}

	public JComboBox<String> getCbEspece() {
		return cbEspece;
	}

	public JComboBox<String> getCbRace() {
		return cbRace;
	}

	public JPanel getPanelBttHaut() {
		return panelBttHaut;
	}

	public JPanel getPanelClient() {
		return panelClient;
	}

	public JPanel getPanelAnimal() {
		return panelAnimal;
	}
	
	public JPanel getPanelGlobal() {
		return panelGlobal;
	}
	
	

}
