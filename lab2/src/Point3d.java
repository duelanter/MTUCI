public class Point3d {

    private double x;
    private double y;
    private double z;

    public Point3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceTo(Point3d point) {
        double xd = this.x - point.x;
        double yd = this.y - point.y;
        double zd = this.z - point.z;
        return (Math.sqrt(xd*xd + yd*yd + zd*zd));
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void setZ(double z) {this.z = z;}
}