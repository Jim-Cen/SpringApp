package pers.jim.app.business;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jim.util.MD5;
import pers.jim.app.mapper.UserMapper;
import pers.jim.app.model.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	public String checkPassword(String name,String password) {
		User user=userMapper.getUserByName(name);
		if (user == null || !password.equals(user.getPassword())) {
			return null;
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String token = MD5.encode(timestamp.toString()+"JIM"+name);
		if(userMapper.setTokenByName(user.getUserName(), token) > 0) {
			return token;
		} else {
			return null;
		}
	}
}
