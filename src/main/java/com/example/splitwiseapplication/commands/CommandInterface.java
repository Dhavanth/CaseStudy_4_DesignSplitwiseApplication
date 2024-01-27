package com.example.splitwiseapplication.commands;

public interface CommandInterface {
    public boolean matches(String input);
    public void execute(String input);
}
