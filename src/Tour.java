
public class Tour {

	Joueur j;
	
	public Tour(Joueur J) {
		this.j = J;
	}
	
	public void bataille(Carte c, Armee [] tab, int n, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int i) {
		ActionOrdi o = new ActionOrdi();
		
		c.AfficherCarte();
		c.afficherTerritoire(tab, j1, j2, j3, j4, j5, j6, n);
		
		
		c.afficherMessage("Joueur " + i, "Veuillez cliquer sur un de vos territoires", "", "");
	}
}
