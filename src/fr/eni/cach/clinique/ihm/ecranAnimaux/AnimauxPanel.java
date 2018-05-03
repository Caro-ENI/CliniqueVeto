package fr.eni.cach.clinique.ihm.ecranAnimaux;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import fr.eni.cach.clinique.bll.AnimalManager;
import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.EspeceManager;
import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Espece;
import fr.eni.cach.clinique.bo.Race;
import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.ecranClients.GestClientPanel;
import fr.eni.cach.clinique.ihm.priseRDV.PriseRDVPanel;

public class AnimauxPanel extends JPanel {

	private static final long serialVersionUID = -4242093774482057299L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JButton bttValider;
	private JButton bttAnnuler;

	private JLabel lblCodeAnimal;
	private JLabel lblNomAnimal;
	private JLabel lblCouleur;
	private JLabel lblEspece;
	private JLabel lblTatouage;
	private JLabel lblRace;
	private JLabel lblNomClient;

	private JTextField tfCodeAnimal;
	private JTextField tfNomAnimal;
	private JTextField tfCouleur;
	private JTextField tfTatouage;

	private JComboBox<String> cbSexe;
	private JComboBox<Espece> cbEspece;
	private JComboBox<Race> cbRace;

	private JPanel panelBttHaut;
	private JPanel panelClient;
	private JPanel panelAnimal;
	private JPanel panelGlobal;
	
	public AnimauxPanel(Client client, Animal animal, JPanel panelParent) {

		// Initialisation des champs

		// Les Labels

		this.createLblCodeAnimal();
		this.createLblNomAnimal();
		this.createLblCouleur();
		this.createLblEspece();
		this.createLblTatouage();
		this.createLblRace();

		this.createLblNomClient(client);

		// Les TextFields
		this.createTfCodeAnimal(animal);
		this.createTfNomAnimal(animal);
		this.createTfCouleur(animal);
		this.createTfTatouage(animal);

		// Les Boutons

		this.createBttValider(client, animal, panelParent);
		this.createBttAnnuler(animal);

		// Les Combobox

		this.createCbSexe(animal);
		this.createCbEspece(animal);
		this.createCbRace(animal);

		// Création des panels

		this.createPanelBttHaut();
		this.createPanelClient();
		this.createPanelAnimal();

		// Panel Global

		GridBagLayout layoutGlobal = new GridBagLayout();

		this.setLayout(layoutGlobal);

		utilsIHM.addComponentTo(getPanelBttHaut(), this, 0, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getPanelClient(), this, 0, 1, 1, 1, 1, true);
		utilsIHM.addComponentTo(getPanelAnimal(), this, 0, 2, 1, 4, 1, true);

	}

	// --------------------------CREATION DES
	// PANELS-----------------------------------------

	private void createPanelAnimal() {
		panelAnimal = new JPanel(new GridBagLayout());

		utilsIHM.addComponentTo(getLblCodeAnimal(), panelAnimal, 0, 0, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getTfCodeAnimal(), panelAnimal, 1, 0, 1, 1, 0.2, true);

		utilsIHM.addComponentTo(getLblNomAnimal(), panelAnimal, 0, 1, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getTfNomAnimal(), panelAnimal, 1, 1, 4, 1, 0.6, true);
		utilsIHM.addComponentTo(getCbSexe(), panelAnimal, 5, 1, 1, 1, 0.2, true);

		utilsIHM.addComponentTo(getLblCouleur(), panelAnimal, 0, 2, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getTfCouleur(), panelAnimal, 1, 2, 4, 1, 0.6, true);

		utilsIHM.addComponentTo(getLblEspece(), panelAnimal, 0, 3, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getCbEspece(), panelAnimal, 1, 3, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getLblRace(), panelAnimal, 3, 3, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getCbRace(), panelAnimal, 4, 3, 1, 1, 0.2, true);

		utilsIHM.addComponentTo(getLblTatouage(), panelAnimal, 0, 4, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getTfTatouage(), panelAnimal, 1, 4, 4, 1, 0.6, true);
	}

	private void createPanelClient() {
		panelClient = new JPanel(new GridBagLayout());

		panelClient.setBorder(new TitledBorder("Client : "));

		utilsIHM.addComponentTo(getLblNomClient(), panelClient, 0, 0, 1, 1, 1, true);

	}

	private void createPanelBttHaut() {
		// permet de définir l'orientation de l'écriture dans le panel
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelBttHaut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// panelBoutons.setBackground(Color.GRAY);

		// pour mettre une bordure sur le panel boutons : faire appel à la
		// BorderFactory
		panelBttHaut.setBorder(BorderFactory.createEtchedBorder());

		// attention à l'ordre d'ajout

		panelBttHaut.add(getBttValider());
		panelBttHaut.add(getBttAnnuler());

	}

	// -------------------CREATION JCOMBOBOX------------------------------------

	private void createCbRace(Animal animal) {
		if (animal.getRace() == null) {
			cbRace = new JComboBox<>();
			cbRace.setSelectedItem(null);
		} else {
			cbRace = new JComboBox<>();
			cbRace.addItem(new Race(animal.getRace()));
		}
	}

	private void createCbEspece(Animal animal) {
		cbEspece = new JComboBox<>();
		if (animal.getEspece() == null) {
			try {
				List<Espece> especes = EspeceManager.getInstance().selectAllEspeces();
				for (Espece espece : especes) {
					cbEspece.addItem(espece);
				}
			} catch (BLLException e) {
				e.printStackTrace();
			}
			cbEspece.setSelectedItem(null);
			cbEspece.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (cbEspece.getSelectedItem() instanceof Espece) {
							Espece especeSelect = (Espece) cbEspece.getSelectedItem();
							List<Race> raceDeEspece;
							raceDeEspece = EspeceManager.getInstance().selectEspByRace(especeSelect.getEspece());
							cbRace.removeAllItems();
							for (Race race : raceDeEspece) {
								cbRace.addItem(race);
							}
						}
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

		} else {
			cbEspece.addItem(new Espece(animal.getEspece()));
		}
	}

	private void createCbSexe(Animal animal) {
		if (animal.getSexe() == null) {
			String sexes[] = { "Mâle", "Femelle", "Hermaphrodite" };
			cbSexe = new JComboBox<>(sexes);
			cbSexe.setSelectedItem(null);
		} else {
			cbSexe = new JComboBox<>();
			switch (animal.getSexe()) {
			case "M":
				cbSexe.addItem("Mâle");
				break;
			case "F":
				cbSexe.addItem("Femelle");
				break;
			case "H":
				cbSexe.addItem("Hermaphrodite");
				break;
			}
			
		}

	}

	// -------------------CREATION BOUTONS--------------------------------------

	private void createBttAnnuler(Animal animal) {
		bttAnnuler = new JButton("Annuler");
		bttAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (animal.getCodeAnimal() < 1) {
					tfCodeAnimal.setText("0000");
					tfNomAnimal.setText("");
					tfCouleur.setText("");
					tfTatouage.setText("");
					cbSexe.setSelectedItem(null);
					cbEspece.setSelectedItem(null);
					cbRace.removeAllItems();
					
				} else {
					tfCodeAnimal.setText(String.valueOf(animal.getCodeAnimal()));
					tfNomAnimal.setText(animal.getNomAnimal());
					tfCouleur.setText(animal.getCouleur());
					tfTatouage.setText(animal.getTatouage());

				}
			}
		});
	}

	private void createBttValider(Client client, Animal animal, JPanel panelParent) {
		bttValider = new JButton("Valider");
		bttValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (animal.getCodeAnimal()< 1){
					try {
					// l'animal était vide en arrivant donc c'est une création
					Animal animalAAjouter = AnimauxPanel.this.createAnimalFromTF(client);
					animalAAjouter.setAntecedents("");
										
						AnimalManager.getInstance().addAnimal(animalAAjouter);
					
					JOptionPane.showMessageDialog(AnimauxPanel.this, "L'Animal a bien été créé !",
							"Ajout d'un Animal", JOptionPane.INFORMATION_MESSAGE);
					
					//Rafraichissement des affichages des panels parents:
					if (panelParent instanceof GestClientPanel){
						((GestClientPanel) panelParent).rerefreshAffichageAnimaux(client);
						
					} else if (panelParent instanceof PriseRDVPanel){
						((PriseRDVPanel) panelParent).refrechJcbAnimaux(animalAAjouter);
					}
					
					//fermeture de la JIF
					AnimauxPanel.this.getParent().getParent().getParent().setVisible(false);
					
					} catch (BLLException e1) {
						JOptionPane.showMessageDialog(AnimauxPanel.this, e1.getMessage(),
								"Ajout d'un Animal", JOptionPane.ERROR_MESSAGE);
					}
					
				} else {
					try {
					//c'est une modification d'un animal existant
					Animal animalAModifier = AnimauxPanel.this.createAnimalFromTF(client);
					animalAModifier.setAntecedents(animal.getAntecedents());
					
						AnimalManager.getInstance().updateAnimal(animalAModifier);
					
					JOptionPane.showMessageDialog(AnimauxPanel.this, "L'Animal a bien été modifié !",
							"Modification d'un Animal", JOptionPane.INFORMATION_MESSAGE);
					
					//Rafraichissement des affichages des panels parents:
					if (panelParent instanceof GestClientPanel){
						((GestClientPanel) panelParent).rerefreshAffichageAnimaux(client);
					}
					
					
					//fermeture de la JIF
					AnimauxPanel.this.getParent().getParent().getParent().setVisible(false);
					} catch (BLLException e1) {
						JOptionPane.showMessageDialog(AnimauxPanel.this, e1.getMessage(),
								"Modification d'un Animal", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	// -------------------CREATION TEXT FIELDS----------------------------------

	private void createTfTatouage(Animal animal) {
		if (animal.getTatouage() == null) {
			tfTatouage = new JTextField("");
		} else {
			tfTatouage = new JTextField(animal.getTatouage());
		}

	}

	private void createTfCouleur(Animal animal) {
		if (animal.getCouleur() == null) {
			tfCouleur = new JTextField("");
		} else {
			tfCouleur = new JTextField(animal.getCouleur());
		}
	}

	private void createTfNomAnimal(Animal animal) {
		if (animal.getNomAnimal() == null) {
			tfNomAnimal = new JTextField("");
		} else {
			tfNomAnimal = new JTextField(animal.getNomAnimal());
		}
	}

	private void createTfCodeAnimal(Animal animal) {
		if (animal.getCodeAnimal() < 1) {
			tfCodeAnimal = new JTextField("0000");
			tfCodeAnimal.setEnabled(false);
		} else {
			tfCodeAnimal = new JTextField(String.valueOf(animal.getCodeAnimal()));
			tfCodeAnimal.setEnabled(false);
		}
	}

	// -------------------CREATION LABELS--------------------------------------

	private void createLblNomClient(Client client) {
		lblNomClient = new JLabel(client.toString());
	}

	private void createLblRace() {
		lblRace = new JLabel("Race");

	}

	private void createLblTatouage() {
		lblTatouage = new JLabel("Tatouage");

	}

	private void createLblEspece() {
		lblEspece = new JLabel("Espèce");

	}

	private void createLblCouleur() {
		lblCouleur = new JLabel("Couleur");

	}

	private void createLblNomAnimal() {
		lblNomAnimal = new JLabel("Nom");

	}

	private void createLblCodeAnimal() {
		lblCodeAnimal = new JLabel("Code");

	}

	// ----------------------------------Méthodes internes ------------------------------------------------
	
	/**
	 * Construit l'animal courant selon les champs
	 * 
	 * @return
	 */
	public Animal createAnimalFromTF(Client client) {
		Animal animalCree = new Animal();
		animalCree.setCodeAnimal(Integer.parseInt(getTfCodeAnimal().getText()));
		animalCree.setNomAnimal(getTfNomAnimal().getText());
		
		switch (getCbSexe().getSelectedItem().toString()) {
			case "Mâle":
				animalCree.setSexe("M");
				break;
			case "Femelle":
				animalCree.setSexe("F");
				break;
			case "Hermaphrodite":
				animalCree.setSexe("H");
				break;
			}
		animalCree.setCouleur(getTfCouleur().getText());
		animalCree.setRace((getCbRace().getSelectedItem()).toString());
		animalCree.setEspece((getCbEspece().getSelectedItem()).toString());
		animalCree.setCodeClient(client.getCodeClient());
		animalCree.setTatouage(getTfTatouage().getText());
		animalCree.setArchive(false);
		
		return animalCree;
	}
	
	
	// ----------------------------------GETTERS------------------------------------------------

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

	public JTextField getTfCodeAnimal() {
		return tfCodeAnimal;
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

	public JComboBox<Espece> getCbEspece() {
		return cbEspece;
	}

	public JComboBox<Race> getCbRace() {
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
