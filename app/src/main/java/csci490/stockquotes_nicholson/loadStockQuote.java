package csci490.stockquotes_nicholson;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sarah on 3/15/2018.
 */

public class loadStockQuote extends AsyncTask<String, Void, Stock> {
    private TextView symbol;
    private TextView name;
    private TextView price;
    private TextView time;
    private TextView change;
    private TextView range;

    private Context context;


    public loadStockQuote(Context context, TextView symbol, TextView name, TextView price, TextView time, TextView change, TextView range)
    {
        this.context = context;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.time = time;
        this.change = change;
        this.range = range;
    }

    @Override
    protected Stock doInBackground(String... params) {

        try {
            Stock stock = new Stock(params[0]);
            stock.load();
            return stock;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Stock stock) {
        if (stock != null && stock.getSymbol() != "" && stock.getSymbol().length() <= 4) {
            symbol.setText(stock.getSymbol());
            name.setText(stock.getName());
            price.setText(stock.getLastTradePrice());
            time.setText(stock.getLastTradeTime());
            change.setText(stock.getChange());
            range.setText(stock.getRange());
            Toast toast = Toast.makeText(context, "Quote for " + stock.getName(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }
}