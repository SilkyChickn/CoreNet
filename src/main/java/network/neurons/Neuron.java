package network.neurons;

import network.activationFunctions.ActivationFunction;

public abstract class Neuron {

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

    /**Returns the actual output value of this neuron.
     *
     * @return Current neurons value
     */
    public abstract float getValue();
}
