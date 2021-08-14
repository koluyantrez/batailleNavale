package be.kolu;
import java.util.*;

/**
 * La classe Champ hérite de la classe Grid + permet d'afficher une grille selon des normes
 * La classe Champ permet également de dessiner, supprimer des bateaux et autres
 * La classe Champ a comme attributs les champs de Grid + les variables statiques suivantes :
 *      List<Point> myList : Liste des points qui peuvent contenir un bateau ;
 *      List<Boat> boats : Liste des bateaux existants ;
 *      List<Point> tirs : Liste des tirs effectués (Points ciblés)
 *      variable statique nbre qui présente le nombre des bateaux dans le champ ?
 */

public class Champ extends Grid {
    static int nbre = 5; // nbre de bateaux
    static List<Point> myList = new ArrayList<>(); // Liste des pts appartenant à la mer et pouvant être pt bateau
    static HashMap<Integer, Boat> boats = new HashMap<>(); // Map des bateux existants
    static List<Point> listTir = new ArrayList<>(); // Liste des tirs effectués

    final int nbreMin = 1, nbreMax = 9; // Constantes présentants le nbre de bateaux minimal et amaximal

    /**
     * initialisation () : - permet de saisir la taille des grilles
     * - permet d'initalisaer les grilles (matrice) userChamp et tirs par des 0
     */

    public void initialization()
        
        System.out.println( "By Yassine the GOAT and Aina the goat" );
        System.out.println("ARE YOU READY FOR THE BATTLE SHIP !?!" );
        changeSize();

        for (int i = 0, k = 0; i < lon; i++) {
            for (int j = 0; j < lar; j++) // 0 partout
                userChamp[i][j] = 0;
        }
        System.out.println();
    }

    /**
     * nbre_boat() : Permet de saisie le nbre des bateaux à créer
     * Ce nombre est nbre = 5 par défaut ;
     * Sinon l'utilisateur doit choisir un nombre compris entre nbreMin et nbreMax
     */

    public void nbre_boat() {
        System.out.println( "Do you want to define the number of boats ?  yes/no " );
        String ans = myScan.nextLine();
        if (ans.equals( "yes" )) {
            do {
                System.out.println( "Le nombre de bateau doit être compris entre " + nbreMin + " et " + nbreMax );
                System.out.println( "Boats number : " );
                nbre = myScan.nextInt();
            } while (nbre < nbreMin || nbre > nbreMax);
            String ignore = myScan.nextLine();  // ignorer la saisie automatique
        } // else if (!ans.equals( "no" )) {
        //   throw new IllegalArgumentException( "C'est yes ou bien, c'est noooo?" );
        //}
    }

    /**
     * display () : permet d'afficher une grille, y'a 2 cas :
     * cas 1 : userChamp --> affiche "~" si mer (si == 0 ou == -1) et "#" si bateau ( sinon)
     * cas 2 : tirs --> affiche "~" si mer ( == 0) et "X" si tir ( == -1)
     *
     * @param trich : Boolean qui vaut "true" pour une partie de type TRICH, "false" sinon
     */

    public void display(boolean trich) { //affichage
        char coX = 'A';
        int coY = 1;

        System.out.print( "\n  " );
        for (int j = 0; j < lar; j++) { //affichage des coordonnées en Y (0 -> lar)
            System.out.print( coY + " " );
            if (coY < 10)
                System.out.print( " " );
            coY++;
        }

        System.out.println();
        for (int i = 0; i < lon; i++) { // design
            System.out.print( String.valueOf( coX ) + " " );
            coX++;
            for (int j = 0; j < lar; j++)
                if (userChamp[i][j] == 0) // mer
                    System.out.print( "~  " );
                else if (userChamp[i][j] == -1)
                    System.out.print( "O  " );
                else if (userChamp[i][j] == -2)
                    System.out.print( "X  " );
                else if (trich)
                    System.out.print( "#  " ); //Boat
                else // bateau pour une partie non TRICH
                    System.out.print( "~  " );
            System.out.println();
        }
        System.out.println();
    }

    /**
     * displayBoats () : permet d'afficher les bateaux existants
     */

    public void displayBoats() { //affichage
        for (Boat i : boats.values()) {
            i.afficheBoat();
        }
    }

    public void iniList() { // liste contenant les cases pouvant être un bateau
        String str;
        for (int i = 0; i < lon; i++)
            for (int j = 0; j < lar; j++)
                if (possiBat( i, j ))
                    myList.add( Point.element( i, j ) );  // ajout des cases pouvant être bateau
    }

    public void drawBoat(int taille) {
        int repeat = 1, m = 0, n = 0, x = 0, y = 0;
        index++;
        Boat B = new Boat( index );

        do {
            if (myList.isEmpty()) {
                System.out.println( "Impossible d ajouter des bateaux" );
                return;
            }

            int s = myList.size();
            int r = rdm.nextInt( s );  // choix random dans userChamp (la liste myList)
            m = myList.get( r ).getX();
            n = myList.get( r ).getY();
            myList.remove( Point.element( m, n ) );
        } while (!possiBat( m, n ));

        Point pt1 = new Point( m, n );

        List<Point> tempList = Point.init4( m, n );

        while (true) {
            if (myList.isEmpty() || tempList.isEmpty())
                return;

            int c = tempList.size();
            c = rdm.nextInt( c );  // choix random entre NESO
            x = tempList.get( c ).getX();
            y = tempList.get( c ).getY();
            tempList.remove( Point.element( x, y ) );

            if (possiBat( x, y )) { // dans myList
                myList.remove( Point.element( x, y ) );  // bateau => remove de myList
                break;
            }
        }

        Point pt2 = new Point( x, y );

        int K = x;
        int L = y;
        boolean b = false;

        while (true) {
            if (inUserCh( K, L ))
                if (K == m)
                    L = Point.getKL( n, L );
                else
                    K = Point.getKL( m, K );

            if (!possiBat( K, L )) {
                myList.remove( Point.element( K, L ) );
                if (!b) {
                    B.pts.add( Point.element( x, y ) );
                    userChamp[m][n] = index;
                    userChamp[x][y] = index;

                    m = pt2.getX();
                    n = pt2.getY();
                    x = pt1.getX();
                    y = pt1.getY();

                    userChamp[m][n] = 0;
                    userChamp[x][y] = 0;

                    b = true;
                    continue;
                }
            }

            userChamp[m][n] = index;
            userChamp[x][y] = index;
            B.pts.add( Point.element( x, y ) );

            repeat++;
            if (repeat == taille || b) { // REPEAT
                if (!b)
                    B.pts.add( pt1 );
                boats.put( index, B );
                break;
            }

            m = x;
            n = y;

            x = K;
            y = L;
        }

        if (repeat < taille && b) {
            cancelBoat( index );
            drawBoat( taille );
        }
    }

    public Point userTir() {
        int x = 0, y = 0;
        int jkl = 64 + lar;
        char c;
        char l = (char) jkl;
        String d;
        do {
            System.out.println( "TEST DE TIR" );
            System.out.println( "Choisir les coordonnées doivent ne pas dépasser (" + l + ", " + lon + ")" );
            d = myScan.nextLine();
            c = d.charAt( 0 );
            x = (int) c % 65;
            y = Integer.parseInt( d.substring( 1, d.length() ) ) - 1;

            if(!inUserCh( x, y ))
                continue;

            if (userChamp[x][y] >= 0) {// (listTir.contains( pt )); ne fonctionne pas
                listTir.add( Point.element( x, y ) );
                return new Point( x, y );
            } else {
                System.out.println( "Deja fait!" );
            }
        } while (true);
    }

    public Point ordiTir() {
        int x, y;
        Point pt;
        do {
            x = rdm.nextInt( lar );
            y = rdm.nextInt( lon );
            pt = new Point( x, y );

            if (userChamp[x][y] >= 0) {// (listTir.contains( pt )); ne fonctionne pas
                listTir.add( Point.element( x, y ) );
                return new Point( x, y );
            }
        } while (userChamp[x][y] <= 0); //(listTir.contains( pt ));

        listTir.add( pt );
        return pt;
    }

    public int calculDist(int x, int y) {
        int d = 2;
        int k = 1; // distance max
        boolean temoin = false;

        do {
            temoin = false;
            for (int i = x - k; i < x + k; i++)
                for (int j = y - k; j < y + k; j++)
                    if (inUserCh( i, j )) {
                        temoin = true;
                        if (userChamp[i][j] != 0 && userChamp[i][j] != -1) {
                            return k;
                        }
                    }
            k++;
        } while (temoin);
        return k;
    }

    public void tir(boolean test) {
        Point pt;

        if (test)
            pt = userTir();
        else
            pt = ordiTir();

        if (pt == null)
            return;

        int x = pt.getX();
        int y = pt.getY();

        int temp = userChamp[x][y];

        listTir.add( Point.element( x, y ) );
        userChamp[x][y] = -1;

        if (temp >= 1) {

            boats.get( temp ).deletePt( temp, pt ); // effacer le point de ptBoats

            if (boats.get( temp ).pts.isEmpty()) {
                boats.remove( temp );
                System.out.println( "touché /- coulé" );
            }
            else
                System.out.println( "TOUCHER" );
            userChamp[x][y] = -2;
        } else {
            System.out.println( "Raté, mais pas grave" );
            System.out.println( "Calcul du bateau le plus proche..." );
            if (calculDist( x, y ) == 1)
                System.out.println( "Le plus proche est distant de " + calculDist( x, y ) + " case !" );
            else
                System.out.println( "Le plus proche est distant de " + calculDist( x, y ) + " cases !" );
        }
    }

    public void cancelBoat(int index) {
        Boat temp = boats.get( index );
        boats.remove( index );

        for (Point i : temp.pts) {
            int k = i.getX();
            int l = i.getY();
            userChamp[k][l] = 0;
            myList.add( Point.element( k, l ) );
        }
    }

}
