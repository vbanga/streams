package dev.vb.java8.model;

public class Apple {
	public enum AppleColor {
		RED(0), GREEN(1), WHITE(2);
		private final int i;
		private AppleColor(int i) {
			this.i = i;
		};
		public int getColor() {
			return this.i;
		}
	};
	public AppleColor color;
	public String name;
	public double weight;
	public double price;
	public Apple(AppleColor color, String name, double weight, double price) {
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.color = color;
	}
	
	
}
