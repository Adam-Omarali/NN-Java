public class Link {
    public Node source;
    public Node dest;
    public double weight = Math.random() - 0.5;

    public Link(Node source, Node dest, double weight){
        this.source = source;
        this.dest = dest;
    }

    public void setWeight(double size) {
        this.weight = size;
    }
}
