package network.layers;

import network.CoreNetException;
import network.WeightGenerator;
import network.connections.Connection;
import network.neurons.HiddenNeuron;

import java.util.ArrayList;
import java.util.List;

public class OutputLayer {

    //List of all neurons of this output layer
    private List<HiddenNeuron> outputNeurons = new ArrayList<>();

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

    /**Execute forward pass for all neurons in this layer
     */
    public void forwardPass(){
        for(HiddenNeuron neuron: outputNeurons){
            neuron.forwardPass();
        }
    }
    /**Backpropagates this output layer by the learn effect epsilon.
     *
     * DeltaWik = E * Di * ak
     *
     * @param learnEffect How much to adjust the weights (0..1)
     * @param shoulds Output should data trainings set
     */
    public void backpropagate(float learnEffect, float[] shoulds) throws CoreNetException {

        //Check if trainings set matches
        if(shoulds.length != outputNeurons.size())
            throw new CoreNetException("Trainings set output data count doesnt match the output neuron count");

        //Backpropagate output layer
        for(int i = 0; i < shoulds.length; i++){
            outputNeurons.get(i).backpropagateOutput(learnEffect, shoulds[i]);
        }
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
