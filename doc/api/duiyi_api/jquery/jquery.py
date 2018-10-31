#!/usr/bin/python
# -*- coding: utf-8 -*-  
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

import json,chardet,re,codecs,os
from pyquery import PyQuery as pq

def walk_dir(filepath):
    #遍历filepath下所有文件，包括子目录
    res = []
    files = os.listdir(filepath)
    for fi in files:
        fi_d = os.path.join(filepath, fi)        
        if os.path.isdir(fi_d):
            walk_dir(fi_d)                  
        else:
            res.append(fi_d)

    return res

res = walk_dir('/home/liming/Documents/duiyi_api/jquery') # jquery
for jquery_item in res:
    suffix = jquery_item.split('.')[1] # suff
    if suffix in ['html', 'htm']:
        print jquery_item
        try:
            res = ''
            with open(jquery_item) as jquery_h:
                for line in jquery_h:
                    replace_str = "<div id=\"tips-con\" data-key=\"close-update801\" style=\"display: block\"><div id=\"tips-body\" class=\"tips-body\">推荐办理招商信用卡，新户首刷礼，五折享美食，需要的速度围观~<a href=\"http://www.cuishifeng.cn/go/card\" target=\"_blank\">click here</a></div></div>"
                    content = line.strip() # strip 
                    res += content.replace(replace_str, '')

            with open(jquery_item, 'w') as jquery_w: jquery_w.write(res) # write 
        except:
            print jquery_item

