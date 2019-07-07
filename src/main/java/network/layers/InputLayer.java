package network.layers;

import jdk.internal.util.xml.impl.Input;
import network.CoreNetException;
import network.WeightGenerator;
import network.connections.Connection;
import network.neurons.HiddenNeuron;
import network.neurons.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class InputLayer {

    //List of all neurons of this input layer
    private List<InputNeuron> inputNeurons = new ArrayList<>();

    /**Adding input neuron to this input layer.
     *
     * @param neuron Input neuron to add
     */
    public void addInputNeuron(InputNeuron neuron){
        this.inputNeurons.add(neuron);
    }

    /**Fully connecting this input layer with the hidden layer
     *
     * @param hiddenLayer Hidden layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnect(HiddenLayer hiddenLayer, WeightGenerator generator){

        //Iterate through neurons
        for(InputNeuron neuron: inputNeurons){
            for(HiddenNeuron otherNeuron: hiddenLayer.getHiddenNeurons()){

                //Create connection
                new Connection(neuron, otherNeuron, generator.next());
            }
        }
    }

    /**Execute forward pass for all input neurons
     */
    public void forwardPass(){
        for(InputNeuron neuron: inputNeurons){
            neuron.forwardPass();
        }
    }

    /**Fully connecting this input layer with the output layer
     *
     * @param outputLayer Output layer to connect with
     * @param generator Generator to generate weight values
     */
    public void fullyConnect(OutputLayer outputLayer, WeightGenerator generator){

        //Iterate through neurons
        for(InputNeuron neuron: inputNeurons){
            for(HiddenNeuron otherNeuron: outputLayer.getOutputNeurons()){

                //Create connection
                new Connection(neuron, otherNeuron, generator.next());
            }
        }
    }

    /**Getting input neuron for specific id.
     * The id is in the range 0 to maxNeurons -1
     *
     * @param id Id of the input neuron
     * @return Input neuron with this id
     * @throws CoreNetException Index out of bounds
     */
    public InputNeuron getInputNeuron(int id) throws CoreNetException{
        if(id < 0 || id > inputNeurons.size() -1)
            throw new CoreNetException("Input neuron with id " + id + " does not exist!");

        return inputNeurons.get(id);
    }

    /**Setting inputs of the input layer
     *
     * @param inputs Inputs to set
     * @throws CoreNetException Input data doesnt match
     */
    public void setInputs(float[] inputs) throws CoreNetException{
        if(inputs.length != inputNeurons.size())
            throw new CoreNetException("Input data count doesnt match the input neuron count");

        //Setting input data
        for(int i = 0; i < inputs.length; i++){
            inputNeurons.get(i).setInputValue(inputs[i]);
        }
    }

    /**@return Count of input neurons
     */
    public int getInputCount(){
        return inputNeurons.size();
    }
}
