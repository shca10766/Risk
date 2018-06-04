public class Armee {

	public int nombreSoldat;
	public int nombreCavalier;
	public int nombreCanon;
	
	public int nombreCavalier0 = 0;
	public int nombreCanon0 = 0;
	public int nombreSoldat0 = 0;
	
	public int nombreCavalier1 = 0;
	public int nombreSoldat1 = 0;
	
	public int nombreCavalier2 = 0;
	
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
	
	public int getCavalier2Mouvement() {return this.nombreCavalier2;}
	public int getCavalier1Mouvement() {return this.nombreCavalier1;}
	public int getCavalier0Mouvement() {return this.nombreCavalier0;}
	
	public void setCavalier2(int m) {this.nombreCavalier2 = this.nombreCavalier2 + m;}
	public void setCavalier1(int m) {this.nombreCavalier1 = this.nombreCavalier1 + m;}
	public void setCavalier0(int m) {this.nombreCavalier0 = this.nombreCavalier0 + m;}
	
	public int getSoldat1Mouvement() {return this.nombreSoldat1;}
	public int getSoldat0Mouvement() {return this.nombreSoldat0;}
	
	public void setSoldat1(int m) {this.nombreSoldat1 = this.nombreSoldat1 + m;}
	public void setSoldat0(int m) {this.nombreSoldat0 = this.nombreSoldat0 + m;}
	
	public int getCanon0Mouvement() {return this.nombreCanon0;}
	
	public void setCanon0(int m) {this.nombreCanon0 = this.nombreCanon0 + m;}
	
	public void reinitMouvement() {
		this.nombreCanon0 = 0;
		
		this.nombreSoldat0 = 0;
		this.nombreSoldat1 = 0;

		this.nombreCavalier0 = 0;
		this.nombreCavalier1 = 0;
		this.nombreCavalier2 = 0;
	}
}
