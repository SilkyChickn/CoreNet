package network.neurons;

import network.activationFunctions.ActivationFunction;
import network.connections.Connection;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron {

    //Outgoing connections of the neuron
    protected List<Connection> outputConnections = new ArrayList<Connection>();

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

    /**Returns the actual output value of this neuron.
     *
     * @return Current neurons value
     */
    public abstract float getValue();
}
