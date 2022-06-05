class DisplayIntersect {

    DisplayIntersect () {

    }


    // Platforms display and intersect function
    void displayPlatforms(Player player, ArrayList<Platform> platforms) {

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
    void displayBeacons(Player player, ArrayList<Beacon> beacons) {

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
    void displayDecorations(ArrayList<Decoration> decorations) {

        for (int i = 0; i < decorations.size(); i++) {

            decorations.get(i).display();
        }
    }




    // Obstacles display and intersect function
    void displayObstacles(Player player, ArrayList<Obstacle> obstacles) {
        

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
    void displayLetters(ArrayList<Letter> letters, Platform platform) {

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
    boolean platformIntersect(Player r1, Platform r2) {

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
    boolean beaconIntersect(Player r1, Beacon r2) {

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
    boolean obstacleIntersect(Player r1, Obstacle r2) {

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
    boolean letterIntersect(Letter r1, Platform r2) {

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