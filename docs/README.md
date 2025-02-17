# Cinnamoroll ChatBot User Guide

# Cinnamoroll Task Manager
This is a Cinnamoroll themed task manager that has a cute personality.
It helps you manage and organise your tasks including tasks with both with and without deadlines.
It also has some useful features for repeating tasks and tasks with changing descriptions!
The tasks will be stored in a .txt file and will be loaded everytime you open the application

# Quick overview of the ChatBot
Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will prompt the help message.
Some example commands you can try:

list : Lists all tasks.

todo homework : Adds a todo task with description "homework"

delete 3 : Deletes the 3rd task shown in the current list.

mark 3 : marks the third task in the list as complete

bye : Exits the app.


![Screenshot of app 1](/assets/images/Screenshot of app 1.png)
![Screenshot of app 2](/assets/images/Screenshot of app 2.png)


# Features
## Notes about command format:
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo TASK, TASK is a parameter which can be used as todo homework.

## List of commands
1. Add tasks
    * Todos (only description)
    * Deadlines (with description and deadline date)
    * Event (with description and start, end date times)
2. Delete tasks
3. Update description of tasks
4. View list of tasks
5. Clone tasks
6. Marks tasks as done/undone
7. Find tasks with given keywords
8. Help message



### Adding todo task: `todo`

Creates a todo task  
Format: `todo TASK`

### Adding deadline task: `deadline`

Creates a deadline task with deadline  
Format: `deadline TASK /by D/M/YYYY`

### Adding event task: `event`

Creates an event task with start datetime and end datetime  
Format: `event TASK /from D/M/YYYY HHmm /to D/M/YYYY HHmm`

### Deleting task: `delete`

Deletes a task of given task number  
Format: `delete TASK_NUMBER`
  

### Update task description: `update`

Updates a task of given task number  
Format: `update TASK_NUMBER NEW_DESCRIPTION`

### Listing all tasks: `list`

Lists all tasks  
Format: `list`

### Cloning task: `clone`

Clones a task of given task number  
Format: `clone TASK_NUMBER`

### Mark/Unmark tasks: `mark/unmark`

Mark/Unmark a task of given task number  
Format: `mark/unmark TASK_NUMBER`

### Find keywords in tasks: `find`

Find tasks with specified keywords
Format: `find KEYWORDS`

### Viewing Help: `help`

Shows a message explaining how to use the app.
Format: `help`

