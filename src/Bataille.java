import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Bataille {
	public int t1;
	public int t2;
	
	public int territoireAttaquant(Carte c, Joueur j, ActionOrdi o) {
		int i1 = o.click(c);
		
		while (!j.contientListe(i1)) {
			i1 = o.click(c);
		}
		this.t1 = i1;
		return i1;
	}
	
	public int territoireAttaque(Carte c, Joueur j, ActionOrdi o, String [] tab, String t1) {
		int i2 = o.click(c);
		String t2 = tab[i2];
		
		boolean b = c.verifCorrespondance(t1, t2);
		while (!b || j.contientListe(i2)) {
			i2 = o.click(c);
			t2 = tab[i2];
			b = c.verifCorrespondance(t1, t2);
		}
		this.t2 = i2;
		return i2;
	}

	public ArrayList bataille(Joueur j, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, Carte c, String [] tab, Armee [] tabArmee, int n) {
		ActionOrdi o = new ActionOrdi();
		int i1 = territoireAttaquant(c, j, o);
		String t1 = tab[i1];
		
		int i2 = territoireAttaque(c, j, o, tab, t1);
		String t2 = tab[i2];
		
		int iJ1 = j.getIndex();
		c.afficherMessage("Joueur " + iJ1, t1 + " attaque " + t2, "", "");
		StdDraw.pause(2000);
		Armee a1 = tabArmee[i1];
		ArrayList al1 = armeeAttaquant(o, c, tabArmee, j1, j2, j3, j4, j5, j6, n, a1);
		
		ArrayList listeAttaquant = recupListe(al1, 0);
		ArrayList attaquantPuissance = recupListe(al1, 1);

		int iJ2 = joueurDefense (j1, j2, j3, j4, j5, j6, i2);
		Armee a2 = tabArmee[i2];	
		
		ArrayList al2 = armeeDefense(o, c, tabArmee, j1, j2, j3, j4, j5, j6, n, a2);
		
		ArrayList listeDefense = recupListe(al2, 0);
		ArrayList defensePuissance = recupListe(al2, 1);
		
		ArrayList vainqueur = combat(listeAttaquant, listeDefense, attaquantPuissance, defensePuissance, a1, a2);
		return vainqueur;
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
			if (uniteRepos - cvBataille > 1) {
				nombreUnite += cvBataille;
				uniteRepos = uniteRepos - cvBataille;
				
				for (int k1 = 0; k1 < cvBataille; k1++) {
					al.add((String) "cavalier");
					int p = puissanceUnite("cavalier");
					al.add((int) p);
				}
			}
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int sBataille = choixNombreUnite("soldat", s, c, o, nombreUnite, 3);
			if (uniteRepos - sBataille > 1) {
				nombreUnite += sBataille;
				uniteRepos = uniteRepos - sBataille;
				
				for (int k2 = 0; k2 < sBataille; k2++) {
					al.add((String) "soldat");
					int p = puissanceUnite("soldat");
					al.add((int) p);
				}
			}
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int cnBataille = choixNombreUnite("canon", cn, c, o, nombreUnite, 3);
			if (uniteRepos - cnBataille > 1) {
				nombreUnite += cnBataille;
				uniteRepos = uniteRepos - cnBataille;
				
				for (int k3 = 0; k3 < cnBataille; k3++) {
					al.add((String) "canon");
					int p = puissanceUnite("canon");
					al.add((int) p);
				}
			}
			
			al = ordrePuissance(al);
		}
		return al;
	}
	
	public ArrayList armeeDefense(ActionOrdi o, Carte c, Armee [] tabArmee, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int n, Armee a1) {
		int nombreUnite = 0;
		ArrayList al = new ArrayList();
		
		c.AfficherCarte();
		c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
		
		int cn = a1.getCanon();
		int cv = a1.getCavalier();
		int s = a1.getSoldat();
		
		int sBataille = 0;
		if (s < 2) {
			sBataille = s;
		}
		else {
			sBataille = 2;
		}
		nombreUnite += sBataille;	
		
		for (int k2 = 0; k2 < sBataille; k2++) {
			al.add("soldat");
			int p = puissanceUnite("soldat");
			al.add(p);
		}
		
		if (nombreUnite == 2) {
			al = ordrePuissance(al);
			return al;
		}			
		
		int cnBataille = 0;
		if (nombreUnite + cn < 2) {
			cnBataille = cn;
		}
		else {
			cnBataille = 2 - nombreUnite;
		}
		nombreUnite += cnBataille;
		
		for (int k3 = 0; k3 < cnBataille; k3++) {
			al.add("canon");
			int p = puissanceUnite("canon");
			al.add(p);
		}
		
		if (nombreUnite == 2) {
			al = ordrePuissance(al);
			return al;
		}	
		
		int cvBataille = 0;
		if (nombreUnite + cv < 2) {
			cvBataille = cv;
		}
		else {
			cvBataille = 2 - nombreUnite;
		}
		nombreUnite += cvBataille;
		
		for (int k1 = 0; k1 < cvBataille; k1++) {
			al.add("cavalier");
			int p = puissanceUnite("cavalier");
			al.add(p);
		}
		
		al = ordrePuissance(al);
		return al;
	}
	
	public ArrayList recupListe(ArrayList al, int i) {
		ArrayList liste = new ArrayList();
		if (i == 0) {
			for (int k1 = 0; k1 < al.size(); k1 = k1 + 2) {
				String u = (String) al.get(k1);
				liste.add(u);
			}
		}
		else {
			for (int k2 = 0; k2 < al.size(); k2 = k2 + 2) {
				int p = (int) al.get(k2+1);
				liste.add(p);
			}
		}
		return liste;
	}
	
	public int choixNombreUnite(String unite, int u, Carte c, ActionOrdi o, int n, int max) {
		int attaquant = 0;
		if (u != 0 || max == 2) {
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

	public ArrayList combat(ArrayList listeAtt, ArrayList listeDef, ArrayList puissanceAtt, ArrayList puissanceDef, Armee a1, Armee a2) {
		ArrayList vainqueur = new ArrayList();
		ArrayList defaite = new ArrayList();
		int s1 = listeAtt.size();
		int s2 = listeDef.size();
		int s = Math.min(s1, s2);
		for (int k = 0; k < s; k++) {
			String u1 = (String) listeAtt.get(k);
			String u2 = (String) listeDef.get(k);
			int p1 = (int) puissanceAtt.get(k);
			int p2 = (int) puissanceDef.get(k);
			if (p1 <= p2) {
				uniteDetruite(u1, a1);
			}
			else {
				uniteDetruite(u2, a2);
				vainqueur.add(u1);
			}
		}
		int puissanceArmee2 = a2.puissance();
		if (puissanceArmee2 == 0) {
			if (s1 - s > 0) {
				for (int i = s; i < s1; i++) {
					String u = (String) listeAtt.get(i);
					vainqueur.add(u);
				}
			}
			return vainqueur;
		}
		else {
			return defaite;
		}
	}
	
	public int puissanceUnite(String u) {
		int p;
		switch (u) {
		case "soldat" :
			p = (int) (6*Math.random()) + 1;
			break;
		case "cavalier" :
			p = (int) (6*Math.random()) + 2;
			break;
		default :
			p = (int) (6*Math.random()) + 4;
			break;
		}
		return p;
	}

	public void uniteDetruite(String u, Armee a) {
		switch(u) {
		case "soldat" :
			a.setSoldat(-1);
			break;
		case "cavalier" :
			a.setCavalier(-1);
			break;
		default :
			a.setCanon(-1);	
		}
	}

	public ArrayList ordrePuissance(ArrayList al) {
		int p1, p2, p3;
		String u1, u2, u3;
		int u = al.size();
		switch (u) {
		case 4 :
			p1 = (int) al.get(1);
			u1 = (String) al.get(0);
			p2 = (int) al.get(3);
			u2 = (String) al.get(2);
			int max = Math.max(p1, p2);
			if (p1 < p2) {
				al.remove(1);
				al.add(1, p2);
				
				al.remove(0);
				al.add(0, u2);
				
				al.remove(3);
				al.add(3, p1);
				
				al.remove(2);
				al.add(2, u1);
			}
			break;
		case 6 :
			p1 = (int) al.get(1);
			u1 = (String) al.get(0);
			p2 = (int) al.get(3);
			u2 = (String) al.get(2);
			p3 = (int) al.get(5);
			u3 = (String) al.get(4);
			int max1 = Math.max(p1, p2);
			if (p1 < p2) {
				al.remove(1);
				al.add(1, p2);
				
				al.remove(0);
				al.add(0, u2);
				
				al.remove(3);
				al.add(3, p1);
				
				al.remove(2);
				al.add(2, u1);
				int c1 = p1;
				String s1 = u1;
				p1 = p2;
				u1 = u2;
				p2 = c1;
				u2 = s1;
			}
			int max2 = Math.max(p2, p3);
			if (p1 < p2) {
				al.remove(3);
				al.add(3, p3);
				al.remove(5);
				al.add(5, p2);
				int c2 = p2;
				String s2 = u2;
				p2 = p3;
				u2 = u3;
				p3 = c2;
				u3 = s2;
			}
			int max3 = Math.max(p1, p3);
			if (p1 < p3) {
				al.remove(1);
				al.add(1, p3);
				al.remove(5);
				al.add(5, p1);
			}
			break;
		}
		return al;
	}

}
