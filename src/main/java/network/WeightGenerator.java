package network;

import java.util.Random;

public class WeightGenerator {

    //Range of the generated values
    private float min, delta;

    //Random generator
    private Random random;

    //Seed to generate values
    private long seed;

    /**Creating weight generator and setting its initial seed.
     * Every network with this seed get the same initial weights
     *
     * @param seed Initial seed value
     * @param min Minimum weight value to generate (inclusive)
     * @param max Maximum weight value to generate (exclusive)
     */
    public WeightGenerator(long seed, float min, float max){
        this.min = min;
        this.seed = seed;

        delta = max -min;
        random = new Random(seed);
    }

    /**Creating weight generator and setting its initial seed to current time millis.
     *
     * @param min Minimum weight value to generate (inclusive)
     * @param max Maximum weight value to generate (exclusive)
     */
    public WeightGenerator(float min, float max){
        this.min = min;
        this.seed = System.currentTimeMillis();

        delta = max -min;
        random = new Random(seed);
    }

    public float next(){
        return min + (random.nextFloat() * delta);
    }
}
