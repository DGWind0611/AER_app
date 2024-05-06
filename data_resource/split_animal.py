import pandas as pd

# 定義動物類別對應的 class 值的字典
animal_classes = {
    "哺乳綱": "Mammalia",
    "鳥綱": "Aves",
    "爬蟲綱": "Reptilia",
    "兩棲綱": "Amphibia",
}

# 定義每個動物類別所需的欄位
required_columns = [
    "simple_name",
    "common_name_c",
    "alternative_name_c",
    "class"
]

# 讀取CSV檔案
df = pd.read_csv('TaiCOL_taxon_20240418.csv', encoding='utf-8')
print("原始資料數量：", len(df))

# 篩選出kingdom為Animalia且is_in_taiwan為True的資料並選擇指定的欄位
filtered_df = df[(df['kingdom'] == 'Animalia') & (df['is_in_taiwan'] == True)]

# 保留指定的動物類別和相應的欄位
filtered_df = filtered_df[filtered_df['class'].isin(animal_classes.values())]

# 選擇需要的欄位
filtered_df = filtered_df[required_columns]

# 顯示篩選後的資料數量
print("篩選後的資料數量：", len(filtered_df))

# 先寫成一個檔案
filtered_df.to_csv('animal_data.csv', index=False)

# 根據每個類別寫入到不同的 CSV 檔案
for class_name, class_value in animal_classes.items():
    class_data = filtered_df[filtered_df['class'] == class_value]
    class_data.to_csv(f'{class_value}.csv', index=False)

print("檔案已成功寫入")
