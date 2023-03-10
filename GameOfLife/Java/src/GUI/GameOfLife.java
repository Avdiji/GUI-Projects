package main.java.GUI;

import main.java.Logic.EColorScheme;
import main.java.Logic.GOL_Logic;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

/**
 * Class contains the GUI of game of life
 **/
public class GameOfLife extends JFrame {

    private static final int WIDTH_FRAME = 1000;
    private static final int LENGTH_FRAME = 1000;

    private final GOL_Logic logic;

    // Timer
    private Timer timer;
    private int timerSpeed;

    // GUI components
    private JLabel titleLabel;
    private JPanel cellsPanel;

    // all the buttons of Game of Life
    private JPanel optionsPanel;
    private JButton clear;
    private JButton timer_slower;
    private JButton timer_faster;
    private JButton toggleStart;
    private JButton colorScheme_blue;
    private JButton colorScheme_red;
    private JButton colorScheme_dark;
    private JButton colorScheme_light;

    public GameOfLife() {
        logic = new GOL_Logic();
        timerSpeed = 100;

        initComponents();
        initFrame();
        addComponents();
        setVisible(true);
    }

    // Method initializes the Frame
    private void initFrame() {
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH_FRAME, LENGTH_FRAME);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Cell.COLOR_LIGHT_BACKGROUND);
    }

    // Method initializes all Components
    private void initComponents() {
        // init Title
        titleLabel = new JLabel("Conway's Game Of Life");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // init Cells
        cellsPanel = new JPanel();
        cellsPanel.setLayout(new GridLayout(GOL_Logic.WIDTH_CELLS, GOL_Logic.LENGTH_CELLS));
        cellsPanel.setBackground(Cell.COLOR_LIGHT_BACKGROUND);

        initColorSchemeButtons();
        initOptions();
    }


    // Method initializes the optionsPanel and each individual option
    private void initOptions() {
        optionsPanel = new JPanel();
        optionsPanel.setBounds(0, 0, WIDTH_FRAME, 100);
        optionsPanel.setBackground(Cell.COLOR_LIGHT_BACKGROUND);

        toggleStart = new JButton("start");
        clear = new JButton("clear");
        timer_slower = new JButton("<<<");
        timer_faster = new JButton(">>>");

        toggleStart.setFont(new Font("Arial Black", Font.PLAIN, 15));
        clear.setFont(new Font("Arial Black", Font.PLAIN, 15));
        timer_slower.setFont(new Font("Arial Black", Font.PLAIN, 15));
        timer_faster.setFont(new Font("Arial Black", Font.PLAIN, 15));

        initTimer();
    }

    // Method initializes the Timer
    private void initTimer() {
        ActionListener timerListener = e -> logic.generateNextIteration();
        timer = new Timer(timerSpeed, timerListener);
    }

    // Method initializes the color schemes for the game of life
    private void initColorSchemeButtons() {
        colorScheme_blue = new JButton("BLUE");
        colorScheme_blue.setForeground(Color.WHITE);
        colorScheme_blue.setBackground(Cell.COLOR_BLUE_ALIVE);
        colorScheme_blue.addActionListener(getColorSchemeListener(EColorScheme.BLUE));

        colorScheme_red = new JButton("RED");
        colorScheme_red.setForeground(Color.WHITE);
        colorScheme_red.setBackground(Cell.COLOR_RED_ALIVE);
        colorScheme_red.addActionListener(getColorSchemeListener(EColorScheme.RED));

        colorScheme_dark = new JButton("DARK");
        colorScheme_dark.setForeground(Color.WHITE);
        colorScheme_dark.setBackground(Cell.COLOR_DARK_ALIVE);
        colorScheme_dark.addActionListener(getColorSchemeListener(EColorScheme.DARK));

        colorScheme_light = new JButton("LIGHT");
        colorScheme_light.setForeground(Color.BLACK);
        colorScheme_light.setBackground(Cell.COLOR_LIGHT_DEAD);
        colorScheme_light.addActionListener(getColorSchemeListener(EColorScheme.LIGHT));
    }

    // Method combines all components
    private void addComponents() {
        add(titleLabel, BorderLayout.NORTH);
        IntStream.range(0, GOL_Logic.WIDTH_CELLS * GOL_Logic.LENGTH_CELLS).forEach(i -> cellsPanel.add(logic.getCells().get(i)));
        add(cellsPanel);

        // toggle start action Listener
        toggleStart.addActionListener(e -> {
            if (toggleStart.getText().equals("start")) {
                toggleStart.setText("stop");
                timer.start();
            } else {
                toggleStart.setText("start");
                timer.stop();
            }
            logic.generateNextIteration();
        });

        // clear action Listener
        clear.addActionListener(e -> {
            logic.resetCells();
            logic.renderCells(logic.getCurrentColor());
        });

        // slower action Listener
        timer_slower.addActionListener(e -> {
            timer.stop();
            timerSpeed += 25;
            initTimer();
            timer.start();
        });

        // faster action Listener
        timer_faster.addActionListener(e -> {
            timer.stop();
            timerSpeed -= 25;
            initTimer();
            timer.start();
        });
        optionsPanel.add(toggleStart);
        optionsPanel.add(clear);
        optionsPanel.add(timer_slower);
        optionsPanel.add(timer_faster);
        optionsPanel.add(colorScheme_blue);
        optionsPanel.add(colorScheme_red);
        optionsPanel.add(colorScheme_dark);
        optionsPanel.add(colorScheme_light);
        add(optionsPanel, BorderLayout.SOUTH);
    }

    // Method returns an ActionListener with the corresponding colorScheme
    private ActionListener getColorSchemeListener(EColorScheme colorScheme) {
        return e -> {
            logic.renderCells(colorScheme);
            switch (colorScheme) {
                case LIGHT -> {
                    getContentPane().setBackground(Cell.COLOR_LIGHT_BACKGROUND);
                    optionsPanel.setBackground(Cell.COLOR_LIGHT_BACKGROUND);
                    cellsPanel.setBackground(Cell.COLOR_LIGHT_BACKGROUND);
                    titleLabel.setForeground(Color.BLACK);
                }
                case DARK -> {
                    getContentPane().setBackground(Cell.COLOR_DARK_BACKGROUND);
                    optionsPanel.setBackground(Cell.COLOR_DARK_BACKGROUND);
                    cellsPanel.setBackground(Cell.COLOR_DARK_BACKGROUND);
                    titleLabel.setForeground(Color.WHITE);
                }
                case RED -> {
                    getContentPane().setBackground(Cell.COLOR_RED_BACKGROUND);
                    optionsPanel.setBackground(Cell.COLOR_RED_BACKGROUND);
                    cellsPanel.setBackground(Cell.COLOR_RED_BACKGROUND);
                    titleLabel.setForeground(Color.BLACK);
                }
                case BLUE -> {
                    getContentPane().setBackground(Cell.COLOR_BLUE_BACKGROUND);
                    optionsPanel.setBackground(Cell.COLOR_BLUE_BACKGROUND);
                    cellsPanel.setBackground(Cell.COLOR_BLUE_BACKGROUND);
                    titleLabel.setForeground(Color.BLACK);
                }
            }
        };
    }


}
