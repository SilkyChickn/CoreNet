package network.connections;

import network.neurons.HiddenNeuron;
import network.neurons.Neuron;

public class Connection {

    //Neuron to get input from
    private Neuron inputNeuron;

    //Neuron on the output side
    private HiddenNeuron outputNeuron;

    //Current weight of this connection
    private float weight;

    /**Creating new connection between two neurons
     *
     * @param in Neuron on the connection input side
     * @param out Neuron on the connection output side
     * @param weight Connections initial weight
     */
    public Connection(Neuron in, HiddenNeuron out, float weight){
        this.inputNeuron = in;
        this.outputNeuron = out;
        this.weight = weight;

        //Adding connection to neurons
        out.addInputConnection(this);
        in.addOutputConnection(this);
    }

    /**Adding float value to the weight
     *
     * @param weight Value to add to weight
     */
    public void addWeight(float weight){
        this.weight += weight;
    }

    /**@return Neuron on the output side
     */
    public HiddenNeuron getOutputNeuron() {
        return outputNeuron;
    }

    /**@return Connections current weight
     */
    public float getWeight() {
        return weight;
    }

    /**Getting value of the input neuron. (Not Weighted!)
     *
     * @return Value of the input neuron
     */
    public float getValue(){
        return (inputNeuron.getValue() * weight);
    }

    /**@return Inputting neuron of this connection
     */
    public Neuron getInputNeuron() {
        return inputNeuron;
    }
}
