package com.example.splitwiseapplication.repositories;


import com.example.splitwiseapplication.models.Expense;
import com.example.splitwiseapplication.models.ExpenseUser;
import com.example.splitwiseapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, User> {

    List<ExpenseUser> findAllByUser(User user);
}
