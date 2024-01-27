package com.example.splitwiseapplication.commands;

import com.example.splitwiseapplication.controllers.SettleupController;
import com.example.splitwiseapplication.dtos.SettleUpUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUp implements CommandInterface{

    private SettleupController settleupController;

    @Autowired
    public SettleUp(SettleupController settleupController) {
        this.settleupController = settleupController;
    }
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        if(words.get(1).equalsIgnoreCase("settleup"))
            return true;
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDto settleUpUserRequestDto = new SettleUpUserRequestDto();
        settleUpUserRequestDto.setUserId(userId);

        SettleupController settleupController = new SettleupController();
        settleupController.settleUp(settleUpUserRequestDto);



    }
}
