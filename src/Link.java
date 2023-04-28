public class Link {
    public Node source;
    public Node dest;
    public double weight = Math.random() - 0.5;

    public Link(Node source, Node dest){
        this.source = source;
        this.dest = dest;
    }
}
