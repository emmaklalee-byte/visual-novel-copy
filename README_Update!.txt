TODO // Multiple End Screens
TODO // Better looking GUI => maybe we can get icons instead of text for the buttons?
TODO // depending on time, we can try and make a start menu + CG cutscenes + save/load function

README_Update!

No changes to the uses of background/character
Again, only Mouseclick.java needs to be run

HOW TO USE CHOICES AND THE NEW BACKGROUND FORMAT:
The format is as such:
//
CHOICE
[integer n representing how many choices are available]
[name of choice 1]
[name of choice 2]
[name of choice n] (Make sure that there are no unwanted spaces!  This may cause some issues)
[name of choice 1]@@
[char number] your text goes here
[name of choice 2]@@
[char number] your text goes here
...
//
The above is the checkpoint that the 'name of choice 1' button will jump to.
Note the '@@' component--I assume that this won't show up in the regular story text all that much
If it does, feel free to change it in the jumpTo(searchString, dataBase) function, but remember to   change the corresponding text in the test1.txt file and the if statement on line 220
Also note that the char corresponding to character is not necessary for any of the choice commands

Backgrounds are initialized as such:
_1
_2
_[number corresponding to desired background]
A choice that jumps to an earlier or later point in the script will automatically initialize the most recent background

I recommend you take a look at the test1.txt file and run the program to get a feel of how it works!  If there are any issues, feel free to ask me~
-Emma Lee 2023