$(function() {
  dataService.send('/', {}, function(err, data) {
    if (err) {
      mydialog.showAlert(data.message);
      return;
    }
    console.log(data);
  });


  $('#btnUpload').click(function() {
    dataService.saveFile('/myimages/upload', $('#upload'), {
      'images.description': '文件描述'
    }, function(err, data) {
      console.log(err, data);
    });
  });

});