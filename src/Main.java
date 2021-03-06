public class Main {

	public static void main(String[] args) {
		
		String [] tab = {
				"Alaska", "Territoires du Nord", "Groenland", "Alberta", "Ontario", "Quebec", "Ouest des Etats-Unis", "Est des Etats-Unis", "Am�rique Centrale",
				"Venezuela", "Br�sil", "P�ru", "Argentine",
				"Afrique du Nord", "Egypte", "Congo", "Est de l'Afrique", "Sud de l'Afrique", "Madadascar",
				"Est Australien", "Ouest Australien", "Nouvelle Guin�e", "Indon�sie",
				"Moyen Orient", "Inde", "Siam", "Chine", "Afghanistan", "Mongolie", "Japon", "Oural", "Irkoutsk", "Kamchatka", "Sib�rie", "Yakoutsk",
				"Ukraine", "Scandinavie", "Nord de l'Europe", "Sud de l'Europe", "Ouest de l'Europe", "Islande", "Grande Bretagne"			
		};
		// Cr�ation de toute les arm�es
		Armee [] tabArmee = new Armee[42];
		
		Armee a0 = new Armee(); tabArmee[0] = a0;
		Armee a1 = new Armee(); tabArmee[1] = a1;
		Armee a2 = new Armee(); tabArmee[2] = a2;
		Armee a3 = new Armee(); tabArmee[3] = a3;
		Armee a4 = new Armee(); tabArmee[4] = a4;
		Armee a5 = new Armee(); tabArmee[5] = a5;
		Armee a6 = new Armee(); tabArmee[6] = a6;
		Armee a7 = new Armee(); tabArmee[7] = a7;
		Armee a8 = new Armee(); tabArmee[8] = a8;
		Armee a9 = new Armee(); tabArmee[9] = a9;
		Armee a10 = new Armee(); tabArmee[10] = a10;
		Armee a11 = new Armee(); tabArmee[11] = a11;
		Armee a12 = new Armee(); tabArmee[12] = a12;
		Armee a13 = new Armee(); tabArmee[13] = a13;
		Armee a14 = new Armee(); tabArmee[14] = a14;
		Armee a15 = new Armee(); tabArmee[15] = a15;
		Armee a16 = new Armee(); tabArmee[16] = a16;
		Armee a17 = new Armee(); tabArmee[17] = a17;
		Armee a18 = new Armee(); tabArmee[18] = a18;
		Armee a19 = new Armee(); tabArmee[19] = a19;
		Armee a20 = new Armee(); tabArmee[20] = a20;
		Armee a21 = new Armee(); tabArmee[21] = a21;
		Armee a22 = new Armee(); tabArmee[22] = a22;
		Armee a23 = new Armee(); tabArmee[23] = a23;
		Armee a24 = new Armee(); tabArmee[24] = a24;
		Armee a25 = new Armee(); tabArmee[25] = a25;
		Armee a26 = new Armee(); tabArmee[26] = a26;
		Armee a27 = new Armee(); tabArmee[27] = a27;
		Armee a28 = new Armee(); tabArmee[28] = a28;
		Armee a29 = new Armee(); tabArmee[29] = a29;
		Armee a30 = new Armee(); tabArmee[30] = a30;
		Armee a31 = new Armee(); tabArmee[31] = a31;
		Armee a32 = new Armee(); tabArmee[32] = a32;
		Armee a33 = new Armee(); tabArmee[33] = a33;
		Armee a34 = new Armee(); tabArmee[34] = a34;
		Armee a35 = new Armee(); tabArmee[35] = a35;
		Armee a36 = new Armee(); tabArmee[36] = a36;
		Armee a37 = new Armee(); tabArmee[37] = a37;
		Armee a38 = new Armee(); tabArmee[38] = a38;
		Armee a39 = new Armee(); tabArmee[39] = a39;
		Armee a40 = new Armee(); tabArmee[40] = a40;
		Armee a41 = new Armee(); tabArmee[41] = a41;
		
		// Choix du nombre de joueurs
		Carte c = new Carte();
		c.AfficherCarte();
		c.afficherMessage("Combien de joueurs �tes vous ?", "Choisir un chiffre entre 2 et 6", "", "");
		
		ActionOrdi o = new ActionOrdi();
		
		int n = o.touchePresse();
		
		while (n < 2 || n > 6) {
			c.afficherMessage("", "", "Entr�e invalide veuillez r�essayer", "");
			n = o.touchePresse();
		}
		
		// initialisation des joueurs
		Joueur j1 = new Joueur(1); c.j1 = j1;
		Joueur j2 = new Joueur(2); c.j2 = j2;
		Joueur j3 = new Joueur(3); c.j3 = j3;
		Joueur j4 = new Joueur(4); c.j4 = j4;
		Joueur j5 = new Joueur(5); c.j5 = j5;
		Joueur j6 = new Joueur(6); c.j6 = j6;
		
		c.tabArmee = tabArmee;
		c.n = n;
		
		
		Initialisation init = new Initialisation(n);
		
		// Initialisation des territoires
		init.initialisationTerritoire(j1, j2, j3, j4, j5, j6);
		
		c.afficherTerritoire();	
		
		// Initialisation de l'arm�e
		switch (n) {
		case 2:
			c.afficherMessage("Joueur 1 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j1);
			c.afficherMessage("Joueur 2 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j2);
			break;
		case 3:
			c.afficherMessage("Joueur 1 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j1);
			c.afficherMessage("Joueur 2 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j2);
			c.afficherMessage("Joueur 3 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j3);
			break;
		case 4:
			c.afficherMessage("Joueur 1 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j1);
			c.afficherMessage("Joueur 2 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j2);
			c.afficherMessage("Joueur 3 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j3);
			c.afficherMessage("Joueur 4 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j4);
			break;
		case 5:
			c.afficherMessage("Joueur 1 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j1);
			c.afficherMessage("Joueur 2 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j2);
			c.afficherMessage("Joueur 3 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j3);
			c.afficherMessage("Joueur 4 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j4);
			c.afficherMessage("Joueur 5 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j5);
			break;
		case 6: 
			c.afficherMessage("Joueur 1 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j1);
			c.afficherMessage("Joueur 2 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j2);
			c.afficherMessage("Joueur 3 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j3);
			c.afficherMessage("Joueur 4 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j4);
			c.afficherMessage("Joueur 5 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j5);
			c.afficherMessage("Joueur 6 : Initialisez votre arm�e", "Pour cela cliquez sur un", "de vos territoires pour", "ajouter une arm�e");
			init.initialisationArmee(tab, tabArmee, c, j6);
			break;		
		}
		
		int nombreTour = 0;
		boolean V1 = false;
		boolean V2 = false;
		boolean V3 = false;
		boolean V4 = false;
		boolean V5 = false;
		boolean V6 = false;
		
		// Partie en cours jusqu'� la victoire d'un des joueurs
		while (!V1 && !V2 && !V3 && !V4 && !V5 && !V6) {
			Tour T1 = new Tour(j1);
			Tour T2 = new Tour(j2);
			Tour T3 = new Tour(j3);
			Tour T4 = new Tour(j4);
			Tour T5 = new Tour(j5);
			Tour T6 = new Tour(j6);
			
			switch (n) {
			case 2 : 
				T1.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
				V1 = j1.verifVictoire();
				
				// SI le joueur 1 n'a pas gagn�
				if (!V1) {
					T2.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V2 = j2.verifVictoire();
				}
				break;
			case 3:
				if (!j1.alTerritoire.isEmpty()) {
					T1.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V1 = j1.verifVictoire();
				}
				
				if (!V1 && !j2.alTerritoire.isEmpty()) {
					T2.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V2 = j2.verifVictoire();
				}
				if ((!V1 && !V2) && !j3.alTerritoire.isEmpty()) {
					T3.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V3 = j3.verifVictoire();
				}
				break;
			case 4:
				if (!j1.alTerritoire.isEmpty()) {
					T1.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V1 = j1.verifVictoire();
				}
				
				if (!V1  && !j2.alTerritoire.isEmpty()) {
					T2.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V2 = j2.verifVictoire();
				}
				if ((!V1 && !V2) && !j3.alTerritoire.isEmpty()) {
					T3.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V3 = j3.verifVictoire();
				}
				if ((!V1 && !V2 && !V3) && !j4.alTerritoire.isEmpty()) {
					T4.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V4 = j4.verifVictoire();
				}
				break;
			case 5:
				if (!j1.alTerritoire.isEmpty()) {
					T1.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V1 = j1.verifVictoire();
				}
				
				if (!V1  && !j2.alTerritoire.isEmpty()) {
					T2.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V2 = j2.verifVictoire();
				}
				if ((!V1 && !V2) && !j3.alTerritoire.isEmpty()) {
					T3.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V3 = j3.verifVictoire();
				}
				if ((!V1 && !V2 && !V3) && !j4.alTerritoire.isEmpty()) {
					T4.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V4 = j4.verifVictoire();
				}
				if ((!V1 && !V2 && !V3 && !V4) && !j5.alTerritoire.isEmpty()) {
					T5.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V5 = j5.verifVictoire();
				}
				break;
			case 6 :
				if (!j1.alTerritoire.isEmpty()) {
					T1.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V1 = j1.verifVictoire();
				}
				
				if (!V1  && !j2.alTerritoire.isEmpty()) {
					T2.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V2 = j2.verifVictoire();
				}
				if ((!V1 && !V2) && !j3.alTerritoire.isEmpty()) {
					T3.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V3 = j3.verifVictoire();
				}
				if ((!V1 && !V2 && !V3) && !j4.alTerritoire.isEmpty()) {
					T4.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V4 = j4.verifVictoire();
				}
				if ((!V1 && !V2 && !V3 && !V4) && !j5.alTerritoire.isEmpty()) {
					T5.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V5 = j5.verifVictoire();
				}
				if ((!V1 && !V2 && !V3 && !V4 && !V5) && !j6.alTerritoire.isEmpty()) {
					T6.tour(nombreTour, c, o, j1, j2, j3, j4, j5, j6, tab, tabArmee, n);
					V6 = j6.verifVictoire();
				}
				break;
			}
			
			nombreTour++;
		}
		c.afficherTerritoire();
		// Cas du joueur gagnant
		if (V1) {
			c.afficherMessage("Victoire du Joueur 1", "", "", "");
		}
		else if (V2) {
			c.afficherMessage("Victoire du joueur 2", "", "", "");
		}
		else if (V3) {
			c.afficherMessage("Victoire du joueur 3", "", "", "");
		}
		else if (V4) {
			c.afficherMessage("Victoire du joueur 4", "", "", "");
		}
		else if (V5) {
			c.afficherMessage("Victoire du joueur 5", "", "", "");
		}
		else {
			c.afficherMessage("Victoire du joueur 6", "", "", "");
		}
		
	}
	
}
