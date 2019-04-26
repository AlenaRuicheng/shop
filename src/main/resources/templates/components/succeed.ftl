<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SHOP - 卖家端</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="alert alert-dismissable alert-success">
                    <h4>
                        <strong>${msg!""} </strong>
                    </h4><a href="${redirect_uri}" class="alert-link">3s后自动跳转</a>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    setTimeout('location.href="${redirect_uri}"', 3000);
</script>
</html>