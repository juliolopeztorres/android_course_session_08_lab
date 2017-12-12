package oob.menufy.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import oob.menufy.Fragment.AlertFragment;
import oob.menufy.Fragment.EmailFragment;
import oob.menufy.Fragment.InformationFragment;
import oob.menufy.R;

public class MainActivity extends AppCompatActivity {

    private static final  String optionMessage = "The option is %s";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Switch switchOption;

    private EmailFragment emailFragment = new EmailFragment();
    private AlertFragment alertFragment = new AlertFragment();
    private InformationFragment informationFragment = new InformationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bindUI();
        this.setHomeButton();
        this.setEventListeners();
        this.setDefaultFragment();
    }

    private void bindUI() {
        this.toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.drawerLayout = this.findViewById(R.id.drawerLayout);
        this.navigationView = this.findViewById(R.id.navigationView);
        this.switchOption = (Switch) this.navigationView.getMenu().findItem(R.id.menu_option).getActionView();
    }

    private void setHomeButton() {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEventListeners() {
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationItemClicked(item);
                return true;
            }
        });
        this.switchOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(
                    MainActivity.this,
                    String.format(MainActivity.optionMessage, b ? "checked" : "unchecked"),
                    Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigationItemClicked(MenuItem item) {
        Fragment fragment = this.getFragment(item.getItemId());

        if (fragment == null) {
            return;
        }
        updateFragmentView(item, fragment);
    }

    private void updateFragmentView(MenuItem item, Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMainContent, fragment).commit();
        this.getSupportActionBar().setTitle(item.getTitle());
        item.setChecked(true);
        this.drawerLayout.closeDrawers();
    }

    private void setDefaultFragment() {
        this.updateFragmentView(this.navigationView.getMenu().getItem(0), this.emailFragment);
    }

    private Fragment getFragment(int itemId) {
        switch (itemId) {
            case R.id.menu_mail:
                return this.emailFragment;
            case R.id.menu_alert:
                return this.alertFragment;
            case R.id.menu_info:
                return this.informationFragment;
            default:
                return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.drawerLayout.openDrawer(Gravity.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
