package com.cognizan.truyum.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizan.truyum.model.MenuItem;

@Repository
public interface MenuItemRepositery extends JpaRepository<MenuItem, Integer> {
	
	@Query(value="SELECT m FROM MenuItem m where m.active=1 and m.dateOfLaunch< CURRENT_Date() ")
    List<MenuItem> getMenuItemsCustomer();
    
    @Query("select m.id from MenuItem m where me_id=:id")
    Long findByMenuItemId(@Param("id") long menuItemId);



}
