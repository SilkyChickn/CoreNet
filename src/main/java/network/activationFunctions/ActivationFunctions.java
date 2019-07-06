package network.activationFunctions;

/**Singelton activation functions.
 */
public class ActivationFunctions {

    /**f(x) = x
     */
    public static final Identity identity = new Identity();

    /**f(x) = 1 / (1 + e^(-x))
     */
    public static final Sigmoid sigmoid = new Sigmoid();

    /**f(x) = (e^x - e^(-x)) / (e^x + e^(-x))
     */
    public static final HyperbolicTangent hyperbolicTangent = new HyperbolicTangent();

    /**f(x) = x &lt; 0 ? 0 : 1
     */
    public static final Boolean bool = new Boolean();
}
