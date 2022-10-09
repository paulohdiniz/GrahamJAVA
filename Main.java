
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.jfree.ui.RefineryUtilities;

public class Main {
    static List<Point> hull = new ArrayList<Point>();
    static List<Point> listPoints = new ArrayList<Point>();

        public static void main(String[] args) throws FileNotFoundException, IOException {
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez saisir un nombre :");
            int nombre = sc.nextInt();
            sc.close();
            for(int i = 0; i < nombre; i++){
                listPoints.add(new Point(ThreadLocalRandom.current().nextDouble(0, nombre), ThreadLocalRandom.current().nextDouble(0, nombre)));
            }

            long startP0 = System.nanoTime();
            Point p0 = FindP0(listPoints);
            long endP0  = System.nanoTime(); 
            long elapsedTimeP0 = endP0 - startP0;
            //System.out.println("Time for find Bottom Point : " + elapsedTimeP0);
            
            listPoints.remove(listPoints.indexOf(p0));
            //aqui a lista ta sem o p0, falta organizar
            listPoints.forEach( list -> list.setTgAngle(p0));

            long start = System.nanoTime();
            Collections.sort(listPoints); 
            long end = System.nanoTime();
            long elapsedTime = end - start;
            //System.out.println("Time for the sort : " + elapsedTime);

            //aqui a lista sai sem o pivo e ordenada pela ordem de angulo ja
            //falta limpar quando os angulos sao iguais e escolher a maior distancia
            hull.add(p0);
            hull.add(listPoints.get(0));
            hull.add(listPoints.get(1));
            for(int i = 2; i < listPoints.size(); i++){
                double crossProduct = cross_product(hull.get(hull.size() - 2), hull.get(hull.size() - 1), listPoints.get(i));
                if(crossProduct == 0){
                    hull.remove(hull.size() -1);
                    hull.add(listPoints.get(i));
                }else if(crossProduct > 0){
                    hull.add(listPoints.get(i));
                }else{
                    while(crossProduct <= 0 && hull.size() > 2 ){
                        hull.remove(hull.size() -1);
                        crossProduct = cross_product(hull.get(hull.size() - 2), hull.get(hull.size() - 1), listPoints.get(i));
                    }
                    hull.add(listPoints.get(i));
                }
            }

            //juste pour fermer le graph
            hull.add(p0);
            
            final XYSeriesDemo demo = new XYSeriesDemo("Graham Algorithme");
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
        }
        public static Point FindP0(List<Point> listPoints){
            Point pointZero = listPoints.get(0);
            for(int i = 1; i < listPoints.size(); i++){
                if( Double.compare(pointZero.getY(), listPoints.get(i).getY()) == 0){
                    if(Double.compare(pointZero.getX(), listPoints.get(i).getX()) > 0){
                        pointZero = listPoints.get(i);
                    }
                }else if(Double.compare(pointZero.getY(), listPoints.get(i).getY()) > 0){
                    pointZero = listPoints.get(i);
                }
            }
            return pointZero;
        }
        public static double cross_product(Point p1, Point p2, Point p3){
            return (p2.getX() - p1.getX())*(p3.getY() - p1.getY()) - (p3.getX() - p1.getX())*(p2.getY() - p1.getY());
        }
        public static List<Point> getHull(){
            return hull;
        }


}
