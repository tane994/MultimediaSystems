public class DDA
{
    static void ddaImplementation(int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps;

        if(Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);
        else
            steps = Math.abs(dy);

        float xInc = dx/steps;
        float yInc = dy/steps;

        for(int i = 0; i < steps; i++)
        {
            System.out.print("(" +x1 + "," + y1 + ")\n");
            x1 = x1 + Math.round(xInc);
            y1 = y1 + Math.round(yInc);
        }
    }
}
