
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Champ {
    static Scanner myScan = new Scanner( System.in );
    static Random rdm = new Random();

    private int[][] userChamp;
    private int lon = 10;
    private int lar = 10;

    static boolean endGame;
    static int index = 0;

    public List<Point> myList = new ArrayList<>();

    public boolean inUserCh(int x, int y) {
        // dans userChamp ?
        return (x >= 0 && x < lon && y >= 0 && y < lar);
    }

    public boolean veriPos(int x, int y) {
        // Vérifier si (x,y) appartient à la matrice
        if (!inUserCh( x, y ))
            return false;

        if (userChamp[x][y] == 0)
            return true;

        return false;
    }

    public boolean pointBaot(int i, int j) {
        // dans userChamp ?
        if (inUserCh( i, j ))
            if (userChamp[i][j] != 0)
                return true;
        return false;
    }

    public boolean touchBoat(int x, int y) {
        // Vérifier si (x,y) appartient à la matrice
        if (!veriPos( x, y ))
            return true;

        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (pointBaot( i, j ))  // verif NESO
                    return true;
        return false;
    }

    public boolean possiBat(int x, int y) {
        if (touchBoat( x, y ))
            return false;

        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (i != x && j != y && !touchBoat( x, y ))  // verif NESO
                    return true;

        return false;
    }

    public int[][] changeSize() {
        // donne le choix des dimensions  (problème d'affichage quand x > 10)
        System.out.println( "Do you want to change the size of grid ?  yes/no " );
        String ans = myScan.nextLine();

        if (ans.equals( "yes" )) {
            System.out.println( "Longueur : " );
            lon = myScan.nextInt() + 1;
            System.out.println( "Largeur : " );
            lar = myScan.nextInt() + 1;

            if (lon < 5 && lar < 5)
                throw new IllegalArgumentException( "Het is klein om te spelen !" ); // langue

        } else if (!ans.equals( "no" ))
            throw new IllegalArgumentException( "C'est yes ou bien, c'est noooo?" );  // relancer changeSize() ?

        userChamp = new int[lon][lar];  // dimension par défaut

        return userChamp;
    }

    public int[][] initialization() {
        //initialisation du jeux
        endGame = false;   //fin du jeu si true

        System.out.println( "Welcome to the battle ship !" );

        changeSize();


        for (int i = 0; i < lon; i++) {
            for (int j = 0; j < lar; j++) // 0 partout
                userChamp[i][j] = 0;
        }
        System.out.println();

        return userChamp;
    }

    public void display() { //affichage

        char coX = 'A';
        int coY = 1;

        System.out.print( "  " );
        for (int j = 0; j < lar; j++) { //affichage des coordonnées en Y (0 -> lar)
            if (coY > 8) {
                System.out.print( String.valueOf( coY ) + " " );
                coY++;
            } else {
                System.out.print( String.valueOf( coY ) + "  " );
                coY++;
            }
        }

        System.out.println();
        for (int i = 0; i < lon; i++) { // design
            System.out.print( String.valueOf( coX ) + " " );
            coX++;
            for (int j = 0; j < lar; j++)
                if (userChamp[i][j] == 0)  // mer ou frontière d'un bateau
                    System.out.print( "~  " );
                else {
                    System.out.print( "#  " ); //Boat
                }
            System.out.println();
        }
    }

    public void iniList() { // liste contenant les cases pouvant être un bateau
        String str;
        for (int i = 0; i < lon; i++)
            for (int j = 0; j < lar; j++)
                if (possiBat( i, j ))
                    myList.add( Point.element( i, j ) );  // ajout des cases pouvant être bateau
    }


    public void drawBoat() {
        int repeat = 0, m = 0, n = 0;
        index++;

        do {
            if (myList.isEmpty())
                return;

            int s = myList.size();
            int r = rdm.nextInt( s );  // choix random dans userChamp (la liste myList)
            m = myList.get(r).getX();
            n = myList.get(r).getY();
            myList.remove(Point.element(m, n));
        } while(!possiBat(m, n));

        List<Point> tempList = Point.init4(m, n);

        int x = 0;
        int y = 0;

        while (true) {
            if(myList.isEmpty() || tempList.isEmpty())
                return;

            int c = tempList.size();
            c = rdm.nextInt(c);  // choix random entre NESO
            x = tempList.get(c).getX();
            y = tempList.get(c).getY();
            tempList.remove(Point.element(x, y));

            if (possiBat(x, y)) { // dans myList
                myList.remove(Point.element(x, y));  // bateau => remove de myList
                break;
            }
        }

        int K = x;
        int L = y;

        while (true) {
            if (inUserCh(K, L)) {
                if (K == m) {
                    L = Point.getKL(n, L);
                } else {
                    K = Point.getKL(m, K);
                }
            }
            repeat = rdm.nextInt(2);
            if (!possiBat(K, L)){
                myList.remove(Point.element(K, L));
                repeat = 0;
            }

            userChamp[m][n] = index;
            userChamp[x][y] = index;


            if (repeat == 0) // REPEAT
                return;

            m = x;
            n = y;

            x = K;
            y = L;
        }
    }

    public void deleteBoat(int index) {
        int i = 0;
    }
}


