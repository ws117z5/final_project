package edu.sjsu.Models;

/**
   A line item
   reusing this just for fun
*/
public interface LineItem<E>
{
   /**
      Gets the price of this line item.
      @return the price
   */
   E getInstance();
   /**
      Gets the description of this line item.
      @return the description
   */   
   String toString();
}
