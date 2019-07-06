package network.activationFunctions;

public abstract class ActivationFunction {

    /**Execute activation function to input value
     *
     * @param x Value to activate
     * @return Activated value
     */
    public abstract float activate(float x);

    /**Execute first derivation from input value
     *
     * @param x Value to derive
     * @return Derived value
     */
    public abstract float derive(float x);
}
