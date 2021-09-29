/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }
    
    

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    
    void swap(int a, int b)                     
    {	Node<AnyType> p;
    	Node<AnyType> q;
    	Node<AnyType> tmp;
        if( a >= size() || b >= size() )
            throw new IndexOutOfBoundsException( "Please enter the nummber within size limit");
        
        p = getNode( a );
        q = getNode( b );
        
             
        p.prev.next = q;                              //modifying links
        q.prev.next = p;
        tmp = p.next;                                 //nodes swapping
        p.next = q.next;                              
        q.next = tmp;
              
    }
    
    public void shift(int p) {
		  if(p == 0) {                         //No List
			  return;
		  }
		  
		  if( p >= size() )
	            throw new IndexOutOfBoundsException( "Please enter the nummber within size limit");
		  
		  else if(p > 0){
		this.endMarker.prev.next = this.beginMarker.next;        // Get the links 
		this.beginMarker.next.prev = this.endMarker.prev;
		
		  Node<AnyType> begin = getNode(p);                     // Get the starting node to shift
		  Node <AnyType> end = getNode(p-1);
		   
		  // Modifying the list 
		  
		  this.beginMarker.next = begin;                        //Set the starting node as head
		  begin.prev = this.beginMarker;
		  
		  this.endMarker.prev = end;                           //Set the tail node (node earlier to shift node)
		  end.next = this.endMarker;
		  
		  }
		  else {
			  this.endMarker.prev.next = this.beginMarker.next;              // Code for shifting in reverse direction
				this.beginMarker.next.prev = this.endMarker.prev;
				
				  Node<AnyType> begin = getNode(this.size()+ p);               // now we need to shift in reverse direction
				  Node <AnyType> end = getNode(this.size()+p-1);
				  
				  this.beginMarker.next = begin;
				  begin.prev = this.beginMarker;
				  
				  this.endMarker.prev = end;
				  end.next = this.endMarker;
		  }
	  }

    public void erase(int z, int x)
    {
    	
    	for(int a=0;a<x;a++) {
 

    	remove( getNode( z ) );              //Get the node and remove till the integer parameter given
    	}

   	return ;	
    
    }
    
    
	
	  public void insertlist(MyLinkedList<AnyType> l, int p) {
		   {   
		        int i = 0;
		        while( i < l.size() )
		            {
		            add( p, l.beginMarker.next.data);        //Add the linked list with the given node
		            l.remove(l.getNode(0));                    
		            p++;                                       //Increment them one by one
		            }
		        
		    }
	  	
	  
	  }
	  
	  
	 
	 
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
    	MyLinkedList<Integer> lst1 = new MyLinkedList<>( );

        for( int i = 0; i < 9; i++ )
                lst1.add( i );
        System.out.println("List for swapping : " + lst1 );
        System.out.print("Swap nodes        : ");
        lst1.swap(6,7);
        System.out.println( lst1 );
        
        System.out.println( "\n" );
        
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        for( int i = 0; i < 4; i++ )
            lst2.add( i );
        System.out.println("List for shifting : " + lst2 );
        lst2.shift(2);
        System.out.print( "Executed Shift    : "+lst2 );
        
        System.out.println( "\n" );
        
        
       
        MyLinkedList<Integer> lst3 = new MyLinkedList<>( );
        
        for( int i = 0; i < 10; i++ )
                lst3.add( i );
        System.out.println("List for erasing : " + lst3 );
        lst3.erase(2,3);
        System.out.println("Erased list      : " + lst3 );
        
        System.out.println( "\n" );
        
        MyLinkedList<Integer> lst4 = new MyLinkedList<>( );
        for( int i = 0; i < 4; i++ )
            lst4.add( i );
        MyLinkedList<Integer> lst5 = new MyLinkedList<>( );
        for( int i = 0; i < 4; i++ )
            lst5.add( i+10 );
        System.out.println( "List where will be modified :"+lst4 );
        System.out.println( "List needs to be inserted   :" + lst5 );
        lst4.insertlist(lst5, 2);
        System.out.println("Executed insert             :"+ lst4 );
        
        /*
    	MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( 2*i );
        System.out.println( lst );
      
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
                
                
        
      
        
         }*/
        
        


    }
}