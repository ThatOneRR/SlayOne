# SlayOne

This is an attempt to recreate Slay.One in a desktop application, not so much as to finish the game but a milestone for me in my coding endeavours. Although my intended goal was to accomplish more than what this is now, it is still an impressive feat (for me, albeit it probably being a smol feat for you, a coding god). I'm pretty happy with the results nevertheless.

Will I be working on it again? Maybe, maybe not, but for now it's probably a no-go because I want to focus on my studies. 

First time doing github stuff so please go easy ;w;

### Uh, cool.... how'd I run it doe?

That is a *great* question. Java's mildly annoying point is that everything compiles as a runnable .jar file. There's a quick and dirty way to compile the jar file though:
1. Zip up the following contents:
  - 'me' folder (direct child of src)
  - All the contents of the 'resources' folder (namely font, levels and sprites fiolders)
  - 'META-INF' folder (direct child of src)
2. Rename the zip file to SlayOne.jar

From here you just need to run the jar file, either through the command prompt:
1. Open up a command prompt and navigate to the directory containing the jar
2. Execute `java -jar SlayOne.jar` (you can add jvm arguments if you want!)
or through a .bat file
1. Create a text document and write `java -jar SlayOne.jar` inside
2. Save the file as a .bat file and run it. (Make sure the .bat file is in the same folder as the jar!)
