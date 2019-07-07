# CoreNet

CoreNet is a lightweighted neural network framework, to create a simple neural network.

## Simple Network

The following code creates a simple network and train it using the [MNIST](http://yann.lecun.com/exdb/mnist/) dataset.
The example creates a network with 3 layers (input, 1 x hidden, output) and fully connecting the neurons. Then it test the network with the mnist data set and train it in an endless loop.

```java
WeightGenerator generator = new WeightGenerator(-1.0f, 1.0f);

NeuralNetwork testNetwork = new NeuralNetwork(generator, 784, 10);
testNetwork.addHiddenLayer(100);
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
```

## Links

> JavaDoc: [https://suuirad.github.io/CoreNet/](https://suuirad.github.io/CoreNet/)

## License

**BSD 2-Clause License**

Copyright (c) 2019, Suuirad<br>
All rights reserved.