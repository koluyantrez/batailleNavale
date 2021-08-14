package be.kolu;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe Grid : Présente une grille (classe mère) et a comme attributs :
 *             int[][] userChamp : matrice de taille (lon * lar) qui présnte la grille de jeu (la mer)
 *             int[][] tirs : matrice de même taille dans laquelle on enregistre les emplacements des tirs effectués
 *             int lon : Longueur de la grille (mer) (nbre de lignes)
 *             int lar : Largeur de la grille (mer) (nbre de colonnes)
 *        +
 *             variable statique index qui présente le nombre des bateaux créés dans le champ
 */

public class Grid {

    protected int[][] userChamp;
    protected int lon = 10; // nbre de ligne
    protected int lar = 10; // nbre de colonne

    static Scanner myScan = new Scanner( System.in );
    static Random rdm = new Random();
    static boolean endGame; // On utilise pas pour le moment
    static int index = 0; // nbre des bateaux créés dans le champ (indice du dernier bateau créé)

    /**
     * * Fct inUserCh () : permet de vérifier si un pt donné appartient ou pas à la grille
     *
     * @param x : Absicisse du pt mis en question
     * @param y : Ordonné du pt mis en question
     * @return : retourne "true" si le pt appartient à la grille, "false" sinon
     */

    public boolean inUserCh(int x, int y) {
        return (x >= 0 && x < lon && y >= 0 && y < lar);
    }

    /**
     * * Fct veriPos () : permet de vérifier si un pt donné appartient à la mer (pas bateau)
     *
     * @param x : Absicisse du pt mis en question
     * @param y : Ordonné du pt mis en question
     * @return : retourne "true" si le pt appartient à la mer, "false" sinon (hors grille ou appatient à un bateau)
     */

    public boolean veriPos(int x, int y) {
        if (!inUserCh( x, y ))
            return false;

        if (userChamp[x][y] == 0 || userChamp[x][y] == -1) // == -1 s'il s'agit d'un pt tir (mer également) (info)
            return true;

        return false;
    }

    /**
     * Fct pointBaot () : permet de vérifier si un pt donné appartient à un bateau
     *
     * @param i : Absicisse du pt mis en question
     * @param j : Ordonné du pt mis en question
     * @return : retourne "true" si le pt appartient à un bateau, "false" sinon
     */

    public boolean pointBaot(int i, int j) {
        if (inUserCh( i, j ))
            if (userChamp[i][j] != 0 && userChamp[i][j] != -1) // == -1 s'il s'agit d'un pt tir (mer également)
                return true;
        return false;
    }

    /**
     * Fct touchBoat() : vérifie si un pt donné appartient à un bateau ou à la frontière d'un bateau
     *                   -> le pt doit appartenir à la mer et ne touche pas un bateau
     *
     * @param x : Absicisse du pt mis en question
     * @param y : Ordonné du pt mis en question
     * @return : retourne "true" si le pt est hors grille ou appartient à un bateau ou à sa frontière, "false" sinon
     */

    public boolean touchBoat(int x, int y) {
        if (!veriPos( x, y ))
            return true;

        for (int i = x - 1; i < x + 2; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (pointBaot(i, j))  // verif NESO
                    return true;
        return false;
    }

    /**
     * Fct possiBat() : vérifie si un pt donné peut être associé à un bateau
     *                   -> le pt doit appartenir à la mer et n'appartenir pas un bateau, ni le toucher
     *
     * @param x : Absicisse du pt mis en question
     * @param y : Ordonné du pt mis en question
     * @return : retourne "true" si le pt peut être associé à un bateau, "false" sinon
     */

    public boolean possiBat(int x, int y) {
        if (touchBoat( x, y ))
            return false;

        for (int i = x - 1; i < x + 2; i++)
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y)
                    continue;
                if ((i == x || j == y) && !touchBoat( x, y ))  // verif NESO (info)
                    return true;
            }
        return false;
    }

    /**
     * changeSize () : - Permet de modifier la taille de la grille
     *                 - Permet également d'instancier les 2 matrices userChamp et tirs
     *
     *      La taille des 2 matrices est par défaut (lon, lar) = (13, 13) ;
     *      Sinon l'utilisateur doit choisir une taille plus grande que (5, 5)
     *          --> lon > 5 et lar > 5
     */

    public void changeSize() {
        // donne le choix des dimensions  (problème d'affichage quand x > 10)
        System.out.println();
        System.out.print( "Do you want to change the size of grid ?  yes/no : " );
        String ans;
        ans = myScan.nextLine();

        if (ans.equals( "yes" )) {
            do {
                System.out.print( "Longueur : " );
                lon = myScan.nextInt() + 1;
                System.out.print( "Largeur : " );
                lar = myScan.nextInt() + 1;
            } while (lon < 5 && lar < 5);
            String ignore = myScan.nextLine();  // ignorer la saisie automatique
        }// else if (!ans.equals( "no" ))
         //   throw new IllegalArgumentException( "C'est yes ou bien, c'est noooo?" );  // relancer changeSize() ?


        userChamp = new int[lon][lar];  // dimension par défaut (info)
    }
}