import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

public class Initialisation {

	private int entier;
	
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
		// Repartition du nombre de territoires par joueur en focntion du nombre de joueur
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
		while (t < init ) { // tant qu'un joueur ne possède pas le nombre de territoire déterminé
			int s = alTerritoireNonReparti.size();
			int indexTerritoire = (int) ((s-1)*Math.random()); // indice aléatoire du territoire
			ListeTerritoireJoueur.add(alTerritoireNonReparti.get(indexTerritoire)); // Ajout de l'indice du territoire dans la liste des territoires possédés par le joueur j
			alTerritoireNonReparti.remove(indexTerritoire); // Suppression du territoire nouvellement attribué
			t = ListeTerritoireJoueur.size();	
		}
		return ListeTerritoireJoueur;
	}

	public void initialisationArmee(String [] tabTerritoire, Armee [] tabArmee,  Carte c, Joueur j) {
		int i = j.getIndex();
		int n = this.entier;
		
		ArrayList<Integer> al = j.getListeTerritoire();
		int nombreUnitePlace = j.alTerritoire.size();
		
		int limiteUnite = 0;
		// Détermine le nombre d'unité à placer en fonction du nombre de joueur
		switch(n) {
		case 2 : limiteUnite = 40; break;
		case 3 : limiteUnite = 35; break;
		case 4 : limiteUnite = 30; break;
		case 5 : limiteUnite = 25; break;
		case 6 : limiteUnite = 20; break;
		}
		
		limiteUnite = limiteUnite - nombreUnitePlace; // Déja 1 soldat placé dans chaque terrritoire d'où la réduction du nombre d'unité qui reste à placer
		
		// tant que le joueur n'a pas placé toutes ses unités
		while (limiteUnite != 0) {
			
			ActionOrdi o = new ActionOrdi();
			int k = o.click(c);
			
			// click sur un de ses territoires
			while (!j.contientListe(k)) {
				if (k == -1) {
					c.afficherTerritoire();
					c.afficherMessage("Cliquez sur un des points", "pour choisir un de vos territoire", "", "");
				}
				else {
					c.afficherTerritoire();
					c.afficherMessage("Ce territoire ne vous appartient pas", "", "", "");
				}
				k = o.click(c);
			}
			
			// Récupération du nom du territoire et de son armée correspondante
			String terri = tabTerritoire[k];
			Armee a = tabArmee[k];
			
			limiteUnite = ajoutArmee(tabArmee,"canon", terri, o, limiteUnite, i, 7, c, a);
			
			limiteUnite = ajoutArmee(tabArmee,"cavalier", terri, o, limiteUnite, i, 3, c, a);
			
			limiteUnite = ajoutArmee(tabArmee, "soldat", terri, o, limiteUnite, i, 1, c, a);
			
			if (limiteUnite != 0) {
				c.afficherTerritoire();
				c.afficherMessage("Joueur " + i +" : Initialisez votre armée", "Pour cela cliquez sur un", "de vos territoires pour y", "ajouter une armée");
			}
			else {
				c.afficherTerritoire();
			}
		}
	}
	
	public int ajoutArmee(Armee [] tab, String unite, String terri, ActionOrdi o, int limite, int i, int cout, Carte c, Armee a) {
		if (limite >= cout) { // Si le cout de l'unité est inférieur au reste du nombre de la puissance à placer
			c.afficherTerritoire();
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
			else {
				c.afficherTerritoire();
				c.afficherMessage("Le coût d'ajout est trop élevé", "Veuillez ressayer", "", "");
				StdDraw.pause(3000);
				return ajoutArmee(tab, unite, terri, o, limite, i, cout, c, a);
			}
		}
		return limite; // retourne le nombre d'unité restante à placer
	}

}
