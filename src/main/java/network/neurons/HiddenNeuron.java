package network.neurons;

import network.activationFunctions.ActivationFunction;
import network.connections.Connection;

import java.util.ArrayList;
import java.util.List;

public class HiddenNeuron extends Neuron {

    //Connections with other neurons
    private List<Connection> inputConnections = new ArrayList<Connection>();

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

    /**Recalculate value of this neuron and all previous ones and return.
     * Sum of all incoming connections with the activation function and threshold if active
     */
    @Override
    public float getValue() {

        //Activate sum
        float derivedInput = activationFunction.activate(getNetInput());

        //Apply threshold
        if(useThreshold) value = derivedInput >= threshold ? derivedInput : 0;
        else value = derivedInput;

        return value;
    }

    /**Backpropagate the neuron with the output delta and all previous
     *
     * @param learnEffect Learn effect of the weight adjustment
     * @param should Should value of the neuron
     */
    public void backpropagateOutput(float learnEffect, float should){

        float derivedInput = activationFunction.derive(getNetInput()); //Derived net input
        float error = should -getValue(); //Error value
        float di = derivedInput * error; //Small delta i

        //Backpropagate
        backpropagate(learnEffect, di);
    }

    /**Backpropagate the neuron with the hidden delta and all previous
     *
     * @param learnEffect Learn effect of the weight adjustment
     */
    public void backpropagateHidden(float learnEffect){

        //Derived net input
        float derivedInput = activationFunction.derive(getNetInput());

        //Sum of all last adjustments * weight of the outgoing connections
        float sumDlWli = 0.0f;
        for(Connection c: outputConnections){
            float lastAdjust = c.getLastAdjustment();
            float weight = c.getWeight();
            sumDlWli += (lastAdjust * weight);
        }

        //Small delta i
        float di = derivedInput * sumDlWli;

        //Backpropagate
        backpropagate(learnEffect, di);
    }

    private void backpropagate(float learnEffect, float deltai){

        //Calc delta i * learn effect
        float di = learnEffect * deltai;

        //Calc and apply weight adjustments
        for(Connection c: inputConnections){
            float ak = c.getValue(); //Activation level of input neuron
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
            sum += in.getWeightedValue();
        }

        return sum;
    }
}
