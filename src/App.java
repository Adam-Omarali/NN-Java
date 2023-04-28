import java.util.ArrayList;

public class App {
    public static Data data;
    public static ArrayList<Node>[] neuralNetwork = new ArrayList[3];

    public static void makeLinks(Node source, Node[] destNodes){
        for (int i = 0; i < destNodes.length; i++) {
            Link link = new Link(source, destNodes[0]);
            source.outputLinks.add(link);
            destNodes[i].inputLinks.add(link);
        }
    }

    public static double feedForward(Point point){
        ArrayList<Node> inputLayer = neuralNetwork[0];
        inputLayer.get(0).output = point.x;
        inputLayer.get(0).output = point.y;

        //get all non input layers
        for (int i = 1; i < neuralNetwork.length; i++) {
            ArrayList<Node> layer = neuralNetwork[i];
            for (int j = 0; j < layer.size(); j++) {
                Node node = layer.get(j);
                node.updateOutput();
            }
        }

        return neuralNetwork[neuralNetwork.length - 1].get(0).output;
    }

    public static double iteration(Point point){
        double pred = feedForward(point);
        double ans = point.label;
        double error = pred - ans;
        double adjustment = error * ActivationFunction.sigmoidDeriv(pred);
        System.out.println(pred + " " + ans + " " + adjustment);
        return error;
    }

    public static void main(String[] args) throws Exception {
        data = new Data();

        Node xInput = new Node();
        Node yInput = new Node();

        ArrayList<Node> inputLayer = new ArrayList<Node>();
        inputLayer.add(xInput);
        inputLayer.add(yInput);

        Node hidden1 = new Node();
        Node hidden2 = new Node();
        Node hidden3 = new Node();

        ArrayList<Node> hiddenLayer = new ArrayList<Node>();
        hiddenLayer.add(hidden1);
        hiddenLayer.add(hidden2);
        hiddenLayer.add(hidden3);

        Node output = new Node();

        ArrayList<Node> outputLayer = new ArrayList<Node>();
        outputLayer.add(output);

        makeLinks(xInput, new Node[]{hidden1, hidden2, hidden3});
        makeLinks(yInput, new Node[]{hidden1, hidden2, hidden3});

        makeLinks(hidden1, new Node[]{output});
        makeLinks(hidden2, new Node[]{output});
        makeLinks(hidden3, new Node[]{output});

        neuralNetwork[0] = inputLayer;
        neuralNetwork[1] = hiddenLayer;
        neuralNetwork[2] = outputLayer;

        iteration(Data.trainingData.get(0));

        // NeuralNetwork network = new NeuralNetwork(2, new int[]{3, 3}, 1);

        // network.iteration(Data.trainingData.get(0));
    }
}
