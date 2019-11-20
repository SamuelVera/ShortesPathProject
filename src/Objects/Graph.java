
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Graph class
 */
public class Graph {

    //Nodes Array
    private Node[][] nodeArray;
    //Edge list
    private LinkedList<Edge> edgesList = new LinkedList<>();
    //Initial Javier and Andreina nodes
    private Node javierInitalNode;
    private Node andreinaInitialNode;
    //Encounter Point
    private Node terminalNode;

    //Real paths
    private Stack<Node> javierRealPath;
    private Stack<Node> andreinaRealPath;

    //Contructor for the node
    public Graph() {
        //5x5 Node array
        this.nodeArray = new Node[5][5];
        //Initialized Node list
        for (int i = 0; i < 5; i++) { //5 Calles (54-53-52-51-50)
            for (int j = 0; j < 5; j++) { //5 Carreras (11-12-13-14-15)
                this.nodeArray[i][j] = new Node(
                        54 - i, //Calle
                        11 + j //Carrera
                ); //New node                
            }
        }
        // Create Edges List form west to east and south to north
        for (int i = 4; i > -1; i--) { //Calles 53-52-51-50
            for (int j = 0; j < 5; j++) { //Carreras 11-12-13-14
                if (i != 0 || j != 4) { //La esquina superior derecha ya tiene sus arcos arreglados
                    if (i == 0) { //Calle 50
                        this.edgesList.add(new Edge( //West to East Edge
                                this.nodeArray[i][j], //Initial
                                this.nodeArray[i][j + 1], //Final
                                5
                        )
                        );
                    } else if (j == 4) { //Carrera 15
                        this.edgesList.add(new Edge( //South to North Edge
                                this.nodeArray[i][j], //Initial
                                this.nodeArray[i - 1][j], //Final
                                5
                        )
                        );
                    } else { //Other Calles
                        if (j == 0 || j == 1 || j == 2) { //Carreras 11, 12 and 13 in bad shape
                            this.edgesList.add(new Edge( //South to North Edge
                                    this.nodeArray[i][j], //Initial
                                    this.nodeArray[i - 1][j], //Final
                                    7
                            )
                            );
                        } else {
                            this.edgesList.add(new Edge( //South to North Edge
                                    this.nodeArray[i][j], //Initial
                                    this.nodeArray[i - 1][j], //Final
                                    5
                            )
                            );
                        }
                        if (i == 3) { //Calle 51 with big comercial activity
                            this.edgesList.add(new Edge( //West to East Edge
                                    this.nodeArray[i][j], //Initial
                                    this.nodeArray[i][j + 1], //Final
                                    10
                            )
                            );
                        } else {
                            this.edgesList.add(new Edge( //West to East Edge
                                    this.nodeArray[i][j], //Initial
                                    this.nodeArray[i][j + 1], //Final
                                    5
                            )
                            );
                        }
                    }
                }
            }
        }
        //Javier's house Calle 54 - 0 = 54 / Carrera 11 + 3 = 14
        this.javierInitalNode = this.nodeArray[0][3];
        //Andreina's house Calle 54 - 2 = 52 / Carrera 11 + 2 = 13
        this.andreinaInitialNode = this.nodeArray[2][2];
    }

    //Set an Encounter Point
    public void setTerminalNode(int calle, int carrera) {
        this.terminalNode = this.nodeArray[calle][carrera];
    }

    //Get the real paths
    public Stack<Node> getJavierRealPath() {
        return javierRealPath;
    }

    public Stack<Node> getAndreinaRealPath() {
        return andreinaRealPath;
    }

    //Clean for running Dijkstra Algorithm
    public void prepareForDijkstra() {
        for (int i = 0; i < 5; i++) { //Clean nodes and set dx
            for (int j = 0; j < 5; j++) {
                this.nodeArray[i][j].setIsVisitedInDepth(false);
                this.nodeArray[i][j].setIsInAndreinaPath(false);
                this.nodeArray[i][j].setIsInJavierPath(false);
                if (i == 0 && j == 3) { //Javier's house
                    this.nodeArray[i][j].setDx1(0);
                    this.nodeArray[i][j].setDx2(Double.POSITIVE_INFINITY);
                } else if (i == 2 && j == 2) { //Andreina's house
                    this.nodeArray[i][j].setDx1(Double.POSITIVE_INFINITY);
                    this.nodeArray[i][j].setDx2(0);
                } else { //Every other node
                    this.nodeArray[i][j].setDx1(Double.POSITIVE_INFINITY);
                    this.nodeArray[i][j].setDx2(Double.POSITIVE_INFINITY);
                }
            }
        }
        for (int i = 0; i < this.edgesList.size(); i++) { //Clean edges
            this.edgesList.get(i).setIsInAndreinaPath(false);
            this.edgesList.get(i).setIsInJavierPath(false);
        }
    }

    //Prepare for the second Dijkstra
    public void prepareForSecondDijkstraForJavier() {

        //Find Andreina's Real Path
        this.andreinaRealPath = this.findRealPath(this.andreinaInitialNode, true);

        //Final cleaning before the second run of the algorithm
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!this.andreinaRealPath.contains(this.nodeArray[i][j])) {
                    this.nodeArray[i][j].setIsInAndreinaPath(false); //Clean node that isn't in Andreina's Real Path
                }
                this.nodeArray[i][j].setIsVisitedInDepth(false); //Clean for search of the second path
            }
        }

        this.runDijkstraSecondForJavier();
    }

    public void prepareForSecondDijkstraForAndreina() {
        //Find Javier's real path
        this.javierRealPath = this.findRealPath(this.javierInitalNode, false);
        //Final clean before second run
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!this.javierRealPath.contains(this.nodeArray[i][j])) {
                    this.nodeArray[i][j].setIsInJavierPath(false); //Clean node that isn't in Javiers Real Path
                }
                this.nodeArray[i][j].setIsVisitedInDepth(false); //Clean for search of the second path
            }
        }

        this.runDijkstraSecondForAndreina();
    }

    //Find the real path depending on the passed node
    private Stack<Node> findRealPath(Node n, boolean forAndreina) {
        //Run a depth first search to find the path from the encounter point to Andreina's house
        Stack<Node> potentialPath = new Stack<>(); //Stack for the depth first search
        //The stack will have the path

        System.out.println("Find the real path");

        potentialPath.push(this.terminalNode);//Push the terminal node into the stack
        //The depth search will stop when the passed node house is pushed into the stack
        //Aux List to get predecesors that haven been visited from the node that's being evaluated
        LinkedList<Node> predecessors;

        while (potentialPath.peek() != n) {
            //Mark the top of the stack as visited by this algorithm
            potentialPath.peek().setIsVisitedInDepth(true);
            predecessors = this.getPredecessorsNotVisitedOfNode(potentialPath.peek(), forAndreina);
            if (predecessors.isEmpty()) {
                //If there are no predecessors that hasnt been visited pop the stack
                potentialPath.pop();
            } else {
                //If there are predecessors that hasnt been visited push the first of them to the stack
                potentialPath.push(predecessors.getFirst());
                //If this one happens to be the starting node for Andreina the algorithm will stop
            }
        }

        return potentialPath;
    }

    //Get predecessors of a node that haven been visited by the depth first search and are in on of both trees
    private LinkedList<Node> getPredecessorsNotVisitedOfNode(Node n, boolean forAndreina) {
        LinkedList<Node> predecessors = new LinkedList();

        for (int i = 0; i < 5; i++) { //Search in al the nodes
            for (int j = 0; j < 5; j++) {
                Edge e = this.findEdge(n, this.nodeArray[i][j]);
                if (forAndreina) {
                    if (e != null && (e.isIsInAndreinaPath())) {
                        //If both nodes are adjacent and the edge is in the path
                        if ((this.nodeArray[i][j].isIsInAndreinaPath())
                                && !this.nodeArray[i][j].isIsVisitedInDepth()) {  //If the node is in one of both paths and it hasnt been visited in the depth search
                            //Add it to the aux List
                            System.out.println("Fans");
                            predecessors.add(this.nodeArray[i][j]);
                        }
                    }
                } else {
                    if (e != null && (e.isIsInJavierPath())) {
                        //If both nodes are adjacent and the edge is in Javier's the path
                        if ((this.nodeArray[i][j].isIsInJavierPath())
                                && !this.nodeArray[i][j].isIsVisitedInDepth()) {  //If the node is in one of both paths and it hasnt been visited in the depth search
                            //Add it to the aux List
                            predecessors.add(this.nodeArray[i][j]);
                        }
                    }
                }

            }
        }

        return predecessors; //Return the list
    }

    //Run Dijkstra Algorithm with priority for Javier
    public void runDijkstraFirstForJavier() {
        Node y = this.javierInitalNode; //Auxiliar node
        Node nextY = null; //Next candidate to be Y
        System.out.println("Terminal Calle" + this.terminalNode.getCalle() + " Carrera " + this.terminalNode.getCarrera());
        while (!this.terminalNode.isIsInJavierPath()) { //While Javier hasnt reached the encounter point
            System.out.println("Colored Calle " + y.getCalle() + " Carrera " + y.getCarrera());
            y.setIsInJavierPath(true); //Set y in javiers path
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!this.nodeArray[i][j].isIsInJavierPath()) { //For each node that isnt in Javier's path
                        Edge e = this.findEdge(y, this.nodeArray[i][j]); //Auxiliar edge for calcs
                        if (e != null) { //If the edge exists continue with calcs
                            this.nodeArray[i][j].setDx1( //Get minimal dx
                                    this.getMin(this.nodeArray[i][j].getDx1(), y.getDx1() + e.getTime())
                            );
                        }
                        if (nextY == null) { //Check if candidate for y doesnt exists
                            nextY = this.nodeArray[i][j]; //Change the next candidate for beign Y
                        } else if ( //Check if the actual candidates has a bigger dx
                                nextY.getDx1() > this.nodeArray[i][j].getDx1()) {
                            nextY = this.nodeArray[i][j]; //Change candidates
                        }
                    }
                }
            }
            if (nextY == null) { //Verify is the graph is not connected
                System.out.println("Something wrong happened, the graph has more than one component");
                break;
            } else {
                Edge e = null; //Find edge to color;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        //For each colored node
                        if (this.nodeArray[i][j].isIsInJavierPath()) {
                            //Find edge
                            Edge auxEdge = this.findEdge(this.nodeArray[i][j], nextY);
                            if (auxEdge != null) {
                                //If the edge exists
                                if (auxEdge.getTime() + this.nodeArray[i][j].getDx1()
                                        == nextY.getDx1()) {
                                    //If the weight of the paths concide
                                    e = auxEdge; //This is the edge
                                    //Multiple paths will erase previous posible paths
                                }
                            }
                        }
                    }
                }

                System.out.println("Colored edge from "
                        + "Calle " + e.getNodeEnd1().getCalle() + " Carrera " + e.getNodeEnd1().getCarrera()
                        + " to Calle " + e.getNodeEnd2().getCalle() + " Carrera " + e.getNodeEnd2().getCarrera());
                e.setIsInJavierPath(true); //Color the edge
                y = nextY; //Next y to calc
                nextY = null; //Nullify for new candidates
            }
        }
        System.out.println("Javier's time to get to (Calle " + this.terminalNode.getCalle()
                + " - Carrera " + this.terminalNode.getCarrera() + "): " + this.terminalNode.getDx1());
        System.out.println("");
    }

    public void runDijkstraSecondForJavier() {
        Node y = this.javierInitalNode; //Auxiliar node
        Node nextY = null; //Next candidate to be Y
        System.out.println("Terminal Calle" + this.terminalNode.getCalle() + " Carrera " + this.terminalNode.getCarrera());//prints encounter point
        while (!this.terminalNode.isIsInJavierPath()) { //While Javier hasnt reached the encounter point
            System.out.println("Colored Calle " + y.getCalle() + " Carrera " + y.getCarrera());
            y.setIsInJavierPath(true); //Set y in javiers path
            for (int i = 0; i < 5; i++) {//From north to south
                for (int j = 0; j < 5; j++) {//From east to west
                    if ((!this.nodeArray[i][j].isIsInJavierPath() && !this.nodeArray[i][j].isIsInAndreinaPath())
                            || this.nodeArray[i][j] == this.terminalNode) {
                        //For each node that isnt in Javier's path and, isnt in Andreina's path or is the terminal node
                        Edge e = this.findEdge(y, this.nodeArray[i][j]); //Auxiliar edge for calcs
                        if (e != null) { //If the edge exists continue with calcs
                            this.nodeArray[i][j].setDx1( //Get minimal dx
                                    this.getMin(this.nodeArray[i][j].getDx1(), y.getDx1() + e.getTime())
                            );
                        }
                        if (nextY == null) { //Check if candidate for y doesnt exists
                            nextY = this.nodeArray[i][j]; //Change the next candidate for beign Y
                        } else if ( //Check if the actual candidates has a bigger dx
                                nextY.getDx1() > this.nodeArray[i][j].getDx1()) {
                            nextY = this.nodeArray[i][j]; //Change candidates

                        }
                    }
                }
            }
            if (nextY == null) { //Verify is the graph is not connected
                System.out.println("Something wrong happened, the graph has more than one component");
                break;
            } else {
                Edge e = null; //Find edge to color;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        //For each colored node
                        if (this.nodeArray[i][j].isIsInJavierPath()) {
                            //Find edge
                            Edge auxEdge = this.findEdge(this.nodeArray[i][j], nextY);
                            if (auxEdge != null) {
                                //If the edge exists
                                if (auxEdge.getTime() + this.nodeArray[i][j].getDx1()
                                        == nextY.getDx1()) {
                                    //If the weight of the paths concide
                                    e = auxEdge; //This is the edge
                                    //Multiple paths will erase previous posible paths
                                }
                            }
                        }
                    }
                }
                if (e != null) {
                    e.setIsInJavierPath(true); //Edge enters the path
                    y = nextY; //Next y to use
                    nextY = null; //Clean candidate
                } else {
                    System.out.println("Something awful");
                }
            }
        }
        System.out.println("Javier's time to get to (Calle " + this.terminalNode.getCalle()
                + " - Carrera " + this.terminalNode.getCarrera() + "): " + this.terminalNode.getDx1());
        System.out.println("");

        //Find Javier's real path
        this.javierRealPath = this.findRealPath(this.javierInitalNode, false);

    }

    //Run Dijkstra Algorithm with priority for Andreina
    public void runDijkstraFirstForAndreina() {
        Node y = this.andreinaInitialNode; //Auxiliar node
        Node nextY = null; //Next candidate to be Y
        System.out.println("Terminal Calle" + this.terminalNode.getCalle() + " Carrera " + this.terminalNode.getCarrera());
        while (!this.terminalNode.isIsInAndreinaPath()) { //While Andreina hasnt reached the encounter point
            System.out.println("Colored Calle " + y.getCalle() + " Carrera " + y.getCarrera());
            y.setIsInAndreinaPath(true); //Set y in Andreina's path
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!this.nodeArray[i][j].isIsInAndreinaPath()) { //For each node that isnt in Andreina's path
                        Edge e = this.findEdge(y, this.nodeArray[i][j]); //Auxiliar edge for calcs
                        if (e != null) { //If the edge exists continue with calcs
                            this.nodeArray[i][j].setDx2( //Get minimal dx
                                    this.getMin(this.nodeArray[i][j].getDx2(), y.getDx2() + e.getTime())
                            );
                        }
                        if (nextY == null) { //Check if candidate for y doesnt exists
                            nextY = this.nodeArray[i][j]; //Change the next candidate for beign Y
                        } else if ( //Check if the actual candidates has a bigger dx
                                nextY.getDx2() > this.nodeArray[i][j].getDx2()) {
                            nextY = this.nodeArray[i][j]; //Change candidates
                        }
                    }
                }
            }
            if (nextY == null) { //Verify is the graph is not connected
                System.out.println("Something wrong happened, the graph has more than one component");
                break;
            } else {
                Edge e = null; //Find edge to color;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        //For each colored node
                        if (this.nodeArray[i][j].isIsInAndreinaPath()) {
                            //Find edge
                            Edge auxEdge = this.findEdge(this.nodeArray[i][j], nextY);
                            if (auxEdge != null) {
                                //If the edge exists
                                if (auxEdge.getTime() + this.nodeArray[i][j].getDx2()
                                        == nextY.getDx2()) {
                                    //If the weight of the paths concide
                                    e = auxEdge; //This is the edge
                                    //Multiple paths will erase previous posible paths
                                }
                            }
                        }
                    }
                }

                System.out.println("Colored edge from "
                        + "Calle " + e.getNodeEnd1().getCalle() + " Carrera " + e.getNodeEnd1().getCarrera()
                        + " to Calle " + e.getNodeEnd2().getCalle() + " Carrera " + e.getNodeEnd2().getCarrera());
                e.setIsInAndreinaPath(true); //Color the edge
                y = nextY; //Next y to calc
                nextY = null; //Nullify for new candidates
            }
        }
        System.out.println("Andreina's time to get to (Calle " + this.terminalNode.getCalle()
                + " - Carrera " + this.terminalNode.getCarrera() + "): " + this.terminalNode.getDx2());
        System.out.println("");
    }

    public void runDijkstraSecondForAndreina() {
        Node y = this.andreinaInitialNode; //Auxiliar node
        Node nextY = null; //Next candidate to be Y
        System.out.println("Terminal Calle" + this.terminalNode.getCalle() + " Carrera " + this.terminalNode.getCarrera());
        while (!this.terminalNode.isIsInAndreinaPath()) { //While Andreina hasnt reached the encounter point
            System.out.println("Colored Calle " + y.getCalle() + " Carrera " + y.getCarrera());
            y.setIsInAndreinaPath(true); //Set y in Andreina's path
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((!this.nodeArray[i][j].isIsInAndreinaPath() && !this.nodeArray[i][j].isIsInJavierPath())
                            || this.nodeArray[i][j] == this.terminalNode) {
                        //For each node that isnt in Andreina's path and, isnt in Javier's path or is the terminal node
                        Edge e = this.findEdge(y, this.nodeArray[i][j]); //Auxiliar edge for calcs
                        if (e != null) { //If the edge exists continue with calcs
                            this.nodeArray[i][j].setDx2( //Get minimal dx
                                    this.getMin(this.nodeArray[i][j].getDx2(), y.getDx2() + e.getTime())
                            );
                        }
                        if (nextY == null) { //Check if candidate for y doesnt exists
                            nextY = this.nodeArray[i][j]; //Change the next candidate for beign Y
                        } else if ( //Check if the actual candidates has a bigger dx
                                nextY.getDx2() > this.nodeArray[i][j].getDx2()) {
                            nextY = this.nodeArray[i][j]; //Change candidates
                        }
                    }
                }
            }
            if (nextY == null) { //Verify is the graph is not connected
                System.out.println("Something wrong happened, the graph has more than one component");
                break;
            } else {
                Edge e = null; //Find edge to color;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        //For each colored node
                        if (this.nodeArray[i][j].isIsInAndreinaPath()) {
                            //Find edge
                            Edge auxEdge = this.findEdge(this.nodeArray[i][j], nextY);
                            if (auxEdge != null) {
                                //If the edge exists
                                if (auxEdge.getTime() + this.nodeArray[i][j].getDx2()
                                        == nextY.getDx2()) {
                                    //If the weight of the paths concide
                                    e = auxEdge; //This is the edge
                                    //Multiple paths will erase previous posible paths
                                }
                            }
                        }
                    }
                }
                if (e != null) {
                    e.setIsInAndreinaPath(true); //Edge enters the path
                    y = nextY; //Next y to use
                    nextY = null; //Clean candidate
                } else {
                    System.out.println("Something awful");
                }
            }
        }

        System.out.println("Andreina's time to get to (Calle " + this.terminalNode.getCalle()
                + " - Carrera " + this.terminalNode.getCarrera() + "): " + this.terminalNode.getDx2());
        System.out.println("");

        //Find Andreina's real path
        this.andreinaRealPath = this.findRealPath(this.andreinaInitialNode, true);

    }

    //Find edge (i,j)
    public Edge findEdge(Node nodeX, Node nodeY) {
        for (int k = 0; k < this.edgesList.size(); k++) {
            if ((this.edgesList.get(k).getNodeEnd1() == nodeX && this.edgesList.get(k).getNodeEnd2() == nodeY)
                    || (this.edgesList.get(k).getNodeEnd1() == nodeY && this.edgesList.get(k).getNodeEnd2() == nodeX)) {
                //If the edge exists
                return this.edgesList.get(k);
            }
        }
        return null; //Edge doesnt exist
    }

    //Get the min of two numbers
    public double getMin(double a, double b) {
        if (a < b) {
            return a;
        }
        return b;
    }

}
