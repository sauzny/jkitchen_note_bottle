# 交叉表

## 一、需求说明

原始数据格式

洲 | 国家 | 城市 | 年 | 月 | 收入 | 支出
- | - | - | - | - | - | - |
亚洲 | 中国 | 北京 | 1990 | 1 | 100 | 200
亚洲 | 中国 | 北京 | 1990 | 2 | 101 | 201
亚洲 | 中国 | 北京 | 1990 | 3 | 102 | 202
亚洲 | 中国 | 北京 | 1991 | 1 | 100 | 200
亚洲 | 中国 | 北京 | 1991 | 2 | 101 | 201
亚洲 | 中国 | 北京 | 1991 | 3 | 102 | 202
亚洲 | 中国 | 天津 | 1990 | 1 | 100 | 200
亚洲 | 中国 | 天津 | 1990 | 2 | 101 | 201
亚洲 | 中国 | 天津 | 1990 | 3 | 102 | 202
亚洲 | 中国 | 天津 | 1991 | 1 | 100 | 200
亚洲 | 中国 | 天津 | 1991 | 2 | 101 | 201
亚洲 | 中国 | 天津 | 1991 | 3 | 102 | 202
亚洲 | 日本 | 大阪 | 1990 | 1 | 100 | 200
亚洲 | 日本 | 大阪 | 1990 | 2 | 101 | 201
亚洲 | 日本 | 大阪 | 1990 | 3 | 102 | 202
亚洲 | 日本 | 大阪 | 1991 | 1 | 100 | 200
亚洲 | 日本 | 大阪 | 1991 | 2 | 101 | 201
亚洲 | 日本 | 大阪 | 1991 | 3 | 102 | 202
亚洲 | 日本 | 东京 | 1990 | 1 | 100 | 200
亚洲 | 日本 | 东京 | 1990 | 2 | 101 | 201
亚洲 | 日本 | 四国 | 1990 | 3 | 102 | 202
亚洲 | 日本 | 四国 | 1991 | 1 | 100 | 200
亚洲 | 日本 | 四国 | 1991 | 2 | 101 | 201
亚洲 | 日本 | 四国 | 1991 | 3 | 102 | 202
欧洲 | 法国 | 巴黎1 | 1990 | 1 | 100 | 200
欧洲 | 法国 | 巴黎1 | 1990 | 2 | 101 | 201
欧洲 | 法国 | 巴黎1 | 1990 | 3 | 102 | 202
欧洲 | 法国 | 巴黎1 | 1991 | 1 | 100 | 200
欧洲 | 法国 | 巴黎1 | 1991 | 2 | 101 | 201
欧洲 | 法国 | 巴黎1 | 1991 | 3 | 102 | 202
欧洲 | 法国 | 巴黎2 | 1990 | 1 | 100 | 200
欧洲 | 法国 | 巴黎2 | 1990 | 2 | 101 | 201
欧洲 | 法国 | 巴黎2 | 1990 | 3 | 102 | 202
欧洲 | 法国 | 巴黎2 | 1991 | 1 | 100 | 200
欧洲 | 法国 | 巴黎2 | 1991 | 2 | 101 | 201
欧洲 | 法国 | 巴黎2 | 1991 | 3 | 102 | 202
欧洲 | 英国 | 伦敦1 | 1990 | 1 | 100 | 200
欧洲 | 英国 | 伦敦1 | 1990 | 2 | 101 | 201
欧洲 | 英国 | 伦敦1 | 1990 | 3 | 102 | 202
欧洲 | 英国 | 伦敦1 | 1991 | 1 | 100 | 200
欧洲 | 英国 | 伦敦1 | 1991 | 2 | 101 | 201
欧洲 | 英国 | 伦敦1 | 1991 | 3 | 102 | 202
欧洲 | 英国 | 伦敦2 | 1990 | 1 | 100 | 200
欧洲 | 英国 | 伦敦2 | 1990 | 2 | 101 | 201
欧洲 | 英国 | 伦敦2 | 1990 | 3 | 102 | 202
欧洲 | 英国 | 伦敦2 | 1991 | 1 | 100 | 200
欧洲 | 英国 | 伦敦2 | 1991 | 2 | 101 | 201
欧洲 | 英国 | 伦敦2 | 1991 | 3 | 102 | 202

目标数据格式

 |  | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 亚洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | 欧洲 | top~sum | top~sum
  :-: |  :-: |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |  - |
 |  | 中国 | 中国 | 中国 | 中国 | 中国 | 中国 | 中国 | 中国 | 中国 | 中国 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 日本 | 洲~sum | 洲~sum | 法国 | 法国 | 法国 | 法国 | 法国 | 法国 | 法国 | 法国 | 法国 | 法国 | 英国 | 英国 | 英国 | 英国 | 英国 | 英国 | 英国 | 英国 | 英国 | 英国 | 洲~sum | 洲~sum | top~sum | top~sum
 |  | 北京 | 北京 | 天津 | 天津 | 国家~sum | 国家~sum | 国家~avg | 国家~avg | 国家~chart | 国家~chart | 大阪 | 大阪 | 东京 | 东京 | 四国 | 四国 | 国家~sum | 国家~sum | 国家~avg | 国家~avg | 国家~chart | 国家~chart | 洲~sum | 洲~sum | 巴黎1 | 巴黎1 | 巴黎2 | 巴黎2 | 国家~sum | 国家~sum | 国家~avg | 国家~avg | 国家~chart | 国家~chart | 伦敦1 | 伦敦1 | 伦敦2 | 伦敦2 | 国家~sum | 国家~sum | 国家~avg | 国家~avg | 国家~chart | 国家~chart | 洲~sum | 洲~sum | top~sum | top~sum
 |  | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出 | 收入 | 支出
1990 | 1 | 100 | 200 | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 100 | 200 | 100 | 200 |  |  | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 400.0 | 800.0 | 100 | 200 | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 100 | 200 | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 400.0 | 800.0 | 800.0 | 1600.0
1990 | 2 | 101 | 201 | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 101 | 201 | 101 | 201 |  |  | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 404.0 | 804.0 | 101 | 201 | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 101 | 201 | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 404.0 | 804.0 | 808.0 | 1608.0
1990 | 3 | 102 | 202 | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 102 | 202 |  |  | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 408.0 | 808.0 | 102 | 202 | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 102 | 202 | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 408.0 | 808.0 | 816.0 | 1616.0
1990 | 年~min | 100.0 | 200.0 | 100.0 | 200.0 |  |  |  |  |  |  | 100.0 | 200.0 | 100.0 | 200.0 | 102.0 | 202.0 |  |  |  |  |  |  |  |  | 100.0 | 200.0 | 100.0 | 200.0 |  |  |  |  |  |  | 100.0 | 200.0 | 100.0 | 200.0 |  |  |  |  |  |  |  |  |  | 
1991 | 1 | 100 | 200 | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 100 | 200 |  |  | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 400.0 | 800.0 | 100 | 200 | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 100 | 200 | 100 | 200 | 200.0 | 400.0 | 100.0 | 200.0 | [100.0, 100.0] | [200.0, 200.0] | 400.0 | 800.0 | 800.0 | 1600.0
1991 | 2 | 101 | 201 | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 101 | 201 |  |  | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 404.0 | 804.0 | 101 | 201 | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 101 | 201 | 101 | 201 | 202.0 | 402.0 | 101.0 | 201.0 | [101.0, 101.0] | [201.0, 201.0] | 404.0 | 804.0 | 808.0 | 1608.0
1991 | 3 | 102 | 202 | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 102 | 202 |  |  | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 408.0 | 808.0 | 102 | 202 | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 102 | 202 | 102 | 202 | 204.0 | 404.0 | 102.0 | 202.0 | [102.0, 102.0] | [202.0, 202.0] | 408.0 | 808.0 | 816.0 | 1616.0
1991 | 年~min | 100.0 | 200.0 | 100.0 | 200.0 |  |  |  |  |  |  | 100.0 | 200.0 |  |  | 100.0 | 200.0 |  |  |  |  |  |  |  |  | 100.0 | 200.0 | 100.0 | 200.0 |  |  |  |  |  |  | 100.0 | 200.0 | 100.0 | 200.0 |  |  |  |  |  |  |  |  |  | 
left~sum | left~sum | 606.0 | 1206.0 | 606.0 | 1206.0 |  |  |  |  |  |  | 606.0 | 1206.0 | 201.0 | 401.0 | 405.0 | 805.0 |  |  |  |  |  |  |  |  | 606.0 | 1206.0 | 606.0 | 1206.0 |  |  |  |  |  |  | 606.0 | 1206.0 | 606.0 | 1206.0 |  |  |  |  |  |  |  |  |  |

## 二、逻辑过程
