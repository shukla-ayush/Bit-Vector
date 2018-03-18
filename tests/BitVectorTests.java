package q1aTests;

import static org.junit.Assert.*;


import org.junit.Test;

import q1a.BitVector;
import q1a.IBitVector;

/**
 * This class has methods that test the BitVector 
 * class functionalities.
 * 
 * @author ashuk
 * 
 */
public class BitVectorTests {

	/**
	 * getsetTest is the test to check the functioning 
	 * of set() snd get().
	 * 
	 * set() should set the bit at given position (i).
	 * 
	 * get() should return true or false, if bit is set 
	 * at given position (i), or not.
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Test
	public void getsetTest() {
		IBitVector bVector = new BitVector(); 
		bVector.set(29);
		bVector.set(30);
		bVector.set(31);
		bVector.set(32);
		bVector.set(33);
		assertEquals(true, bVector.get(32));
		assertEquals(true, bVector.get(33));
		assertEquals(true, bVector.get(31));
	}
	
	
	/**
	 * getsetHigherTest is the test to check the functioning 
	 * of set() snd get(), for high integer numbers
	 * 
	 * set() should set the bit at given position (i).
	 * 
	 * get() should return true or false, if bit is set 
	 * at given position (i), or not.
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Test
	public void getsetHigherTest() {
		IBitVector bVector = new BitVector(); 
		bVector.set(1000000000);
		bVector.set(2000000000);
		bVector.set(1200000000);
		bVector.set(2100000000);
		assertEquals(true, bVector.get(2100000000));
		assertEquals(true, bVector.get(1000000000));
		assertEquals(true, bVector.get(2000000000));
	}
	
	
	/**
	 * clearTest is to test if the clear() method 
	 * correctly unsets the bit at specified
	 * position.
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Test
	public void clearTest() {
		IBitVector bVector = new BitVector(); 
		bVector.set(62);
		bVector.set(100);
		assertEquals(true, bVector.get(62));
		bVector.clear(62);
		assertEquals(false, bVector.get(62));
	}
	
	
	/**
	 * copyTest is to test if the copy() method 
	 * correctly sets the bits of specified
	 * Bit Vector to the given BitVector.
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Test
	public void copyTest() {
		BitVector bVector1 = new BitVector(); 
		bVector1.set(62);
		bVector1.set(100);
		BitVector bVector2 = new BitVector(); 
		bVector2.set(32);
		bVector2.set(200);
		bVector1.copy(bVector2);
		assertEquals(true, bVector1.get(32));
		assertEquals(4, bVector1.size());
	}
	
	/**
	 * copySizeTest is to test if the copy() method 
	 * correctly increase size of the array to suit
	 * new set bits.
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Test
	public void copySizeTest() {
		BitVector bVector1 = new BitVector(); 
		bVector1.set(62);
		bVector1.set(100);
		BitVector bVector2 = new BitVector(); 
		bVector2.set(32);
		bVector2.set(200);
		bVector1.copy(bVector2);
		assertEquals(4, bVector1.size());
	}
	
	/**
	 * sizeTest is to test if the number of bits set in the
	 * BitVector are correct, and thus correctly returned by
	 * size() method
	 * 
	 * @return void
	 * 
	 * @author ashuk
	 */
	@Test
	public void sizeTest() {
		IBitVector bVector = new BitVector(); 
		bVector.set(62);
		bVector.set(100);
		bVector.set(1000);
		bVector.set(2000);
		assertEquals(4, bVector.size());
	}
}
	