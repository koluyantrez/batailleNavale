package be.kolu;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Boat : Présnte un objet bateau et a comme attributs :
 *             Point pt1 : Pt qui présente la proue  du bateau
 *             Point pt2 : Pt qui présente la poupe  du bateau
 *             int id : qui présente l'id du bateau (index)
 */

public class Boat {
    int id = 0;
    List<Point> pts = new ArrayList<>();

    static Scanner myScan = new Scanner( System.in );

    /**
     * Constructeur
     */

    Boat(int id) {
        this.id = id;
    }

    /**
     * afficheBoat : permet d'afficher les infos d'une bateau
     */

    public void afficheBoat() {
        System.out.print( "Bateau " + String.valueOf( id ) + " : " );
        for (Point i : pts) {
            System.out.print( i.affichePoint() + " " );
        }
        System.out.println( "" );
    }

    public List<Point> getPts() {
        return pts;
    }

    public int getId() {
        return id;
    }

    public void setPt1(List<Point> pts) {
        this.pts = pts;
    }

    public void setIndex(int id) {
        this.id = id;
    }


    public void deletePt(int index, Point pt) {

        int i = Point.search( pts, pt );
        if (i != -1) {
            pts.remove( pts.get( i ) );
            System.out.println( "Test2" );
        }
    }
}