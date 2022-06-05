class Beacon {

    PShape beacon; 

    float x, y, w, h;
    

    // Beacon constructor
    Beacon (float startX, float startY) {

        beacon = loadShape("assets/beacon_65x65.svg");
        
        x = startX;
        y = startY;
        w = 50;
        h = 50;
    }


    // Beacon display function
    void display() {
            
        shape(beacon, x, y, w, h);
    }
}