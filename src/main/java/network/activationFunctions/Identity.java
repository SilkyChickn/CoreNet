package network.activationFunctions;

class Identity extends ActivationFunction {

    /**f(x) = x
     *
     * @param x Value to activate
     * @return Activated value
     */
    @Override
    public float activate(float x) {
        return x;
    }

    /**f(x) = 1
     *
     * @param x Value to derive
     * @return derived value
     */
    @Override
    public float derive(float x) {
        return 1;
    }
}
