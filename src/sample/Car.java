package sample;

public class Car {

    private int x = 290;
    private int y = 520;
    private int size = 30;

    private int ROAD_LEFT_EDGE = 150;
    private int ROAD_RIGHT_EDGE = 450;


    public Car(int ROAD_LEFT_EDGE, int ROAD_RIGHT_EDGE){
        this.ROAD_LEFT_EDGE = ROAD_LEFT_EDGE;
        this.ROAD_RIGHT_EDGE = ROAD_RIGHT_EDGE;

    }

    public int[] moveCar(int carnTurnDefuzzy){

        if(x < ROAD_LEFT_EDGE + size && carnTurnDefuzzy < 50) return new int[]{x, 10};
        if(x > ROAD_RIGHT_EDGE - size && carnTurnDefuzzy > 60) return new int[]{x, 10};

        if(carnTurnDefuzzy < 40)// moveSharpLeft
            x -= 10;
        else if(carnTurnDefuzzy < 50)// moveLeft
            x -= 5;
        else if(carnTurnDefuzzy < 60) ; // stay;
        else if(carnTurnDefuzzy < 80) // moveRight
            x += 5;
        else // moveSharpRight
            x += 10;

        return new int[]{x, 10};
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getSize(){
        return size;
    }


}
