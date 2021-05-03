package com.NumberPlay;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumberPlayList {
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<5;i++)
			list.add(i);
		//Method 1 traversing using iterator
		Iterator<Integer> itr=list.iterator();
		while(itr.hasNext())
			System.out.println("Iterator value "+itr.next());
		//Method 2 Traversing with consumer interface implementation
		class MyConsumer implements Consumer<Integer>{
			public void accept(Integer t) {
				System.out.println("Method2 Consumer impl value "+t);
			}
		}
		MyConsumer action=new MyConsumer();
		list.forEach(action);
		//Method 3 Traversing with anonomys consumer interface
		list.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.println("Method3 Consumer anonomys impl value "+t);
			}
		});
		//Method 4 explicit lamba exp
		Consumer<Integer> listAction=n->{
			System.out.println("Method4 foreach lambda impl value "+n);
		};
		list.forEach(listAction);
		//Method 5 implicit lmbda exp
		list.forEach(n->{System.out.println("Method5 foreach lambda impl value "+n);});
		//Method 6 implicit lambda exp double
		Function<Integer,Double> doubleFun=Integer::doubleValue; //n->n.doubleValue();
		list.forEach(n->{
			System.out.println("Method6 foreach lambda impl value "+doubleFun.apply(n));
			});
		//Method 7 implicit lambda even check
		Predicate<Integer> isEven=n->n>0 && n%2==0;
		list.forEach(n->{
			System.out.println("Method7 foreach lambda check for even "+n+" Check for even "+isEven.test(n));
			});
		
		//Method 8 Stream
		list.stream().forEach(n->{
			System.out.println("Method 8: "+isEven.test(n));
		});
		//Method 9 double value stream
		List<Double> streamList=list.stream()
				.filter(isEven)
				.map(doubleFun)
				.collect(Collectors.toList());
		System.out.println("Method 9 Even List: "+streamList);
		//Method 10 first even
		Integer first=list.stream()
				.filter(isEven)
				.peek(n->System.out.println("even peek "+n))
				.findFirst()
				.orElse(null);
		System.out.println(" Method 10 First even : "+first);
		//Method 11 min even
		Integer minEven=list.stream()
				.filter(isEven)
				.min(Comparator.comparing(Integer::intValue))
				.orElse(null);
		System.out.println("Method 11 Min even: "+minEven);
		//Method 12 max even
		Integer max=list.stream()
					.filter(isEven)
					.max(Comparator.comparing(Integer::intValue))
					.orElse(null);
					
				System.out.println("Method 12 Max even: "+max);
	   //Method 13 Sum,count and avg
	   Integer sum=list.stream().reduce(0, Integer::sum);
	   long count=list.stream().count();
	   System.out.println("Method 13 sum is: "+sum+" count is: "+count+" Average is: "+sum/count);
	   //Method 14 checking all even, single even and none divisible by 6
	   boolean allEven=list.stream().allMatch(isEven);
	   boolean oneEven=list.stream().anyMatch(isEven);
	   boolean noneMulSix=list.stream().noneMatch(i->i>0 && i%6==0);
	   System.out.println("Method 14 alleven: "+allEven+" one even: "+oneEven+" none divisible by 6: "+noneMulSix);
	   //Method 15 sort ascending
	   List<Integer> sortedLs=list.stream()
			   .sorted((n1,n2)->n1.compareTo(n2))
			   .collect(Collectors.toList());
	   System.out.println("Method 15 Sorted list: "+sortedLs);
	}
}
