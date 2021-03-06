package io.maddevs.openfreecabs.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import io.maddevs.openfreecabs.R;
import io.maddevs.openfreecabs.adapters.NearCabsAdapter;
import io.maddevs.openfreecabs.models.CompanyModel;
import io.maddevs.openfreecabs.utils.DataStorage;
import io.maddevs.openfreecabs.utils.views.DividerItemDecoration;

/**
 * Created by rustam on 28.08.16.
 */
public class NearCabListActivity extends AppCompatActivity implements NearCabsAdapter.OnItemClickListener {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));
        recyclerView.setAdapter(new NearCabsAdapter(this));
    }

    @Override
    public void onClick(CompanyModel item) {
        if (item.contacts != null && item.contacts.size() > 0) {
            DataStorage.instance.selectedCompanyContacts = item.contacts;
            Intent intent = new Intent(this, ContactsActivity.class);
            intent.putExtra("companyName", item.name);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.no_contacts, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
