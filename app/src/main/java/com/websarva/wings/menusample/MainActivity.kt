package com.websarva.wings.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var _menuList : MutableList<MutableMap<String, Any>>? = null

    private val FROM = arrayOf("name" , "price")

    private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()

        val lvMenu = findViewById<ListView>(R.id.lvMenu)

        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)


        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()

        registerForContextMenu(lvMenu)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        //定食メニューリスト用のListオブジェクトを用意。
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        //「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        var menu = mutableMapOf("name" to "から揚げ定食", "price" to 800, "desc" to "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        //「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        //以下データ登録の繰り返し。
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to 850, "desc" to "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to 1000, "desc" to "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to 750, "desc" to "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to 900, "desc" to "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to 850, "desc" to "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to 900, "desc" to "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to 850, "desc" to "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to 850, "desc" to "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to 950, "desc" to "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        return menuList
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View?, positon: Int, id: Long) {
            val item = parent.getItemAtPosition(positon) as MutableMap<String,Any>

            order(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun createCurryList(): MutableList<MutableMap<String,Any>> {

        val menuList: MutableList<MutableMap<String,Any>> = mutableListOf()

        var menu = mutableMapOf<String,Any>("name" to "ビーフカレー", "price" to 520, "desc" to
        "特選スパイスを効かせた国産ピーフ100％のカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String,Any>("name" to "ポークカレー", "price" to 600 , "desc" to
                "国産豚肉だけを使用した絶品のカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String,Any>("name" to "キーマカレー", "price" to 600 , "desc" to
                "国産羊肉だけを使用した絶品のカレーです")
        menuList.add(menu)
        menu = mutableMapOf<String,Any>("name" to "チキンカレー", "price" to 600 , "desc" to
                "国産鶏肉だけを使用した絶品のカレーです")
        menuList.add(menu)
        return menuList
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menuListOptionTeishoku ->
                _menuList = createTeishokuList()
            R.id.menuListOptionCurry ->
                _menuList = createCurryList()
        }

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapterView = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapterView

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        menu?.setHeaderTitle(R.string.menu_list_context_header)
    }

    private fun order(menu: MutableMap<String,Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int

        val intent = Intent(applicationContext, MenuThanksActivity::class.java)

        intent.putExtra("menuName" , menuName)
        intent.putExtra("price" , "${menuPrice}円")

        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

        val listPosition = info.position

        val menu = _menuList!![listPosition]

        when(item.itemId){

            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(applicationContext, desc, Toast.LENGTH_LONG).show()
            }

            R.id.menuListContextOrder ->
                order(menu)
        }

        return super.onContextItemSelected(item)
    }

}