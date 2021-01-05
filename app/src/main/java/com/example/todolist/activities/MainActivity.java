package com.example.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.db.CrudHelperImpl;
import com.example.todolist.db.DbHandler;
import com.example.todolist.entities.Todo;
import com.example.todolist.views.TodoListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Todo> todos = new ArrayList<>();
    private FloatingActionButton fab;
    private RecyclerView todo_list;
    private TodoListAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findUsefulComponents();
        fab.setOnClickListener(v -> startActivity(new Intent(this, AddActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateRecyclerView();
    }

    private void findUsefulComponents() {
        fab = findViewById(R.id.fab);
        todo_list = (RecyclerView) findViewById(R.id.todo_list);
    }

    private void populateRecyclerView(){
        DbHandler handler = new DbHandler(this);
        CrudHelperImpl db = new CrudHelperImpl(handler);
        todos = db.findAll();
        todoAdapter = new TodoListAdapter(this, todos);

        todo_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        todo_list.setItemAnimator(new DefaultItemAnimator());
        todo_list.setAdapter(todoAdapter);
        setItemAnimation();
    }

    private void setItemAnimation() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteTodo(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(todo_list);
    }

    public void deleteTodo(final int pos){
        new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja realmente excluir a tarefa?")
                .setPositiveButton("Sim", (dialog, btn) -> {
                    dialog.dismiss();
                    deleteTodoFromDB(pos);
                })
                .setNegativeButton("Não", (dialog, which) -> {})
                .setOnDismissListener(dialog -> todoAdapter.notifyDataSetChanged())
                .show();
    }

    public void deleteTodoFromDB(int pos) {
        CrudHelperImpl db = new CrudHelperImpl(new DbHandler(this));
        db.delete(todos.get(pos));
        todos.remove(pos);
        todoAdapter.notifyItemRemoved(pos);
        Toast.makeText(this, "Tarefa deletada com sucesso", Toast.LENGTH_SHORT).show();
    }
}
