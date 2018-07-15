package dev.vb.java8;

import org.junit.Assert;
import org.junit.Test;



public class ForkJoinSumCalculatorTest {

	@Test
	public void testComputeEven() {
		run(10, 45);
	}
	
	@Test
	public void testComputeOdd() {
		run(15, 105);
	}
	
	@Test
	public void testCompute2sMultiple() {
		run(8, 28);
	}
	
	private void run(int count, long result) {
		long[] numbers = new long[count];
		for (int i=0;i<count;i++) {
			numbers[i] = i;
		}
		ForkJoinSumCalculator calculator = new ForkJoinSumCalculator();
		calculator.setNumbers(numbers);
		
		Assert.assertEquals(result, calculator.compute().longValue());
	}

}
