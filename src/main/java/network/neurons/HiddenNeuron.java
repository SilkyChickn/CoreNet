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

        //Sum input values
        float sum = 0.0f;
        for(Connection in: inputConnections){
            sum += in.getWeightedValue();
        }

        //Activate sum
        sum = activationFunction.activate(sum);

        //Apply threshold
        if(useThreshold) value = sum >= threshold ? sum : 0;
        else value = sum;

        return value;
    }

    /**Backpropagates the neuron and all previous
     *
     * @param learnEffect Learn effect of the weight adjustment
     * @param should Should value of the neuron
     */
    public void backpropagate(float learnEffect, float should){
        //TODO: implement backpropagation
    }
}
