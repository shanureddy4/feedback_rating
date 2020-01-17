package com.example.feedback_rating
// here we used rating listeners instead we can use getRating() and this to be in the button listener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.service.autofill.OnClickAction
import android.view.View
import android.widget.*
import com.beardedhen.androidbootstrap.BootstrapButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(p0: AdapterView<*>?) {}//spinner method
    var subs= arrayOf("---choose---","Webtech","Datamining","Android programming","Java","CPP")//this will be there in spinner and nothing more
    var average:Float=0.0F//for calculating average of all the rating bars
    private var sub:String=""// to use subs values in button and spinner method
    override fun onCreate(savedInstanceState: Bundle?) {
        var intent= Intent(this,MPGraph_view::class.java)
        var (a,b,c,d)= listOf<Float>(0.0F,0.0F,0.0F,0.0F)//variables of eadh rating bars for calculating average
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       var edittext=findViewById<EditText>(R.id.editText).text
        var button=findViewById<BootstrapButton>(R.id.button)
        //ratingbar
        var ratebar=findViewById<RatingBar>(R.id.ratingbar)
        ratebar.onRatingBarChangeListener
        ratebar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            a=fl
        }
        var ratebar1 = findViewById<RatingBar>(R.id.ratingbar1)
        ratebar1.onRatingBarChangeListener
        ratebar1.setOnRatingBarChangeListener { ratingBar, fl, c ->
            b=fl
        }
        var ratebar2 = findViewById<RatingBar>(R.id.ratingbar2)
        ratebar2.onRatingBarChangeListener
        ratebar2.setOnRatingBarChangeListener { ratingBar, fl, b ->
            c=fl
        }
        var ratebar3 = findViewById<RatingBar>(R.id.ratingbar3)
        ratebar3.onRatingBarChangeListener
        ratebar3.setOnRatingBarChangeListener { ratingBar, fl, b ->
            d=fl
        }
        //hash map for putting subs values and average
        var tree:HashMap<String,Float> = HashMap<String,Float>()
        button.setOnClickListener {
            if(tree.containsKey("---choose---"))
            {
                Toast.makeText(this,"Please select subject properly",Toast.LENGTH_SHORT).show()
                tree.remove("---choose---")
            }
            else {
                if (edittext.toString() == "") {
                    Toast.makeText(this, "Please Enter Id", Toast.LENGTH_SHORT).show()
                } else {

                    average = (a + b + c + d) / 4
                    tree.set(sub, average)
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()

                }
            }
        }
        var button2=findViewById<BootstrapButton>(R.id.button2)
        button2.setOnClickListener {
            if(tree.isEmpty())
            {
                Toast.makeText(this,"Please Choose atleast one subject",Toast.LENGTH_SHORT).show()
            }
            else {

                // for((key,value) in tree) { //tree.toSortedMap() //Toast.makeText(this,"${key.toString()+" "+value.toString()}",Toast.LENGTH_SHORT).show() }
                intent.putExtra("sub", tree)
                intent.putExtra("ID", edittext.toString())
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }


        }
        //spinner
        var spinner=findViewById<Spinner>(R.id.spin)
        var adapter:ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,subs)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.setAdapter(adapter)
        with(spinner)
        {
            onItemSelectedListener=this@MainActivity
        }
    }

//ratingbar function
   // override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {// var collect:Float=ratebar2!!.getRating() //check?.text=collect.toString() }

    //spinner function
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


        sub = subs[p2]
        Toast.makeText(this, sub, Toast.LENGTH_SHORT).show()

}
}
/**             summary
 * on changing rating bars the function ItemSelected will invoke and this will update variable sub
 * now on clicking OK button it will check whether the hashmap contain .....choose... value
 * if not then check the ID number if it is not null it will calculate the average of all the rating bars and store in hash tree
 * now on pressing SUBMIT button it will send the ID, hash tree to the another activity
 *
 */
