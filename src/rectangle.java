public class rectangle extends hitbox {

    double height,width;
    rectangle(pos entety) {
        super(entety);
    }
    @Override
    boolean enthaelt(Punkt P) {
        double abstandx,abstandy;
        abstandx=Math.abs(this.getPos().x-P.x);
        abstandy=Math.abs(this.getPos().y-P.y);
        return (this.height)/2<abstandy&&(this.width)/2<abstandx;
    }

    @Override
    boolean ueberschneidet(hitbox H) {
        if(H instanceof rectangle){
            return ueberschneidet((rectangle)H);
        }
        return false;//todo

    }

    boolean ueberschneidet(rectangle R) {//TODO
        double abstandx,abstandy;
        abstandx=Math.abs(this.getPos().x-R.getPos().x);
        abstandy=Math.abs(this.getPos().y-R.getPos().y);
        return (this.height+R.height)/2<abstandy&&(this.width+R.width)/2<abstandx;

    }
}
