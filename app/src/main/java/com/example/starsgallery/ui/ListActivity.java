package com.example.starsgallery.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.starsgallery.R;
import com.example.starsgallery.adapter.StarAdapter;
import com.example.starsgallery.service.StarService;

public class ListActivity extends AppCompatActivity {

    private StarAdapter starAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView rv = findViewById(R.id.recycle_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        starAdapter = new StarAdapter(this, StarService.getInstance().findAll());
        rv.setAdapter(starAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // On utilise main_menu au lieu de menu → plus de conflit !
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();

            if (searchView != null) {
                searchView.setQueryHint("Chercher un personnage GoT...");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String q) {
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (starAdapter != null) {
                            starAdapter.getFilter().filter(newText);
                        }
                        return true;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            new ShareCompat.IntentBuilder(this)
                    .setType("text/plain")
                    .setChooserTitle("Partager StarsGallery GoT")
                    .setText("Découvrez StarsGallery — Game of Thrones Edition ! ")
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}