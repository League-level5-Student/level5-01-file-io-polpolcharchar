package _06_Pixel_Art_Save_State;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener{
    private JFrame window;
    private GridInputPanel gip;
    private GridPanel gp;
    ColorSelectionPanel csp;

    public void start() throws IOException {
        gip = new GridInputPanel(this);	
        
        window = new JFrame("Pixel Art");
        window.setLayout(new FlowLayout());
        window.setResizable(false);

        window.add(gip);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void submitGridData(int w, int h, int r, int c) throws IOException {
        gp = new GridPanel(w, h, r, c);
        csp = new ColorSelectionPanel();
        window.remove(gip);
        window.add(gp);
        window.add(csp);
        gp.repaint();
        gp.addMouseListener(this);
        window.pack();
    }

    public static void main(String[] args) throws IOException {
        new PixelArtMaker().start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gp.setColor(csp.getSelectedColor());
        System.out.println(csp.getSelectedColor());
        gp.clickPixel(e.getX(), e.getY());
        gp.repaint();
        if(csp.save) {
        	csp.save = false;
        	gp.save();
        }
        if(csp.reset) {
        	csp.reset = false;
        	gp.reset();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
