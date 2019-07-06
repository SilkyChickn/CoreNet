package network.layers;

import network.WeightGenerator;
import network.connections.Connection;
import network.neurons.HiddenNeuron;
import network.neurons.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer {

    //List of all neurons of this hidden layer
    private List<HiddenNeuron> hiddenNeurons = new ArrayList<HiddenNeuron>();

    /**Fully connecting this hidden layer with the input layer
     *
     * @param other Input layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnect(InputLayer other, WeightGenerator generator){

        //Iterate through neurons
        for(HiddenNeuron neuron: hiddenNeurons){
            for(InputNeuron otherNeuron: other.getInputNeurons()){

                //Create connection
                new Connection(otherNeuron, neuron, generator.next());
            }
        }
    }

    /**Fully connecting this hidden layer with the output layer
     *
     * @param other Output layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnect(OutputLayer other, WeightGenerator generator){

        //Iterate through neurons
        for(HiddenNeuron neuron: hiddenNeurons){
            for(HiddenNeuron otherNeuron: other.getOutputNeurons()){

                //Create connection
                new Connection(neuron, otherNeuron, generator.next());
            }
        }
    }

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

    /**Fully connecting this hidden layer with the other hidden layer.
     * This hidden layer will be after the other.
     *
     * @param other Hidden layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnectBefore(HiddenLayer other, WeightGenerator generator){

        //Iterate through neurons
        for(HiddenNeuron neuron: hiddenNeurons){
            for(HiddenNeuron otherNeuron: other.hiddenNeurons){

                //Create connection
                new Connection(otherNeuron, neuron, generator.next());
            }
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
