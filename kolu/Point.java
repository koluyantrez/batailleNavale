package be.kolu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe Point : présente un objet Point et a comme attributs :
 *             int x : abscisse
 *             int y : ordonnée
 *             Peut avoir aussi un char C pour la couleur
 */

public class Point
{
    private int x = 0, y = 0;

    /**
     * Constructeur (int x, int y)
     */

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur (Point pt)
     */

    Point(Point pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    /**
     * affichePoint : permet d'afficher les infos d'une pt
     *
     * @return : le String (texte) à afficher
     */

    public String affichePoint() {
        char c = (char)(x + 65);
        int k = y + 1;
        return( "(" + c + k + ")" );
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    /**
     * getKL() : méthode statique utiliséé dans la recherche du point qui suit la queue du bateau
     *
     * @param MN : Abscisse ou ordonné du queue
     * @param KL : Abscisse ou ordonné du point suivant à trouver
     * @return : retourne l'abscisse ou l'ordonné du pt qui suit la queue du bateau
     *
     * info
     */

    public static int getKL(int MN, int KL) {
        if (KL == MN - 1)
            KL--;
        else
            KL++;
        return KL;
    }

    /**
     * element() : méthode statique permetant de créer un point à partir du couple (x, y) donné
     *
     * @param x : Abscisse du pt à créer
     * @param y : Ordonne du pt à créer
     * @return : retourne le pt (x,y) créé
     */

    public static Point element(int x, int y) {
        Point pt = new Point (x,y);
        return pt;
    }

    /**
     * init4() :méthode statique permettant de récupérer les 4 pts qui entourent vérticalement et horizontalement un pt(m, n) donné
     *
     * @param m : Abscise du pt mis en question
     * @param n : Ordonné du pt mis en question
     * @return : retourne une liste tempList contenant les 4 pt qui entourent le pt(m, n) (mêmes ligne & colonne)
     */

    public static List<Point> init4(int m, int n) {

        List<Point> tempList = new ArrayList<>();
        tempList.add(element( m - 1, n ) );
        tempList.add(element( m + 1, n ) );
        tempList.add(element( m, n - 1 ) );
        tempList.add(element( m, n + 1 ) );

        return tempList;
    }

    public static int search ( List<Point> pts, Point pt) {
        int i = -1;

        for (i = 0; i < pts.size() ; i++){
            if (pts.get( i ).getX() == pt.getX() && pts.get( i ).getY() == pt.getY())
                return i;
        }
        return i;
    }

    public static boolean isIN(  List<Point> pts, Point pt) {

        int i = search( pts, pt );

        if(i == -1)
            return false;

        return true;
    }
}