# <img width="24" heigh="24" alt="logo" src="https://user-images.githubusercontent.com/17144826/216780487-d0138ed4-474f-451b-90eb-75d087977073.png"> klin
The tiny tool to easily open a JIRA issue

<img width="214" alt="image" src="https://user-images.githubusercontent.com/17144826/186452528-5c0d650c-7325-4050-bf60-03399ca4db77.png">


## Project status 
This is a personal R&D project, and it is still in the development stage. 
Currently, it has only been tested with Google Chrome.

## Build

1. Assemble
    ```
    ./gradlew clean klin
    ```
2. Navigate to `./klin` and add the settings to the `settings.json` file.

   Fill in these settings for each JIRA project:
 
   | Name                | Type   | Required | Default value | Description                                                                                                                                                                                                                                                      | Validation                                                |
   |---------------------|--------|----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
   | name                | String | true     | -             | The name of the project. Value must be equal to JIRA project key                                                                                                                                                                                                 | 1. Not blank string<br/>2. Length less then 10 characters |
   | baseUrl             | String | true     | -             | URL to your JIRA instance                                                                                                                                                                                                                                        | Correct URL                                               |
   | logoPath            | String | false    | -             | Path to the JIRA project logo. <br/>1. Relative path to the image on local machine start from `klin` dir<br/>2. URL to the internet resource<br/><br/>There will be no logo on `klin` main page for current project if this parameter is not present in settings |                                                           |
   | tooltipText         | String | false    | -             | The tooltip text that will be displayed when hovering over the project name on the main page of the extension.<br/><br/>There will be no tooltip on `klin` main page for current project if this parameter is not present in settings                            | Length less then 20 characters                            |
   | maxTaskNumberLength | Number | false    | 5             | The maximum number of digits for the valid JIRA task number                                                                                                                                                                                                      | Positive number                                           |

   Example 
   ```
   {
     "projects": [
       {
         "name":"JDK",
         "baseUrl":"https://bugs.openjdk.org",
         "logoPath":"https://bugs.openjdk.org/secure/projectavatar?pid=10100&avatarId=10301",
         "tooltipText":"OpenJDK issues",
         "maxTaskNumberLength":7
       }
     ]
   }
   ```
   With these settings, `klin` will open pages with links such as `https://bugs.openjdk.org/browse/JDK-8292697`
3. Open Chrome and add the new extension:
    - Menu -> More Tools -> Extensions
    - Enable "Developer mode"
    - Press "Load unpacked"
    - Select the `./klin` folder

## Usage

You can call the extension using hotkeys when the Chrome window is active:
   - `Ctrl+Shift+O` on Windows/Linux
   - `Command+Shift+O` on MacOS.

Alternatively, you can open `klin` from the extensions toolbar.

If multiple projects have been added to `settings.json`, you can use the Tab key to change the active project.

Type the task number (or paste it from the clipboard), and then press Enter. The Jira task will open in a new tab.
If `Enter` was pressed with `Shift`, then the Jira issue will open in the background (without focus).
