package com.example.libraryapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<BookInfo> bookInfoArrayList;
    private Context mcontext;
    private SharedPreferences preferences;

    NotificationManager notificationManager;

    public BookAdapter(ArrayList<BookInfo> bookInfoArrayList, Context mcontext) {
        this.bookInfoArrayList = bookInfoArrayList;
        this.mcontext = mcontext;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item, parent, false);

        notificationManager =
                (NotificationManager)
                        mcontext.getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel(
                "com.ebookfrenzy.notifydemo.news",
                "NotifyDemo News",
                "Example News Channel");
        return new BookViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void createNotificationChannel(String id, String name, String description) {

        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel = new NotificationChannel(id, name, importance);

        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(
                new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(channel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void sendNotification(View view,String veri) {

        int notificationID = 101;

        String channelID = "com.ebookfrenzy.notifydemo.news";

        Notification notification =
                new Notification.Builder(mcontext,
                        channelID)
                        .setContentTitle("Favori Kitaplara Eklendi")
                        .setContentText(veri + " adlı kitap favorilerinize eklendi")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setChannelId(channelID)
                        .build();

        notificationManager.notify(notificationID, notification);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoArrayList.get(position);
        holder.nameTV.setText(bookInfo.getTitle());
        holder.publisherTV.setText(bookInfo.getPublisher());
        holder.pageCountTV.setText("Sayfa Sayısı : " + bookInfo.getPageCount());
        holder.dateTV.setText(bookInfo.getPublishedDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, BookDetailsActivity.class);
                i.putExtra("title", bookInfo.getTitle());
                i.putExtra("subtitle", bookInfo.getSubtitle());
                i.putExtra("authors", bookInfo.getAuthors());
                i.putExtra("publisher", bookInfo.getPublisher());
                i.putExtra("publishedDate", bookInfo.getPublishedDate());
                i.putExtra("description", bookInfo.getDescription());
                i.putExtra("pageCount", bookInfo.getPageCount());

                mcontext.startActivity(i);
            }
        });

        preferences = mcontext.getSharedPreferences("com.example.libraryapp", Context.MODE_PRIVATE);
        String data = preferences.getString("kullaniciAd", "Bilinmiyor");

        holder.favoriteBTN.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                SQLite sqlLite = new SQLite(mcontext.getApplicationContext());

                if (sqlLite.KitapKayitliMi(bookInfo.getTitle(), data)==0)
                {
                    sqlLite.KitapEkle(bookInfo.getTitle(), data);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                        sendNotification(view,bookInfo.getTitle());
                }
                else
                    Toast.makeText(mcontext.getApplicationContext(), "Kitap zaten favorilerinizde kayıtlı", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return bookInfoArrayList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, publisherTV, pageCountTV, dateTV;
        ImageButton favoriteBTN;

        public BookViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVBookTitle);
            publisherTV = itemView.findViewById(R.id.idTVpublisher);
            pageCountTV = itemView.findViewById(R.id.idTVPageCount);
            dateTV = itemView.findViewById(R.id.idTVDate);
            favoriteBTN = itemView.findViewById(R.id.idBtnFavorite);
        }
    }
}
