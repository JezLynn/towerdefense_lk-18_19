package towerdefense;

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
  // Ende Attribute
  
  // Anfang Methoden
 Player(int Lebenspunkte, int Startkapital){
  this.Lebenspunkte = Lebenspunkte;
  this.Geld = Startkapital;
 } 
 
 public void addGeld(int Betrag){
  this.Geld = this.Geld + Betrag;
 } 
 
 pulic int getGeld(){
  return this.Geld;
 } 
 
 public boolean removeGeld(int Betrag){
  if (this.geld >= Betrag){
   this.Geld = this.Geld-Betrag;
   return true;
  } else {
   return false;
  } 
 } 
 
 public boolean decreaseLebenspunkte(int Abzug){
  this.Lebenspunkte = this.Lebenspunkte - Abzug;
  if (this.Lebenspunkte > 0)
   return true;
  } else {
   return false;
  } 
 } 
  // Ende Methoden
} // end of Player
