# klin
The tiny tool to easily open a JIRA issue


<img width="240" alt="klin example" src="https://user-images.githubusercontent.com/17144826/183117139-46846061-ab05-49f8-ab1c-e37e4d18cb86.png">


## Project status 
This is a personal R&D project and it is still in developing stage.

Now it was testing only with Google Chrome.

## Build

1. Assemble
    ```
    ./gradelw clean klin
    ```
1. Go to `./klin` and add settings to `settings.json` file. 
   ```
   {
     "projects": [
       {
         "name":"string", //Jira key (required). Example: WEB
         "baseUrl":"string", //Base URL to your Jira instanse (required). Example: https://you.jira.instance
         "logoPath":"string", //Path to the project logo (optional). Example: ./web.png
         "maxTaskNumberLength":number, //The maximum number of characters (digits) in the task number (optional). Example: 4. Default: 5
         "minTaskNumberLength":number  //The minimin number of characters (digits) in the task number (optional). Example: 2. Default: 1
       }
     ]
   }
   ```
   With these settings, klin will open pages with links like `https://you.jira.instanse/WEB-1`
1. Open your Chrome and add new extension
    - Menu -> More Tools -> Extensions
    - Enable "Developer mode"
    - Press "Load unpacked"
    - Select the `./klin` folder

## Usage

You can call extension by hotkeys when Chrome window is active:
- `Ctrl+Shift+Y` on Windows/Linux
- `Command+Shift+Y` on MacOS

Or you can open `klin` from extensions toolbar.

If multiple projects have been added to `settings.json` you can use Tab to change active project.

Print task number (or paste it from the clipboard) and then press `Enter`. Jira task will be opened in new window.
