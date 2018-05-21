$(function() {

  function query() {
    mydialog.showWait('查询数据中...', '请稍候');
    dataService.send('/myimages/queryLast', {}, function(err, data) {
      console.log(err, data);
      mydialog.hideWait();
      mydialog.showAlert(data.message, '信息', function() {
        $('#mycarousel .carousel-indicators').html('');
        $('#mycarousel .carousel-inner').html('');
        if (data.datas.list.length <= 0) {
          mydialog.showAlert('请上传图片。。。', '信息');
          return;
        }

        $.each(data.datas.list, function(i, v) {
          //======================================================
          var li = $(document.createElement('li'));
          li.attr('data-target', '#mycarousel');
          li.attr('data-slide-to', i);
          if (i == 0) {
            li.attr('class', 'active');
          }
          $('#mycarousel .carousel-indicators').append(li);
          //=======================================================
          var item = $(document.createElement('div'));
          i == 0 ? item.attr('class', 'item active') : item.attr('class', 'item');
          var img = $(document.createElement('img'));
          img.attr('src', '../../' + v.savePath);
          img.attr('class', 'img-responsive');
          item.append(img);
          var caption = $(document.createElement('div'));
          caption.attr('class', 'carousel-caption');
          caption.html(v.description);
          item.append(caption);
          $('#mycarousel .carousel-inner').append(item);
        });

        $('#mycarousel').carousel({
          interval: 2000
        });
        
      });
    });

  }

  query();

});