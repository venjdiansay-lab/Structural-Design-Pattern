package com.example.media;

import com.example.media.player.*;
import com.example.media.plugin.*;
import com.example.media.playlist.*;
import com.example.media.proxy.RemoteProxySource;
import com.example.media.source.*;

public class Demo {
    public static void main(String[] args) {
        // Build renderers (implementors for Bridge)
        Renderer hw = new HardwareRenderer();
        Renderer sw = new SoftwareRenderer();

        // Build a concrete MediaPlayer (abstraction in Bridge)
        MediaPlayer player = new ConcreteMediaPlayer(hw);

        // Build sources
        MediaSource local = new LocalFileSource("movie.mp4");
        MediaSource hls = new HlsSource("https://example.com/stream.m3u8");
        MediaSource remote = new RemoteApiSource("https://api.example.com/media/123");
        MediaSource cachedRemote = new RemoteProxySource(remote);

        // Legacy adapted source
        LegacyMonolithicSource legacy = new LegacyMonolithicSource("old-disk:track-7");
        MediaSource adapted = new LegacySourceAdapter(legacy, "track-7");

        // Build a composite playlist
        CompositePlaylist root = new CompositePlaylist("Root Playlist");
        root.add(new MediaFile("Local Movie", local));

        CompositePlaylist sub = new CompositePlaylist("Stream Playlist");
        sub.add(new MediaFile("HLS Live", hls));
        sub.add(new MediaFile("Cached Remote", cachedRemote));
        root.add(sub);

        root.add(new MediaFile("Legacy Track (adapted)", adapted));

        // 1) Play playlist using current renderer (Hardware)
        System.out.println("\\n=== 1. Play playlist with HardwareRenderer (Bridge) ===");
        root.play(player);

        // 2) Apply decorators (watermark + equalizer) by wrapping the current renderer
        System.out.println("\\n=== 2. Apply decorators: Watermark + Equalizer (Decorator) ===");
        Renderer withWatermark = new WatermarkDecorator(hw, "SAMPLE-WM");
        Renderer withEq = new EqualizerDecorator(withWatermark, "BassBoost");
        player.setRenderer(withEq);
        player.playSource(cachedRemote); // triggers proxy caching

        // Play again to show cache hit
        System.out.println("\\n=== 3. Play cached remote stream again (Proxy cache hit) ===");
        player.playSource(cachedRemote);

        // 4) Switch renderer implementation at runtime (Bridge)
        System.out.println("\\n=== 4. Switch to SoftwareRenderer (Bridge) ===");
        player.setRenderer(sw);
        player.playSource(local);

        System.out.println("\\n=== Demo finished ===");
    }
}
