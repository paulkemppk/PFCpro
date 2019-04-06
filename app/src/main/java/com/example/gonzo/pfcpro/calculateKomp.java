package com.example.gonzo.pfcpro;

/**
 * Created by Gonzo on 13.06.2016.
 */

public class calculateKomp {


    calculateKomp(){

    }



    public double calcVerd(double ind, double kap, double f){


        double ergebniss = 100*(2*Math.PI*f*(ind*0.001)/(1/(2*Math.PI*f*(kap*0.000001)*3)));

        ergebniss =  Math.round(ergebniss*100.)/100.;

        return ergebniss;


    }


    public double calcPowr(double leistung, double cosphi, double zcosphi){

        double angle = Math.acos(cosphi);
        double zangle = Math.acos(zcosphi);

        double tan = Math.tan(angle);
        double ztan = Math.tan(zangle);

        double ergebniss2 = leistung*(tan-ztan);

        ergebniss2 =  Math.round(ergebniss2*100.)/100.;

        return ergebniss2;
    }

    public double calcKap(double kapazität, double frequenz, double spannung, int sterndreieck, int verd){

        double ergebniss3 = 0;

        if(sterndreieck == 1) {

            switch(verd){

                case 1:
                    ergebniss3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * 3 * (kapazität / 1000000)))/0.95;
                    break;
                case 2:
                    ergebniss3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz * 3 * (kapazität / 1000000)))/0.93;
                    break;
                case 3:
                    ergebniss3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * 3 * (kapazität / 1000000)))/0.86;
                    break;
                default:
                    ergebniss3 = Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * 3 * (kapazität / 1000000));
                    break;
            }




        } else if(sterndreieck == 2){

            switch(verd){

                case 1:
                    ergebniss3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * (kapazität / 1000000)))/0.95;
                    break;
                case 2:
                    ergebniss3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz * (kapazität / 1000000)))/0.93;
                    break;
                case 3:
                    ergebniss3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * (kapazität / 1000000)))/0.86;
                    break;
                default:
                    ergebniss3 = Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * (kapazität / 1000000));
                    break;

            }

        }

        ergebniss3 = ergebniss3/1000;
        ergebniss3 =  (Math.round(ergebniss3*100.)/100.);
        return ergebniss3;


    }


    public double calcCK(int ctPrim, int ctSek, double kleinste){

        double ck = 0;

        ck = (kleinste*1.44)/(ctPrim/ctSek);

        return ck;


    }


}
