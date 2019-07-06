package network.activationFunctions;

class Boolean extends ActivationFunction {

    /**f(x) = x < 0 ? 0 : 1
     *
     * @param x Value to activate
     * @return Activated value
     */
    @Override
    public float activate(float x) {
        return (x < 0) ? 0 : 1;
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
