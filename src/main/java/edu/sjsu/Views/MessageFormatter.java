package edu.sjsu.Views;

import edu.sjsu.Controllers.MainController;
import edu.sjsu.Controllers.MessageController;
import edu.sjsu.Models.*;

public class MessageFormatter implements ListFormatter<User> {
   public String formatHeader(User user)
   {
      return user.getName() + " Messages \n\n\n";
   }

   public String formatLineItem(LineItem item)
   {
      //total += item.getPrice();
      //return (String.format(
            //"%s: $%.2f\n",item.toString(),item.getPrice()));
            if(((Message) item).getUser() == MainController.getUser()) {
               return item.toString() + "\n";
            } else {
               return "\t\t\t\t\t\t\t\t" + item.toString() + "\n";
            }
   }

   public String formatFooter()
   {
      //return (String.format("\n\nTOTAL Users: $%.2f\n", total));
      return "";
   }
}