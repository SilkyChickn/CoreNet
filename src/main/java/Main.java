import network.CoreNetException;
import network.NeuralNetwork;
import network.WeightGenerator;

public class Main {
    public static void main(String[] args) throws CoreNetException {

        WeightGenerator generator = new WeightGenerator(1, 1);
        NeuralNetwork testNetwork = new NeuralNetwork(generator, 4, 1);

        testNetwork.addHiddenLayer(2);
        testNetwork.fullyConnect();

        testNetwork.getInputLayer().getInputNeuron(0).setInputValue(1.0f);
        testNetwork.getInputLayer().getInputNeuron(1).setInputValue(1.0f);
        testNetwork.getInputLayer().getInputNeuron(2).setInputValue(1.0f);
        testNetwork.getInputLayer().getInputNeuron(3).setInputValue(1.0f);

        System.out.println("Value:" + testNetwork.getOutputLayer().getOutputNeuron(0).getValue());
    }
}
