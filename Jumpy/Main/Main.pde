PShape background, form_start, form_end;

PFont font;

boolean left, right, up, down, space, start;

StartScreen start_screen;

Info info_start, info_end;

Level01 level01;
Level02 level02;
Level03 level03;


// Setup function
void setup() {
    
    // Window size
    size(1600, 900);
    smooth();
    
    // Background
    background = loadShape("assets/background_1600x900.svg");

    // Font
    font  = loadFont("Bahnschrift-40.vlw");

    // Info shapes
    form_start = loadShape("assets/info/form_start_600x600.svg");
    form_end = loadShape("assets/info/form_end_600x600.svg");
    
    // Keyboard values
    left = false;
    right = false;
    up = false;
    down = false;
    space = true;
    start = false;

    // Start screen
    start_screen = new StartScreen();

    // Infos
    info_start = new Info(form_start);
    info_end = new Info(form_end);

    // Levels
    level01 = new Level01();
    level02 = new Level02();
    level03 = new Level03();
}




// Draw function
void draw() {

    shape(background, 0, 0, 1600, 900);

    fill(255);
    textFont(font);
    textAlign(CENTER);
    
    
    if (space) {
        
        start_screen.display();
    }
    

    if (!space) {

        if (level01.beacons.size() > 0) {
            
            level01.display();
            
            info_start.display();
            info_start.info.setVisible(!start);
        }

        if (level01.beacons.size() == 0 && level02.beacons.size() > 0) {
            
            level02.display();
        }

        if (level02.beacons.size() == 0) {
            
            level03.display();
        }

        if (level03.beacons.size() == 0) {

            info_end.display();
        }
    }
}




// Keyboard function (Pressed)
void keyPressed() {
    
    switch (keyCode) {
        
        case 37 : //left
            left = true;
        break;

        case 38 : //up
            up = true;
            start = true;
        break;

        case 39 : //right
            right = true;
        break;

        case 40 : //down
            down = true;
        break;

        case 32 : //space
            space = true;
        break;
    }
}

// Keyboard function (Released)
void keyReleased() {

    switch (keyCode) {
        
        case 37 : //left
            left = false;
        break;

        case 38 : //up
            up = false;
            start = true;
        break;

        case 39 : //right
            right = false;
        break;

        case 40 : //down
            down = false;
        break;
        
        case 32 : //space
            space = false;
        break;
    }    
}
