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
    void display() {

        text("Level 03", width/2, 50);

        
        display_intersect.displayPlatforms(player, platforms);
        display_intersect.displayObstacles(player, obstacles);
        display_intersect.displayDecorations(decorations);
        display_intersect.displayBeacons(player, beacons);


        player.update();
        player.display();
    }
}