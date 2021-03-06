package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product,Integer>{
	Product getByProductName(String productName);
	
	//select * from products where product_name=abc and category_id=1
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	
	//select * from products where product_name=abc or category_id=1
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
	
	//select * from products where category_id in(1,2,3,4)
	//category id'si 1,2,3,4 olanlardan birisini getir anlamında kullanılır.
	//Product class'inin icinde category_id olmadıgı icin buraya isimlendirme olarak category yazilmali.
	List<Product> getByCategoryIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	//select * from products where product_name=bisey and category_id=bisey
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	
	
	//select p.productId, p.productName, c.categoryName from Category c inner join Product p
	//on c.categoryId = p.categoryId
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWitCategoryDetails();
	
}
