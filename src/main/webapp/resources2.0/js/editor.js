var Editor = function(options) {};
Editor.defaults = {
    editorEm:undefined,
    lang: 'zh-CN',
    minHeight: 100,
    placeholder: '请输入内容....',
    uploadPath:'/upload'
};
Editor.prototype = {
    init: function(options){
        this.config = $.extend(true, {}, Editor.defaults, options);
        var that = this;
        if(that.config.editorEm === undefined) {
            layer.msg('Error:请配置编译器容器');
            return;
        }
        if(that.config.uploadPath === undefined) {
            layer.msg('Error:请配置图片上传地址');
            return;
        }
        $(that.config.editorEm).summernote({
            minHeight: that.config.minHeight,
            lang: that.config.lang,
            callbacks: {
                onImageUpload: function(files) {
                    that.upload(files)
                }
            }
        });
        return this;
    },
    upload: function(files){
        var that = this, formData = new FormData();
        for(f in files){
            formData.append("file", files[f]);
        }
        // formData.append('file',files[0]);
        $.ajax({
            url : that.config.uploadPath,
            type : 'POST',
            data : formData,
            processData : false,
            contentType : false,
            success : function(result) {
                if(result.code != 0){
                    layer.msg('Error:上传图片失败');
                }else{
                    for(i in result.data){
                        $(that.config.editorEm).summernote('insertImage',result.data[i],function ($image) {
                            $image.css('width', $image.width() / 2);
                            $image.attr('data-filename', 'retriever');
                        });
                    }
                }
            }
        });
    },
    getCode: function(){
        return $(this.config.editorEm).summernote('code');
    },
    setCode: function(code){
        $(this.config.editorEm).summernote('code', code);
        return this;
    },
    destroy: function(){
        $(this.config.editorEm).summernote('destroy');
        return this;
    }
}
