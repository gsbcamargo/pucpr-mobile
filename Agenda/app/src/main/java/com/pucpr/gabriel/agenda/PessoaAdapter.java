package com.pucpr.gabriel.agenda;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PessoaAdapter extends BaseAdapter {

    private List<Pessoa> pessoas;
    private Activity activity;

    public PessoaAdapter(Activity activity, List<Pessoa> pessoas) {
        this.activity = activity;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int i) {
        return pessoas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pessoas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView nome = v.findViewById(R.id.text_nome);
        TextView endereco = v.findViewById(R.id.text_endereco);
        TextView telefone = v.findViewById(R.id.text_telefone);
        Pessoa pessoa = pessoas.get(i);
        nome.setText(pessoa.getNome());
        endereco.setText(pessoa.getEndereco());
        telefone.setText(pessoa.getTelefone());

        return v;
    }
}
