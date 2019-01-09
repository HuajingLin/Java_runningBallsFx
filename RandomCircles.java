/* 
 * For CSCI 112 - JavaFX Final Assignment
 * 
 * Author Name: Huajing Lin
 * Last Modify: 04/22/2016
 */
package random.circles;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class RandomCircles extends Application {

    private static final int count = 20;    //the number of circle 
    private Circle[] circles;
    private boolean bExit;

    public static void main(String[] args) {
        launch(args);
    } // end main()

    // create a stage and scene for JavaFX
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Random Circles");
        primaryStage.setResizable(false);
        Group root = new Group();

        // istantiate a 500 x 500 Canvas
        Canvas canvas = new Canvas(Globals.canvasWidth, Globals.canvasHeight);
        // instantiate a 2D graphics context object for the canvas     
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // add the Canvas to the Scene
        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //
        //Create circle array
        circles = new Circle[count];
        bExit = false;
        // call the method to draw on the Canvas
        drawShapes(gc);

    } // end start()

    @Override
    public void stop() {
        bExit = true;
    }

    private void drawShapes(GraphicsContext gc) {

        int r;  // red Color factor
        int g;  // green Color factor
        int b;  // blue Color factor

        int x;  // x coordinate  to locate circle
        int y;  // y coordinate  to locate circle

        int s;  // size of the circle (side of enclosing square)

        // draw 20 circles
        for (int i = 0; i < count; i++) {
            // randomly generate a color
            r = (int) (Math.random() * 256);
            g = (int) (Math.random() * 256);
            b = (int) (Math.random() * 256);

            //set line and fill color
            //gc.setStroke(Color.color(r, g, b));
            //gc.setFill(color);
            // randomly generate  and draw a circle
            x = (int) (Math.random() * Globals.canvasWidth);
            y = (int) (Math.random() * Globals.canvasHeight);
            s = (int) (Math.random() * 100);

            //
            circles[i] = new Circle(r, g, b, x, y, s);
            //gc.fillOval(x, y, s, s);

        } // end for  

        Task<Void> progressTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                MoveCircles(gc);
                return null;
            }
        };
        new Thread(progressTask).start();
    } // end drawShapes()

    private void MoveCircles(GraphicsContext gc) {
        while (!bExit) {
            for (int i = 0; i < count; i++) {
                circles[i].move(gc);
            }
            //Threed sleep 10 millisecond 
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //clear the canvas
            gc.clearRect(0, 0, Globals.canvasWidth, Globals.canvasHeight);
        }
    }
} // end class CosineRoseFX

