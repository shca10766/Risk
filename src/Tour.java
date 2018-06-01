import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Tour {

	Joueur j;
	
	public Tour(Joueur J) {
		this.j = J;
	}
	
	public void tour(int nombreTour, Carte c, ActionOrdi o, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, String [] tab, Armee [] tabArmee, int n) {
		c.AfficherCarte();
		c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
		
		if (nombreTour != 0) {
			Renfort renf = new Renfort(j);
			renf.renfort(n, c, tabArmee, tab, j1, j2, j3, j4, j5, j6);
			j.reinitTerritoireCapture();
		}
		
		c.afficherMessage("Que voulez vous faire", "Tapez 1 : Bataille", "Tapez 2 : Deplacement", "Tapez 0 : Rien");
		int choix = o.touchePresse();
		
		while (choix != 0) {
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			if (choix == 1) {
				Bataille bat = new Bataille();
				ArrayList vainqueur = bat.bataille(j, j1, j2, j3, j4, j5, j6, c, tab, tabArmee, n);
				int territoireAttaquant = bat.t1;
				int territoireAttaque = bat.t2;
				
				if (!vainqueur.isEmpty()) {
					j.setTerritoireCapture();
					Deplacement dep = new Deplacement(j1, vainqueur, territoireAttaque, territoireAttaquant);
					dep.invasion(tabArmee, j1, j2, j3, j4, j5, j6);
				}
			}
			
			else if (choix == 2) {
				Deplacement dep1 = new Deplacement(j1);
				dep1.deplacement(c, tabArmee, tab);
			}
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			c.afficherMessage("Que voulez vous faire", "Tapez 1 : Bataille", "Tapez 2 : Deplacement", "Tapez 0 : Rien");
			choix = o.touchePresse();	
		}
		
		c.AfficherCarte();
		c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
		c.afficherMessage("Fin du tour !", "", "", "");
		StdDraw.pause(2000);
	}
}
