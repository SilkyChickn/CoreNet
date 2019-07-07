package network;

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
    public NeuralNetwork(WeightGenerator weightGenerator, int inputs, int outputs){
        this.weightGenerator = weightGenerator;

        //Create inputs
        for(int i = 0; i < inputs; i++)
            inputLayer.addInputNeuron(new InputNeuron(ActivationFunctions.identity));

        //Create outputs
        for(int i = 0; i < outputs; i++)
            outputLayer.addOutputNeuron(new HiddenNeuron(ActivationFunctions.identity));
    }

    /**Adding new hidden layer at the end with specific neuron count
     *
     * @param neuronCount Neurons in the new hidden layer
     */
    public void addHiddenLayer(int neuronCount){

        //Create hidden layer
        HiddenLayer newLayer = new HiddenLayer();
        hiddenLayers.add(newLayer);

        //create neurons
        for(int i = 0; i < neuronCount; i++)
            newLayer.addHiddenNeuron(new HiddenNeuron(ActivationFunctions.identity));
    }

    /**Fully connecting the mesh
     */
    public void fullyConnect() {

        //No hidden layers exist
        if(hiddenLayers.size() == 0) {
            inputLayer.fullyConnect(outputLayer, weightGenerator);
        }else{

            //Connect hidden layers from left to right
            for(int i = 0; i < hiddenLayers.size() -1; i++){
                hiddenLayers.get(i).fullyConnectAfter(hiddenLayers.get(i +1), weightGenerator);
            }

            //Connect input and output layers
            inputLayer.fullyConnect(hiddenLayers.get(0), weightGenerator);
            outputLayer.fullyConnect(hiddenLayers.get(hiddenLayers.size() -1), weightGenerator);
        }
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
