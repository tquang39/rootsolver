package hus.oop.rootsolver;

public class MyLinkedList {
    private MyNode top;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        this.top = null;
    }

    /**
     * Lấy kích thước danh sách
     * @return
     */
    public int size() {
        int count = 0;
        MyNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Lấy giá trị của node ở vị trí index
     * @return
     */
    public double get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return getNodeByIndex(index).data;
    }

    /**
     * Thay đổi giá trị của node ở vị trí index
     */
    public void set(double data, int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        getNodeByIndex(index).data = data;
    }

    /**
     * Thêm node có giá trị data tại vị trí cuối danh sách
     * @param data
     */
    public void add(double data) {
        MyNode newNode = new MyNode(data);
        if (top == null) {
            top = newNode;
        } else {
            MyNode last = getNodeByIndex(size() - 1);
            last.next = newNode;
            newNode.previous = last;
        }
    }

    /**
     * Thêm node có giá trị data tại vị trí index
     * @param data
     * @param index
     */
    public void insert(double data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        MyNode newNode = new MyNode(data);
        
        if (index == 0) {
            newNode.next = top;
            if (top != null) {
                top.previous = newNode;
            }
            top = newNode;
        } else {
            MyNode current = getNodeByIndex(index - 1);
            newNode.next = current.next;
            newNode.previous = current;
            if (current.next != null) {
                current.next.previous = newNode;
            }
            current.next = newNode;
        }
    }

    /**
     * Xóa node tại vị trí index
     * @param index
     */
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        if (index == 0) {
            top = top.next;
            if (top != null) {
                top.previous = null;
            }
        } else {
            MyNode current = getNodeByIndex(index);
            current.previous.next = current.next;
            if (current.next != null) {
                current.next.previous = current.previous;
            }
        }
    }

    /**
     * Lấy node ở vị trí index.
     * @param index
     * @return
     */
    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
