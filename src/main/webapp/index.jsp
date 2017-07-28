<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8">
    <title>主页</title>
    <meta name="description" content="这是一个 index 页面">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="js/amazeui.min.js"></script>
    <link rel="stylesheet" href="/css/amazeui.min.css" />
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/app.css">
    <script src="/js/echarts.min.js"></script>
    <script type="text/javascript">



    </script>
</head>

<body  data-type="login">

    <div class="am-g myapp-login">
        <div class="myapp-login-logo-block  tpl-login-max">
            <div class="myapp-login-logo-text">
                <div class="myapp-login-logo-text">
                    天空云<span> 管理系统</span> <i class="am-icon-skyatlas"></i>

                </div>
            </div>

            <div class="am-u-sm-8 login-font" style="float: right;">
                <span class="" style="float: right;margin-right: 20%;">---&nbsp;&nbsp;开发版2017-04-13</span>
            </div>

            <div class="am-u-sm-10 login-am-center">
                <form class="am-form">
                    <fieldset>
                        <div class="am-form-group">
                            <input type="email" class="" id="doc-ipt-email-1" placeholder="请输入邮箱">
                        </div>
                        <div class="am-form-group">
                            <input type="password" class="" id="doc-ipt-pwd-1" placeholder="请输入密码">
                        </div>
                        <p><button type="submit" class="am-btn am-btn-default">登录</button></p>
                    </fieldset>
                </form>
            </div>
            <div class="login-font">
                <i>注册</i>丨<span>介绍</span>
                <div>
                    <form action="/user/testWrongFormat" method="post" enctype="multipart/form-data">
                        <input type="file" name="excelFile" />
                        <input type="submit" value="上传" />
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>

</html>