public class Ball{
    int blX,blY,xx,rr=1;

    int xPl1,yPl1,xPl2,yPl2,speed;

    Ball(int bx, int by){
        blX = bx;
        blY = by;
    }

    int ballMove(int xx,Player p1,Player p2){
        xPl1 = p1.getPlX();
        yPl1 = p1.getPlY();
        xPl2 = p2.getPlX();
        yPl2 = p2.getPlY();


        switch(xx){
            case 1:  blX+=speed;
                break;
            case 2:  blX+=speed;
                blY+=speed;
                break;
            case 3:  blX+=speed;
                blY-=speed;
                break;
            case 4:  blX-=speed;
                break;
            case 5:  blX-=speed;
                blY-=speed;
                break;
            case 6:  blX-=speed;
                blY+=speed;
                break;
        }
        // pl2 auto move code

        if(blX>=580){
            if(xx==1){
                p2.updatePl(xPl2,blY-30);
                xPl2 = p2.getPlX();
            }
            else{
                if((xx==2) || (xx==3)){
                    p2.updatePl(xPl2,(blY)-20);
                    yPl2 = p2.getPlY();
                }
            }
        }

        // ball moving conditions

        if(blX>=720 && (blY>=5 && blY<=380)){
            rr = 4+(int)(Math.random()*((6-4)+1));
        }
            else{ 
                if(xx==1 && blX>=720){
                  rr = 4+(int)(Math.random()*((6-4)+1));
                }
    
                else{ 
                    if(blY<=5 && (blX>=5 && blX<=720)){
                        int[] ar = {2,6};
                        rr = ar[((int) Math.floor((Math.random()*2)))];
                    }
    
                    else{
                        if(blY>=380 && (blX>=5 && blX<=720)){
                            int[] ar = {3,5};
                            rr = ar[((int) Math.floor((Math.random()*2)))];
                        }
    
                        else{    
                            if(((blX>=xPl1-10) && blX<=(xPl1+10)) && (((blY+10)>=(yPl1)) && ((blY+10)<=(yPl1+80))) ){
                                rr = ((int) Math.floor((Math.random()*3)+1));
                                int score = p1.getScore();
                                p1.setScore(score+5);
                            }
                            else{
                                if(((blX<xPl2+10) && blX>=(xPl2-10)) && (((blY+10)>=(yPl2)) && ((blY+10)<=(yPl2+80)))){
                                    rr = 4+(int)(Math.random()*((6-4)+1));
                                }
                            }
    
                        }
                    }
                }
            }

        
        return rr;

    }

    void setSpeed(int speed){
        if(speed<=22){
            this.speed = speed;
        }
        
    }

    int getBallX(){
        return blX;
    }

    int getBallY(){
        return blY;
    }

}
