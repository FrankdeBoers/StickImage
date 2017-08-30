package ghc.stickview710;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ghc.stickview710.stickerview.StickerView;


public class StickerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView cleanTv, nextTv;
    StickerView stickerView;
    int icons[] = new int[]{
            R.drawable.icon_0, R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3
            , R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7};

    public static final String STICKER_UP = "StickerViewActivity.position";

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticker_view_main);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.GONE);
        cleanTv = (TextView) findViewById(R.id.cleanTv);
        nextTv = (TextView) findViewById(R.id.nextTv);
//        stickerView = new StickerView(context);
        stickerView = (StickerView) findViewById(R.id.sticker_View_layout);
        stickerView.setMinStickerSizeScale(0.9f);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        int stickerFromUp = getIntent().getIntExtra(STICKER_UP, 0);
        stickerView.addSticker(icons[stickerFromUp]);

        // 上方的 CLEAN 点击事件，清除所有的 贴纸
        cleanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stickerView.clearSticker();
            }
        });

        // 保存事件
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*BitmapUtil.FINAL_BITMAP = */
                String savepath = stickerView.saveSticker();
                Log.d("StickerViewActivity", "savepath: " + savepath);
                scanFile(savepath);

                Toast.makeText(StickerViewActivity.this, "save success.", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(StickerViewActivity.this, PictureActivity.class);
                startActivity(intent);*/
            }
        });
    }


    /**
     * 扫描文件
     *
     * @param path
     */
    private void scanFile(String path) {
        MediaScannerConnection.scanFile(this, new String[]{path},
                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.e("TAG", "onScanCompleted");
                    }
                });
    }
}
