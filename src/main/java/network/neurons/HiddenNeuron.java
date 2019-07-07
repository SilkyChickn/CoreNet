package network.neurons;

import network.activationFunctions.ActivationFunction;
import network.connections.Connection;

import java.util.ArrayList;
import java.util.List;

public class HiddenNeuron extends Neuron {

    //Connections with other neurons
    private List<Connection> inputConnections = new ArrayList<>();
    
    //Last small delta value
    private float smallDelta = 0.0f;
    
    /**Creating hidden neuron and setting its activation function.
     * At the beginning this neuron will be non connected.
     *
     * @param activationFunction Activation function of this neuron
     */
    public HiddenNeuron(ActivationFunction activationFunction) {
        super(activationFunction);
    }

    /**Adding incoming connection to the neuron
     *
     * @param connection New incoming connection
     */
    public void addInputConnection(Connection connection) {
        inputConnections.add(connection);
    }

    /**Recalculate value of this neuron (all previous ones must be calculated before).
     * Sum of all incoming connections with the activation function and threshold if active
     */
    @Override
    public void forwardPass() {

        //Activate sum
        float activatedInput = activationFunction.activate(getNetInput());

        //Apply threshold
        if(useThreshold) value = activatedInput >= threshold ? activatedInput : 0;
        else value = activatedInput;
    }

    /**Backpropagate the neuron with the output delta and all previous
     *
     * @param learnEffect Learn effect of the weight adjustment
     * @param should Should value of the neuron
     */
    public void backpropagateOutput(float learnEffect, float should){
        smallDelta = should -getValue(); //Small delta i
        backpropagate(learnEffect); //Backpropagate
    }

    /**Backpropagate the neuron with the hidden delta and all previous
     *
     * @param learnEffect Learn effect of the weight adjustment
     */
    public void backpropagateHidden(float learnEffect){

        //Sum of all last adjustments * weight of the outgoing connections
        smallDelta = 0.0f;
        for(Connection c: outputConnections){
            smallDelta += c.getOutputNeuron().smallDelta * c.getWeight();
        }

        //Backpropagate
        backpropagate(learnEffect);
    }

    /**Backpropagate this neuron. The small delta value of this neuron will be used.
     *
     * @param learnEffect Learn effect of the adjustment
     */
    private void backpropagate(float learnEffect){

        //Calc delta i * learn effect
        float di = learnEffect * smallDelta * activationFunction.derive(getValue());
        
        //Calc and apply weight adjustments
        for(Connection c: inputConnections){
            float ak = c.getInputNeuron().value; //Activation level of input neuron
            float DWik = di * ak; //Delta weight adjustment
            c.addWeight(DWik); //Adjust weight
        }
    }

    /**@return Sum of all weighted input values
     */
    private float getNetInput(){

        //Sum input values
        float sum = 0.0f;
        for(Connection in: inputConnections){
            sum += in.getValue();
        }

        return sum;
    }
}
