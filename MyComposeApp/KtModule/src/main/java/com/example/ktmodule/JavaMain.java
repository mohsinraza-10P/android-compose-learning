package com.example.ktmodule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;

public class JavaMain {

    public static void main(String[] args) {
        JButton button = new JButton("Click Me");

        // Using a lambda expression as an event handler
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });


        button.addActionListener( event -> {

        });
    }

    static void method() {
    }
}
