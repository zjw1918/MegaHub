package io.mega.megahub.pages.weather

import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.mega.megahub.R
import io.mega.megahub.bean.Weather
import java.text.SimpleDateFormat

class WeatherViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    private var mViewMap: SparseArray<View> = SparseArray()

    companion object {
        fun getImgUrl(abbr: String) = "https://www.metaweather.com/static/img/weather/png/$abbr.png"
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T: View> getView(viewId: Int): T {
        var view = mViewMap.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViewMap.put(viewId, view)
        }
        return view as T
    }

    fun bindView(w: Weather) {
        getView<TextView>(R.id.tv_item_weather_date).text = SimpleDateFormat("MM-dd E").format(w.applicable_date)
        val iv = getView<ImageView>(R.id.iv_item_weather_img)
        Glide.with(iv).load(getImgUrl(w.weather_state_abbr)).into(iv)
        getView<TextView>(R.id.tv_item_weather_temp).text =
            itemView.context.resources.getString(R.string.item_weather_temp_max_min,
                w.max_temp.toInt(), w.min_temp.toInt())
    }
}
