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
public class NodeLayer{
    
    private final Node[] nodes;
    
    public enum layerType {
        input,
        hidden,
        output;
    }
    
    public NodeLayer(layerType lt, int count) {
        nodes = new Node[count];
        switch(lt) {
            case input: {
                for(int i = 0; i < count; ++i) {
                    nodes[i] = new InputNode();
                }
            } break;
            case hidden: {
                for(int i = 0; i < count; ++i) {
                    nodes[i] = new Node();
                }
            } break;
            case output: {
                for(int i = 0; i < count; ++i) {
                    nodes[i] = new OutputNode();
                }
            } break;
        }
    }
    
    public Node[] getNodes() {
        return nodes;
    }
    
    public void modifyConnections() {
        for(Node n : nodes) {
            n.modifyOutputs();
        }
    }
    
    public void cycleNodes() {
        for(int i = 0; i < nodes.length; ++i) {
            nodes[i].send();
        }
    }
    
}
