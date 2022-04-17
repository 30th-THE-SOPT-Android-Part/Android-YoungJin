# Android-YoungJin
<!-- week2 작성 시 토글로 정리 예정 -->
<!-- <details>
<summary>week1</summary> -->
# Week1
<br>

- [X] 도전과제 2-1
- [X] 도전과제 2-2
- [X] 도전과제 3-1
- [X] 도전과제 3-2


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