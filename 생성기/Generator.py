#혈액 추가 sql문 생성을 위한 파이썬코드
'''

'''
import sys
import random

input = sys.stdin.readline

'''
#생성할 파일 이름 입력
print('생성할 파일이름을 입력하시오')
file_name = input().strip()

print('생성할 생성문의 갯수를 입력하시오')
num = int(input())

print('카테고리 번호를 입력하시오 : 1~7\n 1.한식 2.중식 3.양식 4.일식')
Category_num = int(input())

Category = ['한식', '중식', '양식', '일식']
'''

#직접 입력방법
#생성할 파일 이름 입력
file_name = 'insert_dml'

num = 70 #정수입력
Category = ['한식', '중식', '양식', '일식']

#Category = '' #카테고리 입력


table = ['사용자', '레시피', '댓글', '리뷰']

print('생성할 INSERT문 종류\n1.사용자 2.레시피 3.댓글 4.리뷰')
table_num = int(input())


# 여기서 부터 들어가는 값들
#region 유저정보

ID = [
'gm58z9u',
'idZJhqf',
'F42Q6I7',
'vf5ZiKm',
'bJcR1GC',
'h1BfrZz',
'N2qpbZI',
'gzJuSZc',
'L5i6GI1',
'0HlUjbO',
'Nrx9bmv',
'WDrk5I1',
'rgyxgmV',
'XGwrYAm',
'F9mSW1h',
'0whftlF',
'9HmcCBa',
'IagiDOj',
'56QQOZd',
'TCVH3Ez',
'HjK4Bji',
'Pp6Lbxj',
'vGuD8BH',
'QxHGfHM',
'YsT2kpG',
'O4lVB4D',
'AmhTTDS',
'LGSfMvh',
'jB55m0f',
'mbt65PR',
'rVk7wjk',
'hEQp0YS',
'UxXAmrn',
'I8XeEw8',
'TfwXDnW',
'tXfA7MJ',
'Bv0l4MP',
'uIYd1lY',
'sIfAHTs',
'118aK9c',
'rcjIA89',
'eNlIkY7',
'OpEpFTI',
'kHn0N1f',
'RY1RpuG',
'yZlevrh',
'1ebJeJJ',
'HlkQijz',
'1hEbaIR',
'etUo3QZ',
'W0A1VI3',
'ZRcDmPz',
'FtLJLMe',
'bdehn1l',
'Hix5po9',
'Tg9pg1a',
'FR1RrFz',
'CVffmiG',
'rTkrB1R',
'NdH3qj7',
'Hah8gQt',
'LTVHM1n',
'UbqeMVN',
'piSMZ2O',
'cCrG6wA',
'BTKzTk3',
'1RRBmOT',
'8URRlbA',
'n2jQmww',
'XGg6Hv2',
'A5zCLLK',
'kWg5b32',
'36qkHsg',
'ExXdjq1',
'kpPZThY',
's3Oiu3G',
'Eyp6q05',
'tBFk366',
'JzPzmhe',
'tZIE4sI',
'M1AiCLx',
'dr0nB9B',
'er5iemG',
'G6amEJ0',
'CZKmbwH',
'iSxvWyG',
'iGyqCkv',
'n31yqmz',
'h3xtQkU',
'9CQ1ODx',
'Otdb2FW',
'T94cnsk',
'gDfYsVG',
'H4gu0wZ',
'JXLz0TN',
'84xi73j',
'JikfvX1',
'Ob3g9wN',
'VCMqu48',
'4Xqmox3'
]

name = [
    '오재호', '탁선준', '류병하', '권인혁', '권범준', '설규빈', '정진희', '손문영', '한경옥', '황기원', '성창재', '표영자', '사공상현', '강태환',
    '이재용', '황태석', '최인혁', '황보호성', '백명자', '심동준', '추하현', '사공재욱', '조병하', '전원경', '남궁종남', '이미선', '이대현', '전승하',
    '오정훈', '남궁진경', '추주은', '제갈선영', '성문혁', '제갈성원', '서인경', '황보인옥', '추상학', '장성준', '성진호', '사공원일', '남궁다현', '복경희',
    '양남지', '배경자', '정재하', '남궁영연', '유동주', '정명환', '장소정', '이민국', '권원진', '봉승호', '한지윤', '배수민', '이민희', '장호성', '한광민', '풍종훈', '남궁호정', '조재숙',
    '류기남', '정문식', '송경선', '황보은식', '전희욱', '임미주', '윤유현', '최원재', '박재성', '예상윤', '백원자', '제갈윤자', '노기호', '임현희', '손기철',
    '이호영', '남준수', '장한빈', '배원일', '안성일', '남지윤', '신형민', '안승윤', '백효원', '하동환', '유동환', '예성훈', '황보선희', '남궁순재', '문경옥',
    '신현웅', '표형주', '황진수', '윤신우', '권영옥', '황보혜경', '안연자', '남성현', '전영식', '장예영'
]


email = [ '@naver.com', '@daum.net', '@gmail.com', '@hanmail.net', '@nate.com', '@yahoo.com']

Rating = [
    '아이언',
    '브론즈',
    '실버',
    '골드',
    '플레티넘',
    '다이아'
]


#endregion


#region 레시피정보

'레시피번호, 제목, 작성자ID, 작성내용, 작성시간, 카테고리, 레시피설명, 조회수, 추천수, 난이도'

ko_title = [
    '곤약밥',
    '녹두밥',
    '메밀밥',
    '밀밥',
    '쌀밥',
    '당근밥',
    '대추밥',
    '대통밥',
    '두부밥',
    '밤밥',
    '비지밥',
    '보리밥',
    '수수밥',
    '은행밥',
    '옥수수밥',
    '율무밥',
    '자굴밥',
    '조밥',
    '찰밥',
    '콩밥',
    '완두콩밥',
    '클로렐라밥',
    '팥밥',
    '매운잡채',
    '비빔당면',
    '우짜',
    '잡채',
    '짜장면',
    '간짜장',
    '물짜장',
    '사천짜장',
    '삼선짜장',
    '짬짜장',
    '볶음면',
    '볶음짬뽕',
    '김치말이국수',
    '냉면',
    '진주냉면',
    '평양냉면',
    '농마국수',
    '밀면',
    '열무국수',
    '초계국수',
    '콩국수',
    '비빔면',
    '골뱅이소면',
    '냉면',
    '덮밥',
    '컵밥',
    '약밥',
    '영양밥',
    '율무밥',
    '밥버거',
    '고구마떡',
    '구름떡',
    '귀리떡',
    '근대떡',
    '김치떡',
    '깻잎떡',
    '꿀떡',
    '느티떡',
    '달떡',
    '달팽이떡',
    '댑싸리떡',
    '도토리떡',
    '두텁떡',
    '마꽃떡',
    '돼지고기김치볶음',
    '두부김치',
    '보쌈',
    '부대찌개',
    '삼합',
    '기무치',
    '마르코프차',
    '겉절이',
    '상추겉절이',
    '김치',
    '배추김치',
    '깍두기',
    '순무김치',
    '총각김치',
    '열무김치',
    '갓김치',
    '오이소박이',
    '동치미'
]

jp_title = [
    '쇼츄',
    '고구마',
    '맥주',
    '아사히',
    '에비스',
    '위스키',
    '블렌디드 위스키',
    '싱글 몰트 위스키',
    '하이볼',
    '가쿠빈',
    '히비키',
    '야마자키',
    '하쿠슈',
    '요이치',
    '미도리',
    '차조기',
    '카키고오리',
    '빙수',
    '일본식 중화요리',
    '라멘',
    '나가사키 짬뽕',
    '군만두',
    '냉라멘',
    '텐신항',
    '경양식',
    '오므라이스',
    '나폴리탄 스파게티',
    '카레라이스',
    '영국',
    '하이라이스',
    '데미글라스 소스',
    '고로케',
    '프랑스',
    '크로켓',
    '토루코라이스',
    '타코(요리)',
    '멕시코',
    '타코(요리)',
    '오키나와 요리',
    '카스테라',
    '사라다빵',
    '사라다',
    '고로케',
    '멜론빵',
    '슈크림빵',
    '카레빵',
    '텐동',
    '도리텐',
    '텐카스',
    '가라아게',
    '전분',
    '치킨',
    '치킨난반',
    '프라이',
    '가스',
    '고로케',
    '빵가루',
    '새우튀김',
    '굴튀김',
    '돈가스',
    '비프가스',
    '쿠시카츠',
    '덴푸라',
    '가라아게',
    '가스',
    '돈가스',
    '쿠시카츠',
    '간사이',
    '오사카부',
    '포석정',
    '츠케멘',
    '떡',
    '모찌',
    '찰떡',
    '오조니'
]

cn_title = [
        
    '삼선볶음밥',
    '황금 볶음밥',
    '기스면',
    '작장면',
    '도삭면',
    '딴딴면',
    '라몐',
    '뱡뱡면',
    '쏸라펀',
    '우육면',
    '장수면',
    '차오몐',
    '초마면',
    '탕육사면',
    '감자볶음',
    '거지닭',
    '경장육사',
    '구수계',
    '궁보계정',
    '깐쇼새우',
    '꽃빵',
    '난자완스',
    '남경 오리 요리',
    '납팔죽',
    '누룽지탕',
    '동파육',
    '딤섬',
    '라조기',
    '량샤',
    '마라샹궈',
    '마라탕',
    '마파두부',
    '만두',
    '만터우',
    '멘보샤',
    '모두부',
    '백운저수',
    '부용단',
    '북경 오리 구이',
    '불도장',
    '탕후루',
    '빤켁',
    '삼부점',
    '샤오룽샤',
    '샥스핀',
    '석자갱',
    '소롱포',
    '소채',
    '자차이',
    '청경채볶음',
    '월병(과자)',
    '유탸오',
    '오향장육',
    '유린기',
    '어향육사',
    '젠빙',
    '주량원자',
    '쭝쯔',
    '차사오',
    '처우구이위',
    '고추잡채',
    '취두부',
    '탄카오루양',
    '팔보채',
    '피단',
    '해삼주스',
    '행인두부',
    '할포',
    '회과육',
    '훈툰',
    '훠궈'


]

Wes_title = [
    '프라이드치킨',
    '버팔로 윙',
    '비어 캔 치킨',
    '치킨너겟',
    '바비큐',
    '햄버거',
    '당근 케이크',
    '치즈케이크',
    '브라우니',
    '대마초 브라우니',
    '도넛',
    '레드 벨벳 케이크',
    '머핀',
    '몬테크리스토 샌드위치',
    '시카고식 피자',
    '시폰케이크',
    '팬케이크',
    '옥수수빵',
    '잠발라야',
    '캘리포니아 롤',
    '치킨누들수프',
    '크림소스 스파게티',
    '마카로니 앤 치즈',
    '랍스터 롤',
    '크랩 케이크',
    '바나나 스플릿',
    '비스킷',
    '초코칩 쿠키',
    '베이크드 빈즈',
    '검보',
    '클램차우더',
    '그래놀라',
    '나폴리탄 아이스크림',
    '땅콩버터',
    '루트비어',
    '마시멜로',
    '계란 프라이',
    '토스트',
    '팬케이크',
    '추수감사절',
    '칠면조',
    '팝콘',
    '바비큐',
    '타코',
    '칠라킬레',
    '부리토',
    '파히타',
    '타말레스',
    '세비체',
    '과카몰레',
    '뵈렉',
    '에크멕',
    '괴즐레메',
    '파즐르 카이가나',
    '크이말르 피데',
    '에틀리에크멕',
    '야을라마',
    '라흐마준',
    '이칠리 쾨프테',
    '툴룸바',
    '로크마',
    '셰케르파레',
    '누리예',
    '푸리',
    '쇼티',
    '므차디',
    '므흐로바니',
    '츠비쉬타리',
    '쿠브다리',
    '고지나키',
    '힌칼리',
    '쿠파티',
    '므츠바디',
    '오자쿠리',
    '로비오',
    '사치비',
    '슈크메룰리',
    '차나히',
    '헐라슬레',
    '푀르쾰트 ',
    '오브바잔키',
    '자피에칸카',
    '샤코티스',
    '비고스',
    '피에로기',
    '즈라지',
    '고웡프키',
    '샤슈위크',
    '클롭시키',
    '주레크',
    '바르시치',
    '카푸시니아크',
    '플라키',
    '미제리아',
    '로수',
    '오구르코바',
    '플라츠키'

]



cate_table = []

#endregion

#region 댓글정보

'댓글번호, 레시피번호, 작성시간, 댓글내용, 작성자ID'

Comments = [
    '우와 대박이에요~~!',
    '좋은 정보 감사합니다!',
    '행복하세요',
    '오늘은 이거닷',
    '좋은 레시피 감사합니다',
    '너무 어려워요 ㅠㅠ',
]

#endregion

#region 리뷰

'리뷰번호, 레시피번호, 작성시간, 평점, 작성자ID'

#endregion

f = open('./'+file_name+'.txt', 'w', encoding="UTF-8")

#사용자
if table_num == 1:
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (사용자ID, 이름, 이메일, 전화번호, 비밀번호, 총작성수, 등급) VALUES ('{ID[i]}', '{name[random.randint(0,len(name)-1)]}', '{ID[random.randint(0,len(ID)-1)]+email[random.randint(0,len(email)-1)]}', '{'010-'+ str(random.randint(1,9999)).zfill(4) +'-'+ str(random.randint(1,9999)).zfill(4)}', '{'passwd'}', 0, '뉴비');\n")
    
#레시피
elif table_num == 2:

    print('카테고리 번호를 입력하시오 : 1~4\n 1.한식 2.중식 3.양식 4.일식')
    Category_num = int(input())

    if Category_num == 1:
        cate_table = ko_title
    elif Category_num == 2:
        cate_table = cn_title
    elif Category_num == 3:
        cate_table = Wes_title
    elif Category_num == 4:
        cate_table = jp_title


    for i in range(len(cate_table)-1) :
        class_num = random.randint(0, len(cate_table)-1)
        f.write(f"INSERT INTO {table[table_num-1]} (레시피번호, 제목, 작성자ID, 작성내용, 작성시간, 카테고리, 레시피설명, 조회수, 추천수, 난이도) \
VALUES ({i+1+235}, '{cate_table[class_num]}', '아이디들어갈곳', '조리법 {random.randint(0, num)}', '{'2023-'+str(random.randint(1,12))+'-'+str(random.randint(1,30))}', '{Category[Category_num-1]}', '맛있는 {cate_table[class_num]} 레시피!', {0}, {0}, {random.randint(1,5)});\n")
    
#댓글
elif table_num == 3:
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (댓글번호, 레시피번호, 작성시간, 댓글내용, 작성자ID) VALUES ({1}, {i+1}, '{'2023-'+str(random.randint(1,12))+'-'+str(random.randint(1,30))}', '{Comments[random.randint(0,len(Comments)-1)]}', '아이디들어갈곳');\n")

#리뷰
else :
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (리뷰번호, 레시피번호, 리뷰내용, 작성시간, 평점, 작성자ID) VALUES ({1}, {i+1}, '{i+1}번 레시피 리뷰','{'2023-'+str(random.randint(1,12))+'-'+str(random.randint(1,30))}', {random.randint(1,10)}, '아이디들어갈곳');\n")


f.close()


