/**
 * Created by ruanweibiao on 2016-03-02.
 */
define('fw/uimanager',['_g','doT', 'widget/tablegrid' ,  'widget/dialog'],function(_g , tplEngine , tablegrid ) {
    var _this = this;

    var UIManager = new function() {

        var restApiCtx = _g.getFullRestApiContext();

        // --- inner init method
        var dialogWidget = null, dialogConfig = {};

        (function(){
            require(['widget/dialog'], function(widgetBuilder) {
                widgetBuilder.init(dialogConfig);
                dialogWidget = widgetBuilder.build();
            });
        }());


        this.openDialog = function(viewConf) {

            // --- create dialog html ---
            dialogWidget.openCommonDialog(viewConf);


        };

        this.closeDialog = function(dialogRef) {
            dialogWidget.closeDialog(dialogRef);

            // --- delete element ---
        }

        // --- define message dialog
        this.showMsgDialog = function(dialogRef) {
            dialogWidget.showMsgDialog(dialogRef);

        }






        this.renderWidget = function(widgetName , config , callback) {

            // --- call config ---
            require([widgetName], function(widgetBuilder) {
                widgetBuilder.init(config);
                var widget = widgetBuilder.build();
                if (callback) {
                    callback(widget);
                }
            });

        };

        this.renderContentHeader = function(conf) {
            // --- init content base tools ---
            var contHeaderElems = $('section.content-header');
            tplEngine.render({
                templateId:'content-nav-header-tpl',
                data:{
                    "title":conf['title']?conf['title']:''
                },
                renderCallback: function(renderResult) {
                    contHeaderElems.html(renderResult);
                }
            });
        }


        this.renderNavMenu = function(conf) {
            var menuElems = $('section.sidebar ul.sidebar-menu');

            $.get(restApiCtx + '/system/nav-menus',function(data,status) {
                if (status == 'success') {
                    // --- parse data ---
                    var navMenus = data["data"];
                    // --- parse template ---
                    tplEngine.render({
                        templateId:'sidebar-menu-tpl',
                        data:navMenus,
                        renderCallback: function(renderResult) {
                            menuElems.html(renderResult);
                        }
                    });
                }
            });
        }


        this.getRenderedRowTools = function(iconClsArray) {

            var templateHtml = '<div class="tools">';
            templateHtml = templateHtml + '{{~it : value : index }}' + '<i class="fa {{=value["iconCls"]}}" data-value="{{=value["value"]}}" ></i>' + '{{~}}',
            templateHtml = templateHtml+'</div>';

            var tools = null;

            tplEngine.render({
                template:templateHtml,
                data:iconClsArray,
                renderCallback:function(result) {
                    tools = result;
                }
            });

            return tools;

        }


        this.getTableCheckSelectedPlugin = function(conf) {

            if (!conf['name']) {
                throw Error('Field "name" is not empty.');
            }

            var _rawVal = conf['raw'];
            if (!_rawVal) {
                _rawVal = '';
            }

            var _val = conf['value'];
            var appendHtml = '';
            if ( _val == _rawVal ) {
                appendHtml = 'checked="checked" ';
            }


            var compType = conf['type'];
            var html = '';
            if (compType == 'radio') {
                html = '<input type="radio" name="'+conf['name']+'" value="'+_rawVal+'" '+appendHtml +' />';
            } else {
                html = '<input type="checkbox" value="'+_rawVal+'" name="'+ conf['name'] +'" '+appendHtml +' />';
            }
            return html;

        }

    };



    return UIManager;
});