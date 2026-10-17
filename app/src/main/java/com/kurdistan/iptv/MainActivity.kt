package com.kurdistan.iptv

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.text.Editable
import android.text.TextWatcher

private const val BG = 0xFF07111F.toInt()
private const val PANEL = 0xFF101E30.toInt()
private const val GOLD = 0xFFFFD21F.toInt()
private const val GREEN = 0xFF00C878.toInt()
private const val MUTED = 0xFF9FB0C5.toInt()

class MainActivity : Activity() {
    private lateinit var list: LinearLayout
    private lateinit var search: EditText
    private var category = "هەموو"
    private var query = ""
    private val categories = listOf("هەموو", "کوردی", "هەواڵ", "وەرزشی", "منداڵان", "ئایینی", "عەرەبی")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = BG; window.navigationBarColor = BG
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(BG); setPadding(dp(16), dp(10), dp(16), 0) }
        val header = LinearLayout(this).apply { gravity = Gravity.CENTER_VERTICAL; orientation = LinearLayout.HORIZONTAL }
        val brand = TextView(this).apply { text = "KURDISTAN "; setTextColor(Color.WHITE); textSize = 22f; setTypeface(null, Typeface.BOLD) }
        val iptv = TextView(this).apply { text = "IPTV"; setTextColor(GOLD); textSize = 22f; setTypeface(null, Typeface.BOLD) }
        header.addView(brand); header.addView(iptv); root.addView(header)
        root.addView(TextView(this).apply { text = "پەخشی ڕاستەوخۆی کەناڵەکان  •  LIVE TV"; setTextColor(MUTED); textSize = 13f; setPadding(0, dp(5), 0, dp(14)) })
        search = EditText(this).apply {
            hint = "گەڕان بە ناوی کەناڵ..."; setHintTextColor(MUTED); setTextColor(Color.WHITE); textSize = 15f
            setSingleLine(true); setPadding(dp(14), 0, dp(14), 0); background = rounded(PANEL, 14)
        }
        root.addView(search, LinearLayout.LayoutParams(-1, dp(48)))
        val scrollCats = HorizontalScrollView(this).apply { isHorizontalScrollBarEnabled = false; setPadding(0, dp(12), 0, dp(8)) }
        val catRow = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        categories.forEach { c ->
            val b = TextView(this).apply { text = c; textSize = 13f; setTextColor(if (c == category) BG else Color.WHITE); gravity = Gravity.CENTER; setPadding(dp(15), dp(9), dp(15), dp(9)); background = rounded(if (c == category) GOLD else PANEL, 22); setOnClickListener { category = c; render() } }
            val lp = LinearLayout.LayoutParams(-2, -2); lp.marginEnd = dp(7); catRow.addView(b, lp)
        }
        scrollCats.addView(catRow); root.addView(scrollCats)
        val section = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL; setPadding(0, dp(5), 0, dp(8)) }
        section.addView(TextView(this).apply { text = "کەناڵەکان"; setTextColor(Color.WHITE); textSize = 19f; setTypeface(null, Typeface.BOLD) }, LinearLayout.LayoutParams(0, -2, 1f))
        section.addView(TextView(this).apply { text = "${Channels.all.size} CHANNELS"; setTextColor(GREEN); textSize = 11f; setTypeface(null, Typeface.BOLD) })
        root.addView(section)
        val scroller = ScrollView(this).apply { isFillViewport = true }
        list = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(0, 0, 0, dp(20)) }
        scroller.addView(list); root.addView(scroller, LinearLayout.LayoutParams(-1, 0, 1f))
        val nav = TextView(this).apply { text = "⌂  سەرەکی       ▣  کەناڵەکان       ♥  دڵخوازەکان       ⚙  ڕێکخستن"; setTextColor(GOLD); textSize = 12f; gravity = Gravity.CENTER; setPadding(0, dp(15), 0, dp(18)); background = rounded(PANEL, 18) }
        root.addView(nav, LinearLayout.LayoutParams(-1, -2))
        setContentView(root)
        search.addTextChangedListener(object : TextWatcher { override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} ; override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { query = s?.toString().orEmpty(); render() }; override fun afterTextChanged(s: Editable?) {} })
        render()
    }

    private fun render() {
        if (!::list.isInitialized) return
        list.removeAllViews()
        val filtered = Channels.all.filter { (category == "هەموو" || it.category == category) && it.name.contains(query, true) }
        filtered.forEachIndexed { index, channel ->
            val row = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL; setPadding(dp(12), dp(11), dp(12), dp(11)); background = rounded(PANEL, 16) }
            val logo = TextView(this).apply { text = channel.name.filter { it.isLetterOrDigit() }.take(2).uppercase(); textSize = 15f; setTypeface(null, Typeface.BOLD); setTextColor(GOLD); gravity = Gravity.CENTER; background = rounded(0xFF1B3049.toInt(), 12) }
            row.addView(logo, LinearLayout.LayoutParams(dp(48), dp(48)))
            val details = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(12), 0, dp(5), 0) }
            details.addView(TextView(this).apply { text = channel.name; setTextColor(Color.WHITE); textSize = 14f; setTypeface(null, Typeface.BOLD) })
            details.addView(TextView(this).apply { text = "● LIVE  •  ${channel.category}"; setTextColor(GREEN); textSize = 11f; setPadding(0, dp(4), 0, 0) })
            row.addView(details, LinearLayout.LayoutParams(0, -2, 1f))
            row.addView(TextView(this).apply { text = "▶"; textSize = 18f; setTextColor(GOLD); gravity = Gravity.CENTER })
            row.setOnClickListener { startActivity(Intent(this, PlayerActivity::class.java).putExtra("name", channel.name).putExtra("url", channel.url)) }
            val lp = LinearLayout.LayoutParams(-1, -2); lp.bottomMargin = dp(9); list.addView(row, lp)
        }
        if (filtered.isEmpty()) list.addView(TextView(this).apply { text = "هیچ کەناڵێک نەدۆزرایەوە"; setTextColor(MUTED); gravity = Gravity.CENTER; setPadding(0, dp(32), 0, dp(32)) })
    }

    private fun rounded(color: Int, radius: Int) = GradientDrawable().apply { setColor(color); cornerRadius = dp(radius).toFloat() }
    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()
}
