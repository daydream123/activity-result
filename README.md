# activity-result

It's a new idea to implement auto login and startActivityResult() with a callback to receive activityResult.

# 1. How to use Activity inteceptor

## 1.1 Define inteceptor:

```java
public class LoginInterceptor extends Interceptor {

    @Override
    public int getRequestCode() {
        return 111;
    }

    @Override
    public boolean isValid(Context context) {
        return LoginActivity.isLogin(context);
    }

    @Override
    public void process(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), LoginActivity.class);
        fragment.startActivityForResult(intent, getRequestCode());
    }
}
```

## 1.2 Verify interceptor in activity

```java
@InterceptWith(LoginInterceptor.class)
public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ActivityResult result = new ActivityResult(this);
        result.intercept(new OnInterceptResult(this) {

            /**
             * init data or load data from http and so on.
             */
            @SuppressLint("SetTextI18n")
            @Override
            public void invoke() {
                TextView imageView = findViewById(R.id.contentView);
                imageView.setText("This Is the Order Detail Page");
            }
        });
    }
}
```

**Note**: if someone Activity need to verify two or more elements like login state and permission, so the usage is as below:

```java
@InterceptWith({LoginInterceptor.class, PermissionInterceptor.class})
public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activity);

        ActivityResult activityResult = new ActivityResult(this);
        activityResult.intercept(new OnInterceptResult(this) {
            @Override
            public void invoke() {
                TextView textView = findViewById(R.id.contentView);
                textView.setText("This The Admin Manager page");
            }
        });
    }
}
```

## 1.3 launch Activity annotated with Inteceptor

```java
Intent intent = new Intent(context, OrderDetailActivity.class);
startActivity(intent);
```

# 2. startActivityForResult with callback

```java
Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
ActivityResult activityResult = new ActivityResult(this);
activityResult.startActivityForResult(intent, new OnResultCallback() {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            String phoneNum = Util.resolvePhoneNumber(getApplicationContext(), data.getData());
            Toast.makeText(MainActivity.this, "phone number: " + phoneNum, Toast.LENGTH_SHORT).show();
        }
    }
});
```
> ActivityResult has two constructs, one for `Activity` and one for `Fragment`, so you can also startActivityForResult() in Fragment.
