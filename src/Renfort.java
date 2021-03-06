import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

public class Renfort {

	private Joueur j;
	
	public Renfort(Joueur J) {
		this.j = J;
	}
	
	// Nombre de renfort en fonction du nombre de territoire poss�d�s par le joueur
	public int renfortTerritoire() {
		ArrayList<Integer> al = j.alTerritoire;
		int T = al.size();
		
		int r = (int) Math.floor(T/3);
		return r;
	}
	
	// Nombre de renfort en fonction du nombre de r�gion contr�l�e et le nombre de territoire dans ces r�gions
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
	
	// V�rification si la r�gion est contr�l�e par le joueur
	// a et b donnent l'intervalle des indices des territoires de la r�gion correspondante
	public boolean controleRegion(int a, int b) {
		for (int i = a; i <= b; i++) {
			if (!j.contientListe(i)) {
				return false;
			}
		}
		return true;
	}
	
	// Nombre de renfort en fonction du nombre de territoires captur�s au tour pr�c�dent
	public int renfortTerritoireCapture() {
		int t = j.getTerritoireCapture();
		int r = 0;
		
		for (int i = 0; i <= t; i++) {
			int p = (int) Math.random();
			if (p < 0.5) { // probabilit� de 50% d'avoir un renfort par territoire captur�
				r++; 
			}
		}
		
		return r;
	}

	public void renfort(int n, Carte c, Armee [] tabArmee, String [] tabTerritoire) {
		int i = j.getIndex();
		
		ArrayList<Integer> al = j.getListeTerritoire();
		
		int renfTerr = renfortTerritoire();
		int renfReg = renfortRegion();
		int renfCapt = renfortTerritoireCapture();
		
		// Nombre d'unit� � placer pendant les renforts
		int limiteUnite = renfTerr + renfReg + renfCapt;
		
		// Tant que toutes les unit�s ne sont pas plac�es
		while (limiteUnite != 0) {
			
			ActionOrdi o = new ActionOrdi();
			int k = o.click(c);
			
			// V�rifier que le joueur click sur un de ses territoires
			while (!j.contientListe(k)) {
				if (k == -1) {
					c.afficherTerritoire();
					c.afficherMessage("Cliquez sur un point", "pour choisir une territoire", "correspondant", "");
				}
				else {
					c.afficherTerritoire();
					c.afficherMessage("Ce territoire ne vous", "appartient pas", "", "");
				}
				k = o.click(c);
			}
			
			String terri = tabTerritoire[k];
			Armee a = tabArmee[k];
			
			limiteUnite = ajoutArmee(tabArmee, "canon", terri, o, limiteUnite, i, 7, c, a);
			
			limiteUnite = ajoutArmee(tabArmee, "cavalier", terri, o, limiteUnite, i, 3, c, a);
			
			limiteUnite = ajoutArmee(tabArmee, "soldat", terri, o, limiteUnite, i, 1, c, a);
			
			if (limiteUnite != 0) {
				c.afficherTerritoire();
				c.afficherMessage("Ajouter vos renforts", "Cliquez sur un de vos territoires", "pour cela", "");
			}
			
		}
	}
	
	public int ajoutArmee(Armee [] tab, String unite, String terri, ActionOrdi o, int limite, int i, int cout, Carte c, Armee a) {
		if (limite >= cout) { // Si le nombre d'unit� restantes � placer est sup�rieure ou �gale au co�t du type de l'unit�
			
			c.afficherTerritoire();
			c.afficherMessage("Joueur " + i + " ins�rer vos renforts", "(reste " +limite+ " unit�(s))", "Territoire " +terri, "Combien de " + unite + " ajouter?");
			
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
				c.afficherMessage("", "", "", "Nombre d'unit� restante insuffisant");
				StdDraw.pause(1500);
				return ajoutArmee(tab, unite, terri, o, limite, i, cout, c, a);
			}
		}
		return limite;
	}
}
