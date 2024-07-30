package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        Random rand = new Random(seed);
        MemoryGame game = new MemoryGame(rand,40, 40);
        game.startGame();
    }

    public MemoryGame(Random rand, int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        this.rand = rand;
    }

    public String generateRandomString(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int randomIndex = rand.nextInt(CHARACTERS.length);
            char randomChar = CHARACTERS[randomIndex];
            res.append(randomChar);
        }
        return res.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);

        if (!gameOver) {
            Font smallFont = new Font("Monaco", Font.PLAIN, 20);
            StdDraw.setFont(smallFont);
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.text(width / 2, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
        }

        Font centerFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(centerFont);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(width / 2, height / 2, s);

        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(String.valueOf(letters.charAt(i)));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        StringBuilder res = new StringBuilder();
        drawFrame("");

        if (playerTurn) {
            while (res.length() < n) {
                if (StdDraw.hasNextKeyTyped()) {
                    res.append(StdDraw.nextKeyTyped());
                    drawFrame("Your input: " + res);
                }
            }
        }

        return res.toString();
    }

    public void startGame() {
        round = 1;
        gameOver = false;

        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round: " + round + ", Start!");

            String str = generateRandomString(round);
            StdDraw.pause(1000);
            flashSequence(str);

            playerTurn = true;
            String input = solicitNCharsInput(round);
            StdDraw.pause(500);

            if (!input.equals(str)) {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
            } else {
                StdDraw.pause(1000);
                round++;
            }
        }
    }

}
