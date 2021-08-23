package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var titleTodo: RecyclerView
    private lateinit var buttonAdd: Button
    private lateinit var buttonDelete: Button
    private lateinit var todoTitle: EditText
    private val api by lazy {ApiRetrofit().endpoint}



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getTodo()
        setupList()

//        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        setupView()
        setupListener()


//
//        btnAddTodo.setOnClickListener {
//            val todoTitle = etTodoTitle.text.toString()
//            if(todoTitle.isNotEmpty()) {
//                val todo = Todo(todoTitle)
//                todoAdapter.addTodo(todo)
//                etTodoTitle.text.clear()
//            }
//        }
//        btnDeleteTodo.setOnClickListener {
//            todoAdapter.deleteTodos()
//        }
    }

    override fun onStart() {
        super.onStart()
        getTodo()
    }

    private fun setupListener() {
       buttonAdd.setOnClickListener {
//           val intent = Intent(this@MainActivity, MainActivity::class.java)
//           startActivity(intent)
//           if (todoTitle.text.toString().isNotEmpty()) {

               api.create(todoTitle.text.toString())
                       .enqueue(object : Callback<SubmitModel> {
                           override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
//                               print("ini adalah response = "+ response)
                               if (response.isSuccessful) {
                                   val submit = response.body()
                                   Toast.makeText(this@MainActivity, submit!!.message, Toast.LENGTH_SHORT).show()
                                   print("ini adalah response = "+ response)
                               }
                           }

                           override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                               Toast.makeText(this@MainActivity, "add list fail", Toast.LENGTH_SHORT).show()
                           }
                       })
//           } else {
//               Toast.makeText(this@MainActivity, "empty box", Toast.LENGTH_SHORT).show()
//           }

           todoTitle.text.clear()
       }
    }

    private fun setupView() {
        buttonAdd = findViewById(R.id.btnAddTodo)
        buttonDelete = findViewById(R.id.btnDeleteTodo)
        todoTitle = findViewById(R.id.etTodoTitle)

    }

    private fun setupList() {
        titleTodo = findViewById(R.id.rvTodoItems)
        todoAdapter = TodoAdapter(arrayListOf())
        titleTodo.adapter = todoAdapter
    }

    private fun getTodo() {
        api.data().enqueue(object : Callback<NoteModel> {
            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                if (response.isSuccessful) {
                    val listData = response.body()!!.notes
                    todoAdapter.setData(listData)
                }
            }

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }
}