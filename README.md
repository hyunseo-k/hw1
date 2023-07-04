# 1분반 바텐더


## 개요

2023 여름 몰입캠프 공통과제 1(2023.06.29. ~ 2023.07.05.)을 수행한 결과물입니다.
술을 사랑하는 몰입캠프 1분반 사람들의 즐거운 칵테일 생활을 도와주는 앱입니다!

## Collaborators

|  Name  |                    GitHub ID                    |          소속           |
| :----: | :---------------------------------------------: | :---------------------: |
| 강현서 |    [hyunseo-k](https://github.com/hyunseo-k)    | 성균관대 소프트웨어학과 |
| 권진현 | [jinhyeonkwon](https://github.com/jinhyeonkwon) |    카이스트 전산학부    |

## 기능 설명

### 탭 1 : 전화번호부

칵테일은 분반 사람들과 함께 먹어야 제맛입니다. 언제든지 칵테일을 함께 먹을 사람을 부를 수 있는 전화번호부 기능입니다!

- assets/junbun.json 파일로 미리 저장해둔 연락처 정보를 보여줍니다. 각 항목을 누르면, 연락처 상세 보기가 가능하며 전화 걸기, 문자 보내기 버튼을 사용하여 바로 연락할 수 있습니다!

<img src="https://github.com/hyunseo-k/hw1/assets/79782180/f855105b-0a40-4795-9afd-afa3d0a08b1f" width="30%" height="30%"/>
<img src="https://github.com/hyunseo-k/hw1/assets/79782180/c3c5e136-6979-433f-8e95-09d1e38cd207" width="30%" height="30%"/>
<img src="https://github.com/hyunseo-k/hw1/assets/79782180/d5fbe44c-834c-453f-8b96-1c8b58286ec2" width="30%" height="30%"/>

### 탭 2 : 갤러리

매일매일 마시는 칵테일 사진을 모아서 볼 수 있는 갤러리입니다. 나만의 칵테일 컬렉션을 만들고, 분반 사람들과의 추억을 간직할 수 있습니다!

- assets/images.json 파일에, 미리 저장해둔 사진 이름과 제목의 쌍이 저장되어 있습니다. 갤러리는 각 사진을 제목과 함께 보여주며, 클릭하면 해당 사진만 확대해서 볼 수 있는 팝업이 나타납니다!

<img src="https://github.com/hyunseo-k/hw1/assets/79782180/b373f3a2-2167-4330-be35-740e3c180894" width="30%" height="30%"/>
<img src="https://github.com/hyunseo-k/hw1/assets/79782180/e0bdef7c-f524-4482-9946-6af870edd0b6" width="30%" height="30%"/>

### 탭 3 : 칵테일

칵테일 사랑의 완성은, 직접 만들어 먹는 것이죠! 만들어보고 싶은 칵테일의 레시피를 검색하거나, 랜덤 칵테일을 추천받을 수 있습니다!

- TheCocktailDB API를 사용하여 칵테일 정보를 불러옵니다. 검색창에 원하는 칵테일 이름을 입력하면, 이름이 일치하는 칵테일 중에 ABC 순으로 가장 앞에 있는 결과를 띄워 줍니다.
- '랜덤 칵테일 추천받기' 버튼을 누르면, 랜덤 칵테일을 불러와서 화면에 보여줍니다.

<img src="https://github.com/hyunseo-k/hw1/assets/79782180/232dfdc3-9a52-43fc-ae82-03261a1b5fcb" width="30%" height="30%"/>
<img src="https://github.com/hyunseo-k/hw1/assets/79782180/bfcf29d3-110d-4c18-8a21-958ee0902282" width="30%" height="30%"/>
