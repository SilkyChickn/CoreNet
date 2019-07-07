import mnist.MnistTrainer;
import network.CoreNetException;
import network.NeuralNetwork;
import network.WeightGenerator;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws CoreNetException, IOException, URISyntaxException {

        WeightGenerator generator = new WeightGenerator(-1.0f, 1.0f);
        NeuralNetwork testNetwork = new NeuralNetwork(generator, 784, 10);

        //testNetwork.addHiddenLayer(100);
        testNetwork.fullyConnect();

        MnistTrainer trainer = new MnistTrainer();

        System.out.println("Starting test...");
        trainer.test(testNetwork);

        float learnEffect = 0.01f;
        while(true){
            System.out.println("Start training...");
            trainer.train(testNetwork, learnEffect);
            learnEffect *= 0.9f;
        }
    }
}
