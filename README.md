# Indexing text files

This simple app aims to index text files and directories, allowing user to later query files with a given word.
## Table of Contents

1. [Installation](#installation)
2. [Usage](#usage)
3. [Features](#features)

## Installation

1. Ensure you have Java installed.
2. Clone the repository:
   ```bash
   git clone https://github.com/jackobpy/indexingTextFiles.git
   ```
3. Compile the project:
    ```bash
    javac Main.java
   ```
## Usage

Run the application
1. The app welcomes you with a menu
``` bash
Choose the option
1. Index a file
2. Index a directory
3. Query files with a given word
4. Close the application
```
2. Input the number of the option that you want to choose:
   1. to index a file you have to specify the path to the file
   2. to index a directory you have to specify the path to the directory
   3. to query files containing a word you have to specify the word to look for
## Features

- indexing a text file or a whole directory (with a specified absolute path)
- querying files containing a given word
- consecutive requests supported
- the app uses Tokenizer interface, which means that future developers can create their own implementations of the tokenization algorithm to be used

