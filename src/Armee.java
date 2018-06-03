public class Armee {

	public int nombreSoldat;
	public int nombreCavalier;
	public int nombreCanon;
	
	public Armee() {
		this.nombreSoldat = 1;
		this.nombreCanon = 0;
		this.nombreCavalier = 0;
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

	
}
