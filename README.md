# -CSE-464-2023---kdjarme-asu.edu
Course Project for CSE 464 - Fall 2023 - ASU<br>
This project implements a program that is able to parse a DOT file, make modifcations to parsed files, and export modified graphs into DOT files.<br><br>
The program is primarily a single class known as ImportedGraph that contains all methods for the first four features and a test class called TestImportedGraph that contains test cases for the ImportedGraph class.

## Table of Contents
  - [Maven Setup](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#maven-setup)
  - [API](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#api)
  - [Test Notes](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#test-notes)
  - [Project Discrepancies]()

## Project Setup with Maven 
This project is compiled using Java JDK Version 20.0.2 To make sure the program compiles properly, make sure you are using the correct SDK!<br><br>
This program uses Maven to handle its dependencies. <br><br>
To get started, access the folder named "CSE-464-CourseProject" in the program in your terminal and compile using the command.<br><br>
```mvn package```<br><br>
This will download all necessary dependencies for the code

## API
This program's development is broken up into multiple features that are developed over time. The features and their documentation are noted in this section.

### Table of Contents
Part 1 Features:
  - [Feature 1: Parsing a DOT File](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#feature-1---parsing-a-dot-file)
  - [Feature 2: Adding Vertices](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#feature-2---adding-vertices)
  - [Feature 3: Adding Edges](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#feature-3---adding-edges)
  - [Feature 4: Outputting Graphs](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu#feature-4---outputting-importedgraph-objects)

Part 2 Features:
  - [Feature 1: Removing Vertices and Edges]
  - [Feature 2: Searching for Nodes]

## Part 1
### Feature 1 - Parsing a DOT File
<b>Link:</b> [Link to Feature 1 commit](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/commit/0093cac456e4741939d79b4948c53eb850e1c94b)<br><br>
<b>Description:</b> Focuses on developments made tot he  Allows for user to import graph data stored in dot format into program. Also created some baseline test cases for the functions implemented for feature 1 
#### Added Methods and Test Outputs
<b>public boolean parseGraph(String filePath)</b><br>
  * **Description:**
     - Creates a graph in the ImportedGraph class instance formatted to match a given DOT file. This function only supports the creation of simple, directed graphs.
   
   * **Parameter(s):**
     - String filePath - contains the path to the dotfile that is to be parsed. Must include the name of the file and its extension as well

   * **Output:**
     - Returns a bool representing if the graph was successfully parsed. A value of true means that the graph was successfully parsed.
    
   * **Example use:**<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/60afd618-6675-4c42-b498-22ef18454653) <br><br>

<b>public String toString()</b><br>
  * **Description:**
     - Prints out a string representation of the graph

   * **Output:**
     - Returns a string that lists information in the order of:
       - Number of Vertices
       - List of all Vertices
       - Number Edges
       - List of all Edges 
    
   * **Example Output:**<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/9364b77f-1183-490a-9d19-00657c7a4b32)

<b>public boolean outputGraph(String filePath)</b><br>
  * **Description:**
     - Creates a text file containg a text representation of the graph. This output is not in DOT format.
   
   * **Parameter(s):**
     - String filePath - contains the path to where the output is desired. Must include the name of the new file and its extension as well.

   * **Output:**
     - Returns a bool representing if the graph was successfully parsed. A value of true means that the graph was successfully parsed.
    
   * **Example use:**
     - Where ig is an instance of ImportedGraph
     - The first image is a an example of the code and the second image is a screenshot of an example output
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/60afd618-6675-4c42-b498-22ef18454653) <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/4a93c20d-1971-4f67-9d8a-136f02c937cd) <br><br>

### Feature 2 - Adding Vertices
**Link:** [Link to Feature 2 commit](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/commit/eb865183d9d3dc9dc58931b036a82098cb08ff16)
**Description:** Allows for vertices to be added to the imported graphs. <br><br>
Also implemented preliminary test cases for related functions.

<b>public String addNode(String label)</b><br>
  * **Description:**
     - Attempts to add a new node specfied by the parameter to the ImportedGraph instance
   
   * **Parameter(s):**
     - String label - contains the name of the new vertex to be added

   * **Output:**
     - Returns a string representing if the node was successfully added.
    
   * **Example output:**
     - First image contains a sample output if adding the node was successful. The second image contains a sample output if adding the node was not successful<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/564a76da-d49d-4194-9ec0-c78d279ec48e)<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/bf283364-ec3d-49dd-80a6-0122adb7fd23)<br><br>

<b>public String addNodes(String[] label)</b><br>
  * **Description:**
     - Attempts to add multiple new nodes specfied by the parameter to the ImportedGraph instance
   
   * **Parameter(s):**
     - String[] label - Array list containing the name of the new vertices to be added

   * **Output:**
     - Returns a string representing if the node was successfully added for each node that attempts to be added
    
   * **Example output:** <br><br>
     - Containts example of some successful and some not successful attempts to add a vertex through use of the addNodes method <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/b9e26b66-0aaa-4c61-b894-75fcb6befaf5) <br><br>

### Feature 3 - Adding Edges
**Link:** [Link to Feature 3 commit](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/commit/29246f2ad33fc4f418d7cc72dbd74a3d7f16bdcd)
**Description:** Allows user to add new edges to an imported graph<br>
Also included baseline test suite for the related method

<b>public void addEdge(String srcLabel, String dstLabel)</b><br>
  * **Description:**
     - Attempts to add a new edge specfied by the parameters to the ImportedGraph instance. Starts by first trying to add the nodes to the ImportedGraph instance.
   
   * **Parameter(s):**
     - String srcLabel - contains the name of the vertex where the edge will originate from
     - String dstLabel - contains the name of the vertex where the edge will end at

   * **Output:**
     - No return value but successful attempt will result in the instance of ImportedGraph with a new edge and a failed attempt will result in reason being output to console
    
   * **Example output:**
     - First image contains a sample output if adding the edge was successful. The second image contains a sample output if adding the node was not successful<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/877a3567-6ac1-467d-a778-c6a04280f255) <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/77c0c434-704c-4419-9247-030d1e311165) <br><br>

### Feature 4 - Outputting ImportedGraph Objects
**Link:** [Link to Feature 4 commit](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/commit/c6a455438d9a6ffa0c286956d302071527534f98)
**Description:** Allows users to export their Imported Graph object to either a dot file or PNG file<br>
Also adds test cases for relevant functions

<b>public void outputDOTGraph(String filePath)</b><br>
  * **Description:**
     - Exports ImportedGraph object into a DOT format flie
   
   * **Parameter(s):**
     - String filePath - contains the name of the path where the DOT file will be created in. Must also contain the name of the file with the ".dot" extension

   * **Output:**
     - No return value but successful attempt will result in the creation of a DOT file in specified directory
    
   * **Example output:**
     - First image contains an example of use of the function and second image contains an example DOT file that has been ouputted using this method <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/1ad17faa-a2eb-49fb-a684-f122a0927447)<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/55c031c7-24a0-4a36-98fc-ee7a30171a19)<br><br>

<b>public void public boolean outputGraphics(String filePath, String format)</b><br>
  * **Description:**
     - Exports ImportedGraph object into a specified image format
   
   * **Parameter(s):**
     - String filePath - contains the name of the path where the image file will be created in. Must also contain the name of the file but not the extension type
     - String format - contains name of image format to be used.
       - Supported types include:
         - PNG

   * **Output:**
     - Returns true on successful attempt and will result in the creation of an image file in specified directory
    
   * **Example output:**
     - First image contains an example of use of the function and second image contains an example PNG file that has been ouputted using this method <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/30f74760-7537-4021-b794-8ece03039f24) <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/e75eedc3-a9c6-4ad9-a7df-200e69eb1ec9) <br><br>

## Part 2
### Feature 1 - Removing Vertices and Edges
**Link:** [Link to Feature 1 commit](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/commit/85f305ba43bb987c3454299c7be69b211f5234f7)
**Description:** Allows for vertices to be added to the imported graphs. <br><br>
Also implemented preliminary test cases for related functions.

<b>public String removeNode(String label)</b><br>
  * **Description:**
     - Attempts to remove a node specfied by the parameter to the ImportedGraph instance
   
   * **Parameter(s):**
     - String label - contains the name of the new vertex to be removed

   * **Output:**
     - Returns a string representing if the node was successfully removed.
    
   * **Example output:**
     - First image contains a sample output if removing the node was successful. The second image contains a sample output if removing the node was not successful<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/d45f0c26-0218-4e14-9def-032af1c7dc7d)<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/6e02618b-10c9-4feb-9aba-ff8c0ddb93e6)<br><br>     

<b>public String removeNodes(String[] label)</b><br>
  * **Description:**
     - Attempts to remove multiple nodes specfied by the parameter to the ImportedGraph instance
   
   * **Parameter(s):**
     - String[] label - Array list containing the name of the vertices to be removed

   * **Output:**
     - Returns a string representing if the node was successfully added for each node that attempts to be added
    
   * **Example output:** <br><br>
     - Containts example of some successful and some not successful attempts to remove a vertex through use of the removeNodes method <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/eaf0dbb9-b8eb-414f-9c32-5ec89e0a749f) <br><br>

<b>public void removeEdge(String srcLabel, String dstLabel)</b><br>
  * **Description:**
     - Attempts to remove an edge specfied by the parameters to the ImportedGraph instance. Starts by first trying to add the nodes to the ImportedGraph instance.
   
   * **Parameter(s):**
     - String srcLabel - contains the name of the vertex where the edge will originate from
     - String dstLabel - contains the name of the vertex where the edge will end at

   * **Output:**
     - No return value but successful attempt will result in the instance of ImportedGraph with a new edge and a failed attempt will result in reason being output to console
    
   * **Example output:**
     - First image contains a sample output if adding the edge was successful. The second image contains a sample output if remove the node was not successful<br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/9e1ddef1-bfe9-4016-9a8a-63d6918e13cf) <br><br>
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/446765ea-a910-4597-850e-b8c021e8a82b) <br><br>

### Feature 2 - Graph Searching using BFS and DFS
**Link:** [Link to Feature 2 commit](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/commit/6470e2af82dc75cb2c5080ccaf525a2e6595ac04)
**Description:** Allows for searching a specified vertice starting from another vertice using BFS or DFS algorithm. <br><br>
Also implemented preliminary test cases for related functions.

<b>public MyPath GraphSearch(String src, String dst, Algorithm Algo)</b><br>
  * **Description:**
     - Attempts to search for a label starting the search from another specified label
   
   * **Parameter(s):**
     - String srcLabel - contains the name of the vertex where the search will originate from
     - String dstLabel - contains the name of the vertex where the search will end at

   * **Output:**
     - MyPath object that contains a path starting from the srcLabel to dstLabel. If a path from scrLabel to dstLabel cannot be found, null value will be returned.
    
   * **Example output:**
     - Image contains a path representation of the MyPath object that gets returned. Output path is the same regardless of which algorithm is used.
     
     ![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/89edc56c-e5e1-4c88-bb2d-5043b34f3017)
<br><br>

## Project Discrepancies
  - Part 2 requires the creation of a Path class. For my project, I use a library that already contains the definition for Path object. As a result, I opted to name the Path class that I needed to create MyPath.

## Test Notes
Some tests compare the output of two files for equality. Sometimes, a file that is being compared may have different line separators than the other file. If tests are failing due to files not being the same make sure to make the line separators the same.<br><br>
To do this in IntelliJ: First, start by opening the file and selecting the file you want to check the line separators on and go to the bottom of the IDE and select the second option among the tabs.<br><br>
![image](https://github.com/Kukochako/-CSE-464-2023---kdjarme-asu.edu/assets/44959291/013788c1-ca54-49ef-a72e-ffeb0638ec66) <br><br>
You can change the file endings to either LF, CR, or CRLF. You just have to make sure that they all share the same ending, but I recommend changing them to LF as that is what they were tested with during development.
