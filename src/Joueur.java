import java.util.ArrayList;

public class Joueur {

	public int nombreTerritoire;
	public ArrayList al;
	
	
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
}
