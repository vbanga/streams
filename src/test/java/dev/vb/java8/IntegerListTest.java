package dev.vb.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class IntegerListTest {

	@Test 
	public void shouldGetSquareTheNumber() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		Assert.assertTrue("its 25!", numbers.stream().map(i -> i*i).collect(Collectors.toList()).get(4) == 25);
	}
	
	@Test
	public void shouldGetAllPossiblePairs() {
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i,j})).collect(Collectors.toList());
		pairs.stream().forEach(i -> System.out.println(i[0]+","+i[1]));
	}
}
