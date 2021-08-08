
import java.util.Scanner;


public class Boat {
    static Scanner myScan = new Scanner( System.in );

    Point Pt1;
    Point Pt2;
    int index;

    static int nbre = 5;

    public Boat( Point Pt1, Point Pt2)
    {
        this.Pt1 = Pt1;
        this.Pt2 = Pt2;
    }

    public Point getPt1()
    {
        return Pt1;
    }
    public Point getPt2()
    {
        return Pt2;
    }
    public int getIndex() { return index; }
    public void setPt1(Point Pt1)
    {
        this.Pt1 = Pt1;
    }
    public void setPt2(Point Pt2)
    {
        this.Pt2 = Pt2;
    }
    public void setIndex(int index)
    {
        this.index = index;
    }

    public void nbre_boat() {
        System.out.println( "Do you want to define the number of boats ?  yes/no " );
        String ans1 = myScan.nextLine();  // ignorer le char spé
        String ans = myScan.nextLine();
        if (ans.equals( "yes" )) {
            System.out.println( "Boats number : " );
            nbre = myScan.nextInt();
        } else if (!ans.equals( "no" )) {
            throw new IllegalArgumentException( "C'est yes ou bien, c'est noooo?" );
        }
    }
}
