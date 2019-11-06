/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 * Node class
 */
public class Node {
    private int calle; //Calle
    private int carrera; //Carrerar
    private double dx1; //Distance from Javier's House to this node
    private double dx2; //Distance from Andreina's House to this node
    private boolean isInAndreinaPath; //This node in Andreina's path
    private boolean isInJavierPath; //This node is in Javier's path
    
        //Construction of the node
    Node(int calle, int carrera){
        this.calle = calle;
        this.carrera = carrera;
    }

        //Getters and Setter
    public double getDx1() {
        return dx1;
    }
    public void setDx1(double dx1) {
        this.dx1 = dx1;
    }

    public double getDx2() {
        return dx2;
    }
    public void setDx2(double dx2) {
        this.dx2 = dx2;
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

    public int getCalle() {
        return calle;
    }
    public int getCarrera() {
        return carrera;
    }
    
    
    
}
