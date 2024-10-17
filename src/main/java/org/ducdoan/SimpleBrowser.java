package org.ducdoan;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleBrowser {

    public static void main(String[] args) {
        // Tạo JFrame chính
        final JFrame frame = new JFrame("Simple Browser");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tạo JFXPanel cho JavaFX WebView
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel, BorderLayout.CENTER);

        // Tạo thanh điều khiển ở phía trên với JTextField và JButton
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        JTextField urlField = new JTextField("https://www.google.com"); // Ô nhập URL
        JButton goButton = new JButton("Go"); // Nút bấm

        controlPanel.add(urlField, BorderLayout.CENTER); // Thêm ô nhập vào panel
        controlPanel.add(goButton, BorderLayout.EAST); // Thêm nút bấm vào panel

        frame.add(controlPanel, BorderLayout.NORTH); // Đặt panel lên trên cùng

        // Thiết lập JavaFX WebView và WebEngine bên trong JFXPanel
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            fxPanel.setScene(new Scene(webView)); // Đặt WebView vào JFXPanel

            // Tải trang mặc định
            webEngine.load("https://www.google.com");

            // Thêm ActionListener vào nút "Go" để tải trang từ URL nhập vào
            goButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.runLater(() -> {
                        String url = urlField.getText(); // Lấy URL từ ô nhập liệu
                        webEngine.load(url); // Tải trang theo URL nhập vào
                    });
                }
            });
        });

        // Hiển thị JFrame
        frame.setVisible(true);
    }
}
