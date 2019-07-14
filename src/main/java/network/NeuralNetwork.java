package network;

import network.activationFunctions.ActivationFunction;
import network.activationFunctions.ActivationFunctions;
import network.layers.HiddenLayer;
import network.layers.InputLayer;
import network.layers.OutputLayer;
import network.neurons.HiddenNeuron;
import network.neurons.InputNeuron;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    //Input layer of the network
    private InputLayer inputLayer = new InputLayer();

    //Output layer of the network
    private OutputLayer outputLayer = new OutputLayer();

    //List of all convolutional layers
    //private List<HiddenLayer> convulutionalLayers = new ArrayList<HiddenLayer>();

    //List of all hidden layers
    private List<HiddenLayer> hiddenLayers = new ArrayList<>();

    //Weight generator to generate connection weights
    private WeightGenerator weightGenerator;

    /**Creating new neural network with specific input and output count.
     *
     * @param weightGenerator Generator to generate weights
     * @param inputs Input neuron count
     * @param outputs Output neuron count
     */
    public NeuralNetwork(WeightGenerator weightGenerator, int inputs, int outputs, int hiddenLayers){
        this.weightGenerator = weightGenerator;

        //Create inputs
        for(int i = 0; i < inputs; i++)
            inputLayer.addInputNeuron(new InputNeuron(ActivationFunctions.sigmoid));

        //Create hidden layers
        for(int i = 0; i < hiddenLayers; i++)
            this.hiddenLayers.add(new HiddenLayer());

        //Create outputs
        for(int i = 0; i < outputs; i++)
            outputLayer.addOutputNeuron(new HiddenNeuron(ActivationFunctions.sigmoid));

        //Single layer perceptron
        if(hiddenLayers == 0) inputLayer.fullyConnectOutputLayer(outputLayer, weightGenerator);
    }

    /**Mutating new neurons in a specific hidden layer and connecting them with the mesh
     *
     * @param layer Hidden layer to mutate neurons in
     * @throws CoreNetException Hidden layer does not exist
     */
    public void mutateHiddenNeurons(int layer, int count) throws CoreNetException {
        if(layer < 0 || layer > hiddenLayers.size() -1)
            throw new CoreNetException("Hidden layer does not exist");

        //Add neurons
        for(int i = 0; i < count; i++){
            mutateHiddenNeuron(layer, ActivationFunctions.sigmoid);
        }
    }

    /**Mutating new neuron in a specific hidden layer and connecting with the mesh
     *
     * @param layer Hidden layer to mutate neuron in
     * @param activationFunction Activation function of the neuron
     * @throws CoreNetException Hidden layer does not exist
     */
    public void mutateHiddenNeuron(int layer, ActivationFunction activationFunction) throws CoreNetException {
        if(layer < 0 || layer > hiddenLayers.size() -1)
            throw new CoreNetException("Hidden layer does not exist");

        //Add neuron
        HiddenNeuron neuron = new HiddenNeuron(activationFunction);
        hiddenLayers.get(layer).addHiddenNeuron(neuron);

        //Connect neuron left
        if(layer == 0) inputLayer.connectNeuronAfter(neuron, weightGenerator);
        else hiddenLayers.get(layer -1).connectNeuronAfter(neuron, weightGenerator);

        //Connect neuron right
        if(layer == hiddenLayers.size() -1) outputLayer.connectNeuronBefore(neuron, weightGenerator);
        else hiddenLayers.get(layer +1).connectNeuronBefore(neuron, weightGenerator);
    }

    /**Backpropagates the network by the learn effect epsilon.
     *
     * DeltaWik = E * Di * ak
     *
     * @param learnEffect How much to adjust the weights (0..1)
     * @param inputData Input data trainings set
     * @param outputData Output should data trainings set
     */
    public void backpropagate(float learnEffect, float[] inputData, float[] outputData) throws CoreNetException{
        inputLayer.setInputs(inputData); //Set input data
        forwardPass(); //Forward pass input data
        outputLayer.backpropagate(learnEffect, outputData); //Backpropagate output layer

        //Backpropagate hidden layers (backwards)
        for(int i = hiddenLayers.size() -1; i >= 0; i--){
            hiddenLayers.get(i).backpropagate(learnEffect);
        }
    }

    /**Evaluating the last forwarded output of the network.
     * The network will try to improve by your evaluation.
     *
     * @param learnEffect Learn effect to use, how strong to remember decision
     * @param evaluation Evaluation of the last forwardPass.
     *                   Strong negative is bad, strong positive is good, zero is neutral
     */
    public void evaluate(float learnEffect, float evaluation){

        //Calculate evaluation factor
        float factor = learnEffect * evaluation;

        try {

            //Create learn set
            float[] outs = new float[outputLayer.getOutputCount()];
            for (int i = 0; i < outs.length; i++) {
                outs[i] = outputLayer.getOutputNeuron(i).getValue();
            }

            //Evaluate
            outputLayer.backpropagate(factor, outs);
            for(int i = hiddenLayers.size() -1; i >= 0; i--){
                hiddenLayers.get(i).backpropagate(factor);
            }
        }catch(CoreNetException ex){
            ex.printStackTrace();
        }
    }

    /**Executes forward pass for the whole network with the setted input values.
     * Outputed values of the neuron are only valid after this
     */
    public void forwardPass(){
        inputLayer.forwardPass();
        for(HiddenLayer layer: hiddenLayers) layer.forwardPass();
        outputLayer.forwardPass();
    }

    /**@return Networks input layer
     */
    public InputLayer getInputLayer() {
        return inputLayer;
    }

    /**@return Networks output layer
     */
    public OutputLayer getOutputLayer() {
        return outputLayer;
    }
}
