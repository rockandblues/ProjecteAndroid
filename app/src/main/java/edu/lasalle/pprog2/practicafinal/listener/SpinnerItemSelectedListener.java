package edu.lasalle.pprog2.practicafinal.listener;

import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import edu.lasalle.pprog2.practicafinal.adapters.PageAdapter;

/**
 * Created by MatiasVillarroel on 22/05/17.
 */

public class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> types;
    private PageAdapter pageAdapter;
    private boolean selectItem;

    public SpinnerItemSelectedListener(PageAdapter pageAdapter,ArrayList<String> types ){
        this.types = types;
        this.pageAdapter = pageAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //evita que se llame el metodo cuando se crea el spinner
        if (selectItem) {
            //Buscar el tipo y darselo a los fragments por medio del pageAdapter
            pageAdapter.notifyFilterSelected(types.get(position));
        }else {
            selectItem = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
