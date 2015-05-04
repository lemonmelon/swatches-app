package dk.lemonmelon.swatches;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class LoadSwatches extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_swatches);

        Intent goToViewSwatches = new Intent(this, ViewSwatches.class);
        goToViewSwatches.putExtra(ViewSwatches.SWATCHES, "46,204,113;39,174,96;241,196,15;243,156,18;231,76,60;236,240,241;189,195,199;149,165,166;127,140,141\n");
        startActivity(goToViewSwatches);
    }
}
