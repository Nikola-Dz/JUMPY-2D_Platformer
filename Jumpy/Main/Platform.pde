class Platform {

    PShape platform;    

    float x, y, w, h;


    // Platform constructor
    Platform (PShape startPlatform, float startX, float startY, float startWidth, float startHeight) {

        platform = startPlatform;
        
        x = startX;
        y = startY;
        w = startWidth;
        h = startHeight;
    }


    // Platform display function
    void display() {
            
        shape(platform, x, y, w, h);
    }
}