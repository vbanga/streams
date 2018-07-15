package dev.vb.java8;

import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value=1,jvmArgs = { "-Xms4G", "-Xmx4G" })
@State(Scope.Thread)
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long[] numbers;
	private long sum = 0;
	private static final long THRESHOLD = 1000000L;
	
	
	public void setNumbers(long[] n) {
		this.numbers = n;
	}
	
	public long computeSequentially() {
		return Arrays.stream(numbers).reduce(0L, Long::sum);
	}
	
	@Benchmark
	public static void run() {
		final int SIZE = 1000000;
		long[] numbers = new long[SIZE];
		for(int i = 0;i<SIZE;i++) {
			numbers[i] = 1;
		}
		ForkJoinSumCalculator c = new ForkJoinSumCalculator();
		c.setNumbers(numbers);
//		System.out.println("Sum: " + c.compute());
	}
	
	public static void main(String[] args) {
		run();
	}
	
	@Override
	protected Long compute() {
		if (numbers.length <= THRESHOLD) {
			sum = computeSequentially();
			return sum;
		} else {
			int newSize = numbers.length %2 ==0? numbers.length/2:(numbers.length/2 +1);
			long[] left = new long[numbers.length/2];
			long[] right = new long[newSize];
			for (int i=0;i<numbers.length / 2;i++) {
				left[i] = numbers[i];
				right[i] = numbers[i+numbers.length/2];
			}
			if(newSize != numbers.length/2) {
				right[newSize-1] = numbers[numbers.length -1]; 
			}
			ForkJoinSumCalculator c1 = new ForkJoinSumCalculator();
			c1.setNumbers(left);
			ForkJoinSumCalculator c2 = new ForkJoinSumCalculator();
			c2.setNumbers(right);
			ForkJoinTask<Long> leftTask = c1.fork();
			long s2 = c2.compute();
			long s1 = leftTask.join();
			sum = s1 + s2;
			return sum;
		}
	}

}
