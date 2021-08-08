
public class Game {

    public static void fini() {
        Champ C = new Champ();
        int[][] grid;
        grid = C.initialization();
        int b = Boat.nbre; //nbre_boat()+1
        C.iniList();

        // choix du nombre de bateau


        while (C.index < b && !C.myList.isEmpty()) {
            C.drawBoat();
        }
        C.display( );
    }
}
