# AndroidTodo
To Do List tutorial for Android Development
## Requirements
- [Android Studio](https://developer.android.com/sdk/index.html) : You will need this IDE to develop for Android.

## Recommended
- [Genymotion](https://www.genymotion.com) : This is a really nice Android emulator that will make testing your app a whole lot easier.
- Basic Knowledge of Java

## Step One: Introduction Setting Up MainActivity
- After Android Studio is installed open it up and create a new project
- The first thing to do is set up the the MainActivity layout (this is the view that the app first opens when it starts)
- Make sure that the MainActivity is a RelativeLayout (check activity_main.xml).
- Drag a ListView to the activity_main.xml view make sure it has id @android:id/list
- It should look something like this:
<br>
<img src="http://nisargap.github.io/AndroidTodo/images/StepOne.png" width="350">

## Step Two: Setting Up the Task View
- Create a file under the layout directory called "task_view.xml"
- Drag a TextView and a Button the the view (this is for displaying each of the tasks and the done button is for deleting a task after completion)
- Make sure the line android:id="@+id/TodoLayout" is added under the RelativeLayout tags in the task_view.xml file
- It should look something like this:u
<br>
<img src="http://nisargap.github.io/AndroidTodo/images/StepTwo.png" width="350">

## Step Three: Creating the Add Task Menu
- Under the menu directory delete any file that is there and create a file called menu.xml and put this code in it
```xml
<?xml version="1.0" encoding="utf-8"?>

<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:androidtodo="http://schemas.android.com/apk/res-auto">

    <item android:id="@+id/action_add_task"
        androidtodo:showAsAction="always"
        android:icon="@android:drawable/ic_menu_add"
        android:title="Add Task" />

</menu>
```
- In your MainActvity.java look for the onCreateOptionsMenu method and make sure that the method contains this code:
```java
  getMenuInflater().inflate(R.menu.menu, menu);
  return true;
```
