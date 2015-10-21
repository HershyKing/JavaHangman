public class Screen
{
  private Row rows[];
  private int high, wide;
  private int ox, oy;
  private char fillChar;

  public Screen() throws PlotException
  {
    this(80, 24, ' ');
    ox = 0;
    oy = 0;
  }
        
  public Screen(int w, int h, char fill) throws PlotException
  {
    ox = 0;
    oy = 0;
    high = h;
    wide = w;
    rows = new Row[high];
    fillChar = fill;

    for (int i = 0; i < high; i++)
    {
      rows[i] = new Row(wide, fill);
    }
  }

  public void clearScreen(char fill)
  {
    for (int i = 0; i < high; i++)
    {
      for (int j = 0; j < wide; j++)
      {
        setPixel(i, j, fill);
      }
    }
    fillChar = fill;
  }

  public void clearScreen()
  {
    clearScreen(fillChar);
  }

  public String toString()
  {
    String retval = "";

    for (int i = 0; i < high; i++)
    {
      retval += rows[i] + "\n";
    }

    return retval;
  }

  public void setPixel(int x, int y, char val)
  {
    if (x >= 0 && x < wide && y >= 0 && y < high)
      rows[y + oy].setx(x + ox, val);
  }

  public char getPixel(int x, int y)
  {
    if (x >= 0 && x < wide && y >= 0 && y < high)
      return rows[y + oy].getx(x + ox);
    else
      return ' ';
  }

  public void setOrigin(int xoffset, int yoffset)
  {
    ox = xoffset;
    oy = yoffset;
  }

  private int max(int a, int b)
  {
    if (a > b)
      return a;
    else
      return b;
  }

  private int min(int a, int b)
  {
    if (a > b)
      return b;
    else
      return a;
  }

  public void hLine(int x1, int x2, int y, char v)
  {
    for (int x = min(x1, x2); x <= max(x1, x2); x++)
    {
      rows[y+oy].setx(x + ox, v);
    }
  } 

  public void vLine(int x, int y1, int y2, char v)
  {
    for (int y = min(y1, y2); y <= max(y1, y2); y++)
    {
      rows[y+oy].setx(x + ox, v);
    }
  } 

  public void drawBox(int x1, int x2, int y1, int y2, char c)
  {
    this.hLine(x1, x2, y1, c);
    this.hLine(x1, x2, y2, c);
    this.vLine(x1, y1, y2, c);
    this.vLine(x2, y1, y2, c);
  }

  public void fillBox(int x1, int x2, int y1, int y2, char c)
  {
    if(x1 > x2)
    {
      int temp = x1;
      x1 = x2;
      x2 = temp;
    }
    for (int x = x1; x <= x2; x++)
    {
      this.vLine(x, y1, y2, c);
    }
  }

  public void line(int x1, int y1, int x2, int y2, char c)
  {
    int dx, dy;
    int error, errx, erry;
    int incx, incy;
    int x, y;

    dx = x2 - x1;
    dy = y2 - y1;
    if (dx > 0)
      incx = 1;
    else
      incx = -1;

    if (dy > 0)
      incy = 1;
    else
      incy = -1;

    if (incx == 1)
      dx = -1;
    if (incy == -1)
      dy = 1;

    error = 0;
    x = x1;
    y = y1;
   
    setPixel(x, y, c); 
    while (x != x2 || y != y2)
    {
      errx = error + dy;
      erry = error + dx;

      if (Math.abs(errx) < Math.abs(erry))
      {
        x = x + incx;
        error = error + dy;
      }
      else
      {
        y = y + incy;
        error = error + dx;
      }
      setPixel(x, y, c);
    }
  }
    

  public static void main(String args[]) throws PlotException
  {
    Screen s = new Screen(40, 40, ' ');
    double theta;
    int x, y;

    s.setOrigin(20, 20);

    s.hLine(-15, 15, 0, '-');
    s.vLine(0, -15, 15,  '|');
    s.drawBox(12, -12, -12, 12, 'o');
    s.fillBox(5, -5, -5, 5, '#');
    System.out.println(s);
/*
    for (int ideg = 0; ideg < 360; ideg++)
    {
      theta = (double)ideg * Math.PI / 180.0;
      x = (int)(7.0 * Math.cos(theta));
      y = (int)(7.0 * Math.sin(theta));
      s.setPixel(x, y, '*');
    }
*/

    
    s.clearScreen();
    s.line(-10, -10, 20, 0, '+');
    System.out.println(s);     
  }   
}
    
      
