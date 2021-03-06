// Java program for Bresenhams Line Generation
// Assumptions :
// 1) Line is drawn from left to right.
// 2) x1 < x2 and y1 < y2
// 3) Slope of the line is between 0 and 1.
// We draw a line from lower left to upper
// right.
public class Brasenham
{

    // Driver code
    public static void main (String[] args)
    {
        int x1 = 3, y1 = 2, x2 = 15, y2 = 5;
        bresenham(x1, y1, x2, y2);
    }

    // function for line generation
    static void bresenham(int x1, int y1, int x2,
                          int y2)
    {
        int m_new = 2 * (y2 - y1);
        int slope_error_new = m_new - (x2 - x1);

        for (int x = x1, y = y1; x <= x2; x++)
        {
            System.out.print("(" +x + "," + y + ")\n");

            // Add slope to increment angle formed
            slope_error_new += m_new;

            // Slope error reached limit, time to
            // increment y and update slope error.
            if (slope_error_new >= 0)
            {
                y++;
                slope_error_new -= 2 * (x2 - x1);
            }
        }
    }
}

