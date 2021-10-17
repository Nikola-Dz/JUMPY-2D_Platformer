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
  void display() {

    shape(info, x, y, w, h);
  }
}
