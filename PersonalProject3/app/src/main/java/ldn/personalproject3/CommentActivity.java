package ldn.personalproject3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {

    private String username;
    private String image;
    private CommentAdapter commentAdapter;
    private ListView listView;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private CommentInfo click_comment;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
    private String show_number = "\nPhone number not existed.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        username = getIntent().getStringExtra("username");
        image = getIntent().getStringExtra("image");
        commentAdapter = new CommentAdapter(this, username);

        listView = findViewById(R.id.comment_list);
        listView.setAdapter(commentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //动态申请权限
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
                click_comment = commentAdapter.getItem(position);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if(commentAdapter.getItem(position).getUsername().equals(username)){
                    new AlertDialog.Builder(CommentActivity.this).setTitle("Delete or not").setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(getApplicationContext(),"对话框“确定”按钮被点击",Toast.LENGTH_SHORT).show();
                                    commentAdapter.removeItem(position);
                                }
                            }).setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(getApplicationContext(),"对话框“取消”按钮被点击",Toast.LENGTH_SHORT).show();
                                }
                            }).create().show();
                }else{
                    new AlertDialog.Builder(CommentActivity.this).setTitle("Report or not").setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Report Successfully.",Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(getApplicationContext(),"对话框“取消”按钮被点击",Toast.LENGTH_SHORT).show();
                                }
                            }).create().show();
                }
                //去除重复的单击事件
                return true;
            }
        });

        final EditText editText = findViewById(R.id.comment_edit_text);
        editText.setHint(username);
        Button button = findViewById(R.id.comment_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(CommentActivity.this, "Comment is empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                commentAdapter.addItem(new CommentInfo(formatter.format(new Date(System.currentTimeMillis())), username, editText.getText().toString(),image,new ArrayList<String>()));
                //清空发送框中的内容
                editText.setText("");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                //得到手机号码
                show_number = "\nPhone number not existed.";
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = \"" + click_comment.getUsername() + "\"", null, null);
                if(cursor!= null && cursor.moveToFirst()){
                    show_number = "\nPhone: ";
                    do {
                        show_number += cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "         ";
                    } while (cursor.moveToNext());
                }
                if(cursor != null)
                    cursor.close();

                new AlertDialog.Builder(CommentActivity.this).setTitle("Info").setMessage("Username: " + click_comment.getUsername() + show_number).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getApplicationContext(),"对话框“确定”按钮被点击",Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
