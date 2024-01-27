package com.example.splitwiseapplication.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Expense extends BaseModel{

    private int amount;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
    @ManyToOne
    @CreatedBy
    private User createdBy;
    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;

}
