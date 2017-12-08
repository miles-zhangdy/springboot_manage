'use strict';

function Table(tableId, options, callback) {
	// elements
	if (!tableId) {
		console.error('Invalid tableId');
		return;
	}
	this.oBox = $('#' + tableId);			// main table
	this.tbody = this.oBox.find('tbody');		// tbody		
	this.options = options || {};			// table data
	if (!this.options.theadData) {
		console.error('Invalid table data');
		return;
	}
	this.options.formatter = options.formatter || {};		// data formatter
	this.options.formatter.num = options.formatter.num || 0;
	this.options.formatter.date = options.formatter.date || 'yyyy-mm-dd';
	this.callback = callback || {};			// callback function
	this.rowData = {};					// data of each row
	this.init(tableId);
}
Table.prototype = {
	init: function(tableId) {
		var _this = this;
		// clean up tbody
		if (!this.oBox.find('tbody').length) {
			this.oBox.append('<tbody></tbody>');
			this.tbody = this.oBox.find('tbody');
		}
		this.tbody.html('');
		this.tbody.on('click', 'tr', function() {
			_this.callback.selRow && _this.callback.selRow($(this).index());
		});
	},
	insert: function(tableId, data) {
		var _this = this;
		this.tbody.empty();
		
		if(data.length) {
			$.each(data, function(i, n) {
				var clazz ="or-tr1";
				if(i%2 ==0){
					 clazz ="or-tr1";
				}else{
					 clazz ="or-tr2";
				}
				var tr = $('<tr class="' + clazz + '" rid="' + i + '"></tr>');
				_this.recur(n, tr,tableId,i);
				_this.tbody.append(tr);
				_this.rowData[i] = n;
			});
		}
	},
	recur: function(n, tr, tableId, row) {
		// 根据theadData每一项的code获取tbody对应code的数据并依次插入
		// 根据theadData每一项的type对tbody对应code的数据进行处理
		for (var i = 0; i < this.options.theadData.length; i++) {
			switch (this.options.theadData[i].type) {
				case 'text':
					if(n[this.options.theadData[i].code]==undefined||n[this.options.theadData[i].code]==null){
						tr.append('<td class="or-td"></td>');
					}else{
						if(!this.options.theadData[i].view) {
							//style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
							if(this.options.theadData[i].maxlength != undefined && this.options.theadData[i].maxlength > 0){
								if(n[this.options.theadData[i].code].length > this.options.theadData[i].maxlength){
									tr.append('<td class="or-td" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' + n[this.options.theadData[i].code].substring(0,this.options.theadData[i].maxlength) + '</td>');
									break;
								}
							}
							tr.append('<td class="or-td" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' + n[this.options.theadData[i].code] + '</td>');
							break;
						}
						// console.log(n[this.options.theadData[i].code]);
						// style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
						if(this.options.theadData[i].maxlength != undefined && this.options.theadData[i].maxlength > 0){
							if(n[this.options.theadData[i].code].length > this.options.theadData[i].maxlength){
								tr.append('<td class="or-td" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' + 
										this.filtrate(n[this.options.theadData[i].code].substring(0,this.options.theadData[i].maxlength), this.options.theadData[i].view[n[this.options.theadData[i].code].substring(0,this.options.theadData[i].maxlength)]) + '</td>');
								break;
							}
						}
						tr.append('<td class="or-td" style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">' + this.filtrate(n[this.options.theadData[i].code], this.options.theadData[i].view[n[this.options.theadData[i].code]]) + '</td>');
					}
					
					break;
				case 'date':
					if(n[this.options.theadData[i].code]==undefined||n[this.options.theadData[i].code]==null){
						tr.append('<td class="or-td"></td>');
					}else{
						tr.append('<td class="or-td">' + this.formatDate(n[this.options.theadData[i].code], this.options.formatter.date) + '</td>');
					}
					break;
				case 'num':
					tr.append('<td class="or-td">' + n[this.options.theadData[i].code].toFixed(this.options.formatter.num) + '</td>');
					break;
				case 'input':
					if(this.options.theadData[i].maxlength == undefined){
						tr.append('<td class="or-td"><input type="text" name="'+this.options.theadData[i].code+'"></td>');
						break;
					}
					tr.append('<td class="or-td"><input type="text" maxlength='+this.options.theadData[i].maxlength+'  name="'+this.options.theadData[i].code+'"></td>');
					break;
				case 'checkbox':
					tr.append('<td class="or-td"><input type="checkbox" name="'+tableId+'_ck" rid="'+row+'" ></td>');
					break;
				case 'op':
					this.callback.op && this.callback.op(tr, n, this.options.theadData[i]);
					break;
				default:
					break;
			}
		}
	},
	formatDate: function(ms, formatter) {
		if(!ms) {
			return;
		}
		var date = new Date();
		date.setTime(ms);
		var year = date.getFullYear();
		var month = this.toDouble(date.getMonth() + 1);
		var day = this.toDouble(date.getDate());
		var hour = this.toDouble(date.getHours());
		var tHour = parseInt(hour) < 12? parseInt(hour): parseInt(hour) - 12;
		var minute = this.toDouble(date.getMinutes());
		var second = this.toDouble(date.getSeconds());
		switch (formatter) {
			case 'yyyy-mm-dd':
				return year + '-' + month + '-' + day;
				break;
			case 'yyyy-mm-dd hh:mm:ss':
				return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
				break;
			case 'yyyy-mm-dd HH:mm:ss':
				return year + '-' + month + '-' + day + ' ' + tHour + ':' + minute + ':' + second;
				break;
			default:
				return;
				break;
		}
	},
	filtrate: function(v, text) {
		
		if(text!=null&&text!=""&&text!=undefined){
			return text;
		}else{
			return v;
		}
	},
	toDouble: function(num) {
		return num < 10? '0' + num: num;
	},
	getRowData: function(i) {
		return this.rowData[i];
	}
};

// $(function() {
// 	var table = new Table('table', {
// 		theadData: theadArr
// 	});
// 	table.insert('table', tbodyArr);
// });
// var theadArr = [{
// 	code: 'id',
// 	type: 'num'
// },{
// 	code: 'time',
// 	type: 'date'
// }, {
// 	code: 'content',
// 	type: 'text'
// }, {
// 	code: 'input',
// 	type: 'checkbox'
// }, {
// 	code: 'status',
// 	type: 'text',
// 	view: {
// 		1: '状态良好',
// 		2: '状态不好'
// 	}
// }];
// var tbodyArr = [{
// 	id: 3.5,
// 	time: 1445911887063,
// 	content: 'hello world',
// 	fn: 'Good morning',
// 	status: '1'
// }, {
// 	id: 2.4,
// 	time: 1445911887063,
// 	content: 'hello world',
// 	fn: 'Good morning',
// 	status: '1'
// }, {
// 	id: 3,
// 	time: 1445911887063,
// 	content: 'hello world',
// 	fn: 'Good morning',
// 	status: '2'
// }];

