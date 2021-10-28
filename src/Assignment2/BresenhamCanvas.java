package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BresenhamCanvas extends JComponent
{
    private static final long serialVersionUID = 1L;
    private int columns;
    private int rows;
    public static int gridDotRadius = 2;
    public static int dotRadius = 12;
    private LinkedList<Point> points = new LinkedList<Point>();

    public BresenhamCanvas(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;
    }

    // Repaints the canvas
    public void paint(Graphics g)
    {
        g.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
        for (Point point : points)
        {
            drawDot(g, point.x, point.y);
        }
        // 1. Clears background
        // 2. Draws grid
        // 3. Draws all stored points, calls drawDot(...)
    }

    // Adds a point to internal point list
    public void addPoint(int gridX, int gridY)
    {
        points.add(new Point(gridX, gridY));
    }

    // Draws the grid. Calls drawGridDot for each grid point
    private void drawGrid(Graphics g)
    {
        for (int row = 0; row < rows; row++)
        {
            for (int column = 0; column < columns; column++)
            {
                drawGridDot(g, column, row);
            }
        }
    }

    // Draws a single grid point
    // Encapsulates the conversion of grid coordinates (e.g. 2/2) into pixel coordinates (e.g. 125/125)
    private void drawGridDot(Graphics g, int gridX, int gridY)
    {
        int xPos = getWidth() / (columns + 1) * (gridX + 1) - gridDotRadius / 2;
        int yPos = getHeight() / (rows + 1) * (gridY + 1) - gridDotRadius / 2;
        g.fillOval(xPos - 1, yPos - 1, gridDotRadius, gridDotRadius);
    }

    // Draws a single point (heavy dot) onto the canvas
    // Encapsulates the conversion of grid coordinates (e.g. 2/2) into pixel coordinates (e.g. 125/125)
    private void drawDot(Graphics g, int gridX, int gridY)
    {
        int xPos = getWidth() / (columns + 1) * gridX - gridDotRadius / 2;
        int yPos = getHeight() / (rows + 1) * gridY - gridDotRadius / 2;
        g.setColor(Color.red);
        g.fillOval(xPos - dotRadius / 2, yPos - dotRadius / 2, dotRadius, dotRadius);
    }


    // Brasenham algorithm
    public void bresenhamLine(int x1, int y1, int x2, int y2)
    {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if(dx > dy)
            slopeSmallerThanOne(x1, y1, x2, y2);
        else
            slopeGreaterThanOne(x1, y1, x2, y2);
    }

    private void slopeSmallerThanOne(int x1, int y1, int x2, int y2)
    {
        int x = x1;
        int y = y1;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = (2 * dy) - dx;

        if (x1 < x2)
        {
            while (x <= x2)
            {
                addPoint(x, y);
                x++;
                if (d < 0)
                {
                    d = d + 2 * dy;
                } else
                {
                    d = d + 2 * dy - 2 * dx;
                    if (y2 < y1)
                        y--;

                    else
                        y++;
                }
            }
        }
        else if (x2 < x1)
        {
            while (x >= x2)
            {
                addPoint(x, y);
                System.out.println(x);
                x--;
                if (d < 0)
                {
                    d = d + 2 * dy;
                }
                else
                {
                    d = d + 2 * dy - 2 * dx;
                    if (y2 < y1)
                        y--;

                    else
                        y++;
                }
            }
        }
    }

    private void slopeGreaterThanOne(int x1, int y1, int x2, int y2)
    {
        int x = x1;
        int y = y1;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int d = (2 * dx) - dy;

        if (y1 < y2)
        {
            while (y <= y2)
            {
                addPoint(x, y);
                y++;
                if (d < 0)
                {
                    d = d + 2 * dx;
                }
                else
                {
                    d = d + 2 * dx - 2 * dy;
                    if (x2 < x1)
                        x--;
                    else
                        x++;
                }
            }
        }
        else if (y2 < y1)
        {
            while (y >= y2)
            {
                addPoint(x, y);
                System.out.println(x);
                y--;
                if (d < 0)
                {
                    d = d + 2 * dx;
                }
                else
                {
                    d = d + 2 * dx - 2 * dy;
                    if (x2 < x1)
                        x--;
                    else
                        x++;
                }
            }
        }
    }



    public void drawCircle(int radius, int centerX, int centerY, Graphics g)
    {
        int y = radius;
        int x = 0;

        int delta = 3 - 2 * radius;
        while (y >= x) {
            drawPixelAndReflect(centerX, centerY, x, y);

            if (delta < 0)
            {
                delta = delta + 4 * x + 6;
                ;
            }
            else
            {
                delta = delta + 4 * (x - y) + 10;
                ;
                y--;
            }
            x++;
        }
    }

    private void drawPixelAndReflect(int centerX, int centerY, int x, int y)
    {
        bresenhamLine(centerX + x, centerY + y, centerX + x, centerY + y);
        bresenhamLine(centerX + x, centerY - y, centerX + x, centerY - y);
        bresenhamLine(centerX - x, centerY + y, centerX - x, centerY + y);
        bresenhamLine(centerX - x, centerY - y, centerX - x, centerY - y);

        bresenhamLine(centerX - y, centerY + x, centerX - y, centerY + x);
        bresenhamLine(centerX - y, centerY - x, centerX - y, centerY - x);
        bresenhamLine(centerX + y, centerY + x, centerX + y, centerY + x);
        bresenhamLine(centerX + y, centerY - x, centerX + y, centerY - x);
    }
}