package pers.jim.app.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.jim.app.model.User;

@Mapper
public interface UserMapper {
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
	public User getUserByName(@Param("userName") String userName);
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
	public String getTokenByName(@Param("userName") String userName);
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
	public int setTokenByName(@Param("userName") String userName,@Param("token") String token);
}
