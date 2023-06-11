package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {

		UserEntity userinfo = userInfoRepository.findByeMail(eMail);
		
		if (userinfo == null) {
			throw new UsernameNotFoundException("user not found" + eMail);
		}
		return new UserInfoUserDetails(userinfo);
	}

}