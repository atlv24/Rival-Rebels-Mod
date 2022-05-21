# Rival Rebels Mod
This is the official source code of the classic Rival Rebels mod. The mod adds heavy weapons, nukes, machines, and a multiplayer combat system with rounds to Minecraft.
 
# Supported Versions
```1.7.10```, 
```1.6.4```,
```1.6.2```,
```1.5.2```, 
```1.5.1```,
```1.4.7```

Please note that official mod development has ceased operations and will **not continue**.
However, if you want to bring Rival Rebels to newer minecraft versions, feel free to clone or fork this repo so that you can update it. (Please don't send a pull request to the official Rival Rebels repository as mod development has stopped, and the developer is busy doing other things).

# Build Instructions

If you have decided to update the mod to a newer version, you will need to build the development workspace first in order to be able to run the client with the mod. 

1. First, you must clone the repo by executing: ```git clone https://github.com/rodolphito/Rival-Rebels-Mod.git``` in the terminal.
2. Change your terminal dir to the source code's root dir.
3. execute ```gradlew setupDecompWorkspace``` if you're on a windows command prompt. For linux or people using the bash terminal shell, execute ```./gradlew setupDecompWorkspace```.
4. If you wish to code the mod in the Eclipse IDE, execute ```gradlew eclipse```, then open eclipse, then select "<root project dir>/eclipse" as the workspace. If you wish to code the mod with Intellij IDEA, don't execute anything in the command prompt, just open Intellij, select "File > Import Project", then select the build.gradle file in the file navigation window.
