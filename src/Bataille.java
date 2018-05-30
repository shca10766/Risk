
public class Bataille {

	public void attaque(Joueur j, Carte c, String [] tab) {
		ActionOrdi o = new ActionOrdi();
		int i1 = o.click(c);
		
		while (!j.contientListe(i1)) {
			i1 = o.click(c);
		}
		String t1 = tab[i1];
		
		int i2 = o.click(c);
		String t2 = tab[i2];
		
		boolean b = c.verifCorrespondance(t1, t2);
		while (!b || j.contientListe(i2)) {
			i2 = o.click(c);
			t2 = tab[i2];
			b = c.verifCorrespondance(t1, t2);
		}
		
		int i = j.getIndex();
		c.afficherMessage("Joueur " + i, "Le territoire " + t1 + " attaque le territoire " + t2, "", "");
	}
	
}
