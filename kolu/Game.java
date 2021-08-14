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
    public static int nbreTry = 0; // Nbre d'essais maximale à définir

    static Scanner myScan = new Scanner( System.in );

    /**
     * Constructeur qui permet d'initialiser le champ et de dessiner les bateaux
      */

    Game() {
        g.initialization(); // Initlisation du champ par des 0 (Mer)
        g.iniList(); // Initialisation de la liste myList contenant les pts peuvant considérés comme pt boat
       

        int i = 0, b = g.nbre; //nbre_boat()+1
        ArrayList<Point> detail = new ArrayList<Point>(){{
            add(Point.element(5, 1));
            add(Point.element(4, 1));
            add(Point.element(3, 2));
            add(Point.element(2, 1));
        }};

        System.out.println( "Voulez-vous changer les bateaux ?  yes/no " );
        String ans = myScan.nextLine();
        if (ans.equals( "yes" )) {
            detail.clear();
            g.nbre_boat();
            b = g.nbre;

            int j = 1;
            do {
                j++;
                System.out.println( "Nmbre de bateaux de longeur " + j);
                int k = myScan.nextInt();
                int back = detail.size();
                if (i + k > b)
                    k = b - i;
                detail.add(back, Point.element( j, k));
                i = i + k;
            } while (i < b);
            String ignore = myScan.nextLine();  // ignorer la saisie automatique
        }

        for(Point d : detail){
            /**
            if(!g.myList.isEmpty()){
                System.out.println( "Pas possible ");
                return;
            }*/
            for (int k = 0; k < d.getY() ; k++){
                g.drawBoat(d.getX());
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

        System.out.println( " \n\n\n\t\tPartie terminé. Votre score est de : " + nbreTry + "essais"); // (info)
        System.out.println( " \n\n\n\t\t");
    }
}
