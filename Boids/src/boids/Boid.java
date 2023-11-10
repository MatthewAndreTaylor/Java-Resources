/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import static java.lang.Math.PI;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Matt
 */
public class Boid {
    static final Random rand = new Random();
    private static final int SIZE = 4;
    static final Path2D shape = new Path2D.Double();
    static Color [] colors = 
    {
        new Color(255, 0, 0),new Color(0, 0, 255),new Color(60, 179, 113),
        new Color(238, 130, 238),new Color(255, 165, 0),new Color(106, 90, 205),
        new Color(255,0,255),new Color(255, 255, 0)
    };
 
    static {
        shape.moveTo(0, -SIZE * 2);
        shape.lineTo(-SIZE, SIZE * 2);
        shape.lineTo(SIZE, SIZE * 2);
        shape.closePath();
    }
 
    final double maxForce, maxSpeed;
    Vector2 location;
 
    Vector2 velocity, acceleration;
    int color;
    private boolean included = true;
 
    Boid(double x, double y) {
        color = rand.nextInt(8);
        acceleration = new Vector2(0,0);
        velocity = new Vector2(rand.nextInt(3) + 1, rand.nextInt(3) - 1);
        location = new Vector2(x, y);
        maxSpeed = 3.0;
        maxForce = 0.05;
    }
 
    void update() {
        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        location.add(velocity);
        acceleration.mult(0);
    }
 
    void applyForce(Vector2 force) {
        acceleration.add(force);
    }
 
    Vector2 seek(Vector2 target) {
        Vector2 steer = Vector2.sub(target, location);
        steer.normalize();
        steer.mult(maxSpeed);
        steer.sub(velocity);
        steer.limit(maxForce);
        return steer;
    }
 
    void flock(Graphics2D g, List<Boid> boids) {
        view(g, boids);
        borders(boids);
        
        Vector2 rule1 = separation(boids);
        Vector2 rule2 = alignment(boids);
        Vector2 rule3 = cohesion(boids);
        Vector2 rule4 = middle();
 
        rule1.mult(2.6);
        rule2.mult(1.5);
        rule3.mult(1.2);
        rule4.mult(0.000005 * Boids.centerPull());
        
        applyForce(rule1);
        applyForce(rule2);
        applyForce(rule3);
        applyForce(rule4);
    }
 
    void view(Graphics2D g, List<Boid> boids) {
        double sightDistance = 110;
        double peripheryAngle = PI * 0.95;
 
        for (Boid b : boids) {
            b.included = false;
 
            if (b == this)
                continue;
 
            double d = Vector2.dist(location, b.location);
            if (d <= 0 || d > sightDistance)
                continue;
 
            Vector2 lineOfSight = Vector2.sub(b.location, location);
 
            double angle = Vector2.angleBetween(lineOfSight, velocity);
            if (angle < peripheryAngle)
                b.included = true;
        }
    }
    
 
    Vector2 separation(List<Boid> boids) {
        double desiredSeparation = 40;
 
        Vector2 steer = new Vector2(0, 0);
        int count = 0;
        for (Boid b : boids) {
            if (!b.included)
                continue;
 
            double d = Vector2.dist(location, b.location);
            if ((d > 0) && (d < desiredSeparation)) {
                Vector2 diff = Vector2.sub(location, b.location);
                diff.normalize();
                diff.div(d);        // weight by distance
                steer.add(diff);
                count++;
            }
        }
        if (count > 0) {
            steer.div(count);
        }
 
        if (steer.mag() > 0) {
            steer.normalize();
            steer.mult(maxSpeed);
            steer.sub(velocity);
            steer.limit(maxForce);
            return steer;
        }
        return Vector2.zero;
    }
 
    Vector2 alignment(List<Boid> boids) {
        double preferredDist = 50;
 
        Vector2 steer = new Vector2(0, 0);
        int count = 0;
 
        for (Boid b : boids) {
            if (!b.included)
                continue;
 
            double d = Vector2.dist(location, b.location);
            if ((d > 0) && (d < preferredDist)) {
                steer.add(b.velocity);
                count++;
            }
        }
 
        if (count > 0) {
            steer.div(count);
            steer.normalize();
            steer.mult(maxSpeed);
            steer.sub(velocity);
            steer.limit(maxForce);
        }
        return steer;
    }
 
    Vector2 cohesion(List<Boid> boids) {
        double preferredDist = 50;
 
        Vector2 target = new Vector2(0, 0);
        int count = 0;
 
        for (Boid b : boids) {
            if (!b.included)
                continue;
 
            double d = Vector2.dist(location, b.location);
            if ((d > 0) && (d < preferredDist)) {
                target.add(b.location);
                count++;
            }
        }
        if (count > 0) {
            target.div(count);
            return seek(target);
        }
        return target;
    }
    
    Vector2 middle()
    {        
        return new Vector2(Boids.w/2 - location.x, Boids.h/2 - location.y);
        //return new Vector2(MouseInfo.getPointerInfo().getLocation().x - location.x, MouseInfo.getPointerInfo().getLocation().y - location.y);
    }
    
    void borders(List<Boid> boids) {
        for(Boid b : boids) {
            if (b.location.x < -2) b.location.x = Boids.w+2;
            if (b.location.y < -2) b.location.y = Boids.h+2;
            if (b.location.x > Boids.w+2) b.location.x = -2;
            if (b.location.y > Boids.h-28) b.location.y = -2;
        }
  }
    
    void draw(Graphics2D g) {
        AffineTransform save = g.getTransform();
        g.translate(location.x, location.y);
        g.rotate(velocity.heading() + PI /2);
        g.setColor(colors[this.color]);
        g.fill(shape);
        g.setTransform(save);
    }
 
    void run(Graphics2D g, List<Boid> boids, int w, int h) {
        flock(g, boids);
        update();
        draw(g);
    }
}