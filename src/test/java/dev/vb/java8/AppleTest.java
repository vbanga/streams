package dev.vb.java8;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.vb.java8.model.Apple;
import dev.vb.java8.model.Apple.AppleColor;


public class AppleTest {
	static AppleBasket basket = new AppleBasket();
	@BeforeClass
	static public void init() {
		Apple a1 = new Apple(AppleColor.GREEN, "Green-Apple-1", 50.0, 0.5);
		Apple a2 = new Apple(AppleColor.RED, "Green-Apple", 50.0, 0.5);
		Apple a3 = new Apple(AppleColor.WHITE, "Green-Apple", 50.0, 0.5);
		Apple a4 = new Apple(AppleColor.GREEN, "Green-Apple-2", 20.0, 0.8);
		Apple a5 = new Apple(AppleColor.RED, "Green-Apple", 20.0, 0.8);
		Apple a6 = new Apple(AppleColor.WHITE, "Green-Apple", 20.0, 0.8);
		Apple a7 = new Apple(AppleColor.GREEN, "Green-Apple-3", 20.0, 0.8);
		basket.addApple(a1);
		basket.addApple(a2);
		basket.addApple(a3);
		basket.addApple(a4);
		basket.addApple(a5);
		basket.addApple(a6);
		basket.addApple(a7);
		System.out.println(basket.getBasket());
	}
	
	@Test
	public void shouldSortByWeight() {
		basket.getBasket().sort((Apple a1, Apple a2) -> {return a1.weight < a2.weight ? -1: 1;});
		
	}
	
	@Test
	public void shouldGetGreenApples() {
		List<Apple> greenApples = basket.getApples(AppleColor.GREEN);
		Assert.assertTrue("There should be 3 green apples", greenApples.size()==3);
	}
	
	@Test
	public void shouldGetHeavyApples() {
		List<Apple> greenApples = basket.getApplesWeighingMoreThan(45.0);
		Assert.assertTrue("There should be 3 heavy apples", greenApples.size()==3);
	}

	@Test
	public void shouldGetAppleWithDistinctWeight() {
		List<Double> weights = basket.getBasket().stream().map(a -> a.weight).distinct().collect(Collectors.toList());
		Assert.assertTrue("There should be 2 unique weight apples", weights.size() ==2);
	}
	
	@Test
	public void shouldGetFirstTwoGreenApples() {
		List<Apple> greenApples = basket.getFirstGreenApples(2);
//		System.out.println(greenApples);
//		Assert.assertTrue("First green apple should be 1, it is: " + greenApples.get(0).name, greenApples.get(0).name.equals("Green-Apple-1"));
//		Assert.assertTrue("First green apple should be 2, it is: "+ greenApples.get(1).name, greenApples.get(1).name.equals("Green-Apple-1"));
	}
	
	@Test
	public void shouldGetAllApples() {
		List<Apple> allApples = basket.getBasket().stream().collect(Collectors.toList());
		System.out.println(allApples);
	}
	
}
