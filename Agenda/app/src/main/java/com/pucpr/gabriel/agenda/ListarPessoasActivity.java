package com.pucpr.gabriel.agenda;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarPessoasActivity extends AppCompatActivity {

    private ListView listView;
    private PessoaDAO pessoaDAO;
    private List<Pessoa> pessoas;
    private final List<Pessoa> pessoasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);

        listView = findViewById(R.id.lista_pessoas);
        pessoaDAO = new PessoaDAO(this);
        pessoas = pessoaDAO.obterTodas();
        pessoasFiltradas.addAll(pessoas);
       // ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoasFiltradas);
        PessoaAdapter adapter = new PessoaAdapter(this, pessoasFiltradas);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_principal, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procurarPessoa(newText);
                System.out.println("Digitou " + newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_contexto, menu);
    }

    public void procurarPessoa(String nome) {
        pessoasFiltradas.clear();
        for (Pessoa p : pessoas) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                pessoasFiltradas.add(p);
            }
        }
        listView.invalidateViews();
    }

    public void cadastrar(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void editar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pessoa pessoaParaAtualizar = pessoasFiltradas.get(menuInfo.position);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("pessoa", pessoaParaAtualizar);
        startActivity(intent);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pessoa pessoaParaExcluir = pessoasFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Deseja excluir o contato?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    pessoasFiltradas.remove(pessoaParaExcluir);
                    pessoas.remove(pessoaParaExcluir);
                    pessoaDAO.excluir(pessoaParaExcluir);
                    listView.invalidateViews();
                }).create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        pessoas = pessoaDAO.obterTodas();
        pessoasFiltradas.clear();
        pessoasFiltradas.addAll(pessoas);
        listView.invalidateViews();
    }
}