/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.12.2018
 * @author Konstantin Bachem
 */

public class Vektor {

  double x; // Speichert die x-Koordinate des Vektors
  double y; // Speichert die y-Koordinate des Vektors

  /**
   * Erstellt einen neuen Vektor mit leeren Werten
   */
  Vektor() {}

  /**
   * Erstellt einen neuen Vektor mit den gegebenen x- und y- Werten
   * @param x
   * @param y
   */
  Vektor(double x,double y) {
    this.x=x;
    this.y=y;
  }

  /**
   * Gibt einen neuen Vektor mit den x- und y- Werten dieses Vektors zurück
   * @return {@link Vektor}
   */
  public Vektor copy() {
    return new Vektor(x,y);
  }

  /**
   * Gibt die Distanz relativ vom Ursprung zurück
   * @return {@link double}
   */
  public double betrag() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Addiert den {@link Vektor} zu diesem hinzu
   * @param v {@link Vektor}
   */
  public void add(Vektor v) {
    x += v.x;
    y += v.y;
  }

  /**
   * Multiplziert den Vektor mit der {@link double} r
   * @param r {@link double}
   */
  public void multiply(double r) {
    x *= r;
    y *= r;
  }

  /**
   *
   * @param x {@link double}
   * @param y {@link double}
   * @param betrag {@link double}
   */
  public void setx(double x,boolean y,double betrag) {
    if(Math.abs(x)>=Math.abs(betrag)) {
      this.x=Math.abs(betrag)*(x<0?-1:1);
      this.y=0;
    } else {
      this.x = x;
      this.y = Math.sqrt(betrag * betrag - x * x) * (y ? 1 : -1); //c**2-a**2=b**2 pytagoras
    }
  }

  /**
   *
   * @param x {@link double}
   */
  public void setx(double x) {
    this.x=x;
  }

  public void sety(boolean x,double y,double betrag) {
    if(Math.abs(y)>=Math.abs(betrag)) {
      this.y=Math.abs(betrag)*(y<0?-1:1);
      this.x=0;
    } else {
      this.y = y;
      this.x = Math.sqrt(betrag * betrag - y * y) * (x ? 1 : -1);
    }
  }

  public void sety(double y) {
    this.y=y;
  }

  public void turn(double rad) {
    //    //  double l=betrag();
    //    //    double startwinkel=Math.tanh(y/x);
    //    double x2=x*Math.cos(rad)-y*Math.sin(rad);
    //    //    double y2=x*Math.sin(grad)+y*Math.cos(grad);
    //    y=x*Math.sin(rad)+y*Math.cos(rad);
    //    x=x2;
    //    //    y=y2; 
    double cos=Math.cos(rad);
    double sin=Math.sin(rad);
    double x2=x*cos-y*sin;
    y=x*sin+y*cos;
    x=x2; 
  }

  public static void main(String[] args) {} // end of main
} // end of class Vektor
                                                                                                                                                                                                                                               
