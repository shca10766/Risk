import java.util.ArrayList;

public class Deplacement {

	public ArrayList al;
	public Joueur j;
	public int t1;
	public int t2;
	
	public Deplacement(Joueur J, ArrayList L, int T1, int T2) {
		this.al = L;
		this.j = J;
		this.t1 = T1;
		this.t2 = T2;
	}
	
	public Deplacement(Joueur J) {
		this.j = J;
	}
	
	public void deplacement(Carte c, Armee [] tab, String [] tabTerritoire) {
		Joueur j = this.j;
		ActionOrdi o = new ActionOrdi();
		int territoireDepart = choixTerritoire(o, c);
		int territoireArrive = choixTerritoire(o, c);
		
		String t1 = tabTerritoire[territoireDepart];
		String t2 = tabTerritoire[territoireArrive];
		boolean territoireVoisin = c.verifCorrespondance(t1, t2);
		
		while (!territoireVoisin) {
			territoireArrive = choixTerritoire(o, c);
			t2 = tabTerritoire[territoireArrive];
			territoireVoisin = c.verifCorrespondance(t1, t2);
		}
		
		Armee a1 = tab[territoireDepart];
		Armee a2 = tab[territoireArrive];
		
		int cn = a1.getCanon();
		int cv = a1.getCavalier();
		int s = a1.getSoldat();
		int p = a1.puissance();
		
		int cn0 = a1.getCanon0Mouvement();
		int cv0 = a1.getCavalier0Mouvement();
		int s0 = a1.getSoldat0Mouvement();
		
		ArrayList armeeDeplace = new ArrayList();
		
		c.afficherMessage("Combien de canon deplacer? (" + cn0 + " au repos)", "Combien de cavalier deplacer? (" + cv0 + " au repos)", "Combien de soldat deplacer?  (" + s0 + " au repos)", "");
		
		int cnDep = o.touchePresse();
		if ((cnDep <= cn) && (p - cnDep > 0) && (cn - cn0) >= cnDep) {
			armeeDeplace.add("canon");
		}
		p = p - cnDep;
		
		int cvDep = o.touchePresse();
		if ((cvDep <= cv) && (p - cvDep > 0) && (cv - cv0) >= cvDep) {
			armeeDeplace.add("cavalier");
		}
		p = p - cvDep;
		
		int sDep = o.touchePresse();
		if ((sDep <= s) && (p - sDep > 0) && (s - s0) >= sDep) {
			armeeDeplace.add("soldat");
		}
		listeDeplace(armeeDeplace, a2, a1);
	}
	
	public int choixTerritoire(ActionOrdi o, Carte c) {
		Joueur j = this.j;
		int t = o.click(c);
		boolean possessionJoueur = j.contientListe(t);
		while (!possessionJoueur) {
			t = o.click(c);
			possessionJoueur = j.contientListe(t);
		}
		return t;
	}
	
	public Joueur joueurVaincu(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6) {
		int i = this.t1;
		if (j1.contientListe(i)) {return j1;}
		else if (j2.contientListe(i)) {return j2;}
		else if (j3.contientListe(i)) {return j3;}
		else if (j4.contientListe(i)) {return j4;}
		else if (j5.contientListe(i)) {return j5;}
		else {return j6;}
	}
	
	public void invasion(Armee [] tab, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6) {		
		int k1 = this.t1;
		int k2 = this.t2;
		Armee aVaincu = tab[k1];
		Armee aVainqueur = tab[k2];
		
		Joueur jVaincu = joueurVaincu(j1, j2, j3, j4, j5, j6);
		jVaincu.removeListe(k1);
		
		Joueur jVainqueur = this.j;
		jVainqueur.ajoutListe(k1);
		
		ArrayList unitDeplace = this.al;
		listeDeplace(al, aVaincu, aVainqueur);
		
	}
	
	public void listeDeplace(ArrayList al, Armee a1, Armee a2) {
		int cv1 = a2.getCavalier1Mouvement();
		int cv2 = a2.getCavalier2Mouvement();
		int s1 = a2.getSoldat1Mouvement();
		
		int cv = a2.getCavalier();
		int so = a2.getSoldat();
		
		int s = al.size();
		for (int i = 0; i < s; i++) {
			String u = (String) al.get(i);
			switch (u) {
			case "soldat":
				a1.setSoldat(1);
				a2.setSoldat(-1);
				/*if (s1 == 0) {
					a1.setSoldat1(1);
				}
				else {
					a2.setSoldat1(-1);
					a1.setSoldat0(1);
				}*/
				break;
			case "cavalier" :
				a1.setCavalier(1);
				a2.setCavalier(-1);
				/*if (cv2 == 0 && cv1 == 0) {
					a1.setCavalier2(1);
				}
				else if (cv2 != 0) {
					a2.setCavalier2(-1);
					a1.setCavalier1(1);
				}
				else {
					a2.setCavalier1(-1);
					a1.setCavalier0(1);
				}*/
				break;
			default :
				a1.setCanon(1);
				a2.setCanon(-1);
				//a1.setCanon0(1);
				break;
			}
			cv1 = a2.getCavalier1Mouvement();
			cv2 = a2.getCavalier2Mouvement();
			s1 = a2.getSoldat1Mouvement();
			
			cv = a2.getCavalier();
			so = a2.getSoldat();
		}
	}
}
