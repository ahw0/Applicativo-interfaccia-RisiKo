# RisiKo application interface

Application for end users to interface with the RisiKo database to use all gaming actions.

## Index

1. [Application-guide](#Application-guide)
  * [Login and customization screen](#Login-and-customization-screen)
  * [Main screen](#Main-screen)
  * [Sections](#Sections)
  	* [New match](#New-match)
    * [Resume play](#Resume-play)
    * [Tools](#Tools)
    * [Download](#Download)
2. [Play a game](#Play-a-game)
  * [Preliminary phase](#Preliminary-phase)
  * [Positioning phase](#Positioning-phase)
  * [Combat phase](#Combat-phase)
  * [Moving phase](#Moving-phase)
3. [Get some information](#Get-some-information)
  * [Retracing a game](#Retracing-a-game)
  * [Managing Log file](#Managing-Log-file)
4. [Other information available](#Other-information-available)
  * [Player](#Player)
  * [Turn](#Turn)
  * [General](#General)


# Application Guide

This is a guide that allows you to use the application with [its](https://github.com/simonemargio/Database-RisiKo) database.

## Login and customization screen

When the application starts, the login menu is displayed, through which you can perform the following actions:

- **Login**: login with username and password for database that you want to use.
- **Opzioni**: specify database access parameters.
- **Esci**: exit.

If you go to **[Opzioni]** you can make changes on the connection to the database.
From here you can make appropriate changes to access the database. Among these are the customization of:

-	**Host**
-	**Servizio** 
-	**Porta** 

Once you change the information, you can proceed by pressing the appropriate button **[Applica]** to make it effective.
The case where you want to restore the default access parameters will have to press the **[Ripristina predefiniti]**.

The default parameters are also written and displayed automatically at each opening of the screen.
When you finish the customizations, the user will be notified by means of a pop-up containing all the parameters he has edited.

Each database access customization field has appropriate controls to avoid entering long strings or characters in fields where only numbers are required.

You can go back to the login menu. Access by right credentials leads the user to the application's main screen, opposite case will be notified of a login problem.

## Main screen

The main screen is the heart of the program where the user can perform all the actions that allow the management of a RisiKo game.

Features include:
- **Nuova Partita**: creating a new game to play RisiKo.
- **Riprendi Partita**: resume a left pending game.
- **Tools**: retrace a game and get additional information about players or a turn of play.
- **Download**: offers the ability to download files containing the database to be able to use the application and a test population.
- **Esci**: exit.

The user can select by pressing the appropriate button to access one of the present mode and its features.

## Sections
### New match

When creating a game, you are asked to specify various parameters such as:

- **Nome partita**: name to be assigned to the match. Used subsequently in most interactions where the user has to specify the reference game (eg, resuming an old match, getting player information, and more).
- **N° Giocatori**: selectable with the drop down menu allows you to specify the number of players who want to participate in the game.
- **Nome Giocatore**: name or nickname of the players in the match.
- **Colore**: the color chosen by the player with whom he intends to play.
Depending on the number of players selected (minimum three players) will be made available or not the fields for entering such information.
 

Once ready, you can start the game by pressing the **[Inizia Partita]** button.

Opportune controls avoid inserting special characters and spaces that can lead to a general malfunction; as well as fields left completely empty. Each field is also set to accept a maximum length of predefined characters (match name: max 20 characters, player name: max 10 characters).
To avoid picking the values you have chosen and then checking for them and possibly informing you about their non-use, you have preferred to rely on events such as Key Pressed and Key Typed that you created ad hoc for not accepting non-consonant characters.

Among the controls present, the game is not created when:
- There is already a match with the same name chosen by the user.
- There are two or more players with the same nickname (management is case sensitive, meaning a player "Pippo" is different from the player
"pippo").
- There are two or more players with the same color. - Empty fields are present.


In such cases, the user is informed of a mistake made with a suitable notice.


### Resume Match

All matches that do not yet have a decree winner can be resumed with this feature.
The user will be shown the list of all the items present. Choosing the name of the game must be written in the appropriate field below and then you can proceed by pressing the **[Riprendi Partita]** button.

Actions in a game are divided into three phases: positioning, fighting, moving. When you decide to stop a game you will necessarily find yourself in one of these stages. Upon his resumption, the application verifies the player's turn (that is, the last round corresponding to the player to whom he touches) in which of the three stages there is not yet a "line" with player information. Necessarily it will be one of the three, and the first one will be just the playing phase to be resumed.

### Tools 

Allows the user to get all the information on a game. In the top left, there is a box containing the name of all the matches that are present.
Choosing the name of the match and writing in the field instead of "nome partita..." you can proceed to:

-	**Ripercorri la partita**: are shown in the big box, under the "riassunto della partita" entry, all the step-by-step information about the game. More details on
[Get some information](#Get-some-information).
-	**Elimina la partita**: delete a present game and all the information saved on it.
-	**Altre info**: more information about: players, shifts and general.

When a game is played back, in addition to getting all the information in the big box you will also see some information such as:

-	**Stato della partita**: can be of two types: 
     * In corso: unfinished match without any winner.
     * Finita: match ended with a winner.
-	**Vincitore**: depending on the status of the game there will be the nickname of the winning player or the word "Nessuno".
-	**Totale turni**: numeric value that expresses the number of shifts that were reached during the game.
-	**Totale combattimenti**: numeric value that expresses the number of fights that took place during the game.

### Download

Area where user can download files and scripts:
-	**Database**: file to proceed with creating the database according to the rules of the game RisiKo!
-	**Popolamento**: file that shows a popup example that you can run in the database.

Each game in the database is registered by a numeric code, ie integers ranging from 1 to N. For example, the "MulinoBianco" game has a code of 4, while the "Frollino" game has a code of 5.
If you download the popup file, the value for the associated match is equal to 1. This means that if a match with that associated value is already present, the popup script will not run.
The choice of value 1 is derived from the motivation that the popup script is executed when the database is "naked and raw", just as a sample file used before proceeding to any other insertion through database and / or application.


# Play a game
## Preliminary phase

Once a game is created, the preliminary phase of the game begins.

The phase involves the insertion of a predetermined number of armies (shown on the screen below "N° armate max da inserire") in one or a maximum of three territories belonging to the player.

In the top part you can view information such as:
-	**Tocca a**: shows the nickname of the player to play the action, in this case the insertion of armies. - Il tuo ID: identifier associated with the player.
-	**Partita N°**: identification associated to match/number of match.
-	**Nome Partita**: name that was given by the user to the game.
-	**N° armate max da inserire**: are the maximum number of armies that the player can enter in one or more territories.
-	**N° armate totali**: are the number of armies that are still to be entered.
In the lower right there is:
-	**N° turno**: the numerical value of the round of play.

***More details on N° turno***: the game rotates around the turns, expressed in this case by numeric values from 1 to N. In each turn there is always a player associated with it (example at round number 3 there is associated "Simone", the number 4 "Pluto" and so on). In simple words, the turn corresponds to "who touches play now?".

On the left, instead, you indicate the territory **[I tuoi territori]** of the player and the number of armies in it **[Carri sul territorio]**. Initially each territory will have one armed army to guard.

The information about: **[Il tuo ID]**, **[Partita N]** and **[N° turno]** can safely be neglected by the player (they are present to verify and display the correct continuity of the game) as the remaining information is enough to be able to enter armies.

The last part, on the right, followed by the word **[Posizionamento]** consists of three parts:
-	**Nome territorio**: enter the name of the territory where you want to add the armed forces.
-	**N° armate**: the numerical value of the armed forces that you want to enter in the territory previously expressed.
-	**Abilita territorio**: if player decides to insert armies into another territory, he can enable that entry by ticking the corresponding box.

***More details on abilita territorio***: as long as the armies are not finished, as per regulation, each player must have at most 3 armies in each territory.
For strategic reasons, a player can, for example, place 2 armies on territory A and 1 on territory B.
To make such placements, the player will write A in the first field under "Nome territorio" and the value 2 in the field next, then check the second (or third) checkbox to write the B territory and the value 1.
Up to three territories can be empowered as maximum three armies can be placed in three territories (1 army on each territory).

In each field, copy-paste information is forbidden to prevent copying of some special characters that may cause subsequent insertion into the database. Additionally, appropriate checks verify that the name of the territory is correct and that it belongs to the player. Of course we do not want to make enemies stronger by giving them armies!

At least one territory chosen and the number of armies to be entered can be done by pressing the **[Inserisci]** button.

The preliminary phase lasts until all the players have armed to add. Once each player has finished the number of armies he gets into the game's life.

It always starts from turn number 1, actually there is also turn 0.
This is used by database to understand that players who want to participate in the game are over and that you can go to the next stage of preparation.
At this stage, the database randomizes the territories and assigns them to the players (placing 1 armed on each territory), assigns the goals and sets the round that the players have to do (this is not to say the id number player 1 is the First to start).

## Positioning phase

When the preliminary phase is over, the real phase of the game begins. It is divided into three parts:

-	**Posizionamento**: the player has the right every turn to enter N armed (calculated on the basis of his territories or a combination of cards) in his territories.
-	**Combattimento**: you can decide whether or not to attack the enemy territories with your own armies.
-	**Spostamento**: you can decide whether or not to move a certain number of armies from one territory to another.
The positioning screen contains some of the information already seen.

Additionally:
-	**N° armate max che si possono inserire**: numeric value that expresses the exact number of armies that the player can enter in one or more of his territories.
-	**Tipo inserimento**: the player can choose how to get the number of armies by specifying the type of insertion. It is divided into:
     * **Inserimento classico**: the number of armies is calculated by how many the territories occupied by the player divided by three. Additionally, if the player occupies all the territories of one or more continents, he is entitled to an additional number of armies.
     * **Inserimento combinazione carte**: includes the number of armies calculated by the additional classical addition of additional armaments given by the card combination used by the player.
     ***Note: on the number of armies read the rules of the game.***
-	**Le tue carte**: cards that have in hand the player. For convenience, they are expressed in a single letter (C – Cavalleria, F – Fante, A – Artigliere, J – Jolly).  
-	**Il tuo obiettivo**: the goal that has been entrusted to the player is indicated. If a player reaches his goal, he is declared a winner, completing the game.
-	**Territori confinanti**: For strategic reasons, the player of the turn can enter any territory name (his or her) in the field under the name "Nome territorio" and pressing the **[Mostra territori confinanti]** button will show all the adjacent territories to that chosen.

***More details on N° armate max che si possono inserire***: expresses as stated the maximum number of armies that the player can enter. This value can be calculated in two ways depending on the player's choice.

Depending on the type of insertion type, the value of "max armata that can be inserted" varies.
In case, however, card combination insertion is selected, the panel will be enabled under the letter "Carte" as shown.

The player can choose which combination of cards to use based on the cards he has available (under the box "Le tue carte"). To facilitate reading close to each combination, there is a combination of letters that the player must own. For example, if the player has the following cards: A - A - C - F - A, he can choose to use the FCA combination or the AAA combination.
Once the correct combination is selected, just press the **[Usa combinazione]** button and automatic will update the number of massive armies that you must enter.


A gambling card consists of two information: territory and symbol. The territory is one of the territories in the game map while the symbol may be one of the characters: C, F, A, J. The rules of the game provide that if the player in the combination of cards used also has the territory of one or more cards, he is entitled to a supplement of 2 additional armies.
So what if the player has in his hand cards like: F - C - A - A, where only one of the As is also associated with a territory that he owns?
Since the combination involves F - C - A, which of the two A is taken to form such a combination?
The most obvious answer is to consider A that also includes the territory owned by the player, so that they have an even greater number of armies to insert.
And it is also the answer given by the database. In fact, the database begins to search for the best A card, which also includes a territory owned by the player.
So in this case, two A will be selected between two.


Once you have selected an insertion type, the button **[Inserisci carri]** will be enabled.
The player can then enter the name of a territory he owns (visible in the "I tuoi territori" screen) in the appropriate field under "Nome territorio".
You can then choose the number of armies that you want to place on the territory by writing in the field below the "N° Armate". Infinite just press the **[Inserisci carri]** button to make the insertion. The number of armies will be instantly updated in the "Carri sul territorio" screen for the chosen territory.
The player may also decide to place the armies he has in multiple territories.
Once you have armed numbers below the maximum number of Arms that can be inserted; just delete the name of the first written territory and enter a new territory name with its armed forces.
 
Once you have entered all your armies, you go to the next step.


## Combat phase

Both combat phase and the next phase of displacement fall into the optional stages. The player is free to choose not to make any fighting or move.

At this stage, the button is located at the bottom of the center **[Finisci i combattimenti]**.
This button can be used for:
-	Go to the next step without having to do any fighting. Simply press it once the combat screen appears.
-	Go to the next step after having N fights.

As in the previous screens, there are already described information.
To best describe the remaining information and the entire screen, we follow step by step a possible fight.

The turn player decides to make a fight. The territory from which to start the attack will be Ontario. This is written
In the box under “Nome territorio attaccante”.


As the player does not know which enemy territories are bordering on the attack territory, he decides to press the button **[Mostra confini nemici]** to obtain the information on:
-	**Territori nemici confinanti**: the names, if present, of all territories not owned by the player bordering the territory he has expressed in the “Nome territorio attaccante” field.
-	**Carri sul territorio**: enemy tanks that are present on the territory.
-	**Colore**: color of the enemy, useful for strategic purposes. 


Once you have chosen the attack area and the one to attack, we are ready to attack!
The player presses the button **[Prepara all’attacco]**. 

Go to the second phase of the fight, the dice roll.
As you can see from the figure under the button **[Prepara all’attacco]**, the information on the top right of the screen will be updated:
-	**Nickname difensore**: is the nickname of the player he is attacking.
-	**ID difensore**: the id of the defender who has been assigned.

To launch the dice, first specify the number of dice to be fired. This can be done using the drop-down menu for both the attacking player and the defensive player (see below). The maximum number of dice that can be launched by both players is 3.
Obviously, the number of dice to use depends on both the strategies used by the players and the number of armies in both territories. In fact, each nut is associated with an armor.
If the attacking player has 2 armies he can not cast 3 dice as he can not throw 2 (remember that according to the rules of the game on the attacking territory there must always be 1 armed guard, so having 2 armies will not be able to launch a 2 Because in case of defeat the territory would be lost, in violation of the regulation).


When selected the number of armed ones, the boxes below will be enabled where the values of the darts launched will be entered.
Here's an example:

When it is all ready, the player must only press the **[Attach]** button to start the fight.
It is also possible to decide that you no longer want to attack the established territory, in this case just press **[Cambia territorio da attaccare]**. 
In this case you decide to stick. Once the button **[Attacca]** is pressed, the player will be notified of the outcome of the combat, obtaining information on the number of armies lost (both of the attacker and the defender).

He outcome of this fight has revealed that the defender has lost one wagon. The highest value was dropped by the attacker's nut (5) and was compared with the highest value of the defender (1), thus leading the defender to lose a number of wagons equal to the number of darts thrown.

When the combat is over, the player can:
-	Decide to stick again simply by writing down the value of the new dice rolled up and pressing the **[Attacca]**.
-	Change the territory to attack by pressing **[Cambia territorio da attaccare]**.
-	Finish the fighting by pressing **[Finisci i combattimenti]**. 

Let's suppose we want to continue the attack until Alberta is no longer armed to defend it.
In this case, the attacking player will conquer the territory and a notification of the type will appear.

Conquered the territory, as written by the notification, are moved N armed as the number of dice rolled.
However, the player may also decide to move all the armies (except one who has to stay) from the attacking territory to the one gained. To do so just press the button below **[Sposta tutte le armate]**. 

Having conquered the territory of Alberta, you will find it in the "Your Territories" list with the number of armies established (in this case the attack was made using 2 dice and then 2 armed were moved).

***Further information***: Every territory won is verified if the defending player has lost the last territory he had. A positive case is notified to the user that the defending player has lost. Likewise, it is verified that the attacking player, who has conquered a new territory, has achieved his goal.
A positive case is notified to the user that the attacking player has won the game and is brought back to the main menu.

When the fighting is over, you decide to go to the next step by pressing the button **[Finisci i combattimenti]**. 


The combat phase is the one that has multiple controls and calls to the database compared to all the other application screens. Among the many controls you go to check for example: if the player has the territory from which to start the attack, if the attack territory is confined to the one to attack, if both territories are not the same player, if it is present More than 1 armed in the area of attack and many others. The same applies to the launch of the dice, as well as the call to appropriate database functions to verify the outcome of the fight, or to verify the player's victory or the defeat player defeat.



## Moving phase

It came to the last stage of the game that ends the player's turn, the move.
As in the combat phase, you can still choose whether to make an armed move or not to do so by just pressing the **[Non effettuare nessuno spostamento]** button.


In case you want to move a number of armies from one territory to another, you must enter the name of the starting territory in the box under "Nome territorio di partenza".

Since a move can only take place between adjacent territories belonging to the same player in the current round, pressing the **[Mostra confini]** button will show, if present, the neighboring territories belonging to the player where the armies can be sent. In this case, Venezuela's territory has a neighboring territory belonging to the player himself in Brazil.


Choose the territory where the armed forces are to be moved and written in the box below
"Nome territorio d’arrivo" has to specify the number of armies to be moved to "N° armate da spostare".

All it takes is to simply press the armed push button to receive the correct shift notification.


This completes the last phase of the player's turn and will resume from the first "Posizionamento" phase with the next player.

The round then created "Placement - Combat - Shift" will only be interrupted if:
-	A winner is proclaimed.
-	You decide to pause the game by pressing the X icon on the screen where it is located.

## Get some information

### Retracing a game

It is possible to find step by step every single move that took place in a game by pressing the button **[Ripercorri la partita]**. 


The information that is shown in the big box below the text
"Riassunto della partita" is found from the file in the main path where the application is started.

## Managing Log file

File management is done as follows:

The file is generated by creating the game by naming it and adding the text ***"_ log.txt"*** as an identifier.
Normally, when a game is created, it is not possible to have a file with the name of the game; This is because it would have in the database a match with the same name as the one you want to create, thus leading to the non-creation of the game and therefore of the new file. If a file that has the same name as the game you are creating and that name is not present in the database, the file is overwritten.
 
During the course of a game, all relevant information is taken to reconstruct it step by step.
All information is converted to a string and sent to a special function that "opens" the file, inserts the string at the end and
"Closes."

The set of all strings allows you to get a single file where there are information about:
-	Information about creating the game. Includes date, number of players present, nickname and color chosen.
-	All the placements in the stages: preliminary, positioning, fighting and moving. It includes territories, number of armed forces, actions taken
-	All the fights done. Includes the number of launched dice, the outcome of the combat, missed armies.
-	Learn more about: moments when the game was paused, resumed the game, losing players, winning player.

The file is deleted if the game is deleted by pressing the button **[Elimina la partita]**. 

Some questions:

**Q**: What happens if I delete a game and the file has already been deleted?

**A**: You are notified to the user that the file has probably already been deleted but to avoid problems if it is present, to delete it.

**Q**: If you delete a file during a game, what happens?

**A**: Before writing anything on the file, it always verifies its existence.
The case where the file is no longer present will be re-created, inserting inside a notation that informs the user that all the old information is lost, then you continue to write to the file all the information in the game.

**Q**: Does the file contain all but all the information?

**A**: There are some information that is not in the file but are obtained by accessing the **[More Info]** button. Missing information is: dice dice, goal assigned to players, cards that players have in hand.

**Q**: Is the file manually created before the game is created?

**A**: When the game is created, the file is overwritten.

## Other information available



Additional information can be obtained by switching on the screen shown in the picture using the **[Altre info]** button on the “Ripercorre una partita” screen.

All information available, unlike the file, is obtained directly by querying the database.

Among these are:
-	**Giocatori**: the game is selected and a player is provided with various information about the player. For example: all the conquered territories, the cards in hand, the total army that is still in play and others.
-	**Turni**: the game is selected and one turn shows all the actions taken in the three phases (positioning, fighting, moving) from the player of the chosen turn.
-	**Generali**: includes a generic collection of general information. For example: the number of matches present, the number of all the fights of all parts, the territory preferred by the players and others.

### Player


The player information screen can be divided into two parts.
The left part (which includes the "Passo 1/Step 1", "Passo 2/Step 2") where the interaction of the user is requested and the right part where all the information will be displayed.

Analyzing the left side you will need to start from "Step 1" and specify the name of the game from which to retrieve the information. Once the game has been selected, it will be written in the field under the entry "Enter the name of the match".
Once done, you must press the ** Mostra i giocatori]** button.

You will then go to "Step 2" where in the box at the bottom will be the nicknames of all the players of the chosen game.


You will then need to select a player's nickname and place it in the field below the wording “Inserisci il nome del giocatore”. 

To get the information, just hit the **[Mostra info]** button. Instead, if you want to change the game to get information on other players, you need to press the **[Cambia partita]** button and repeat the procedure starting from "Step 1".

Once the **[Mostra info]** button is pressed, all the fields on the right side of the screen will be enabled, as shown in the figure below.


Among the fields present we find:
-	**Nickname**: name of the selected player.
-	**Colore**: player's chosen color.
-	**ID conferito**: the numeric value that has been attributed from the database to the player.
-	**N° armate in gioco**: the number of armies that the player has in all his territories.
-	**Obiettivo**: objective assigned to it.
-	**Carte che ha in mano**: card symbol that the player can use.
(For convenience the symbols are shown in their entirety).
-	**N° combattimenti fatti**: the number of fights that the player did during the match.
-	**Quante volte ha tirato il dado**: how many times, during the game, the player rolled the dice.
-	**N° territori conquistati**: number of territories that managed to conquer destroying enemy armies.
-	**Combattimenti effettuati**: the territory from which the attack was launched and the attacked territory are shown in pairs.
-	**Territori conquistati**: name of all the territories conquered.
-	**Territori ordinati in base al numero di armate**: all the territories belonging to the player are listed, giving priority to the territories with several wagons.
-	**Giudizio finale**: based on certain parameters, a judgment is made to give any advice to the player or to encourage him to reach his goal.


### Turn


As already explained, this screen can be divided into two parts.

The user must choose and write the name of the match to "Step 1" and press the button **[Mostra i turni]**. 
After "Step 2", all the rounds that took place during the course of the match will be shown with the related player nicknames associated with it.



Once you have chosen the turn you will have to enter the numeric value in the field below “Inserisci il numero del turno”. 

Pressing **[Mostra info]** will display all the information on the right side of the screen. An example is shown on the next page.

Instead, if you want to change the game to get information on other game rounds you will have to press **[Cambia partita]**. 


Among the fields present we find:
-	**N° turno**: the number of the turn that was chosen.
-	**Giocatore**: nickname of the player who did the actions in the turn chosen.
-	**N° di posizionamenti effettuati**: the number of number of wagons placed on the player in that turn.
-	**Somma delle truppe inserite**: if the player makes multiple placements, all troops will be added.
-	**Altre informazioni (Posizionamento)**: the territories, the number of armies, and the positioning type (preliminary - classic - by card combination) are shown.
-	**N° combattimenti effettuati**: number of fights during the round.
-	**Altre informazioni (Combattimento)**: are shown the area of attack and the defending territory, all the combinations of dice that were launched and the outcome of the fight (whether or not it led to the conquest of the territory).
-	**N° di spostamenti effettuati**: number of shifts (between 0 and 1 as shifting is optional or can be done only one).
-	**Altre informazioni (Spostamento)**: the displacement is displayed giving information on the departure territory, arrival territory, and number of armed forces moved.

### General


The ***“Generali”*** screen shows general information taking into account all the items present.

Among the fields present we find:
Numero totale di: shows the sum of all the information of all the matches taken together.

-	**Partite presenti**: matches that were created.
-	**Giocatori presenti**: all players.
-	**Dadi tirati**: total number of dice that are streaked during the matches.
-	**Armate**: all the armies present on all continents.
-	**Turni di gioco**: the rounds played.
-	**Combattimenti/Posizionamenti/Spostamenti**: total of all the game phases performed.

**Numero totale di inserimenti**: shows the sum of information about the type of inserts that each player has made.
-	**Preliminari**: more information above.
-	**Classici/Combinazione carte**: more information above.

Territori: there are information about the territories.
-	**Territorio preferito dai giocatori**: shows the territory that contains more armies than all the items present.
-	**Territorio più trascurato**: shows the territory with the smallest number of armies of all matches.
-	**Territorio più attaccato**: shows the territory that has suffered more attacks in the fighting phases.

**Altre**: information about cards and goals.
-	**Combinazione di carte più usata**: shows the combination of cards most used by players of all matches.
-	**L’obiettivo più presente**: 
shows the goal that has been assigned to the players.

