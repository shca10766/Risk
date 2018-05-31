import java.util.ArrayList;

public class Armee {

	public int nombreSoldat;
	public int nombreCavalier;
	public int nombreCanon;
	private final int numeroTerritoire;
	public ArrayList al;
	
	public Armee(int s, int n, int cv, int cn) {
		this.nombreSoldat = s;
		this.numeroTerritoire = n;
		this.nombreCanon = cn;
		this.nombreCavalier = cv;
		this.al = listeMouvement(cn, cv, s);
	}
	
	public int puissance() {
		int p = 7*this.nombreCanon + 3*this.nombreCavalier + this.nombreSoldat;
		return p;
	}
	
	public void setSoldat(int n) {this.nombreSoldat = this.nombreSoldat + n;}
	
	public int getSoldat() {return this.nombreSoldat;}
	
	public void setCavalier(int n) {this.nombreCavalier = this.nombreCavalier + n;}
	
	public int getCavalier() {return this.nombreCavalier;}
	
	public void setCanon(int n) {this.nombreCanon = this.nombreCanon + n;}
	
	public int getCanon() {return this.nombreCanon;}
	
	public ArrayList listeMouvement(int cn, int cv, int s) {
		ArrayList al = listeArmee(cn, cv, s);
		int size = al.size();
		ArrayList mouv = new ArrayList();
		for (int i = 0; i < size; i++) {
			String u = (String) al.get(i);
			switch (u) {
			case "cavalier":
				mouv.add(3); break;
			case "canon" :
				mouv.add(1); break;
			default : 
				mouv.add(2); break;
			}
		}
		this.al = mouv;
		return mouv;
	}
	
	public ArrayList listeArmee(int cn, int cv, int s) {
		ArrayList al = new ArrayList();
		for (int k1 = 0; k1 < cv; k1++) {
			al.add("cavalier");
		}
		
		for (int k3 = 0; k3 < s; k3++) {
			al.add("soldat");
		}
		
		for (int k2 = 0; k2 < cn; k2++) {
			al.add("canon");
		}
		
		return al;
	}

	public void setMouvement(int k) {
		int m = (int) this.al.get(k);
		this.al.remove(k);
		this.al.add(k, m-1);
	}
	
	public int getMouvement(int k) {
		return (int) this.al.get(k);
	}
}
