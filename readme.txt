This is a visual novel tool that can export visual novel.
Script and  images are required as input.
To run it, you only need to run the Mouseclick.java file.

The script should be put into the "txtresource" folder, and named as "test1.txt".
In the script, the very first char of every line is used as a signal of character, so it will not be shown on the screen.
If you want to change the background, just leave the line as "_". (See the example in the test1 file)

The image of the background and characters should be all png files.
background images should be put into the "pngresource/background" folder, and character image should be put into the "pngresource/character" folder.

*******VERY IMPORTANT!!!********
It is a must to point who the character is in every line of script so that the programme can link the image with the dialogue.
You can use 1~9 to express the characters, so now it is able to handle 9 characters in total. (There is a way to handle more, but I'm lazy... Tell me if the number of characters is over 9)
THE MOST IMPORTANT PART is that the image files are read FROM THE END TO THE BEGINNING, so the last file in the folder will appear first for the background images, and the last image in the character folder will be 1 when in the script.
The size of the background picture should be around 1300*500. The sixe of the character image should be smaller than background.

There isn't any choice function, if you guys would like to write I'll be very happy.
If there's any difficulty or bugs, just pointed out and ask me to try to fix it. (But I'm just a greenhand I can't promise that I can fix all of the questions....)

Written by Ruoxi Luo, 1/19/2023

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
