public class ActivationFunction {
    public static double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoidDeriv(double x){
        return sigmoid(x) * (1- sigmoid(x));
    }
}
