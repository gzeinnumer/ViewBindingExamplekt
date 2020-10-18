# ViewBindingExampleKT

- [View Binding](https://developer.android.com/topic/libraries/view-binding?hl=id)

**Important** enable `ViewBinding` on your project, setup in `gradle`.

```gradle
android {

    ...
    
    //Android Studio Version Until 4
    viewBinding {
        enabled = true
    }
    
    //Android Studio Version 4 -> gradle version 6.1.1 -> android gradle plugin version 4.0.0
    buildFeatures{
        viewBinding = true
    }

}
```

#
#### ViewBinding On Activity

> activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:text="Hallo Zein"
        android:id="@+id/my_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</LinearLayout>
```

> MainActivity.java

```kotlin
public class MainActivity extends AppCompatActivity implements AdapterRVMultiType.MyOnClick {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        
        binding.myTextView = "Hallo Zein"
    }

   override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
```
[FullCode](https://github.com/gzeinnumer/ViewBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/viewbindingexamplekt/MainActivity.kt)

#
#### ViewBinding On Fragment

> fragment_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:text="Hallo Zein"
        android:id="@+id/my_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</FrameLayout>
```

> MainFragment.java

```kotlin
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? =null
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
```
[FullCode](https://github.com/gzeinnumer/ViewBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/viewbindingexamplekt/MainFragment.kt)

#
#### ViewBinding On AdapterRecyclerView (Single Type)

> item_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:text="Hallo Zein"
        android:id="@+id/my_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</LinearLayout>
```

> AdapterRV.java

```kotlin
class AdapterRV : RecyclerView.Adapter<AdapterRV.MyHolder>() {
    
    ...

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    
    class MyHolder(itemView: ItemAdapterRvBinding) : ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(
            data: String,
            onClick: MyOnClick?
        ) {
            binding.myTextView.text = data
            if (onClick != null) {
                binding.myTextView.setOnClickListener(View.OnClickListener {
                    onClick.myOnClick(
                        adapterPosition
                    )
                })
            }
        }

    }
}
```
[FullCode](https://github.com/gzeinnumer/ViewBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/viewbindingexamplekt/AdapterRV.kt)

#
#### ViewBinding On AdapterRecyclerView (Multi Type)

> item_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:text="Hallo Zein"
        android:id="@+id/my_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</LinearLayout>
```

> AdapterRV.java

```kotlin
public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyHolder> {
    
    ...
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == TYPE_NORMAL) {
            return MyHolder(
                ItemAdapterRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        } else {
            return MyHolder(
                ItemAdapterRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    private val TYPE_NORMAL = 1

    override fun getItemViewType(position: Int): Int {
        return if (position != -1) {
            TYPE_NORMAL
        } else {
            0
        }
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(data: String, onClick: MyOnClick?) {
            binding.myTextView.text = data
            if (onClick != null) {
                binding.myTextView.setOnClickListener(View.OnClickListener {
                    onClick.myOnClick(
                        adapterPosition
                    )
                })
            }
        }
    }
}
```
[FullCode](https://github.com/gzeinnumer/ViewBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/viewbindingexamplekt/AdapterRVMultiType.kt)

#
#### ViewBinding On DialogFragment

> fragment_main_dialog.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:text="Hallo Zein"
        android:id="@+id/my_text_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</LinearLayout>
```

> MainDialog.java

```koltin
class MainDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainDialogBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)        
        binding.myTextView.text = "Hallo GZeinNumer"
    }
    

   
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
```
[FullCode](https://github.com/gzeinnumer/ViewBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/viewbindingexamplekt/MainDialog.kt)

---

```
Copyright 2020 M. Fadli Zein
```
