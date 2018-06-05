import edu.princeton.cs.introcs.StdDraw;

public class ActionOrdi {

	private boolean click = false; 
	private double x1 = -1;
	private double x2 = -1;
	private double y1 = -1;
	private double y2 = -1; 

	// renvoie la touche du clavier pressée
	public int touchePresse() {
		boolean b = StdDraw.hasNextKeyTyped();
		while (!b) {
			b = StdDraw.hasNextKeyTyped();
		}
		int touche = StdDraw.nextKeyTyped();
		if (touche - 48 < 0 || touche - 48 > 9 ) { // rappel récursif si n - 48 n'est pas compris entre 0 et 9
			return touchePresse();
		}
		return touche - 48;
	}
	
	// Renvoie l'indice du territoire correspondant au point sur lequel on a cliqué
	public int click(Carte c) {
		int t;
		this.click = false;
		while (!this.click) {
			if (StdDraw.isMousePressed()) {
				this.x2 = StdDraw.mouseX();
				this.y2 = StdDraw.mouseY();
				if (this.x1 != this.x2 || this.y1 != this.y2) { // Permet d'annuler la valeur du click précédent
					this.x1 = this.x2;
					this.y1 = this.y2;
					t = c.clickCorrespondance(this.x1, this.y1);
					this.click = true;
					return t;
				}
			}
		}
		return -1;
	}
}
