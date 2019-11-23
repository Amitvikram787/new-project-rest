package com.cognizan.truyum.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognizan.truyum.model.Cart;

public interface CartRepositery extends JpaRepository<Cart, Integer> {
	
	
	@Query("select c from Cart c where ct_us_id=:id")
    Cart getAllCartItems(@Param("id") int id);
    
    @Query(value="select * from Cart where ct_us_id=:id",nativeQuery=true)
    Cart findByUserId(@Param("id") int id);


	
	

}
