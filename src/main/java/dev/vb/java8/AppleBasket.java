package dev.vb.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.vb.java8.model.Apple;
import dev.vb.java8.model.Apple.AppleColor;

public class AppleBasket {
	List<Apple> basket = new ArrayList<>();
	
	public void addApple(Apple a) {
		basket.add(a);
	}
	
	public List<Apple> getBasket() {
		return this.basket;
	}
	
	public List<Apple> getApples(AppleColor color) {
		return basket.stream().filter(a -> a.color == color).collect(Collectors.toList());
	}
	
	public List<Apple> getApplesWeighingMoreThan(double weight) {
		return basket.stream().filter(a -> a.weight > weight).collect(Collectors.toList());
	}

	public List<Apple> getFirstGreenApples(int i) {
		
		return basket.stream().filter(a -> a.color == AppleColor.GREEN).limit(i).collect(Collectors.toList());
	}
}
