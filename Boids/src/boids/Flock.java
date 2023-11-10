/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boids;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Matt
 */
public class Flock {
    static List<Boid> boids;
 
    Flock() {
        boids = new ArrayList<>();
    }
 
    void run(Graphics2D g,  int w, int h) {
        for (Boid b : boids) {
            b.run(g, boids, w, h);
        }
    }
 
    static Flock spawn(double w, double h, int numBoids) {
        Flock flock = new Flock();
        Random rand = new Random();
        for (int i = 0; i < numBoids; i++)
            
            // Random spawn
            boids.add(new Boid(w + rand.nextInt(800) - 400, h + rand.nextInt(800)-400));
        return flock;
    }
}
