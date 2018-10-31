#!/usr/bin/python
# -*- coding: utf-8 -*- 
import sys
reload(sys)
sys.setdefaultencoding("utf-8")
import json,urllib,urllib2,json
import requests, httplib
import MySQLdb
from ConfigParser import SafeConfigParser

def commit_sql_cmd(self, sql_cmd, db_type, params=None):
    if not db_type in self.db_dict:
        raise Exception("Unknown db_type: %s" % db_type)
    if self.db_dict[db_type] is None:
        self._reset_db_connection(db_type)
    db = self.db_dict[db_type]
    db_cur = db.cursor()
    retry_count = 0
    while retry_count < 3:
        try:
            db_cur.execute(sql_cmd,params)
            insert_id = db.insert_id()
            db.commit()
            return insert_id
        except:
            self._reset_db_connection(db_type)
            db = self.db_dict[db_type]
            db_cur = db.cursor()

        retry_count += 1

    return -1

def get_request(url, host, params, method = 'GET', ctime = 30):
    headers = gen_http_header(host)
    retry = 3
    while retry > 0:
        try:
            if method == 'GET':
                r = requests.get(url, timeout=ctime) # path
                res = r.text.encode(r.encoding)
                return res
            elif method == 'POST':
                r = requests.post(url, headers = headers, data=json.dumps(params), timeout = ctime)
                res = r.text.encode(r.encoding)
                return res
            else:
                return None
        except requests.exceptions.ReadTimeout:
            print 'connection next time'
            retry -= 1

    return  ''

def get_http_proxy(url, host, proxy_host, proxy_port):
    gen_header  = {'Host': '%s' % host,
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36',
        'Accept-Encoding': '*;q=0',
        'Accept-Language': 'zh-CN,zh;q=0.8',
        'Cookie': '',
    }
    retry = 3
    req = urllib2.Request(url, headers = gen_header)
    while retry > 0:
        try:  
            # 高匿列表：http://www.xicidaili.com/nn/
            proxy_handler = urllib2.ProxyHandler({'http': '%s:%s' % (proxy_host, proxy_port)})
            opener = urllib2.build_opener(proxy_handler)
            reply = opener.open(req, timeout=30)
            r = reply.read()
            reply.close()
            return r.strip()
        except Exception,e:
            retry -= 1

    return ''

def gen_http_header(host):
    return {
            'Host': '%s' % host,
            'Accept': 'application/json, text/javascript, */*; q=0.01',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/61.0',
            'Accept-Encoding': '*;q=0',
            'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3',
            'Content-Type': 'application/json',
            'Cookie': '164a6af37e4ed-04b3084da01864-47e1039-1fa400-164a6af37e7726',
            'Connection': 'keep-alive'
    }

def gen_phone_header(host):
    return {
        'Host': '%s' % host,
        'Accept': 'application/json, text/javascript, */*; q=0.01',
        'User-Agent': 'AMAP_Location_SDK_Android 3.6.1',
        'Accept-Encoding': 'gzip',
        'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3',
        'Connection': 'keep-alive',
        'Content-Type': 'application/octet-stream',
        'Content-Length': 682
    }