package com.example.splitwiseapplication.dtos;

import com.example.splitwiseapplication.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDto {
    private List<Expense> expenses;
}
