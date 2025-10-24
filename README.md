# Modular Media Streaming Suite (Java)  

This is a **Java reference implementation** of a modular media streaming suite demonstrating key **structural design patterns**. It simulates playback via console output and showcases how these patterns can be applied to build a flexible, extensible media player.  

The project implements the following structural patterns:  

- **Bridge** – Separates media player abstraction from rendering implementations (hardware vs software).  
- **Adapter** – Wraps legacy media sources so they can be used with the new player seamlessly.  
- **Decorator** – Adds features like **watermarking** and **equalizer presets** at runtime without modifying the core player.  
- **Composite** – Supports **playlists and nested playlists**, allowing complex hierarchies of media files to be played uniformly.  
- **Proxy** – Caches remote media streams to optimize repeated playback.  

---

## Features  

- Play media from **local files**, **HLS streams**, and **remote APIs**.  
- Support for **composite playlists** (nested playlists).  
- **Dynamic renderer switching** (hardware GPU vs software CPU) at runtime.  
- Apply **decorators** for audio and visual enhancements.  
- **Caching** of remote media for faster repeated access.  
- Seamless **legacy media integration** using adapters.  

---

## Getting Started  

### Prerequisites  

- Java 17+ (tested on JDK 25)  
- Visual Studio Code with **Java Extension Pack** or any Java IDE  

---

### Run in VS Code  

1. Open this folder in Visual Studio Code.  
2. Install the **Java Extension Pack** if prompted.  
3. Open `Demo.java` and run the configuration **"Run Modular Media Suite"**, or press **Ctrl+F5**.  

---

### Run from the Command Line  

From the repository root folder:  

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out com.example.media.Demo
