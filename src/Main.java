import edu.princeton.cs.introcs.StdDraw;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		String [] tab = {
				"Alaska", "Territoires du Nord", "Groenland", "Alberta", "Ontario", "Quebec", "Ouest des Etats-Unis", "Est des Etats-Unis", "Amérique Centrale",
				"Venezuela", "Brésil", "Péru", "Argentine",
				"Afrique du Nord", "Egypte", "Congo", "Est de l'Afrique", "Sud de l'Afrique", "Madadascar",
				"Est Australien", "Ouest Australien", "Nouvelle Guinée", "Indonésie",
				"Moyen Orient", "Inde", "Siam", "Chine", "Afghanistan", "Mongolie", "Japon", "Oural", "Irkoutsk", "Kamchatka", "Sibérie", "Yakoutsk",
				"Ukraine", "Scandinavie", "Nord de l'Europe", "Sud de l'Europe", "Ouest de l'Europe", "Islande", "Grande Bretagne"			
		};
		
		Armee [] tabArmee = new Armee[42];
		
		Armee a0 = new Armee(1, 0, 0, 0); tabArmee[0] = a0;
		Armee a1 = new Armee(1, 1, 0, 0); tabArmee[1] = a1;
		Armee a2 = new Armee(1, 2, 0, 0); tabArmee[2] = a2;
		Armee a3 = new Armee(1, 3, 0, 0); tabArmee[3] = a3;
		Armee a4 = new Armee(1, 4, 0, 0); tabArmee[4] = a4;
		Armee a5 = new Armee(1, 5, 0, 0); tabArmee[5] = a5;
		Armee a6 = new Armee(1, 6, 0, 0); tabArmee[6] = a6;
		Armee a7 = new Armee(1, 7, 0, 0); tabArmee[7] = a7;
		Armee a8 = new Armee(1, 8, 0, 0); tabArmee[8] = a8;
		Armee a9 = new Armee(1, 9, 0, 0); tabArmee[9] = a9;
		Armee a10 = new Armee(1, 10, 0, 0); tabArmee[10] = a10;
		Armee a11 = new Armee(1, 11, 0, 0); tabArmee[11] = a11;
		Armee a12 = new Armee(1, 12, 0, 0); tabArmee[12] = a12;
		Armee a13 = new Armee(1, 13, 0, 0); tabArmee[13] = a13;
		Armee a14 = new Armee(1, 14, 0, 0); tabArmee[14] = a14;
		Armee a15 = new Armee(1, 15, 0, 0); tabArmee[15] = a15;
		Armee a16 = new Armee(1, 16, 0, 0); tabArmee[16] = a16;
		Armee a17 = new Armee(1, 17, 0, 0); tabArmee[17] = a17;
		Armee a18 = new Armee(1, 18, 0, 0); tabArmee[18] = a18;
		Armee a19 = new Armee(1, 19, 0, 0); tabArmee[19] = a19;
		Armee a20 = new Armee(1, 20, 0, 0); tabArmee[20] = a20;
		Armee a21 = new Armee(1, 21, 0, 0); tabArmee[21] = a21;
		Armee a22 = new Armee(1, 22, 0, 0); tabArmee[22] = a22;
		Armee a23 = new Armee(1, 23, 0, 0); tabArmee[23] = a23;
		Armee a24 = new Armee(1, 24, 0, 0); tabArmee[24] = a24;
		Armee a25 = new Armee(1, 25, 0, 0); tabArmee[25] = a25;
		Armee a26 = new Armee(1, 26, 0, 0); tabArmee[26] = a26;
		Armee a27 = new Armee(1, 27, 0, 0); tabArmee[27] = a27;
		Armee a28 = new Armee(1, 28, 0, 0); tabArmee[28] = a28;
		Armee a29 = new Armee(1, 29, 0, 0); tabArmee[29] = a29;
		Armee a30 = new Armee(1, 30, 0, 0); tabArmee[30] = a30;
		Armee a31 = new Armee(1, 31, 0, 0); tabArmee[31] = a31;
		Armee a32 = new Armee(1, 32, 0, 0); tabArmee[32] = a32;
		Armee a33 = new Armee(1, 33, 0, 0); tabArmee[33] = a33;
		Armee a34 = new Armee(1, 34, 0, 0); tabArmee[34] = a34;
		Armee a35 = new Armee(1, 35, 0, 0); tabArmee[35] = a35;
		Armee a36 = new Armee(1, 36, 0, 0); tabArmee[36] = a36;
		Armee a37 = new Armee(1, 37, 0, 0); tabArmee[37] = a37;
		Armee a38 = new Armee(1, 38, 0, 0); tabArmee[38] = a38;
		Armee a39 = new Armee(1, 39, 0, 0); tabArmee[39] = a39;
		Armee a40 = new Armee(1, 40, 0, 0); tabArmee[40] = a40;
		Armee a41 = new Armee(1, 41, 0, 0); tabArmee[41] = a41;
		
		Carte c = new Carte();
		c.AfficherCarte();
		c.afficherMessage("Combien de joueurs êtes vous ?", "Choisir un chiffre entre 2 et 6", "", "");
		
		ActionOrdi o = new ActionOrdi();
		
		int n = o.touchePresse();
		
		Joueur j1 = new Joueur(1);
		Joueur j2 = new Joueur(2);
		Joueur j3 = new Joueur(3);
		Joueur j4 = new Joueur(4);
		Joueur j5 = new Joueur(5);
		Joueur j6 = new Joueur(6);
		
		Initialisation init = new Initialisation(n);
		
		init.initialisationTerritoire(c, j1, j2, j3, j4, j5, j6);
		
		c.AfficherCarte();
		c.afficherTerritoire(tabArmee, j1, j2, j3, j4, j5, j6, n);	
		
		Bataille bat = new Bataille();
		bat.attaque(j1, c, tab);
		
		switch (n) {
		case 2:
			init.initialisationArmee(tab, tabArmee, c, j1, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j2, j1, j2, j3, j4, j5, j6);
			break;
		case 3:
			init.initialisationArmee(tab, tabArmee, c, j1, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j2, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j3, j1, j2, j3, j4, j5, j6);
			break;
		case 4:
			init.initialisationArmee(tab, tabArmee, c, j1, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j2, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j3, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j4, j1, j2, j3, j4, j5, j6);
			break;
		case 5:
			init.initialisationArmee(tab, tabArmee, c, j1, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j2, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j3, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j4, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j5, j1, j2, j3, j4, j5, j6);
			break;
		case 6: 
			init.initialisationArmee(tab, tabArmee, c, j1, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j2, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j3, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j4, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j5, j1, j2, j3, j4, j5, j6);
			init.initialisationArmee(tab, tabArmee, c, j6, j1, j2, j3, j4, j5, j6);
			break;		}
		
		

	}
	
}
