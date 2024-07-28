package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 87;
    private static final int HEIGHT = 87;
    private static final long SEED = 283343;
    private static final Random RANDOM = new Random(SEED);

    private static final Map<Integer, Integer> layer_map = Map.of(
            0, 1,
            1, 2,
            2, 3,
            3, 2,
            4, 3,
            5, 2,
            6, 3,
            7, 2,
            8, 1
    );

    private static TETile TileType(int tileNum) {
        switch (tileNum) {
            case 0: return Tileset.FLOOR;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.TREE;
            default: return Tileset.MOUNTAIN;
        }
    }
    public void addHexagon(int size, int x, int y, TETile[][] world) {
        TETile type = TileType(RANDOM.nextInt(5));
        for (int i = 0; i < size; i++) {
            printRow(size + 2 * i, x - size / 2 - i, y + size - i, type, world);
            printRow(size + 2 * i, x - size / 2 - i, y - size  + 1 + i, type, world);
        }
    }

    public void addTesselation(int size, TETile[][] world) {
        for (int i = 0; i < 9; i++){
            printLayer(size, i, size * (i + 1), world);
        }
    }

    private void printLayer(int size, int layer, int y, TETile[][] world) {
        int num = layer_map.get(layer);
        int witdh = world.length;
        for(int i = 0; i < num; i++) {
            addHexagon(size, calculateLayerPos(size, num, witdh) + (3 * size * i), y, world);
        }
    }

    private int calculateLayerPos(int size, int num, int width) {
        return width / 2 - 2 * size * (num - 1) + 2 * (num - 1);
    }

    private void printRow(int length, int x, int y, TETile type, TETile[][] world) {
        for (int i = 0; i < length; i++) {
            world[x + i][y] = type;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        HexWorld hexWorld = new HexWorld();
        hexWorld.addTesselation(5, world);
        ter.renderFrame(world);
    }
}
