/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boids;

import static java.lang.Math.acos;
import static java.lang.Math.atan2;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author Matt
 */
public class Vector2 {
    double x, y;
    
    static Vector2 zero = new Vector2(0,0);
 
    Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
 
    void add(Vector2 v) {
        x += v.x;
        y += v.y;
    }
 
    void sub(Vector2 v) {
        x -= v.x;
        y -= v.y;
    }
 
    void div(double val) {
        x /= val;
        y /= val;
    }
 
    void mult(double val) {
        x *= val;
        y *= val;
    }
 
    double mag() {
        return sqrt(x*x + y*y);
    }
 
    double dot(Vector2 v) {
        return x * v.x + y * v.y;
    }
 
    void normalize() {
        double mag = mag();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }
 
    void limit(double lim) {
        double mag = mag();
        if (mag != 0 && mag > lim) {
            x *= lim / mag;
            y *= lim / mag;
        }
    }
 
    double heading() {
        return atan2(y, x);
    }
 
    static Vector2 sub(Vector2 v, Vector2 v2) {
        return new Vector2(v.x - v2.x, v.y - v2.y);
    }
 
    static double dist(Vector2 v, Vector2 v2) {
        return sqrt(pow(v.x - v2.x, 2) + pow(v.y - v2.y, 2));
    }
 
    static double angleBetween(Vector2 v, Vector2 v2) {
        return acos(v.dot(v2) / (v.mag() * v2.mag()));
    }
}
