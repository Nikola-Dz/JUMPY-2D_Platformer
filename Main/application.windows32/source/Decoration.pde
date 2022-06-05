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
  void display() {

    shape(decoration, x, y, w, h);
  }
}
