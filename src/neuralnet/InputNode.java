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
public class InputNode extends Node{
    
    protected String name;
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    
    @Override
    public void send() {
        for(Connection c : outputs) {
        c.push(inValue);
        }
        inValue = 0;
    }
    
}
