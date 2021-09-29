import java.util.LinkedList;
import java.util.Queue;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
           throw new UnderflowException( );
        return findMin( root ).element;
    }
    

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
           throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    
 
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */

    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }
        

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
   
    
    public int nodeCount(BinaryNode<AnyType> t)      //////working of nodeCount
    {
        if(t == null)
            return 0;
        else 
            return 1 + nodeCount(t.left) + nodeCount(t.right);     // counting all
    }
    
    
    public boolean isFull(BinaryNode<AnyType> t)      ////checking for isfull
    {
      if ((t.right == null && t.left == null))      //////see if both pointers null
         return true;
      else
      {     
         if ((t.right != null && t.left != null))   //////see if any one of its pointer is null
         {
        	 if (isFull(t.right) && isFull(t.left))     ////now recursively see for other child node
               return true;
            else
               return false; 
         }	 
         else
            return false;   
      }        
    }
    public boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> p) 
    {
      
        if (t == null && p == null)  // both are null
            {
            return true;
            }
        if ((t == null && p != null) || (t != null && p == null))   // one of them is null
        {
        return false;
        }
        return compareStructure(t.left, p.left) && compareStructure(t.right, p.right);  ////recursively see cases of chil nodes
    }
    public boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> p)    ////to check if isequal
    {
      
     if(t== null && p ==null)                             //// check if both null
    	 return true;
     else  {
    	  if (t != null && p != null)                ////if not then compare child nodes
    	 {if(t.element == p.element) {
    		 return (equals(t.right, p.right) && equals(t.left , p.left));
    	 }
    	 else
    		 return false;}
    	  else return false;
    	 
     }
    }
    public BinaryNode<AnyType> copy(BinaryNode<AnyType> t, BinaryNode<AnyType> p)   ////to copy a tree
    {
      if (p!=null)             //check if not null
      {
         t=new BinaryNode<>(p.element);     ////initializing the new tree
         t.left = p.left;                      ////creating copy of nodes
         t.right = p.right;
         t.left=copy(t.left,p.left);            /////recursively copy for child nodes
         t.right=copy(t.right,p.right);
      }
      return t;
    }
    
    public BinaryNode<AnyType> mirror(BinaryNode<AnyType> p, BinaryNode<AnyType> t)   ////to create a mirror
    {
    	if(p==null)                ///check if null
    		return p;
    	else {
    		t = new BinaryNode<>(p.element);   ///same as copy, only modification is each child node is swapped
    		t.left = p.right;
    		t.right = p.left;
    		t.left = mirror(t.left, p.right);
    		t.right =mirror(t.right, p.left);
    		return t;
    	}
    }
    
    public boolean ismirror(BinaryNode<AnyType> t, BinaryNode<AnyType> p)  ///check if mirror
    {  
    	if (t==null && p ==null)   ///check if null
    		return true;
    	else if((t==null && p!=null ) || t!=null && p==null)   ///check if not structured
    		return false;
    	else {
    		if(t.element == p.element) {       ///check if elements are mirrorred with respective elements
    			return (ismirror(t.right, p.left) && ismirror(t.left, p.right));
    		}
    		else
    			return false;
    	}
    }
    
   public void printLevels(BinaryNode<AnyType> t) {          ///print by levels
	   if(t == null)
		   return ;
	   else {
		   Queue<BinaryNode<AnyType>> q = new  LinkedList<>();   ////using queue concept
		   q.add(t);                                             ////adding if child is present
		   while(!q.isEmpty()) {                         /////if child not there then remove the element
			   BinaryNode<AnyType> pr = q.peek();
			   q.remove();
			   System.out.print(pr.element);
			   System.out.print("\t");
			   
			   if(pr.left!=null) {
				   q.add(pr.left);
			   }
			   if(pr.right!=null)
				   q.add(pr.right);
		   }
		   return;
	   }
   }
   
	
	 public void rotateRight(AnyType e) 	////parameterizing the function with single argument 
	 { 
		 root=rotateRight(root,e,false);    ////calling the function 
	 }    	 
   private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t,AnyType e,boolean present)
   {
     if (present == false)                       ///if false then continue (works as a flag)
     {
        if (t!=null)                          ////if not empty then continue 
        {
           if (e.compareTo(t.element) < 0)               ////recursively rotating
              t.left=rotateRight(t.left,e,false);
           else if (e.compareTo(t.element) > 0)
              t.right=rotateRight(t.right,e,false);
           else
              t=rotateRight(t,e,true);             ////flag to stop
              
           return t;  
        }   
        else
           return null;                     
     } 
     else                                           ////if t is null(no child nodes)
     {
        BinaryNode<AnyType> p;                    ////create a node for rotation
        BinaryNode<AnyType> k;
        p=t.left;
        if (p != null)                           ////swapping
        {
           k=p.right;
           t.left=k;
           p.right=t;   
           return p;   
        }
        else
       
           return t;            
     }         
   }
   
   public void rotateLeft(AnyType e)                    /////works similar to right rotation
   {
     root=rotateLeft(root,e,false);
   }    
   private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t,AnyType e,boolean present)
   {
     if (present == false)
     {
        if (t!=null)
        {
           if (e.compareTo(t.element) < 0)
              t.left=rotateLeft(t.left,e,false);
           else if (e.compareTo(t.element) > 0)
              t.right=rotateLeft(t.right,e,false);
           else
              t=rotateLeft(t,e,true);   
              
           return t;  
        }   
        else
           return null;                     
     }
     else
     {
        BinaryNode<AnyType> p;
        BinaryNode<AnyType> k; 
        p=t.right;
        if (p!= null)
        {
           k=p.left;
           t.right=k;
           p.left=t;   
           return p;
        }   
        else
        
           return t;             
     }         
   }
   
       
      /** The tree root. */
    private BinaryNode<AnyType> root;
   


        // Test program
    public static void main( String [ ] args )
    {
//        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
//        final int NUMS = 4000;
//        final int GAP  =   37;
//
//        System.out.println( "Checking... (no more output means success)" );
//
//        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
//            t.insert( i );
//
//        for( int i = 1; i < NUMS; i+= 2 )
//            t.remove( i );
//
//        if( NUMS < 40 )
//            t.printTree( );
//        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
//            System.out.println( "FindMin or FindMax error!" );
//
//        for( int i = 2; i < NUMS; i+=2 )
//             if( !t.contains( i ) )
//                 System.out.println( "Find error1!" );
//
//        for( int i = 1; i < NUMS; i+=2 )
//        {
//            if( t.contains( i ) )
//                System.out.println( "Find error2!" );
//        }
    	 BinarySearchTree<Integer> t = new BinarySearchTree<>();             //create a tree with root = 4, immediate children = 2,6, and so on.
         t.insert(60);
         t.insert(50);
         t.insert(70);
         t.insert(40);
         t.insert(35);
         t.insert(45);
         t.insert(80);
         
         
    	 BinarySearchTree<Integer> p = new BinarySearchTree<>();             //create a tree with root = 4, immediate children = 2,6, and so on.
    	 p.insert(11);
         p.insert(9);
         p.insert(12);
         p.insert(5);
         p.insert(2);
         p.insert(7);
         p.insert(13);

         System.out.print("The node count of tree is: ");

         System.out.println(t.nodeCount(t.root));
         System.out.print("\n");
         
         System.out.print("The tree is whether full or not: ");

         System.out.println(t.isFull(t.root));
         System.out.print("\n");
         
         System.out.println("Check if two trees structure are similar for the given below tree: ");
         t.printLevels(t.root);
         System.out.print("\n");
         p.printLevels(p.root);
         System.out.print("\n");
         System.out.print("Are they similar: ");

         System.out.println(t.compareStructure(t.root, p.root));
        // System.out.print("\n");
 
         
         System.out.print("Are given two trees are equal: ");

         System.out.println(t.equals(t.root, p.root));
        // System.out.print("\n");
         
         
         System.out.print("To check if two tress are Mirror: ");

         System.out.println(t.ismirror(t.root, p.root));
         
         System.out.print("\n");
 
         BinarySearchTree<Integer>cp = new BinarySearchTree<>();   

         
         cp.root = t.copy(t.root, cp.root);
         System.out.println("A tree is create as a copy, now check whether it is equal or not: ");
         System.out.println("The original tree is:");
         t.printLevels(t.root);
         System.out.print("\n");
         System.out.println("The copied tree is:");
         cp.printLevels(cp.root);
         System.out.print("\n");
         System.out.print("Are they equal: ");              
         System.out.println(t.equals(t.root, cp.root));
         System.out.print("\n");      
                
         BinarySearchTree<Integer> mr = new BinarySearchTree<>();             
         
         mr.root = t.mirror(t.root, mr.root);
         System.out.println("Checking for mirror after creating a mirror tree: ");
         System.out.println("The original tree is:");
         t.printLevels(t.root);
         System.out.print("\n");
         System.out.println("The mirrored tree is:");
         mr.printLevels(mr.root);
         System.out.print("\n");
         System.out.print("Are they mirrored: "); 
      
         System.out.println(t.ismirror(t.root, mr.root));
         System.out.print("\n");
         System.out.print("\n");
         
         System.out.println("The original tree is:");
         t.printLevels(t.root);
         System.out.print("\n");
          
         System.out.println("To rotate the original tree right from 60: ");

         cp.rotateRight(60);
         
         cp.printLevels(cp.root);
         System.out.print("\n"); 
         
         System.out.print("\n");
         
         System.out.println("To rotate the output tree left from 50:");

         cp.rotateLeft(50);
         
         cp.printLevels(cp.root);
         System.out.print("\n");
         System.out.print("\n"); 
         System.out.println("After creating both rotation, we must get the orignal tree itself!");
         System.out.print("Check if they are copy: ");

         System.out.println(t.equals(t.root, cp.root));
         System.out.print("\n");

         
         System.out.println("To write through Levels:");
         
         t.printLevels(t.root);
     
    }
}