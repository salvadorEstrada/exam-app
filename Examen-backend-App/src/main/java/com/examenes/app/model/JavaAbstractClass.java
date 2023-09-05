package com.examenes.app.model;
import java.util.*;
 abstract class Book{
  String title;
  abstract void setTitle(String s);
     String getTitle(){
         return title;
     }
}

//Write MyBook class here
class MyBook extends Book{
     void setTitle(String s){
         title =s;
     }
}

public class JavaAbstractClass
{
    public static void main(String []args)
    {
     Scanner sc = new Scanner(System.in);
     String title = sc.nextLine();
     MyBook mb = new MyBook();
     mb.setTitle(title);

        System.out.println("The new novel is"+ mb.getTitle());
    }
}


