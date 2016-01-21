package com.takusemba.espressodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener {

	@Bind(R.id.toolbar)
	Toolbar mToolbar;

	@Bind(R.id.error_message)
	TextView mErrorMessage;

	@Bind(R.id.user_id)
	EditText mUserId;

	@Bind(R.id.password)
	EditText mPassword;

	@Bind(R.id.login_button)
	Button mLoginButton;

	String mMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);
		mLoginButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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

	@Override
	public void onClick(View view) {
		if (isEverythingOk()) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			intent.putExtra("user_id", mUserId.getText().toString());
			startActivity(intent);
		} else {
			mErrorMessage.setText(mMessage);
		}
	}

	private boolean isEverythingOk() {
		String userId = mUserId.getText().toString();
		String password = mPassword.getText().toString();
		if (userId.isEmpty()) {
			mMessage = getString(R.string.error_user_id_is_empty);
			return false;
		} else if (password.isEmpty()) {
			mMessage = getString(R.string.error_pw_is_empty);
			return false;
		} else if (password.length() < 4) {
			mMessage = getString(R.string.error_pw_is_less_than_4);
			return false;
		} else if (!password.matches(".*\\d.*")) {
			mMessage = getString(R.string.error_pw_needs_numbers);
			return false;
		} else if (!password.matches(".*[a-zA-Z]+.*")) {
			mMessage = getString(R.string.error_pw_needs_characters);
			return false;
		} else {
			mMessage = "";
			return true;
		}
	}
}
