$(function() {
  var needRefresh = false;

  $('#btnShowAdd').click(function() {
    mydialog.showCustom($('#addDialog'), '留言', function() {
      $('#txtInfo').val('');
      if (needRefresh) {
        needRefresh = false;
        refreshPage();
      }
    });
    setTimeout(function() {
      $('#txtUsername').focus();
    }, 500);
  });

  $('#btnRefresh').click(refreshPage);

  $('#btnClose').click(function() {
    mydialog.hideCustom();
  });

  $('#btnSave').click(function() {
    mydialog.showWait('发布留言中。。。', '请等待');
    dataService.send('/bbs/add', {
      'bbs.username': $('#txtUsername').val(),
      'bbs.info': $('#txtInfo').val()
    }, function(err, data) {
      mydialog.hideWait();
      if (err || !data.success) {
        mydialog.showAlert(data.message, '信息');
        return;
      }
      needRefresh = true;
      mydialog.hideCustom();
    });

  });

  //默认分页信息
  var page = {
    'pageNumber': 1,
    'pageSize': 10
  };

  function refreshPage() {
    $('#divbbs').html('');
    page.pageNumber = 1;
    query();
  }

  function query() {
    mydialog.showWait('查询数据中...', '请稍候');
    dataService.send('/bbs/query', {
      'page.pageNumber': page.pageNumber,
      'page.pageSize': page.pageSize
    }, function(err, data) {
      console.log(err, data);
      mydialog.hideWait();
      page = data.datas.page;
      if (!data.success) {
        mydialog.showAlert(data.message, '信息');
        return;
      }
      $.each(data.datas.list, function(i, v) {
        var divMain = $(document.createElement('div'));
        divMain.attr('class', 'div-01');

        var divTitle = $(document.createElement('div'));
        divTitle.attr('class', 'title');
        divTitle.append(v.username);
        divMain.append(divTitle);

        var divDivider = $(document.createElement('div'));
        divDivider.attr('class', 'divider');
        divMain.append(divDivider);

        var divBody = $(document.createElement('div'));
        divBody.attr('class', 'body');
        divBody.append(v.info);
        divMain.append(divBody);

        divDivider = $(document.createElement('div'));
        divDivider.attr('class', 'divider');
        divMain.append(divDivider);

        var divFooter = $(document.createElement('div'));
        divFooter.attr('class', 'footer');
        divFooter.append(v.createDate);
        divMain.append(divFooter);

        $('#divbbs').append(divMain);



      });
    });
  }

  query();

});