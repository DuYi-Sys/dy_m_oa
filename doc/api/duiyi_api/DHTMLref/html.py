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

res = walk_dir('/home/liming/Documents/duiyi_api/html5') # html
for html5_item in res:
    suffix = html5_item.split('.')[1] # suff
    if suffix in ['html', 'htm']:
        try:
            res = ''
            with open(html5_item) as html5_h:
                html_content = html5_h.read() # read 
                html_content = html_content.decode('gb2312').encode('utf-8-sig')
                # html_content = html_content[len(codecs.BOM_UTF8):] # delete BOM

                # print html_content

                content = pq(unicode(html_content, "utf-8-sig", "ignore"))
                content("#footer").remove()
                content('#selected').remove() 
                content('#searchui').remove()

                # chardet.detect() code 
                res = codecs.BOM_UTF8
                res += str(content.html()) # result

            with open(html5_item, 'w') as html5_h: html5_h.write(res) # write 
        except:
            print html5_item
