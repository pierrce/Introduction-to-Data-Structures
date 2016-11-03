
public class SimpleLinkedList<E> implements ListADT<E> {

	private DblListnode<E> head;
	private DblListnode<E> tail;
	private DblListnode<E> curr;
	private int listCount;

	public SimpleLinkedList() {

		head = new DblListnode<E>(null);
		tail = new DblListnode<E>(null);
		listCount = 0;

	}


	public void add(E item) {
		DblListnode<E> obj = new DblListnode<E>(item);

		//If no items, sets this item to head.
		if(head.getData() == null) {
			head = obj;
			tail = obj;
		}

		else{
			tail.setNext(obj);
			tail.getNext().setPrev(tail);
			tail = tail.getNext();
		}

		//Increments size.
		listCount++;

	}


	public void add(int pos, E item) {

		DblListnode<E> obj = new DblListnode<E>(item);

		curr = head;

		if(head.getData() == null){
			head = obj;
			tail = obj;
		}

		//If first item added, sets head equal to that item.
		if(pos == 0){ 
			head.setPrev(obj);
			head.getPrev().setNext(head);
			head = head.getPrev();
		}

		//If last item added, sets tail equal to that item.
		if(pos == listCount){
			tail.setNext(obj);
			tail = tail.getNext();
		}

		if(pos != 0 && pos != listCount){

			if(pos != 1){
				//Sets curr to node before the position the new one will be implemented.
				for(int i = 0; i<pos; i++){
					curr = curr.getNext();

				}
			}

			//Implements the new E into the linked list.
			obj.setNext(curr.getNext());
			obj.setPrev(curr);
			curr.getNext().setPrev(obj);
			curr.setNext(obj);


		}
		//Increments size.
		listCount++;

	}


	public boolean contains(E item) {

		curr = head;

		//Iterates through linkedlist and checks if each node is equal to item.
		for(int i = 0; i<listCount; i++){
			if(curr.equals(item)) return true;
			curr = curr.getNext();
		}

		return false;
	}


	public E get(int pos) {
		curr = head;

		//Iterates curr to the pos.
		for(int i = 0; i<pos; i++){
			curr = curr.getNext();
		}

		return curr.getData();
	}


	public boolean isEmpty() {
		if(listCount == 0) return true;
		return false;
	}


	public E remove(int pos) {
		curr = head;

		//Iterates curr to the pos.
		for(int i = 0; i<pos; i++){
			curr = curr.getNext();
		}
		
		if(listCount == 1){
			head.setData(null);
			curr.setData(null);
			tail.setData(null);
			listCount--;
			return head.getData();
		}
		
		else{

			if(curr.getData() == head.getData()){
				head = head.getNext();
				curr.getNext().setPrev(null);
				curr = head;
				
			}

			if(curr.getData() == tail.getData() && curr.getData() != head.getData()){
				tail = tail.getPrev();
				curr.getPrev().setNext(null);
				curr = tail;
			}
			
			if(curr.getData() != tail.getData() && curr.getData() != head.getData()){
				curr.getNext().setPrev(curr.getPrev());
				System.out.println(curr.getNext() + " "+ curr.getPrev());
				curr.getPrev().setNext(curr.getNext());
			}
		}

		listCount--;
		return curr.getData();
	}


	public int size() {
		return listCount;
	}




}
