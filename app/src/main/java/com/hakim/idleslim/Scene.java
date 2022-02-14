package com.hakim.idleslim;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Point;
        import android.os.Bundle;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ImageView;

        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.firestore.FirebaseFirestore;

public class Scene extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    public Button logout;

    public GoogleSignInClient mGoogleSignInClient;
    public FirebaseFirestore fStore;
    public FirebaseAuth mAuth;
    public String userID;

    public ImageView stageForestImage;
    public ImageView menu1;

    //game
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // facebook + google auth
        logout = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

//        int displayWidth = getWindowManager().getDefaultDisplay().getHeight();
//        stageForestImage = (ImageView)findViewById(R.id.stageForest);
//        stageForestImage.getLayoutParams().height = displayWidth / 3;
//
//        menu1 = (ImageView)findViewById(R.id.menu1);
//        menu1.getLayoutParams().height = (displayWidth / 3) *2;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);

        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(this, gso);
        googleSignInClient.signOut();
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
