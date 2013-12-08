# Switch 문장을 Polymorphic Dispatcher로 치환하기
* switch 문장은 polymorhic dispatch를 사용해서 제거해야 case 문장에 존재하는 코드들로 부터의 영향을 최소화할 수 있다.

# 사전 준비
* git co -b remove-switch-stmt origin/remove-switch-stmt

# 1. subclassing
* Movie 클래스의 determineAmount 메소드는 type code에 의존하고 있다.
* type code를 기반으로 Movie 클래스를 NewReleaseMovie, ChildrensMovie, RegularMovie로 subclassing한다. 
## 1-1. red phase
* 테스트를 먼저 수정.
* 테스트의 입장으로 보면 변경된 부분은 polymorphic하다.
* 테스트 코드에서
	new Movie를 new NewReleaseMovie 등의 서브클래스 생성으로 변경
## 1-2. green phase
* hot fix를 이용해서 subclasses를 생성한다.
* run test하여 제대로 동작하는지 확인
## 1-3. blue phase
* remove duplication
* new NewReleaseMovie("New Release 1", Movie.NEW_RELEASE)를 new NewReleaseMovie("New Release 1");

# 2. Push member down - determineAmount, determineFrequentRentalPoint
* Movie 클래스에서 determineAmount, determineFrequentRentalPoint 2개의 메소드를 선택하여 push members down
## 2-1. green phase
* push members down refactoring시 set action / leave abstract declaration을 선택한다.
## 2-2. blue phase
* remove unused code
* eclEmma의 code coverage를 수행하면서 반복적으로 각 클래스의 사용되지 않는 코드들을 제거한다.
