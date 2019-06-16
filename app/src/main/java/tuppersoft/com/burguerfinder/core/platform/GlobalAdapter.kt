package tuppersoft.com.burguerfinder.core.platform


import androidx.recyclerview.widget.RecyclerView

abstract class GlobalAdapter<T>(private var items: MutableList<T>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = items?.size ?: 0

    fun getItem(position: Int): T {
        if (items?.get(position) != null)
            return items?.get(position) as T
        else
            throw NullPointerException() as Throwable
    }

    fun addItems(newItems: MutableList<T>, position: Int = items?.lastIndex ?: 0) {
        if (items == null) {
            items = mutableListOf()
        }
        items!!.addAll(position, newItems)
        notifyDataSetChanged()
    }

    fun resetItems(newItems: MutableList<T>) {
        items = newItems
        notifyDataSetChanged()
    }
}