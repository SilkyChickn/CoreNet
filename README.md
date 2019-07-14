# CoreNet
[![version][version-img]](https://github.com/Suuirad/CoreNet)
[![license][license-img]](LICENSE)

CoreNet is a lightweighted neural network framework, to create a simple neural network.

## Simple Network

The following code creates a simple network and train it using the [MNIST](http://yann.lecun.com/exdb/mnist/) dataset.

```java
//Generator to generate network initial weights
//Here weights between 0 and 1 will be created
WeightGenerator generator = new WeightGenerator(0.0f, 1.0f);

//Create neural network with random weights from generator
//Network has 784 input neurons, 10 output neurons (MNIST) and no hidden layers
NeuralNetwork testNetwork = new NeuralNetwork(generator, 784, 10, 0);

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
```

After the training u can check the resulting network with the graphical mnist simulator.

![Image could not be shown.][simulator]

## Links

> JavaDoc: [https://suuirad.github.io/CoreNet/](https://suuirad.github.io/CoreNet/)

## License

**BSD 2-Clause License**

Copyright (c) 2019, Suuirad<br>
All rights reserved.

<!-- Shiled links -->
[version-img]: https://img.shields.io/badge/version-v.0.1.0-green.svg?style=flat-square
[license-img]: https://img.shields.io/badge/license-BSD-blue.svg?style=flat-square

<!-- Images -->
[simulator]: img/mnist_sim.PNG "CoreNet MNIST Simulator"