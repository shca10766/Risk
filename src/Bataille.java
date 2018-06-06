import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Bataille {
	public int t1;
	public int t2;
	private Joueur j;
	
	public Bataille(Joueur J) {this.j = J;}
	
	// Territoire attaquant
	public int TerritoireAttaquant(Carte c,  ActionOrdi o, Armee [] tabArmee) {
		int t = o.click(c);
		
		Armee a = new Armee();
		if (t != -1 ) {
			a = tabArmee[t];
		}
		
		while (!j.contientListe(t) || a.puissance() <= 1) { // V�rifier que le territoire attaquant est poss�d� par le joueur
			t = o.click(c);
			if (t != -1 ) {
				a = tabArmee[t];
			}
		}
		
		this.t1 = t;
		return t;
	}
	
	public int TerritoireAttaque(Carte c, ActionOrdi o) {
		int t = o.click(c);
		
		while (j.contientListe(t) && t == -1) { // V�rifier que le territoire attaqu� n'est pas poss�d� par le joueur
			t = o.click(c);
		}
		this.t2 = t;
		return t;
	}

	public ArrayList<Integer> bataille(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, Carte c, String [] tabTerritoire, Armee [] tabArmee, int n) {
		ActionOrdi o = new ActionOrdi();
		int territoireAttaquant = TerritoireAttaquant(c, o, tabArmee);		
		int territoireDefense = TerritoireAttaque(c, o);
		
		String tAttaquant = tabTerritoire[territoireAttaquant];
		String tDefense = tabTerritoire[territoireDefense];
		boolean territoireVoisin = c.verifCorrespondance(tAttaquant, tDefense);
		
		// V�rifier que les deux territoires sont voisins
		while (!territoireVoisin) {
			territoireDefense = TerritoireAttaque(c, o);
			tDefense = tabTerritoire[territoireDefense];
			territoireVoisin = c.verifCorrespondance(tAttaquant, tDefense);
		}
		
		int joueurAttaqaunt = j.getIndex();
		c.afficherMessage("", "", "", tAttaquant + " attaque " + tDefense);
		StdDraw.pause(2000);
		
		Armee armeeAttaquant = tabArmee[territoireAttaquant];
		// Cr�ation de l'arm�e attaquante
		ArrayList al1 = armeeAttaquant(o, c, tabArmee, j1, j2, j3, j4, j5, j6, n, armeeAttaquant);
		
		// S�paration des types d'unit� et de leur puissance d'attaque
		ArrayList listeAttaquant = recupListe(al1, 0); // Que des variables de type String
		ArrayList attaquantPuissance = recupListe(al1, 1); // Que des variables du type int

		// D�terminer l'index du joueur attaqu�
		int joueurDefense = joueurDefense (j1, j2, j3, j4, j5, j6, territoireDefense);
		Armee armeeDefense = tabArmee[territoireDefense];	
		
		// Cr�ation de l'arm�e d�fensive
		ArrayList al2 = armeeDefense(armeeDefense);
		
		// S�paration des types d'unit� et de leur puissance d'attaque
		ArrayList listeDefense = recupListe(al2, 0);
		ArrayList defensePuissance = recupListe(al2, 1);
		
		c.AfficherCarte();
		c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
		afficherCombat(c, listeAttaquant, listeDefense, attaquantPuissance, defensePuissance);
		
		// R�alise le combat entre les deux arm�es
		ArrayList vainqueur = combat(listeAttaquant, listeDefense, attaquantPuissance, defensePuissance, armeeAttaquant, armeeDefense);
		return vainqueur;
	}
	
	// D�terminer quel joueur poss�de le territoire correspondant
	public int joueurDefense(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int indexTerritoire) {
		if (j1.contientListe(indexTerritoire)) {return 1;}
		else if (j2.contientListe(indexTerritoire)) {return 2;}
		else if (j3.contientListe(indexTerritoire)) {return 3;}
		else if (j4.contientListe(indexTerritoire)) {return 4;}
		else if (j5.contientListe(indexTerritoire)) {return 5;}
		else {return 6;}
	}
	
	// Cr�ation de l'arm�e attaquante
	public ArrayList armeeAttaquant(ActionOrdi o, Carte c, Armee [] tabArmee, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int n, Armee armeeAttaquant) {
		int nombreUnite = 0;
		ArrayList al = new ArrayList();
		
		int cn = armeeAttaquant.getCanon();
		int cv = armeeAttaquant.getCavalier();
		int s = armeeAttaquant.getSoldat();
		
		// Toutes les unit�s ne pouvant pas se d�placer
		int cn0 = armeeAttaquant.getCanon0Mouvement();
		int cv0 = armeeAttaquant.getCavalier0Mouvement();
		int s0 = armeeAttaquant.getSoldat0Mouvement();
		
		int uniteRepos = cn + cv + s;
		
		// Si il reste une unit� dans le territoire et qu'il reste des unit�s qui peuvent se d�placer
		if (uniteRepos > 1 && (cn0 + cv0 + s0 != cn + cv + s)) {
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			// Ordre pour lequel on demande le nombre d'attaquant pour chaque unit� donne l'ordre de la priorit� d'Attaque
			
			int cvBataille = choixNombreUnite("cavalier", cv, cv0, c, o, nombreUnite);
			// Si l'on ne prend pas d'unit� qui soit au repos
			if (uniteRepos - cvBataille >= 1) {
				nombreUnite += cvBataille;
				uniteRepos = uniteRepos - cvBataille;
				
				// On ajoute pour chaque unit� choisie son type et sa puissance d'attaque
				for (int k1 = 0; k1 < cvBataille; k1++) {
					al.add((String) "cavalier");
					int p = puissanceUnite("cavalier");
					al.add((int) p);
				}
			}
			
			c.AfficherCarte();
			c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);
			
			int sBataille = choixNombreUnite("soldat", s, s0, c, o, nombreUnite);
			if (uniteRepos - sBataille >= 1) {
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
			
			int cnBataille = choixNombreUnite("canon", cn, cn0, c, o, nombreUnite);
			if (uniteRepos - cnBataille >= 1) {
				nombreUnite += cnBataille;
				uniteRepos = uniteRepos - cnBataille;
				
				for (int k3 = 0; k3 < cnBataille; k3++) {
					al.add((String) "canon");
					int p = puissanceUnite("canon");
					al.add((int) p);
				}
			}
			// Trier la liste de l'arm�e attaquante en fonction de la puissance d'attaque de l'unit�
			al = ordrePuissance(al);
		}
		return al;
	}
	
	public ArrayList armeeDefense(Armee a1) {
		int nombreUnite = 0;
		ArrayList al = new ArrayList();
		
		int cn = a1.getCanon();
		int cv = a1.getCavalier();
		int s = a1.getSoldat();
		
		// Ajout d'abord de soldat s'ils existent
		int sBataille = 0;
		if (s < 2) { // Si il y a moins de deux soldats
			sBataille = s;
		}
		else {
			sBataille = 2;
		}
		nombreUnite += sBataille;	
		
		// On ajoute pour chaque unit� choisie son type et sa puissance d'attaque
		for (int k2 = 0; k2 < sBataille; k2++) {
			al.add("soldat");
			int p = puissanceUnite("soldat");
			al.add(p);
		}
		
		if (nombreUnite == 2) { // Si on a notre nombre de d�fenseur maximum (2)
			al = ordrePuissance(al);
			return al;
		}			
		
		int cnBataille = 0;
		// Si le nombre de d�fenseur ne d�passe pas 2
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
	
	// S�pare dans une liste de deux type (String et int) un type particulier (int ou String)
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
	
	public int choixNombreUnite(String unite, int u, int u0, Carte c, ActionOrdi o, int n) {
		int attaquant = 0;
		if (u != 0 && u0 != u) { // Si l'unit� est pr�sente dans le territoire et que si elle existe elles puissent se d�placer
			c.afficherMessage("Combien de " + unite + " dans la bataille ?", u0 + " au repos", "", "");
			attaquant = o.touchePresse();
			while (attaquant > u) {
				attaquant = o.touchePresse();
			}
			if (n + attaquant > 3 || u0 > attaquant) { 
				attaquant = 0;
				c.afficherMessage("", "", "Arm�e d'attaque impossible", "");
				StdDraw.pause(2000);
			}
			else {
				c.afficherMessage("", "", attaquant + " " + unite +"(s) dans la bataille", "");
				StdDraw.pause(2000);
			}
		}
		return attaquant;
	}

	public ArrayList combat(ArrayList listeAtt, ArrayList listeDef, ArrayList puissanceAtt, ArrayList puissanceDef, Armee a1, Armee a2) {
		ArrayList vainqueur = new ArrayList();
		ArrayList defaite = new ArrayList();
		
		int s1 = listeAtt.size();
		int s2 = listeDef.size();
		int s = Math.min(s1, s2); // R�cup�re la taille minimum des deux listes pour les faire combattre
		for (int k = 0; k < s; k++) {
			String u1 = (String) listeAtt.get(k);
			String u2 = (String) listeDef.get(k);
			int p1 = (int) puissanceAtt.get(k);
			int p2 = (int) puissanceDef.get(k);
			if (p1 <= p2) { // Si l'unit� attaquante a une puissance d'attaque inf�rieure ou �gale au d�fenseur
				uniteDetruite(u1, a1);
			}
			else {
				uniteDetruite(u2, a2);
				vainqueur.add(u1);
			}
		}
		//Si toutes les unit�s ennemies sont d�truites on cr�e la liste des unit�s se d�placeant
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
	
	// Assimiler al�atoirement une puissance d'attaque en fonction du type d'unit�
	public int puissanceUnite(String u) {
		int p;
		switch (u) {
		case "soldat" :
			p = 1 + (int)(Math.random() * ((6 - 1) + 1));
			break;
		case "cavalier" :
			p = 2 + (int)(Math.random() * ((7 - 2) + 1));
			break;
		default :
			p = 4 + (int)(Math.random() * ((9 - 4) + 1));
			break;
		}
		return p;
	}

	// D�truire une unit� apr�s un combat
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

	// Trier la liste en fonction de la puissance d'attaque des unit�s
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
			if (p3 > p2 && p3 > p1) {
				al.remove(1);
				al.add(1, p3);
				
				al.remove(0);
				al.add(0, u3);
				
				al.remove(5);
				al.add(5, p1);
				
				al.remove(4);
				al.add(4, u1);
				int c1 = p1;
				String s1 = u1;
				p1 = p3;
				u1 = u3;
				p3 = c1;
				u3 = s1;
				if (p2 < p3) {
					al.remove(3);
					al.add(3, p3);
					
					al.remove(2);
					al.add(2, u3);
					
					al.remove(5);
					al.add(5, p2);
					
					al.remove(4);
					al.add(4, u2);
				}
			}
			else {
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
				if (p2 < p3) {
					al.remove(3);
					al.add(3, p3);
					
					al.remove(2);
					al.add(2, u3);
					
					al.remove(5);
					al.add(5, p2);
					
					al.remove(4);
					al.add(4, u2);
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
					
					al.remove(0);
					al.add(0, u3);
					
					al.remove(5);
					al.add(5, p1);
					
					al.remove(4);
					al.add(4, u1);
				}
			}
			break;
		}
		return al;
	}

	public void afficherCombat(Carte c, ArrayList listeAttaquant, ArrayList listeDefense, ArrayList PuissanceAtt, ArrayList PuissanceDef) {
		int size1 = listeAttaquant.size();
		int size2 = listeDefense.size();
		
		String att1, att2, att3;
		String def1, def2;
		
		int pAtt1, pAtt2, pAtt3;
		int pDef1, pDef2;
		
		if (size2 == 2) {
			
			def1 = (String) listeDefense.get(0);
			def2 = (String) listeDefense.get(1);
			
			pDef1 = (int) PuissanceDef.get(0);
			pDef2 = (int) PuissanceDef.get(1);
			
			if (size1 == 1) {
				att1 = (String) listeAttaquant.get(0);
				pAtt1 = (int) PuissanceAtt.get(0);
				
				c.afficherMessage(att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", def2  + " (" + pDef2 + ")", "", "");
				StdDraw.pause(5000);
			}
			
			else if (size1 == 2) {
				
				att1 = (String) listeAttaquant.get(0);
				att2 = (String) listeAttaquant.get(1);
				
				pAtt1 = (int) PuissanceAtt.get(0);
				pAtt2 = (int) PuissanceAtt.get(1);
				
				c.afficherMessage(att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ") contre " + def2  + " (" + pDef2 + ")", "", "");
				StdDraw.pause(5000);
			}
			else if (size1 == 3) {
				
				att1 = (String) listeAttaquant.get(0);
				att2 = (String) listeAttaquant.get(1);
				att3 = (String) listeAttaquant.get(2);
				
				pAtt1 = (int) PuissanceAtt.get(0);
				pAtt2 = (int) PuissanceAtt.get(1);
				pAtt3 = (int) PuissanceAtt.get(2);
				
				c.afficherMessage(att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ") contre " + def2  + " (" + pDef2 + ")", att3 + " (" + pAtt3 + ")", "");
				StdDraw.pause(5000);
			}
			else {
				c.afficherMessage("Pas de combat", "", "", "");
				StdDraw.pause(5000);
			}
		}
		
		else if (size2 == 1) {
			def1 = (String) listeDefense.get(0);
			pDef1 = (int) PuissanceDef.get(0);
			
			if (size1 == 1) {
				att1 = (String) listeAttaquant.get(0);
				pAtt1 = (int) PuissanceAtt.get(0);
				
				c.afficherMessage(att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", "", "", "");
				StdDraw.pause(5000);
			}
			else if (size1 == 2) {
				att1 = (String) listeAttaquant.get(0);
				pAtt1 = (int) PuissanceAtt.get(0);
				
				att2 = (String) listeAttaquant.get(1);
				pAtt2 = (int) PuissanceAtt.get(1);
				
				c.afficherMessage(att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ")", "", "");
				StdDraw.pause(5000);
			}
			
			else if (size1 == 3) {
				att1 = (String) listeAttaquant.get(0);
				pAtt1 = (int) PuissanceAtt.get(0);
				
				att2 = (String) listeAttaquant.get(1);
				pAtt2 = (int) PuissanceAtt.get(1);
				
				att3 = (String) listeAttaquant.get(2);
				pAtt3 = (int) PuissanceAtt.get(2);
				
				c.afficherMessage(att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ")", att3 + " (" + pAtt3 + ")", "");
				StdDraw.pause(5000);
			}
			else {
				c.afficherMessage("Pas de combat", "", "", "");
				StdDraw.pause(5000);
			}
		}
		
	}

}
