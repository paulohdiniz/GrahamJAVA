public class Point implements Comparable{
    private double x;
    private double y;
    private double tgAngle;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point (Point point){
        this.x = point.getX();
        this.y = point.getY();
    }

    public double distance(Point point){
        return Math.sqrt( (x - point.getX())*(x - point.getX()) + (y - point.getY())*(y - point.getY())   );
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public double getTgAngle(){
        return tgAngle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTgAngle(Point p0){
        this.tgAngle = Math.atan2(this.y - p0.getY(), this.x - p0.getX());
        
    }

    @Override
    public int compareTo(Object p) {
        // TODO Auto-generated method stub
        double compareTg = ((Point)p).getTgAngle();
        return Double.compare(this.tgAngle, compareTg);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", angle=" + tgAngle +
                '}';
    }

}
