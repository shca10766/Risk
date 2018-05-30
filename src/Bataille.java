import java.util.ArrayList;

public class Bataille {

	public void bataille(Joueur j, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, Carte c, String [] tab, Armee [] tabArmee, int n) {
		ActionOrdi o = new ActionOrdi();
		int i1 = o.click(c);
		
		while (!j.contientListe(i1)) {
			i1 = o.click(c);
		}
		String t1 = tab[i1];
		
		int i2 = o.click(c);
		String t2 = tab[i2];
		
		boolean b = c.verifCorrespondance(t1, t2);
		while (!b || j.contientListe(i2)) {
			i2 = o.click(c);
			t2 = tab[i2];
			b = c.verifCorrespondance(t1, t2);
		}
		
		int iJ1 = j.getIndex();
		c.afficherMessage("Joueur " + iJ1, "Votre territoire " + t1 + " attaque le territoire " + t2, "", "");
		Armee a1 = tabArmee[i1];
		ArrayList al1 = armeeAttaquant(o, c, tabArmee, j1, j2, j3, j4, j5, j6, n, a1);
		
		int iJ2 = joueurDefense (j1, j2, j3, j4, j5, j6, i2);
		Armee a2 = tabArmee[i2];	
		
		c.AfficherCarte();
		c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
		
		c.afficherMessage("Joueur " + iJ2, "Défendez votre territoire", "", "");
		
		ArrayList al2 = armeeDefense(o, c, tabArmee, j1, j2, j3, j4, j5, j6, n, a2);
	}
	
	public int joueurDefense(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int i) {
		if (j1.contientListe(i)) {return 1;}
		else if (j2.contientListe(i)) {return 2;}
		else if (j3.contientListe(i)) {return 3;}
		else if (j4.contientListe(i)) {return 4;}
		else if (j5.contientListe(i)) {return 5;}
		else {return 6;}
	}
	
	public ArrayList armeeAttaquant(ActionOrdi o, Carte c, Armee [] tabArmee, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int n, Armee a1) {
		int nombreUnite = 0;
		ArrayList al = new ArrayList();
		
		int cn = a1.getCanon();
		int cv = a1.getCavalier();
		int s = a1.getSoldat();
		
		int uniteRepos = cn + cv + s;
		
		while (nombreUnite == 0 && uniteRepos > 1 ) {
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int cvBataille = choixNombreUnite("cavalier", cv, c, o, nombreUnite, 3);
			verif1Repos("cavalier", uniteRepos, cvBataille, nombreUnite, al);
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int sBataille = choixNombreUnite("soldat", s, c, o, nombreUnite, 3);
			verif1Repos("soldat", uniteRepos, sBataille, nombreUnite, al);
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int cnBataille = choixNombreUnite("canon", cn, c, o, nombreUnite, 3);
			verif1Repos("canon", uniteRepos, cnBataille, nombreUnite, al);
		}
		return al;
	}
	
	public ArrayList armeeDefense(ActionOrdi o, Carte c, Armee [] tabArmee, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int n, Armee a1) {
		int nombreUnite = 0;
		ArrayList al = new ArrayList();
		
		while (nombreUnite == 0) {
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int cn = a1.getCanon();
			int cv = a1.getCavalier();
			int s = a1.getSoldat();
			
			int sBataille = choixNombreUnite("soldat", s, c, o, nombreUnite, 2);
			nombreUnite += sBataille;	
			
			for (int k2 = 0; k2 < sBataille; k2++) {
				al.add("soldat");
			}
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int cnBataille = choixNombreUnite("canon", cn, c, o, nombreUnite, 2);
			nombreUnite += cnBataille;
			
			for (int k3 = 0; k3 < cnBataille; k3++) {
				al.add("canon");
			}
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int cvBataille = choixNombreUnite("cavalier", cv, c, o, nombreUnite, 2);
			nombreUnite += cvBataille;
			
			for (int k1 = 0; k1 < cvBataille; k1++) {
				al.add("cavalier");
			}
		}
		return al;
	}
	
	public int choixNombreUnite(String unite, int u, Carte c, ActionOrdi o, int n, int max) {
		int attaquant = 0;
		if (u != 0) {
			c.afficherMessage("Combien de " + unite + " dans la bataille ?", "", "", "");
			attaquant = o.touchePresse();
			while (attaquant > u) {
				attaquant = o.touchePresse();
			}
		}
		if (n + attaquant > max) {
			attaquant = 0;
			c.afficherMessage("", "Vous dépassez le nombre d'unité", "(maximum "+ max +" unités)", "");
		}
		else {
			c.afficherMessage("", attaquant + " " + unite +"(s) dans la bataille", "", "");
		}
		return attaquant;
	}
	
	public void verif1Repos(String unite, int uniteRepos, int uBataille, int nombreUnite, ArrayList al) {
		if (uniteRepos - uBataille > 1) {
			nombreUnite += uBataille;
			uniteRepos = uniteRepos - uBataille;
			
			for (int k = 0; k < uBataille; k++) {
				al.add(unite);
			}
		}
	}
}
