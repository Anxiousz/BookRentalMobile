package com.technifysoft.bookapp.filter;

import android.widget.Filter;

import com.technifysoft.bookapp.adapter.AdapterPdfAdmin;
import com.technifysoft.bookapp.model.ModelPdf;

import java.util.ArrayList;

public class FilterPdfAdmin extends Filter {
    ArrayList<ModelPdf> filterList;
    AdapterPdfAdmin adapterPdfAdmin;

    public FilterPdfAdmin(ArrayList<ModelPdf> filterList, AdapterPdfAdmin adapterPdfAdmin) {
        this.filterList = filterList;
        this.adapterPdfAdmin = adapterPdfAdmin;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value should not be null and empty
        if (constraint != null && constraint.length() > 0) {

            //change to upper case, or lower case to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelPdf> filteredModels = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                //validate
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)) {
                    //add to filtered list
                    filteredModels.add(filterList.get(i));
                }
            }

            results.count = filteredModels.size();
            results.values = filteredModels;

        } else {
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        //apply filter change
        adapterPdfAdmin.pdfArrayList = (ArrayList<ModelPdf>) results.values;

        //notify change
        adapterPdfAdmin.notifyDataSetChanged();
    }
}
