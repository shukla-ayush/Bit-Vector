package bitvector;

/**
 * BitVector class implements IBitVector, and thus
 * defines methods like get, set, copy to be performed
 * on the BitVector data structure. The various 
 * methods defined in the class are get, set, copy,
 * size, iterator, and clear.
 * 
 * @author ashuk
 *
 */

public class BitVector implements IBitVector{

	// constant size of element of Bit array
	private int INT_SIZE = 32;
	
	// int array that represents the Bit array
	private int[] bVector;
	
	// int array for temporary changes
	private int[] tempbVector;
	
	
	public BitVector() {
		
		// Initializing Bit array (int array)
		bVector = new int[1];
	}

	
	/**
	 * get function returns a true or false value
	 * depending upon if the given integer 
	 * corresponding to array element's position
	 * is set or not.
	 * 
	 * @param int i which represents position as integer,
	 * where to check if the bit is set or not
	 * in int array element's position.
	 * 
	 * @return true or false, depending if bit is set at
	 * given position or not.
	 * 
	 * @author ashuk
	 */
	@Override
	public boolean get(int i) {
		return (bVector[i/INT_SIZE] & (1 << (i%INT_SIZE))) != 0;
	}

	
	/**
	 * set function sets the Bit array at corresponding
	 * position given as paramter i.
	 * Bit array here is represented as an int array.
	 * 
	 * @param int i which represents position as integer, where
	 * to set the bit in int array and at what position
	 * of the int element.
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Override
	public void set(int i) {
		if((i/INT_SIZE) > (bVector.length) || (i/INT_SIZE) == (bVector.length)){
			
			// copying array elements to temporary array
			tempbVector = new int[(i/INT_SIZE) + 1];
			for(int j = 0; j < bVector.length; j++) {
				tempbVector[j] = bVector[j];	
			}
			
			// setting bit at desired position
			int bitPosition = (i%INT_SIZE);
			int f = 1;
			f = f << bitPosition;
			tempbVector[i/INT_SIZE] = tempbVector[i/INT_SIZE] | f;
			
			// copying back from temporary array to primary Bit array
			bVector = new int[(i/INT_SIZE) + 1];
			for(int j = 0; j < bVector.length; j++) {
				bVector[j] = tempbVector[j];
			}
		}
		else {
			// setting bit at desired position
			int bitPosition = (i%INT_SIZE);
			int f = 1;
			f = f << bitPosition;
			bVector[i/INT_SIZE] = bVector[i/INT_SIZE] | f;
		}
	}
	

	/**
	 * clear function clears the set bit or toggles
	 * the bit from 1 to 0 in int array at the given
	 * position.
	 * 
	 *  @param int i which represents the position at
	 *  which the bit needs to be toggled from 1 to 0.
	 *  
	 *  @return void
	 *  
	 * @author ashuk
	 * 
	 */
	@Override
	public void clear(int i) {
		int bitPosition = (i%INT_SIZE);
		int f = 1;
		f = f << bitPosition;
		f = ~f;
		bVector[i/INT_SIZE] = bVector[i/INT_SIZE] & f;
	}
	
	
	/**
	 * copy function copies the set bits from the
	 * specified bit array to the function calling
	 * array.
	 * 
	 * @param the array whose bits need to be copied
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Override
	public void copy(BitVector b) {
		// if array to be copied has less number of bits set.
		if(this.bVector.length < b.bVector.length) {
			// copying array elements to temporary array
			tempbVector = new int[b.bVector.length];
			for(int j = 0; j < b.bVector.length; j++) {
				tempbVector[j] = b.bVector[j];
			}
			
			// setting bit at desired position
			for(int j = 0; j < this.bVector.length; j++) {
				tempbVector[j] = tempbVector[j] | this.bVector[j];
			}
			
			// copying back from temporary array to primary Bit array
			this.bVector = new int[tempbVector.length];
			for(int j = 0; j < tempbVector.length; j++) {
				this.bVector[j] = tempbVector[j];
			}
		}
		
		// if array to be copied has more number of bits set.
		else {
			// copying array elements to temporary array
			tempbVector = new int[this.bVector.length];
			for(int j = 0; j < b.bVector.length; j++) {
				tempbVector[j] = b.bVector[j];
			}
			
			// setting bit at desired position
			for(int j = 0; j < this.bVector.length; j++) {
				tempbVector[j] = tempbVector[j] | this.bVector[j];
			}
			
			// copying back from temporary array to primary Bit array
			this.bVector = new int[tempbVector.length];
			for(int j = 0; j < tempbVector.length; j++) {
				this.bVector[j] = tempbVector[j];
			}
		}
	}
	
	
	/**
	 * size function returns the number of bits set
	 * in the bit vector
	 * 
	 * @return the count of the bits set in the Bit
	 * vector
	 * 
	 * @author ashuk
	 */
	@Override
	public int size() {
				
		int count = 0;
		tempbVector = new int[bVector.length];
		for(int j = 0; j < bVector.length; j++) {
			tempbVector[j] = bVector[j];
		}
		for(int j = 0; j<tempbVector.length; j++) {
			for (int k = 0; k < 32; k++) {
		        if (tempbVector[j] < 0) {
		        	count ++;
		        }
		        tempbVector[j] <<= 1;
		    }
		}
		return count;
	    
	}
		
	
	/**
	 * iterator function traverses over the set bit values
	 * in the int elements in the int array.
	 * 
	 * @return returns object of type Iterator, which
	 * is an object to traverse over set bits in int array's
	 * element.
	 * 
	 * @author ashuk		
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new BitIterator();
	}
	
	
	/**
	 * 
	 * BitIterator provides an object of Iterator that iterates over 
	 * the Bit Vector data structure, and provides features
	 * like hasAnotherElement(), nextElement(), which
	 * are declared in the Iterator interface.
	 *
	 * @param <T>
	 * T specifies generic data type. Any data type can use 
	 * object of BitIterator.
	 * 
	 * @author ashuk
	 */
	public class BitIterator implements Iterator<Integer>{

		// keep track of Bit Vector's index position
		int index = 0;
		int count = 0;
		
		// array to store position of set bits in Bit vector.
		int[] numbers;
		
		// index for numbers array
		int i = 0;
		
		// last set number in Bit Vector
		int lastSetNum = 0;
		
		// integer used during iteration.
		private Integer num;
		
		/**
		 * @author ashuk
		 * 
		 * Constructor for BitIterator.
		 * creates an array of all set bits' position.
		 */
		public BitIterator() {
			
			// finds last set bit in Bit Vector and size for 
			for(int k = 0; k < bVector.length*32; k++) {
				if ( (bVector[k/32] & (1 << (k%32) )) != 0 ) {
					count++;
					lastSetNum = k;
				}
			}
			
			// array having position of set bits
			numbers = new int[count];
			
			// creating array of position of set bits
			for(int k = 0; k <= lastSetNum; k++) {
				if ( (bVector[k/32] & (1 << (k%32) )) != 0 ) {
					numbers[i] = k;
					i++;
				}
			}
			
			
		}
		
		
		/**
		 * this function checks if the Bit Vector has more
		 * set bits' position to be traversed
		 * 
		 * @return return boolean value, depending, if there are more
		 * values to be traversed.
		 * 
		 * @author ashuk
		 */
		@Override
		public boolean hasAnotherElement() {
			return index < numbers.length;
		}

		
		/**
		 * returns the next set bit in the Bit Vector.
		 * 
		 * @return returns the next element in the bit vector.
		 * 
		 * @author ashuk
		 */
		@Override
		public Integer nextElement() {
			num = numbers[index++];
			return num;
		}
		
	}
	
	
	
}
