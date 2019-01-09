/* 
 * For CSCI 112 - JavaFX Final Assignment
 * 
 * Author Name: Huajing Lin
 * Last Modify: 04/22/2016
 */

package random.circles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle {
    // a color
    private int rColor;
    private int gColor;
    private int bColor;
    private int xPosition;
    private int yPosition;
    private int radius;
    private int xStep;
    private int yStep;
    private static final int StepNum = 5;   //the steps of moving;
    Circle(int r,int g, int b, int x, int y, int rad){
        rColor = r;
        gColor = g;
        bColor = b;
        xPosition = x;
        yPosition = y;
        radius = rad;
        //System.out.printf("r:%d, g:%d, b:%d\n",r, g, b);
        int[] oneStep = {1, -1};
        xStep = (int) (Math.random() * StepNum) - 3;
        if (xStep == 0) {
            xStep = oneStep[(int) (Math.random() * 2)];
        }
        
        yStep = (int) (Math.random() * StepNum) - 3;
        if (yStep == 0) {
            yStep = oneStep[(int) (Math.random() * 2)];
        }
        
    }
    //The method of the ball moving
    public void move(GraphicsContext gc) {
        xPosition += xStep;
        yPosition += yStep;
        
        if (xPosition > Globals.canvasWidth - radius || xPosition < 0) { //Returns if the circle exceeds the screen x bounds,
            xStep *= -1;

        }
        if (yPosition > Globals.canvasHeight - radius || yPosition < 0) { //Returns if the circle exceeds the screen y bounds
            yStep *= -1;
        }
        gc.setFill(Color.rgb(rColor, gColor, bColor));
        gc.fillOval(xPosition, yPosition, radius, radius);
    }// end move()
}
