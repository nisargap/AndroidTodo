# AndroidTodo
To Do List tutorial for Android Development.
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
- Drag a Large TextView and a Button the the view (this is for displaying each of the tasks and the done button is for deleting a task after completion)
- The button should have an id of "buttonDone" and the TextView should have an id of "taskTextView"
- Make sure the line android:id="@+id/TodoLayout" is added under the RelativeLayout tags in the task_view.xml file
- It should look something like this:
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

## Step Six: Storing and Displaying the Data
- Create some private variables in your MainActivity class (put these anywhere)
```java
 private ArrayAdapter<String> taskAdapter;
 private ArrayList<String> items;
 ```
 - Create a method called updateUI in your MainActivity class
 ```java
 private void updateUI() {
      
      // this is the connection to the task_view layout we created
      taskAdapter = new ArrayAdapter<>(this, R.layout.task_view, R.id.taskTextView, items);
      
      // make sure the ListView we created in step one has the android:id = @android:id/list in the main_activity.xml
      // or else this won't work
      ListView listView = (ListView) findViewById(android.R.id.list);
      
      // sets the listView adapter to the taskAdapter
      listView.setAdapter(taskAdapter);
}
```
- Next we need to modify the onOptionsItemSelected
      - Make this part:
      ```java
      Log.d("MainActivity",task);
      ```
      - Look like this:
      ```java
      items.add(task);
      updateUI();
      ```
- Next we need to modify our onCreate method so it initializes our tasks ArrayList and calls the updateUI() method
```java
// your method should look like this
@Override
protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      // initialize items
      items = new ArrayList<>();
      // calls updateUI()
      updateUI();
}

```
- Now if you run your app you should be able to view and add elements to the task list


## Step Seven: Making the Done Button Work
- Go to your task_view.xml file under layouts and click on the Text tab at the bottom
- Add the following two lines to your Button tags
```xml
android:onClick="onDoneClick"
android:id="@+id/buttonDone"
```
- Now since we made the on click action point to a method called onDoneClick we'll have to create that method in our MainActivity
- The method should look like:
```java
// this method handles the onClick action for the Done button
public void onDoneClick(View view) {
      
      // we have to reference the parent view
      View v = (View) view.getParent();
      
      // get the current taskTextView element for that specfic Done button
      TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
      
      // get the string of the task associated with the Done button
      String task = taskTextView.getText().toString();
      
      // find the element in the items ArrayList that has the same text and remove it
      for (int i = 0; i < items.size(); i++){
      
            if(items.get(i).equals(task)){
            
                items.remove(i);
            }
      }
      
      // call the updateUI() to show changes
      updateUI();
}
```
- And we're done! [Download](http://nisargap.github.io/AndroidTodo/release/AndroidTodo.apk) the final result!
- Here's the [database version](http://nisargap.github.io/AndroidTodo/release/AndroidTodoDB.apk) so the data stays persistent on each launch
- You're final app should look like this:
<br>
<img src="http://nisargap.github.io/AndroidTodo/images/StepSeven.png" width="350">

## Resources
- [Android API Docs](http://developer.android.com/reference/packages.html)
- [Android Udacity Course](https://www.udacity.com/course/developing-android-apps--ud853)

## Referencesd
- [Aldo Ziflaj's 2014 Android Todo Tutorial](http://www.sitepoint.com/starting-android-development-creating-todo-app/)
