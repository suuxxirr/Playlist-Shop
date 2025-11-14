## Commit Message 규칙
#### 전체적인 포맷
```
타입(Type): 제목(Subject)

본문(Body)

꼬리말(Footer)
```

#### Type
```
feat: 새로운 기능에 대한 커밋
fix: 버그 수정
build: 빌드 관련 파일 수정
ci: CI 관련 설정 수정
docs: 문서(문서 추가, 수정, 삭제)
style: 스타일(코드형식: 비즈니스 로직에 변경 없는 경우)
refactor: 코드 리팩토링
test: 테스트(테스트 코드 추가/수정/삭제: 비즈니스 로직에 변경 없는 경우)
chore: 기타 변경사항
```

#### Subject
- 50자 이상 X, 마침표 사용 X
- 과거시제 X, 명령조 사용

#### Body
- 선택사항
- how보다 what, why에 집중하여 작성

#### Footer
- 선택사

#### Full Commit Message Example
```
Feat: 신규 RFID 인식 기능 추가(#123)

신규 RFID 기능 인식 기능 추가
  - RFIDReader.java: 사용자 요건 사항으로 인한 RFID 인식 기능 추가

해결: #123
```
