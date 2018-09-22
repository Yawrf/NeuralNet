/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnet;

import static neuralnet.NodeLayer.layerType.*;

/**
 *
 * @author rewil
 */
public class Brain {
    
    NodeLayer[] layers;
    
    public Brain(int hiddenLayers, int nodesPerLayer) {
        
        layers = new NodeLayer[2 + hiddenLayers];
        layers[0] = new NodeLayer(input, nodesPerLayer);
        for(int i = 1; i < layers.length - 1; ++i) {
            layers[i] = new NodeLayer(hidden, nodesPerLayer);
        }
        layers[layers.length - 1] = new NodeLayer(output, nodesPerLayer);
        
        for(int i = 0; i < layers.length - 1; ++i) {
            for(Node n : layers[i].getNodes()) {
                for(Node m : layers[i + 1].getNodes()) {
                    Connection c = new Connection(n, m);
                    n.addConnection(c, true);
                    m.addConnection(c, false);
                }
            }
        }
    }
    
    public NodeLayer[] getLayers() {
        return layers;
    }
    
    public void modifyConnections() {
        for(int i = 0; i < layers.length; ++i) {
            layers[i].modifyConnections();
        }
    }
    
    public void think() {
        for(int i = 0; i < layers.length; ++i) {
            layers[i].cycleNodes();
        }
    }
    
}
