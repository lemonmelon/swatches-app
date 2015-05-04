package dk.lemonmelon.swatches;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ViewSwatches extends Activity {
    public static String SWATCHES = "dk.lemonmelon.swatches.ViewSwatches.SWATCHES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = getApplicationContext();

        Intent creatingIntent = getIntent();
        String swatchesData = creatingIntent.getStringExtra(SWATCHES);
        Swatch[] swatches = parseSwatchData(swatchesData);

        View[] swatchRows = buildSwatchView(swatches, context);

        setContentView(R.layout.activity_view_swatches);

        LinearLayout.LayoutParams swatchRowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 0, 1);
        LinearLayout swatchContainer = (LinearLayout) findViewById(R.id.swatch_container);
        for(View row : swatchRows) {
            swatchContainer.addView(row, swatchRowParams);
        }

        View nextSwatchButton = findViewById(R.id.next_swatch_button);
        nextSwatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click event not implemented yet", Toast.LENGTH_SHORT).show();
                //TODO: find next swatch
            }
        });
    }

    private Swatch[] parseSwatchData(String data) {
        data = data.replaceAll("\r", "");
        String[] swatchDataSets = data.split("\n");

        //For now, we only handle the first data set.
        //This should be fixed in the future.
        String[] swatchDatas = swatchDataSets[0].split(";");
        Swatch[] swatches = new Swatch[swatchDatas.length];
        for(int i = 0; i < swatchDatas.length; i++) {
            swatches[i] = parseSingleSwatchData(swatchDatas[i]);
        }
        return swatches;
    }

    private Swatch parseSingleSwatchData(String data) {
        String[] colors = data.split(",");
        int r = Integer.parseInt(colors[0]);
        int g = Integer.parseInt(colors[1]);
        int b = Integer.parseInt(colors[2]);

        return new Swatch(r, g, b);
    }

    private View[] buildSwatchView(Swatch[] swatches, Context c) {
        View[] rows = new View[3];
        for(int i = 0; i < 3; i++) {
            int offset = i * 3;
            rows[i] = buildSwatchRow(swatches, offset, c);
        }
        return rows;
    }

    private LinearLayout buildSwatchRow(Swatch[] swatches, int offset, Context c) {
        LinearLayout.LayoutParams singleSwatchParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.FILL_PARENT, 1);

        LinearLayout row = new LinearLayout(c);
        for(int j = offset; j < offset + 3; j++) {
            Swatch s = swatches[j];
            View v = buildSingleSwatch(s, c);
            row.addView(v, singleSwatchParams);
        }
        return row;
    }

    private TextView buildSingleSwatch(Swatch s, Context c) {
        Typeface tf = Typeface.create("sans", Typeface.BOLD);

        TextView v = new TextView(c);
        v.setText(s.getName());
        v.setBackgroundColor(s.getColor());
        v.setTextColor(getResources().getColor(R.color.white));
        v.setGravity(Gravity.CENTER);
        v.setShadowLayer(12f, 0f, 0f, R.color.black);
        v.setTypeface(tf);

        final Context context = c;
        final Swatch swatch = s;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Color from Swatches", "rgb" + swatch.getName());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_swatches, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
