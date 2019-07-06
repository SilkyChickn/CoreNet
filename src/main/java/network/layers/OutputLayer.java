package network.layers;

import network.CoreNetException;
import network.WeightGenerator;
import network.connections.Connection;
import network.neurons.HiddenNeuron;
import network.neurons.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer {

    //List of all neurons of this output layer
    private List<HiddenNeuron> outputNeurons = new ArrayList<HiddenNeuron>();

    /**@return List of all neurons of this output layer
     */
    List<HiddenNeuron> getOutputNeurons() {
        return outputNeurons;
    }

    /**Adding output neuron to this output layer.
     *
     * @param neuron Output neuron to add
     */
    public void addOutputNeuron(HiddenNeuron neuron){
        this.outputNeurons.add(neuron);
    }

    /**Fully connecting this output layer with the hidden layer
     *
     * @param hiddenLayer Hidden layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnect(HiddenLayer hiddenLayer, WeightGenerator generator){

        //Iterate through neurons
        for(HiddenNeuron neuron: outputNeurons){
            for(HiddenNeuron otherNeuron: hiddenLayer.getHiddenNeurons()){

                //Create connection
                new Connection(otherNeuron, neuron, generator.next());
            }
        }
    }

    /**Fully connecting this output layer with the input layer
     *
     * @param inputLayer Input layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnect(InputLayer inputLayer, WeightGenerator generator){

        //Iterate through neurons
        for(HiddenNeuron neuron: outputNeurons){
            for(InputNeuron otherNeuron: inputLayer.getInputNeurons()){

                //Create connection
                new Connection(otherNeuron, neuron, generator.next());
            }
        }
    }

    /**Getting output neuron for specific id.
     * The id is in the range 0 to maxNeurons -1
     *
     * @param id Id of the output neuron
     * @return Output neuron with this id
     * @throws CoreNetException Index out of bounds
     */
    public HiddenNeuron getOutputNeuron(int id) throws CoreNetException{
        if(id < 0 || id > outputNeurons.size() -1)
            throw new CoreNetException("Output neuron with id " + id + " does not exist!");

        return outputNeurons.get(id);
    }

    /**@return Count of output neurons
     */
    public int getOutputCount(){
        return outputNeurons.size();
    }
}
