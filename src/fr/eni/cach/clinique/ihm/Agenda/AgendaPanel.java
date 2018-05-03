package fr.eni.cach.clinique.ihm.Agenda;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.cach.clinique.bll.Utilitaires.DateLabelFormatter;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.DossierMedical.DossierMedicPanel;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;

public class AgendaPanel extends JPanel {

	private static final long serialVersionUID = -149957493039323454L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JLabel lblVeterinaire;
	private JLabel lblDate;

	private JDatePicker datePicker;

	// A terme faire des combobox de Vï¿½tï¿½rinaire
	private JComboBox<String> cbVeterinaire;

	private JTable tablAgenda;
	private TableAgendaModel modelTablAgenda;

	private JButton bttDossierMedical;

	private JPanel panelHaut;
	private JScrollPane panelAgenda;
	private JPanel panelDossierMedical;

	private Rdv rdvCourant = null;

	public AgendaPanel() {

		// Initialisation des champs

		// Labels
		this.createLblVeterinaire();
		this.createLblDate();

		// DatePicker
		this.createDatePicker();

		// JComboBox
		this.createCbVeterinaire();

		// Boutons
		this.createBttDossierAnimal();

		// JTable
		this.createTablAgenda();

		// Sous-Panels
		this.createPanelHaut();
		this.createPanelAgenda();
		this.createPanelDossierMedical();

		// Panel global
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);

		this.add(getPanelHaut(), BorderLayout.NORTH);
		this.add(getPanelAgenda(), BorderLayout.CENTER);
		this.add(getPanelDossierMedical(), BorderLayout.SOUTH);

	}

	// --------------- CREATION DES PANELS-------------------------------

	private void createPanelDossierMedical() {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelDossierMedical = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		panelDossierMedical.add(getBttDossierMedical());
	}

	private void createPanelAgenda() {
		panelAgenda = new JScrollPane();
		panelAgenda.setViewportView(getTablAgenda());
	}

	private void createPanelHaut() {
		panelHaut = new JPanel(new GridBagLayout());
		panelHaut.setBorder(new TitledBorder("De"));

		utilsIHM.addComponentTo(getLblVeterinaire(), panelHaut, 0, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getCbVeterinaire(), panelHaut, 1, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getLblDate(), panelHaut, 2, 0, 1, 1, 1, true);

		utilsIHM.addComponentTo((JComponent) getDatePicker(), panelHaut, 4, 0, 1, 1, 1, true);

	}

	// --------------- CREATION DES COMPONENTS---------------------------

	private void createDatePicker() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

	}

	private void createCbVeterinaire() {
		String veterinaires[] = { "Vétérinaire 1", "Vétérinaire 2", "Vétérinaire 3", "Vétérinaire 4" };
		cbVeterinaire = new JComboBox<>(veterinaires);

	}

	private void createTablAgenda() {
		modelTablAgenda = TableAgendaModel.getInstance();
		tablAgenda = new JTable(modelTablAgenda);
		tablAgenda.setBorder(BorderFactory.createEtchedBorder());
		tablAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablAgenda.setRowHeight(30);
		tablAgenda.getSelectionModel().setSelectionInterval(0, 0);
		tablAgenda.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					rdvCourant = TableAgendaModel.getInstance().getValueAt(tablAgenda.getSelectedRow());
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(AgendaPanel.this, e1.getMessage(), "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			/* ** Méthodes non-utilisées ** */
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});

	}

	private void createBttDossierAnimal() {
		bttDossierMedical = new JButton("Dossier médical");
		bttDossierMedical.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdvCourant == null) {
					JOptionPane.showMessageDialog(AgendaPanel.this,
							"Avant d'ouvrir le dossier médical d'un animal, vous devez sélectionner un rendez-vous dans votre agenda.",
							"Attention", JOptionPane.WARNING_MESSAGE);
				} else {
					DossierMedicPanel panelDossierMedical = new DossierMedicPanel(rdvCourant);
					JInternalFrame jifDossierMedical = utilsIHM.createJIF("Dossier médical", panelDossierMedical);
					jifDossierMedical.setSize(800, 400);
					jifDossierMedical.setVisible(true);
					CliniqueVetoFrame2.getInstance("").getDesktop().add(jifDossierMedical);
					try {
						jifDossierMedical.setSelected(true);
					} catch (java.beans.PropertyVetoException eAjoutCli) {
					}
				}
			}
		});
	}

	private void createLblDate() {
		lblDate = new JLabel("Date");
		lblDate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}

	private void createLblVeterinaire() {
		lblVeterinaire = new JLabel("Vétérinaire");
		lblVeterinaire.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}

	// --------------- GETTERS--------------------------------

	public JLabel getLblVeterinaire() {
		return lblVeterinaire;
	}

	public JLabel getLblDate() {
		return lblDate;
	}

	public JComboBox<String> getCbVeterinaire() {
		return cbVeterinaire;
	}

	public JTable getTablAgenda() {
		return tablAgenda;
	}

	public TableAgendaModel getModelTablAgenda() {
		return modelTablAgenda;
	}

	public JButton getBttDossierMedical() {
		return bttDossierMedical;
	}

	public JPanel getPanelHaut() {
		return panelHaut;
	}

	public JScrollPane getPanelAgenda() {
		return panelAgenda;
	}

	public JPanel getPanelDossierMedical() {
		return panelDossierMedical;
	}

	public JDatePicker getDatePicker() {
		return datePicker;
	}

}
