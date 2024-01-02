package MyPhoneDict.DataStructures;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import MyPhoneDict.Enums.Gender;
import MyPhoneDict.Enums.PhoneType;

public class AdressList implements Serializable {
	
	   private static final long serialVersionUID = 1L;
	   public ListNode firstNode;
	    public ListNode currentNode;
	    

	    //Constructor
	    public AdressList() {
	        this.firstNode = null;
	        this.currentNode = null;
	       
	    }

	    //Method used to check if the list is empty
	    public boolean isEmpty() {
			return firstNode == null;
	    }
	    
	    public void addToFront(String name, String surname, String phoneNumber, PhoneType phoneType, Gender sex, String email, String BirthDay, String address){
	        ListNode newFrontNode = new ListNode( name,  surname,  phoneNumber,  phoneType,  sex,  email,  BirthDay,address);
	        if (firstNode == null) {
	            this.firstNode = newFrontNode;
	        } else {
	            currentNode = newFrontNode;
	            currentNode.setNext(this.firstNode);
	            this.firstNode = currentNode;
	        }
	        currentNode = firstNode;
	    }
	    
	    public void deleteNode(int index) {

            int size = this.sizeOf();
             System.out.println("size is "+size + "index is "+ index);
            index= size - index-1;
             System.out.println("deleting index " + index);

            if(index<0) {
                return;
            }
            if(size == 1) {
                firstNode = null;
                currentNode = null;
                return;
            }
            System.out.println(index+firstNode.toString());
            if(firstNode == null)
                return;

            ListNode temp = firstNode;

            if(index == 0) {
                firstNode= temp.next;
                return;
            }
            for(int i = 0; temp != null && i<index -1;i++) {
                temp=temp.next;
            }if(temp == null || temp.next == null) {
                return;
            }

            ListNode next = temp.next.next;
            temp.next=next;
        }
	   
	    public String toString() {
	        String toStr = "";
	        if (currentNode != null) {
	        	toStr += "Name:"+currentNode.getName()+"\nSurname:"
	        +currentNode.getSurname()+"\nPhoneNumber:"
	        +currentNode.getPhoneNumber()+"\nPhoneType"
	        +currentNode.getPhoneType()+"\nEmail:"
	        +currentNode.getEmail()+"\nBirthday:"
	        +currentNode.getBirthday() +"\n\n";
	        	currentNode=currentNode.next;
	        	toStr += toString();
     }
	        return toStr;
  	 }

	    public ListNode[] getNodes() {
	        ListNode[] nodes = new ListNode[this.sizeOf()];
	        ListNode node = firstNode;
	        int i = 0;
	        while(node!=null) {
	        	nodes[i] = node;
	        	i++;
	        	node = node.next;
	        }
	        return nodes;
	    }	    
	    public void writeListToFile() {
	    	try {
	    			FileOutputStream fos = new FileOutputStream("phonebook.dat");
	    			GZIPOutputStream gzos = new GZIPOutputStream(fos);
	    			ObjectOutputStream oos = new ObjectOutputStream(gzos);	 	
	    			
	    			FileOutputStream fosNotCompressed = new FileOutputStream("phonebook_not_compressed.dat");
	    			ObjectOutputStream oosNotCompressed = new ObjectOutputStream(fosNotCompressed);	 	
	    			
	    			
	    		    oos.writeObject(this);
	    		    oos.close();
	    		    oosNotCompressed.writeObject(this);
	    		    oosNotCompressed.close();
	    	}	  		    
	    		 catch (IOException ex) {
	    		    ex.printStackTrace();
	    		}
	    	
	    }
	    public AdressList readListFromFile() {
			try {

				FileInputStream fileIn = new FileInputStream("phonebook.dat");
				GZIPInputStream gzin = new GZIPInputStream(fileIn);
				ObjectInputStream objectIn = new ObjectInputStream(gzin);

				Object obj = objectIn.readObject();

				System.out.println("The Object has been read from the file");
				objectIn.close();
				return (AdressList)obj;

			} catch (Exception ex) {
				return new AdressList();
			}
			
	    }
			
	    public int sizeOf() {
	    	ListNode node = firstNode;
	    	int size = 0;
	    	while(node!=null) {
	    		size++;
	    		node = node.next;
	    	}
	    	return size;	
	    }
}