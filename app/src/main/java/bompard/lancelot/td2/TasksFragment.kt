package bompard.lancelot.td2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.item_task.*

class TasksFragment : Fragment(R.layout.fragment_tasks) {


    private val tasks = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3")
    )

    private val adapter = TasksAdapter(tasks)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasks_recycler_view.adapter = adapter
        tasks_recycler_view.layoutManager = LinearLayoutManager(activity)
        adapter.onDeleteClickListener = { task -> tasks.remove(task)
        adapter.notifyDataSetChanged()


        }

        adapter.onEditClickListener = { task ->
            val intent = Intent(activity, TaskActivity::class.java)
            intent.putExtra(TASK_KEY, task)
            startActivityForResult(intent, EDIT_TASK_REQUEST_CODE)


        }

        floatingActionButton.setOnClickListener {

            val intent = Intent(activity, TaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)

        }





    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            val task = data!!.getSerializableExtra("TASK") as Task
            tasks.add(task)
            adapter.notifyItemInserted(tasks.size)
        }

    }

    companion object {
        private const val ADD_TASK_REQUEST_CODE = 666
        private const val TASK_KEY = "task_key"
        private const val EDIT_TASK_REQUEST_CODE = 1
    }

}