package be.kolu;
import java.util.Scanner;

/**
 * La classe Main contient le programme principale main()
 */

public class Main {

    /**
     * Le programme principale main() permet de lancer un jeu selon le choix de l'utilisateur
     *
     * @param args
     */

    public static void main(String[] args) {
        Scanner myScan = new Scanner( System.in );
        int choice = 0;

        // Le jeu
        do {
            Game jeu = new Game(); // Objet de type Game (contient un champ pour le jeu)

            // Affichage du menu
            do {

                System.out.println( " Pour une partie de type USER,   tapez 1 " );
                System.out.println( " Pour une partie de type TRICHE, tapez 2 " );
                System.out.println( " Pour une partie de type IA,     tapez 3 " ); // IA = Ordinateur
                System.out.print( " Votre choix : " );
                choice = myScan.nextInt();
            } while (choice != 1 && choice != 2 && choice != 3);

            // Lancer la partie
            jeu.game( choice );

            // Demander à l'utilisateur s'il veut rejouer
            System.out.println( " Nouvelle partie ? (yes / no)" );
            myScan.nextLine();
            if (!myScan.nextLine().equals( "yes" )) {
                // Peut-être un petit resume des parties jouées (info)
                System.out.println( " Au revoir " );
                return;
            }
        } while (true);
    }
}