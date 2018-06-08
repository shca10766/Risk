import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

public class Tour {

	private Joueur j;
	
	public Tour(Joueur J) {
		this.j = J;
	}
	
	public void tour(int nombreTour, Carte c, ActionOrdi o, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, String [] tab, Armee [] tabArmee, int n) {
		
		int i = j.getIndex();
		
		// Si l'on est plus au premier tour on ajoute des renforts
		if (nombreTour != 0) {
			c.afficherTerritoire();
			c.afficherMessage("Ajouter vos renforts", "Cliquez sur un de vos territoires", "pour cela", "");
			Renfort renf = new Renfort(j);
			renf.renfort(n, c, tabArmee, tab);
			j.reinitTerritoireCapture();
		}
		
		// Choix du type d'action
		c.afficherTerritoire();
		c.afficherMessage("Joueur " + i + " que voulez vous faire", "Tapez 1 : Bataille", "Tapez 2 : Deplacement", "Tapez 0 : Fin du tour");
		int choix = o.touchePresse();
		
		// Tant que le joueur ne décide pas d'arrêter il continue son tour
		while (choix != 0) {
			
			c.afficherTerritoire();
			
			// Joueur choisit la bataille
			if (choix == 1) {
				c.afficherMessage("Joueur " + i, "Choisissez un de vos territoires", "pour attaquer un territoire voisin", "");
				Bataille bat = new Bataille(j);
				ArrayList vainqueur = bat.bataille(j1, j2, j3, j4, j5, j6, c, tab, tabArmee, n);
				int territoireAttaquant = bat.t1;
				int territoireAttaque = bat.t2;
				
				// Si le joueur a réussi à détruire toutes les unités ennemies il y a déplacement 
				if (!vainqueur.isEmpty()) {
					j.setTerritoireCapture();
					Deplacement dep = new Deplacement(j, vainqueur, territoireAttaque, territoireAttaquant);
					dep.invasion(tabArmee, j1, j2, j3, j4, j5, j6);
				}
			}
			
			// Joueur choisit le déplacement
			else if (choix == 2) {
				Deplacement dep1 = new Deplacement(j);
				dep1.deplacement(c, tabArmee, tab);
			}
			
			else {
				c.afficherTerritoire();
				c.afficherMessage("Entrée invalide", "Veuillez réessayer", "", "");
				StdDraw.pause(1500);
			}
			
			// Choisit une nouvelle action
			c.afficherTerritoire();
			c.afficherMessage("Que voulez vous faire", "Tapez 1 : Bataille", "Tapez 2 : Deplacement", "Tapez 0 : Fin du tour");
			choix = o.touchePresse();	
		}
		
		// Réinitialisation de tous les mouvements à 0
		for (int j = 0; j < tabArmee.length; j++) {
			Armee a = tabArmee[j];
			a.reinitMouvement();
		}
		
		c.afficherTerritoire();
		c.afficherMessage("Fin du tour !", "", "", "");
		StdDraw.pause(2000);
	}
}
