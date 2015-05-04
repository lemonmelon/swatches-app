package dk.lemonmelon.swatches;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class LoadSwatches extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_swatches);

        Intent goToViewSwatches = new Intent(this, ViewSwatches.class);
        String defaultSwatchSetData = readDefaultSwatchSet();
        goToViewSwatches.putExtra(ViewSwatches.SWATCHES, defaultSwatchSetData);
        startActivity(goToViewSwatches);
    }

    private String readDefaultSwatchSet() {
        InputStream is = this.getResources().openRawResource(R.raw.first_swatch);
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
