package com.example.myapplication.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Spell.class, Class.class, Relations.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "dndSpells.db";

    public abstract SpellDao spellDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME)
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            db.execSQL("INSERT INTO 'spells' (spell_name,spell_level,spell_nature,spell_during_time,spell_distance,spell_material,spell_read_time,spell_desc) VALUES (\n" +
                                    "  'АДСКОЕ ВОЗМЕЗДИЕ',\n" +
                                    "  '1',\n" +
                                    "  'воплощение',\n" +
                                    "  'Мгновенная',\n" +
                                    "  '60 футов',\n" +
                                    "  'В, С',\n" +
                                    "  '1 реакция, совершаемая вами в ответ на получение урона от существа, находящегося в пределах 60 футов от вас и видимого вами',\n" +
                                    "  'Вы указываете пальцем, и существо, причинившеевам урон, мгновенно окружается пламенем. Существо должно совершить спасбросок Ловкости. Оно получает урон огнём 2к10 при провале, или половину этого урона при успехе. На больших уровнях: Если вы накладываете это заклинание, используя ячейку 2 уровня или выше, урон увеличивается на 1к10 за каждый уровень ячейки выше первого.'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'spells' (spell_name,spell_level,spell_nature,spell_during_time,spell_distance,spell_material,spell_read_time,spell_desc) VALUES (\n" +
                                    "  'АНТИПАТИЯ/СИМПАТИЯ',\n" +
                                    "  '8',\n" +
                                    "  'очарование',\n" +
                                    "  '10 дней',\n" +
                                    "  '60 футов',\n" +
                                    "  'В, С, М (кусок квасцов, пропитанный уксусом для эффекта антипатии или капля мёда для эффекта симпатии)',\n" +
                                    "  '1 час',\n" +
                                    "  'Это заклинание привлекает или отталкивает выбранных вами существ. Вы выбираете цель в пределах дистанции: либо предмет размером не больше Огромного, либо существо, либо область не больше куба с длиной ребра 200 футов. Затем укажите вид разумных существ, такой как красные драконы, гоблины или вампиры. Вы наделяете цель аурой, которая в течение длительности либо привлекает, либо отталкивает указанных существ. Выберите либо эффект антипатии, либо эффект симпатии: Антипатия. Эти чары вызывают у выбранных вами существ непреодолимое желание покинуть область и избегать цель. Если такое существо видит цель и оказывается в пределах 60 футов от неё, оно должно преуспеть в спасброске Мудрости, иначе станет испуганным. Существо остаётся испуганным, пока видит цель или находится в пределах 60 футов от неё. Будучи испуганным целью, существо должно тратить своё перемещение на побег в ближайшее безопасное место, откуда оно уже не увидит цель. Если существо отдалится от цели более чем на 60 футов и не сможет её видеть, оно перестаёт быть испуганным, но становится испуганным вновь, если увидит цель или окажется в пределах 60 футов от неё. Симпатия. Эти чары вызывают у выбранных вами существ непреодолимое желание приблизиться к цели, если они находятся в пределах 60 футов от неё или видят её. Если такое существо видит цель или оказывается в пределах 60 футов от неё, оно должно преуспеть в спасброске Мудрости, иначе оно в каждом своём ходу тратит перемещение на то, чтобы войти в область или прикоснуться к цели. Сделав это, оно уже не может добровольно отойти от цели. Если цель причиняет урон или как-то иначе вредит указанному существу, существо может совершить спасбросок Мудрости для окончания эффекта, как описано ниже. Окончание эффекта. Если указанное существо окончит ход за пределами 60 футов от цели или не сможет её видеть, оно совершает спасбросок Мудрости. В случае успеха эффект на существе заканчивается и оно понимает, что тяга или отвращение были вызваны магией. Кроме того, попавшему под эффект заклинания существу позволяется спасбросок Мудрости каждые 24 часа, пока заклинание активно. Совершившее успешный спасбросок существо получает иммунитет к эффекту на 1 минуту, после чего снова попадает под его действие.'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'spells' (spell_name,spell_level,spell_nature,spell_during_time,spell_distance,spell_material,spell_read_time,spell_desc) VALUES (\n" +
                                    "  'АУРА ЖИВУЧЕСТИ',\n" +
                                    "  '8',\n" +
                                    "  'воплощение',\n" +
                                    "  'Концентрация, вплоть до 1 минуты ',\n" +
                                    "  '60 футов',\n" +
                                    "  'В ',\n" +
                                    "  '1 действие',\n" +
                                    "  'От вас исходит аура живительной энергии с радиусом 30 футов. Пока заклинание активно, аура перемещается вместе с вами, оставаясь с центром на вас. Вы можете бонусным действием восстанавливать одному любому существу в ауре (включая себя) 2к6 хитов.'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Бард' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Волшебник' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Друид' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Жрец' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Колдун' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Паладин' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Следопыт' " +
                                    ");");
                            db.execSQL("INSERT INTO 'classes' (class_name) VALUES (\n" +
                                    "  'Чародей' " +
                                    ");");
                            db.execSQL("INSERT INTO 'relations' (class_relation_id,spell_relation_id,class_level) VALUES (\n" +
                                    "  '1',\n" +
                                    "  '1',\n" +
                                    "  '1'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'relations' (class_relation_id,spell_relation_id,class_level) VALUES (\n" +
                                    "  '1',\n" +
                                    "  '2',\n" +
                                    "  '1'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'relations' (class_relation_id,spell_relation_id,class_level) VALUES (\n" +
                                    "  '1',\n" +
                                    "  '3',\n" +
                                    "  '1'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'relations' (class_relation_id,spell_relation_id,class_level) VALUES (\n" +
                                    "  '1',\n" +
                                    "  '2',\n" +
                                    "  '2'\n" +
                                    ");");
                            db.execSQL("INSERT INTO 'relations' (class_relation_id,spell_relation_id,class_level) VALUES (\n" +
                                    "  '2',\n" +
                                    "  '2',\n" +
                                    "  '1'\n" +
                                    ");");
                        }
                    })
                    .build();
        }
        return instance;
    }
}
