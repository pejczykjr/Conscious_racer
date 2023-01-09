# Conscious racer
<b>The goal of this project is to create the game for university classes. It is aimed at practicing motor-visual agility and familiarization with basic traffic law.</b>

## Game description
This game is designed to enable users to improve their motor-visual coordination and practice knowledge of the traffic law. 
User will steer the vehicle in 3 lanes. His task will be to get as many points as possible.
After a certain time, more traffic signs, traffic lights or asking the player to choose the correct answer will be displayed.
The game will require the user to react quickly while moving the car and passing other cars, and the player will have a limited time to respond or interact, such as:
pressing the spacebar to stop at a red light or after displaying a information about the road with priority given
marking a choice about not turning when seeing a sign telling you to go straight.

<p align="center">
<img src=https://github.com/pejczykjr/Conscious_racer/blob/master/Pictures/Cars%20and%20light/game%20concept.png width=50% height=50%/> <br>Game concept </p>

## How to play?
<ins>Menu:</ins> There is a menu at the beggining of the game, where are 3 different options:    
-   Play - starts the game
-   Score - shows highest achieved score, can be reseted
-   Exit - quits application

<ins>Game:</ins> To move on the map user is able to use keys WASD. Player can move car upwards, downwards, to the left and right. His task is to omit upcoming cars from the top of the screen. Player's speed is dependent on enemy's car speed- in x-axis speed is equal, but in y-axis it is half of its speed. Every 6 seconds speed of enemy's car will be increased, so player's too, and also point will be added to score. After some time in different time intervals there will appear traffic signs and lights. User has to make the right decision during an interaction, but has also limited time for that. After a collision with another car, expiration of the response/interaction time or an incorrect choice, the user gets penalty points. Game has 2 levels- in level 1 there will spawn 2 enemy cars (sometimes in one lane), but in level 2 only one, because of extremely high speed. The game ends when the time expires. During the game player can press Esc key to stop it, then Paused Game Menu will appear.  

<ins>Paused Game Menu</ins> has 3 different options: 
-   Resume - resumes the game where it stopped  
-   Menu - moves the user back to the menu, resets the game
-   Exit - quits application    

<ins>Highest score</ins> keeps the best user's score. User is able to reset his highest score. After playing the game, it will be updated and so every time when result improves.

## Project setup instructions
<b><ins>Configuration:</ins></b>    

`1. Replace pictures' paths to your own.`

    -   Class GameObject, lines 46-52.
    -   Class Handler, line 32-34. 
    -   Class Menu, line 69.

`2. Set time of the game you want to spend on it.`

    -   Class Hud, line 19.

`3. Set FPS you want to play at.`

    - Class Game, line 37.

`4. Set time after enemies speeds up.`  

    - Class Spawn, line 91.

## Project requirements/steps
- [x] Creation of images
- [x] Back-end implementation
- [x] Menu handling
- [x] Paused game handling
- [x] File handling- saving highest score
- [x] Functional part- race
- [ ] Additional part- traffic law

## License info
Author: [Mateusz Pieczykolan](https://github.com/pejczykjr)
