package edu.sjsu.Views;

import edu.sjsu.Models.*;

public class UserFormatter implements ListFormatter<User> {
   public String formatHeader(User user)
   {
      return "Users\n\n\n";
   }

   public String formatLineItem(LineItem item)
   {
      //total += item.getPrice();
      //return (String.format(
            //"%s: $%.2f\n",item.toString(),item.getPrice()));
            return item.toString() + "\n";
   }

   public String formatFooter()
   {
      //return (String.format("\n\nTOTAL Users: $%.2f\n", total));
      return "";
   }
}