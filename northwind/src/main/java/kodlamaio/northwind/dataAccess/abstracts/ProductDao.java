package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;

public interface ProductDao extends JpaRepository<Product,Integer>{
	Product getByProductName(String productName);
	
	//select * from products where product_name=abc and category_id=1
	Product getByProductNameAndCategoryId(String productName, int categoryId);
	
	//select * from products where product_name=abc or category_id=1
	List<Product> getByProductNameOrCategoryId(String productName, int categoryId);
	
	//select * from products where category_id in(1,2,3,4)
	//category id'si 1,2,3,4 olanlardan birisini getir anlamında kullanılır.
	List<Product> getByCategoryIdIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	//select * from products where product_name=bisey and category_id=bisey
	@Query("From Product where productName=:productName and categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);

}
