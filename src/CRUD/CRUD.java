package CRUD;

import Entidades.Entidade;
import java.util.ArrayList;

public class CRUD <T extends Entidade> {
    private ArrayList<T> list;

    public CRUD(ArrayList<T> list) { this.list = list; }

    public boolean inserir(T obj) {
        return list.add(obj);
    }

    public ArrayList<T> listar() {
        return list;
    }

    public T listar(int id) {
        for (T obj : list) {
            int id_obj = obj.getId();
            if (id_obj == id) return obj;
        }
        return null;
    }

    public boolean alterar(T obj) {
        int idx = list.indexOf(obj);
        T rtn = list.set(idx, obj);
        return rtn != null;
    }

    public boolean deletar(T obj) {
        return list.remove(obj);
    }
}
