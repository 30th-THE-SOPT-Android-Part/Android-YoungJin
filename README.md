# Android-YoungJin
<!-- week4 작성 시 토글로 정리 예정 -->
<!-- <details>
<summary>week3</summary> -->

# Week3
<br>

- [X] LEVEL 3

EditText 디자인 적용|Bottom Navigation 적용|ViewPager2, TabLayout 적용|Gallery 접근|
---|---|---|---|
|<img src="https://user-images.githubusercontent.com/48701368/166404072-13f265e2-f07b-400b-a7fc-75bfcb1ab174.gif" width="250">|<img src="https://user-images.githubusercontent.com/48701368/166404061-3e7f8e2f-4f79-435f-9773-e42e2cc9b1a7.gif" width="250">|<img src="https://user-images.githubusercontent.com/48701368/166404076-c82abfdd-a7af-49a9-bc02-480c04a36812.gif" width="250">|<img src="https://user-images.githubusercontent.com/48701368/166404149-51ef9603-ccd8-4530-b6be-2dca6d4d064a.gif" width="250">

<br>

# 로그인 화면에서 포커스 여부에 따른 EditText 디자인 적용

`selector_edittext_background.xml`
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_focused="true">
        <shape android:shape="rectangle">
            <stroke android:width="1dp" android:color="@color/gray_150" />
            <corners android:radius="@dimen/radiusEditText" />
        </shape>
    </item>
    <item>
        <shape android:shape="rectangle">
            <solid android:color="@color/gray_100" />
            <corners android:radius="@dimen/radiusEditText" />
        </shape>
    </item>
</selector>
```
<br>

# Bottom Navigation 적용

`bottom_nav_menu.xml`
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/profile_nav_graph"
        android:icon="@drawable/ic_profile"
        android:title="@string/tab_profile" />
    <item
        android:id="@+id/github_nav_graph"
        android:icon="@drawable/ic_github"
        android:title="@string/tab_github" />
    <item
        android:id="@+id/gallery_fragment"
        android:icon="@drawable/ic_camera"
        android:title="@string/tab_camera" />
</menu>
```

`activity_main.xml`
```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
            android:id="@+id/nav_main"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@color/white"
            android:theme="@style/TextAppearance.BottomNavigation.Tab.Text"
            app:itemIconTint="@color/selector_bottom_navi"
            app:itemRippleColor="@color/gray_300"
            app:itemTextColor="@color/selector_bottom_navi"
            app:layout_constraintBottom_toBottomOf="parent"
            app:menu="@menu/bottom_nav" />
```

`MainActivity.kt`
**Navigation** 을 사용해서 프래그먼트 전환 처리

```kotlin
private fun initLayout() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        binding.navMain.setupWithNavController(navHostFragment.navController)
    }
```

<br>

# ViewPager2 및 TabLayout적용
1. **tabIndicator** 를 제거하기 위해 `app:tabIndicator="@null"` 추가
2. **tabItem** 에 버튼 디자인을 적용하기 위해 `selector_tab_item_background.xml`추가

`selector_tab_item_background.xml`
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_selected="true">
        <shape android:shape="rectangle">
            <solid android:color="@color/gray_700" />
            <corners android:radius="@dimen/radiusTabItem" />
        </shape>
    </item>
    <item>
        <shape android:shape="rectangle">
            <solid android:color="@android:color/transparent" />
        </shape>
    </item>
</selector>
```

`fragment_github.xml`
```xml
        <com.google.android.material.tabs.TabLayout
            android:id="@+id/tab"
            android:layout_width="match_parent"
            android:layout_height="@dimen/HeightTabItem"
            android:layout_marginHorizontal="@dimen/spacingBase"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/toolbar"
            app:tabBackground="@drawable/selector_tab_item_background"
            app:tabGravity="center"
            app:tabIndicator="@null"
            app:tabMaxWidth="@dimen/WidthTabItem"
            app:tabSelectedTextColor="@color/white"
            app:tabTextAppearance="@style/Home.TabItem.TextAppearance.Style"
            app:tabTextColor="@color/gray_700">

            <com.google.android.material.tabs.TabItem
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/github_detail_follower" />

            <com.google.android.material.tabs.TabItem
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/github_detail_repository" />

            <com.google.android.material.tabs.TabItem
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/github_detail_following" />
        </com.google.android.material.tabs.TabLayout>

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/github_detail"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tab" />
```

`GithubFragment.kt`
```kotlin
private fun initLayout() {
        binding.githubDetail.run {
            adapter = GithubAdapter(requireActivity())
            setCurrentItem(GithubDetailViewType.FOLLOWER.ordinal, false)
        }

        TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }
```
<br>

# 갤러리에서 받아온 이미지 Uri를 Glide로 띄우기
**ActivityResult API** 사용하여 읽기 권한 요청, 사용자가 선택한 이미지 uri를 Glide로 띄움

```kotlin
private fun addListeners() {
        binding.imageContainer.setOnClickListener {
            storagePermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private val storagePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                galleryLauncher.launch("image/*")
            }
        }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            Glide.with(binding.galleryImage).load(it).into(binding.galleryImage)
        }

```
<br>

# 새롭게 알게된 내용

## ViewPager2 중첩 스크롤 이슈
**Navigation** 을 사용해서 프래그먼트 전환을 구현했기 때문에 **BottomNavigation** 과 **ViewPager2** 연동할 수 없었지만, **ViewPager2** 중첩 스크롤 이슈 해결 방법을 찾아보았다.

### Support nested scrollable elements
> To support a scroll view inside a ViewPager2 object with the same orientation, **you must call requestDisallowInterceptTouchEvent()** on the ViewPager2 object when you expect to scroll the nested element instead. The [ViewPager2 nested scrolling sample](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/res/layout/item_nested_recyclerviews.xml#L43) demonstrates one way of solving this problem with a versatile [custom wrapper layout](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt).

### 방법1
xml 파일에서 중복되는 **ViewPager2** 를 **NestedScrollableHost** 로 감싸주면 해결 완!
```xml
<com.ssacproject.thirdweek.NestedScrollableHost
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent">
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</com.ssacproject.thirdweek.NestedScrollableHost>
```

### 방법2
**android:nestedScrollingEnabled** 속성을 **true** 로 설정
```xml
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/viewpager"
    android:layout_width="match_parent"
    android:nestedScrollingEnabled="true"
    android:layout_height="wrap_content" />
```

<br>

## DataStore
> **DataStore** 는 **SharedPreferences** 를 대체하기 위해 Jetpack에서 발표한 라이브러리다. **Kotlin coroutine** 과 **Flow** 를 사용하여 비동기적으로, 일관되게 데이터를 저장할 수 있다.

기존에 로그인 성공 시 다음화면으로 **UserInfo** (커스텀 객체)를 intent로 전달했었는데, 전달하지 않고 로컬에 저장해두어 필요 시 유저 정보를 불러오고 싶어 Datastore를 사용했다.

### Preferences DataStore and Proto DataStore
Preferences DataStore|Proto DataStore|
|---|---|
|1. **key-value Pair** 의 형태로 데이터를 저장 <br> 2. **Type-Safety** 제공하지 않음|1. 커스텀 데이터 타입의 데이터를 저장하는데 사용 <br> 2. 미리 정의된 **schema** 를 통하여 **Type-Safety** 를 보장 <br> 3. `app/src/main/proto/` 디렉토리 안의 proto file 의 스키마를 미리 정의해야함 (이 과정이 귀찮아서 1번 사용함 ^____^)|

<br>

# 참고
[Nested ViewPager2 이슈를 해결해보자 with NestedScorllableHost](https://velog.io/@l2hyunwoo/NestedScrollableHost)

[Preferences Datastore에서 데이터 유지](https://developer.android.com/codelabs/android-preferences-datastore?hl=ko#5)

[DataStore](https://developer.android.com/topic/libraries/architecture/datastore)

[SharedPreferences 대신 쓰는 DataStore](https://kangmin1012.tistory.com/47)

<br>

<details>
<summary>week2</summary>

# Week2
<br>

- [X] LEVEL 2
- [X] LEVEL 3

Fragment 전환|아이템 이동 및 삭제|
---|---
|<img src="https://user-images.githubusercontent.com/48701368/164447442-d689262b-9b41-44fa-86b2-056546ee8361.gif" width="250">|<img src="https://user-images.githubusercontent.com/48701368/164446719-47aa1685-77c8-4905-a113-05bad2e3aeed.gif" width="250">|

<br>

# Fragment 전환 및 RecyclerView 설정
1. **ViewPager2**와 **TabLayout**을 연결하여 Fragment 전환을 구현. 탭의 position이 변경될떄마다 fragment와 title이 변경됨

`GithubProfileActivity.kt`
```kotlin
private fun initLayout() {
    binding.githubDetail.run {
        adapter = GithubDetailAdapter(this@GithubProfileActivity)
        setCurrentItem(position, false)
    }

    TabLayoutMediator(binding.tab, binding.githubDetail) { tab, position ->
        tab.text = getString(tabTitles[position])
        }.attach()
}
```

```kotlin
inner class GithubDetailAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = GithubDetailViewType.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FollowerFragment.newInstance(GithubDetailViewType.FOLLOWER)
            }
            1 -> {
                RepositoryFragment()
            }
            2 -> {
                FollowerFragment.newInstance(GithubDetailViewType.FOLLOWING)
            }
            else -> FollowerFragment.newInstance(GithubDetailViewType.FOLLOWER)
        }
    }
}
```

2. 가장 처음 보일 Framgnet 설정. 아래 사진에서 Followers, Repositories, Following 뷰를 클릭하면 각 뷰에 따라 position을 할당해서 해당 position으로 처음 보일 Fragment 설정

<img src="https://user-images.githubusercontent.com/48701368/164451416-8bce94f6-800d-4f31-b623-5c65ebd34036.png" width="250">

`activity_home.xml`
```xml
android:onClick="moveToGithubProfile"
```

`HomeActivity.kt`
```kotlin
fun moveToGithubProfile(view: View) {
    val position = when (view) {
        binding.followingContainer -> GithubDetailViewType.FOLLOWING.ordinal
        binding.repositoryContainer -> GithubDetailViewType.REPOSITORIES.ordinal
        else -> GithubDetailViewType.FOLLOWER.ordinal
    }
    // 깃허브 프로필 화면으로 이동하는 코드 생략
}
```

`GithubProfileActivity.kt > initLayout() > setCurrentItem(position, false)`

3. 팔로워, 팔로잉 목록은 **GridLayout** 적용

`framgnet_follower.xml`
```xml
 <androidx.recyclerview.widget.RecyclerView
        app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
        app:spanCount="2"
        tools:listitem="@layout/item_follower" />
```

4. 팔로워 item 클릭 시 `FollowerDetailActivity`로 이동
```kotlin
override fun onItemClick(item: FollowerInfo) {
    val intent = Intent(requireContext(), FollowerDetailActivity::class.java)
    intent.putExtra(ARG_FOLLOWER_INFO, item)
    startActivity(intent)
}
```

5. **ItemDecoration**과 **BindingAdapter**를 이용해서 RecyclerView에 구분선 추가

```kotlin
@BindingAdapter(value = ["dividerHeight", "dividerPadding", "dividerColor"], requireAll = false)
fun RecyclerView.setDivider(
    dividerHeight: Float?,
    dividerPadding: Float?,
    @ColorInt dividerColor: Int?
) {
    val decoration = CustomDecoration(
        height = dividerHeight ?: 0f,
        padding = dividerPadding ?: 0f,
        color = dividerColor ?: Color.TRANSPARENT
    )
    addItemDecoration(decoration)
}
```

```kotlin
class CustomDecoration(
    private val height: Float,
    private val padding: Float,
    @ColorInt private val color: Int
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = color
    }

    override fun onDrawOver(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}
```

```xml
<androidx.recyclerview.widget.RecyclerView
        app:dividerColor="@{@color/gray_200}"
        app:dividerHeight="@{2f}"
        app:dividerPadding="@{0f}" />

```

<br>

# RecyclerView Item 이동 및 삭제 구현

1. **ItemTouchHelper**를 사용하면 **Drag&Drop, Swipe** 기능을  쉽게 구현할 수 있다. move 와 swipe events 에 대한 **callback**을 받는다.

```kotlin
class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener) :
    ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START // 오른쪽에서 왼쪽으로만 스와이프 가능
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.adapterPosition)
    }
}
```

```kotlin
class RepositoryListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemTouchHelperListener {

    private lateinit var touchListener: OnItemTouchListener


    interface OnItemTouchListener {
        fun onItemMove(fromPosition: Int, toPosition: Int)
        fun onItemSwipe(position: Int)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        touchListener.onItemMove(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        touchListener.onItemSwipe(position)
    }
}
```

`RepositoryFragment.kt`
```kotlin
override fun onItemMove(fromPosition: Int, toPosition: Int) {
    viewModel.moveRepository(fromPosition, toPosition)
}

override fun onItemSwipe(position: Int) {
    viewModel.removeRepository(position)
}
```

`ProfileViewModel.kt`
```kotlin
fun moveRepository(fromPosition: Int, toPosition: Int) {
    repositories.value = repositories.value?.apply {
        val origin = this[fromPosition]
        removeAt(fromPosition)
        add(toPosition, origin)
    }
}

fun removeRepository(position: Int) {
    repositories.value = repositories.value?.apply {
        removeAt(position)
    }
}
```

`RepositoryFragment.kt`
```kotlin
private fun addListeners() {
    viewModel.getRepositories().observe(viewLifecycleOwner) {
        if (it == null) return@observe
        adapter.submitList(it.toMutableList())
    }
}
```

## notifyDataSetChanged() 문제
> But this comes with a big downside – if it's a trivial change (maybe a single item added to the top), the RecyclerView isn't aware – it is told to drop all its cached item state, and thus needs to rebind everything.
It's much preferable to use DiffUtil, which will calculate and dispatch minimal updates for you.

공식문서를 확인하면 `notifyDataSetChanged()`는 위와 같은 문제가 있다. 정리해보면 다음과 같다.

문제 : Adapter는 변경된 data만 업데이트하는 것이 전제 item을 rebind하게됨. -> blicking issue 발생

해결 : DiffUtil을 사용하자!

## DiffUtil
> DiffUtil을 사용하면 두 데이터 셋을 비교한 후 그 중 변경된 데이터 셋만 반환하여 RecyclerView Adapter에 업데이트를 알림

`FollowerListAdapter.kt`
```kotlin
 private val diffCallback = object : DiffUtil.ItemCallback<FollowerInfo>() {
        override fun areItemsTheSame(oldItem: FollowerInfo, newItem: FollowerInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FollowerInfo, newItem: FollowerInfo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)


    fun submitList(items: List<FollowerInfo>?) {
        differ.submitList(items)
    }
```

<br>

# BaseActivity, BaseFragment 적용

1. Base class 추가

BaseActivity.kt|BaseFragment.kt|
---|---
|<img src="https://user-images.githubusercontent.com/48701368/164454020-a529a3df-786e-4d14-b534-21e45b73c1d2.png" width="250">|<img src="https://user-images.githubusercontent.com/48701368/164454024-203de9b6-afd3-4682-a243-d011a85a4b90.png" width="250">|

2. Activity와 Fragment에서 **BaseActivity** 및 **BaseFragment**를 상속 받음 

```kotlin
BaseActivity<ActivityFollowerDetailBinding>(R.layout.activity_follower_detail)

BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower)
```
<br>

# 새롭게 알게된 내용
## submitList 호출 시 주의 사항
```java
public void submitList(@Nullable final List<T> newList,
            @Nullable final Runnable commitCallback) {
        // incrementing generation means any currently-running diffs are discarded when they finish
        final int runGeneration = ++mMaxScheduledGeneration;

        if (newList == mList) {
            // nothing to do
            return;
        }
}
```

**submitList**의 구현부를 확인하면 `newList==mList`인 경우 return 된다. list의 주소가 같지 않은 경우에만 실제 아이템 비교로 넘어갈 수 있다. 따라서 매번 Room이나 API를 통해 데이터를 가져오는 작업이 아닌 앱 내에서 참조가 변경되지 않는 리스트를 유지하면서 값이 변경될 때도 업데이트를 반영하기 위해서는 submitList 함수 호출 시 **주소가 다른 리스트를 넘겨주어야 한다.**

```kotlin
adapter.submitList(it.toMutableList())
```

# 참고
[Slow rendering](https://developer.android.com/topic/performance/vitals/render)

[ListAdapter의 작동 원리 및 갱신이 안되는 경우](https://bb-library.tistory.com/257)

[RecyclerView에 divider 넣기 - ItemDecoration](https://leveloper.tistory.com/180)

[ItemTouchHelper.SimpleCallback](https://developer.android.com/reference/androidx/recyclerview/widget/ItemTouchHelper.SimpleCallback)

[Android Swipe To Delete in RecyclerView With DiffUtil](https://intensecoder.com/android-swipe-to-delete-in-recyclerview-with-diffutil/)

[Base 코드 관련 정리 (feat. BaseActivity, BaseFragment)](https://youngest-programming.tistory.com/285)

</details>

<details>
<summary>week1</summary>

# Week1
<br>

- [X] LEVEL 2
- [X] LEVEL 3

SignInActivity|SignUpActivity|HomeActivity|
---|---|---
|<img src="https://user-images.githubusercontent.com/48701368/162119245-a710b0ea-8a4e-442c-8f8c-56600c9ccae5.gif" width="250">|<img src="https://user-images.githubusercontent.com/48701368/162119248-84c01dea-75a7-4afa-9d02-2b4157f738f6.gif" width="250">|<img src="https://user-images.githubusercontent.com/48701368/162119409-110433d9-de67-494b-9e25-e22a00d27f18.gif" width="250">|
        
<br>

# 로그인
- **입력값 검증** : 공백 입력 방지를 위해 입력값 변경 시 마다 **trim()** 을 적용하고, 로그인 버튼 클릭 시 **isNullOrEmpty()** 로 입력값을 검증

    1. **입력 완료** : **Intent**를 통해 **HomeActivity**로 전환, 전환 시 입력한 사용자 정보(userInfo)를 **putExtra**를 통해 메인으로 전달

    2. **입력 미완료** : "아이디 또는 비밀번호를 확인해 주세요" Toast 띄우기

- **비밀번호 가리기** : `android:inputType="textPassword"` 속성 활용

- **EditText 미리보기** : `android:hint="@string/id_hint"` 속성 활용

- **회원가입 클릭** : Intent를 통해 **SignUpActivity**로 전환

<br>

## 로그인 프로세스

`activity_sign_in.xml`

1. **DataBinding**을 사용하여 입력값이 변경될 때마다 `viewModel::onUserIdTextChanged` 호출 ▶️ **MutableLiveData**타입 변수 `viewModel.userId` 에 입력값 저장
(activity에서 로그인 버튼 클릭 시 **binding.idInput.text**로 접근할 수 있지만 viewModel을 활용하기 위함) 

```xml
<EditText...
    android:id="@+id/id_input"
    android:hint="@string/id_hint"
    android:onTextChanged="@{viewModel::onUserIdTextChanged}"
    android:text="@{viewModel.userId}" />
```
```kotlin
// SignViewModel.kt
fun onUserIdTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    userId.value = s.toString().trim() // 공백 입력 방지
}

fun onUserPasswordTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    userPassword.value = s.toString().trim()
}
```

2. 로그인 버튼 클릭 시 **SignViewModel.kt**의 **signIn()** 호출
```xml
 <Button...
     android:id="@+id/sign_in"
     android:onClick="@{() -> viewModel.signIn()}" />
```
<br>

`SignViewModel.kt`

3. **isNullOrEmpty()** 이용해서 입력값 검증
`val isValid = !(userId.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())`

4. 입력값이 유효한 경우, 사용자 입력 정보(signInfo, userInfo)를 저장 (자기소개화면으로 데이터를 전달하기 위함)
   
5. **MutableLiveData** 타입의 **isValidSignInput** 변수에 검증 결과를 저장 (검증 결과에 따라 화면 전환 및 Toast를 띄우기 위함)

```kotlin
private val isValidSignInput = MutableLiveData<Boolean>()

fun signIn() {
    val isValid = !(userId.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())
     if (isValid) {
        signInfo = SignInfo(id = userId.value!!, password = userPassword.value!!)
        userInfo.name =  userName.value ?: "최영진"
        
        // TODO Implement the signin process
    }

    isValidSignInput.value = isValid
}
```

- **signInfo** : 로그인 정보(id, password)를 담는 data class
```kotlin
@Parcelize // Intent로 '객체'를 전달하기 위함
data class SignInfo(
    val id: String,
    val password: String,
) : Parcelable
```

- **userInfo** : 프로필 정보(name, age, mbti 등 프로필 정보)를 담는 data class
```kotlin
@Parcelize
data class UserInfo(
    var name: String,
    val age: Int,
    val mbti: String,
    val part: SoptPart = SoptPart.AOS,
    ...
): Parcelable
```

<br>

`SignInActivity.kt`

6. **SignViewModel**의 **isValidSignInput**값을 관찰하고, 변경된 값에 따라 ui 처리
7. 검증 성공 여부에 따라 Toast 메세지를 다르게 띄움
8. 검증 성공 시, **HomeActivity**로 이동하기 위해 **moveToHome()** 호출 
```kotlin
private fun addObservers() {
    viewModel.getValidSignInput().observe(this) { isValid ->
        if (isValid) {
            val name = viewModel.getUserInfo()?.name
            showToast(String.format(getString(R.string.sign_in_success_toast_text), name))
            moveToHome()
        } else {
            // Toast 띄우기 코드 생략
        }
    }
}
```

9. **Intent**를 활용해 **HomeActivity**로 이동 (이때 저장해둔 프로필 정보(userInfo)를 **putExtra**로 전달)

```kotlin
    private fun moveToHome() {
    val intent = Intent(this, HomeActivity::class.java)
    intent.putExtra(ARG_USER_INFO, viewModel.getUserInfo())
    startActivity(intent)
    finish()
}
```

<br>

# 회원가입
- 입력값 검증 : 로그인과 동일하게 진행

    1. 입력 완료 : `finish()`로 Activity를 종료하고 **SignInActivity**로 복귀 ▶️ 복귀 시 **registerForActivityResult**를 사용해서 회원가입에서 입력한 아이디 및 비밀번호 보여줌  **[성장과제 2-1]**

    2. 입력 미완료 : Toast 띄우기

<br> 

## 회원가입 프로세스
 
`SignInActivity.kt`
1. 로그인 화면 복귀 시 회원가입에서 입력한 아이디 및 비밀번호 보여주기 위한 사전 준비로 **registerForActivityResult**를 사용하여 **Callback** 등록

2. 회원가입화면에서 인자로 받아온 result 객체로 사용자 입력 정보(data)를 전달 받아서 **SignViewModel** ▶️ **MutableLiaveData** 타입의 **userId.value**, **userPassword.value** 변수에 저장하도록 구현

```kotlin
private fun setSignUpResult() {
    resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

        val data = result.data ?: return@registerForActivityResult
        data.getParcelableExtra<UserInfo>(ARG_USER_INFO)?.let { user ->
          viewModel.setUserInfo(user)
        }
        data.getParcelableExtra<SignInfo>(ARG_SIGN_INFO)?.let { sign ->
          viewModel.setSignInfo(sign)
        }
    }
}
```

3. **DataBinding**을 사용하여 xml에서 id, password값을 관찰 ▶️ 값 변경 시 id, password text 속성값이 갱신될 것임

```kotlin
// SignViewModel.kt
fun setSignInfo(signInfo: SignInfo) {
    userId.value = signInfo.id
    userPassword.value = signInfo.password
}
```

```xml
<EditText...
    android:id="@+id/id_input"   
    android:text="@{viewModel.userId}" />
```

4. 회원가입에서 입력한 데이터를 받아오기 위해 **resultLauncher.launch()**로 **SignUpActivity**를 실행

```kotlin
private fun addListeners() {
    binding.signUp.setOnClickListener {
        resultLauncher.launch(Intent(this, SignUpActivity::class.java))
    }
}
```

<br>

`activity_sign_up.xml`

5. 입력을 마친 후 회원가입 버튼 클릭 시 **SignViewModel** ▶️ **signUp()** 함수 호출
```xml
<Button...
    android:id="@+id/sign_up"
    android:onClick="@{() -> viewModel.signUp()}" />
```

<br>

`SignViewModel.kt`

6. 입력값 검증 후 입력값이 유효한 경우, 사용자 입력 정보를 저장 (로그인화면 데이터를 전달하기 위함)

```kotlin
fun signUp() {
    val isValid = !(userId.value.isNullOrEmpty() || userName.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty())
    if (isValid) {
        signInfo = SignInfo(id = userId.value!!, password = userPassword.value!!)
        userInfo.name =  userName.value!!
         // TODO Implement the signin process
     }

    isValidSignInput.value = isValid
}
```

<br>

`SignUpActivity.kt.kt`

7. 검증에 성공한 경우(isValid == true), **SignInActivity**로 이동하기 위해 **moveToSignIn()** 호출 

8. 검증에 실패한 경우, Toast 메세지를 띄움
```kotlin
private fun addObservers() {
    viewModel.getValidSignInput().observe(this) { isValid ->
        if (isValid) {
             moveToSignIn()
         } else {
              // Toast 띄우기 코드 생략
         }
     }
 }
```

9. **setResult**를 사용해서 로그인 화면으로 사용자 입력 정보를 전달 
```kotlin
private fun moveToSignIn() {
    val intent = Intent(this, SignInActivity::class.java)
    intent.putExtra(ARG_USER_INFO, viewModel.getUserInfo())
    intent.putExtra(ARG_SIGN_INFO, viewModel.getSignInfo())
    setResult(RESULT_OK, intent)
    finish()
}
```

<br>

# 자기소개

`activity_home.xml`

- 사진 비율 1:1로 만들기 위해 `app:layout_constraintDimensionRatio="1:1"`로 비율 설정 **[성장과제 2-2]**

```xml
 <ImageView...
    android:id="@+id/profile_img"
    android:layout_width="80dp"
    android:layout_height="0dp"
    android:src="@drawable/profile_img"
    app:layout_constraintDimensionRatio="1:1" />
```

- **SignInActivity**에서 전달받은 userInfo를 **ProfileViewModel**에 저장
```kotlin
fun setUserInfo(userInfo: UserInfo) {
    this.userInfo.value = userInfo
}
```
- viewModel.userInfo 관찰 ▶️ 값 변경 시 프로필 정보 모두 업데이트
```xml
  <TextView...
    android:id="@+id/name"
    android:text="@{viewModel.userInfo.name}"/>
```

- **ScrollView** 적용 **[성장과제 2-2]**

```xml
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <androidx.constraintlayout.widget.ConstraintLayout.../>
 </ScrollView>
 ```

<br>

# 도전 과제
## DataBinding과 ViewBinding

### Databinding 이란?
UI 요소와 데이터를 **프로그램적 방식으로 연결하지 않고, 선언적 형식으로 결합**할 수 있게 도와주는 라이브러리를 말함 **[도전과제 3-1]**

- 프로그램적 방식 예시 : TextView에 문자열을 넣기 위해 코드상에서 값을 일일이 집어넣는 작업
```kotlin
// findViewById 방식
val textview = findViewById<TextView>(R.id.textview11)
textview.text = "안녕"
// ViewBinding 방식
binding.textview11.text = "안녕"
```
- 선언적 방식 예시 : XML에 데이터를 직접 집어넣어서 해결하는 방법
```xml
<TextView...
    android:id="@+id/name"
    android:text="@{viewmodel.userName}" />
```

<br>

### DataBinding과 ViewBinding 비교
- ViewBinding ⊂ Databinding

- 공통점
    - findViewById에 비해 상대적으로 간단, 퍼포먼스 효율이 좋고 용량이 절약됨
    - 뷰의 직접 참조를 생성하므로 유효하지 않은 뷰 id로 인한 NPE로부터 안전

- DataBinding 장점 : ***동적 UI 콘텐츠 선언, 양방향 데이터 binding**도 지원

- ViewBinding 장점 : 빠른 컴파일 속도, DataBinding보다 **퍼포먼스 효율이 좋고 용량이 절약**됨

<br>

# 새롭게 알게된 내용
### registerForActivityResult
- 사용법 : registerForActivityResult()를 통해 ActivityResultContract를 등록하고 callback 함수를 재정의

- 예시는 회원가입 프로세스 2, 3, 10 번 코드 참고

- [startActivityForResult는 왜 deprecated 되었는가?](https://todaycode.tistory.com/121)

<br>

# 프로그램 구조
<img width="200" alt="스크린샷 2022-04-06 오후 10 24 44" src="https://user-images.githubusercontent.com/48701368/161985107-9a09dad1-8df2-450b-9fcd-234bf7cd265c.png">

MVVM 아키텍처 적용 **[도전과제 3-2]**

<br>

# 참고
[Activity에서 데이터 전달 받기 공식문서](https://developer.android.com/training/basics/intents/result)

[Activity Result API](https://junyoung-developer.tistory.com/151)

[registerForActivityResult()란?](https://velog.io/@ho-taek/Android-registerForActivityResult%EB%9E%80)

[Databinding 공식문서](https://developer.android.com/topic/libraries/data-binding)

[Android Binding](https://velog.io/@sh1mj1/Android-Binding-ViewBinding-DataBinding)

</details>