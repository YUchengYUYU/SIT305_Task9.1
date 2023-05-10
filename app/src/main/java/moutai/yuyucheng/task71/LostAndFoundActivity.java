package moutai.yuyucheng.task71;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LostAndFoundActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioLost;
    private RadioButton radioFound;
    private EditText editTextName,editTextPhone,editTextDescription,editTextDate,editTextLocation;
    private Button buttonSave;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found);

        radioGroup = findViewById(R.id.radio_group);
        radioLost = findViewById(R.id.radio_lost);
        radioFound = findViewById(R.id.radio_found);
        editTextName = findViewById(R.id.edit_text_name);
        buttonSave = findViewById(R.id.button_save);

        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextDate = findViewById(R.id.edit_text_date);
        editTextLocation = findViewById(R.id.edit_text_location);

        databaseHelper = new DatabaseHelper(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = radioLost.isChecked() ? "lost" : "found";
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String location = editTextLocation.getText().toString().trim();

                long id = databaseHelper.insertData(status, name, date, phone, description, location);
                if (id > 0) {
                    Toast.makeText(LostAndFoundActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LostAndFoundActivity.this, "Error Saving Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
