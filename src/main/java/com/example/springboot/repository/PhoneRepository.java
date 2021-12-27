package com.example.springboot.repository;

import com.example.springboot.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Phone findByValue(String phoneNumber);

//    boolean existsByEmail(String email);
//
////    boolean existsByPhonesss(List<Phone> phones);
//
//    boolean existsByUsername(String username);

}
