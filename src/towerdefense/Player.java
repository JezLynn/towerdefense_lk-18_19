package towerdefense;

import towerdefense.grafikelemente.*;
/**
  *
  * Beschreibung Klasse Player enthält Lebenspunkte und Geld
  *
  * @version 1.0 vom 12.12.2018
  * @author 
  */

public class Player {
  
  // Anfang Attribute
 int Lebenspunkte;
 int Geld;
 Grafikelemente G;//Grafikelement für stats
  // Ende Attribute
  
  // Anfang Methoden
 Player(int Lebenspunkte, int Startkapital){
  this.Lebenspunkte = Lebenspunkte;
  this.Geld = Startkapital;
 } 
 
 public void addGeld(int Betrag){
  this.Geld = this.Geld + Betrag;
  System.out.println(Geld);
 } 
 
 public int getGeld(){
  return this.Geld;
 }

 public int getLebenspunkte(){
  return this.Lebenspunkte;
 }

 public boolean removeGeld(int Betrag){
  if (this.Geld >= Betrag){
   this.Geld = this.Geld-Betrag;
   return true;
  } else {
   return false;
  } 
 } 
 
 public boolean decreaseLebenspunkte(int Abzug){
  this.Lebenspunkte = this.Lebenspunkte - Abzug;
  if (this.Lebenspunkte > 0) {
   return true;
  } else {
   return false;
  } 
 }
 /**
 * updatet die Stats und bereitet sie zum Anzeigen vor
  */
 public void updatestats(){
  String Content="HP: "+Integer.toString(Lebenspunkte)+"   Geld: "+Integer.toString(Geld);
  Punkt position= new Punkt(10,510);
  this.G=new text(position,Content);
 }
}
