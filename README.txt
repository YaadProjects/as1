liangmah --HabitTracker
assignment 1 CMPUT301 Fall 2016
Name: Kelvin Liang
CCID:liangmah	
Video URL: https://www.youtube.com/watch?v=6FVcPuF5KwU&feature=youtu.be

NOTE: Tested the code on the lab machines and I had to change the compileSdkVersion to 23 and
      buildToolsVersion to "23.0.3" for the code to build on the lab machine. I pushed these changes to github. I'am assuming 		that this is what you guys wanted. 


ACTIVITIES:

HabitMainActivity	
	HabitMainActivity is the backbone of habitracker. It begins upon application startup and is continually running throughout the program life span. 
	A list of habits for the current day of the week will be listed with info of when each habit was created. Each habit is clickable for more information
	regarding the habit. The top right '+' button will bring the user to another menu where they can add new habits. At the bottom of the screen are 3 more 
	buttons. "Change Day" brings up a dialog menu where the user can change the current day of the week to view other habits. By default the actual current day of the week will be loaded. "History" will bring the user to another menu where a list of ALL habits completed at any time will be listed. "Clear" is used to delete ALL existing habits. Button was primarily used for testing during the development process. 

habit_add_activity

	This activity class handles the process for adding new habits to habitracker. Upon start there will be a question at the top of the screen prompting the user for the days of the week that the new habit will occur. At the bottom the user can enter the name of the habit. Two buttons exist, one to confirm the addition of the defined habit and a second to cancel the habit addition request. If user fails to define a habit name or any days of the week habit will not be added. 

habit_history

	This class displays history about the currently selected habit and allows the user to add completions and to delete the habit. The title of the habit and date of habit creation is listed at the top of the screen. In the middle a count of how many times the habit has been completed will be shown and a list of the days in which habit is to be done is shown. Furthermore at the bottom will be a checkbox which indicates if the habit has been done for the current date. Three buttons are at the bottom of the screen. "Completed" lets the user add to the completion count for the habit, a date and time of completion will also be recorded, if task was not previously completed for the day, the Completed Today checkbox will be checked off. "Delete" allows the user to delete the habit. "Done" lets the user return to the main menu. 

completed_habits_activity

	This class displays the list of all habits completed by the user. There exists one button to return to the main page and each habit in the list is clickable. If a habit is clicked another page is loaded which displays each date and time the selected habit was completed. 

completedHabitHistory_activity

	This class displays ALL dates and times in which the currently selected habit was completed. Each individual date is clickable, once clicked a prompt will pop up asking the user if they want to delete the date of completion. Upon saying yes the date will be deleted and the completion count of the habit will decrease

CLASSES

Habit
	This class handles all habit information, it is largely based on the lonely twitter code

SaveLoad_Controller 
	This class handles the saving and loading of data for habitracker, also largely based on lonely twitter code 



Attributions

Below are links to websites in which I used other people's works for learning purposes and for snippets of code used in liangmah--HabitTracker

https://developer.android.com/training/basics/firstapp/starting-activity.html
	-- used to learn how to use intents to start activities

http://stackoverflow.com/questions/1124548/how-to-pass-the-values-from-one-activity-to-previous-activity
    -- used to learn how to pass data  between activites using intents

http://stackoverflow.com/questions/12934661/android-get-current-date-and-show-it-in-textview
	-- used code by "Android_Addict" to format dates to strings

http://stackoverflow.com/questions/6663500/set-color-to-a-textview
    -- used to learn how to set textcolor 

http://stackoverflow.com/questions/19155109/android-list-view-setonitemclicklistener-not-working
    -- used parts of code to learn and use in habitracker for the purpose of allowing my lists to be clickable

http://stackoverflow.com/questions/26722001/android-when-do-we-use-getintent
	-- used to learn how to retrieve data from other acitivites 

http://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
	-- used to learn how to pass Object classes 

https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
	-- used to learn how to create dialogs

http://stackoverflow.com/questions/25261296/how-to-save-a-checkbox-state-in-android-app
	-- used for checkbox 

https://futurestud.io/tutorials/custom-fonts-on-android-quick-and-dirty
	-- used to learn how to use custom fonts 
