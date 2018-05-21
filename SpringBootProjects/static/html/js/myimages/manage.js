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

  //默认分页信息
  var page = {
    'pageNumber': 1,
    'pageSize': 5
  };

  $('#btnNext').click(function() {
    page.pageNumber = page.pageNumber + 1;
    if (page.pageNumber > page.pageCount) {
      page.pageNumber = page.pageCount;
    }
    query();
  });

  $('#btnPre').click(function() {
    page.pageNumber = page.pageNumber - 1;
    if (page.pageNumber <= 0) {
      page.pageNumber = 1;
    }
    query();
  });

  function query() {
    mydialog.showWait('查询数据中...', '请稍候');
    dataService.send('/myimages/queryPage', {
      'page.pageNumber': page.pageNumber,
      'page.pageSize': page.pageSize
    }, function(err, data) {
      console.log(err, data);
      mydialog.hideWait();
      page = data.datas.page;
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

          td = $(document.createElement('td'));
          var span = $(document.createElement('span'));
          span.append('删除');
          span.attr('class', 'btn btn-danger');
          span.click(function() {
            mydialog.showConfirm('是否删除：' + v.description + '?', '确认删除', function() {
              deleteImages(v.imageId);
            });
          });
          td.append(span);
          tr.append(td);

          $('#tbData').append(tr);
        });
        $('#spPage').html(page.pageCount + '/' + page.pageNumber);
      });
    });

  }

  function deleteImages(imageId) {
    console.log(imageId);
    mydialog.showWait('删除数据中...', '请稍候');
    dataService.send('/myimages/delete', {
      'images.imageId': imageId
    }, function(err, data) {
      mydialog.hideWait();
      mydialog.showAlert(data.message, '信息', function() {
        query();
      });

    });
  }

  query();

});