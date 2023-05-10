package moutai.yuyucheng.task71;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewStatus;
    private TextView textViewName;
    private Button buttonDelete;
    private TextView textViewDate;
    private TextView textViewDescription;
    private TextView textViewLocation;
    private TextView textViewPhone;
    private DatabaseHelper databaseHelper;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewStatus = findViewById(R.id.text_view_status);
        textViewName = findViewById(R.id.text_view_name);
        buttonDelete = findViewById(R.id.button_delete);
        textViewDate = findViewById(R.id.text_view_date);
        textViewDescription = findViewById(R.id.text_view_description);
        textViewLocation = findViewById(R.id.text_view_location);
        textViewPhone = findViewById(R.id.text_view_phone);

        databaseHelper = new DatabaseHelper(this);

        id = getIntent().getLongExtra("id", -1);
        loadData();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = databaseHelper.deleteData(id);
                if (isDeleted) {
                    Toast.makeText(DetailActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailActivity.this, "Error Deleting Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        Cursor cursor = databaseHelper.getAllData();
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                if (idIndex != -1 && cursor.getLong(idIndex) == id) {
                    int statusIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS);
                    if (statusIndex != -1) {
                        textViewStatus.setText(cursor.getString(statusIndex));
                    }

                    int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
                    if (nameIndex != -1) {
                        textViewName.setText(cursor.getString(nameIndex));
                    }

                    int dateIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE);
                    if (dateIndex != -1) {
                        textViewDate.setText(cursor.getString(dateIndex));
                    }

                    int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION);
                    if (descriptionIndex != -1) {
                        textViewDescription.setText(cursor.getString(descriptionIndex));
                    }

                    int locationIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LOCATION);
                    if (locationIndex != -1) {
                        textViewLocation.setText(cursor.getString(locationIndex));
                    }

                    int phoneIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE);
                    if (phoneIndex != -1) {
                        textViewPhone.setText(cursor.getString(phoneIndex));
                    }

                    break;
                }
            } while (cursor.moveToNext());
        }
    }


}

