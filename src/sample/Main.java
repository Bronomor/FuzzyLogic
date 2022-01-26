package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class Main extends Application {

    static final int WINDOW_WIDTH = 600;
    static final int WINDOW_HEIGHT = 600;
    private final int ROAD_LENGTH = 600;
    private int DISTANCE_TO_SPAWN_HOLE = 0;
    private int VELOCITY_SIMULATION = 10;
    private int ROAD_LEFT_EDGE = 150;
    private int ROAD_RIGHT_EDGE = 450;

    Stage window = new Stage();
    VBox vbox = new VBox();
    Scene scene = new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);
    private final Timeline timeline = new Timeline();

    private Car car = new Car(ROAD_LEFT_EDGE, ROAD_RIGHT_EDGE);
    private Hole hole = new Hole();
    private Fuzzy fuzzy = new Fuzzy();

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            window.setTitle("Menu");
            window.show();
            window.setScene(scene);
            game_logic();
            timeline.play();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void game_logic(){
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(20),
                (ActionEvent event) -> {
                    animation();
                    draw();
                }
        ));
    }

    public void animation() {

        if(DISTANCE_TO_SPAWN_HOLE <= 0){
            hole.spawn();
            DISTANCE_TO_SPAWN_HOLE = ROAD_LENGTH;
        }

        DISTANCE_TO_SPAWN_HOLE -= VELOCITY_SIMULATION;
        hole.reduceY(VELOCITY_SIMULATION);

        int defuzzyVal;
        //System.out.println("Dane: " + "OdlegloscXDoDziury " + (hole.getX() - car.getX()) + " OdlegloscYDoDziury" + (car.getY() - hole.getY()) + " OdlegloscOdLewegoBoku " + (car.getX() - ROAD_LEFT_EDGE));
        defuzzyVal = fuzzy.getDefuzzy(hole.getX() - car.getX(), car.getY() - hole.getY(), car.getX() - ROAD_LEFT_EDGE);

        car.moveCar(defuzzyVal);
    };

    public void draw(){

        vbox.getChildren().clear();

        Canvas canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.rgb(107, 107, 107));
        gc.fillRect(0,0, WINDOW_WIDTH,WINDOW_HEIGHT);

        // road
        gc.setFill(Color.rgb(0, 0, 0));
        gc.fillRect(WINDOW_WIDTH/2-150,0, 300,WINDOW_HEIGHT);

        // belts

        gc.setFill(Color.rgb(255, 255, 255));
        gc.fillRect(WINDOW_WIDTH/2-150,0, 10,WINDOW_HEIGHT);
        gc.fillRect(WINDOW_WIDTH/2+150,0, 10,WINDOW_HEIGHT);

        gc.fillRect(WINDOW_WIDTH/2, 10, 10,WINDOW_HEIGHT/6);
        gc.fillRect(WINDOW_WIDTH/2,WINDOW_HEIGHT/6 + 30, 10,WINDOW_HEIGHT/6);
        gc.fillRect(WINDOW_WIDTH/2,2*WINDOW_HEIGHT/6 + 50, 10,WINDOW_HEIGHT/6);
        gc.fillRect(WINDOW_WIDTH/2,3*WINDOW_HEIGHT/6 + 70, 10,WINDOW_HEIGHT/6);
        gc.fillRect(WINDOW_WIDTH/2,4*WINDOW_HEIGHT/6 + 90, 10,WINDOW_HEIGHT/6);

        vbox.getChildren().add(canvas);


        // car

        gc.setFill(Color.rgb(224, 49, 32));
        gc.fillRect(car.getX(), car.getY(), car.getSize(), car.getSize());

        // hole

        gc.setFill(Color.rgb(93, 93, 93));
        gc.fillOval(hole.getX(), hole.getY(), hole.getRadius(), hole.getRadius());
    }

}
