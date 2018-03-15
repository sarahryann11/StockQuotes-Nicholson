package csci490.stockquotes_nicholson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    private TextView symbol;
    private TextView name;
    private TextView price;
    private TextView time;
    private TextView change;
    private TextView range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = this.findViewById(R.id.editText);

        symbol = this.findViewById(R.id.symbol);
        name = this.findViewById(R.id.name);
        price = this.findViewById(R.id.price);
        time = this.findViewById(R.id.time);
        change = this.findViewById(R.id.change);
        range = this.findViewById(R.id.range);

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                loadStockQuote quote = new loadStockQuote(MainActivity.this, symbol, name, price, time, change, range);
                quote.execute(editText.getText().toString());
                return true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("stockInput", editText.getText().toString());
        outState.putString("symbol", symbol.getText().toString());
        outState.putString("name", name.getText().toString());
        outState.putString("price", price.getText().toString());
        outState.putString("time", time.getText().toString());
        outState.putString("change", change.getText().toString());
        outState.putString("range", range.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        editText.setText(savedInstanceState.getString("stockInput"));
        symbol.setText(savedInstanceState.getString("symbol"));
        name.setText(savedInstanceState.getString("name"));
        price.setText(savedInstanceState.getString("price"));
        time.setText(savedInstanceState.getString("time"));
        change.setText(savedInstanceState.getString("change"));
        range.setText(savedInstanceState.getString("range"));
    }

}

