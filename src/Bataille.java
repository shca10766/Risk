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
		
		while (!j.contientListe(t) || a.puissance() <= 1) { // Vérifier que le territoire attaquant est possédé par le joueur
			if (t == -1) {
				c.afficherTerritoire();
				c.afficherMessage("Veuillez cliquer sur un des points", "pour choisir un territoire", "" , "");
			}
			else if (j.contientListe(t) && a.puissance() ==1) {
				c.afficherTerritoire();
				c.afficherMessage("Ce territoire ne possède qu'une armée", "Il ne peut donc pas attaquer", "" , "");
			}
			else {
				c.afficherTerritoire();
				c.afficherMessage("Veuillez cliquer sur un des territoires", "en votre possession", "" , "");
			}
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
		
		while (j.contientListe(t) || t == -1) { // Vérifier que le territoire attaqué n'est pas possédé par le joueur
			if (t == -1) {
				c.afficherTerritoire();
				c.afficherMessage("Veuillez cliquer sur un des points", "pour choisir un territoire", "" , "");
			}
			else {
				c.afficherTerritoire();
				c.afficherMessage("Veuillez cliquer sur un des territoires", "n'étant pas en votre possession", "" , "");
			}
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
		
		// Vérifier que les deux territoires sont voisins
		while (!territoireVoisin) {
			
			c.afficherTerritoire();
			c.afficherMessage("Ces deux territoires", "ne sont pas voisins", "Veuillez ressayer" , "");
			
			territoireAttaquant = TerritoireAttaquant(c, o, tabArmee);
			tAttaquant = tabTerritoire[territoireAttaquant];
			
			territoireDefense = TerritoireAttaque(c, o);	
			tDefense = tabTerritoire[territoireDefense];
			
			territoireVoisin = c.verifCorrespondance(tAttaquant, tDefense);
		}
		
		int joueurAttaqaunt = j.getIndex();
		c.afficherMessage("", "", "", tAttaquant + " attaque " + tDefense);
		StdDraw.pause(2000);
		
		Armee armeeAttaquant = tabArmee[territoireAttaquant];
		// Création de l'armée attaquante
		ArrayList al1 = armeeAttaquant(o, c, tabArmee, armeeAttaquant);
		
		// Séparation des types d'unité et de leur puissance d'attaque
		ArrayList listeAttaquant = recupListe(al1, 0); // Que des variables de type String
		ArrayList attaquantPuissance = recupListe(al1, 1); // Que des variables du type int

		// Déterminer l'index du joueur attaqué
		int joueurDefense = joueurDefense (j1, j2, j3, j4, j5, j6, territoireDefense);
		Armee armeeDefense = tabArmee[territoireDefense];	
		
		// Création de l'armée défensive
		ArrayList al2 = armeeDefense(armeeDefense);
		
		// Séparation des types d'unité et de leur puissance d'attaque
		ArrayList listeDefense = recupListe(al2, 0);
		ArrayList defensePuissance = recupListe(al2, 1);
		
		c.afficherTerritoire();
		afficherCombat(c, listeAttaquant, listeDefense, attaquantPuissance, defensePuissance);
		
		// Réalise le combat entre les deux armées
		ArrayList vainqueur = combat(listeAttaquant, listeDefense, attaquantPuissance, defensePuissance, armeeAttaquant, armeeDefense);
		return vainqueur;
	}
	
	// Déterminer quel joueur possède le territoire correspondant
	public int joueurDefense(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int indexTerritoire) {
		if (j1.contientListe(indexTerritoire)) {return 1;}
		else if (j2.contientListe(indexTerritoire)) {return 2;}
		else if (j3.contientListe(indexTerritoire)) {return 3;}
		else if (j4.contientListe(indexTerritoire)) {return 4;}
		else if (j5.contientListe(indexTerritoire)) {return 5;}
		else {return 6;}
	}
	
	// Création de l'armée attaquante
	public ArrayList armeeAttaquant(ActionOrdi o, Carte c, Armee [] tabArmee, Armee armeeAttaquant) {
		int nombreUnite = 0;
		ArrayList al = new ArrayList();
		int size = al.size();
		
		int cn = armeeAttaquant.getCanon();
		int cv = armeeAttaquant.getCavalier();
		int s = armeeAttaquant.getSoldat();
		
		// Toutes les unités ne pouvant pas se déplacer
		int cn0 = armeeAttaquant.getCanon0Mouvement();
		int cv0 = armeeAttaquant.getCavalier0Mouvement();
		int s0 = armeeAttaquant.getSoldat0Mouvement();
		
		int uniteSansDeplacement = cn + cv + s;
		int uniteRepos = cn + cv + s;
		
		// Si il reste une unité dans le territoire et qu'il reste des unités qui peuvent se déplacer
		if (uniteRepos > 1 && (cn0 + cv0 + s0 != cn + cv + s)) {
			
			// Ordre pour lequel on demande le nombre d'attaquant pour chaque unité donne l'ordre de la priorité d'Attaque
			
			al = choixNombreUnite("cavalier", cv, cv0, c, o, nombreUnite, al, uniteRepos);
			size = al.size();
			nombreUnite = size / 2;
			uniteRepos = uniteSansDeplacement - nombreUnite;
			
			al = choixNombreUnite("soldat", s, s0, c, o, nombreUnite, al, uniteRepos);
			size = al.size();
			nombreUnite = size / 2;
			uniteRepos = uniteSansDeplacement - nombreUnite;
			
			al = choixNombreUnite("canon", cn, cn0, c, o, nombreUnite, al, uniteRepos);

			// Trier la liste de l'armée attaquante en fonction de la puissance d'attaque de l'unité
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
		
		// On ajoute pour chaque unité choisie son type et sa puissance d'attaque
		for (int k2 = 0; k2 < sBataille; k2++) {
			al.add("soldat");
			int p = puissanceUnite("soldat");
			al.add(p);
		}
		
		if (nombreUnite == 2) { // Si on a notre nombree de défenseur maximum (2)
			al = ordrePuissance(al);
			return al;
		}			
		
		int cnBataille = 0;
		// Si le nombre de défenseur ne dépasse pas 2
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
	
	// Sépare dans une liste de deux type (String et int) un type particulier (int ou String)
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
	
	public ArrayList choixNombreUnite(String unite, int u, int u0, Carte c, ActionOrdi o, int nombreUnite, ArrayList al, int uniteRepos) {
		int attaquant = 0;
		if (u != 0 && u0 != u) { // Si l'unité est présente dans le territoire et que si elle existe elles puissent se déplacer
			c.afficherTerritoire();
			c.afficherMessage("Combien de " + unite + " dans la bataille ?", u0 + " au repos", "", "");
			attaquant = o.touchePresse();
			while (attaquant > u) {
				c.afficherMessage("", "", "Ce territoire ne possède pas autant", "d'unité de ce type Ressayez !");
				attaquant = o.touchePresse();
			}
			if (nombreUnite + attaquant > 3 || u0 > attaquant) { 
				c.afficherTerritoire();
				if (nombreUnite + attaquant > 3) {
					c.afficherMessage("Vous ne pouvez choisir que trois", "unités au maximum", "", "");
				}
				else {
					c.afficherMessage("Certaines de ces unités", "ont dépassé leur quota", "de mouvement par tour", "");
				}
				StdDraw.pause(1500);
				return choixNombreUnite(unite, u, u0, c, o, nombreUnite, al, uniteRepos);
			}
			else {
				c.afficherTerritoire();
				c.afficherMessage("", "", attaquant + " " + unite +"(s) dans la bataille", "");
				StdDraw.pause(1500);
			}
		}
		
		if (uniteRepos - attaquant >= 1) {
			nombreUnite += attaquant;
			
			// On ajoute pour chaque unité choisie son type et sa puissance d'attaque
			for (int k1 = 0; k1 < attaquant; k1++) {
				al.add((String) unite);
				int p = puissanceUnite(unite);
				al.add((int) p);
			}
		}
		else {
			c.afficherTerritoire();
			c.afficherMessage("Tout territoires doit posséder", "au moins une unité", "", "");
			StdDraw.pause(1500);
			return choixNombreUnite(unite, u, u0, c, o, nombreUnite, al, uniteRepos);
		}
		return al;
	}

	public ArrayList combat(ArrayList listeAtt, ArrayList listeDef, ArrayList puissanceAtt, ArrayList puissanceDef, Armee a1, Armee a2) {
		ArrayList vainqueur = new ArrayList();
		ArrayList defaite = new ArrayList();
		
		int s1 = listeAtt.size();
		int s2 = listeDef.size();
		int s = Math.min(s1, s2); // Récupère la taille minimum des deux listes pour les faire combattre
		for (int k = 0; k < s; k++) {
			String u1 = (String) listeAtt.get(k);
			String u2 = (String) listeDef.get(k);
			int p1 = (int) puissanceAtt.get(k);
			int p2 = (int) puissanceDef.get(k);
			if (p1 <= p2) { // Si l'unité attaquante a une puissance d'attaque inférieure ou égale au défenseur
				uniteDetruite(u1, a1);
			}
			else {
				uniteDetruite(u2, a2);
				vainqueur.add(u1);
			}
		}
		//Si toutes les unités ennemies sont détruites on crée la liste des unités se déplaceant
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
	
	// Assimiler aléatoirement une puissance d'attaque en fonction du type d'unité
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

	// Détruire une unité après un combat
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

	// Trier la liste en fonction de la puissance d'attaque des unités
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
	
	public ArrayList ordreTypeUniteAttaquant(ArrayList al) {
		int p1, p2, p3;
		String u1, u2, u3;
		int s = al.size();
		
		if (s == 4) {
			p1 = (int) al.get(1);
			p2 = (int) al.get(3);
			
			u1 = (String) al.get(0);
			u2 = (String) al.get(2);
			if (p1 == p2) {
				if ((u1 == "canon" && (u2 == "soldat" || u2 == "cavalier")) || (u1 == "soldat" && u2 == "cavalier")) {
					al.remove(0);
					al.add(0, u2);
					
					al.remove(2);
					al.add(2, u1);
				}
			}
		}
		
		else if (s == 6) {
			p1 = (int) al.get(1);
			p2 = (int) al.get(3);
			p3 = (int) al.get(5);
			
			u1 = (String) al.get(0);
			u2 = (String) al.get(2);
			u3 = (String) al.get(4);
			
			if (p1 == p2) {
				if ((u1 == "canon" && (u2 == "soldat" || u2 == "cavalier")) || (u1 == "soldat" && u2 == "cavalier")) {
					al.remove(0);
					al.add(0, u2);
					
					al.remove(2);
					al.add(2, u1);
				}
			}
			
			if (p2 == p3) {
				if ((u2 == "canon" && (u3 == "soldat" || u3 == "cavalier")) || (u2 == "soldat" && u3 == "cavalier")) {
					al.remove(2);
					al.add(2, u3);
					
					al.remove(4);
					al.add(4, u2);
				}
			}
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
				
				c.afficherMessage("COMBAT", att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", def2  + " (" + pDef2 + ")", "");
				StdDraw.pause(5000);
			}
			
			else if (size1 == 2) {
				
				att1 = (String) listeAttaquant.get(0);
				att2 = (String) listeAttaquant.get(1);
				
				pAtt1 = (int) PuissanceAtt.get(0);
				pAtt2 = (int) PuissanceAtt.get(1);
				
				c.afficherMessage("COMBAT", att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ") contre " + def2  + " (" + pDef2 + ")", "");
				StdDraw.pause(5000);
			}
			else if (size1 == 3) {
				
				att1 = (String) listeAttaquant.get(0);
				att2 = (String) listeAttaquant.get(1);
				att3 = (String) listeAttaquant.get(2);
				
				pAtt1 = (int) PuissanceAtt.get(0);
				pAtt2 = (int) PuissanceAtt.get(1);
				pAtt3 = (int) PuissanceAtt.get(2);
				
				c.afficherMessage("COMBAT", att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ") contre " + def2  + " (" + pDef2 + ")", att3 + " (" + pAtt3 + ")");
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
				
				c.afficherMessage("COMBAT", att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", "", "");
				StdDraw.pause(5000);
			}
			else if (size1 == 2) {
				att1 = (String) listeAttaquant.get(0);
				pAtt1 = (int) PuissanceAtt.get(0);
				
				att2 = (String) listeAttaquant.get(1);
				pAtt2 = (int) PuissanceAtt.get(1);
				
				c.afficherMessage("COMBAT", att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ")", "");
				StdDraw.pause(5000);
			}
			
			else if (size1 == 3) {
				att1 = (String) listeAttaquant.get(0);
				pAtt1 = (int) PuissanceAtt.get(0);
				
				att2 = (String) listeAttaquant.get(1);
				pAtt2 = (int) PuissanceAtt.get(1);
				
				att3 = (String) listeAttaquant.get(2);
				pAtt3 = (int) PuissanceAtt.get(2);
				
				c.afficherMessage("COMBAT", att1 + " (" + pAtt1 + ") contre " + def1  + " (" + pDef1 + ")", att2 + " (" + pAtt2 + ")", att3 + " (" + pAtt3 + ")");
				StdDraw.pause(5000);
			}
			else {
				c.afficherMessage("Pas de combat", "", "", "");
				StdDraw.pause(5000);
			}
		}
		
	}

}
