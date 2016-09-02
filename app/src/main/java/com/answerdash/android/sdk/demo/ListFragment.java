package com.answerdash.android.sdk.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.answerdash.android.sdk.AnswerDashButton;

import java.util.List;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final CountryAdapter adapter = new CountryAdapter(Utilities.getAvailableCountries());
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        final AnswerDashButton helpButton = (AnswerDashButton) view.findViewById(R.id.helpButton);
        helpButton.setBorderColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
    }

    private static class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

        @NonNull
        private List<String> countries;

        public CountryAdapter(@NonNull List<String> countries) {

            this.countries = countries;
        }

        @Override
        public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_country, parent, false);
            return new CountryViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(CountryViewHolder holder, int position) {

            String country = countries.get(position);
            holder.bind(country);
        }

        @Override
        public int getItemCount() {
            return countries.size();
        }
    }

    private static class CountryViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private TextView countryText;

        public CountryViewHolder(View itemView) {

            super(itemView);

            countryText = (TextView) itemView.findViewById(R.id.countryText);
        }

        public void bind(@NonNull final String countryName) {

            countryText.setText(countryName);
        }

    }

}
