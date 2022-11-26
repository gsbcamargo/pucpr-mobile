package com.pucpr.gabriel.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Conexao conexao;
    private SQLiteDatabase db;

    public PessoaDAO(Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long inserir(Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("endereco", pessoa.getEndereco());
        values.put("telefone", pessoa.getTelefone());

       return db.insert("pessoa", null, values);
    }

    public List<Pessoa> obterTodas() {
        List<Pessoa> pessoas = new ArrayList<>();
        Cursor cursor = db.query("pessoa", new String[]{"id", "nome", "endereco", "telefone"},
                null, null, null, null, null);
        while(cursor.moveToNext()) {
            Pessoa p = new Pessoa();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setEndereco(cursor.getString(2));
            p.setTelefone(cursor.getString(3));
            pessoas.add(p);
        }
        return pessoas;
    }

    public void excluir(Pessoa pessoa) {
        db.delete("pessoa", "id = ?", new String[]{pessoa.getId().toString()});
    }

    public void atualizar(Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put("nome", pessoa.getNome());
        values.put("endereco", pessoa.getEndereco());
        values.put("telefone", pessoa.getTelefone());
        db.update("pessoa", values, "id = ?", new String[]{pessoa.getId().toString()});
    }
}
