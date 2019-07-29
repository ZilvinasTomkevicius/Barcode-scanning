package app.georentate.barcodescanner.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import app.georentate.barcodescanner.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var startScanButton: Button

    companion object {
        var resultList = ArrayList<String>(arrayListOf())
        var recyclerViewAdapter = RecyclerViewAdapter(arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }

        recyclerViewAdapter.updateResultList(resultList)

        startScanButton = findViewById(R.id.scan_button)
        startScanButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ScanActivity::class.java))
        }
    }

    /*
        inflating delete button
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean{
       when(item!!.itemId) {
           R.id.bar_button_id -> {
               if(resultList.size > 0)
                   showAlert()
               return true
           }
       }
        return true
    }

    /*
        alert for deleting code list
     */
    private fun showAlert() {
        var alertDialog = AlertDialog.Builder(this)
            .setMessage("Clear list?")
            .setPositiveButton("Yes") { dialogInterface, i ->
                resultList.clear()
                recyclerViewAdapter.updateResultList(resultList)
            }
            .setNegativeButton("NO") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .show()
    }
}
