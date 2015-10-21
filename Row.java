public class Row
{
  private char row[];
  private int width;

  public Row() throws PlotException
  {
    this(80, ' ');
  }

  public Row(int w) throws PlotException
  {
    this(w, ' ');
  }

  public Row(int w, char fill) throws PlotException
  {
    if (w < 0)
    {
      throw new PlotException("No negative length rows");
    }
    this.width = w;
    this.row = new char[width];
    for (int i = 0; i < width; i++)
    {
      row[i] = fill;
    }
  }

  public void setx(int x, char val)
  {
    if (x >= 0 && x < width)
    {
      row[x] = val;
    }
  }

  public char getx(int x)
  {
    if (x >= 0 && x < width)
      return row[x];
    else
      return ' ';
  }

  public String toString()
  {
    String retval = "";
 
    for (int i = 0; i < width; i++)
    {
      retval = retval + row[i];
    }
    return retval;
  }

  public static void main(String args[]) throws PlotException
  {
    Row r = null;
    int wide = -10;
    try
    {
      r = new Row(wide);
    }
    catch (PlotException pe)
    {
      r = new Row(-wide);
    }
    

    for (int i = -10; i < 100; i++)
    {
      if (i % 2 == 1)
        r.setx(i, '*');
    }

    System.out.println(r);
  }
}




  
