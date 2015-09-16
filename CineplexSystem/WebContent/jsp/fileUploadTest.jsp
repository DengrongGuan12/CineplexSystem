<%--<%@page language="java" pageEncoding="GB18030" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >

<head>

<title>SWFUpload Demos - Application Demo</title>

<link href="swfupload/default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="swfupload/swfupload.js"></script>

<script type="text/javascript" src="swfupload/handlers.js"></script>

<script type="text/javascript">
		//alert("kaishi");
		var swfu;
		window.onload = function () {
			swfu = new SWFUpload({
				// Backend Settings

				upload_url: "deal.jsp",//�������ϴ��ļ��ĵ�ַ

				post_params: {"param": "ifa219qj1fs33n8jpl63nkd9o1"},



				// File Upload Settings

				file_size_limit : "2 MB",	// 2MB

				file_types : "*.jpg",

				file_types_description : "JPG Images",

				file_upload_limit : "0",




				// Event Handler Settings - these functions as defined in Handlers.js

				//  The handlers are not part of SWFUpload but are part of my website and control how

				//  my website reacts to the SWFUpload events.

				file_queue_error_handler : fileQueueError,

				file_dialog_complete_handler : fileDialogComplete,

				upload_progress_handler : uploadProgress,

				upload_error_handler : uploadError,

				upload_success_handler : uploadSuccess,

				upload_complete_handler : uploadComplete,
				
				file_queue_error_handler : fileQueueError,
				//��ѡ���ļ��Ի���ر���ʧʱ�����ѡ����ļ����뵽�ϴ�������ʧ�ܣ���ô���ÿ��������ļ����ᴥ��һ�θ��¼�
				file_dialog_complete_handler : fileDialogComplete,
				//���ļ�ѡȡ�Ի���رպ󴥷����¼�������
				file_queued_handler : fileQueued,
				//���ļ�ѡ��Ի���ر���ʧʱ�����ѡ����ļ��ɹ������ϴ����У���ô���ÿ���ɹ�������ļ����ᴥ��һ�θ��¼�
				upload_progress_handler : uploadProgress,
				//���¼���flash��ʱ�������ṩ���������ֱ�����ϴ��ļ��������ϴ����ֽ������ܹ����ֽ�������˿���������¼�������ʱ����ҳ���е�UIԪ�أ��Դﵽ��ʱ��ʾ�ϴ����ȵ�Ч��
				upload_error_handler : uploadError,
				//����ʲôʱ��ֻҪ�ϴ�����ֹ����û�гɹ���ɣ���ô���¼�������������error code������ʾ�˵�ǰ���������
				upload_success_handler : uploadSuccess,
				//���ļ��ϴ��Ĵ����Ѿ���ɣ���������ֻ��ָ��Ŀ�괦���������Files��Ϣ��ֻ�ܷ��������Ƿ�ɹ����գ�
				upload_complete_handler : uploadComplete,
				//���ϴ������е�һ���ļ������һ���ϴ����ڣ������ǳɹ�(uoloadSuccess����)����ʧ��(uploadError����)�����¼����ᱻ��������Ҳ��־��һ���ļ����ϴ���ɣ����Խ�����һ���ļ����ϴ���
				
				// Button Settings

				button_image_url : "swfupload/images/SmallSpyGlassWithTransperancy_17x18.png",

				button_placeholder_id : "spanButtonPlaceholder",//��Ҫ�滻��html�ڵ�

				button_width: 180,

				button_height: 18,

				button_text : '<span class="button">Select Images <span class="buttonSmall">(2 MB Max)</span></span>',

				button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',

				button_text_top_padding: 0,

				button_text_left_padding: 18,

				button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,

				button_cursor: SWFUpload.CURSOR.HAND,

				

				// Flash Settings

				flash_url : "swfupload/swfupload.swf",//swfupload.swf



				custom_settings : {

					upload_target : "divFileProgressContainer"

				},

				

				// Debug Settings

				debug: false

			});

		};

	</script>

</head>

<body>

<div id="header">

	<h1 id="logo">SWFUpload</h1>

	<div id="version">v2.2.0</div>

</div>

<div id="content">

	<h2>Application Demo</h2>

	<p>This demo shows how SWFUpload can behave like an AJAX application.  Images are uploaded by SWFUpload then some JavaScript is used to display the thumbnails without reloading the page.</p>

		<form>

		<div style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">

			<span id="spanButtonPlaceholder"></span>

		</div>

	</form>

		<div id="divFileProgressContainer" style="height: 75px;"></div>

	<div id="thumbnails"></div>
	

</div>

</body>

</html>

--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >

<head>

<title>SWFUpload Demos - Simple Demo</title>

<link href="${pageContext.request.contextPath}/swfupload/default.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/swfupload.queue.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/fileprogress.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/swfupload/handlers.js"></script>

<script type="text/javascript">
		//alert();
		var swfu;



		window.onload = function() {
			//alert();
			var settings = {

				flash_url : "swfupload/swfupload.swf",

				upload_url: "upload/FileUpload.action",

				post_params: {"Filename" : ""},

				file_size_limit : "100 MB",

				file_types : "*.*",

				file_types_description : "All Files",

				file_upload_limit : 100,

				file_queue_limit : 0,
				file_post_name: "Filedata",

				custom_settings : {

					progressTarget : "fsUploadProgress",

					cancelButtonId : "btnCancel"

				},

				debug: false,



				// Button settings

				button_image_url: "swfupload/images/SmallSpyGlassWithTransperancy_17x18.png",

				button_width: "140",

				button_height: "18",

				button_placeholder_id: "spanButtonPlaceHolder",

				button_text: '<span class="button">ѡ��ͼƬ<span class="buttonSmall">(���2 MB)</span></span>',

				button_text_style: ".theFont { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; }",

				button_text_top_padding: 0,

				button_text_left_padding: 18,

				button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,

				button_cursor: SWFUpload.CURSOR.HAND,

				

				// The event handler functions are defined in handlers.js

				file_queued_handler : fileQueued,

				file_queue_error_handler : fileQueueError,

				file_dialog_complete_handler : fileDialogComplete,

				upload_start_handler : uploadStart,

				upload_progress_handler : uploadProgress,

				upload_error_handler : uploadError,

				upload_success_handler : uploadSuccess,

				upload_complete_handler : uploadComplete,

				queue_complete_handler : queueComplete	// Queue plugin event

			};


			//alert(��ʼ��);
			swfu = new SWFUpload(settings);

	     };

	</script>

</head>

<body>

<div id="header">

	<h1 id="logo"><a href="#">SWFUpload</a></h1>

	<div id="version">v2.2.0</div>

</div>



<div id="content">

	<h2>Simple Demo</h2>

	<form id="form1" action="upload/FileSave" method="post" enctype="multipart/form-data">

		<p>This page demonstrates a simple usage of SWFUpload.  It uses the Queue Plugin to simplify uploading or cancelling all queued files.</p>



			<div class="fieldset flash" id="fsUploadProgress">

			<span class="legend">Upload Queue</span>

			</div>

		

			<div>

				<span id="spanButtonPlaceHolder"></span>

				<input id="btnCancel" type="button" value="ȡ�������ϴ�" onclick="swfu.cancelQueue();" disabled="disabled" style="margin-left: 2px; font-size: 8pt; height: 18px;" />
				
				<input type="submit" value="�ύ"/>

			</div>



	</form>

</div>

</body>

</html>

