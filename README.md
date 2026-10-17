# KURDISTAN IPTV — Android Starter (Channels Only)

A dark, Kurdish-inspired Android IPTV app starter using Kotlin and AndroidX Media3 ExoPlayer.

## Features
- Channels-only interface: no movies, series, or film categories.
- Portrait channel list; player supports device rotation / landscape fullscreen.
- Search channels by name and filter by channel category.
- HLS/M3U8 playback through Media3 ExoPlayer.
- Free access in the app; no login or subscription screen.
- Kurdish dark theme with green/yellow accents.

## Build an APK
1. Install Android Studio on a computer.
2. Extract this ZIP and open the `KurdistanIPTV` folder in Android Studio.
3. Allow Gradle Sync to finish and connect an Android phone or use an emulator.
4. Choose **Build → Build APK(s)**.

## Channel list
Edit `app/src/main/java/com/kurdistan/iptv/Channel.kt`. Each channel uses:
`Channel("CHANNEL NAME", "https://provider.example/live.m3u8", "category")`

The included list is a starter subset, not every channel from the original message. Add the remaining channel URLs there as needed.

## Reliability notes
- Streams may be unavailable, geoblocked, changed, or require provider authorization. The app cannot guarantee every supplied URL will always work.
- A stable connection is important. ExoPlayer handles HLS playback, but stream outages are controlled by the provider.
- The manifest allows cleartext HTTP because some supplied links use HTTP. Prefer HTTPS sources where possible; only use streams you are authorized to access.
- This ZIP contains Android Studio source code, not a prebuilt APK. Android Studio is required to build the installable APK.
