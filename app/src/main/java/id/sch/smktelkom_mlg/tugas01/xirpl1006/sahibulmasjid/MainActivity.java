package id.sch.smktelkom_mlg.tugas01.xirpl1006.sahibulmasjid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etTanggal, etAlamat;
    //    RadioButton rbPr, rbLk;
    RadioGroup rg;
    CheckBox cbSBA, cbHAH, cbQ, cbSK;
    Button bOK;
    Spinner spProvinsi, spKota;
    TextView tvHP, tvJK, tvBidang, tvHasil, tvJudul;
    String[][] arKota = {{"Jakarta Barat", "Jakarta Pusat", "Jakarta Selatan", "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi", "Bogor"},
            {"Semarang", "Magelang", "Surakarta"},
            {"Surabaya", "Malang", "Blitar", "Kediri", "Banyuwangi", "Madura"},
            {"Denpasar", "Tabanan"}};
    ArrayList<String> ListKota = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);
        tvHP = (TextView) findViewById(R.id.textViewHProvinsi);
        tvBidang = (TextView) findViewById(R.id.textViewBidang);
        tvJK = (TextView) findViewById(R.id.textViewJK);
        tvJudul = (TextView) findViewById(R.id.textViewJudul);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        etNama = (EditText) findViewById(R.id.editTextNama);
        etAlamat = (EditText) findViewById(R.id.editTextAlamat);
        etTanggal = (EditText) findViewById(R.id.editTextTanggal);
        rg = (RadioGroup) findViewById(R.id.jenk);
        cbSBA = (CheckBox) findViewById(R.id.checkBoxSBA);
        cbSK = (CheckBox) findViewById(R.id.checkBoxSK);
        cbQ = (CheckBox) findViewById(R.id.checkBoxQ);
        cbHAH = (CheckBox) findViewById(R.id.checkBoxHAH);
        bOK = (Button) findViewById(R.id.buttonOK);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ListKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);


        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                ListKota.clear();
                ListKota.addAll(Arrays.asList(arKota[pos]));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProses();
            }
        });

    }

    private void doProses() {
        if (isValid()) {
            String Nama = etNama.getText().toString();
            String Tanggal = etTanggal.getText().toString();
            String Alamat = etAlamat.getText().toString();
            String Kota = spKota.getSelectedItem().toString();
            String Provinsi = spProvinsi.getSelectedItem().toString();

            tvJudul.setText("\n Data Diri Pendaftar \n");
            tvHasil.setText(
                    "Nama               : " + Nama + "\n" +
                            "Tempat Lahir   : " + Kota + ", " + Provinsi + "\n" +
                            "Tanggal Lahir  : " + Tanggal + "\n" +
                            "Alamat             : " + Alamat + "\n"
            );

            String JK = "";
            if (rg.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                JK = rb.getText().toString();
            }
            if (JK == "") {
                tvJK.setText("Anda belum memilih data Jenis Kelamin");
            } else {
                tvJK.setText("Jenis Kelamin   : " + JK);
            }

            String Bidang = "";
            int startlen = Bidang.length();
            if (cbSBA.isChecked()) Bidang += cbSBA.getText() + ", ";
            if (cbHAH.isChecked()) Bidang += cbHAH.getText() + ", ";
            if (cbSK.isChecked()) Bidang += cbSK.getText() + ", ";
            if (cbQ.isChecked()) Bidang += cbQ.getText();
            if (Bidang.length() == startlen) {
                tvBidang.setText("Anda belum memilih Bidang yang Anda minati \n");
            } else {
                tvBidang.setText("Bidang yang Anda minati    : " + Bidang);
            }

        }
    }

    private boolean isValid() {
        boolean valid = true;

        String Nama = etNama.getText().toString();
        String Tanggal = etTanggal.getText().toString();
        String Alamat = etAlamat.getText().toString();

        if (Nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (Nama.length() != 3) {
            etNama.setError("Nama minimal 3 digit");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (Alamat.isEmpty()) {
            etAlamat.setError("Alamat belum diisi");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (Tanggal.isEmpty()) {
            etTanggal.setError("Tanggal Lahir belum diisi");
            valid = false;
        } else if (Tanggal.length() != 8) {
            etTanggal.setError("Format Tanggal Lahir ddmmyy");
            valid = false;
        } else {
            etTanggal.setError(null);
        }
        return valid;

    }

}

