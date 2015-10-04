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
- In your MainActivity.java look for the onOptionsItemsSelected(MenuItem item) method and change its code so it looks like:
```java 
// your method should look like this
@Override
public boolean onOptionsItemSelected(MenuItem item) {
      
      // switch case to perform a menu action
      switch (item.getItemId()) {
            
            case R.id.action_add_task:
            
                  // testing to see if add button works
                  // should print Add a new task in the console
                  Log.d("MainActivity", "Add a new task");
                  return true;
            default:
                  return false;
      }
                  
}
```
## Step Four: Testing the Add Button
- Now that we wrote some code for an add button lets do some testing!
- For those using Genymotion:
      - Open up Genymotion from your desktop
      - In Genymotion click on the + symbol to add a new virtual device
      - Create a Nexus 6 Android version 5.1.0 that uses API 22
      - Start the Nexus 6 emulator
      - Go to AndroidStudio and click on the green triangle to play
      - Select the open Nexus 6 emulator
- For those not using Genymotion:
      - Click on the play button select the default Android emulator
- Your app should look like:
<br>
<img src="http://nisargap.github.io/AndroidTodo/images/StepFour.png" width="350">
- Now if you click on the + button the console should print "Add a new task"

## Step Five: Adding more functionality to our menu
- Now that we know our menu button works lets add something more useful how about we add an alert box in which you can type new tasks into
- Modify the onOptionsItemsSelected method so it looks like this:
```java
// your method should look like this
@Override
public boolean onOptionsItemSelected(MenuItem item) {
      
      // switch case to perform a menu action
      switch (item.getItemId()) {
            
            case R.id.action_add_task:
            
                  // alert box builder 
                  AlertDialog.Builder builder = new AlertDialog.Builder(this);
                  
                  // sets the title of the alert box
                  builder.setTitle("Add a task");
                  
                  // sets the message
                  builder.setMessage("What do you want to do?");
                  
                  // creates an input field
                  final EditText inputField = new EditText(this);
                  
                  // sets the input field int he alert box
                  builder.setView(inputField);
                  
                  // creates a button to add a task
                  builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                  
                        // handles the on click event for the add button
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        
                              String task = inputField.getText().toString();
                              
                              // prints out the entered task to console
                              Log.d("MainActivity",task);
                        }
                  }
                  
                  // sets the Cancel button
                  builder.setNegativeButton("Cancel",null);
                  
                  // creates the builder
                  builder.create().show();
                  
                  return true;
                  
            default:
                  return false;
      }
                  
}
```
- After adding this code and pressing that green triangle button the app should look like:
<img src="http://nisargap.github.io/AndroidTodo/images/StepFive.png" width="350">

