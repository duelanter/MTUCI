import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

class FractalExplorer4 {
    private int displaySize;
    private JImageDisplay4 display;

    private FractalGenerator4 fractal;
    private Rectangle2D.Double range;

    public FractalExplorer4(int size) {
        displaySize = size;
        fractal = new Mandelbrot4();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay4(displaySize, displaySize);
    }

    public void createAndShowGUI() {
        display.setLayout(new BorderLayout());
        JFrame myFrame = new JFrame("Fractal Explorer");
        myFrame.add(display, BorderLayout.CENTER);
        JButton resetButton = new JButton("Reset Display");
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);
        myFrame.add(resetButton, BorderLayout.SOUTH);
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }

    private void drawFractal() {
        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {
                double xCord = fractal.getCoord(range.x,
                        range.x + range.width, displaySize, x);
                double yCord = fractal.getCoord(range.y,
                        range.y + range.height, displaySize, y);
                int iteration = fractal.numIterations(xCord, yCord);
                if (iteration == -1) {
                    display.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    private class ResetHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            fractal.getInitialRange(range);
            drawFractal();
        }
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCord = fractal.getCoord(range.x,
                    range.x + range.width, displaySize, x);
            int y = e.getY();
            double yCord = fractal.getCoord(range.y,
                    range.y + range.height, displaySize, y);
            fractal.recenterAndZoomRange(range, xCord, yCord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer4 displayExplorer = new FractalExplorer4(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}