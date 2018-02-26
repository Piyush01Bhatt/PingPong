public class Player{
    int plX,plY,score;

    Player(int px, int py){
        plX = px;
        plY = py;
    }

    int getScore(){
        return score;
    }

    void setScore(int score){
        this.score = score;
    }

    int getPlX(){
        return plX;
    }

    int getPlY(){
        return plY;
    }

    void updatePl(int px,int py){
        plX = px;
        plY = py;
    }

}
