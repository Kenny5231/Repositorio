/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacion.pkg1__;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Kenny
 */
/**
 * GridArea.java: Base class to LeftField and RightField
 *
 * @author       William Dubel
 * @version      1.0     June 29, 2001
 */

/**
 * Clase GridArea: Clase base para LeftField y RightField
 * Esta clase define un área de cuadrícula que se utilizará en un juego.
 * Incluye métodos y oyentes para manejar eventos de ratón.
 * 
 * @autor William Dubel
 * @versión 1.0 29 de junio de 2001
 */
class GridArea extends JPanel {
    // Inicializa y define las variables de la clase

    protected int area[][] = {
            {0, 11, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 10, 0, 42, 40, 40, 0, 0, 0, 0},
            {0, 10, 0, 31, 0, 0, 0, 0, 0, 0},
            {0, 10, 0, 30, 00, 0, 0, 0, 0, 0},
            {0, 10, 0, 30, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 30, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 52, 50, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 22, 20, 20, 20, 0, 0, 0, 0, 0}
    }; // Solo una matriz de prueba por ahora

    protected boolean vertical = false;
    private String title;
    private Point selected;
    protected Point cursorLocation;
    private Rectangle gridRects[][] = new Rectangle[10][10];
    protected PlayingField mainHandle;

    // Constructor
    public GridArea(String title, PlayingField mainHandle) {
        this.title = title;
        this.mainHandle = mainHandle;

        // Inicializa la matriz gridRects con rectángulos para las celdas de la cuadrícula
        for (int y = 0; y < 10; y++)
            for (int x = 0; x < 10; x++) gridRects[x][y] = new Rectangle(x * 25, y * 25, 25, 25);

        // Agrega oyentes de eventos de ratón
        addMouseMotionListener(new MouseMovingHandler());
        addMouseListener(new MouseHandler());

        setOpaque(false); // Hace que el panel sea transparente
    }

    // Obtiene el punto seleccionado y lo reinicia
    public Point getSelected() {
        Point temp = selected;
        selected = null;
        mainHandle.selectedShip = 0; // Asegúrate de obtener el barco antes de getSelected
        return temp;
    }

    // Obtiene las dimensiones preferidas del panel
    public Dimension getPreferredSize() {
        return new Dimension(251, 270);
    }

    // Establece el contenido de un área de la cuadrícula en una ubicación específica
    public void setArea(Point donde, int contenido) {
        area[(int)donde.getX()][(int)donde.getY()] = contenido;
    }

    // Obtiene el contenido de un área de la cuadrícula en una ubicación específica
    public int getArea(Point check) {
        return area[(int)check.getX()][(int)check.getY()];
    }

    // Verifica si la colocación del barco es válida
    protected boolean validPlacement() {
        if (vertical) {
            if ((int)cursorLocation.getY() + mainHandle.selectedShipSize > 10) return false;
            for (int i = 0; i < mainHandle.selectedShipSize; i++)
                if (false) return false; // TODO: Implementar la verificación de superposición de barcos
        } else {
            if ((int)cursorLocation.getX() + mainHandle.selectedShipSize > 10) return false;
            for (int i = 0; i < mainHandle.selectedShipSize; i++)
                if (false) return false; // TODO: Implementar la verificación de superposición de barcos
        }
        return true;
    }

    // Pinta el componente con líneas de cuadrícula y un título
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Crea un fondo degradado
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, new Color(40, 200, 140),
                250.0f, 250.0f, new Color(40, 180, 210));
        g2.setPaint(gp);
        g2.fillRect(0, 0, 250, 250);

        g2.setColor(new Color(0, 100, 90));
        for (int i = 1; i < 10; i++) {
            g2.drawLine(i * 25, 0, i * 25, 250);
            g2.drawLine(0, i * 25, 250, i * 25);
        }
        g2.setColor(Color.black);
        g2.draw3DRect(0, 0, 250, 250, false);

        g2.setColor(new Color(0, 60, 60));
        g2.drawString(title, 125 - (title.length() * 4), 268);
    }

    // Maneja eventos de movimiento del ratón
    private class MouseMovingHandler extends MouseMotionAdapter {
        private Rectangle lastSelected = new Rectangle();

        public void mouseMoved(MouseEvent e) {
            int x = (int)(e.getPoint().getX() / 25);
            int y = (int)(e.getPoint().getY() / 25);

            if (x < 10 && y < 10 && gridRects[x][y] != lastSelected) {
                lastSelected = gridRects[x][y];
                cursorLocation = new Point(x, y);
                repaint();
            }
        }
    }

    // Maneja eventos de clic del ratón
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.getModifiers() == e.BUTTON1_MASK) {
                selected = cursorLocation;
                mainHandle.addMessage("Usted seleccionó: " + selected); // TODO: Considera eliminar este mensaje de depuración
            }
            if (e.getModifiers() == e.BUTTON3_MASK) {
                vertical = !vertical; // Alterna el estado de colocación vertical del barco
                repaint();
            }
        }
    }
}


