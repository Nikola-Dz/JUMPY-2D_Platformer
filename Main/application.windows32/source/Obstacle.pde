class Obstacle {

    PShape obstacle;    

    float x, y, w, h;


    // Obstacle constructor
    Obstacle (PShape startObstacle, float startX, float startY, float startWidth, float startHeight) {

        obstacle = startObstacle;
        
        x = startX;
        y = startY;
        w = startWidth;
        h = startHeight;
    }


    // Obstacle display function
    void display() {
            
        shape(obstacle, x, y, w, h);
    }
}
