package ch03;

public class HeapSort {
	private int SIZE;
	private int heapArr[];
	
	public HeapSort()
	{
		SIZE =0;
		heapArr = new int [50];
	}
	
	public void insertHeap(int input) {
//		data를 추가하면서 크기도 1 커진다.
		int i = ++SIZE;
		//while(( i != 1) && ( input > heapArr[i/2])){ //max heap
		while((i!=1)&&(input<heapArr[i/2])) { // min heap
			heapArr[i] = heapArr[i/2];
			i=i/2;
		}
		heapArr[i] = input;
	}
	
	public int getHeapSize()
	{
		return SIZE;
	}
	
	public int deleteHeap() 
	{
//		delete하면 무조건 맨위에 있는 꺼를 없앤다.
//		data를 삭제하면서 크기도 1 작아진다.
		int parent, child;
		int data, temp;
		data = heapArr[1];
		
		temp=heapArr[SIZE];
		SIZE-=1;
		parent =1; child =2;
		
		while(child <=SIZE) {
			//if((child < HEAP_SIZE) && (heapArr[child] < heapArr[child+1])){ //max heap
			if((child<SIZE)&&(heapArr[child]>heapArr[child+1])) { //min heap
//				둘중에 더작은 것을 child로 선정
				child ++;
			}
			if(temp<=heapArr[child])break;
			heapArr[parent] = heapArr[child];
			parent=child;
			child *= 2;
		}
//		parent=child 값을 대입해줬기 때문에 child 값에 temp를 넣는 것이다.
		heapArr[parent] = temp;
		return data;
		
	}
	
	public void printHeap() {
		//System.out.printf("\n Max Heap : ");
		System.out.printf("\n Min Heap:");
		for(int i=1;i<=SIZE;i++) {
			System.out.printf("[%d] ", heapArr[i]);
		}
	}
	public static void main(String[] args) {
		HeapSort h = new HeapSort();
		h.insertHeap(80);
		h.insertHeap(50);
		h.insertHeap(70);
		h.insertHeap(10);
		h.insertHeap(60);
		h.insertHeap(20);
		
		h.printHeap();
		
		int n, data;
		n = h.getHeapSize();
		for(int i=1; i<=n;i++) {
			data = h.deleteHeap();
			System.out.printf("\n 출력 : [%d]", data);
		}

	}

}
