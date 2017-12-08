/**
 * 公共方法js
 */

$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	complete : function(XMLHttpRequest, textStatus) {
		// 通过XMLHttpRequest取得响应头，sessionstatus，
		var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); 
		if (sessionstatus == "timeout") {
			// 如果超时就处理 ，指定要跳转的页面
			window.location.replace("user/tologin");
		}
	}
});

var ObjectUtil = {
	isEmpty : function(val) {
		if (val != null && val != '' && $.trim(val) != '' && val != undefined) {
			return false;
		}
		return true;
	},
	isNotEmpty : function(val) {
		if (val != null && val != '' && $.trim(val) != '' && val != undefined) {
			return true;
		}
		return false;
	},
	getValue : function (val, defaultValue) {
		if (val != null && val != '' && $.trim(val) != '' && val != undefined) {
			return val;
		}
		return defaultValue;
	},
	dateCompareTo : function(val1, val2) {// 时间比较
		var date1 = new Date(Date.parse(val1.replace(/-/g, "/"))); // 转换成Date();
		var date2 = new Date(Date.parse(val2.replace(/-/g, "/")));
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	},
	dateCompareToNum : function(val1, val2) {// 时间比较
		var date1 = new Date(Date.parse(val1.replace(/-/g, "/"))); // 转换成Date();
		var date2 = new Date(Date.parse(val2.replace(/-/g, "/")));
		if (date1.getTime() > date2.getTime()) {// date1大于date2
			return 0;
		} else if (date1.getTime() == date2.getTime()) {
			return 1;
		}
		return 2;
	},
	strToDate : function(val) {// 字符创转换为时间格式
		return new Date(Date.parse(val.replace(/-/g, "/"))); // 转换成Date();
	},
	replace : function(str, findText, repText) {// 单个替换
		return str.replace(findText, repText);
	},
	replaceAll : function(str, findText, repText) {// 全部替换
		var regExp = new RegExp(findText, 'g');
		return str.replace(regExp, repText);
	},
	fmtTime : function(ms, formattr) {
		if (ms == null) {
			return "";
		}
		var date = new Date();
		date.setTime(ms);
		var year = date.getFullYear();
		var month = toDouble(date.getMonth() + 1);
		var day = toDouble(date.getDate());
		var hour = toDouble(date.getHours());
		var tHour = parseInt(hour) < 12 ? parseInt(hour) : parseInt(hour) - 12;
		var minute = toDouble(date.getMinutes());
		var second = toDouble(date.getSeconds());
		switch (formattr) {
		case 'yyyy-mm-dd':
			return year + '-' + month + '-' + day;
			break;
		case 'yyyy-mm-dd hh:mm:ss':
			return year + '-' + month + '-' + day + ' ' + hour + ':' + minute
					+ ':' + second;
			break;
		case 'yyyy-mm-dd HH:mm:ss':
			return year + '-' + month + '-' + day + ' ' + tHour + ':' + minute
					+ ':' + second;
			break;
		default:
			return;
			break;
		}
	},
	chooseData : function(val, object, defaultVal){
		if(object == null){
			return defaultVal;
		}
		if(ObjectUtil.isNotEmpty(object[val])){
			return object[val];
		}
		return defaultVal;
	}
};
function toDouble(num) {
	return num < 10 ? '0' + num : num;
}
Date.prototype.dateDiff = function(interval, objDate2) {
	var d = this, i = {}, t = d.getTime(), t2 = objDate2.getTime();
	i['y'] = objDate2.getFullYear() - d.getFullYear();
	i['q'] = i['y'] * 4 + Math.floor(objDate2.getMonth() / 4)
			- Math.floor(d.getMonth() / 4);
	i['m'] = i['y'] * 12 + objDate2.getMonth() - d.getMonth();
	i['ms'] = objDate2.getTime() - d.getTime();
	i['w'] = Math.floor((t2 + 345600000) / (604800000))
			- Math.floor((t + 345600000) / (604800000));
	i['d'] = Math.floor(t2 / 86400000) - Math.floor(t / 86400000);
	i['h'] = Math.floor(t2 / 3600000) - Math.floor(t / 3600000);
	i['n'] = Math.floor(t2 / 60000) - Math.floor(t / 60000);
	i['s'] = Math.floor(t2 / 1000) - Math.floor(t / 1000);
	return i[interval];
}

Date.prototype.dateAdd = function(interval, number) {
	var d = this;
	var k = {
		'y' : 'FullYear',
		'q' : 'Month',
		'm' : 'Month',
		'w' : 'Date',
		'd' : 'Date',
		'h' : 'Hours',
		'n' : 'Minutes',
		's' : 'Seconds',
		'ms' : 'MilliSeconds'
	};
	var n = {
		'q' : 3,
		'w' : 7
	};
	eval('d.set' + k[interval] + '(d.get' + k[interval] + '()+'
			+ ((n[interval] || 1) * number) + ')');
	return d;
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), /* 季度 */
		"S" : this.getMilliseconds()
	// 毫秒
	}
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
