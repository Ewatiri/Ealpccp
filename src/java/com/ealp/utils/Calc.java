/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ealp.utils;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

/**
 *
 * @author eva
 */
public class Calc {

    public double getMedian(double [] values){
        Median median = new Median();
        double m = median.evaluate(values);
        return m;
    }
    
    public double getMean (double [] values){
        Mean mean = new Mean();
        double m = mean.evaluate(values);
        return m;
    }
}
