package pd.treemap.demo;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import pd.treemap.Mappable;
import pd.treemap.Rect;
import pd.treemap.TreemapLayout;

/**
 * Simple application for testing the algorithm
 *
 */
public class TreemapTest extends Frame {

    TreemapLayout algorithm;
    DemoMapModel mapModel;

    public TreemapTest() {
        int w = 1200;
        int h = 800;

        Rect bounds = new Rect(0, 0, w, h);
        algorithm = new TreemapLayout();
        mapModel = new DemoMapModel(new int[] {6, 6, 4, 3, 2, 2, 1}, 1200, 800);
        algorithm.layout(mapModel, bounds);

        setBounds(100, 100, w, h);
        setVisible(true);

        addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
            });
    }

    public void paint(Graphics g) {
        Mappable[] items = mapModel.getItems();
        Rect rect;

        g.setColor(Color.black);

        for (int i=0; i<items.length; i++) {
            rect = items[i].getBounds();
            int a=(int)rect.x;
            int b=(int)rect.y;
            int c=(int)rect.w;
            int d=(int)rect.h;
            g.drawRect(a,b,c,d);
        }
    }

    static public void main(String[] args) {
        new TreemapTest();
    }
}
