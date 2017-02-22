package pd.treemap;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pd.treemap.MapItem;
import pd.treemap.Mappable;
import pd.treemap.Rect;
import pd.treemap.TreemapLayout;

public class TreemapLayoutTest {

	private static final double DELTA = 1e-10;
	private TreemapLayout layout = new TreemapLayout();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSortDescending() {
		Mappable[] items = new MapItem[11];
		double[] input = {24,2,45,20,56,75,2,56,99,53,12};
		double[] output = {99, 75, 56, 56, 53, 45, 24, 20, 12, 2, 2};
		for (int i = 0; i < items.length; i++) {
			items[i] = new MapItem(input[i],0);
		}
		Mappable[] sorted = layout.sortDescending(items);
		for (int i = 0; i < items.length; i++) {
			assertEquals(output[i], sorted[i].getSize(), DELTA);
		}
	}

	@Test
	public void testLayoutRowHorizontal() {
		Rect bounds = new Rect(0, 0, 6, 4);
		Mappable[] items = new MapItem[3];
		items[0] =  new MapItem(6, 0);
		items[1] =  new MapItem(6, 0);
		items[2] =  new MapItem(4, 0);

		Rect[] output1 = new Rect[1];
		output1[0] = new Rect(0, 0, 1.5, 4);

		Rect[] output2 = new Rect[2];
		output2[0] = new Rect(0, 0, 3, 2);
		output2[1] = new Rect(0, 2, 3, 2);

		Rect[] output3 = new Rect[3];
		output3[0] = new Rect(0, 0, 4, 1.5);
		output3[1] = new Rect(0, 1.5, 4, 1.5);
		output3[2] = new Rect(0, 3, 4, 1);

		Rect rect =layout.layoutRow(items, 0, 0, bounds);
		assertEquals(1.5, rect.x, DELTA);
		assertEquals(0,   rect.y, DELTA);
		assertEquals(4.5, rect.w, DELTA);
		assertEquals(4,   rect.h, DELTA);

		assertEquals(output1[0].x, items[0].getBounds().x, DELTA);
		assertEquals(output1[0].y, items[0].getBounds().y, DELTA);
		assertEquals(output1[0].w, items[0].getBounds().w, DELTA);
		assertEquals(output1[0].h, items[0].getBounds().h, DELTA);

		rect = layout.layoutRow(items, 0, 1, bounds);
		assertEquals(3, rect.x, DELTA);
		assertEquals(0, rect.y, DELTA);
		assertEquals(3, rect.w, DELTA);
		assertEquals(4, rect.h, DELTA);

		for (int i = 0; i < 2; i++) {
			assertEquals(output2[i].x, items[i].getBounds().x, DELTA);
			assertEquals(output2[i].y, items[i].getBounds().y, DELTA);
			assertEquals(output2[i].w, items[i].getBounds().w, DELTA);
			assertEquals(output2[i].h, items[i].getBounds().h, DELTA);
		}

		rect = layout.layoutRow(items, 0, 2, bounds);
		assertEquals(4, rect.x, DELTA);
		assertEquals(0, rect.y, DELTA);
		assertEquals(2, rect.w, DELTA);
		assertEquals(4, rect.h, DELTA);

		for (int i = 0; i < 2; i++) {
			assertEquals(output3[i].x,items[i].getBounds().x, DELTA);
			assertEquals(output3[i].y,items[i].getBounds().y, DELTA);
			assertEquals(output3[i].w,items[i].getBounds().w, DELTA);
			assertEquals(output3[i].h,items[i].getBounds().h, DELTA);
		}

	}

	@Test
	public void testLayoutRowVertical() {
		Rect bounds = new Rect(0, 0, 4, 6);
		Mappable[] items = new MapItem[3];
		items[0] =  new MapItem(6, 0);
		items[1] =  new MapItem(6, 0);
		items[2] =  new MapItem(4, 0);

		Rect[] output1 = new Rect[1];
		output1[0] = new Rect(0, 0, 4, 1.5);

		Rect[] output2 = new Rect[2];
		output2[0] = new Rect(0, 0, 2, 3);
		output2[1] = new Rect(2, 0, 2, 3);

		Rect[] output3 = new Rect[3];
		output3[0] = new Rect(0, 0, 1.5, 4);
		output3[1] = new Rect(1.5, 0, 1.5, 4);
		output3[2] = new Rect(3, 0, 1, 4);

		Rect rect =layout.layoutRow(items, 0, 0, bounds);
		assertEquals(0, rect.x, DELTA);
		assertEquals(1.5,   rect.y, DELTA);
		assertEquals(4, rect.w, DELTA);
		assertEquals(4.5,   rect.h, DELTA);

		assertEquals(output1[0].x, items[0].getBounds().x, DELTA);
		assertEquals(output1[0].y, items[0].getBounds().y, DELTA);
		assertEquals(output1[0].w, items[0].getBounds().w, DELTA);
		assertEquals(output1[0].h, items[0].getBounds().h, DELTA);

		rect = layout.layoutRow(items, 0, 1, bounds);
		assertEquals(0, rect.x, DELTA);
		assertEquals(3, rect.y, DELTA);
		assertEquals(4, rect.w, DELTA);
		assertEquals(3, rect.h, DELTA);

		for (int i = 0; i < 2; i++) {
			assertEquals(output2[i].x, items[i].getBounds().x, DELTA);
			assertEquals(output2[i].y, items[i].getBounds().y, DELTA);
			assertEquals(output2[i].w, items[i].getBounds().w, DELTA);
			assertEquals(output2[i].h, items[i].getBounds().h, DELTA);
		}

		rect = layout.layoutRow(items, 0, 2, bounds);
		assertEquals(0, rect.x, DELTA);
		assertEquals(4, rect.y, DELTA);
		assertEquals(4, rect.w, DELTA);
		assertEquals(2, rect.h, DELTA);

		for (int i = 0; i < 2; i++) {
			assertEquals(output3[i].x,items[i].getBounds().x, DELTA);
			assertEquals(output3[i].y,items[i].getBounds().y, DELTA);
			assertEquals(output3[i].w,items[i].getBounds().w, DELTA);
			assertEquals(output3[i].h,items[i].getBounds().h, DELTA);
		}

	}

	@Test
	public void testHighestAspect() {
		Rect bounds = new Rect(0, 0, 6, 4);
		Mappable[] items = new MapItem[3];
		items[0] =  new MapItem(6, 0);
		items[1] =  new MapItem(6, 0);
		items[2] =  new MapItem(4, 0);

		assertEquals(4/1.5, layout.highestAspect(items, 0, 0, bounds), DELTA);
		assertEquals(3/2.0, layout.highestAspect(items, 0, 1, bounds), DELTA);
		assertEquals(4/1.0, layout.highestAspect(items, 0, 2, bounds), DELTA);
	}

	@Test
	public void testLayout_24() {
		Rect bounds = new Rect(0, 0, 6, 4);
		Mappable[] items = new MapItem[7];
		items[0] =  new MapItem(6, 0);
		items[1] =  new MapItem(6, 0);
		items[2] =  new MapItem(4, 0);
		items[3] =  new MapItem(3, 0);
		items[4] =  new MapItem(2, 0);
		items[5] =  new MapItem(2, 0);
		items[6] =  new MapItem(1, 0);

		layout.layout(items, 0, 6, bounds);

		for (int i = 0; i < 7; i++) {
			System.out.println("Item " + i + "x=" + items[i].getBounds().x);
			System.out.println("Item " + i + "y=" + items[i].getBounds().y);
			System.out.println("Item " + i + "w=" + items[i].getBounds().w);
			System.out.println("Item " + i + "h=" + items[i].getBounds().h);
			System.out.println("-----");
		}

		assertTrue(true);

	}

}
