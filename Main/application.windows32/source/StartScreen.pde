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
    void display() {

        platform.display();
            
        display_intersect.displayLetters(letters, platform);
        display_intersect.displayDecorations(decorations);

        if (letters.get(2).y + letters.get(2).h < 700) {
            
            textSize(35);
            text("Press SPACE to start", width/2, 750);
        }
    }
}
