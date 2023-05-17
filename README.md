# SCC110-AirHockey

This repository contains the following files for the SCC.110 project "Air Hockey" (Project 2 in 2022/23):

**Java classes (.java files)**
+ Ball.java
+ GameArena.java
+ Line.java
+ Rectangle.java
+ Text.java
+ Main.java
+ Gamestate.java
+ Menustate.java
+ Textbox.java
+ Table.java
+ Puck.java
+ Mallet.java

**Sound effects (.wav files)**
+ applause.wav
+ bounce.wav
+ drumroll.wav
+ fanfare.wav
+ hit.wav

The folder **\docs** contains JavaDoc documentation. Click on *allclasses-index.html* to access the documentation.

The controls for the game are displayed on screen at all times. We start in the menustate, where the user can use the controls displayed to play, exit, cheat or mute the game. From there we go into the gamestate if 'P' is pressed, and the mallets and puck spawn centrally on the vertical axis, with the mallets in seperate halves. The mallets cannot exit the table or cross the center line. The puck cannot exit the table, and bounces off the walls and mallets. Upon touching the grey goals, a point is awarded to the player controlling the mallet of the other half. The controls for the mallet, score, timer, and muted status are displayed at the top of the screen. If the game is cheated, the players can place the puck back into the center with the space bar. The game lasts a total of 150 seconds, where after which the winner is displayed, and we quickly go back to the main menu.

I have made a TextBox class, combining elements of the rectangle and text classes for convenience.
The Puck class contains all the puck related logic, methods and members.
The Mallet class contains all of the mallet related logic, methods and members.
The Table class contains instances of the mallets and puck, and oversees the updating of each.
The Gamestate class contains an instance of the table, and oversees the drawing of all the elements, and the updating of the table.
The Menustate class contains all of the menu related members and methods.
The Main class contains all of the logic required to run the game.

Every method has a comment above it with a brief description of its purpose.
Every member in each class is private, with a getter and a setter if necessary.
