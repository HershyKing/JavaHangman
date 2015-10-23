public class Line
{
  char theLine[];
  int width;

  //The constructor
  //Input a width and char to fill the line
  public Line(int width, char fill) throws LineException
  {
    this.width = width;
    theLine = new char[this.width];
    if (this.width <= 0)
    {
      throw new LineException("A line must have a positive length");
    }
    for (int i = 0; i  < this.width; i++) 
    {
      theLine[i] = fill;
    }
  }

  //If char == void
  public Line(int width) throws LineException
  {
    this(width, ' ');
  }

  //Get current width
  public int getWidth()
  {
    return this.width;
  } 

  //set char
  public void setChar(int x, char c)
  {
    if (x >= 0 && x < this.width)
    {
      this.theLine[x] = c;
    }
    return;
  }

  //Input a position and get the current char 
  public char getChar(int x)
  {
    char retval = ' ';
    if (x >= 0 && x < this.width)
    {
      retval = this.theLine[x];;
    }
    return retval;
  }

  public String toString()
  {
    String retval = "";
    for (int i = 0; i < this.width; i++)
    {
      retval += this.theLine[i];
    }
    retval += "\n";
    return retval;
  }

  public static void main(String args[]) throws LineException
  {
    Line myLine = new Line(50, '*');

    System.out.println(myLine);

    for (int i = 0; i < 50; i+=2)
    {
      myLine.setChar(i, '0');
    }

    System.out.println(myLine);
  }
}

