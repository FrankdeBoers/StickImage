package ghc.stickview710;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ghc.stickview710.stickerview.StickerView;


public class StickerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView cleanTv, nextTv;
    StickerView stickerView;
    //icon link http://www.easyicon.net/iconsearch/iconset:Landscapes-icons/
    int icons[] = new int[]{
            R.drawable.icon_0, R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3
            , R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticker_view_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.GONE);
        cleanTv = (TextView) findViewById(R.id.cleanTv);
        nextTv = (TextView) findViewById(R.id.nextTv);
        stickerView = (StickerView) findViewById(R.id.sticker_View_layout);
        stickerView.setMinStickerSizeScale(0.9f);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        ImageAdapter imageAdapter = new ImageAdapter();
//        recyclerView.setAdapter(imageAdapter);

        stickerView.addSticker(icons[0]);

        /*//给下方的 sticker 选择框设置点击事件，可以选择多个
        imageAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                stickerView.addSticker(icons[position]);
            }
        });*/

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
                BitmapUtil.FINAL_BITMAP = stickerView.saveSticker();
                Intent intent = new Intent(StickerViewActivity.this, PictureActivity.class);
                startActivity(intent);
            }
        });
    }

    /*public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements View.OnClickListener {

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.mOnItemClickListener = listener;
        }

        public ImageAdapter() {
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            view.setOnClickListener(this);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.imageView.setImageResource(icons[position]);
            viewHolder.itemView.setTag(position);
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return icons.length;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (int) v.getTag());
            }
        }


        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
            }
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }*/
}
