package com.example.splitwiseapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends BaseModel{

    private String name;
    @ManyToMany
    private List<User> groupMembers;
    @OneToMany(mappedBy = "group")
    private List<Expense> groupExpenses;
    @ManyToOne
    private User createdBy;
}
