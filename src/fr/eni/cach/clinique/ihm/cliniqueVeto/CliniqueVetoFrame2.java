package fr.eni.cach.clinique.ihm.cliniqueVeto;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.Agenda.AgendaPanel;
import fr.eni.cach.clinique.ihm.Personnels.GestPersonnelsPanel;
import fr.eni.cach.clinique.ihm.ecranClients.GestClientPanel;
import fr.eni.cach.clinique.ihm.priseRDV.PriseRDVPanel;

public class CliniqueVetoFrame2 extends JFrame {
	
	// *********** ATTRIBUTS ****************************
	
	private static final long serialVersionUID = 6842296107317141423L;

	/**
	 * on définit une taille en pixels (int) qui servira à placer le coin haut
	 * gauche de la fenêtre
	 */
	private int inset = 50;

	/**
	 * Partie dans laquelle vont se placer les JInternalFrames
	 */
	private JDesktopPane desktop;
	
	/**
	 * Singelton pour des méthodes utilitaires :
	 * .getInstance() -> renvoie l'instance du singleton
	 * .addComponentTo() -> ajoute de manière automatiques des components dans un GridBaglayout
	 * .createJIF() -> créateur de JInternalFrames
	 * 
	 */
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JMenuBar monMenuBar;
	private JMenu jmFichier;
	private JMenu jmGestion;
	private JMenu jmAgenda;
	private JMenu jmPersonnel;
	private JMenuItem miDeco;
	private JMenuItem miQuitter;
	private JMenuItem miPriseRDV;
	private JMenuItem miGestClient;
	private JMenuItem miAgenda;
	private JMenuItem miGestPersonnel;
	
	// *********** SINGLETON ****************************
	
	/**
	 * Instance unique (SINGLETON) de la fenêtre Clinique Veto
	 */
	private static CliniqueVetoFrame2 instance = null;
	
	/**
	 * Renvoie l'instance unique de CliniqueVeto 
	 * Pour celà il faut lui donner le rôle de l'utilisateur 
	 * afin que l'affichage initial soit bien paramétré
	 * @param userRole rôle de l'utilisateur actuel
	 * @return
	 */
	public static CliniqueVetoFrame2 getInstance(String userRole){
		if (instance == null){
			instance = new CliniqueVetoFrame2(userRole);
		}
		return instance;
	}
	
	/**
	 * Constructeur de la fenêtre Clinique Vétérinaire
	 * Fenêtre Pincipale de l'appli Clinque Vétérinaire
	 * Permettant, après connexion, de naviguer dans l'application
	 */
	private CliniqueVetoFrame2(String userRole) {
		
		/* ****** INITIALISATIONs ******/
		
		// Donne le titre de la fenêtre
		super("Clinique Vétérinaire");

		// définit le mode de fermeture
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// permet de parametrer la taille de la fenêtre :
		// -> Récupère la taille de l'écran
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// -> détermine la taille de la fenêtre (position x du coin haut gauche,
		// position y du coin gauche, largeur, hauteur)
		this.setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

		
		/* ****** CREATIONs ******/
		
		// création de desktop :
		desktop = new JDesktopPane();

		//création de la Barre de menus :
		//JMenuItems
		createMiQuitter();
		createMiDeco();
		createMiGestClient();
		createMiPriseRDV();
		createMiAgenda();
		createMiGestPersonnel();
		//JMenus
		createJmFichier();
		createJmGestion();
		createJmAgenda();
		createJmPersonnel();
		//JMenuBar
		createMonMenuBar();
		
		
		
		/* ****** SETs ******/
		
		//détermination de la barre de menu :
		this.setJMenuBar(getMonMenuBar());
		
		// détermination de la méthode de dragNdrop des fenêtres dans le desktop
		// LIVE_DRAG_MODE ou OUTLINE_DRAG_MODE (OUTLINE -> meilleures perf')
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		
		// on ajoute, selon le rôle de l'utilisateur, une seconde fenêtre utilitaire :
		switch (userRole) {
		case "vet" :
			AgendaPanel panelAgenda = new AgendaPanel();
			JInternalFrame jifAgenda = utilsIHM.createJIF("Agenda", panelAgenda);
			jifAgenda.setVisible(true);
			desktop.add(jifAgenda);
			try {
				jifAgenda.setMaximum(true);
				} catch (PropertyVetoException e) {}
			break;
		case "adm" : 
			GestPersonnelsPanel panelGestPersonnel = new GestPersonnelsPanel();
			JInternalFrame jifGestPesonnel = utilsIHM.createJIF("Gestion du Personnel", panelGestPersonnel);
			jifGestPesonnel.setVisible(true);
			desktop.add(jifGestPesonnel);
			try {
				jifGestPesonnel.setMaximum(true);
				} catch (PropertyVetoException e) {}
			break;
		case "sec" : //cas par défaut : secrétaire
			PriseRDVPanel panelPriseRDV = new PriseRDVPanel();
			JInternalFrame jifPriseRDV = utilsIHM.createJIF("Prise de Rendez-Vous", panelPriseRDV);
			jifPriseRDV.setVisible(true);
			desktop.add(jifPriseRDV);
			try {
				jifPriseRDV.setMaximum(true);
				} catch (PropertyVetoException e) {}
			break;
		default : 
			
			break;
		}
		
		// on définit desktop comme étant le pane par défaut
		this.setContentPane(desktop);
		
	}
	
	// *********** CREATION BARRE DE MENU ***************

	/**
	 * Crée la Barre de Menus :
	 * FICHIER 
	 * 		-> Deconnexion
	 * 		-> Quitter
	 * GESTION RDV
	 * 		-> Prise RDV
	 * 		-> Gestion Clients
	 * AGENDA
	 * 		-> Agenda
	 * GESTION PERSONNEL
	 * 		-> Gestion Personnel
	 */
	private void createMonMenuBar() {
		
		monMenuBar = new JMenuBar();
		
	

        //Ajout du menu déroulant FICHIER:
        monMenuBar.add(getJmFichier());

        //Ajout du menu déroulant GESTION CLIENT: 
        monMenuBar.add(getJmGestion());
        
        //Ajout du menuItem AGENDA:
        monMenuBar.add(getJmAgenda());
        
        //Ajout du menuItem GESTTION PERSONNEL:
        monMenuBar.add(getJmPersonnel());

  
       
	}
	
	// *********** CREATION JMENUS **********************
	
	/**
	 * Crée le menu déroulant FICHIER
	 * Contient les fonctions Déconnexion et Quitter
	 */
	private void createJmFichier(){
		jmFichier = new JMenu("Fichier");
		//raccourci alt+D
		jmFichier.setMnemonic(KeyEvent.VK_D);
		//ajout du MenuItem déconnexion
		jmFichier.add(getMiDeco());
		//ajout du MenuItem quitter
		jmFichier.add(getMiQuitter());
		
	}
	
	/**
	 * Crée le menu déroulant GESTION RDV
	 * Contient les fonctions Prise RDV et Gestion Client
	 */
	private void createJmGestion(){
		jmGestion = new JMenu("Gestion Rendez-Vous");
		//ajout du MenuItem Prise de RDV
		jmGestion.add(getMiPriseRDV());
		//ajout du MenuItem Gestion Client
		jmGestion.add(getMiGestClient());
	}
	
	/**
	 * Crée le menu déroulant Gestion Personnel
	 */
	private void createJmPersonnel() {
		jmPersonnel = new JMenu("Gestion Personnel");
		jmPersonnel.add(miGestPersonnel);
	}

	/**
	 * Crée le menu déroulant Agenda
	 */
	private void createJmAgenda() {
		jmAgenda = new JMenu("Agenda");
		jmAgenda.add(miAgenda);
	}
	
	// *********** CREATION JMENUITEMS ******************

	/**
	 * Crée le MenuItem Agenda 
	 * (au clic) ouvre la fenêtre Agenda
	 */
	private void createMiAgenda() {
		miAgenda = new JMenuItem("Agenda");
		miAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AgendaPanel panelAgenda = new AgendaPanel();
				
				JInternalFrame jifAgenda = utilsIHM.createJIF("Agenda", panelAgenda);
				jifAgenda.setVisible(true);
				desktop.add(jifAgenda);	
				//Permet de mettre la JInternalFrame créée au premier plan
				try {
					jifAgenda.setMaximum(true);
					jifAgenda.setSelected(true);
		        } catch (java.beans.PropertyVetoException eAgenda) {
		        	
		        }
			}
		});
	}
	
	/**
	 * Crée le MenuItem Déco
	 * (au clic) déconnecte l'utilisateur courant
	 *  et réaffiche la fenêtre de connexion
	 */
	private void createMiDeco(){
		miDeco = new JMenuItem("Déconnexion");
	}
	
	/**
	 * Crée le MenuItem Quitter
	 * (au clic) quitte l'application
	 */
	private void createMiQuitter(){
		miQuitter = new JMenuItem("Quitter");
        miQuitter.setMnemonic(KeyEvent.VK_Q);
        miQuitter.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        
        miQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//quitte l'application
				 System.exit(0);
			}
		});;
	}
	
	/**
	 * Crée le MenuItem Gestion Client
	 * (au clic) ouvre la fenêtre de Gestion des Clients
	 */
	private void createMiGestClient(){
		miGestClient = new JMenuItem("Gestion des Clients");
		miGestClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GestClientPanel panelGestClient = new GestClientPanel();
				JInternalFrame jifGestClient = utilsIHM.createJIF("Gestion des Clients", panelGestClient);
				jifGestClient.setVisible(true);
				desktop.add(jifGestClient);
				try {
					jifGestClient.setMaximum(true);
					jifGestClient.setSelected(true);
		        } catch (java.beans.PropertyVetoException eGestCli) {
		        	
		        }
			}
		});
	}
	
	/**
	 * Crée le MenuItem de Prise de RDV
	 * (au clic) ouvre la fenêtre de Prise de RDV
	 */
	private void createMiPriseRDV() {
		miPriseRDV = new JMenuItem("Prise de Rendez-Vous");
		miPriseRDV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PriseRDVPanel panelPriseRDV = new PriseRDVPanel();
				JInternalFrame jifPriseRDV = utilsIHM.createJIF("Prise de Rendez-Vous", panelPriseRDV);
				jifPriseRDV.setVisible(true);
				desktop.add(jifPriseRDV);
				try {
					jifPriseRDV.setMaximum(true);
					jifPriseRDV.setSelected(true);
		        } catch (java.beans.PropertyVetoException ePriseRDV) {
		        	
		        }
			}
		});
	}
	
	/**
	 * Crée le Menuitem de Gestion de Personnel
	 * (au clic) ouvre la fenêtre de Gestion du Personnel
	 */
	private void createMiGestPersonnel() {
		miGestPersonnel = new JMenuItem("Gestion Personnel");
		miGestPersonnel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GestPersonnelsPanel panelGestPersonnel = new GestPersonnelsPanel();
				JInternalFrame jifGestPesonnel = utilsIHM.createJIF("Gestion du Personnel", panelGestPersonnel);
				jifGestPesonnel.setVisible(true);
				desktop.add(jifGestPesonnel);
				try {
					jifGestPesonnel.setMaximum(true);
					jifGestPesonnel.setSelected(true);
		        } catch (java.beans.PropertyVetoException eGestPerso) {
		        	
		        }
			}
		});
	}
	
	// *********** GETTERS ******************************
	
	/**
	 * Renvoie la barre des menus
	 * (détail dans la JavaDoc de création)
	 * @return
	 */
	public JMenuBar getMonMenuBar() {
		return monMenuBar;
	}
	
	/**
	 * Renvoie le menu Fichier
	 * (détail dans la JavaDoc de création)
	 * @return
	 */
	public JMenu getJmFichier() {
		return jmFichier;
	}
	
	/**
	 * Renvoie le menu GESTION
	 * (détail dans la JavaDoc de création)
	 * @return
	 */
	public JMenu getJmGestion(){
		return jmGestion;
	}

	/**
	 * Renvoie le menuItem Agenda
	 * (détail dans la JavaDoc de création)
	 * @return
	 */
	public JMenuItem getMiAgenda(){
		return miAgenda;
	}

	/**
	 * Renvoie le menuItem Déconnexion
	 * (détail dans la JavaDoc de création
	 * @return
	 */
	public JMenuItem getMiDeco(){
		return miDeco;
	}

	/**
	 * Renvoie le menuItem Quitter
	 * (détail dans la JavaDoc de création
	 * @return
	 */
	public JMenuItem getMiQuitter() {
		return miQuitter;
	}

	/**
	 * Renvoie le menuItem Prise de RDV
	 * (détail dans la JavaDoc de création
	 * @return
	 */
	public JMenuItem getMiPriseRDV() {
		return miPriseRDV;
	}

	/**
	 * Renvoie le menuItem Gestion Client
	 * (détail dans la JavaDoc de création
	 * @return
	 */
	public JMenuItem getMiGestClient(){
		return miGestClient;
	}
	
	/**
	 * Renvoie le menuItem Gestion Personnel
	 * (détail dans la JavaDoc de création
	 * @return
	 */
	public JMenuItem getMiGestPersonnel(){
		return miGestPersonnel;
	}

	public JDesktopPane getDesktop() {
		return desktop;
	}

	public JMenu getJmAgenda() {
		return jmAgenda;
	}

	public JMenu getJmPersonnel() {
		return jmPersonnel;
	}
	
}
