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
    void display() {

        text("Level 02", width/2, 50);

        
        display_intersect.displayPlatforms(player, platforms);
        display_intersect.displayDecorations(decorations);
        display_intersect.displayBeacons(player, beacons);


        player.update();
        player.display();
    }
}
