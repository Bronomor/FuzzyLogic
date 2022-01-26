package sample;

public class Hole {

    private int x = 10;
    private int y = 10;
    private int radius = 40;

    public Hole(){
    }

    public void spawn(){
        y = 0;
        x = (int) (Math.random() * 250) + 180;
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getRadius(){
        return radius;
    }

    public void reduceY(int vel){
        y += vel;
    }


}
