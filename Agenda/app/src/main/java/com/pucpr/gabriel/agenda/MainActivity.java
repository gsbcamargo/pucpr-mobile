package com.pucpr.gabriel.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private PessoaDAO pessoaDAO;

    private Pessoa pessoa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        endereco = findViewById(R.id.editEndereco);
        telefone = findViewById(R.id.editTelefone);
        pessoaDAO = new PessoaDAO(this);

        Intent intent = getIntent();
        if (intent.hasExtra("pessoa")) {
            pessoa = (Pessoa) intent.getSerializableExtra("pessoa");
            nome.setText(pessoa.getNome());
            endereco.setText(pessoa.getEndereco());
            telefone.setText(pessoa.getTelefone());
        }
    }

    public void salvar(View view) {
        if (pessoa == null) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome.getText().toString());
            pessoa.setEndereco(endereco.getText().toString());
            pessoa.setTelefone(telefone.getText().toString());
            long id = pessoaDAO.inserir(pessoa);
            Toast.makeText(this, "Pessoa inserida com o id: " + id, Toast.LENGTH_SHORT).show();
        }

        pessoa.setNome(nome.getText().toString());
        pessoa.setEndereco(endereco.getText().toString());
        pessoa.setTelefone(telefone.getText().toString());
        pessoaDAO.atualizar(pessoa);
        Toast.makeText(this, "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();

    }
}