package edu.sjsu.Views;
import edu.sjsu.Models.LineItem;

public interface ListFormatter<E>
{
   /**
      Formats the header of the invoice.
      @return the invoice header
   */
   String formatHeader(E obj);

   /**
      Formats a line item of the invoice.
      @return the formatted line item
   */
   String formatLineItem(LineItem item);

   /**
      Formats the footer of the invoice.
      @return the invoice footer
   */
   String formatFooter();
}
