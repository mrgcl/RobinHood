/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * Table components of the Robin Hood hashing
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class HashTable
{   /**
     * Array that stores the nodes
     */
    private  Node[] table;
    /**
     * Size of the hash table
     */
    private int size;
    /**
     * Number of inserted elements
     */
    private int inserted=0;
    /**
     * Constructor that creates the container array with given size
     * @param given size of the array to be created
     */
    public HashTable(int given)
    {
        table = new Node[given];
        size = given;
    }
    
    public void insert(int x){
    	Node node = new Node();
    	node.setValue(x);
    	insert(node);
    }

    /**
     * Method for inserting nodes into hash table, this method checks the
     * insertion status, and can call another insert(x) instance if needed.
     * So stack overflow can occur by misuse of this method.
     * @param x node to be inserted in hash table
     * @return true if insertion is successful, otherwise false
     */
    private final boolean insert(Node x)
    {
        int index;
        if(inserted==size)
        {
            System.out.println("Table is full!");
            return false;
        }
        index = x.getTargetLocation(size);
        if(table[index]==null || !table[index].isActive())
        {
            table[index]=x;
            inserted++;
            System.out.printf("Node %d Inserted @ room %d - collision count for this node is %d\r\n",x.getValue(),index,x.getCollisionCount());
            return true;
        }
        else if(table[index].getCollisionCount()<x.getCollisionCount())
        {
            table[index].collisionIncrement();
            x.collisionIncrement();
            System.out.printf("Node %d collided with node %d while inserting - " +
                    " collision count for the former node is %d\r\nReplacing node %d " +
                    "- collision count for this node is %d\r\n",
                    x.getValue(),table[index].getValue(),
                    x.getCollisionCount(),table[index].getValue(),
                    table[index].getCollisionCount());
            Node previous;
            previous=table[index];
            table[index]=x;
            System.out.printf("Node %d Inserted @ room %d - collision count for this node is %d\r\n",x.getValue(),index,x.getCollisionCount());

            return insert(previous);
        }
        else if(table[index].getCollisionCount()>=x.getCollisionCount())
        {
            x.collisionIncrement();
            System.out.printf("Node %d collided with node %d while inserting - " +
                    " collision count for the former node is %d\r\n",
                    x.getValue(),table[index].getValue(),
                    x.getCollisionCount());
            return insert(x);
        }
        return false;
        
    }
    /**
     * Printing method to standard output,prints each node's value in every room
     * to standard output, prints "empty" if the room is filled with any node.
     * Not in final mode, testing purposes only
     */
    public void printTable()
    {
        for(int i=0;i<table.length;i++)
        {
            if(table[i]==null)
                System.out.println("empty");
            else System.out.println(table[i].getValue());
        }
    }
    
    public int searchTest(int value){
    	int probes = 0;
    	Node aux = new Node();
    	aux.setValue(value);
    	int pos = aux.getTargetLocation(size);
    	while(true){
    		probes += 1;
    		if(table[pos] == null) return -1;
    		else if(!table[pos].isActive()) return -2;
    		else if(probes > table[pos].getProbeCount()) return -3;      		
    		else if(table[pos].getValue() == value) return probes;
    		pos += 1;
    	}
    }
    
    public int search(int value){
    	int probes = 0;
    	Node aux = new Node();
    	aux.setValue(value);
    	int pos = aux.getTargetLocation(size);
    	while(true){
    		probes += 1;
    		if(table[pos] == null) return -1;
    		else if(!table[pos].isActive()) return -1;
    		else if(probes > table[pos].getProbeCount()) return -1;
    		else if(table[pos].getValue() == value) return pos;
    		pos =+ 1;
       	}
    }
    
    public void delete(int value){
    	int pos = search(value);
    	if(pos != -1){
    		table[pos].deactivate();
    	}
    }
    
    public void deleteAll(){
    	for(Node n: table){
    		if(n == null) continue;
    		n.deactivate();
    	}
    	inserted = 0;
    }
    
    public Node[] getTable(){
    	return table;
    }
}
