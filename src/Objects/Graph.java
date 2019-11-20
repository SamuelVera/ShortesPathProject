/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.LinkedList;

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
            for (int j = 0; j < 4; j++) { //Carreras 11-12-13-14
                if (i == 0) { //Calle 50
                    this.edgesList.add(new Edge( //West to East Edge
                            this.nodeArray[i][j], //Initial
                            this.nodeArray[i][j + 1], //Final
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
        //Javier's house Calle 54 - 0 = 54 / Carrera 11 + 3 = 14
        this.javierInitalNode = this.nodeArray[0][3];
        //Andreina's house Calle 54 - 2 = 52 / Carrera 11 + 2 = 13
        this.andreinaInitialNode = this.nodeArray[2][2];
    }

    //Set an Encounter Point
    public void setTerminalNode(int calle, int carrera) {
        this.terminalNode = this.nodeArray[calle][carrera];
    }

    //Clean for running Dijkstra Algorithm
    public void prepareForDijkstra() {
        for (int i = 0; i < 5; i++) { //Clean nodes and set dx
            for (int j = 0; j < 5; j++) {
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

    //Sets all vertices distances = infinity except for the source vertex, sets the source distance = 0
    public void prepareForSecondDijkstraForJavier() {
        for (int i = 0; i < 5; i++) {//cleans nodes and sets dx only for Javier
            for (int j = 0; j < 5; j++) {
                this.nodeArray[i][j].setIsInJavierPath(false);
                if (i == 0 && j == 3) {
                    this.nodeArray[i][j].setDx1(0);
                } else {
                    this.nodeArray[i][j].setDx1(Double.POSITIVE_INFINITY);
                }
            }
        }

        for (int i = 0; i < this.edgesList.size(); i++) {
            this.edgesList.get(i).setIsInJavierPath(false);
        }

        //save the shortest path's nodes in a arraylist so the rest of the non-visited nodes can be clear and used by Javier
        LinkedList andreinaPath = new LinkedList<>();//aux linkedList for nodes in the shortest path
        Node y = this.terminalNode;
        andreinaPath.add(y);
        while (y != this.andreinaInitialNode) {//while the aux node y isn't Andreina's house
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Edge e = this.findEdge(y, this.nodeArray[i][j]);//find an edge between aux node y and the current node
                    if (e != null && this.nodeArray[i][j].isIsInAndreinaPath()) {//if there is an edge and the current node is in Andreina's path
                        y = this.nodeArray[i][j];//make the current node the aux y
                        andreinaPath.addFirst(y);//and add this node to Andreina's path
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.nodeArray[i][j].isIsInAndreinaPath() && !andreinaPath.contains(this.nodeArray[i][j])) {
                    this.nodeArray[i][j].setIsInAndreinaPath(false);
                }
            }
        }
        this.runDijkstraSecondForJavier();
    }

    public void prepareForSecondDijkstraForAndreina() {
        for (int i = 0; i < 5; i++) {//cleans nodes and sets dx only for Andreina
            for (int j = 0; j < 5; j++) {
                this.nodeArray[i][j].setIsInAndreinaPath(false);
                if (i == 0 && j == 3) {
                    this.nodeArray[i][j].setDx2(0);
                } else {
                    this.nodeArray[i][j].setDx2(Double.POSITIVE_INFINITY);
                }
            }
        }

        for (int i = 0; i < this.edgesList.size(); i++) {
            this.edgesList.get(i).setIsInAndreinaPath(false);
        }

        //save the shortest path's nodes in a arraylist so the rest of the non-visited nodes can be clear and used by Javier
        LinkedList<Node> javierPath = new LinkedList<>();//aux linkedList for nodes in the shortest path
        Node y = this.terminalNode;
        javierPath.add(y);//last node pushed into nodeList
        while (y != this.javierInitalNode) {//while the aux node y isn't Andreina's house
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Edge e = this.findEdge(y, this.nodeArray[i][j]);//find an edge between aux node y and the current node
                    if (e != null && this.nodeArray[i][j].isIsInJavierPath()) {//if there is an edge and the current node is in Javier's path
                        y = this.nodeArray[i][j];//make the current node the aux y
                        javierPath.addFirst(y);//and add this node to Javier's path
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.nodeArray[i][j].isIsInJavierPath() && !javierPath.contains(this.nodeArray[i][j])) {
                    this.nodeArray[i][j].setIsInJavierPath(false);
                }
            }
        }
        this.runDijkstraSecondForAndreina();
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

        this.prepareForSecondDijkstraForAndreina();
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
                    if (!this.nodeArray[i][j].isIsInJavierPath() && !this.nodeArray[i][j].isIsInAndreinaPath()) { //For each node that isnt in Javier's path
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

        this.prepareForSecondDijkstraForJavier();
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
                    if (!this.nodeArray[i][j].isIsInAndreinaPath() && !this.nodeArray[i][j].isIsInJavierPath()) { //For each node that isnt in Andreina's path
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
