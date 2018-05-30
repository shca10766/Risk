import java.awt.Font;
import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

public class Carte {
	
	private int t1;
	private int t2;

	
	public void setT1(int t) {this.t1 = t;}
	public void setT2(int t) {this.t2 = t;}
	
	public int getT1() {return this.t1;}
	public int getT2() {return this.t2;}
	
	public void AfficherCarte() {
		StdDraw.setCanvasSize(1300, 700);
		StdDraw.picture(0.5, 0.5, "carte.png");
		StdDraw.setXscale(0,1300);							
		StdDraw.setYscale(700,0);
	}
	
	public void afficherMessage(String l1, String l2, String l3, String l4) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(1140, 200, l1);
		StdDraw.text(1140, 230, l2);
		StdDraw.text(1140, 260, l3);
		StdDraw.text(1140, 290, l4);
	}
	
	public int xCorrespondance(int n) {
		if (n == 0) {return 80;}
		else if (n == 1) {return 150;}
		else if (n == 2) {return 350;}
		else if (n == 3) {return 160;}
		else if (n == 4) {return 220;}
		else if (n == 5) {return 280;}
		else if (n == 6) {return 160;}
		else if (n == 7) {return 230;}
		else if (n == 8) {return 170;}
		
		else if (n == 9) {return 220;}
		else if (n == 10) {return 310;}
		else if (n == 11) {return 250;}
		else if (n == 12) {return 250;}
		
		else if (n == 13) {return 450;}
		else if (n == 14) {return 540;}
		else if (n == 15) {return 540;}
		else if (n == 16) {return 600;}
		else if (n == 17) {return 550;}
		else if (n == 18) {return 630;}
		
		else if (n == 19) {return 950;}
		else if (n == 20) {return 850;}
		else if (n == 21) {return 905;}
		else if (n == 22) {return 820;}
		
		else if (n == 23) {return 610;}
		else if (n == 24) {return 730;}
		else if (n == 25) {return 810;}
		else if (n == 26) {return 790;}
		else if (n == 27) {return 670;}
		else if (n == 28) {return 810;}
		else if (n == 29) {return 910;}
		else if (n == 30) {return 690;}
		else if (n == 31) {return 800;}
		else if (n == 32) {return 860;}
		else if (n == 33) {return 730;}
		else if (n == 34) {return 810;}
		
		else if (n == 35) {return 590;}
		else if (n == 36) {return 500;}
		else if (n == 37) {return 480;}
		else if (n == 38) {return 520;}
		else if (n == 39) {return 420;}
		else if (n == 40) {return 425;}
		else if (n == 41) {return 425;}
		else {return 0;}
	}
	
	public int yCorrespondance(int n) {
		if (n == 0) {return 110;}
		else if (n == 1) {return 90;}
		else if (n == 2) {return 80;}
		else if (n == 3) {return 160;}
		else if (n == 4) {return 160;}
		else if (n == 5) {return 190;}
		else if (n == 6) {return 250;}
		else if (n == 7) {return 270;}
		else if (n == 8) {return 320;}
		
		else if (n == 9) {return 390;}
		else if (n == 10) {return 460;}
		else if (n == 11) {return 452;}
		else if (n == 12) {return 560;}
		
		else if (n == 13) {return 430;}
		else if (n == 14) {return 390;}
		else if (n == 15) {return 510;}
		else if (n == 16) {return 475;}
		else if (n == 17) {return 600;}
		else if (n == 18) {return 605;}
		
		else if (n == 19) {return 580;}
		else if (n == 20) {return 590;}
		else if (n == 21) {return 483;}
		else if (n == 22) {return 490;}
		
		else if (n == 23) {return 340;}
		else if (n == 24) {return 360;}
		else if (n == 25) {return 390;}
		else if (n == 26) {return 320;}
		else if (n == 27) {return 260;}
		else if (n == 28) {return 250;}
		else if (n == 29) {return 225;}
		else if (n == 30) {return 170;}
		else if (n == 31) {return 150;}
		else if (n == 32) {return 110;}
		else if (n == 33) {return 120;}
		else if (n == 34) {return 100;}
		
		else if (n == 35) {return 190;}
		else if (n == 36) {return 130;}
		else if (n == 37) {return 240;}
		else if (n == 38) {return 300;}
		else if (n == 39) {return 330;}
		else if (n == 40) {return 150;}
		else if (n == 41) {return 220;}
		else {return 0;}
	}
	
	public void afficherPoint(Armee [] tab, ArrayList al, int c) {
		
		int s ; int cn ; int cv ;
		int l = al.size();
		for (int i = 0; i < l; i++) {
			int n = (int) al.get(i);
			Armee a = tab[n];
			s = a.getSoldat();
			cn = a.getCanon();
			cv = a.getCavalier();
			int x = xCorrespondance(n);
			int y = yCorrespondance(n);
			switch (c) {
				case 1 :
					StdDraw.setPenColor(StdDraw.RED);
					break;
				case 2 :
					StdDraw.setPenColor(StdDraw.BLUE);
					break;
				case 3 :
					StdDraw.setPenColor(StdDraw.WHITE);
					break;
				case 4 :
					StdDraw.setPenColor(StdDraw.ORANGE);
					break;
				case 5 :
					StdDraw.setPenColor(StdDraw.MAGENTA);
					break;
				case 6 :
					StdDraw.setPenColor(StdDraw.BLACK);
					break;
			}
			StdDraw.filledCircle(x, y, 5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.circle(x, y, 5);
			
			StdDraw.rectangle(x, y + 6, 25, 13);
			
			Font font = new Font("Arial", Font.BOLD, 15);
			StdDraw.setFont(font);
			StdDraw.text(x, y + 15, s+":"+cv+":"+cn);
		}
	}
			
	public int clickCorrespondance(double x, double y) {
		
		// Europe
		if ((x < 435 && x > 415) && (y < 230 && y > 210)) {return 41;}
		else if ((x < 435 && x > 415) && (y < 160 && y > 140)) {return 40;}
		else if ((x < 430 && x > 410) && (y < 340 && y > 320)) {return 39;}
		else if ((x < 530 && x > 510) && (y < 310 && y > 290)) {return 38;}
		else if ((x < 490 && x > 470) && (y < 250 && y > 230)) {return 37;}
		else if ((x < 510 && x > 490) && (y < 140 && y > 120)) {return 36;}
		else if ((x < 600 && x > 580) && (y < 200 && y > 180)) {return 35;}
		
		// Asie
		else if ((x < 820 && x > 800) && (y < 110 && y > 90)) {return 34;}
		else if ((x < 740 && x > 720) && (y < 130 && y > 110)) {return 33;}
		else if ((x < 870 && x > 850) && (y < 120 && y > 100)) {return 32;}
		else if ((x < 810 && x > 790) && (y < 160 && y > 140)) {return 31;}
		else if ((x < 700 && x > 680) && (y < 180 && y > 160)) {return 30;}
		else if ((x < 920 && x > 900) && (y < 235 && y > 215)) {return 29;}
		else if ((x < 820 && x > 800) && (y < 260 && y > 240)) {return 28;}
		else if ((x < 680 && x > 660) && (y < 270 && y > 250)) {return 27;}
		else if ((x < 800 && x > 780) && (y < 330 && y > 310)) {return 26;}
		else if ((x < 820 && x > 800) && (y < 400 && y > 380)) {return 25;}
		else if ((x < 740 && x > 720) && (y < 370 && y > 350)) {return 24;}
		else if ((x < 620 && x > 600) && (y < 350 && y > 330)) {return 23;}
		
		// Oc�anie
		else if ((x < 960 && x > 940) && (y < 590 && y > 570)) {return 19;}
		else if ((x < 860 && x > 840) && (y < 600 && y > 580)) {return 20;}
		else if ((x < 910 && x > 900) && (y < 490 && y > 475)) {return 21;}
		else if ((x < 830 && x > 810) && (y < 500 && y > 480)) {return 22;}
		
		// Am�rique du Sud
		else if ((x < 230 && x > 210) && (y < 400 && y > 380)) {return 9;}
		else if ((x < 320 && x > 300) && (y < 470 && y > 450)) {return 10;}
		else if ((x < 260 && x > 240) && (y < 460 && y > 440)) {return 11;}
		else if ((x < 260 && x > 240) && (y < 570 && y > 550)) {return 12;}
		
		// Afrique
		else if ((x < 460 && x > 440) && (y < 440 && y > 420)) {return 13;}
		else if ((x < 550 && x > 530) && (y < 400 && y > 380)) {return 14;}
		else if ((x < 550 && x > 530) && (y < 520 && y > 500)) {return 15;}
		else if ((x < 610 && x > 590) && (y < 480 && y > 470)) {return 16;}
		else if ((x < 560 && x > 540) && (y < 610 && y > 590)) {return 17;}
		else if ((x < 640 && x > 620) && (y < 610 && y > 600)) {return 18;}
		
		// Am�rique du Nord
		else if ((x < 90 && x > 70) && (y < 120 && y > 100)) {return 0;}
		else if ((x < 160 && x > 140) && (y < 100 && y > 80)) {return 1;}
		else if ((x < 360 && x > 340) && (y < 90 && y > 70)) {return 2;}
		else if ((x < 170 && x > 150) && (y < 170 && y > 150)) {return 3;}
		else if ((x < 240 && x > 210) && (y < 170 && y > 150)) {return 4;}
		else if ((x < 290 && x > 270) && (y < 200 && y > 180)) {return 5;}
		else if ((x < 170 && x > 150) && (y < 260 && y > 240)) {return 6;}
		else if ((x < 240 && x > 220) && (y < 280 && y > 260)) {return 7;}
		else if ((x < 180 && x > 160) && (y < 330 && y > 310)) {return 8;}
		
		else {return -1;}
	}
	
	public boolean verifCorrespondance(String t1, String t2) {
		switch(t1) {
			case "Alaska":
				if (t2 == "Territoires du Nord" || t2  == "Alberta" || t2 == "Kamchatka") {return true;}
				else {return false;}
			case "Territoires du Nord":
				if (t2 == "Alaska" || t2  == "Alberta" || t2 == "Ontario" || t2 == "Groenland") {return true;}
				else {return false;}
			case "Groenland":
				if (t2 == "Territoires du Nord" || t2  == "Ontario" || t2 == "Quebec" || t2 == "Islande") {return true;}
				else {return false;}
			case "Alberta":
				if (t2 == "Territoires du Nord" || t2  == "Alaska" || t2 == "Ontario" || t2 == "Ouest des Etats-Unis" ) {return true;}
				else {return false;}
			case "Ontario":
				if (t2 == "Territoires du Nord" || t2  == "Alberta" || t2 == "Quebec" || t2 == "Ouest des Etats-Unis" || t2 == "Est des Etats-Unis" || t2 == "Groenland" ) {return true;}
				else {return false;}
			case "Quebec":
				if (t2 == "Ontario" ||  t2 == "Est des Etats-Unis" || t2 == "Groenland" ) {return true;}
				else {return false;}
			case "Est des Etats-Unis":
				if (t2  == "Ontario" || t2 == "Quebec" || t2 == "Ouest des Etats-Unis" || t2 == "Am�rique Centrale") {return true;}
				else {return false;}
			case "Ouest des Etats-Unis":
				if (t2 == "Ontario" || t2  == "Alberta" || t2 == "Est des Etats-Unis" || t2 == "Am�rique Centrale" ) {return true;}
				else {return false;}
			case "Am�rique Centrale":
				if (t2  == "Venezuela" || t2 == "Ouest des Etats-Unis" || t2 == "Est des Etats-Unis") {return true;}
				else {return false;}
				
				
			case "Venezuela":
				if (t2 == "Am�rique Centrale" || t2  == "Br�sil" || t2 == "P�ru") {return true;}
				else {return false;}
			case "Br�sil":
				if (t2 == "Venezuela" || t2  == "Argentine" || t2 == "P�ru" || t2 == "Afrique du Nord") {return true;}
				else {return false;}
			case "P�ru":
				if (t2 == "Venezuela" || t2  == "Br�sil" || t2 == "Argentine") {return true;}
				else {return false;}
			case "Argentine":
				if (t2  == "Br�sil" || t2 == "P�ru") {return true;}
				else {return false;}
			
				
			case "Afrique du Nord":
				if (t2 == "Ouest de l'Europe" || t2  == "Br�sil" || t2 == "Sud de l'Europe" || t2 == "Egypte" || t2 == "Est de l'Afrique" || t2 == "Congo") {return true;}
				else {return false;}
			case "Egypte":
				if (t2 == "Afrique du Nord" || t2  == "Moyen Orient" || t2 == "Sud de l'Europe" || t2 == "Est de l'Afrique") {return true;}
				else {return false;}
			case "Est de l'Afrique":
				if (t2 == "Moyen Orient" || t2  == "Sud de l'Afrique" || t2 == "Madadascar" || t2 == "Egypte" || t2 == "Afrique du Nord" || t2 == "Congo") {return true;}
				else {return false;}
			case "Congo":
				if (t2 == "Afrique du Nord" || t2  == "Sud de l'Afrique" || t2 == "Est de l'Afrique") {return true;}
				else {return false;}
			case "Sud de l'Afrique":
				if (t2 == "Madadascar" || t2 == "Est de l'Afrique" || t2 == "Congo") {return true;}
				else {return false;}
			case "Madadascar":
				if (t2 == "Sud de l'Afrique" || t2 == "Est de l'Afrique") {return true;}
				else {return false;}
				
				
			case "Est Australien":
				if (t2 == "Ouest Australien" || t2 == "Nouvelle Guin�e") {return true;}
				else {return false;}
			case "Ouest Australien":
				if (t2 == "Est Australien" || t2 == "Indon�sie") {return true;}
				else {return false;}
			case "Indon�sie":
				if (t2 == "Ouest Australien" || t2 == "Nouvelle Guin�e" || t2 == "Siam") {return true;}
				else {return false;}
			case "Nouvelle Guin�e":
				if (t2 == "Est Australien" || t2 == "Indon�sie") {return true;}
				else {return false;}
				
				
			case "Siam":
				if (t2 == "Indon�sie" || t2 == "Inde" || t2 == "Chine") {return true;}
				else {return false;}
			case "Inde":
				if (t2 == "Siam" || t2 == "Chine" || t2 == "Afghanistan" || t2 == "Moyen Orient") {return true;}
				else {return false;}
			case "Chine":
				if (t2 == "Siam" || t2 == "Inde" || t2 == "Afghanistan" || t2 == "Oural" || t2 == "Sib�rie" || t2 == "Mongolie") {return true;}
				else {return false;}
			case "Moyen Orient":
				if (t2 == "Inde" || t2 == "Egypte" || t2 == "Afghanistan" || t2 == "Est de l'Afrique" || t2 == "Ukraine" || t2 == "Sud de l'Europe") {return true;}
				else {return false;}
			case "Afghanistan":
				if (t2 == "Inde" || t2 == "Chine" || t2 == "Oural" || t2 == "Moyen Orient" || t2 == "Ukraine") {return true;}
				else {return false;}
			case "Oural":
				if (t2 == "Ukraine" || t2 == "Chine" || t2 == "Afghanistan" || t2 == "Sib�rie") {return true;}
				else {return false;}
			case "Sib�rie":
				if (t2 == "Oural" || t2 == "Chine" || t2 == "Yakoutsk" || t2 == "Irkoutsk" || t2 == "Mongolie") {return true;}
				else {return false;}
			case "Mongolie":
				if (t2 == "Japon" || t2 == "Chine" || t2 == "Sib�rie" || t2 == "Irkoutsk" || t2 == "Kamchatka") {return true;}
				else {return false;}
			case "Irkoutsk":
				if (t2 == "Sib�rie" || t2 == "Mongolie" || t2 == "Yakoutsk" || t2 == "Kamchatka") {return true;}
				else {return false;}
			case "Yakoutsk":
				if (t2 == "Sib�rie" || t2 == "Irkoutsk" || t2 == "Kamchatka") {return true;}
				else {return false;}
			case "Kamchatka":
				if (t2 == "Alaska" || t2 == "Japon" || t2 == "Mongolie" || t2 == "Irkoutsk" || t2 == "Yakoutsk") {return true;}
				else {return false;}
			case "Japon":
				if (t2 == "Mongolie" || t2 == "Kamchatka") {return true;}
				else {return false;}
				
				
			case "Ukraine":
				if (t2 == "Oural" || t2 == "Afghanistan" || t2 == "Moyen Orient" || t2 == "Sud de l'Europe" || t2 == "Nord de l'Europe" || t2 == "Scandinavie") {return true;}
				else {return false;}
			case "Sud de l'Europe":
				if (t2 == "Ukraine" || t2 == "Moyen Orient" || t2 == "Nord de l'Europe" || t2 == "Ouest de l'Europe" || t2 == "Egypte") {return true;}
				else {return false;}
			case "Ouest de l'Europe":
				if (t2 == "Grande Bretagne" || t2 == "Nord de l'Europe" || t2 == "Sud de l'Europe" || t2 == "Afrique du Nord") {return true;}
				else {return false;}
			case "Nord de l'Europe":
				if (t2 == "Grande Bretagne" || t2 == "Ouest de l'Europe" || t2 == "Sud de l'Europe" || t2 == "Scandinavie" || t2 == "Ukraine") {return true;}
				else {return false;}
			case "Grande Bretagne":
				if (t2 == "Islande" || t2 == "Nord de l'Europe" || t2 == "Ouest de l'Europe" || t2 == "Scandinavie") {return true;}
				else {return false;}
			case "Scandinavie":
				if (t2 == "Grande Bretagne" || t2 == "Nord de l'Europe" || t2 == "Islande" || t2 == "Ukraine") {return true;}
				else {return false;}
			case "Islande":
				if (t2 == "Grande Bretagne" || t2 == "Scandinavie" || t2 == "Groenland") {return true;}
				else {return false;}
			default :
				return false;					
		}
	}
	
	public void afficherTerritoire(Armee [] tab, Joueur j1, Joueur j2, Joueur j3, Joueur j4, Joueur j5, Joueur j6, int n) {
		switch(n) {
		case 2 :
			afficherPoint(tab, j1.getListeTerritoire(), 1);
			afficherPoint(tab, j2.getListeTerritoire(), 2);
			break;
		case 3 :
			afficherPoint(tab, j1.getListeTerritoire(), 1);
			afficherPoint(tab, j2.getListeTerritoire(), 2);
			afficherPoint(tab, j3.getListeTerritoire(), 3);
			break;
		case 4 :
			afficherPoint(tab, j1.getListeTerritoire(), 1);
			afficherPoint(tab, j2.getListeTerritoire(), 2);
			afficherPoint(tab, j3.getListeTerritoire(), 3);
			afficherPoint(tab, j4.getListeTerritoire(), 4);
			break;
		case 5 :
			afficherPoint(tab, j1.getListeTerritoire(), 1);
			afficherPoint(tab, j2.getListeTerritoire(), 2);
			afficherPoint(tab, j3.getListeTerritoire(), 3);
			afficherPoint(tab, j4.getListeTerritoire(), 4);
			afficherPoint(tab, j5.getListeTerritoire(), 5);
			break;
		case 6 :
			afficherPoint(tab, j1.getListeTerritoire(), 1);
			afficherPoint(tab, j2.getListeTerritoire(), 2);
			afficherPoint(tab, j3.getListeTerritoire(), 3);
			afficherPoint(tab, j4.getListeTerritoire(), 4);
			afficherPoint(tab, j5.getListeTerritoire(), 5);
			afficherPoint(tab, j6.getListeTerritoire(), 6);
			break;
		}
	}
}
