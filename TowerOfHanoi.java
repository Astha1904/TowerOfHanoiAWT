import java.awt.*;
import java.awt.event.*;

public class TowerOfHanoi extends Frame implements ActionListener {

    TextField diskField;
    Button startBtn, solveBtn;
    Label infoLabel, moveLabel;
    HanoiCanvas canvas;

    int moves = 0;
    int numDisks = 0;

    public TowerOfHanoi() {

        setTitle("Tower of Hanoi");
        setSize(750, 480);
        setLayout(new BorderLayout());

        // -------- TOP PANEL --------
        Panel topPanel = new Panel(new FlowLayout());

        topPanel.add(new Label("Enter number of disks:"));

        diskField = new TextField(5);
        startBtn = new Button("Start");
        solveBtn = new Button("Solve");

        moveLabel = new Label("Moves: 0");
        infoLabel = new Label(" ");

        topPanel.add(diskField);
        topPanel.add(startBtn);
        topPanel.add(solveBtn);
        topPanel.add(moveLabel);
        topPanel.add(infoLabel);

        add(topPanel, BorderLayout.NORTH);

        // -------- CANVAS --------
        canvas = new HanoiCanvas();
        add(canvas, BorderLayout.CENTER);

        startBtn.addActionListener(this);
        solveBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // -------- START BUTTON --------
        if (e.getSource() == startBtn) {
            try {
                numDisks = Integer.parseInt(diskField.getText());

                if (numDisks <= 0) {
                    infoLabel.setText("Enter a positive number");
                    return;
                }

                moves = 0;
                moveLabel.setText("Moves: 0");

                canvas.initializeDisks(numDisks);

                int totalMoves = (int) Math.pow(2, numDisks) - 1;
                infoLabel.setText("Total moves required: " + totalMoves);

            } catch (NumberFormatException ex) {
                infoLabel.setText("Enter a valid number");
            }
        }

        // -------- SOLVE BUTTON --------
        if (e.getSource() == solveBtn) {

            if (numDisks == 0) {
                infoLabel.setText("Please click Start first");
                return;
            }

            moves = 0;
            moveLabel.setText("Moves: 0");

            new Thread(() -> solveHanoi(numDisks, 'A', 'C', 'B')).start();
        }
    }

    // -------- RECURSIVE SOLUTION --------
    private void solveHanoi(int n, char from, char to, char aux) {
        if (n == 0) return;

        solveHanoi(n - 1, from, aux, to);

        canvas.moveDisk(from, to);
        moves++;
        moveLabel.setText("Moves: " + moves);

        solveHanoi(n - 1, aux, to, from);
    }

    public static void main(String[] args) {
        new TowerOfHanoi();
    }
}
