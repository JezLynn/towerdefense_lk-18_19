/**
 *
 * Beschreibung
 * eine koordinate auf dem Spielfeld
 *
 * @version 1.0 vom 14.12.2018
 * @author Konstantin Bachem
 */

public class Punkt {
  double x,y; // Speichert die Koordinaten des Punktes

  /**
   * Erstellt einen neuen Punkt mit den gegebenen x- und y- Werten
   * @param x
   * @param y
   */
  Punkt(double x, double y) {
    this.x=x;
    this.y=y;
  }

  /**
   * Erstellt einen neuen Punkt mit den x- und y- Werten des gegebenen Punktes p
   * @param p
   */
  Punkt(Punkt p) {
    this.x=p.x;
    this.y=p.y;
  }

  /**
   * Gibt einen neuen Punkt mit den x- und y- Werten dieses Punktes zurück
   * @return
   */
  public Punkt copy() {
    return new Punkt(this.x,this.y);
  }

  /**
   * Addiert den {@link Vektor} v zu den x- und y- Werten dieses Punktes
   * @param v
   */
  public void add(Vektor v) {
    this.x+=v.x;
    this.y+=v.y;
  }

  /**
   * Gibt die Distanz dieses Punktes relativ zu einem anderen Punkt zurück
   * @param p
   * @return double
   */
  public double distance(Punkt p) {
    return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
  }
   
  
  public static void main(String[] args) {} // end of main
} // end of class Punkt

