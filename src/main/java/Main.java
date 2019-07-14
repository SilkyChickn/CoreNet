import mnist.MnistTrainer;
import network.CoreNetException;
import network.NeuralNetwork;
import network.WeightGenerator;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws CoreNetException, IOException, URISyntaxException {

        //Generator to generate network initial weights
        //Here weights between 0 and 1 will be created
        WeightGenerator generator = new WeightGenerator(0.0f, 1.0f);

        //Create neural network with random weights from generator
        //Network has 784 input and 10 output neurons (MNIST)
        NeuralNetwork testNetwork = new NeuralNetwork(generator, 784, 10, 0);
        //testNetwork.mutateHiddenNeurons(0, 10);

        //Create MNIST Dataset trainer and start test before training
        MnistTrainer trainer = new MnistTrainer();
        System.out.println("Starting test...");
        trainer.test(testNetwork);

        //Train until the network reach a success rate of 88 percent
        float learnEffect = 0.01f;
        while(trainer.train(testNetwork, learnEffect) < 0.9f){
            learnEffect *= 0.9f;
        }

        //start graphical and interactive MNIST simulator
        trainer.simulate(testNetwork);
    }
}
