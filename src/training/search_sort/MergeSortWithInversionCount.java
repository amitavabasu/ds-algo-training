package training.search_sort;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MergeSortWithInversionCount {
	
	/*
	 * Do merge sort and also calculate inversion count while doing it from a given array of integers
	 * 
	 */
	
	public static long mergeSort(int[] arr, int[] res, int l, int r) {//<-- main merge sort method which breaks gown the array, splitting it from middle recursively
		long invCount=0;//<-- initialize inversion count to 0
		if(r>l) {//<-- if right grater than left
			int mid;//<-- define middle
			mid = (l+r)/2;//<-- calculate mid
			
			invCount = mergeSort(arr,res,l,mid);//<-- recursively call merge sort for left to mid and get inversion count for left array
			invCount = invCount+mergeSort(arr,res,mid+1,r);//<-- recursively call merge sort for mid+1 to right and get inversion count for right array. ALSO add the total inversion count
			invCount = invCount+merge(arr,res,l,mid+1,r);//<-- now after both the recursion call ends it goes into merge part of the process
														 //This will actually return the inversion count. Check that middle is always mid+1
		}
		return invCount;//<-- return inversion count
	}

	public static long merge(int[] arr, int res[], int l, int m, int r) {//<-- merge method takes array of integer, result array, left, middle and right
		int i = l;//<-- initialize i to l because l can not be changed
		int j = m;//<-- initialize j to m (i.e. mid) because m can not be incremented
		int k = l;//<-- also initialize k result array index to l
		long invCount = 0;//<-- initialize inversion count to 0
		while(i<=m-1 && j<=r) {//<-- until i less than equal to m-1 (end of first half of the array) and j less than r (second half of the array)
			if(arr[i] <= arr[j]) {//<-- compare corresponding elements from both the array
				res[k++] = arr[i++];//<-- if left side element is less than equal to right side element, then put the left element into result array. Increment k & i
			}else {
				res[k++] = arr[j++];//<-- else (means left side element is grater than right side element, put the right element into result array. Increment k & j
				invCount = invCount+(m-i);//<-- in this case we found that left side element is grater than right side element. This gives us an inversion.
										  //So, calculate inversion count. It is tricky that total inversion count is actually m-i means all elements right have an inversion.
										  //Add previously inversion count with current inversion count. inVCount +=m-i
			}
		}
		while(i<=m-1) {//<-- copy any remaining part of the left side of the array first to result array
			res[k++] = arr[i++];
		}
		while(j<=r) {//<-- then copy any remaining part of the right side of the array to result array
			res[k++] = arr[j++];
		}
		for(i=l; i<=r; i++) {//<-- copy back the entire result array into actual array
			arr[i] = res[i]; //Can we just interchange the array and the result ?
		}
		return invCount;//<-- finally return the inversion count 
	}

	//O(n log n)
    static long mergeSortAndIountInversions(int[] arr) {//<-- start method
    	int[] res = new int[arr.length];
		long invCount = mergeSort(arr, res, 0, arr.length-1);//<-- call merge sort for the entire array length 0 to length-1
		System.out.println(Arrays.toString(res));
		return invCount;
    }

	//O(n^2)
    static long inversionCount(int[] arr) {//<-- alternate inversion count to check the validity of merge sort based inversion count
    	int invCount = 0;//<-- initialize inversion count to 0
    	for(int i=0; i<(arr.length-1); i++) {//<-- for each element in array
    		for(int j=i; j<arr.length; j++) {//<-- from current (i-th) element to rest of the array
    			if (arr[i] > arr[j]) //<-- if i-th current element is grater than the j-th element  
                    invCount++; //<-- increment inversion count
    		}
    	}
    	return invCount;
    }
    
    static Queue<Integer> queue = new LinkedList<>();//Initialize a global queue to hold indexes of each element
    static int seqCount = 1;//initialize sequence count as 1 (why - ? why not 0)
    static void countContSeq(int[] arr) {
    	for(int i=0; i<arr.length; i++) { //for each element in array
    		queue.add(i); //add it's index to global queue
    	}
    	boolean done = false; //initialize done as false
    	while(!done) {
    		System.out.println(queue.toString());
	    	Queue<Integer> newQueue = new LinkedList<>();//initialize a new queue
	    	boolean found = false;//initialize found as false
	    	while(!queue.isEmpty()) {//repeat until global queue is empty
	    		int i = queue.remove();//remove the index from global queue
	    		for(int j=i; j<arr.length; j++) { //iterate from current index to the end of the array
	    			if(arr[j]>arr[i]) { //if the value at each index of the array is grater than the value of current index found in global queue, this means that value is in sequence.
	    				if(!newQueue.contains(j)) //if this values index in the array is not already added into the new queue
	    					newQueue.add(j); //add the array index of the value which is grater than the value of current index, means they are in sequence
	    				found = true; // set the found value as true as we found an array element which is grater than the value of current index therefore they are in sequence
	    			}
	    		}
	    	}
			if(found) { // if a sequence found
				seqCount++; // increment sequence count
				queue = newQueue; //Now we can process only the queue which holds the indexes of all grater values of the current index value. This means we processed
				//the sequence for one element, now we can process the sequence count for next element which is in sequence.
			}else {
				done = true; //No sequence found, this means we do not have any index in sequence queue from this point/index in the array.
				// Hence we can end the sequence counting process.
			}
    	}
    	System.out.println(seqCount);
    }
    
    
	
	public static void main(String[] s) {
		//int[] arr = {2,1};		
		//int[] arr = {7, 5, 3, 1};
		//int[] arr = {2, 1, 3, 1, 2};
		//int[] arr = {2, 4, 1, 3, 5, 6};
		//int[] arr = {3, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		//int[] arr = {1, 2, 1, 5};
		int[] arr = new int[]{10,9,8,7,6,5,4,3,2,1};
//		System.out.println(Arrays.toString(arr));
//		System.out.println("Correct: "+inversionCount(arr));
//		System.out.println(mergeSortAndIountInversions(arr));

		
		//int[] arr2 = {2,1};
		//int[] arr2 = {7, 5, 3, 1};
		//int[] arr2 = {2, 1, 3, 1, 2};
//		int[] arr2 = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,0};
//		System.out.println(MergeSortDup1.mergeSortAndIountInversions(arr2));
		countContSeq(arr);
	}
}
