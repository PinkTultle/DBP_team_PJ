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

print('카테고리 번호를 입력하시오 : 1~7\n 1.한식 2.중식 3.양식 4.일식 5.멕시코식 6.베트남식 7.간식')
Category_num = int(input())

Category = ['한식', '중식', '양식', '일식', '멕시코식','베트남식', '간식']
'''

#직접 입력방법
#생성할 파일 이름 입력
file_name = 'test'

num = 5 #정수입력

Category = '' #카테고리 입력


table = ['사용자', '레시피', '댓글', '리뷰']

print('생성할 INSERT문 종류\n1.사용자 2.레시피 3.댓글 4.리뷰')
table_num = int(input())


# 여기서 부터 들어가는 값들

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
    '양남지', '배경자', '정재하', '남궁영연', '유동주', '정명환', '장소정', '이민국', '권원진', '봉승호', '한지윤', '배수민', '이민희', '장호성', '한광민', '풍종훈', '남궁호정', '조재숙'
    '류기남', '정문식', '송경선', '황보은식', '전희욱', '임미주', '윤유현', '최원재', '박재성', '예상윤', '백원자', '제갈윤자', '노기호', '임현희', '손기철',
    '이호영', '남준수', '장한빈', '배원일', '안성일', '남지윤', '신형민', '안승윤', '백효원', '하동환', '유동환', '예성훈', '황보선희', '남궁순재', '문경옥',
    '신현웅', '표형주', '황진수', '윤신우', '권영옥', '황보혜경', '안연자', '남성현', '전영식', '장예영'
]


email = [ '@naver.com', '@daum.net', '@gmail.com', '@hanmail.net', '@nate.com', '@yahoo.com']


























f = open('./'+file_name+'.txt', 'w')

#사용자
if table_num == 1:
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (사용자ID, 이름, 이메일, 전화번호, 비밀번호, 총작성수, 등급) VALUES ('{ID[i]}','{name[random.randint(0,len(name)-1)]}','{ID[random.randint(0,len(ID)-1)]+email[random.randint(0,len(email)-1)]}','{'010-'+ str(random.randint(1,9999)).zfill(4) +'-'+ str(random.randint(1,9999)).zfill(4)}','{'passwd'}', {0},'{None}');\n")

#레시피
elif table_num == 2:
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (신청일자, 신청_혈액형, 신청_혈액성분, 신청_혈액팩_수, 신청_담당자_코드, 신청_센터) VALUES ( '2023-"+str(random.randint(1,4)) +"-"+str(random.randint(1,30)) +"', '"+ str(blood_list[random.randint(0,3)]) +"', '"+ str(type_list[random.randint(0,2)]) +"', '"+ str(random.randint(1,6)) +"', 'PIC-0000000"+str(random.randint(1,21)) +"', '"+ str(center[random.randint(0,33)])+"');\n")

#댓글
elif table_num == 3:
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (신청일자, 신청_혈액형, 신청_혈액성분, 신청_혈액팩_수, 신청_담당자_코드, 신청_센터) VALUES ( '2023-"+str(random.randint(1,4)) +"-"+str(random.randint(1,30)) +"', '"+ str(blood_list[random.randint(0,3)]) +"', '"+ str(type_list[random.randint(0,2)]) +"', '"+ str(random.randint(1,6)) +"', 'PIC-0000000"+str(random.randint(1,21)) +"', '"+ str(center[random.randint(0,33)])+"');\n")

#리뷰
else :
    for i in range(num) :
        f.write(f"INSERT INTO {table[table_num-1]} (신청일자, 신청_혈액형, 신청_혈액성분, 신청_혈액팩_수, 신청_담당자_코드, 신청_센터) VALUES ( '2023-"+str(random.randint(1,4)) +"-"+str(random.randint(1,30)) +"', '"+ str(blood_list[random.randint(0,3)]) +"', '"+ str(type_list[random.randint(0,2)]) +"', '"+ str(random.randint(1,6)) +"', 'PIC-0000000"+str(random.randint(1,21)) +"', '"+ str(center[random.randint(0,33)])+"');\n")


f.close()


