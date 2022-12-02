요구 사항

앱

Gradle 버전을 7.5.1로 변경하였습니다.

<!> 코틀린 버전을 1.7.21로 변경하는 과정에서 문제가 생겨 수정중입니다.

모듈

:app Module은 Application Module을, :room Module은 Android Library을 사용했습니다.

아키텍처

TextViewModel Class로 Model과 View Model을 분리하였습니다.

라이브러리

기능 구현에 필요한 Room, Coroutine, Compose Navigation 라이브러리를 선택적으로 implementation 했습니다. Jetpack Compose는 BOM을 적용하였습니다.

프로젝트

Compose를 사용하여 선언형 프로그래밍 방식으로 UI를 제작하였습니다. TextField에 입력된 값을 viewModelScope를 사용하여 비동기로 Room DB에 저장하였습니다. 저장된 값을 AlertDialog에 표시할 때에는 View와 View Model을 연결하는 부분은 LiveData로, Room DB와 View Model을 연결하는 부분은 Flow로 분리하여 구현하였습니다.

viewModelScope와 Flow 모두 Coroutine Context에서 비동기적으로 실행됩니다.
