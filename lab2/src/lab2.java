public class lab2 {
    public static void main(String[] args) {
        Point3d[] myPoint = new Point3d[3];

        for (int i = 0; i <= 2; i++)
        {
            myPoint[i] = new Point3d();
            myPoint[i].setX(Double.valueOf(args[0+3*i]));
            myPoint[i].setY(Double.valueOf(args[1+3*i]));
            myPoint[i].setZ(Double.valueOf(args[2+3*i]));
        }
        for (int i = 0; i <= 2; i++)
        {
            System.out.println("Point " + i + ": (" + myPoint[i].getX() + "  " + myPoint[i].getY() + "  " + myPoint[i].getZ() + ")");
        }
        System.out.printf("Ploshad " + "%.2f", computeArea(myPoint[0], myPoint[1], myPoint[2]));

    }
    public static double computeArea (Point3d p1, Point3d p2, Point3d p3)
    {
        double a = p1.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p3.distanceTo(p1);
        double perimetr = ((a+b+c)/2);
        return (Math.sqrt(perimetr*(perimetr-a)*(perimetr-b)*(perimetr-c)));
    }
}