package pd.treemap.demo;

import java.util.stream.IntStream;

import pd.treemap.MapItem;
import pd.treemap.MapModel;
import pd.treemap.Mappable;


/**
 * Simple Map Model
 *
 */
public class DemoMapModel implements MapModel {

    Mappable[] items;

    /**
     * Creates a Map Model instance based on the relative size of the mappable items
     * and the frame size.
     *
     * @param itemRatio Items representing relative areas
     * @param width Width of the display area
     * @param height Height of the display area
     */
    public DemoMapModel(int[] itemRatio,int width, int height) {
        this.items = new MapItem[itemRatio.length];
        double totalArea = width * height;
        double sum = IntStream.of(itemRatio).sum();

        for (int i = 0; i < items.length; i++) {
            items[i] = new MapItem(totalArea / sum * itemRatio[i], 0);
        }
    }

    @Override
    public Mappable[] getItems() {
        return items;
    }


}
