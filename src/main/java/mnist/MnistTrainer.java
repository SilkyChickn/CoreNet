package mnist;

import network.CoreNetException;
import network.NeuralNetwork;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MnistTrainer {

    //Should outputs
    private static final float[] out0 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final float[] out1 = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final float[] out2 = {0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
    private static final float[] out3 = {0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
    private static final float[] out4 = {0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
    private static final float[] out5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    private static final float[] out6 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
    private static final float[] out7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
    private static final float[] out8 = {0, 0, 0, 0, 0, 0, 0, 0, 1, 0};
    private static final float[] out9 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

    //Mnist data files
    private static final String TRAIN_IMAGES = "train-images.idx3-ubyte";
    private static final String TRAIN_LABELS = "train-labels.idx1-ubyte";
    private static final String TEST_IMAGES = "t10k-images.idx3-ubyte";
    private static final String TEST_LABELS = "t10k-labels.idx1-ubyte";

    //Mnist data
    private List<MnistDigit> trainData;
    private List<MnistDigit> testData;

    /**Loading mnist data set from resources
     *
     * @throws URISyntaxException Error by parsing resource url
     * @throws IOException Error by loading Mnist data
     */
    public MnistTrainer() throws IOException, URISyntaxException {
        trainData = MnistLoader.loadData(TRAIN_IMAGES, TRAIN_LABELS);
        testData = MnistLoader.loadData(TEST_IMAGES, TEST_LABELS);
    }

    /**Step one training iteration.
     *
     * @param network Network to train
     * @param learnEffect Learn effect epsilon
     * @return Error after training by processing the test set. 1 is best, 0 is baddest
     */
    public float train(NeuralNetwork network, float learnEffect) throws CoreNetException{
        checkMatch(network);

        ///Iterate train data
        for(MnistDigit digit: trainData){
            network.backpropagate(learnEffect, digit.getDataArray(), getShould(digit.getLabel()));
        }

        return test(network);
    }

    private void checkMatch(NeuralNetwork network) throws CoreNetException{

        //Check if inputs match
        int mnistIns = trainData.get(0).getDataArray().length;
        if(network.getInputLayer().getInputCount() != mnistIns)
            throw new CoreNetException("The input neuron count doesnt match the mnist data (" + mnistIns + ")");

        //Check if outputs match
        if(network.getOutputLayer().getOutputCount() != 10)
            throw new CoreNetException("The output neuron count doesnt match the mnist data (10)");
    }

    /**Get the error by passing the test data set
     *
     * @param network Network to test
     * @return Error value by processing the test set. 1 is best, 0 is baddest
     */
    public float test(NeuralNetwork network) throws CoreNetException{
        checkMatch(network);

        int correct = 0;
        int wrong = 0;

        for(MnistDigit digit: testData){

            //Set inputs and pass
            network.getInputLayer().setInputs(digit.getDataArray());
            network.forwardPass();

            //Is networks guess correct?
            if(digit.getLabel() == getAnswer(network))correct++;
            else wrong++;
        }

        float error = 1.0f * wrong / testData.size();
        float success = 1.0f * correct / testData.size();

        System.out.println("################## MNIST-Trainer ##################");
        System.out.println("Correct:" + correct + " Wrong:" + wrong);
        System.out.println("ErrorRate:" + error);
        System.out.println("SuccessRate:" + success);
        System.out.println("###################################################");

        //Return correctness in percent (0..1)
        return success;
    }

    private float[] getShould(int label){
        switch (label) {
            case 0: return out0;
            case 1: return out1;
            case 2: return out2;
            case 3: return out3;
            case 4: return out4;
            case 5: return out5;
            case 6: return out6;
            case 7: return out7;
            case 8: return out8;
            case 9: return out9;
        }
        return new float[10];
    }

    private int getAnswer(NeuralNetwork network) throws CoreNetException{
        int best = 0;
        float bestVal = 0.0f;
        for(int i = 0; i < 10; i++){
            float val = network.getOutputLayer().getOutputNeuron(i).getValue();
            if(val > bestVal){
                bestVal = val;
                best = i;
            }
        }
        return best;
    }
}
