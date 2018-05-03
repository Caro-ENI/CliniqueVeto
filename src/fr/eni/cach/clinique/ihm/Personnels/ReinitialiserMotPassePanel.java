package fr.eni.cach.clinique.ihm.Personnels;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.eni.cach.clinique.bll.PersonnelManager;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.ihm.UtilsIHM;

public class ReinitialiserMotPassePanel extends JPanel {

	private static final long serialVersionUID = 8882775963959575605L;
	
	private JPanel panelBoutons;
	private JPanel panelInfos;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttValider;
	private JButton bttAnnuler;
	
	
	private JLabel lblNom;
	private JLabel lblMotPasse;
	private JLabel lblRole;
	
	
	private JTextField tfNom;
	private JPasswordField tfMotPasse;
	private JTextField tfRole;
	
	TablePersonnelModel model = GestPersonnelsPanel.getTablPersonnelModel();
	JTable table = GestPersonnelsPanel.getTablPersonnel();
	
	

	
	public ReinitialiserMotPassePanel () {
		
		// Initialisation des components
		
	
	
		
		
			this.createLblNom ();
			this.createLblMotPasse ();
			this.createLblRole();
			
			
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
		bttAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getTfMotPasse().setText("");
				
			}
		});
		
	}


	private void createBttValider() {
		bttValider = new JButton ("Valider");
		bttValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Personnel persAInitialiser = model.getValueAt(table.getSelectedRow());
					persAInitialiser.setMotPasse(getTfMotPasse().getText());
					
					PersonnelManager.getInstance().updatePersonnel(persAInitialiser);
					
					JOptionPane.showMessageDialog(ReinitialiserMotPassePanel.this, "Mot de passe réinitialisé",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					ReinitialiserMotPassePanel.this.getParent().getParent().getParent().setVisible(false);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}


	private void createTfRole() {
		try {
			Personnel persAInitialiser = model.getValueAt(table.getSelectedRow());
			
			switch (persAInitialiser.getRole()) {
			case "sec" :
				tfRole = new JTextField ("Secrétaire");
				break;
			case "vet" :
				tfRole = new JTextField ("Vétérinaire");
				break;
			case "adm" :
				tfRole = new JTextField ("Administrateur");
				break;
			}
			
			tfRole.setEditable(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	private void createTfMotPasse() {
		tfMotPasse = new JPasswordField();
		
	}


	private void createTfNom() {
		try {
			Personnel persAInitialiser = model.getValueAt(table.getSelectedRow());
			tfNom = new JTextField (persAInitialiser.getNom());
			tfNom.setEditable(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
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




	public JLabel getLblNom() {
		return lblNom;
	}


	public JLabel getLblMotPasse() {
		return lblMotPasse;
	}


	public JLabel getLblRole() {
		return lblRole;
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
