package mnist;

import network.CoreNetException;
import network.NeuralNetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class MnistSimulator implements MouseMotionListener {

    //Elements to draw simulation
    private JFrame frame;
    private JPanel canvas;
    private Graphics2D pen;

    //Guess label
    private JLabel result;

    //Current network to test
    private MnistTrainer trainer;
    private NeuralNetwork network;

    //Canvas data
    private float[] data = new float[28 * 28];

    /**Creating new mnist simulator
     * to test the mnist trained neural network, with self drawed numbers
     */
    MnistSimulator(MnistTrainer trainer, int sx, int sy) throws CoreNetException {
        if(sx % 28 != 0 || sy % 28 != 0)
            throw new CoreNetException("The dimension of the frame must be a multiple of 28x28");

        this.trainer = trainer;

        //Create frame
        frame = new JFrame("CoreNet: Mnist Simulator");
        frame.setSize(sx, sy +70);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.addMouseMotionListener(this);
        frame.setVisible(false);

        //Canvas
        canvas = new JPanel();
        canvas.setSize(sx, sy);
        canvas.setLocation(0, 0);
        frame.add(canvas);

        //Repaint button
        JButton repaint = new JButton("Clear");
        repaint.setSize(75, 25);
        repaint.setLocation(sx -90, sy +5);
        repaint.addActionListener((e) -> repaint());
        frame.add(repaint);

        //Guess button
        JButton guess = new JButton("Guess");
        guess.setSize(75, 25);
        guess.setLocation(10, sy +5);
        guess.addActionListener((e) -> guess());
        frame.add(guess);

        //Guess label
        result = new JLabel("My guess is:");
        result.setSize(100, 25);
        result.setLocation(100, sy +5);
        result.setVisible(true);
        frame.add(result);
    }

    /**Guessing actual image
     */
    private void guess(){
        try {
            network.getInputLayer().setInputs(data);
            network.forwardPass();
            int answer = trainer.getAnswer(network, true);

            result.setText("My guess: " + answer);
            frame.revalidate();
        } catch (CoreNetException e) {
            e.printStackTrace();
        }
    }

    /**Start interactive, graphical mnist simulation with network
     *
     * @param network Network to simulate
     */
    void simulate(NeuralNetwork network){
        this.network = network;
        frame.setVisible(true);
        frame.revalidate();
        pen = (Graphics2D) canvas.getGraphics();
        for(int i = 0; i < 100; i++) repaint();
    }

    /**Repaint simulator
     */
    private void repaint(){

        //Clear data
        data = new float[28 * 28];

        //Clear canvas
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, frame.getWidth(), frame.getHeight() -70);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {

            //Calculate canvas scale
            int scaleX = frame.getWidth() / 28;
            int scaleY = (frame.getHeight() -70) / 28;

            //Calc mouse position
            int mx = (int) (frame.getMousePosition().getX() / scaleX);
            int my = (int) ((frame.getMousePosition().getY() -35) / scaleY);

            //Set data
            for(int x = mx -1; x < mx +2; x++){
                for(int y = my -1; y < my +2; y++){
                    if(x < 28 && y < 28 && x >= 0 && y >= 0) {

                        //Change data
                        pen.setColor(Color.BLACK);
                        pen.fillRect(x * scaleX, y * scaleY, scaleX, scaleY);
                        data[y * 28 + x] = 1.0f;
                    }
                }
            }

        }catch (NullPointerException ex){}
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
