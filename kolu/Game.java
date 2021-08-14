package be.kolu;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Game permet de lancer un jeu, elle a comme attributs :
 *      champ g : champ pour le jeu ;
 *      int nbreTry : nre maximale d'essais
 */

public class Game {

    Champ g = new Champ();
    public static int nbreTry; // Nbre d'essais maximale à définir

    static Scanner myScan = new Scanner( System.in );

    /**
     * Constructeur qui permet d'initialiser le champ et de dessiner les bateaux
      */

    Game() {
        nbreTry = 0;
        g.initialization(); // Initlisation du champ par des 0 (Mer)
        g.iniList(); // Initialisation de la liste myList contenant les pts peuvant considérés comme pt boat

        int i = 0, b = g.nbre; //nbre_boat()+1
        ArrayList<Point> detail = new ArrayList<Point>(){{
            add(Point.element(5, 1));
            add(Point.element(4, 1));
            add(Point.element(3, 2));
            add(Point.element(2, 1));
        }};

        System.out.println( "Voulez-vous définir les tailles des bateaux ?  yes/no " );
        String ans = myScan.nextLine();
        if (ans.equals( "yes" )) {
            detail.clear();
            g.nbre_boat();
            b = g.nbre;
			
			int tai = 0;
            int j = 1;
            do {
                j++;
                System.out.println( "Nombre de bateaux de longeur " + j);
                int k = myScan.nextInt();
                int back = detail.size();
                if (i + k > b)
                    k = b - i;
                detail.add(back, Point.element( j, k));
                i = i + k;
                tai += k*(i+2)*2; 
                if(tai > g.getTaille()){
					System.out.println("Pas assez de place");  
					detail.clear();
					i = 0;
					j = 1;
				}
            } while (i < b && (j<lon || j<lar));
            
				
            String ignore = myScan.nextLine();  // ignorer la saisie automatique
        }

        for(Point d : detail){
            for (int k = 0; k < d.getY() ; k++){
                if(g.myList.isEmpty()){
                    System.out.println( "Ce que vous demandé est impossible ");
                    return;
                }

                g.drawBoat(d.getX());
                g.iniList();
            }
        }
    }

    /**
     * Traitement à faire lors du lanceement un tir
     *
     * @param choice : 1 -> jeu USER, 2 -> jeu TRICH, 3 -> jeu IA (ORDINATEUR)
     */

    public void tryTir(int choice) {
        System.out.println( " \n");
        if (choice != 1) {// N      ormalement (choice == 2) : c'est pour la partie de type TRICH seulement
            g.displayBoats();
            g.display(true ); // Afficher les bateaux restant et "X" dans les emplacements des tirs effectués
        } if (choice == 3)
            g.tir( false ); // Tir automatique (avec IA)
        else
            g.tir( true ); // Tir manuel (USER)

        g.display(false); // Afficher juste les emplacement des tirs effectués et sans bateux
        nbreTry++;
    }

    /**
     * Le corps du jeu
     *
     * @param choice : 1 -> jeu USER, 2 -> jeu TRICH, 3 -> jeu IA (ORDINATEUR)
     */

    public void game(int choice) {
        do {
            tryTir( choice );
        } while (!g.boats.isEmpty()); // boats : liste contenant les bateux restant dans le champ

        System.out.println( " \n\n\n\t\tPartie terminé. Votre score est de : " + nbreTry + " essais"); // (info)
        System.out.println( " \n\n\n\t\t");
    }
}
