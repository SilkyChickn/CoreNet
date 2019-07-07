import network.CoreNetException;
import network.NeuralNetwork;
import network.WeightGenerator;

public class Main {
    public static void main(String[] args) throws CoreNetException {

        WeightGenerator generator = new WeightGenerator(-100, 100);
        NeuralNetwork testNetwork = new NeuralNetwork(generator, 4, 1);

        testNetwork.addHiddenLayer(3);
        testNetwork.fullyConnect();

        testNetwork.getInputLayer().getInputNeuron(0).setInputValue(1.0f);
        testNetwork.getInputLayer().getInputNeuron(1).setInputValue(-2.0f);
        testNetwork.getInputLayer().getInputNeuron(2).setInputValue(3.0f);
        testNetwork.getInputLayer().getInputNeuron(3).setInputValue(-4.0f);
        System.out.println("Value before:" + testNetwork.getOutputLayer().getOutputNeuron(0).getValue());

        float[] inputData = new float[]{
            1.0f, -2.0f, 3.0f, -4.0f
        };
        float[] outputData = new float[]{
            5.0f
        };
        
        for(int i = 0; i < 10000; i++){
            testNetwork.backpropagate(0.1f, inputData, outputData);
        }

        testNetwork.getInputLayer().getInputNeuron(0).setInputValue(1.0f);
        testNetwork.getInputLayer().getInputNeuron(1).setInputValue(-2.0f);
        testNetwork.getInputLayer().getInputNeuron(2).setInputValue(3.0f);
        testNetwork.getInputLayer().getInputNeuron(3).setInputValue(-4.0f);
        System.out.println("Value after:" + testNetwork.getOutputLayer().getOutputNeuron(0).getValue());
    }
}
