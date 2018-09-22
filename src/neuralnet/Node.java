/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rewil
 */
public class Node {
    
    protected final List<Connection> outputs = new ArrayList<>();
    protected final List<Connection> inputs = new ArrayList<>();
    protected double inValue = 0;
    protected double outValue = 0;
    
    protected operation activationOperation;
    
    protected enum operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        BOOLEAN,
        TANH;
    }
    
    public Node() {
        activationOperation = operation.values()[new Random().nextInt(operation.values().length)];
    }
    
    public void addConnection(Connection c, boolean output) {
        if(output) {
            outputs.add(c);
        } else {
            inputs.add(c);
        }
    }
    
    public final void receive(double message) {
        switch(activationOperation) {
            case ADD: inValue += message;
                break;
            case SUBTRACT: inValue -= message;
                break;
            case MULTIPLY: inValue *= message;
                break;
            case DIVIDE: inValue /= message;
                break;
            default: inValue += message;
        }
    }
    
    public void send() {
        for(Connection c : inputs) {
            c.pull();
        }
//        System.out.println("SubPing");
        activationFunction();
        
        for(Connection c : outputs) {
            c.push(outValue);
        }
        resetInValue();
    }
    
    protected void resetInValue() {
        switch(activationOperation) {
            case ADD:
            case SUBTRACT:
            case BOOLEAN:
            case TANH: inValue = 0;
                break;
            case MULTIPLY:
            case DIVIDE: inValue = 1;
        }
    }
    
    protected void activationFunction() {
        switch(activationOperation) {
            case BOOLEAN: {
                    if(inValue >= 0) {
                        outValue += 1;
                    } else {
                        outValue += -1;
                    }
                } break;
            case TANH: outValue = Math.tanh(inValue);
                break;
            default: outValue = inValue / inputs.size();
        }
    }
    
    public void modifyOutputs() {
        changeActivationOperation();
        for(Connection c : outputs) {
            c.modifyWeight();
        }
    }
    
    protected void changeActivationOperation() {
        Random rand = new Random();
        if(rand.nextDouble() > 0.5) {
        activationOperation = operation.values()[rand.nextInt(operation.values().length)];
        }
    }
    
}
