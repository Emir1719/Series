public class Node
{
    private Node next;
    private Node before;
    private Object data;
    private int ID; //Serinin index'i
    private static int count = -1;

    public Node()
    {
        this.next = null;
        this.before = null;
        this.data = null;
        Node.count++;
        this.ID = count;
    }

    public Node(Object value)
    {
        this.next = null;
        this.before = null;
        this.data = value;
        Node.count++;
        this.ID = count;
    }

    public void setBefore(Node before) {
        this.before = before;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public static void setCount(int count) {
        Node.count = count;
    }

    public Node getBefore() {
        return before;
    }

    public Node getNext() {
        return next;
    }

    public Node getNext50x() {
        return getNext10x().getNext10x().getNext10x().getNext10x().getNext10x();
    }

    public Node getNext10x() {
        return next.next.next.next.next.next.next.next.next.next;
    }

    public Node getNext5x() {
        return next.next.next.next.next;
    }

    public Object getData() {
        return data;
    }

    public int getID() {
        return ID;
    }

    public static int getCount(){
        return Node.count;
    }
}