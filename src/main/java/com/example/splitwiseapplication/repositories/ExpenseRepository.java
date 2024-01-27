package com.example.splitwiseapplication.repositories;

import com.example.splitwiseapplication.models.Expense;
import com.example.splitwiseapplication.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Group> {

    List<Expense> findAllByGroup(Group group);

}
