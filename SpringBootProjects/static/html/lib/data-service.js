$(function() {
  var emptyfn = function() {};

  var dataService = {
    'baseurl': 'http://127.0.0.1:13001'
  };
  window.dataService = dataService;

  dataService.setBaseUrl = function(url) {
    dataService.baseurl = url;
  };

  dataService.send = function(url, params, cb) {
    if (!params) {
      params = {};
    }
    if (!cb) {
      cb = emptyfn;
    }
    $.post(dataService.baseurl + url, params, function(data) {
      cb(null, data);
    }, 'json').error(function(data) {
      cb({
        'success': false,
        'code': 500,
        'message': '处理数据发生错误:' + JSON.stringify(data),
      });
    });
  };

  dataService.saveFile = function(url, file, params, cb) {
    if (!params) {
      params = {};
    }
    if (!cb) {
      cb = emptyfn;
    }

    var formData = new FormData();
    formData.append('file', file[0].files[0]);
    for (var p in params) {
      formData.append('' + p, params[p]);
      console.log('' + p, params[p])
    }

    $.ajax({
      'url': dataService.baseurl + url,
      'type': "POST",
      'data': formData,
      'cache': false,
      'contentType': false,
      'processData': false,
      success: function(data) {
        cb(null, data);
      },
      error: function(data) {
        cb(data, null);
      }
    });

  };

});