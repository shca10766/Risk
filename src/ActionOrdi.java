import edu.princeton.cs.introcs.StdDraw;

public class ActionOrdi {
	// back
	private boolean click = false; 
	private double x1 = -1;
	private double x2 = -1;
	private double y1 = -1;
	private double y2 = -1; 

	public int touchePresse() {
		boolean b = StdDraw.hasNextKeyTyped();
		while (!b) {
			b = StdDraw.hasNextKeyTyped();
		}
		int n = StdDraw.nextKeyTyped();
		return n - 48;
	}
	
	public int click(Carte c) {
		int t;
		this.click = false;
		while (!this.click) {
			if (StdDraw.isMousePressed()) {
				this.x2 = StdDraw.mouseX();
				this.y2 = StdDraw.mouseY();
				if (this.x1 != this.x2 || this.y1 != this.y2) {
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
