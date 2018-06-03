import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

public class Initialisation {

	public int entier;
	
	public Initialisation(int n) {
		this.entier = n;
	}
	
	public void initialisationTerritoire(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6) {
		int n = this.entier;
		
		ArrayList<Integer> al = new ArrayList<Integer>();
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
	
	public ArrayList<Integer> repartition(ArrayList<Integer> alTerritoireNonReparti, int j, int numeroJoueur) {
		int init = 0;
		ArrayList<Integer> ListeTerritoireJoueur = new ArrayList<Integer>();
		switch (j) {
			case 2:
				init = 21;
				break;
			case 3:
				init = 14;
				break;
			case 4:
				if (numeroJoueur == 1 || numeroJoueur == 2) { init = 10;}
				else {init = 11;}
				break;
			case 5 :
				if (numeroJoueur == 4 || numeroJoueur == 5) {init = 9;}
				else {init = 8;}
				break;
			case 6 :
				init = 7;
				break;
		}
		int t = ListeTerritoireJoueur.size();
		while (t < init ) {
			int s = alTerritoireNonReparti.size();
			int indexTerritoire = (int) ((s-1)*Math.random());
			ListeTerritoireJoueur.add(alTerritoireNonReparti.get(indexTerritoire));
			alTerritoireNonReparti.remove(indexTerritoire);
			t = ListeTerritoireJoueur.size();	
		}
		return ListeTerritoireJoueur;
	}

	public void initialisationArmee(String [] tabTerritoire, Armee [] tabArmee,  Carte c, Joueur j, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6) {
		int i = j.getIndex();
		int n = this.entier;
		
		ArrayList<Integer> al = j.getListeTerritoire();
		int nombreUnitePlace = j.alTerritoire.size();
		
		int limiteUnite = 0;
		switch(n) {
		case 2 : limiteUnite = 40; break;
		case 3 : limiteUnite = 35; break;
		case 4 : limiteUnite = 30; break;
		case 5 : limiteUnite = 25; break;
		case 6 : limiteUnite = 20; break;
		}
		
		limiteUnite = limiteUnite - nombreUnitePlace;
		
		while (limiteUnite != 0) {
			
			ActionOrdi o = new ActionOrdi();
			int k = o.click(c);
			
			while (!j.contientListe(k)) {
				k = o.click(c);
			}
			
			String terri = tabTerritoire[k];
			Armee a = tabArmee[k];
			
			limiteUnite = ajoutArmee(tabArmee, j1, j2, j3, j4, j5, j6, "canon", terri, o, limiteUnite, i, n, 7, c, a);
			
			limiteUnite = ajoutArmee(tabArmee, j1, j2, j3, j4, j5, j6, "cavalier", terri, o, limiteUnite, i, n, 3, c, a);
			
			limiteUnite = ajoutArmee(tabArmee, j1, j2, j3, j4, j5, j6, "soldat", terri, o, limiteUnite, i, n, 1, c, a);
		}
	}
	
	public int ajoutArmee(Armee [] tab, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, String unite, String terri, ActionOrdi o, int limite, int i, int n, int cout, Carte c, Armee a) {
		if (limite >= cout) {
			c.afficherMessage("Joueur " + i, "Placez encore " +limite+ " unité(s))", "Territoire " +terri, "Combien de " + unite + " ajouter?");
			
			int touche = o.touchePresse();
			
			if (limite >= touche*cout) {
				switch (unite) {
				case "canon" : a.setCanon(touche); break;
				case "cavalier" : a.setCavalier(touche); break;
				case "soldat" : a.setSoldat(touche); break;
				}
				limite = limite - cout*touche;
			}
			
			c.AfficherCarte();
			c.afficherTerritoire(tab, j1, j2, j3, j4, j5, j6, n);
		}
		return limite;
	}

}
