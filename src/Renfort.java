import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Renfort {

	public Joueur j;
	
	public Renfort(Joueur J) {
		this.j = J;
	}
	
	public int renfortTerritoire() {
		ArrayList al = j.al;
		int T = al.size();
		
		int r = (int) Math.floor(T/3);
		return r;
	}
	
	public int renfortRegion() {
		int N = 0;
		if (controleRegion(0, 8)) {N = N + 9;}
		else if (controleRegion(9, 12)) {N = N + 4;}
		else if (controleRegion(13, 18)) {N = N + 6;}
		else if (controleRegion(19, 22)) {N = N + 4;}
		else if (controleRegion(23, 34)) {N = N + 12;}
		else if (controleRegion(35, 41)) {N = N + 7;}
		
		int r = (int) Math.floor(N/2);
		return r;
	}
	
	public boolean controleRegion(int a, int b) {
		for (int i = a; i <= b; i++) {
			if (!j.contientListe(i)) {
				return false;
			}
		}
		return true;
	}
	
	public int renfortTerritoireCapture() {
		int t = j.getTerritoireCapture();
		int r = 0;
		
		for (int i = 0; i <= t; i++) {
			int p = (int) Math.random();
			if (p < 0.5) {
				r++;
			}
		}
		
		return r;
	}

	public void renfort(int n, Carte c, Armee [] tab, String [] tabTerritoire, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6) {
		int i = j.getIndex();
		int u;
		
		ArrayList al = j.getListeTerritoire();
		int taille = j.al.size();
		
		int renfTerr = renfortTerritoire();
		int renfReg = renfortRegion();
		int renfCapt = renfortTerritoireCapture();
		
		int limiteUnite = renfTerr + renfReg + renfCapt;
		
		while (limiteUnite != 0) {
			
			ActionOrdi o = new ActionOrdi();
			int k = o.click(c);
			
			while (!j.contientListe(k)) {
				k = o.click(c);
			}
			
			String terri = tabTerritoire[k];
			Armee a = tab[k];
			int p = a.puissance();
			
			limiteUnite = ajoutArmee(tab, j1, j2, j3, j4, j5, j6, "canon", terri, o, limiteUnite, p, i, n, 7, c, a);
			p = a.puissance();
			
			limiteUnite = ajoutArmee(tab, j1, j2, j3, j4, j5, j6, "cavalier", terri, o, limiteUnite, p, i, n, 3, c, a);
			p = a.puissance();
			
			limiteUnite = ajoutArmee(tab, j1, j2, j3, j4, j5, j6, "soldat", terri, o, limiteUnite, p, i, n, 1, c, a);
			p = a.puissance();
		}
	}
	
	public int ajoutArmee(Armee [] tab, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, String unite, String terri, ActionOrdi o, int l, int p, int i, int n, int m, Carte c, Armee a) {
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
			c.AfficherCarte();
			c.afficherTerritoire(tab, j1, j2, j3, j4, j5, j6, n);
		}
		return l;
	}
}
