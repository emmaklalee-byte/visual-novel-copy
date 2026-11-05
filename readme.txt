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