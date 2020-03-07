package eu.quelltext.coloring;

import org.junit.Test;

import eu.quelltext.images.ConnectedComponents;

import static junit.framework.TestCase.assertEquals;

public class ConnectedComponentTest {

    @Test
    public void testOneComponent() {
        assertComponentComputed(
                new int[]{
                        1, 1, 1,
                        1, 1, 1,
                        1, 1, 1,
                },
                new int[]{
                        0, 0, 0,
                        0, 0, 0,
                        0, 0, 0,
                },
                3, 3
        );
    }

    @Test
    public void testTwoComponents() {
        assertComponentComputed(
                new int[]{
                        1, 1, 1,
                        1, 1, 1,
                        5, 5, 5,
                },
                new int[]{
                        0, 0, 0,
                        0, 0, 0,
                        1, 1, 1,
                },
                3, 3
        );
    }
    @Test
    public void testThreeComponents() {
        assertComponentComputed(
                new int[]{
                        1, 1, 1,
                        1, 5, 5,
                        5, 5, 1,
                },
                new int[]{
                        0, 0, 0,
                        0, 1, 1,
                        1, 1, 2,
                },
                3, 3
        );
    }

    @Test
    public void testBig() {
        assertComponentComputed(
                new int[]{
                        1, 1, 1, 1, 2, 2,
                        1, 0, 0, 1, 0, 1,
                        0, 0, 1, 0, 0, 1,
                },
                new int[]{
                        0, 0, 0, 0, 1, 1,
                        0, 2, 2, 0, 3, 4,
                        2, 2, 5, 3, 3, 4,
                },
                6, 3
        );
    }

    private void assertComponentComputed(int[] classifiedArray, int[] expectedArray, int width, int height) {
        ConnectedComponents connectedComponents = new ConnectedComponents(classifiedArray, width, height);
        ConnectedComponents.Result result = connectedComponents.compute();
        int[] resultArray = result.computeArray();
        assert2DArrayEquals(expectedArray, resultArray, width, height);

    }

    public static void assert2DArrayEquals(int[] expectedArray, int[] array, int width, int height) {
        assertEquals("result should be of length " + expectedArray.length + " and not " + array.length,
                expectedArray.length, array.length);
        String message = "";
        int i = 0;
        for (int y = 0; y < height; y++) {
            String line1 = "";
            String line2 = "";
            for (int x = 0; x < width; x++) {
                line1 += expectedArray[i] + ",\t";
                line2 += array[i] + ",\t";
                i++;
            }
            message += "\n" + line1 + "==\t" + line2;
        }
        for (i = 0; i < expectedArray.length; i++) {
            assertEquals(message, expectedArray[i], array[i]);
        }
    }
}