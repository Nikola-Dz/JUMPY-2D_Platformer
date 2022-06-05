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
    void display() {

        text("Level 01", width/2, 50);


        display_intersect.displayPlatforms(player, platforms);
        display_intersect.displayDecorations(decorations);
        display_intersect.displayBeacons(player, beacons);


        player.update();
        player.display();
    }
}
