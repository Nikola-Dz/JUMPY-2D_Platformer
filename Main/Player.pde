class Player {
    
    PShape player;

    float x, y, w, h;

    float speedX, speedY;
    float gravity = 0.6;
    boolean connected;


    // Player constructor
    Player () {
        
        player = loadShape("assets/player/player_right_60x60.svg");

        x = 20;
        y = 650;
        w = 60;
        h = 60;

        speedX = 0;
        speedY = 0;

        gravity = 0.6;
        connected = false;
    }


    // Player land function
    void land(float destY) {
        
        speedY = -10;
        connected = true;
        y = destY - h;
    }

    
    // Player update function
    void update() {

        // Move left
        if (left) {
            
            speedX = -5;
            player = loadShape("assets/player/player_left_60x60.svg");
        }

        // Move right
        if (right) {
            
            speedX = 5;
            player = loadShape("assets/player/player_right_60x60.svg");
        }

        
        // No movement on X-axis
        if (!left && !right) {
            
            speedX = 0;
        }

        // Movement on both sides
        else if (left && right) {
            
            speedX = 0;
        }


        // Jump
        if (up && connected == true) {
            
            speedY = -15;
            connected = false;
        }

        // In air
        if (connected == false) {
            
            // apply gravity when not on a platform
            speedY += gravity;
        }


        // Movement speed
        x += speedX;
        y += speedY;


        // Screen - Left border
        if (x < 0) {
        
            x = 0;
        }

        // Screen - Right border
        else if ((x + w)  > width) {
        
            x = width - w;
        }

        // Screen - Top border 
        else if (y < 0) {
            
            y = 0;
        }
    }


    // Player display function
    void display() {

        shape(player, x, y, w, h);
    }
}