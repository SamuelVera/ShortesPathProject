/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 * Edge class
 */
public class Edge {
    private double time; //Weight of the edge
    private Node nodeEnd1; //Frist from West to East or South to North
    private Node nodeEnd2; //Second from West to East or South to North
    private boolean isInAndreinaPath; //This node in Andreina's path
    private boolean isInJavierPath; //This node is in Javier's path
    
        //Initialize the edge
    Edge(Node nodeEnd1, Node nodeEnd2, double time){
        this.nodeEnd1 = nodeEnd1;
        this.nodeEnd2 = nodeEnd2;
        this.time = time;
    }
    
        //Getters and setters
    public double getTime() {
        return time;
    }

    public Node getNodeEnd1() {
        return nodeEnd1;
    }
    public Node getNodeEnd2() {
        return nodeEnd2;
    }
    
    public boolean isIsInAndreinaPath() {
        return isInAndreinaPath;
    }
    public void setIsInAndreinaPath(boolean isInAndreinaPath) {
        this.isInAndreinaPath = isInAndreinaPath;
    }

    public boolean isIsInJavierPath() {
        return isInJavierPath;
    }
    public void setIsInJavierPath(boolean isInJavierPath) {
        this.isInJavierPath = isInJavierPath;
    }
}
