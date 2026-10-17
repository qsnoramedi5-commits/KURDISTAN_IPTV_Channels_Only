package com.kurdistan.iptv

data class Channel(val name: String, val url: String, val category: String = "کوردی")

object Channels {
    // Add the rest of the user's channel list here using the same Channel(name, url, category) format.
    val all = listOf(
        Channel("RUDAW TV", "https://live.rudaw.net/hls/rudaw-tv/master.m3u8"),
        Channel("AVA MEDIA TV", "https://ava002.xyz/upload/images/1080.m3u8"),
        Channel("KURDISTAN 24 TV", "https://hlspackager.akamaized.net/live/DB/K24/HLS/K24.m3u8"),
        Channel("SPEDA TV HD", "https://www.speda2.site/upload/images/spedahd.m3u8"),
        Channel("GALI KURDISTAN TV", "https://live.host247.net/gk/gksat/chunklist_w1879482920.m3u8"),
        Channel("KURDSAT NEWS TV", "https://hlspackager.akamaized.net/live/DB/KURDSAT_NEWS/HLS/KURDSAT_NEWS.m3u8"),
        Channel("NUÇÊ TV", "https://hlspackager.akamaized.net/live/DB/NU_TV/HLS/NU_TV.m3u8"),
        Channel("NRT TV HD", "https://hlspackager.akamaized.net/live/DB/NRT_HD/HLS/NRT_HD-avc1_2500000=10002,mp4a_128000=20000.m3u8"),
        Channel("CHANNEL 8", "https://live.channel8.com/Channel8-Kurdish/index.m3u8"),
        Channel("MMN NEWS TV", "http://mmn.mypsx.net:1935/live/mmnhdnews/playlist.m3u8"),
        Channel("AFARIN TV HD", "https://65f16f0fdfc51.streamlock.net/afarinTV/livestream/chunklist_w187911945.m3u8"),
        Channel("KURDISTAN TV HD", "https://5a3ed7a72ed4b.streamlock.net/live/SMIL:myStream.smil/playlist.m3u8"),
        Channel("SHAR TV HD", "http://avrstream.com:1935/live/SHARTV/playlist.m3u8"),
        Channel("AVAR TV HD", "https://avr.host247.net/live/AvarTv/playlist.m3u8"),
        Channel("KURDSAT TV HD", "https://hlspackager.akamaized.net/live/DB/KURDSAT_HD/HLS/KURDSAT_HD.m3u8"),
        Channel("WELAT TV HD", "https://stream.badinan.xyz/welat/tv/playlist.m3u8"),
        Channel("GK+ TV HD", "https://live.host247.net/gk/gkplus/playlist.m3u8"),
        Channel("PESHANG TV HD", "https://stream.forcaroo.com/stream/hls/desktop.m3u8"),
        Channel("GEWHER TV HD", "https://cdn.karwan.tv/gewher-tv/index.m3u8"),
        Channel("AVA FAMILY TV", "https://familyhls.avatv.live/hls/stream.m3u8"),
        Channel("ZAROK TV HD", "https://zindisorani.zaroktv.com.tr/hls/0/stream.m3u8"),
        Channel("GEWHER KIDS TV", "https://cdn.karwan.tv/gewher-kids/index.m3u8", "منداڵان"),
        Channel("WALLA QURAN TV", "http://cdn.karwan.tv/WALAQURAN/index.m3u8", "ئایینی"),
        Channel("GEWHER SPORT TV", "https://cdn.karwan.tv/gewher-sport/index.m3u8", "وەرزشی"),
        Channel("QATAR TV HD", "https://qatartv.akamaized.net/hls/live/20000609/qtv1/master.m3u8", "عەرەبی"),
        Channel("TRT KURDI TV", "https://tv-trtkurdi.medya.trt.com.tr/master.m3u8"),
        Channel("BBC NEWS TV", "https://vs-hls-push-ww-live.akamaized.net/x=4/i=urn:bbc:pips:service:bbc_news_channel_hd/t=3840/v=pv14/b=5070016/main.m3u8", "هەواڵ"),
        Channel("SPACETOON TV HD", "https://live-uae-next.spacetoongo.com/ST_MENA_NEXT/hls/r9p2hjipmw2kl.m3u8", "منداڵان")
    )
}
