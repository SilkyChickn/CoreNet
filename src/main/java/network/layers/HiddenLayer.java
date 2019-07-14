package network.layers;

import network.WeightGenerator;
import network.connections.Connection;
import network.neurons.HiddenNeuron;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer {

    //List of all neurons of this hidden layer
    private List<HiddenNeuron> hiddenNeurons = new ArrayList<>();

    /**Execute forward pass for all hidden neurons in this layer
     */
    public void forwardPass(){
        for(HiddenNeuron neuron: hiddenNeurons){
            neuron.forwardPass();
        }
    }

    /**Backpropagates this hidden layer by the learn effect epsilon.
     *
     * DeltaWik = E * Di * ak
     *
     * @param learnEffect How much to adjust the weights (0..1)
     */
    public void backpropagate(float learnEffect) {
        for(HiddenNeuron neuron: hiddenNeurons){
            neuron.backpropagateHidden(learnEffect);
        }
    }

    /**Connecting neuron with all neurons of this layer, after this layer
     *
     * @param neuron Neuron to connect
     * @param weightGenerator Generator to generate neurons weight
     */
    public void connectNeuronAfter(HiddenNeuron neuron, WeightGenerator weightGenerator){
        for(HiddenNeuron hn: hiddenNeurons){
            new Connection(hn, neuron, weightGenerator.next());
        }
    }

    /**Connecting neuron with all neurons of this layer, before this layer
     *
     * @param neuron Neuron to connect
     * @param weightGenerator Generator to generate neurons weight
     */
    public void connectNeuronBefore(HiddenNeuron neuron, WeightGenerator weightGenerator){
        for(HiddenNeuron hn: hiddenNeurons){
            new Connection(neuron, hn, weightGenerator.next());
        }
    }

    /**Adding hidden neuron to this hidden layer.
     *
     * @param neuron Hidden neuron to add
     */
    public void addHiddenNeuron(HiddenNeuron neuron){
        this.hiddenNeurons.add(neuron);
    }
}
