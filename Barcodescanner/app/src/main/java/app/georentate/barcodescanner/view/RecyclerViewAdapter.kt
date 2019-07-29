package app.georentate.barcodescanner.view

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import app.georentate.barcodescanner.R
import app.georentate.barcodescanner.view.MainActivity.Companion.resultList
import kotlinx.android.synthetic.main.scan_result_item.view.*
import java.lang.Exception

/*
    recycler view realisation
 */
class RecyclerViewAdapter(var resultList: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ResultViewHolder>() {
    fun updateResultList(newResults: List<String>) {
        resultList.clear()
        resultList.addAll(newResults)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ResultViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.scan_result_item, parent, false)
    )

    override fun getItemCount() = resultList.size

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    class ResultViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var resultText = view.result_text

        fun bind(result: String) {
            resultText?.setText(result)

            /*
                handling code editing
             */
            resultText.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    resultList.set(adapterPosition, p0.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        }
    }
}