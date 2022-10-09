 import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
 import org.jfree.chart.ChartPanel;
 import org.jfree.chart.JFreeChart;
 import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
 import org.jfree.data.xy.XYSeriesCollection;
 import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.PolarItemRenderer;
import org.jfree.chart.renderer.xy.CyclicXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
public class XYSeriesDemo extends ApplicationFrame {

/**
 * A demonstration application showing an XY series containing a null value.
 *
 * @param title  the frame title.
 */
public XYSeriesDemo(final String title) {
    super(title);
    final XYSeries serie1 = new XYSeries("Hull Convex", false);
    final XYSeries serie2 = new XYSeries("Points", false, false);
    for(int i = 0; i < Main.getHull().size(); i++){
        serie1.add(Main.hull.get(i).getX(), Main.getHull().get(i).getY());
    }
    for(int j = 0; j < Main.listPoints.size(); j++){
        serie2.add(Main.listPoints.get(j).getX(), Main.listPoints.get(j).getY());
    }
    ;
    final XYSeriesCollection data = new XYSeriesCollection();
    data.addSeries(serie1);
    data.addSeries(serie2);
    final JFreeChart chart = ChartFactory.createXYLineChart(
        "Hull Convex",
        "X", 
        "Y", 
        data,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
    );
    final XYPlot plot1 = chart.getXYPlot();
    plot1.setBackgroundPaint(ChartColor.lightGray);
    plot1.setDomainGridlinePaint(ChartColor.white);
    plot1.setRangeGridlinePaint(ChartColor.white);

    //show the points better
    XYLineAndShapeRenderer renderer =
    (XYLineAndShapeRenderer) plot1.getRenderer();
    
    renderer.setBaseShapesVisible(true);
    renderer.setSeriesLinesVisible(1, false);
    renderer.getSeriesShapesFilled(0);
    final ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(1200, 1200));
    setContentPane(chartPanel);
}
}
