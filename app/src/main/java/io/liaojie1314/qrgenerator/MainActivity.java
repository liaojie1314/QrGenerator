package io.liaojie1314.qrgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText mEtInput;
    private Button mBtGenerate;
    private ImageView mIvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtInput = findViewById(R.id.et_input);
        mBtGenerate = findViewById(R.id.bt_generate);
        mIvOutput = findViewById(R.id.iv_output);

        mBtGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = mEtInput.getText().toString().trim();
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE,
                            350, 350);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    mIvOutput.setImageBitmap(bitmap);
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    //隐藏键盘
                    manager.hideSoftInputFromWindow(mEtInput.getApplicationWindowToken(),
                            0);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}