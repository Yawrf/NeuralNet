/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.Random;

/**
 *
 * @author rewil
 */
public class Connection {
    
    private final Node toNode;
    private final Node fromNode;
    private double weight;
    private double value = 0;
    
    public Connection(Node from, Node to, double weight) {
        toNode = to;
        fromNode = from;
        this.weight = weight;
    }
    
    public Connection(Node from, Node to) {
        this(to, from, new Random().nextDouble());
    }
    
    public void push(double message) {
        value = message * weight;
    }
    
    public void pull() {
        toNode.receive(value);
        if(toNode instanceof OutputNode) {
            System.out.println(weight);
        }
    }
    
    public void modifyWeight() {
        weight += new Random().nextDouble()/4;
    }
    
}
