package domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import domain.entities.Product;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class ProductService {

	public void listItemsInStock(ArrayList<? extends Product> list) {
		int index = 1;
		for (Product item : list) {
			System.out.println(index);
			System.out.println(item);
			index++;
		}
	}

	public ArrayList<? extends Product> sortByTitle(ArrayList<? extends Product> list) {
		Collections.sort(list, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});
		return list;
	}

	public ArrayList<? extends Product> sortByPrice(ArrayList<? extends Product> list) {
		Collections.sort(list, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return (int) (o1.getPrice() - o2.getPrice());
			}
		});
		return list;
	}

}
