# Java Multi-Client Chat Server

A simple multi-client chat application built in Java using sockets, threads, and a GUI client.
This project demonstrates basic networking, concurrency, and Swing-based interface design.

## Features

* Multi-client support using threads
* Server that handles multiple users simultaneously
* Console-based client
* GUI-based client using Java Swing
* Real-time message broadcasting
* Automatic scroll in chat window

## Tech Stack

* Java
* Java Sockets (ServerSocket, Socket)
* Multithreading
* Java Swing (GUI)

## Project Structure

```
client-console/
    Client.java

client-gui/
    ChatClientGUI.java

server/
    ClientHandler.java
    Server.java
```

## How to Run

### Step 1: Compile server

Open terminal inside the `server` folder:

```
javac Server.java ClientHandler.java
```

Run the server:

```
java Server
```

---

### Step 2: Run GUI client

Open a new terminal inside `client-gui`:

```
javac ChatClientGUI.java
java ChatClientGUI
```

You can run multiple clients to simulate a chat room.

---

### Step 3: Run console client (optional)

Inside `client-console`:

```
javac Client.java
java Client
```

---

## What I Learned

* Socket programming in Java
* Handling multiple clients using threads
* Input/output streams
* Building a basic GUI using Swing
* Event handling in Java

## Future Improvements

* Message timestamps
* User join/leave notifications
* Private messaging
* Chat history
* Better UI design

