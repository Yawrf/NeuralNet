/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

/**
 *
 * @author rewil
 */
public class OutputNode extends Node{
    
    protected String name = "null";
    private brainAdaptor parent;
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    public void setParent(brainAdaptor ba) {
        parent = ba;
    }
    
    @Override
    public void send() {
        for(Connection c : inputs) {
            c.pull();
        }
        activationFunction();
        sendValue();
    }
    
    public void sendValue() {
        parent.receive(name, outValue);
        if(!name.equals("null")) {
//            System.out.println(name + outValue);
        }
        resetInValue();
    }
    
}
