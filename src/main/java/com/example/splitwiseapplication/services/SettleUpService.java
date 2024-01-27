package com.example.splitwiseapplication.services;

import com.example.splitwiseapplication.exceptions.GroupNotFoundException;
import com.example.splitwiseapplication.exceptions.UserNotFoundException;
import com.example.splitwiseapplication.models.Expense;
import com.example.splitwiseapplication.models.ExpenseUser;
import com.example.splitwiseapplication.models.Group;
import com.example.splitwiseapplication.models.User;
import com.example.splitwiseapplication.repositories.ExpenseRepository;
import com.example.splitwiseapplication.repositories.ExpenseUserRepository;
import com.example.splitwiseapplication.repositories.GroupRepository;
import com.example.splitwiseapplication.repositories.UserRepository;
import com.example.splitwiseapplication.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class SettleUpService {

    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;
    private SettleUpStrategy settleUpStrategy;

    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;

    @Autowired
    public SettleUpService(GroupRepository groupRepository,
                           ExpenseRepository expenseRepository,
                           SettleUpStrategy settleUpStrategy,
                           UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public List<Expense> settleUpUser(Long userId) throws UserNotFoundException {

        // 1. Fetch user from user repo
        // 2. Fetch all expenses of user
        // 3. Settle up all expenses using SettlingUp Strategy
        // 4. Return the expensesToSettleUp (list)

        // 1. Fetch user from user repo
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
            throw new UserNotFoundException("User not found");
        User user = optionalUser.get();

        // 2. Fetch all expenses of user
        List<ExpenseUser> expensesUsers = expenseUserRepository.findAllByUser(user);
        Set<Expense> expenseSet = new HashSet<>();
        // Why set? Because the same expense can be part of multiple expense users
        for(ExpenseUser expenseUser: expensesUsers){
            expenseSet.add(expenseUser.getExpense());
        }
        List<Expense> expensesTempList = settleUpStrategy.settleUp(expenseSet.stream().toList());
        // Above statement returns all the expenses. We need expenses of a particular user
        List<Expense> dummyExpenses = new ArrayList<>();
        for(Expense expense: expensesTempList){
            for(ExpenseUser expenseUser: expense.getExpenseUsers()){
                if(expenseUser.getUser().equals(user)){
                    dummyExpenses.add(expense);
                    break;
                }
            }
        }

        // 4. Return the expensesToSettleUp (list)
        return dummyExpenses;
    }

    public List<Expense> settleUpGroup(Long groupId) throws GroupNotFoundException {

        // 1. Fetch group from group repo
        // 2. Fetch all expenses of group
        // 3. Settle up all expenses using SettlingUp Strategy
        // 4. Return the expensesToSettleUp (list)

        // 1. Fetch group from group repo
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if(optionalGroup.isEmpty())
            throw new GroupNotFoundException("Group not found");

        Group group = optionalGroup.get();

        // 2. Fetch all expenses of group
        List<Expense> expenses = expenseRepository.findAllByGroup(group);

        // 3. Settle up all expenses using SettlingUp Strategy
        // get the dummy expenses to settleup
        List<Expense> dummyExpenses = settleUpStrategy.settleUp(expenses);

        // 4. Return the expensesToSettleUp (list)
        return dummyExpenses;
    }
}
