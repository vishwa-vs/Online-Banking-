package com.banking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.banking.model.AuthEntity;
import com.banking.repository.AuthRepository;
@Component
public class UserInfoUserDetailsService  implements UserDetailsService {
	
	@Autowired
	private AuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {

		AuthEntity userinfo = authRepository.findByUserEmail(eMail);
		
		if (userinfo == null) {
			throw new UsernameNotFoundException("user not found" + eMail);
		}
		return new UserInfoUserDetails(userinfo);
	}


}
