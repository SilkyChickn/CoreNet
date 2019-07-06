package network.activationFunctions;

class HyperbolicTangent extends ActivationFunction {

    /**f(x) = (e^x - e^(-x)) / (e^x + e^(-x))
     *
     * @param x Value to activate
     * @return Activated value
     */
    @Override
    public float activate(float x) {
        return (float) Math.tanh(x);
    }

    /**f(x) = 1
     *
     * @param x Value to derive
     * @return derived value
     */
    @Override
    public float derive(float x) {
        float tanh = activate(x);
        return 1.0f - ((float) Math.pow(tanh, 2));
    }
}
