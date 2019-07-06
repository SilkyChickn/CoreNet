package network.neurons;

import network.activationFunctions.ActivationFunction;

public class InputNeuron extends Neuron {

    //Inputting value for this neuron
    private float inputValue = 0.0f;

    /**Creating input neuron and setting its activation function.
     * At the beginning this neuron will be non connected.
     * This neuron can be used to input data into the network.
     *
     * @param activationFunction Activation function of this neuron
     */
    public InputNeuron(ActivationFunction activationFunction) {
        super(activationFunction);
    }

    /**Setting value of this neuron
     *
     * @param val New value of this neuron
     */
    public void setInputValue(float val){
        this.inputValue = val;
    }

    /**@return Activated input value with threshold applied
     */
    @Override
    public float getValue() {

        //Activate value
        float temp = activationFunction.activate(inputValue);

        //Apply threshold
        if(useThreshold) value = temp >= threshold ? temp : 0;
        else value = temp;

        return value;
    }
}
