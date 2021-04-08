This Java project is designed to count words and various statistics associated with that within a String. This uses Java 15 and potentially mvn. 

Currently the definition I am trying to use for a word within this Project is:
- Digits, possibly followed by . or , and then more digits, and also possibly followed by a generic "unit" string of characters
  - A unit in this case is a small (<5) String of chars possibly followed by p or / followed further by a small (<5) string of chars
- A string of alphabetic characters without any Diacritics, possibly followed by a hyphen and another string of characters, with the hyphen-alphabetic characters allowed ad infinitum
- 2 Digits followed by / followed by 2 Digits followed by / followed by 2 or 4 digits
 
In order to run this project either:
- Import it into Intellij, change the filePath variable in App to the absolute path of the file to read
- Import it into Intellij, change the Run Configuration to pass in "--file" arguements followed by an absolute path
- run mvn package, followed by calling either the skinny or fat jar (fat jar named "-jar-with-dependencies") with the same --file arguments followed by absolute paths



