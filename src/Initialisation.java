import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Initialisation {

	public int entier;
	
	public Initialisation(int n) {
		this.entier = n;
	}
	
	public void initialisationTerritoire(Carte c, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6) {
		int n = this.entier;
		
		ArrayList al = new ArrayList();
		for (int index = 0; index < 42 ; index++) {
			al.add(index);
		}
		
		switch(n) {
		case 2 :
			j1.initTerritoire(repartition(al, n, 1));
			j2.initTerritoire(repartition(al, n, 2));
			break;
		case 3 :
			j1.initTerritoire(repartition(al, n, 1));
			j2.initTerritoire(repartition(al, n, 2));
			j3.initTerritoire(repartition(al, n, 3));
			break;
		case 4 :
			j1.initTerritoire(repartition(al, n, 1));
			j2.initTerritoire(repartition(al, n, 2));
			j3.initTerritoire(repartition(al, n, 3));
			j4.initTerritoire(repartition(al, n, 4));
			break;
		case 5 :
			j1.initTerritoire(repartition(al, n, 1));
			j2.initTerritoire(repartition(al, n, 2));
			j3.initTerritoire(repartition(al, n, 3));
			j4.initTerritoire(repartition(al, n, 4));
			j5.initTerritoire(repartition(al, n, 5));
			break;
		case 6 :
			j1.initTerritoire(repartition(al, n, 1));
			j2.initTerritoire(repartition(al, n, 2));
			j3.initTerritoire(repartition(al, n, 3));
			j4.initTerritoire(repartition(al, n, 4));
			j5.initTerritoire(repartition(al, n, 5));
			j6.initTerritoire(repartition(al, n, 6));
			break;	
		}
	}
	
	public ArrayList repartition(ArrayList al, int j, int n) {
		int init = 0;
		ArrayList Liste = new ArrayList();
		switch (j) {
			case 2:
				init = 21;
				break;
			case 3:
				init = 14;
				break;
			case 4:
				if (n == 1 || n == 2) { init = 10;}
				else {init = 11;}
				break;
			case 5 :
				if (n == 4 || n == 5) {init = 9;}
				else {init = 8;}
				break;
			case 6 :
				init = 7;
				break;
		}
		int t = Liste.size();
		while (t < init ) {
			int s = al.size();
			int index = (int) ((s-1)*Math.random());
			Liste.add(al.get(index));
			al.remove(index);
			t = Liste.size();	
		}
		return Liste;
	}

	public void initialisationArmee(String [] tabTerritoire, Armee [] tab,  Carte c, Joueur j, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6,  int i) {
		int n = this.entier;
		int u;
		
		ArrayList al = j.getListeTerritoire();
		int taille = j.al.size();
		
		int limiteUnite = 0;
		switch(n) {
		case 2 : limiteUnite = 40; break;
		case 3 : limiteUnite = 35; break;
		case 4 : limiteUnite = 30; break;
		case 5 : limiteUnite = 25; break;
		case 6 : limiteUnite = 20; break;
		}
		
		limiteUnite = limiteUnite - taille;
		
		while (limiteUnite != 0) {
			
			ActionOrdi o = new ActionOrdi();
			
			int k = o.click(c);
			String terri = tabTerritoire[k];
			Armee a = tab[k];
			int p = a.puissance();
			
			limiteUnite = ajoutArmee("canon", terri, o, limiteUnite, p, i, n, 7, c, a);
			p = a.puissance();
			
			c.AfficherCarte();
			c.afficherTerritoire(tab, j1, j2, j3, j4, j5, j6, n);
			
			limiteUnite = ajoutArmee("cavalier", terri, o, limiteUnite, p, i, n, 3, c, a);
			p = a.puissance();
			
			c.AfficherCarte();
			c.afficherTerritoire(tab, j1, j2, j3, j4, j5, j6, n);
			
			limiteUnite = ajoutArmee("soldat", terri, o, limiteUnite, p, i, n, 1, c, a);
			p = a.puissance();
			
			c.AfficherCarte();
			c.afficherTerritoire(tab, j1, j2, j3, j4, j5, j6, n);
			
			tab[k] = a;
		}
	}
	
	public int ajoutArmee(String unite, String terri, ActionOrdi o, int l, int p, int i, int n, int m, Carte c, Armee a) {
		if (l >= m) {
			int num = 0;

			switch (unite) {
			case "canon" : num = a.getCanon(); break;
			case "cavalier" : num =  a.getCavalier(); break;
			case "soldat" : num = a.getSoldat(); break;
			}
			
			c.afficherMessage("Joueur " + i + " (reste " +l+ " unité(s))", terri + " a une puissance de " + p, "Combien de " + unite + "? (déjà " + num + ")", "");
			
			int u = o.touchePresse();
			
			if (l >= u*m) {
				switch (unite) {
				case "canon" : a.setCanon(u); break;
				case "cavalier" : a.setCavalier(u); break;
				case "soldat" : a.setSoldat(u); break;
				}
				l = l - m*u;
			}
			
			else {
				c.afficherMessage("", "", "", "Nombre d'unité restant insuffisant");
				StdDraw.pause(3000);
			}
		}
		return l;
	}

}
