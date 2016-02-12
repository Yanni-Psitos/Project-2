#Manual Test Cases

<b>Step 1</b> - (In MainActivity) Click on search icon in top right corner. <b>Expected</b> - Text box appears with search hints.
<b>Step 2</b> - (In MainActivity, searchView) Type in a parameter abiding by the hints 'Hero, Name, or Power' and click enter. <b>Expected</b> - All data containing the input will be shown in the list.
<b>Step 3</b> - (In MainActivity, listView) Click on a list item. <b>Expected</b> - Takes User to the Detail screen, showing the item, its properties, picture, and its 'add'/'remove' buttons.
<b>Step 4</b> - (In DetailActivity) Click on the button with the '+ person' icon. <b>Expected</b> - Item gets added to favorites list if not already in the favorites list, and verifies with a Toast.
<b>Step 5</b> - (In DetailActivity) Click on the button with the 'X' icon. <b>Expected</b> - Item gets removed from the User's favorite list, if the item was already in there, and verifies with a Toast.
<b>Step 6</b> - (In MainActivity) Click on the button with the 'two person' icon. <b>Expected</b> - Takes User to the Team screen, displaying their favorites list by image and name.
<b>Step 7</b> - (In TeamActivity) Click on the button labeled 'Back'. <b>Expected</b> - Takes User to the Main screen.
<b>Step 8</b> - (In DetailActivity) Click on the 'two person' button, or the 'X' button without appropriate data saved. <b>Expected</b> - No data is changed and a Toast 'Error' message appears to the User.

###Screenshots
[MainActivity Screenshot](http://i.imgur.com/0IkbEJY.png)
[DetailActivity Screenshot](http://i.imgur.com/LMQ4GJu.png)
[TeamActivity Screenshot](http://i.imgur.com/CwSgdwr.png)