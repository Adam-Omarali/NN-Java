import java.util.ArrayList;

public class NeuralNetwork {
    public Node[] inputLayer;
    public ArrayList<Node>[] hiddenLayers;
    public Node[] outputLayer;

    public NeuralNetwork(int inputLayerSize, int[] hiddenLayer, int outputLayerSize){

        inputLayer = new Node[inputLayerSize];
        for (int i = 0; i < inputLayerSize; i++) {
            inputLayer[i] = new Node();
        }

        hiddenLayers = new ArrayList[hiddenLayer.length];
        for (int i = 0; i < hiddenLayer.length; i++) {
            hiddenLayers[i] = new ArrayList<Node>();
            for (int j = 0; j < hiddenLayer[i]; j++) {
                hiddenLayers[i].add(new Node());
            }
        }

        outputLayer = new Node[outputLayerSize];
        for (int i = 0; i < outputLayerSize; i++) {
            outputLayer[i] = new Node();
        }

        makeLinks();

    }

    public void makeLinks(){
        //input layer
        for (int i = 0; i < inputLayer.length; i++) {
            for (int j = 0; j < hiddenLayers[0].size(); j++) {
                addLink(inputLayer[i], hiddenLayers[0].get(j));
            }
        }
        //between hidden layers
        for (int i = 0; i < hiddenLayers.length - 1; i++) {
            for (int j = 0; j < hiddenLayers[i].size(); j++) {
                Node source = hiddenLayers[i].get(j);
                for (int k = 0; k < hiddenLayers[i+1].size(); k++) {
                    Node dest = hiddenLayers[i+1].get(k);
                    addLink(source, dest);
                }
            }
        }

        //output layer
        for (int i = 0; i < hiddenLayers[hiddenLayers.length - 1].size(); i++) {
            for (int j = 0; j < outputLayer.length; j++) {
                addLink(hiddenLayers[hiddenLayers.length - 1].get(i), outputLayer[j]);
            }
        }
    }

    public void addLink(Node source, Node dest){
        Link link = new Link(source, dest);
        source.outputLinks.add(link);
        dest.inputLinks.add(link);
    }

    public double feedForward(Point point){
        inputLayer[0].output = point.x;
        inputLayer[1].output = point.y;

        //get all hidden layers
        for (int i = 1; i < hiddenLayers.length; i++) {
            ArrayList<Node> layer = hiddenLayers[i];
            for (int j = 0; j < layer.size(); j++) {
                Node node = layer.get(j);
                node.updateOutput();
            }
        }

        //output layer update
        for (int j = 0; j < outputLayer.length; j++) {
            Node node = outputLayer[j];
            node.updateOutput();
        }

        return outputLayer[0].output;
    }

    public double iteration(Point point){
        double pred = feedForward(point);
        double ans = point.label;
        double error = Math.pow((pred - ans), 2);
        double adjustment = error * ActivationFunction.sigmoidDeriv(pred);
        System.out.println(pred + " " + ans + " " + adjustment);

        double dcDA = 2 * pred;
        double daDZ = ActivationFunction.sigmoidDeriv(hiddenLayers[hiddenLayers.length - 1].get(0).output);
        double dzDW = hiddenLayers[hiddenLayers.length - 1].get(0).output;
        System.out.println("dcDa: " + dcDA + " daDZ: " + daDZ + " dzDW: " + dzDW + " multiply: " + dcDA * daDZ * dzDW);
        return error;
    }
}
