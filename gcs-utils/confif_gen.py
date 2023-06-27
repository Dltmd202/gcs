# 파일 이름 입력 받기
file_name = input("생성할 파일의 이름을 입력하세요 (확장자 포함): ")

# 사용자로부터 sysid 입력 받기
sysid_list = input("sysid 값을 공백으로 구분하여 입력하세요: ").split()

# sysid 값을 정수로 변환
sysid_list = [int(sysid) for sysid in sysid_list]

# sysid를 오름차순으로 정렬
sysid_list.sort()

# 설정 파일 생성
xml_content = '<?xml version="1.0" encoding="UTF-8" ?>\n\n<agents connection="mav_link" rotation="0">\n'
for index, sysid in enumerate(sysid_list, start=1):
    xml_content += f'    <agent id="{index}">\n'
    xml_content += f'        <sysid>{sysid}</sysid>\n'
    xml_content += f'        <mode> real </mode>\n'
    xml_content += f'        <type>CMODEL</type>\n'
    xml_content += f'        <group>1</group>\n'
    xml_content += f'        <vehicle>IRIS</vehicle>\n'
    xml_content += f'        <ip>10.42.0.{100 + sysid}</ip>\n'
    xml_content += f'        <port>9750</port>\n'
    xml_content += f'    </agent>\n'
xml_content += '</agents>\n'
# 설정 파일 저장
with open(file_name, 'w') as file:
    file.write(xml_content)
print(f"설정 파일이 '{file_name}'에 저장되었습니다.")