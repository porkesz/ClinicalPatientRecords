package cpr.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cpr.dao.EmployeRepository;
import cpr.model.CustomEmployeDetails;
import cpr.model.Employe;

@Service
@Transactional
public class CustomEmployeDetailsService implements UserDetailsService{

	@Autowired
	private EmployeRepository employeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Employe> optionalUsers = employeRepository.findByEmail(email);
		
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return optionalUsers
                .map(CustomEmployeDetails::new).get();
	}
}
