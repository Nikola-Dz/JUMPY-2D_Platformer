import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Main extends PApplet {

PShape background, form_start, form_end;

PFont font;

boolean left, right, up, down, space, start;

StartScreen start_screen;

Info info_start, info_end;

Level01 level01;
Level02 level02;
Level03 level03;


// Setup function
public void setup() {
    
    // Window size
    
    
    
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
public void draw() {

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
public void keyPressed() {
    
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
public void keyReleased() {

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
    public void display() {
            
        shape(beacon, x, y, w, h);
    }
}
class Decoration {

  PShape decoration; 

  float x, y, w, h;


  // Decoration constructor
  Decoration (PShape startDecoration, float startX, float startY, float startWidth, float startHeight) {

    decoration = startDecoration;
    
    x = startX;
    y = startY;
    w = startWidth;
    h = startHeight;
  }


  // Decoration display function
  public void display() {

    shape(decoration, x, y, w, h);
  }
}
class DisplayIntersect {

    DisplayIntersect () {

    }


    // Platforms display and intersect function
    public void displayPlatforms(Player player, ArrayList<Platform> platforms) {

        for (int i = 0; i < platforms.size(); i++) {

            platforms.get(i).display();
        }


        for (int i = 0; i < platforms.size(); i++) {

            if (platformIntersect(player, platforms.get(i))) {

                if (player.y < platforms.get(i).y) {
                
                    player.land(platforms.get(i).y);
            
                    break;
                }

                else if (player.y + player.h > (platforms.get(i).y + platforms.get(i).h)) {
                

                    player.y = platforms.get(i).y + platforms.get(i).h;
            
                    break;
                }

                else if ((player.x + player.w > platforms.get(i).x) && (player.x + player.w < platforms.get(i).x + platforms.get(i).w)) {
                

                    player.x = platforms.get(i).x - player.w;
            
                    break;
                }

                else if (player.x < platforms.get(i).x + platforms.get(i).w) {
                

                    player.x = platforms.get(i).x + platforms.get(i).w;
            
                    break;
                }
            }

            else {

                player.connected = false;
            }
        }
    }




    // Beacons display and intersect function
    public void displayBeacons(Player player, ArrayList<Beacon> beacons) {

        for (int i = 0; i < beacons.size(); i++) {

            beacons.get(i).display();
        }

        for (int i = 0; i < beacons.size(); i++) {

            if (beaconIntersect(player, beacons.get(i))) {
        
                beacons.remove(i);
            
                break;
            }
        }
    }




    // Decorations display function
    public void displayDecorations(ArrayList<Decoration> decorations) {

        for (int i = 0; i < decorations.size(); i++) {

            decorations.get(i).display();
        }
    }




    // Obstacles display and intersect function
    public void displayObstacles(Player player, ArrayList<Obstacle> obstacles) {
        

        for (int i = 0; i < obstacles.size(); i++) {

            obstacles.get(i).display();
        }

        for (int i = 0; i < obstacles.size(); i++) {

            if (obstacleIntersect(player, obstacles.get(i))) {
        
                player.x = 20;
                player.y = 650;
            
                break;
            }
        }
    }




    // Letters display and intersect function
    public void displayLetters(ArrayList<Letter> letters, Platform platform) {

        for (int i = 0; i < letters.size(); i++) {

            letters.get(i).update();
            
            letters.get(i).display();
        }


        for (int i = 0; i < letters.size(); i++) {
                
            if (letterIntersect(letters.get(i), platform)) {

                if (letters.get(i).y < platform.y) {
                
                    letters.get(i).land(platform.y);
            
                    break;
                }
            }

            else {

                letters.get(i).connected = false;
            }
        }
    }








    // Platform intersect function
    public boolean platformIntersect(Player r1, Platform r2) {

        // what is the distance apart x-axis
        float distanceX = (r1.x + r1.w/2) - (r2.x + r2.w/2);
        // what is the distance apart y-axis
        float distanceY = (r1.y + r1.h/2) - (r2.y + r2.h/2);

        // what is the combined half-widths
        float combinedHalfW = r1.w/2 + r2.w/2;
        // what is the combined half-heights
        float combinedHalfH = r1.h/2 + r2.h/2;

        // check for intersection on x-axis
        if (abs(distanceX) < combinedHalfW) {

            // check for intersection on y-axis
            if ((abs(distanceY) < combinedHalfH)) {

                // they are intersecting
                return true;
            }
        }

        return false;
    }




    // Beacon intersect function
    public boolean beaconIntersect(Player r1, Beacon r2) {

        // what is the distance apart x-axis
        float distanceX = (r1.x + r1.w/2) - (r2.x + r2.w/2);
        // what is the distance apart y-axis
        float distanceY = (r1.y + r1.h/2) - (r2.y + r2.h/2);

        // what is the combined half-widths
        float combinedHalfW = r1.w/2 + r2.w/2;
        // what is the combined half-heights
        float combinedHalfH = r1.h/2 + r2.h/2;

        // check for intersection on x-axis
        if (abs(distanceX) < combinedHalfW) {

            // check for intersection on y-axis
            if (abs(distanceY) < combinedHalfH) {

                // they are intersecting
                return true;
            }
        }

        return false;
    }




    // Obstacle intersect function
    public boolean obstacleIntersect(Player r1, Obstacle r2) {

        // what is the distance apart x-axis
        float distanceX = (r1.x + r1.w/2) - (r2.x + r2.w/2);
        // what is the distance apart y-axis
        float distanceY = (r1.y + r1.h/2) - (r2.y + r2.h/2);

        // what is the combined half-widths
        float combinedHalfW = r1.w/2 + r2.w/2;
        // what is the combined half-heights
        float combinedHalfH = r1.h/2 + r2.h/2;

        // check for intersection on x-axis
        if (abs(distanceX) < combinedHalfW) {

            // check for intersection on y-axis
            if (abs(distanceY) < combinedHalfH) {

                // they are intersecting
                return true;
            }
        }

        return false;
    }




    // Letter intersect function
    public boolean letterIntersect(Letter r1, Platform r2) {

        // what is the distance apart x-axis
        float distanceX = (r1.x + r1.w/2) - (r2.x + r2.w/2);
        // what is the distance apart y-axis
        float distanceY = (r1.y + r1.h/2) - (r2.y + r2.h/2);

        // what is the combined half-widths
        float combinedHalfW = r1.w/2 + r2.w/2;
        // what is the combined half-heights
        float combinedHalfH = r1.h/2 + r2.h/2;

        // check for intersection on x-axis
        if (abs(distanceX) < combinedHalfW) {

            // check for intersection on y-axis
            if ((abs(distanceY) < combinedHalfH)) {

                // they are intersecting
                return true;
            }
        }

        return false;
    }
}
class Info {

  PShape info; 

  float x, y, w, h;


  // Info constructor
  Info (PShape startInfo) {

    info  = startInfo;
    
    x = 500;
    y = 150;
    w = 600;
    h = 600;
  }


  // Info display function
  public void display() {

    shape(info, x, y, w, h);
  }
}
class Letter {

    PShape letter;

    float x, y, w, h;

    float speedX, speedY;
    float gravity = 0.6f;
    boolean connected;


    // Letter constructor
    Letter (PShape startLetter, float startX, float startY, float startWidth, float startHeight) {

        letter = startLetter;
        x = startX;
        y = startY;
        w = startWidth;
        h = startHeight;
    
        speedY = 0;

        gravity = 0.6f;
        connected = false;
    }


    // Letter land function
    public void land(float destY) {
        
        speedY = -20;
        connected = true;
        y = destY - h;
    }

    
    // Letter update function
    public void update() {

        if (connected == false) {
            
            // apply gravity when not on a platform
            speedY += gravity;
        }

        y += speedY;
    }


    // Letter display function
    public void display() {

        shape(letter, x, y, w, h);
    }
}
class Level01 {

    Player player;

    DisplayIntersect display_intersect;

    ArrayList<Platform> platforms = new ArrayList<Platform>();

    ArrayList<Beacon> beacons = new ArrayList<Beacon>();

    ArrayList<Decoration> decorations = new ArrayList<Decoration>();

    PShape  
        
        ground = loadShape("assets/platforms/ground_1600x120.svg"),
        block_90x70 = loadShape("assets/platforms/block_90x70.svg"),
        block_130x70 = loadShape("assets/platforms/block_130x70.svg"),
        block_180x70 = loadShape("assets/platforms/block_180x70.svg"),
        block_180x230  = loadShape("assets/platforms/block_180x230.svg"),
        block_270x70  = loadShape("assets/platforms/block_270x70.svg"),
        block_270x120  = loadShape("assets/platforms/block_270x120.svg");

    PShape  
        
        flower_40x65 = loadShape("assets/decorations/flower_40x65.svg"),
        flower_40x70 = loadShape("assets/decorations/flower_40x70.svg"),
        flowers_80x70 = loadShape("assets/decorations/flowers_80x70.svg"),
        grass_260x50 = loadShape("assets/decorations/grass_260x50.svg"),
        tree_90x120 = loadShape("assets/decorations/tree_90x120.svg"),
        tree_220x230 = loadShape("assets/decorations/tree_220x230.svg"),
        tree_170x360 = loadShape("assets/decorations/tree_170x360.svg");


    // Level01 constructor
    Level01 () {

        platforms.add(new Platform(ground, 0, 780, width, 120));
        platforms.add(new Platform(block_180x70, 1270, 730, 180, 70));
        platforms.add(new Platform(block_180x230, 1430, 570, 180, 230));
        platforms.add(new Platform(block_270x70, 1000, 460, 270, 70));
        platforms.add(new Platform(block_130x70, 800, 310, 130, 70));
        platforms.add(new Platform(block_90x70, 1470, 140, 90, 70));
        platforms.add(new Platform(block_90x70, 1100, 230, 90, 70));
        platforms.add(new Platform(block_90x70, 1290, 190, 90, 70));
        platforms.add(new Platform(block_270x70, 400, 380, 270, 70));
        platforms.add(new Platform(block_90x70, 160, 250, 90, 70));


        decorations.add(new Decoration(flower_40x70, 1550, 500, 40, 70));
        decorations.add(new Decoration(flowers_80x70, 430, 310, 80, 70));
        decorations.add(new Decoration(grass_260x50, 240, 730, 260, 50));
        decorations.add(new Decoration(tree_90x120, 245, 540, 180, 240));
        decorations.add(new Decoration(grass_260x50, 900, 730, 260, 50));
        

        beacons.add(new Beacon(1490, 500));
        beacons.add(new Beacon(835, 235));
        beacons.add(new Beacon(1490, 70));
        beacons.add(new Beacon(180, 180));
        beacons.add(new Beacon(510, 540));
        

        display_intersect = new DisplayIntersect();

        player = new Player();
    }


    // Level01 display function
    public void display() {

        text("Level 01", width/2, 50);


        display_intersect.displayPlatforms(player, platforms);
        display_intersect.displayDecorations(decorations);
        display_intersect.displayBeacons(player, beacons);


        player.update();
        player.display();
    }
}
class Level02 {

    Player player;

    DisplayIntersect display_intersect;

    ArrayList<Platform> platforms = new ArrayList<Platform>();

    ArrayList<Beacon> beacons = new ArrayList<Beacon>();

    ArrayList<Decoration> decorations = new ArrayList<Decoration>();

    PShape  
        
        ground = loadShape("assets/platforms/ground_1600x120.svg"),
        block_90x70 = loadShape("assets/platforms/block_90x70.svg"),
        block_130x70 = loadShape("assets/platforms/block_130x70.svg"),
        block_180x70 = loadShape("assets/platforms/block_180x70.svg"),
        block_180x230  = loadShape("assets/platforms/block_180x230.svg"),
        block_270x70  = loadShape("assets/platforms/block_270x70.svg"),
        block_270x120  = loadShape("assets/platforms/block_270x120.svg");

    PShape  
        
        flower_40x65 = loadShape("assets/decorations/flower_40x65.svg"),
        flower_40x70 = loadShape("assets/decorations/flower_40x70.svg"),
        flowers_80x70 = loadShape("assets/decorations/flowers_80x70.svg"),
        grass_260x50 = loadShape("assets/decorations/grass_260x50.svg"),
        tree_90x120 = loadShape("assets/decorations/tree_90x120.svg"),
        tree_220x230 = loadShape("assets/decorations/tree_220x230.svg"),
        tree_170x360 = loadShape("assets/decorations/tree_170x360.svg");


    // Level02 constructor
    Level02 () {

        platforms.add(new Platform(ground, 0, 780, width, 120));
        platforms.add(new Platform(block_90x70, 1190, 650, 90, 70));
        platforms.add(new Platform(block_90x70, 1470, 550, 90, 70));
        platforms.add(new Platform(block_90x70, 1190, 450, 90, 70));
        platforms.add(new Platform(block_90x70, 1470, 350, 90, 70));
        platforms.add(new Platform(block_90x70, 1190, 250, 90, 70));
        platforms.add(new Platform(block_180x70, 1100, 250, 180, 70));
        platforms.add(new Platform(block_180x70, 200, 730, 180, 70));
        platforms.add(new Platform(block_270x120, 365, 680, 270, 120));
        platforms.add(new Platform(block_180x70, 785, 730, 180, 70));
        platforms.add(new Platform(block_180x230, 620, 570, 180, 230));
        platforms.add(new Platform(block_130x70, 400, 430, 130, 70));
        platforms.add(new Platform(block_180x70, 10, 330, 270, 70));
        platforms.add(new Platform(block_90x70, 10, 200, 90, 70));
        platforms.add(new Platform(block_90x70, 420, 200, 90, 70));


        decorations.add(new Decoration(grass_260x50, 1245, 730, 260, 50));
        decorations.add(new Decoration(tree_170x360, 1290, 420, 170, 360));
        decorations.add(new Decoration(flower_40x65, 1120, 185, 40, 65));
        decorations.add(new Decoration(flowers_80x70, 190, 260, 80, 70));


        beacons.add(new Beacon(1490, 150));
        beacons.add(new Beacon(960, 70));
        beacons.add(new Beacon(30, 20));
        beacons.add(new Beacon(440, 130));
        beacons.add(new Beacon(670, 240));

        
        display_intersect = new DisplayIntersect();

        player = new Player();
    }


    // Level02 display function
    public void display() {

        text("Level 02", width/2, 50);

        
        display_intersect.displayPlatforms(player, platforms);
        display_intersect.displayDecorations(decorations);
        display_intersect.displayBeacons(player, beacons);


        player.update();
        player.display();
    }
}
class Level03 {

    Player player;

    DisplayIntersect display_intersect;

    ArrayList<Platform> platforms = new ArrayList<Platform>();

    ArrayList<Beacon> beacons = new ArrayList<Beacon>();

    ArrayList<Decoration> decorations = new ArrayList<Decoration>();

    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    PShape  
        
        ground = loadShape("assets/platforms/ground_1600x120.svg"),
        block_90x70 = loadShape("assets/platforms/block_90x70.svg"),
        block_130x70 = loadShape("assets/platforms/block_130x70.svg"),
        block_180x70 = loadShape("assets/platforms/block_180x70.svg"),
        block_180x230  = loadShape("assets/platforms/block_180x230.svg"),
        block_270x70  = loadShape("assets/platforms/block_270x70.svg"),
        block_270x120  = loadShape("assets/platforms/block_270x120.svg");

    PShape  
        
        flower_40x65 = loadShape("assets/decorations/flower_40x65.svg"),
        flower_40x70 = loadShape("assets/decorations/flower_40x70.svg"),
        flowers_80x70 = loadShape("assets/decorations/flowers_80x70.svg"),
        grass_260x50 = loadShape("assets/decorations/grass_260x50.svg"),
        tree_90x120 = loadShape("assets/decorations/tree_90x120.svg"),
        tree_220x230 = loadShape("assets/decorations/tree_220x230.svg"),
        tree_170x360 = loadShape("assets/decorations/tree_170x360.svg");

    PShape

        water_1000x180 = loadShape("assets/obstacles/water_1000x180.svg");


    // Level03 constructor
    Level03 () {
        
        platforms.add(new Platform(ground, 0, 780, width, 120));
        platforms.add(new Platform(block_130x70, 140, 730, 130, 70));
        platforms.add(new Platform(block_180x230, 258, 570, 180, 230));
        platforms.add(new Platform(block_180x230, 1430, 570, 180, 230));
        platforms.add(new Platform(block_90x70, 530, 450, 90, 70));
        platforms.add(new Platform(block_90x70, 755, 350, 90, 70));
        platforms.add(new Platform(block_90x70, 530, 200, 90, 70));
        platforms.add(new Platform(block_90x70, 1080, 290, 90, 70));
        platforms.add(new Platform(block_90x70, 1200, 530, 90, 70));
        platforms.add(new Platform(block_90x70, 1340, 200, 90, 70));
        platforms.add(new Platform(block_270x70, 10, 300, 270, 70));


        obstacles.add(new Obstacle(water_1000x180, 434, 617, 1000, 180));


        decorations.add(new Decoration(grass_260x50, 15, 250, 260, 50));
        decorations.add(new Decoration(flower_40x70, 166, 230, 40, 70));
        decorations.add(new Decoration(flowers_80x70, 320, 500, 80, 70));
        decorations.add(new Decoration(tree_220x230, 1375, 241, 220, 330));


        beacons.add(new Beacon(550, 130));
        beacons.add(new Beacon(915, 160));
        beacons.add(new Beacon(1220, 460));
        beacons.add(new Beacon(1360, 130));
        beacons.add(new Beacon(20, 70));

        
        display_intersect = new DisplayIntersect();

        player = new Player();
    }


    // Level03 display function
    public void display() {

        text("Level 03", width/2, 50);

        
        display_intersect.displayPlatforms(player, platforms);
        display_intersect.displayObstacles(player, obstacles);
        display_intersect.displayDecorations(decorations);
        display_intersect.displayBeacons(player, beacons);


        player.update();
        player.display();
    }
}
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
    public void display() {
            
        shape(obstacle, x, y, w, h);
    }
}
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
    public void display() {
            
        shape(platform, x, y, w, h);
    }
}
class Player {
    
    PShape player;

    float x, y, w, h;

    float speedX, speedY;
    float gravity = 0.6f;
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

        gravity = 0.6f;
        connected = false;
    }


    // Player land function
    public void land(float destY) {
        
        speedY = -10;
        connected = true;
        y = destY - h;
    }

    
    // Player update function
    public void update() {

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
    public void display() {

        shape(player, x, y, w, h);
    }
}
class StartScreen {

    Platform platform; 

    ArrayList<Letter> letters = new ArrayList<Letter>();

    ArrayList<Decoration> decorations = new ArrayList<Decoration>();
    
    DisplayIntersect display_intersect;

    PShape  
        
        ground = loadShape("assets/platforms/ground_1600x120.svg"),
        letter_j_113x165 = loadShape("assets/letters/letter_j_113x165.svg"),
        letter_m_167x165 = loadShape("assets/letters/letter_m_167x165.svg"),
        letter_p_126x165 = loadShape("assets/letters/letter_p_126x165.svg"),
        letter_u_135x165 = loadShape("assets/letters/letter_u_135x165.svg"),
        letter_y_135x165 = loadShape("assets/letters/letter_y_135x165.svg");
    

    // StartScreen constructor
    StartScreen () {

        platform = new Platform(ground, 0, 780, width, 122);


        letters.add(new Letter(letter_j_113x165, 87, 390, 226, 330));
        letters.add(new Letter(letter_u_135x165, 338, 310, 270, 330));
        letters.add(new Letter(letter_m_167x165, 633, 220, 334, 330));
        letters.add(new Letter(letter_p_126x165, 992, 150, 252, 330));
        letters.add(new Letter(letter_y_135x165, 1269, 50, 270, 330));

        
        display_intersect = new DisplayIntersect();
    }


    // StartScreen display function
    public void display() {

        platform.display();
            
        display_intersect.displayLetters(letters, platform);
        display_intersect.displayDecorations(decorations);

        if (letters.get(2).y + letters.get(2).h < 700) {
            
            textSize(35);
            text("Press SPACE to start", width/2, 750);
        }
    }
}
  public void settings() {  size(1600, 900);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
