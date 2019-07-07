package network.neurons;

import network.activationFunctions.ActivationFunction;
import network.connections.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron {

    //Outgoing connections of the neuron
    List<Connection> outputConnections = new ArrayList<>();

    //Activation function of this neuron
    ActivationFunction activationFunction;

    //Current neurons value
    protected float value = 0.0f;

    //Threshold of this neuron
    float threshold = 0.0f;
    boolean useThreshold = false;

    /**Creating neuron and setting its activation function
     *
     * @param activationFunction Activation function of this neuron
     */
    Neuron(ActivationFunction activationFunction){
        this.activationFunction = activationFunction;
    }

    /**Adding outgoing connection to the neuron
     *
     * @param connection New outgoing connection
     */
    public void addOutputConnection(Connection connection) {
        outputConnections.add(connection);
    }

    /**Calc the actual output value of this neuron.
     */
    public abstract void forwardPass();

    /**@return Actual output value of this neuron.
     */
    public float getValue() {
        return value;
    }
}
