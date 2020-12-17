package edu.sjsu.Views;
import edu.sjsu.Models.LineItem;

public interface ListFormatter<E>
{
   /**
    * Formats the header of the object.
    * @param obj User
    * @return the object header
    */
   String formatHeader(E obj);

   /**
    * Formats a line item of the object.
    * @param item LineItem
    * @return the formatted line item
    */
   String formatLineItem(LineItem item);

   /**
    * Formats the footer of the object.
    * @return the object footer
    */
   String formatFooter();
}
