import java.util.ArrayList;

public class Joueur {

	public ArrayList<Integer> alTerritoire; // liste des territoires possédés par le joueur
	private int i; // index du joueur
	public int territoireCapture = 0;
	
	public Joueur(int index) {this.i = index;}
	
	public void initTerritoire(ArrayList al) { this.alTerritoire = al; }
	
	public ArrayList getListeTerritoire() { return this.alTerritoire; }
	
	// Vérifier que le territoire sur lequel le joueur click est possédé par ce joueur
	public boolean contientListe(int n) {
		ArrayList<Integer> al = this.alTerritoire;
		if (al.contains(n)) {
			return true;
		}
		return false;
	}

	public int getIndex() {return this.i;}

	// Enlever ce territoire de la liste du joueur
	public void removeListe(int k) {
		boolean indiceTrouve = false;
		int i = 0;
		while (!indiceTrouve) {
			int t = (int) this.alTerritoire.get(i);
			if (k == t) {
				indiceTrouve = true;
			}
			else {
				i++;
			}
		}
		this.alTerritoire.remove(i);
	}
	
	// Ajouter ce territoire dans la liste du joueur
	public void ajoutListe(int k) {this.alTerritoire.add(k);}

	// Ajouter un territoire capturé par le joueur
	public void setTerritoireCapture() {this.territoireCapture++;}
	
	public void reinitTerritoireCapture() {this.territoireCapture = 0;}
	
	public int getTerritoireCapture() {return this.territoireCapture;}

	// Vérifier que le joueur possède tous les territoires pour obtenir la condition de victoire
	public boolean verifVictoire() {
		for (int i = 0; i < 42; i++) {
			if (!contientListe(i)) {
				return false;
			}
		}
		return true;
	}
}
