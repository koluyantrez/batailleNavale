
import java.util.ArrayList;
import java.util.List;

public class Point
{
    int x;
    int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public static int getKL(int MN, int KL) {
        if (KL == MN - 1)
            KL--;
        else
            KL++;
        return KL;
    }

    public static Point element(int x, int y) {
        Point pt = new Point (x,y);
        return pt;
    }

    public static List<Point> init4(int m, int n) {

        List<Point> tempList = new ArrayList<>();
        tempList.add(element( m - 1, n ) );
        tempList.add(element( m + 1, n ) );
        tempList.add(element( m, n - 1 ) );
        tempList.add(element( m, n + 1 ) );

        return tempList;
    }


}
