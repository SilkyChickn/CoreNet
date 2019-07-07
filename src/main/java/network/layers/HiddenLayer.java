package network.layers;

import network.WeightGenerator;
import network.connections.Connection;
import network.neurons.HiddenNeuron;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer {

    //List of all neurons of this hidden layer
    private List<HiddenNeuron> hiddenNeurons = new ArrayList<>();

    /**Fully connecting this hidden layer with the other hidden layer.
     * This hidden layer will be before the other.
     *
     * @param other Hidden layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnectAfter(HiddenLayer other, WeightGenerator generator){

        //Iterate through neurons
        for(HiddenNeuron neuron: hiddenNeurons){
            for(HiddenNeuron otherNeuron: other.hiddenNeurons){

                //Create connection
                new Connection(neuron, otherNeuron, generator.next());
            }
        }
    }

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

    /**Adding hidden neuron to this hidden layer.
     *
     * @param neuron Hidden neuron to add
     */
    public void addHiddenNeuron(HiddenNeuron neuron){
        this.hiddenNeurons.add(neuron);
    }

    /**@return List of all neurons of this hidden layer
     */
    List<HiddenNeuron> getHiddenNeurons() {
        return hiddenNeurons;
    }
}
