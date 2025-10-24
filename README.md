A Structural Design Pattern Demonstration

Overview

The Modular Media Streaming Suite is a Java-based reference implementation that demonstrates key structural design patterns through a simulated media streaming system. Instead of actual playback, the system outputs messages to the console to illustrate how these design patterns enable flexibility, scalability, and modularity in software architecture.

Implemented Structural Design Patterns

Bridge – Separates the media player abstraction from rendering implementations (e.g., hardware vs. software rendering).

Adapter – Allows integration of legacy media sources with the new player through interface wrapping.

Decorator – Dynamically adds features such as watermarking and equalizer presets without altering the core player logic.

Composite – Enables hierarchical playlist structures (playlists within playlists) that can be managed uniformly.

Proxy – Implements caching for remote media streams to improve performance during repeated playback.

Key Features

Playback simulation for local files, HLS streams, and remote APIs.

Composite playlist support, allowing nested playlist structures.

Dynamic renderer switching (hardware GPU ↔ software CPU) during runtime.

Runtime decorators for audio and visual enhancements.

Caching proxy mechanism for faster access to remote media.

Adapter integration for seamless use of legacy media sources.

Getting Started
Prerequisites

Java 17+ (tested on JDK 25)

Visual Studio Code with the Java Extension Pack (or any compatible Java IDE)

Run in Visual Studio Code

Open the project folder in VS Code.

Install the Java Extension Pack if prompted.

Open Demo.java.

Run the configuration “Run Modular Media Suite” or press Ctrl + F5.

Run from Command Line

From the project’s root directory:

mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out com.example.media.Demo