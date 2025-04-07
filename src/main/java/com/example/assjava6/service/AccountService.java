package com.example.assjava6.service;

import com.example.assjava6.model.Account;
import com.example.assjava6.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        var authorities = account.getAuthorities().stream()
                .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getRole().getName())) // ✅ Thêm "ROLE_" để Spring Security nhận diện
                .collect(Collectors.toSet());

        System.out.println("User: " + username + " -> Authorities: " + authorities);
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }


    public Optional<Account> findById(Long id) {
        return accountRepository.findById(String.valueOf(id));
    }


    public void deleteById(Long id) {
        accountRepository.deleteById(String.valueOf(id));
    }

}
