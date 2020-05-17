{"objectClass":"NSDictionary","root":{"objectClass":"MindNode","ID":"4405S","rootPoint":{"objectClass":"CGPoint","x":360,"y":1151.5},"lineColorHex":"#BBBBBB","children":{"0":{"objectClass":"MindNode","ID":"Y1S31","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"5P58F","lineColorHex":"#DC306C","text":"启动：startService()和bindService()"},"1":{"objectClass":"MindNode","ID":"6P728","lineColorHex":"#DC306C","text":"停止：stopService"},"2":{"objectClass":"MindNode","ID":"3Y4V5","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"782X4","lineColorHex":"#DC306C","text":"onCreate()"},"1":{"objectClass":"MindNode","ID":"6I201","lineColorHex":"#DC306C","text":"onStartCommand()"},"2":{"objectClass":"MindNode","ID":"HY81V","lineColorHex":"#DC306C","text":"onBind()"},"3":{"objectClass":"MindNode","ID":"5UL5J","lineColorHex":"#DC306C","text":"onUnbind()"},"4":{"objectClass":"MindNode","ID":"B1SK6","lineColorHex":"#DC306C","text":"onDestroy()"},"5":{"objectClass":"MindNode","ID":"DI2ND","lineColorHex":"#DC306C","text":"onRebind()"},"objectClass":"NSArray"},"text":"生命周期"},"3":{"objectClass":"MindNode","ID":"04QYD","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"72X8G","lineColorHex":"#DC306C","text":"onServiceConnected()"},"1":{"objectClass":"MindNode","ID":"8VZ2P","lineColorHex":"#DC306C","text":"onServiceDisconnected()"},"objectClass":"NSArray"},"text":"ServiceConnection"},"4":{"objectClass":"MindNode","ID":"EEFDA","lineColorHex":"#DC306C","text":"Ibinder对象"},"5":{"objectClass":"MindNode","ID":"76858","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"5N8S0","lineColorHex":"#DC306C","text":"onHandleIntent()"},"objectClass":"NSArray"},"text":"IntentService：耗时操作"},"6":{"objectClass":"MindNode","ID":"704DQ","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"72983","lineColorHex":"#DC306C","text":"创建AIDL文件"},"1":{"objectClass":"MindNode","ID":"NV1L1","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"1T4U3","lineColorHex":"#DC306C","text":"继承XXX.Stub，实现方法"},"1":{"objectClass":"MindNode","ID":"4HZJF","lineColorHex":"#DC306C","text":"onBind()返回IBinder对象"},"objectClass":"NSArray"},"text":"Service实现类"},"2":{"objectClass":"MindNode","ID":"28812","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"F606C","lineColorHex":"#DC306C","text":"创建ServiceConnection对象"},"1":{"objectClass":"MindNode","ID":"PI877","lineColorHex":"#DC306C","text":"注意ServiceConnection只是获取Service的onBind()方法的对象的代理，需要XXX.Stub.asInterface(service)进行处理"},"objectClass":"NSArray"},"text":"客户端访问"},"3":{"objectClass":"MindNode","ID":"2CK2T","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"KARN3","lineColorHex":"#DC306C","text":"AIDL定义"},"1":{"objectClass":"MindNode","ID":"05X84","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"43X3W","lineColorHex":"#DC306C","text":"int describeContents()"},"1":{"objectClass":"MindNode","ID":"F07L6","lineColorHex":"#DC306C","text":"void writeToParcel()"},"objectClass":"NSArray"},"text":"实现Parcelable接口方法"},"2":{"objectClass":"MindNode","ID":"K26PV","lineColorHex":"#DC306C","text":"添加静态常量，名为CREATOR，类型为Parcelable.Creator，实现里面的createFromParcel()方法"},"objectClass":"NSArray"},"text":"自定义数据类型，需要实现Parcelable接口"},"objectClass":"NSArray"},"text":"AIDL Service"},"7":{"objectClass":"MindNode","ID":"HGQ37","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"UHC14","lineColorHex":"#DC306C","text":"获取网络和SIM卡信息"},"1":{"objectClass":"MindNode","ID":"4F57P","lineColorHex":"#DC306C","text":"监听手机来电"},"objectClass":"NSArray"},"text":"电话管理器TelephonyManager"},"8":{"objectClass":"MindNode","ID":"7Q7GG","lineColorHex":"#DC306C","children":{"0":{"objectClass":"MindNode","ID":"97H7O","lineColorHex":"#DC306C","text":"发送信息"},"1":{"objectClass":"MindNode","ID":"474ZY","lineColorHex":"#DC306C","text":"群发信息"},"objectClass":"NSArray"},"text":"短信管理器SmsManager"},"9":{"objectClass":"MindNode","ID":"RC8EZ","lineColorHex":"#DC306C","text":"音频管理器AudioManager"},"10":{"objectClass":"MindNode","ID":"72A9D","lineColorHex":"#DC306C","text":"振动器Vibrator"},"11":{"objectClass":"MindNode","ID":"53380","lineColorHex":"#DC306C","text":"手机闹钟服务AlarmManager"},"objectClass":"NSArray"},"text":"Service"},"1":{"objectClass":"MindNode","ID":"71E33","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"PS6QZ","lineColorHex":"#BF58F5","text":"重写onReceive()"},"1":{"objectClass":"MindNode","ID":"FYWW8","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"6ZD72","lineColorHex":"#BF58F5","text":"在代码中注册：registerReceiver()"},"1":{"objectClass":"MindNode","ID":"PYO33","lineColorHex":"#BF58F5","text":"在xml中注册：<register>、<intent-filter>"},"objectClass":"NSArray"},"text":"注册BroadcastReceiver"},"2":{"objectClass":"MindNode","ID":"MU40N","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"61ZGE","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"87UBK","lineColorHex":"#BF58F5","text":"效率高"},"1":{"objectClass":"MindNode","ID":"9RV0R","lineColorHex":"#BF58F5","text":"无法终止Broadcast Intent的传播"},"objectClass":"NSArray"},"text":"普通广播 sendBroadcast()"},"1":{"objectClass":"MindNode","ID":"9106X","lineColorHex":"#BF58F5","children":{"0":{"objectClass":"MindNode","ID":"8773Z","lineColorHex":"#BF58F5","text":"接受者按优先级依次接收"},"1":{"objectClass":"MindNode","ID":"8Y3H1","lineColorHex":"#BF58F5","text":"前面的接受者可以中止Broadcast Intent的传播，也可以在Broadcast中加内容"},"objectClass":"NSArray"},"text":"有序广播 sendOrderedBroadcast()"},"objectClass":"NSArray"},"text":"广播分类"},"objectClass":"NSArray"},"text":"BroadcastReceiver\n（与普通监听器的区别？系统级）"},"objectClass":"NSArray"},"text":"Service和BroadcastReceiver"}}