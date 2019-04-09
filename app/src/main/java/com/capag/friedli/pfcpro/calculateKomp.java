package com.capag.friedli.pfcpro;

import android.widget.Toast;

/**
 * Created by Gonzo on 13.06.2016.
 */

public class calculateKomp {


    calculateKomp(){

    }



    public double calcVerd(double ind, double kap, double f){


        double ergebnis = 100*(2*Math.PI*f*(ind*0.001)/(1/(2*Math.PI*f*(kap*0.000001)*3)));

        ergebnis =  Math.round(ergebnis*100.)/100.;

        return ergebnis;


    }


    public double calcPowr(double leistung, double cosphi, double zcosphi){

        double angle = Math.acos(cosphi);
        double zangle = Math.acos(zcosphi);

        double tan = Math.tan(angle);
        double ztan = Math.tan(zangle);

        double ergebnis2 = leistung*(tan-ztan);

        ergebnis2 =  Math.round(ergebnis2*100.)/100.;

        return ergebnis2;
    }

    public double calcKap(double kapazität, double frequenz, double spannung, int sterndreieck, int verd){

        double ergebnis3 = 0;

        if(sterndreieck == 1) {

            switch(verd){

                case 1:
                    ergebnis3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * 3 * (kapazität / 1000000)))/0.95;
                    break;
                case 2:
                    ergebnis3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz * 3 * (kapazität / 1000000)))/0.93;
                    break;
                case 3:
                    ergebnis3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * 3 * (kapazität / 1000000)))/0.86;
                    break;
                default:
                    ergebnis3 = Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * 3 * (kapazität / 1000000));
                    break;
            }


        } else if(sterndreieck == 2){

            switch(verd){

                case 1:
                    ergebnis3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * (kapazität / 1000000)))/0.95;
                    break;
                case 2:
                    ergebnis3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz * (kapazität / 1000000)))/0.93;
                    break;
                case 3:
                    ergebnis3 = (Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * (kapazität / 1000000)))/0.86;
                    break;
                default:
                    ergebnis3 = Math.pow(spannung,2) *( 2 * Math.PI * frequenz  * (kapazität / 1000000));
                    break;

            }

        }

        ergebnis3 = ergebnis3/1000;
        ergebnis3 =  (Math.round(ergebnis3*100.)/100.);
        return ergebnis3;


    }


    public double calcCK(int ctPrim, int ctSek, double kleinste){

        double ck = 0;

        ck = (kleinste*1.44)/(ctPrim/ctSek);

        return ck;


    }


}
