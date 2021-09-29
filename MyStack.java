import java.util.ArrayList;
import java.util.List; 
import java.util.NoSuchElementException;

class GenericStack<E>{
 private java.util.ArrayList<E> list= new java.util.ArrayList<>();
 

 public int getSize(){
     return list.size();
 }

 public void push(E o){
     list.add(o);
 }
 public E pop(){
     E o=list.get(getSize()-1);
     list.remove(getSize()-1);
     return o;
 }
 public boolean isEmpty(){
     return list.isEmpty();
 }

}
public class MyStack{
 public static void main(String[] args){
    GenericStack<Character> p=new GenericStack<>();
    
    
    String s = "[({}{})]";
   
    
    for(int i = 0; i < s.length(); i++) 
    {
    char x = s.charAt(i);
    if(x == '[' || x == '(' || x == '{' )     // if element is starting, push
        {
        p.push(x);
        }
    else if(x == ']') 
        {
        if( p.getSize() == 0)                                  //if stack is empty and closed brakets 
            {
            System.out.println("No element inside stack.");     
            break;
            }     
        if(p.pop() != '[')                                    //if stack does not have balancing bracket [
            {
            System.out.println("NOT BALANCED.");
            break;
            }
        }
    else if(x == '}') 
        {
        if( p.getSize() == 0)
            {
            System.out.println("No element inside stack.");    //if stack is empty and closed bracket 
            break;
            }     
        if(p.pop() != '{') 
            {
            System.out.println("NOT BALANCED.");                //if stack does not have balancing bracket {
            break;
            }
        }
    else if(x == ')') 
        {
        if( p.getSize() == 0)
            {
            System.out.println("No element inside stack.");     //if stack is empty and closed brace
            break;
            }     
        if(p.pop() != '(') 
            {
            System.out.println("NOT BALANCED.");                //if stack does not have balancing bracket (
            break;
            }
        }
    else
        {
        throw new NoSuchElementException(x+" Please use characters from the choice ---> (,),{,},[,]");     //invalid
        }
   
    if(p.getSize() == 0)                                       //if at end stack is empty, balanced
        {
        System.out.println("BALANCED");
        }
    }
 }
}