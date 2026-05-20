public class Meteo {
    int precip;
    int temp;
    int vent;
    
    public int getVent() {
        return vent;
    }

     public int getTemp() {
        return temp;
    }

    public int getPrecip() {
        return precip;
    }

    public void setMeteo(int precip1, int temp1, int vent1) {
        this.vent = vent;
        this.temp = temp;
        this.precip = precip;
    }
}
