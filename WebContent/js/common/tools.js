function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
function uploadImg() {
	var myFiles = new Array();
	var swfpath = getRootPath() + '/js/common/uploadify.swf';
	var uploadPath = getRootPath() + '/tools/upload';
	var dataFormServer = '';
	var myFileCnt = 0;
	$('#file-upload').uploadify({
	'fileTypeDesc' : '图片文件',
	'fileTypeExts' : '*.jpg; *.png',
	'fileSizeLimit' : '2000KB',
	'width' : 70,
	'height' : 28,
	'swf': swfpath,
	'uploader': uploadPath,
	'buttonText': '上传图片',
	'multi' : false,
	'auto' : true,
	'fileObjName' : 'file',
	'checkExisting' : false,
	'onUploadSuccess' : function(file, data, response) {		
		$("#imgPathID").val(JSON.parse(data).link);
	}
	});
}