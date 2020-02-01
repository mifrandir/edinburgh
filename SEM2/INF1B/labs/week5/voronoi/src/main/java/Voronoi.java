import java.awt.Color;
import java.lang.Math;

public class Voronoi {
    public static double pointDist(Point2D point1, Point2D point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2)
                + Math.pow(point1.getY() - point2.getY(), 2));
    }

    public static int findClosestPoint(Point2D point, Point2D[] sites) {
        if (sites == null) {
            throw new IllegalArgumentException("Received null array.");
        }
        if (sites.length == 0) {
            throw new IllegalArgumentException("Received empty array.");
        }
        double minD = pointDist(point, sites[0]);
        int minI = 0;
        for (int i = 1; i < sites.length; i++) {
            double d = pointDist(point, sites[i]);
            if (d < minD) {
                minD = d;
                minI = i;
            }
        }
        return minI;
    }

    public static int[][] buildVoronoiMap(Point2D[] sites, int width, int height) {
        int[][] indexmap = new int[width][];
        for (int i = 0; i < indexmap.length; i++) {
            indexmap[i] = new int[height];
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                indexmap[x][y] = findClosestPoint(new Point2D(x, y), sites);
            }
        }
        return indexmap;
    }

    public static void plotVoronoiMap(Point2D[] sites, Color[] colors, int[][] indexMap) {
        int width = indexMap.length;
        int height = indexMap[0].length;

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.setPenRadius(0);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                StdDraw.setPenColor(colors[indexMap[x][y]]);
                StdDraw.point(x, y);
            }
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < sites.length; i++) {
            StdDraw.filledCircle(sites[i].getX(), sites[i].getY(), 3);
        }
    }

    public static void main(String[] args) {
        int width = 200;
        int height = 200;

        int nSites = 5;
        Point2D[] sites = new Point2D[nSites];
        sites[0] = new Point2D(50, 50);
        sites[1] = new Point2D(100, 50);
        sites[2] = new Point2D(50, 100);
        sites[3] = new Point2D(125, 50);
        sites[4] = new Point2D(100, 175);

        Color[] colors = new Color[nSites];
        colors[0] = Color.BLUE;
        colors[1] = Color.GREEN;
        colors[2] = Color.YELLOW;
        colors[3] = Color.ORANGE;
        colors[4] = Color.MAGENTA;

        int[][] indexmap = buildVoronoiMap(sites, width, height);
        plotVoronoiMap(sites, colors, indexmap);
    }
}