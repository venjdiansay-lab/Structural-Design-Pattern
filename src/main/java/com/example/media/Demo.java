package com.example.media;

import com.example.media.player.*;
import com.example.media.plugin.*;
import com.example.media.playlist.*;
import com.example.media.proxy.RemoteProxySource;
import com.example.media.source.*;

public class Demo {

    public static void main(String[] args) {

        // -----------------------------------------------------
        // 1. Initialize Renderers (Bridge Implementors)
        // -----------------------------------------------------
        Renderer hw = new HardwareRenderer();
        Renderer sw = new SoftwareRenderer();

        // Create a MediaPlayer abstraction
        MediaPlayer player = new ConcreteMediaPlayer(hw);

        // -----------------------------------------------------
        // 2. Prepare Media Sources
        // -----------------------------------------------------
        MediaSource local = new LocalFileSource("movie.mp4");
        MediaSource hls = new HlsSource("https://example.com/stream.m3u8");
        MediaSource remote = new RemoteApiSource("https://api.example.com/media/123");
        MediaSource cachedRemote = new RemoteProxySource(remote);  // Proxy

        // Legacy source adapted to the new interface (Adapter)
        LegacyMonolithicSource legacy = new LegacyMonolithicSource("old-disk:track-7");
        MediaSource adapted = new LegacySourceAdapter(legacy, "track-7");

        // -----------------------------------------------------
        // 3. Build Composite Playlist (Composite Pattern)
        // -----------------------------------------------------
        CompositePlaylist root = new CompositePlaylist("Root Playlist");
        root.add(new MediaFile("Local Movie", local));

        CompositePlaylist sub = new CompositePlaylist("Stream Playlist");
        sub.add(new MediaFile("HLS Live", hls));
        sub.add(new MediaFile("Cached Remote", cachedRemote));
        root.add(sub);

        root.add(new MediaFile("Legacy Track (adapted)", adapted));

        // -----------------------------------------------------
        // 4. Demonstration
        // -----------------------------------------------------

        // Step 1: Play playlist with Hardware Renderer (Bridge)
        System.out.println("\n[1] Playing playlist using HardwareRenderer (Bridge)");
        root.play(player);

        // Step 2: Apply Decorators (Watermark + Equalizer)
        System.out.println("\n[2] Applying decorators: Watermark + Equalizer (Decorator)");
        Renderer withWatermark = new WatermarkDecorator(hw, "SAMPLE-WM");
        Renderer withEq = new EqualizerDecorator(withWatermark, "BassBoost");
        player.setRenderer(withEq);
        player.playSource(cachedRemote); // triggers proxy caching

        // Step 3: Replay cached media (Proxy Pattern)
        System.out.println("\n[3] Replaying cached remote stream (Proxy cache hit)");
        player.playSource(cachedRemote);

        // Step 4: Switch rendering implementation dynamically (Bridge)
        System.out.println("\n[4] Switching to SoftwareRenderer (Bridge)");
        player.setRenderer(sw);
        player.playSource(local);

        // Demo complete
        System.out.println("\nDemo finished.");
    }
}
