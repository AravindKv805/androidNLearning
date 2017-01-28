package edu.asu.msse.pkondudu.numbershapes;

/**
 * Created by Aravind on 1/27/17.
 */

public class Number {

    int number;

    public boolean isTriangular() {
        int x = 1;
        int triangularNumber = 1;

        while (this.number > triangularNumber) {
            x++;
            triangularNumber += x;
        }
        if (triangularNumber == this.number) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isSquare() {

        Double squareRoot = Math.sqrt(number);
        if (squareRoot == Math.floor(squareRoot)) {
            return true;
        }
        return false;
    }
}
