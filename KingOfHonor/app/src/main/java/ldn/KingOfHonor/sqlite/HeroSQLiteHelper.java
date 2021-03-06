package ldn.KingOfHonor.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DecimalFormat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ldn.KingOfHonor.model.Equipment;
import ldn.KingOfHonor.model.Hero;
import ldn.KingOfHonor.model.Inscription;
import ldn.KingOfHonor.model.Skill;

/**
 * @author: LDN
 * @date: 2020/5/18
 */
public class HeroSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HeroDB";
    private static final int DATABASE_VERSION = 1;

    /**
     * @description 有参构造函数
     * @param context : 唯一的入参，
     *                SQLiteOpenHelper(@Nullable Context context, @Nullable String name,
     *             @Nullable CursorFactory factory, int version)，其他参数在类中定义
     * @author LDN
     * @time 2020/5/18 15:08
     */
    public HeroSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @description 需要重写的 public void onCreate(SQLiteDatabase db)
     * @param db : 数据库对象
     * @author LDN
     * @time 2020/5/18 15:33
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        // 英雄个人信息
        String CREATE_HERO_TABLE = "CREATE TABLE heroes ( " +
                "name TEXT PRIMARY KEY, " +
                "image TEXT," +
                "alias TEXT," +
                "category TEXT," +
                "viability INTEGER,"+
                "attack_damage INTEGER,"+
                "skill_damage INTEGER,"+
                "difficulty INTEGER,"+
                "voice TEXT," +
                "icon TEXT," +
                "favorite INTEGER," +
                "skill1_icon TEXT," +
                "skill1_description TEXT," +
                "skill2_icon TEXT," +
                "skill2_description TEXT," +
                "skill3_icon TEXT," +
                "skill3_description TEXT," +
                "skill4_icon TEXT," +
                "skill4_description TEXT," +
                "equip1 TEXT," +
                "equip2 TEXT," +
                "equip3 TEXT," +
                "equip4 TEXT," +
                "equip5 TEXT," +
                "equip6 TEXT)";
        // 装备信息
        String CREATE_EQUIP_TABLE = "create table equipments ("
                + "_id AUTOINC ,"
                + "image integer,"
                + "name text PRIMARY KEY,"
                + "price integer,"
                + "property text,"
                + "skill text,"
                + "process text,"
                + "category text)";

        String CREATE_INSCRIP_TABLE = "create table inscription (" +
                "name TEXT PRIMARY KEY," +
                "type TEXT," +
                "level INTEGER," +
                "image INTEGER," +
                "pro TEXT," +
                "color TEXT)";
        String CREATE_SKILL_TABLE = "create table skills (" +
                "name TEXT PRIMARY KEY," +
                "image INTEGER," +
                "image_detail INTEGER," +
                "detail1 TEXT," +
                "detail2 TEXT)";

        // create books table
        db.execSQL(CREATE_HERO_TABLE);
        db.execSQL(CREATE_EQUIP_TABLE);
        db.execSQL(CREATE_INSCRIP_TABLE);
        db.execSQL(CREATE_SKILL_TABLE);
    }

    /**
     * @description 数据库更新时（oldVersion ！= newVersion），回调该方法
     * @param db ： 数据库对象
     * @param  oldVersion : 旧的版本号
     * @param  newVersion : 新的版本号
     * @author LDN
     * @time 2020/5/18 15:35
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS heroes");
        db.execSQL("DROP TABLE IF EXISTS equipments");
        db.execSQL("DROP TABLE IF EXISTS inscription");
        db.execSQL("DROP TABLE IF EXISTS skill");
        // create fresh books table
        this.onCreate(db);
    }

    //执行查询语句
    public Cursor query(String sql, String[] args)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);
        return cursor;
    }

    // 英雄数据表
    private static final String TABLE_HEROES = "heroes";
    // 英雄数据表的列名
    private static final String KEY_NAME = "name";
    private static final String KEY_ALIAS = "alias";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_CATEGORY = "CATEGORY";
    private static final String KEY_VIABILITY = "viability";
    private static final String KEY_ATTACK_DAMAGE = "attack_damage";
    private static final String KEY_SKILL_DAMAGE = "skill_damage";
    private static final String KEY_DIFFICULTY = "difficulty";
    private static final String KEY_VOICE = "voice";
    private static final String KEY_ICON = "icon";
    private static final String KEY_FAVORITE = "favorite";
    private static final String KEY_SKILL1_ICON = "skill1_icon";
    private static final String KEY_SKILL2_ICON = "skill2_icon";
    private static final String KEY_SKILL3_ICON = "skill3_icon";
    private static final String KEY_SKILL4_ICON = "skill4_icon";
    private static final String KEY_SKILL1_DESCRIPTION = "skill1_description";
    private static final String KEY_SKILL2_DESCRIPTION = "skill2_description";
    private static final String KEY_SKILL3_DESCRIPTION = "skill3_description";
    private static final String KEY_SKILL4_DESCRIPTION = "skill4_description";
    private static final String[] COLUMNS = {KEY_NAME,KEY_ALIAS,KEY_IMAGE,KEY_CATEGORY,KEY_VIABILITY};
    private static final String[] EQUIP_COLUMNS = {"image",
            "name",
            "price",
            "property",
            "skill",
            "process",
            "category"};

    /**
     * @description 向数据库的heroes表插入一个英雄的信息
     * @param hero : 英雄的信息
     * @author LDN
     * @time 2020/5/18 16:02
     */
    public void addHero(Hero hero){
        Log.d("addHero", hero.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, hero.getName());
        values.put(KEY_ALIAS, hero.getAlias());
        values.put(KEY_IMAGE, hero.getImage());
        values.put(KEY_CATEGORY, hero.getCategory());
        values.put(KEY_VIABILITY, hero.getViability());
        values.put(KEY_ATTACK_DAMAGE, hero.getAttack_damage());
        values.put(KEY_SKILL_DAMAGE, hero.getSkill_damage());
        values.put(KEY_DIFFICULTY, hero.getDifficulty());
        values.put(KEY_VOICE, hero.getVoice());
        values.put(KEY_ICON, hero.getIcon());
        values.put(KEY_FAVORITE, hero.getFavorite());
        values.put(KEY_SKILL1_ICON, hero.getSkill1_icon());
        values.put(KEY_SKILL1_DESCRIPTION, hero.getSkill1_description());
        values.put(KEY_SKILL2_ICON, hero.getSkill2_icon());
        values.put(KEY_SKILL2_DESCRIPTION, hero.getSkill2_description());
        values.put(KEY_SKILL3_ICON, hero.getSkill3_icon());
        values.put(KEY_SKILL3_DESCRIPTION, hero.getSkill3_description());
        values.put(KEY_SKILL4_ICON, hero.getSkill4_icon());
        values.put(KEY_SKILL4_DESCRIPTION, hero.getSkill4_description());
        values.put("equip1", hero.getEquip1());
        values.put("equip2", hero.getEquip2());
        values.put("equip3", hero.getEquip3());
        values.put("equip4", hero.getEquip4());
        values.put("equip5", hero.getEquip5());
        values.put("equip6", hero.getEquip6());
        // 3. insert
        db.insert(TABLE_HEROES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        // 4. close
        db.close();
    }

    /**
     * @description 获取数据表中所有的Hero
     * @return 返回一个链表
     * @author LDN
     * @time 2020/5/18 16:17
     */
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new LinkedList<Hero>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_HEROES;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build book and add it to list
        Hero hero = null;
        if (cursor.moveToFirst()) {
            do {
                hero = new Hero();
                hero.setName(cursor.getString(0));
                hero.setImage(cursor.getString(1));
                hero.setAlias(cursor.getString(2));
                hero.setCategory(cursor.getString(3));
                hero.setViability(cursor.getInt(4));
                hero.setAttack_damage(cursor.getInt(5));
                hero.setSkill_damage(cursor.getInt(6));
                hero.setDifficulty(cursor.getInt(7));
                hero.setVoice(cursor.getString(8));
                hero.setIcon(cursor.getString(9));
                hero.setFavorite(cursor.getInt(10) == 1);
                hero.setSkill1_icon(cursor.getString(11));
                hero.setSkill1_description(cursor.getString(12));
                hero.setSkill2_icon(cursor.getString(13));
                hero.setSkill2_description(cursor.getString(14));
                hero.setSkill3_icon(cursor.getString(15));
                hero.setSkill3_description(cursor.getString(16));
                hero.setSkill4_icon(cursor.getString(17));
                hero.setSkill4_description(cursor.getString(18));
                hero.setEquip1(cursor.getString(19));
                hero.setEquip2(cursor.getString(20));
                hero.setEquip3(cursor.getString(21));
                hero.setEquip4(cursor.getString(22));
                hero.setEquip5(cursor.getString(23));
                hero.setEquip6(cursor.getString(24));
                heroes.add(hero);
            } while (cursor.moveToNext());
        }
        // return heros
        return heroes;
    }

    /**
     * @description 删除数据表中所有的英雄
     * @author LDN
     * @time 2020/5/18 16:55
     */
    public void deleteAllHeroes(){
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_HEROES,null,null);
        // 3. close
        db.close();
    }

    /**
     * @description 更新一个英雄的信息
     * @param hero : 新的英雄信息
     * @return 更新的行数目
     * @author LDN
     * @time 2020/5/18 16:55
     */
    public int updateHero(Hero hero) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", hero.getName());
        values.put("image", hero.getImage().toString());
        values.put("alias", hero.getAlias());
        values.put("category", hero.getCategory());
        values.put(KEY_VIABILITY, hero.getViability());
        values.put(KEY_ATTACK_DAMAGE, hero.getAttack_damage());
        values.put(KEY_SKILL_DAMAGE, hero.getSkill_damage());
        values.put(KEY_DIFFICULTY, hero.getDifficulty());
        values.put(KEY_VOICE, hero.getVoice());
        values.put(KEY_ICON, hero.getIcon());
        values.put(KEY_FAVORITE, hero.getFavorite());
        values.put(KEY_SKILL1_ICON, hero.getSkill1_icon());
        values.put(KEY_SKILL1_DESCRIPTION, hero.getSkill1_description());
        values.put(KEY_SKILL2_ICON, hero.getSkill1_icon());
        values.put(KEY_SKILL2_DESCRIPTION, hero.getSkill2_description());
        values.put(KEY_SKILL3_ICON, hero.getSkill1_icon());
        values.put(KEY_SKILL3_DESCRIPTION, hero.getSkill3_description());
        values.put(KEY_SKILL4_ICON, hero.getSkill1_icon());
        values.put(KEY_SKILL4_DESCRIPTION, hero.getSkill4_description());
        values.put("equip1", hero.getEquip1());
        values.put("equip2", hero.getEquip2());
        values.put("equip3", hero.getEquip3());
        values.put("equip4", hero.getEquip4());
        values.put("equip5", hero.getEquip5());
        values.put("equip6", hero.getEquip6());
        // 3. updating row
        int i = db.update(TABLE_HEROES, //table
                values, // column/value
                KEY_NAME+" = ?", // selections
                new String[] { hero.getName() }); //selection args
        // 4. close
        db.close();
        return i;

    }

    /**
     * @description 删除某个英雄
     * @param hero : 被删除的英雄
     * @author LDN
     * @time 2020/5/18 17:02
     */
    public void deleteHero(Hero hero) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_HEROES,
                KEY_NAME+" = ?",
                new String[] { hero.getName() });
        // 3. close
        db.close();
    }

    // 装备数据表
    private static final String EQUIPMENT_TABLE = "equipments";

    /**
     * @description 往数据表equipments插入一件新装备
     * @param equipment ：装备的信息
     * @author LDN
     * @time 2020/5/18 17:07
     */
    public void addEquipment(Equipment equipment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("image", equipment.getImage());
        values.put("name", equipment.getName());
        values.put("price", equipment.getPrice());
        values.put("property", equipment.getProperty());
        values.put("skill", equipment.getSkill());
        values.put("process", equipment.getProcess());
        values.put("category", equipment.getCategory());
        db.insert(EQUIPMENT_TABLE, null, values);
        values.clear();
        db.close();
    }

    /**
     * @description 获取所有的装备
     * @return 返回一个ArrayList<Equipment>
     * @author LDN
     * @time 2020/5/25 15:59
     */
    public ArrayList<Equipment> getAllEquipment(){
        ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from equipments", null);
        if (cursor.moveToFirst()){
            do{
                Equipment equipment = new Equipment();
                equipment.setImage(cursor.getInt(cursor.getColumnIndex("image")));
                equipment.setName(cursor.getString(cursor.getColumnIndex("name")));
                equipment.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                equipment.setProperty(cursor.getString(cursor.getColumnIndex("property")));
                equipment.setSkill(cursor.getString(cursor.getColumnIndex("skill")));
                equipment.setProcess(cursor.getString(cursor.getColumnIndex("process")));
                equipment.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                equipmentList.add(equipment);
            }while(cursor.moveToNext());
        }
        return equipmentList;
    }

    /**
     * @description 删除所有的装备
     * @author LDN
     * @time 2020/5/25 16:20
     */
    public void deleteAllEquipments(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("equipments", null, null);
        db.close();
    }

    /**
     * @description 获取某类的所有装备
     * @param categoryId:类别id
     * @return ArrayList<Equipment>
     * @author LDN
     * @time 2020/5/31 8:44
     */
    public ArrayList<Equipment> getEquipmentsWithCategory(String categoryId){
        ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from equipments where category = ?", new String[]{categoryId});
        if(cursor.moveToFirst()){
            do{
                Equipment equipment = new Equipment();
                equipment.setImage(cursor.getInt(cursor.getColumnIndex("image")));
                equipment.setName(cursor.getString(cursor.getColumnIndex("name")));
                equipment.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                equipment.setProperty(cursor.getString(cursor.getColumnIndex("property")));
                equipment.setSkill(cursor.getString(cursor.getColumnIndex("skill")));
                equipment.setProcess(cursor.getString(cursor.getColumnIndex("process")));
                equipment.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                equipmentArrayList.add(equipment);
            }while (cursor.moveToNext());
        }
        return equipmentArrayList;
    }

    /**
     * @description 获取指定名字的装备
     * @param name:装备的名字
     * @return Equipment
     * @author LDN
     * @time 2020/5/31 8:46
     */
    public Equipment getEquipmentsWithName(String name){
        Equipment equipment = new Equipment();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from equipments where name = ?", new String[]{name});
        if(cursor.moveToFirst()){
            equipment.setImage(cursor.getInt(cursor.getColumnIndex("image")));
            equipment.setName(cursor.getString(cursor.getColumnIndex("name")));
            equipment.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            equipment.setProperty(cursor.getString(cursor.getColumnIndex("property")));
            equipment.setSkill(cursor.getString(cursor.getColumnIndex("skill")));
            equipment.setProcess(cursor.getString(cursor.getColumnIndex("process")));
            equipment.setCategory(cursor.getString(cursor.getColumnIndex("category")));
        }
        return equipment;
    }

    // 铭文数据表
    private static final String INSCRIPTION_TABLE = "inscription";

    /**
     * @description 插入新的铭文
     * @param inscription：铭文
     * @author LDN
     * @time 2020/5/31 8:59
     */
    public void addInscription(Inscription inscription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", inscription.getName());
        values.put("type", inscription.getType());
        values.put("image", inscription.getImage());
        values.put("level", inscription.getLevel());
        String proString = "";
        DecimalFormat df = new DecimalFormat("0.00");
        int i = 0;
        for (Map.Entry<String, Double> entry:inscription.getProperty().entrySet()) {
            if(i != 0) {
                proString += ", ";
            }
            proString += entry.getKey() + ": " + df.format(entry.getValue());
            i++;
        }
        values.put("pro", proString);
        values.put("color", inscription.getColor());
        db.insert(INSCRIPTION_TABLE, null, values);
        values.clear();
        db.close();
    }

    /**
     * @description 获取所有的铭文
     * @return ArrayList<Inscription>
     * @author LDN
     * @time 2020/5/31 9:05
     */
    public ArrayList<Inscription> getAllInscription(){
        ArrayList<Inscription> inscriptionList = new ArrayList<Inscription>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from inscription", null);
        if (cursor.moveToFirst()){
            do{
                Inscription inscription = new Inscription(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("type")), cursor.getInt(cursor.getColumnIndex("image")), cursor.getInt(cursor.getColumnIndex("level")), cursor.getString(cursor.getColumnIndex("color")));
                String proString = cursor.getString(cursor.getColumnIndex("pro"));
                String[] proArray = proString.split(", ");
                for(int i = 0; i < proArray.length; i++) {
                    String[] eachPro = proArray[i].split(": ");
                    inscription.addProperty(eachPro[0], Double.valueOf(eachPro[1]));
                }
                inscriptionList.add(inscription);
            }while(cursor.moveToNext());
        }
        return inscriptionList;
    }

    /**
     * @description 删除所有的铭文
     * @author LDN
     * @time 2020/5/31 9:07
     */
    public void deleteAllInscription(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("inscription", null, null);
        db.close();
    }

    // 技能数据表
    private static final String SKILL_TABLE = "skills";

    /**
     * @description 插入技能
     * @param skill：要插入的技能
     * @author LDN
     * @time 2020/5/31 9:18
     */
    public void addSkill(Skill skill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("image", skill.getImage());
        values.put("name", skill.getName());
        values.put("image_detail", skill.getImage_detail());
        values.put("detail1", skill.getDetail1());
        values.put("detail2", skill.getDetail2());
        db.insert(SKILL_TABLE, null, values);
        values.clear();
        db.close();
    }

    /**
     * @description 获取所有的技能
     * @return ArrayList<Skill>
     * @author LDN
     * @time 2020/5/31 9:21
     */
    public ArrayList<Skill> getAllSkill(){
        ArrayList<Skill> skillList = new ArrayList<Skill>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from skills", null);
        if (cursor.moveToFirst()){
            do{
                Skill skill = new Skill();
                skill.setImage(cursor.getInt(cursor.getColumnIndex("image")));
                skill.setName(cursor.getString(cursor.getColumnIndex("name")));
                skill.setImage_detail(cursor.getInt(cursor.getColumnIndex("image_detail")));
                skill.setDetail1(cursor.getString(cursor.getColumnIndex("detail1")));
                skill.setDetail2(cursor.getString(cursor.getColumnIndex("detail2")));
                skillList.add(skill);
            }while(cursor.moveToNext());
        }
        return skillList;
    }

    /**
     * @description 删除所有的技能
     * @author LDN
     * @time 2020/5/31 9:21
     */
    public void deleteAllSkills(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("skills", null, null);
        db.close();
    }




}
