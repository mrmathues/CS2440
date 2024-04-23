package storage;
import storage.DoubleNode;

public class DoubleLinkedSeq implements Cloneable{

    private int manyNodes;
    private DoubleNode tail;
    private DoubleNode head;
    private DoubleNode precursor;
    private DoubleNode cursor;

    public DoubleLinkedSeq(){
        tail = head = precursor = cursor = null;
        manyNodes = 0;
    }

    public int size(){
        return manyNodes;
    }

    public void addAfter(double data){
        if (!isCurrent() || cursor == tail) {
            if (tail == null) {
                head = new DoubleNode(data);
                tail = head;
            }
            else{
                DoubleNode newNode = new DoubleNode(data);
                tail.setLink(newNode);
                tail = newNode;
            }
            cursor = tail;
        }
        else{
            DoubleNode newNode = new DoubleNode(data, cursor.getLink());
            cursor.setLink(newNode);
            precursor = cursor;
            cursor = newNode;
        }
        manyNodes++;
    }

    public void addBefore(double data){
        if (!isCurrent()) {
            if (head == null) {
             head = new DoubleNode(data);
             tail = head;
            } else {
             head = new DoubleNode(data, head);
            }
            cursor = head;
            manyNodes++;
            return;
         }
         DoubleNode newNode = new DoubleNode(data);
         if (cursor == head){
             newNode.setLink(head);
             head = newNode;
         }
         else{
             DoubleNode trans = head;
             while (trans.getLink() != cursor) {
                 trans = trans.getLink();
             }
             trans.setLink(newNode);
             newNode.setLink(cursor);
         }
         if (cursor == head){
             tail = head;
         }
         precursor = null;
         cursor = newNode;
         manyNodes++;
    }

    public void addAll(DoubleLinkedSeq addend){
    }

    public boolean isCurrent(){
        if (cursor != null) {
            return true;
        } else {
            return false;
        }
    }

    public void start(){
        if (head != null) {
            cursor = head;
        } else {
            cursor = null;
        }
        precursor = null;
    }

    public void advance(){
        if (isCurrent()) 
        {
            precursor = cursor;
            cursor = cursor.getLink();
        } 
        else 
        {
            throw new IllegalStateException("Current element does not exist");
        }
    }

    public double getCurrent(){
        if (isCurrent()){
            return cursor.getData();
        }
        else{
            throw new IllegalStateException("There is no current element.");
        }
    }

    public void removeCurrent(){
        if (!isCurrent()) {
            throw new IllegalStateException("There is no current element.");
        }
        else{
            if (head == tail){
                head = null;
                tail = null;
                cursor = null;
                manyNodes--;
            }
            else if(cursor == head && precursor == null){
                head = head.getLink();
                cursor = head;
                manyNodes--;
            }
            else if(cursor != tail && precursor != null){
                cursor = cursor.getLink();
                precursor.setLink(cursor);
                manyNodes--;
            }
            else if (cursor == tail && precursor != null){
                cursor = null;
                precursor.setLink(null);
                precursor = null;
                manyNodes--;
            }
        }
    }

    public DoubleLinkedSeq clone(){
        DoubleLinkedSeq answer;
        
        try
        {
            answer = (DoubleLinkedSeq) super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            throw new RuntimeException
                    ("This class does not implement Cloneable.");
        }
        
        if (cursor == null){
            DoubleNode[] newList = DoubleNode.listCopyWithTail(head);
        
            answer.head = newList[0];
            answer.tail = newList[1];
        }
        else if (cursor == head) {
            DoubleNode[] newList = DoubleNode.listCopyWithTail(head);
            answer.head = newList[0];
            answer.tail = newList[1];
            
            answer.precursor = null;
            answer.cursor = answer.head;
        }
        else if (cursor != null && precursor != null) {
            //listPart
            DoubleNode[] firstPart = DoubleNode.listPart(head, precursor);
            answer.head = firstPart[0];
            answer.precursor = firstPart[1];
            
            DoubleNode[] secondPart = DoubleNode.listPart(cursor, tail);
            answer.cursor = secondPart[0];
            answer.tail = secondPart[1];

            answer.precursor.setLink(answer.cursor);
        }
        
        return answer;
    }

    public String toString(){
        String answer = "";
        
        if (!isCurrent()) {
            if (head == null) {
                answer += "<>";
            }
            else if (manyNodes == 1) {
                answer += "<" + head.getData() + ">";
            }
            else if (manyNodes > 1) {
                answer += "<";
                
                for (DoubleNode temp = head; temp != null; temp = temp.getLink()) {
                    answer += "" + temp.getData();
                    
                    if (temp.getLink() != null) {
                        answer += ", ";
                    }
                }
                answer += ">";
            }
        }
        else{
            if(manyNodes == 1){
                answer += "<[" + head.getData() + "]>";
            }
            else if(manyNodes > 1){
                answer += "<";
                for (DoubleNode temp = head; temp != null; temp = temp.getLink()) {
                    
                    if (temp == cursor){
                        answer += "[" + temp.getData() + "]";
                    }
                    else{
                        answer += "" + temp.getData();
                    }

                    if (temp.getLink() != null){
                        answer += ", ";
                    }
                }
                
                answer += ">";
            }
        }
        
        return answer;
    }

    public boolean equals(Object other){
        boolean b = true;
        DoubleLinkedSeq otherSeq = (DoubleLinkedSeq) other;
        
        if (manyNodes == otherSeq.manyNodes){
            for (DoubleNode temp1 = head, temp2 = otherSeq.head;
                        temp1 != null;
                        temp1 = temp1.getLink(), temp2 = temp2.getLink()){
                if (temp1.getData() != temp2.getData()){
                    b = false;
                    break;
                }
                if (temp1 == cursor && temp2 != otherSeq.cursor){
                    b = false;
                    break;
                }
                if (temp2 == otherSeq.cursor && temp1 != cursor){
                    b = false;
                    break;
                }
            }
        }
        else{
            b = false;
        }
        
        return b;
    }

    public static DoubleLinkedSeq concatenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2){
        if (s1.size() == 0 || s2.size() == 0){
            throw new NullPointerException("The sequences cannot be null.");
        } 
        else{
            DoubleLinkedSeq newSeq = s1.clone();
            newSeq.addAll(s2);
            while (newSeq.isCurrent()){
                newSeq.advance();

            }
            return newSeq;
        }
    }
}