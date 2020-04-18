# Top Streaming Artists

A record label executive received text files that contain the top streamed music artists during certain weeks. 
Each file represents one track by an artist. One track means one song. An artist's name might appear multiple times. 
The data comes from [Spotify Charts](https://spotifycharts.com/regional).
The exec wants to know which artists appear on the list and how many times they appear and alphabetize the list.

# Dependencies

* [Java 8](https://docs.oracle.com/javase/8/docs/api/index.html)


# Setup

These are the steps to compile and run `streamingArtists.java`. 
The Source code is available in the `src` folder.
These steps are for use with command line workflow such as with a terminal.

1. Clone this repository `git clone https://github.com/XiaoNai43/CISC3130.git` to your machine to get a copy.
2. Move into the project's root directory with `cd AssignmentOne/src`
3. Compile the java file using the java compiler command `javac streamingArtists.java`
4. Run the compiled java program with the java command `java streamingArtists`

This will give output of top global artists that appeared in the list, alphabetized with a number representing how many times they appeared.
The results are printed into the `topGlobal` text file located in the `src` folder.
If you want to view the regional list change line number 14 to `Path input = Paths.get("../data/regional_us.csv");` with line number 52 to
`Path path = Paths.get("topRegional.txt");`
This will print the results into the `topRegional` text file located in the `src` folder.


# Folder Structure

* Code is saved into the `src` folder.
* Data is saved into the `data` folder.


# License

This project is licensed under the [MIT](https://choosealicense.com/licenses/mit/) License.
