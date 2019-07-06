package network.activationFunctions;

class Sigmoid extends ActivationFunction {

    /**f(x) = 1 / (1 + e^(-x))
     *
     * @param x Value to activate
     * @return Activated value
     */
    @Override
    public float activate(float x) {
        return (float)(1.0d / (1.0d + Math.pow(Math.E, -1.0d * x)));
    }

    /**f(x) = sig(x)(1 - sig(x))
     *
     * @param x Value to derive
     * @return derived value
     */
    @Override
    public float derive(float x) {
        float sigX = activate(x);
        return sigX * (1.0f -sigX);
    }
}
