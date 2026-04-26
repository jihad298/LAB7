package com.example.starsgallery.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.service.StarService;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder>
        implements Filterable {

    private List<Star> stars;
    private List<Star> starsFilter;
    private final Context context;
    private final NewFilter mfilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = new ArrayList<>(stars);
        this.starsFilter = new ArrayList<>(stars);
        this.mfilter = new NewFilter(this);
    }

    // ── ViewHolder ──────────────────────────────────────────────
    public static class StarViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, role, idss;
        RatingBar stars;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            img   = itemView.findViewById(R.id.img);
            name  = itemView.findViewById(R.id.name);
            role  = itemView.findViewById(R.id.role);
            idss  = itemView.findViewById(R.id.ids);
            stars = itemView.findViewById(R.id.stars);
        }
    }

    // ── onCreateViewHolder ───────────────────────────────────────
    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        StarViewHolder holder = new StarViewHolder(v);

        // Clic → popup de notation
        v.setOnClickListener(view -> showEditPopup(view, holder));
        return holder;
    }

    // ── onBindViewHolder ─────────────────────────────────────────
    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star s = starsFilter.get(position);

        holder.role.setText(s.getRole());
        holder.name.setText(s.getName());
        holder.stars.setRating(s.getStar());
        holder.idss.setText(String.valueOf(s.getId()));

        // ✅ Chargement depuis res/mipmap avec l'ID de ressource
        Glide.with(context)
                .load(s.getImgRes())
                .apply(new RequestOptions().circleCrop().override(90, 90))
                .into(holder.img);
    }

    @Override
    public int getItemCount() { return starsFilter.size(); }

    // ── Popup modification de note ───────────────────────────────
    private void showEditPopup(View row, StarViewHolder holder) {
        View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null);

        CircleImageView popupImg  = popup.findViewById(R.id.img);
        RatingBar       popupBar  = popup.findViewById(R.id.ratingBar);
        TextView        popupId   = popup.findViewById(R.id.idss);
        TextView        popupRole = popup.findViewById(R.id.role);

        Bitmap bmp = ((BitmapDrawable) ((CircleImageView) row.findViewById(R.id.img))
                .getDrawable()).getBitmap();
        popupImg.setImageBitmap(bmp);
        popupBar.setRating(((RatingBar) row.findViewById(R.id.stars)).getRating());
        popupId.setText(((TextView) row.findViewById(R.id.ids)).getText());
        popupRole.setText(((TextView) row.findViewById(R.id.role)).getText());

        new AlertDialog.Builder(context)
                .setTitle("" + ((TextView) row.findViewById(R.id.role)).getText())
                .setMessage("Modifier la popularité de ce personnage :")
                .setView(popup)
                .setPositiveButton("Valider", (dialog, which) -> {
                    int id = Integer.parseInt(popupId.getText().toString());
                    Star target = StarService.getInstance().findById(id);
                    if (target != null) {
                        target.setStar(popupBar.getRating());
                        StarService.getInstance().update(target);
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    // ── Filtre ───────────────────────────────────────────────────
    @Override
    public Filter getFilter() { return mfilter; }

    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Star> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(stars);
            } else {
                String keyword = charSequence.toString().toLowerCase().trim();
                for (Star s : stars) {
                    // Recherche sur le rôle ET sur le nom de l'acteur
                    if (s.getRole().toLowerCase().contains(keyword)
                            || s.getName().toLowerCase().contains(keyword)) {
                        filtered.add(s);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            results.count  = filtered.size();
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Star>) filterResults.values;
            mAdapter.notifyDataSetChanged();
        }
    }
}