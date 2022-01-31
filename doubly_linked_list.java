package weblab;

class DLList {

    class Node {

        // Each node object has these three fields
        private Object element;

        private Node previous;

        private Node next;

        /**
         * Constructor: Creates a Node object.
         *
         * @param e the element in the Node.
         * @param p the previous Node.
         * @param n the next Node.
         */
        Node(Object e, Node p, Node n) {
            element = e;
            previous = p;
            next = n;
        }

        /**
         * Sets the element of the Node.
         *
         * @param e the new element.
         */
        public void setElement(Object e) {
            element = e;
        }

        /**
         * Returns the element of the Node.
         *
         * @return the element of the Node.
         */
        public Object getElement() {
            return element;
        }

        /**
         * Sets the next Node.
         *
         * @param n the new next Node.
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Returns the next Node.
         *
         * @return the next Node.
         */
        public Node getNext() {
            return next;
        }

        /**
         * Sets the previous Node.
         *
         * @param p the new previous Node.
         */
        public void setPrevious(Node p) {
            previous = p;
        }

        /**
         * Returns the previous Node.
         *
         * @return the previous Node.
         */
        public Node getPrevious() {
            return previous;
        }
    }

    // Each object in DLList has one field head, which points to the starting Node of DLList.
    private Node head;

    // Each object in DLList has one field tail, which points to the final Node of DLList.
    private Node tail;

    /** Constructor: initialises the head and tail fields as null */
    public DLList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Returns the element in the head Node.
     *
     * @return The element in the head Node.
     */
    public Object getHead() {
        return head.getElement();
    }

    /**
     * Returns the element in the tail Node.
     *
     * @return The element in the tail Node.
     */
    public Object getTail() {
        return tail.getElement();
    }

    /**
     * Adds element e in a new Node to the head of the list.
     *
     * @param e the element to add.
     */



    public void addFirst(Object e) {

        if(this.head == null || this.getHead() == null){
            Node node1 = new Node(e,null,null);
            this.head = node1;
            this.tail = node1;
        }
        else{
            Node formerHead = this.head;
            Node node = new Node(e,null,formerHead);
            node.setPrevious(null);
            node.setNext(formerHead);
            this.head = node;

            node.getNext().setPrevious(node);

            if(this.size() == 2){
                this.tail.setPrevious(node);
            }

        }


    }

    /**
     * Remove the first Node in the list and return its element.
     *
     * @return The element of the head Node. If the list is empty, this method returns null.
     */
    public Object removeFirst() {

        if(this.head==null || this.getHead() == null){
            return null;
        }

        Node formerHead = this.head;
        Node newHead = this.head.getNext();

        if(newHead != null){
            newHead.setPrevious(null);
            this.head = newHead;
        }
        else{
            this.tail = null;
            this.head = null;
        }

        return formerHead.getElement();

    }

    /**
     * Adds element e in a new Node to the tail of the list.
     *
     * @param e the element to add.
     */
    public void addLast(Object e) {

        if(this.tail == null){
            this.addFirst(e);
        }
        else{
            Node oldTail = this.tail;
            Node node = new Node(e,oldTail,null);
            oldTail.setNext(node);
            node.setPrevious(oldTail);
            this.tail = node;
        }



    }

    /**
     * Remove the last Node in the list and return its element.
     *
     * @return The element of the tail Node. If the list is empty, this method returns null.
     */
    public Object removeLast() {

        if(this.tail == null){
            return null;
        }
        if(this.tail == this.head){
            Node retVal = this.tail;
            this.head =null;
            this.tail =null;
            return retVal.getElement();
        }
        else{
            Node formerLast = this.tail;

            Node newLast = this.tail.getPrevious();
            newLast.setNext(null);
            this.tail = newLast;

            return formerLast.getElement();
        }

    }

    /**
     * Returns the number of Nodes in the list.
     *
     * @return the number of Nodes in the list.
     */
    public int size() {

        int counter = 1;

        Node cur = this.head;

        if(this.head == null){
            return 0;
        }
        if(this.head == this.tail){
            return 1;
        }
        else{
            boolean flag = true;
            while(flag){
                counter++;
                cur = cur.getNext();
                if(cur.getNext() == null || cur.getNext().getElement() == null){
                    flag = false;
                }
            }
            return counter;
        }

    }

    /**
     * Adds element e in a new Node which is inserted at position pos. The list is zero indexed, so
     * the first element in the list corresponds to position 0. This also means that `addAtPosition(0,
     * e)` has the same effect as `addFirst(e)`. If there is no Node in position pos, this method adds
     * it to the last position.
     *
     * @param pos The position to insert the element at.
     * @param e The element to add.
     */
    public void addAtPosition(int pos, Object e) {

        if(pos == 0){
            this.addFirst(e);
            return;
        }
//            if(pos == this.size()-1){
//                this.addLast(e);
//                return;
//            }


        if(pos<this.size()){

            Node seeked = this.head;

            for(int i=0;i<pos;i++){
                seeked = seeked.getNext();
            }

            Node newNode = new Node(e,null,null);

            newNode.setNext(seeked);
            newNode.setPrevious(seeked.getPrevious());

            seeked.getPrevious().setNext(newNode);
            seeked.setPrevious(newNode);


        }
        if(pos>=this.size()){
            this.addLast(e);
        }
    }

    /**
     * Remove Node at position pos and return its element. The list is zero indexed, so the first
     * element in the list corresponds to position 0. This also means that `removeFromPosition(0)` has
     * the same effect as `removeFirst()`.
     *
     * @param pos The position to remove the Node from.
     * @return The element of the Node in position pos. If there is no Node in position pos, this
     *     method returns null.
     */
    public Object removeFromPosition(int pos) {
        if(pos == 0){
            return this.removeFirst();
        }
        if(pos == this.size()-1){
            return this.removeLast();
        }


        if(pos<this.size()){

            Node seeked = this.head;

            for(int i=0;i<pos;i++){
                seeked = seeked.getNext();
            }

            Node nextEl = seeked.getNext();
            Node prevEl = seeked.getPrevious();

            nextEl.setPrevious(prevEl);
            prevEl.setNext(nextEl);
            return seeked.getElement();

        }
        return null;
    }

    /** @return A new DLL that contains the elements of the current one in reversed order. */
    public DLList reverse() {
        DLList dllList = new DLList();

        Node curr = this.head;

        if(this.size() == 0){
            return dllList;
        }

        while(curr.getNext() != null){

            dllList.addFirst(curr.getElement());
            curr = curr.getNext();

        }

        dllList.addFirst(curr.getElement());

        return dllList;
    }
}