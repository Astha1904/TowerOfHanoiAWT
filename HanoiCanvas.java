

import java.awt.*;
import java.util.ArrayList;

public class HanoiCanvas extends Canvas {

    ArrayList<Integer> rodA = new ArrayList<>();
    ArrayList<Integer> rodB = new ArrayList<>();
    ArrayList<Integer> rodC = new ArrayList<>();

    int diskHeight = 15;

    public HanoiCanvas() {
        setBackground(Color.WHITE);
    }

    // -------- INITIALIZE DISKS --------
    public void initializeDisks(int n) {
        rodA.clear();
        rodB.clear();
        rodC.clear();

        for (int i = n; i >= 1; i--) {
            rodA.add(i);
        }
        repaint();
    }

    // -------- MOVE DISK --------
    public void moveDisk(char from, char to) {

        ArrayList<Integer> src = getRod(from);
        ArrayList<Integer> dest = getRod(to);

        if (!src.isEmpty()) {
            int disk = src.remove(src.size() - 1);
            dest.add(disk);
        }

        repaint();

        try {
            Thread.sleep(500); // animation delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> getRod(char rod) {
        if (rod == 'A') return rodA;
        if (rod == 'B') return rodB;
        return rodC;
    }

    // -------- DRAW EVERYTHING --------
    public void paint(Graphics g) {

        // Rods
        g.setColor(Color.BLACK);
        g.fillRect(150, 100, 10, 250);
        g.fillRect(350, 100, 10, 250);
        g.fillRect(550, 100, 10, 250);

        // Base
        g.fillRect(100, 350, 500, 10);

        // Draw disks
        drawRod(g, rodA, 155);
        drawRod(g, rodB, 355);
        drawRod(g, rodC, 555);

        // Labels
        g.drawString("A", 150, 380);
        g.drawString("B", 350, 380);
        g.drawString("C", 550, 380);
    }

    // -------- DRAW ROD DISKS --------
    private void drawRod(Graphics g, ArrayList<Integer> rod, int xCenter) {

        int y = 345;

        for (int disk : rod) {

            int width = disk * 25;

            g.setColor(new Color((disk * 50) % 255, 120, 180));
            g.fillRect(xCenter - width / 2, y - diskHeight, width, diskHeight);

            y -= diskHeight;
        }
    }
}
