import csv
import xml.etree.ElementTree as ET

# 解析XML檔案
tree = ET.parse('downloadxml.xml')
root = tree.getroot()

# 欄位名稱
fields = ['ScientificName', 'ChineseName1', 'ChineseName2', 'ChineseName3']

# 建立CSV檔案並寫入資料
with open('tw_fish.csv', 'w', newline='', encoding='utf-8') as csvfile:
    writer = csv.DictWriter(csvfile, fieldnames=fields)
    writer.writeheader()

    # 遍歷每個<DataSet>元素
    for dataset in root.findall('DataSet'):
        data = {}
        # 將每個欄位的值填入字典
        for field in fields:
            data[field] = dataset.find(field).text if dataset.find(field) is not None else ''
        # 寫入CSV檔案
        writer.writerow(data)

print("CSV檔案已成功建立！")
