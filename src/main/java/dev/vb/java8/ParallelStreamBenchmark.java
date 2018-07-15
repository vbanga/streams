package dev.vb.java8;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value=1,jvmArgs = { "-Xms4G", "-Xmx4G" })
@State(Scope.Thread)
public class ParallelStreamBenchmark {
	private static final long N = 10_000_000L;

	@Benchmark
	public long sequentialSum() {
		return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
	}
	
	@Benchmark
	public long parallelOptimizedSum() {
		return LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::sum);
	}

	@TearDown(Level.Invocation)
	public void tearDown() {
		System.gc();
	}
}