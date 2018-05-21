$(function() {
  $('#btnUpload').click(function() {
    mydialog.showWait('文件上传中...', '请稍侯');
    dataService.saveFile('/myimages/upload', $('#upload'), {
      'images.description': $('#txtDescription').val()
    }, function(err, data) {
      mydialog.hideWait();
      mydialog.showAlert(data.message, '消息', function() {
        console.log(data);
        $('#upload').val('');
        $('#txtDescription').val('');
        query();
      });
    });
  });

  function query() {
    mydialog.showWait('查询数据中...', '请稍候');
    dataService.send('/myimages/queryLast', {}, function(err, data) {
      console.log(err, data);
      mydialog.hideWait();
      mydialog.showAlert(data.message, '信息', function() {
        $('#tbData').html('');
        $.each(data.datas.list, function(i, v) {
          var tr = $(document.createElement('tr'));
          var td;

          td = $(document.createElement('td'));
          td.append(v.filename);
          tr.append(td);

          td = $(document.createElement('td'));
          td.append(v.description);
          tr.append(td);

          td = $(document.createElement('td'));
          td.append(v.filesize);
          tr.append(td);

          td = $(document.createElement('td'));
          td.append(v.uploadDate);
          tr.append(td);

          td = $(document.createElement('td'));
          var imgpath = '../..' + v.savePath;
          var a = $(document.createElement('a'));
          a.attr('href', imgpath);
          a.attr('target', '_blank');
          var img = $(document.createElement('img'));
          img.css('width', '50px');
          img.attr('src', '../..' + v.savePath);
          a.append(img);
          td.append(a);
          tr.append(td);

          $('#tbData').append(tr);
        });
      });
    });

  }

  query();

});