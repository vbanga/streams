package dev.vb.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import dev.vb.java8.model.Trader;
import dev.vb.java8.model.Transaction;

public class TraderDeskTest {
	List<Transaction> transactions;
	private  void init() {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		transactions = Arrays.asList(
		 new Transaction(brian, 2011, 300),
		 new Transaction(raoul, 2012, 1000),
		 new Transaction(raoul, 2011, 400),
		 new Transaction(mario, 2012, 710),
		 new Transaction(mario, 2012, 700),
		 new Transaction(alan, 2012, 950)
		 );
	}
	@Test
	public void shouldGetAllTransactionsFromYr() {
		init();
		transactions.stream().filter(t -> t.getYear()==2011).forEach(t -> System.out.println(t.getValue()));
	}
	
	@Test
	public void shouldGetUniqueCities() {
		init();
		transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(c -> System.out.println(c));
	}
	
	@Test
	public void shouldGetAllTradersFromCamAndSortByName() {
		init();
		transactions.stream().map(t -> t.getTrader()).
			filter(trader -> trader.getCity().equals("Cambridge")).
			distinct().
			sorted(Comparator.comparing(Trader::getName)).
			forEach(t -> System.out.println(t.getName()));
	}
	
	@Test
	public void shouldPrintAllTxnValuesFromCambridge() {
		init();
		int sum = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).
			map(t -> t.getValue()).reduce(0,Integer::sum);
		System.out.println(sum);
	}
	
	@Test
	public void shouldGroupTradersByCity() {
		init();
		Map<String, List<Trader>> cityTraderMap = transactions.stream().map(t -> t.getTrader()).distinct().collect(Collectors.groupingBy(Trader::getCity));
		cityTraderMap.entrySet().stream().forEach(e -> System.out.println(e.getKey()+", "+e.getValue()));
		
	}
}
