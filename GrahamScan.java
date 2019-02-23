package Graham;

import java.util.*;

 
public   class GrahamScan {

	
public static final int clockwise=-1,countercl=1,collinear=0;
  


	GrahamScan(){
		
	}
	

  
    public static int Coliniaritate(List<Punct> Puncte) { 
    	if(Puncte.size() <= 2) 
        {  
        	return 1;
        }
          Punct a = Puncte.get(0);
          Punct b = Puncte.get(1);

        for(int i = 2; i < Puncte.size(); i++) {

            Punct c = Puncte.get(i);
           
            if(Orientare(a, b, c) != collinear) {
            	{ 
            	return 0;
            
            	}
            	
            }
        }
         return 1;
    }
    
 

    
    public static List<Punct> ConvexHull(List<Punct> Puncte) throws IllegalArgumentException {
          List<Punct> sorted = new ArrayList<Punct>(sortarePuncte(Puncte));
         	if(sorted.size() < 3) { 
            throw new IllegalArgumentException("O invelitoare convexa este formata din minim 3 puncte.");
        }
         

         	else if(Coliniaritate(sorted)!=0) { 
         
        	throw new IllegalArgumentException("O invelitoare convexa nu poate fi formata doar din puncte coliniare.");
       
        }

        
        Stack<Punct> stack = new Stack<Punct>();
        stack.push(sorted.get(0));
        stack.push(sorted.get(1));
        
         for (int i = 2; i < sorted.size(); i++) {

            Punct prim = sorted.get(i);
            Punct mijloc = stack.pop();
            Punct ultim = stack.peek();

           int ori = Orientare(ultim, mijloc, prim);

            switch(ori) {
                case countercl:
                    stack.push(mijloc);
                    stack.push(prim);
                    break;
                case clockwise:
                    i--;
                    break;
                case collinear:
                    stack.push(prim);
                    break;
            }
        }
       
        stack.push(sorted.get(0));
        return new ArrayList<Punct>(stack);
        
    }
    
    
    public static Punct PunctMinim(List<Punct> Puncte) {

        Punct min = Puncte.get(0);

        for(int i = 1; i < Puncte.size(); i++) {

            Punct aux = Puncte.get(i);

            if(aux.y < min.y || (aux.y == min.y && aux.x < min.x)) {
                min = aux;
            }
        }

        return min;
    }


    public static Set<Punct> sortarePuncte(List<Punct> Puncte) {
     	
    	  Punct min = PunctMinim(Puncte);
      
    	          TreeSet<Punct> set = new TreeSet<Punct>(new Comparator<Punct>() {
           
            public int compare(Punct a, Punct b) {

                if(a == b || a.equals(b)) {
                    return 0;
                }

               
                double thetaA = Math.atan2((long)a.y - min.y, (long)a.x - min.x);
                double thetaB = Math.atan2((long)b.y - min.y, (long)b.x - min.x);
             
                if(thetaA < thetaB) {
                    return -1;
                }
                else if(thetaA > thetaB) {
                    return 1;
                }
                else {
                 //collinear 
                    double distanceA = Math.sqrt((((long)min.x - a.x) * ((long)min.x - a.x)) +
                                                (((long)min.y - a.y) * ((long)min.y - a.y)));
                    double distanceB = Math.sqrt((((long)min.x - b.x) * ((long)min.x - b.x)) +
                                                (((long)min.y - b.y) * ((long)min.y - b.y)));
                     if(distanceA < distanceB) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            }
        });
          
          

        set.addAll(Puncte);

        return set;
    }


    
    
    
    
    public static int Orientare(Punct a, Punct b, Punct c) {

       
        long produsVectorial = ((b.x - a.x) * (c.y - a.y)) - ((b.y - a.y) * (c.x - a.x)) ; 

        if(produsVectorial > 0) {
            
        	return countercl;
        }
        else if(produsVectorial < 0) {
           
        	return clockwise;
        }
        else {
        	return collinear;
        }
    }





}