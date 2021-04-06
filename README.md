This Java project is designed to count words and various statistics associated with that within a String. This uses Java 15 and potentially mvn. 

Currently the definition I am trying to use for a word within this Project is:
    - Contains characters, digits, potentially a dash (-) or . within the middle of the word (admittedly a pretty loose definition)
 
In order to run this project either:
    - Import it into Intellij, change the filePath variable in App to the absolute path of the file to read
    - Import it into Intellij, change the Run Configuration to pass in "--file" arguements followed by an absolute path
    - run mvn package, followed by calling either the skinny or fat jar (fat jar named "-jar-with-dependencies") with the same --file arguments followed by absolute paths



