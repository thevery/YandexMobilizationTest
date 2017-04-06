package com.applications.whazzup.yandextranslator.ui.screens.translate_detail.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.applications.whazzup.yandextranslator.R;
import com.applications.whazzup.yandextranslator.data.storage.realm.TranslateRealm;
import com.applications.whazzup.yandextranslator.ui.screens.language.LanguageAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<TranslateRealm> list = new ArrayList<>();
    private onHistoryClickListener mListener;

    public HistoryAdapter(onHistoryClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(v);
    }

    public void addItem(TranslateRealm translateRealm){
        list.add(translateRealm);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TranslateRealm realm = list.get(position);
        holder.isFacoriteCheckBox.setChecked(realm.isFavorite());
        holder.mOriginalText.setText(realm.getOriginalText());
        holder.mTranslateText.setText(realm.getTranslateText());
        holder.mDirection.setText(realm.getDirection());

    }

    public TranslateRealm getTranslateFromPostition(int position){
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearHistory() {
        list.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.favorite_cbx)
        CheckBox isFacoriteCheckBox;
        @BindView(R.id.original_txt)
        TextView mOriginalText;
        @BindView(R.id.translate_txt)
        TextView mTranslateText;
        @BindView(R.id.direction_txt)
        TextView mDirection;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            isFacoriteCheckBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener!=null){
                mListener.onHistoryItemClick(getAdapterPosition(), v);
            }
        }
    }

    public interface onHistoryClickListener{
        void onHistoryItemClick(int position, View v);
    }
}
