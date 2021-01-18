# Capstone
- 2020 - 2학기 캡스톤디자인

### 최종 결과물은 'json_static_yr' 파일

- 오픈뱅킹 API를 이용해 가계부 만들기
  - 오픈뱅킹 테스트베드 이용하여 임시 데이터로 실행
  - '거래명세서'에서 '거래내역조회 API'를 사용하여 거래 내역 데이터를 출력
  
### 앱 구성화면

#### SPLASH 화면
- 앱 로딩 화면
  
<img src = "https://user-images.githubusercontent.com/45381907/104887152-2d0de900-59ae-11eb-8e99-3111c98d06ef.png" width="300">

#### 첫화면
- 카드뷰 형식으로 구성
  - 내역 조회
    - 테스트베드에 25개의 데이터밖에 올리지 못해서 편의상 소비내역(list)와 소비내역(statistic)으로 구분
    - 두 화면 구성은 동일 단지 데이터가 다름
  - 소비 통계
    - 월별 지출 내역의 합을 내어 월별로 가장 많이 지출한 구분이 무엇인지 순위 정하기
<img src ="https://user-images.githubusercontent.com/45381907/104887010-ff28a480-59ad-11eb-9562-08c1790b4798.png" width="300">
<img src = "https://user-images.githubusercontent.com/45381907/104887011-ffc13b00-59ad-11eb-8db9-d8c1319aeffb.png" width="300">
<img src ="https://user-images.githubusercontent.com/45381907/104887013-ffc13b00-59ad-11eb-83c8-2f7677d98e69.png" width="300">

#### 내역 조회
- 조회하고 싶은 날짜 기간을 선택 하고 조회를 누르면 json 형태로 받아온 데이터를 list 형태로 바꾸어 조회 가능
  - 기본 구성
  
  <img src = "https://user-images.githubusercontent.com/45381907/104887009-ff28a480-59ad-11eb-9827-ae63f3db83ed.png" width="300">
  
  - 날짜 선택
  
  <img src ="https://user-images.githubusercontent.com/45381907/104887007-fdf77780-59ad-11eb-99e9-50f01e0fc8a3.png" width="300">
  
  - 조회 결과
  
  <img src = "https://user-images.githubusercontent.com/45381907/104887153-2da67f80-59ae-11eb-955a-5fa4374e09a6.png" width="300">
  
    - start date가 end date 보다 후로 선택 된 경우 "시작날짜를 마지막 날짜보다 전으로 선택하시오" 라는 toast가 뜨게 됨
    - start date 나 end date가 오늘 후의 날짜로 선택 될 경우 "오늘 이전의 날짜를 선택하시오." 라는 toast가 뜨게 됨
    <img src ="https://user-images.githubusercontent.com/45381907/104887159-2ed7ac80-59ae-11eb-8904-91afe4a44683.png" width="300">
    <img src ="https://user-images.githubusercontent.com/45381907/104887160-2ed7ac80-59ae-11eb-841b-6c4a6a7778d4.png" wdith="300">
#### 소비 통계
- 통계를 보고 싶은 달을 선택하여 조회를 누르면 막대 그래프 형식으로 가장 많은 소비를 했던 구분이 어디인지 시각적으로 확인 가능
<img src ="https://user-images.githubusercontent.com/45381907/104887155-2e3f1600-59ae-11eb-9ca4-3e0ca9c98e15.png" width="300">
<img src ="https://user-images.githubusercontent.com/45381907/104887151-2d0de900-59ae-11eb-9c9a-f3cba2de62f3.png" width="300">
