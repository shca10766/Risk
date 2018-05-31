import java.util.ArrayList;

public class Joueur {

	public int nombreTerritoire;
	public ArrayList al;
	private int i;
	
	public Joueur(int index) {
		this.i = index;
	}
	
	public void setNombreTerritoire(int a) { this.nombreTerritoire += a; }
	
	public int getNombreTerritoire() { return this.nombreTerritoire; }
	
	public void initTerritoire(ArrayList al) { this.al = al; }
	
	public ArrayList getListeTerritoire() { return this.al; }
	
	public boolean contientListe(int n) {
		ArrayList al = this.al;
		if (al.contains(n)) {
			return true;
		}
		return false;
	}

	public int getIndex() {return this.i;}

	public void removeListe(int k) {
		boolean indiceTrouve = false;
		int i = 0;
		while (!indiceTrouve) {
			int t = (int) this.al.get(i);
			if (k == t) {
				indiceTrouve = true;
			}
			else {
				i++;
			}
		}
		this.al.remove(i);
	}
	
	public void ajoutListe(int k) {
		this.al.add(k);
	}

}
