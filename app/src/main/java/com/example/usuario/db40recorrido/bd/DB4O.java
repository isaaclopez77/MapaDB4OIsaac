package com.example.usuario.db40recorrido.bd;

import android.content.Context;
import android.util.Log;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.example.usuario.db40recorrido.pojo.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 4/6/2016.
 */
public class DB4O {

    private Context ctx;
    private ObjectContainer db;

    public void create(Context ctx) {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), ctx.getExternalFilesDir(null) + "/bdDB4O.db4o");
    }

    public void close() {
        db.close();
    }

    public void addPosition(Position p) {
        db.store(p);
        db.commit();
    }

    public List<Position> getPositions(String day) {
        List<Position> positionsR = new ArrayList<>();
        Query query = db.query();
        query.constrain(Position.class);
        ObjectSet<Position> positions = query.execute();
        for(Position p :positions){
            Log.v("estado",p.toString());
        }

        for (Position p : positions) {
            if (day.compareTo(p.getDay()) == 0) {
                positionsR.add(p);
            }
        }
        return positionsR;
    }

    public void deletePosition(Position p) {
        db.delete(p);
        db.commit();
    }
}
