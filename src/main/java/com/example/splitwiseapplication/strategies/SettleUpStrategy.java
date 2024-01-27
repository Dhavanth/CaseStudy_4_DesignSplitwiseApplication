package com.example.splitwiseapplication.strategies;

import com.example.splitwiseapplication.models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {

    public List<Expense> settleUp(List<Expense> expenses);
}
