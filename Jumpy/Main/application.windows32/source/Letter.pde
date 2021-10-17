class Letter {

    PShape letter;

    float x, y, w, h;

    float speedX, speedY;
    float gravity = 0.6;
    boolean connected;


    // Letter constructor
    Letter (PShape startLetter, float startX, float startY, float startWidth, float startHeight) {

        letter = startLetter;
        x = startX;
        y = startY;
        w = startWidth;
        h = startHeight;
    
        speedY = 0;

        gravity = 0.6;
        connected = false;
    }


    // Letter land function
    void land(float destY) {
        
        speedY = -20;
        connected = true;
        y = destY - h;
    }

    
    // Letter update function
    void update() {

        if (connected == false) {
            
            // apply gravity when not on a platform
            speedY += gravity;
        }

        y += speedY;
    }


    // Letter display function
    void display() {

        shape(letter, x, y, w, h);
    }
}
